package br.com.rwtech.gymstyleweb.controller.action.validation.usuario;

import br.com.rwtech.gymstylecore.model.util.Validador;
import br.com.rwtech.gymstyleweb.controller.action.funcionario.update.FuncionarioUpdateAcessoAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationMessage;
import java.util.Calendar;
import org.mentawai.core.Action;
import org.mentawai.core.Input;
import org.mentawai.core.Output;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.RequiredFieldRule;
import org.mentawai.validation.Validator;

/**
 *
 * @author Software1
 */
public class ValidationFuncionarioAcesso extends ValidationFilter {

    private String[] dias = new String[]{"domingo", "segunda", "terca", "quarta", "quinta", "sexta", "sabado", "feriado"};
    private Output output = null;
    private Input input = null;

    public void prepareValidator(Validator val, Action action, String innerAction) {
        output = action.getOutput();
        input = action.getInput();
        if (isPost(action) && input.getValue("temAcesso") != null) {
            Long idUsuario = input.getLong("id");
            if (input.getValue("temAcesso") != null && !input.getString("temAcesso").isEmpty() && input.getString("temAcesso").equalsIgnoreCase("sim")) {
                Boolean livre = input.getBoolean("livre");
                if (!livre) {
                    //O primeiro horário inválido ele ja para o loop,valida e mostra a mensagem
                    for (int i = 1; i <= 8; i++) {
                        int j = i + 1;
                        //faz a varredura em todos os dias das semana
                        for (String dia : dias) {
                            String diaUm = dia + i;//coluna impar
                            String diaDois = dia + j;//coluna par
                            if (validaHora(diaUm, diaDois, val)) {
                                break;
                            }
                        }
                        i++;//incrementa o i para apontar para a coluna 2 e entrando no for ele aponta para a 3
                    }

                    boolean temHora = false;
                    //faz a varredura coluna os valores no input e output
                    for (int i = 1; i <= 8; i++) {
                        for (String dia : dias) {
                            String diaColuna = dia + i;
                            if (input.getValue(diaColuna) != null && !input.getString(diaColuna).isEmpty()) {
                                temHora = true;
                                break;
                            }
                        }
                    }

                    if (!temHora) {
                        val.add("horarioError", new RequiredFieldRule(), ValidationMessage.getInformeHorario());
                    }
                }

                String[] dispositivos = input.getStrings("dispositivo");
                boolean temCatracaSelecionada = false;
                if (dispositivos != null) {
                    if (dispositivos.length > 0) {
                        temCatracaSelecionada = true;
                    }
                }
                if (!temCatracaSelecionada) {
                    val.add("catracasError", new RequiredFieldRule(), "Selecione ao menos uma catraca!");
                }
            }

            output.setValue("id", idUsuario);
            output.setValue("idAcesso", input.getValue("idAcesso"));
            FuncionarioUpdateAcessoAction.preload(output, input);
        }
    }

    public Boolean validaHora(String diaInicio, String diaFim, Validator val) {
        Boolean retornoUm = false;//nao tem erro
        Boolean retornoDois = false;

        String mensagem = "";

        String horaInicio = null;
        if (input.getValue(diaInicio) != null) {
            horaInicio = input.getString(diaInicio);
            if (!horaInicio.isEmpty()) {
                //expressao regular para validar hora
                if (!Validador.isValidHour(horaInicio)) {
                    retornoUm = true;
                    mensagem = ValidationMessage.getInformeHorarioValido();
                }
            }
        }
        String horaFim = null;
        if (input.getValue(diaFim) != null) {
            horaFim = input.getString(diaFim);
            if (!horaFim.isEmpty()) {
                if (!Validador.isValidHour(horaFim)) {
                    retornoDois = true;
                    if (mensagem.isEmpty()) {
                        mensagem = ValidationMessage.getInformeHorarioValido();
                    }
                }
            }
        }

        //verifica se foi informado o inicio e nao ou fim e vice versa
        if ((horaInicio == null || horaInicio.isEmpty()) && (horaFim != null && !horaFim.isEmpty())) {
            mensagem = "Preencha corretamente os períodos informados";
            retornoUm = true;
        } else if ((horaFim == null || horaFim.isEmpty()) && (horaInicio != null && !horaInicio.isEmpty())) {
            mensagem = "Preencha corretamente os períodos informados";
            retornoUm = true;
        } else {
            if (!retornoUm && !retornoDois) {
                if (!horaInicio.isEmpty() && !horaFim.isEmpty()) {
                    //Conferir se hora de início é posterior a de fim
                    String[] horas = horaInicio.split(":");
                    int horaUm = Integer.parseInt(horas[0]);
                    int minUm = Integer.parseInt(horas[1]);
                    Calendar hora1 = Calendar.getInstance();
                    hora1.set(0, 0, 0, horaUm, minUm);

                    horas = horaFim.split(":");
                    int horaDois = Integer.parseInt(horas[0]);
                    int minDois = Integer.parseInt(horas[1]);
                    Calendar hora2 = Calendar.getInstance();
                    hora2.set(0, 0, 0, horaDois, minDois);

                    if (hora1.after(hora2)) {
                        retornoUm = true;
                        mensagem = "Informe horário de entrada anterior ao de saída";
                    }
                }
            }
        }
        if (!retornoUm && !retornoDois) {
            return false;//nao tem erro
        }
        if (retornoUm || retornoDois) {
            val.add("horarioError", new RequiredFieldRule(), mensagem);
            output.setValue("var2", 2);
            return true;
        }
        return false;
    }
}