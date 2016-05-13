package br.com.rwtech.gymstyleweb.controller.action.validation;

import br.com.rwtech.gymstylecore.model.util.Validador;
import br.com.rwtech.gymstyleweb.controller.action.modalidade.ModalidadeAction;
import java.util.Calendar;
import org.mentawai.core.Action;
import org.mentawai.core.Input;
import org.mentawai.core.Output;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.RequiredFieldRule;
import org.mentawai.rule.StringRule;
import org.mentawai.validation.Validator;

/**
 *
 * @author Éder Faria
 */
public class ValidationModalidade extends ValidationFilter {

    private String[] dias = new String[]{"domingo", "segunda", "terca", "quarta", "quinta", "sexta", "sabado", "feriado"};
    private Output output;
    private Input input;

    public void prepareValidator(Validator val, Action action, String innerAction) {
        output = action.getOutput();
        input = action.getInput();
        int var1 = 1;
        int var2 = 1;
        int var3 = 1;
        int horarioImport = 1;
        int horarioNovo = 1;
        if (isPost(action)) {
            val.add("modalidade", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
            //String nome = input.getString("modalidade");

            Boolean status = true;
            var1 = 2;
            for (int i = 1; i <= 7; i++) {
                String nomeValor = "valor" + i + "" + i;
                if (Validador.getMoney(input.getString(nomeValor)) != null && Validador.getMoney(input.getString(nomeValor)) > 0) {
                } else {
                    validaMoeda(nomeValor, val);//se getMoney retornar null é pq nao é valido a moeda
                }
                if (input.getValue(nomeValor) != null && !input.getString(nomeValor).isEmpty()) {
                    status = false;
                    var1 = 1;
                }
            }
            if (status) {
                val.add("valorErrors", new RequiredFieldRule(), "Informe ao menos um valor");
            }
            output.setValue("horariosNovo", false);
            output.setValue("horariosImportar", false);

            String novo = "";
            if (action.getInput().getValue("idHorario") != null) {
                horarioImport = 2;
                val.add("identificacao", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
                output.setValue("todos", false);
                output.setValue("domingo", false);
                output.setValue("segunda", false);
                output.setValue("terca", false);
                output.setValue("quarta", false);
                output.setValue("quinta", false);
                output.setValue("sexta", false);
                output.setValue("sabado", false);
                output.setValue("feriado", false);

                output.setValue("horariosImportar", true);

                boolean domingo = false;
                boolean segunda = false;
                boolean terca = false;
                boolean quarta = false;
                boolean quinta = false;
                boolean sexta = false;
                boolean sabado = false;
                boolean feriado = false;

                String dia = "domingo";
                if (input.getValue(dia) != null) {
                    domingo = true;
                    output.setValue(dia, true);
                }
                dia = "segunda";
                if (input.getValue(dia) != null) {
                    segunda = true;
                    output.setValue(dia, true);
                }
                dia = "terca";
                if (input.getValue(dia) != null) {
                    terca = true;
                    output.setValue(dia, true);
                }
                dia = "quarta";
                if (input.getValue(dia) != null) {
                    quarta = true;
                    output.setValue(dia, true);
                }
                dia = "quinta";
                if (input.getValue(dia) != null) {
                    quinta = true;
                    output.setValue(dia, true);
                }
                dia = "sexta";
                if (input.getValue(dia) != null) {
                    sexta = true;
                    output.setValue(dia, true);
                }
                dia = "sabado";
                if (input.getValue(dia) != null) {
                    sabado = true;
                    output.setValue(dia, true);
                }
                dia = "feriado";
                if (input.getValue(dia) != null) {
                    feriado = true;
                    output.setValue(dia, true);
                }

                if (domingo && segunda && terca && quarta && quinta && sexta && sabado && feriado) {
                    output.setValue("todos", true);
                }

            } else {
                horarioNovo = 2;
                novo = "Novo";
                //output.setValue("onload", false);//para não aparecer o form do importar
                val.add("identificacaoNovo", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());

                output.setValue("horariosNovo", true);
                output.setValue("todosNovo", false);
                output.setValue("domingoNovo", false);
                output.setValue("segundaNovo", false);
                output.setValue("tercaNovo", false);
                output.setValue("quartaNovo", false);
                output.setValue("quintaNovo", false);
                output.setValue("sextaNovo", false);
                output.setValue("sabadoNovo", false);
                output.setValue("feriadoNovo", false);

                boolean domingo = false;
                boolean segunda = false;
                boolean terca = false;
                boolean quarta = false;
                boolean quinta = false;
                boolean sexta = false;
                boolean sabado = false;
                boolean feriado = false;
                String dia = "domingoNovo";
                if (action.getInput().getValue(dia) != null) {
                    domingo = true;
                    output.setValue(dia, true);
                }
                dia = "segundaNovo";
                if (input.getValue(dia) != null) {
                    segunda = true;
                    output.setValue(dia, true);
                }
                dia = "tercaNovo";
                if (input.getValue(dia) != null) {
                    terca = true;
                    output.setValue(dia, true);
                }
                dia = "quartaNovo";
                if (input.getValue(dia) != null) {
                    quarta = true;
                    output.setValue(dia, true);
                }
                dia = "quintaNovo";
                if (input.getValue(dia) != null) {
                    quinta = true;
                    output.setValue(dia, true);
                }
                dia = "sextaNovo";
                if (input.getValue(dia) != null) {
                    sexta = true;
                    output.setValue(dia, true);
                }
                dia = "sabadoNovo";
                if (input.getValue(dia) != null) {
                    sabado = true;
                    output.setValue(dia, true);
                }
                dia = "feriadoNovo";
                if (input.getValue(dia) != null) {
                    feriado = true;
                    output.setValue(dia, true);
                }

                if (domingo && segunda && terca && quarta && quinta && sexta && sabado && feriado) {
                    output.setValue("todosNovo", true);
                }

            }
            //===========================================================================================
            //========   COMUM PARA OS DOIS FORMS. NOVO OU IMPORTADO   ==================================
            //===========================================================================================
            output.setValue("onload", false);//para aparecer o form que anteriormente foi importado
            if (var1 == 1) {
                if (input.getValue("modalidade") == null || input.getString("modalidade").isEmpty()) {
                    var1 = 2;
                } else if (input.getValue("identificacao") != null && input.getString("identificacao").isEmpty()) {
                    var2 = 2;
                    output.setValue("onload", true);//para aparecer o form que anteriormente foi importado
                } else if (input.getValue("identificacao") == null && input.getString("identificacaoNovo").isEmpty()) {
                    var2 = 2;
                } else if (input.getStrings("dispositivo") == null || input.getStrings("dispositivo").length == 0) {
                    var3 = 2;
                }
            }
            if (input.getValue("identificacao") != null && !input.getString("identificacao").isEmpty()) {
                output.setValue("onload", true);//para aparecer o form que anteriormente foi importado
            }

            String[] dispositivos = input.getStrings("dispositivo");
            boolean temCatracaSelecionada = false;
            if (dispositivos != null) {
                if (dispositivos.length == 0) {
                    temCatracaSelecionada = true;
                }
            }
            if (!temCatracaSelecionada) {
                output.setValue("catracasError", "Selecione ao menos uma catraca!");
            }

            output.setValue("var1", var1);
            output.setValue("var2", var2);
            output.setValue("var3", var3);

            //O primeiro horário inválido ele ja para o loop,valida e mostra a mensagem
            for (int i = 1; i <= 8; i++) {
                int j = i + 1;
                //faz a varredura em todos os dias das semana
                for (String dia : dias) {
                    String diaUm = dia + i + novo;//coluna impar
                    String diaDois = dia + j + novo;//coluna par
                    if (validaHora(diaUm, diaDois, val, novo)) {
                        break;
                    }
                }
                i++;//incrementa o i para apontar para a coluna 2 e entrando no for ele aponta para a 3
            }

            boolean temHora = false;
            //faz a varredura coluna os valores no input e output
            for (int i = 1; i <= 8; i++) {
                for (String dia : dias) {
                    String diaColuna = dia + i + novo;
                    if (input.getValue(diaColuna) != null && !input.getString(diaColuna).isEmpty()) {
                        temHora = true;
                        output.setValue(dia + novo, true);
                        input.setValue(dia + novo, true);//tem q setar o input, para se acaso passou em todas as validações chegar no ModalidadeCreate
                    }
                }
            }

            if (!temHora) {
                val.add("horario" + novo + "Error", new RequiredFieldRule(), ValidationMessage.getInformeHorario());
                if (var1 == 1) {
                    output.setValue("var2", 2);
                }
                if (novo.isEmpty()) {
                    horarioImport = 2;
                    horarioNovo = 1;
                } else {
                    horarioImport = 1;
                    horarioNovo = 2;
                }
            }

            if (input.getValue("identificacao" + novo) == null || input.getString("identificacao" + novo).isEmpty()) {
                if (novo.isEmpty()) {
                    horarioImport = 2;
                    horarioNovo = 1;
                } else {
                    horarioImport = 1;
                    horarioNovo = 2;
                }
            }
            output.setValue("horarioImport", horarioImport);
            output.setValue("horarioNovo", horarioNovo);

            ModalidadeAction.preload(output, input.getLong("id"));
        }
    }

    public Boolean validaHora(String diaInicio, String diaFim, Validator val, String novo) {
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
            val.add("horario" + novo + "Error", new RequiredFieldRule(), mensagem);
            output.setValue("var2", 2);
            return true;
        }
        return false;
    }

    public void validaMoeda(String campo, Validator val) {
        if (input.getValue(campo) != null && !input.getString(campo).isEmpty()) {
            if (!Validador.isValidMoney(input.getString(campo))) {
                val.add(campo, new StringRule(1, 1), ValidationMessage.getImage("Informe Valor Válido"));
            }
        }
    }
}
