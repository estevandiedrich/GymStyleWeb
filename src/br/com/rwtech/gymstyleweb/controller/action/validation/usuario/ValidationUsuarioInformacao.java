package br.com.rwtech.gymstyleweb.controller.action.validation.usuario;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstylecore.model.util.Validador;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.usuario.UsuarioAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationMessage;
import java.util.Calendar;
import java.util.List;
import org.mentawai.core.*;
import org.mentawai.filter.*;
import org.mentawai.rule.*;
import org.mentawai.validation.Validator;

/**
 *
 * @author Éder Faria
 */
public class ValidationUsuarioInformacao extends ValidationFilter {

    private static final String USUARIO = "usuario";
    private static final String MATRICULA = "matricula";
    private static final String CPF = "cpf";
    private Output output = null;
    private Input input = null;

    public void prepareValidator(Validator val, Action action, String innerAction) {
        output = action.getOutput();
        input = action.getInput();
        if (isPost(action) && input.getValue(USUARIO) != null) {
            Long idUsuario = input.getLong("id");
            val.add(USUARIO, new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
            val.add(USUARIO, new StringRule(6, 52), ValidationMessage.getCampoMinimo6Maximo52Caracteres());

            val.add("sexo", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
            val.add("dataNascimentoFormat", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());

            if (input.getValue(USUARIO) == null || input.getString(USUARIO).isEmpty()) {
                openDivDadosPessoais();
            } else {
                if (!Validador.isValidName(input.getString(USUARIO))) {
                    val.add(USUARIO, new StringRule(1, 1), ValidationMessage.getInformeNomeValido());
                    openDivDadosPessoais();
                }
            }
            if (input.getValue("sexo") == null || input.getString("sexo").isEmpty()) {
                openDivDadosPessoais();
            }
            val.add("dataNascimentoFormat", new StringRule(10, 10), ValidationMessage.getPreenchaTodoCampo());
            String dataNascimento = input.getString("dataNascimentoFormat");
            String[] data = dataNascimento.split("/");
            if (dataNascimento != null && !dataNascimento.isEmpty()
                    && input.getString("dataNascimentoFormat").length() < 10) {
                openDivDadosPessoais();
            } else {
                if (dataNascimento == null || dataNascimento.isEmpty()) {
                    openDivDadosPessoais();
                } else {
                    if (!Validador.isValidData(dataNascimento)) {
                        val.add("dataNascimentoFormat", new StringRule(1, 1), ValidationMessage.getDataInvalida());
                    } else {
                        Calendar dataNasc = CalendarUtil.setDateCalendar(dataNascimento);
                        if (dataNasc.after(Calendar.getInstance())) {
                            val.add("dataNascimentoFormat", new StringRule(1, 1), ValidationMessage.getInformeDataInferiorDataAtual());
                        }
                    }
                }
            }
            if (data.length != 3) {
                val.add("dataNascimentoFormat", new StringRule(1, 1), ValidationMessage.getDataInvalida());//forçar a validar uma data inválida
            }

            //Validador de MATRICULA
            if ((input.getValue(MATRICULA) == null || input.getString(MATRICULA).isEmpty())) {
                val.add(MATRICULA, new RequiredFieldRule(), ValidationMessage.getInformeMatricula());
                openDivDadosPessoais();
            } else {
                if (input.getInt("matricula") == 0) {
                    val.add(MATRICULA, new IntegerRule(1), "<font class='errors2'>Matricula Inválida</font>");
                    openDivDadosPessoais();
                } else {
                    List<Usuario> usuarios = ServiceLocator.getUsuarioService().readMatricula(input.getString(MATRICULA), idUsuario);
                    if (usuarios != null && !usuarios.isEmpty()) {
                        val.add(MATRICULA, new IntegerRule(Integer.MAX_VALUE), "Matricula Existente - Aluno:<br> <a class='errors2' href='" + Ac.USUARIO_UPDATE_INFORMACAO + ".do?id=" + usuarios.get(0).getId() + "'>'" + usuarios.get(0).getUsuario() + "'</a>");
                        openDivDadosPessoais();
                    }
                }
            }

            //Validador de CPF
            if ((input.getValue(CPF) == null
                    || input.getString(CPF).isEmpty())) {
                val.add(CPF, new RequiredFieldRule(), ValidationMessage.getInformeCpf());
                openDivDadosPessoais();
            } else if ((input.getValue(CPF) != null || !input.getString("cpf").isEmpty())) {
                val.add(CPF, new StringRule(14, 14), ValidationMessage.getPreenchaTodoCampo());
                if (input.getString(CPF).length() < 14) {
                    openDivDadosPessoais();
                }
            }

            //Validador de CPF
            String cpf = (String) input.getValue(CPF);
            //val.add("cpf", new CPFRule(), ValidationMessage.getCpfInvalido());
            if (cpf != null && !cpf.isEmpty()) {
                if (Validador.existCPF(cpf, idUsuario)) {
//                    output.setValue("cpfError", ValidationMessage.getCpfExistente());//se cpf existente eu abro a div
                    openDivDadosPessoais();
                } else {
                    if (!Validador.isValidCPF(cpf)) {
                        val.add(CPF, new StringRule(1, 1), ValidationMessage.getCpfInvalido());
                    }
                }
            }

            //Validador de Email
            String email = (String) input.getValue("email");
            if (email != null && !email.isEmpty()) {
                val.add("email", new EmailRule(), ValidationMessage.getEmailInvalido());
                if (!Validador.isValidEmail(email)) {
                    openDivContato();
                }
            }

//            if (input.getValue("telefone") != null) {
//                String telefone = input.getString("telefone");
//                if (!telefone.isEmpty() && telefone.length() < 13) {
//                    val.add("telefone", new StringRule(1, 1), ValidationMessage.getPreenchaTodoCampo());
//                    setAba("eventoInformacoes");
//                    output.setValue("var3", 2);
//                } else if (telefone.length() == 13) {
//                    if (!telefone.matches("^\\(\\d{2}\\)\\d{4}-\\d{4}$")) {// se for diferente de um telefone normal
//                        val.add("telefone", new StringRule(1, 1), ValidationMessage.getInformeTelefoneValido());
//                    }
//                } else if (telefone.length() == 14) {
//                    if (!telefone.matches("^\\(\\d{2}\\)\\d{5}-\\d{4}$")) {// se for diferente de um telefone normal
//                        val.add("telefone", new StringRule(1, 1), ValidationMessage.getInformeTelefoneValido());
//                    }
//                }
//            }

            output.setValue("id", idUsuario);

            UsuarioAction.preload(output, input);

            output.setValue("retornoVerificaCpf", input.getValue("retornoVerificaCpf"));
            output.setValue("retornoVerificaMatricula", input.getValue("retornoVerificaMatricula"));
            output.setValue(MATRICULA, input.getValue(MATRICULA));
            output.setValue("dataNascimentoFormat", input.getValue("dataNascimentoFormat"));

            String[] cpfs = input.getStrings("infoCpf");
            if (cpfs != null) {
                output.setValue("infoCpf", input.getStrings("infoCpf")[0]);
                String cpf2 = input.getStrings("infoCpf")[0];
                output.setValue("cpf", input.getStrings("infoCpf")[0]);
            }
        }
    }

    /* Via onload a div é aberta para mostrar o cadastro de dados pessoais */
    public void openDivDadosPessoais() {
        output.setValue("var1", 2);
    }

    /* Via onload a div é aberta para mostrar o cadastro de Localidades */
    public void openDivLocalidade() {
        output.setValue("var2", 2);
    }

    /* Via onload a div é aberta para mostrar o cadastro de Contato */
    public void openDivContato() {
        output.setValue("var3", 2);
    }
}