package br.com.rwtech.gymstyleweb.controller.action.validation.usuario;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstylecore.model.util.Validador;
import br.com.rwtech.gymstyleweb.controller.action.funcionario.FuncionarioAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationMessage;
import java.util.Calendar;
import org.mentawai.core.Action;
import org.mentawai.core.Input;
import org.mentawai.core.Output;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.EmailRule;
import org.mentawai.rule.EqualRule;
import org.mentawai.rule.RequiredFieldRule;
import org.mentawai.rule.StringRule;
import org.mentawai.validation.Validator;

/**
 * @author Éder Faria
 */
public class ValidationFuncionarioInformacao extends ValidationFilter {

    private static String USUARIO = "usuario";
    private static String DATA_NASCIMENTO_FORMAT = "dataNascimentoFormat";
//    private static String LOGIN = "login";
//    private static String SENHA = "senha";
//    private static String SENHA2 = "senha2";
    //private static final String MATRICULA = "matricula";
    private static final String CPF = "cpf";
    private static final String VAR1 = "var1";
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
            val.add(DATA_NASCIMENTO_FORMAT, new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());

            if (input.getValue(USUARIO) == null || input.getString(USUARIO).isEmpty()) {
                output.setValue(VAR1, 2);
            } else {
                if (!Validador.isValidName(input.getString(USUARIO))) {
                    val.add(USUARIO, new StringRule(1, 1), ValidationMessage.getInformeNomeValido());
                    output.setValue(VAR1, 2);
                }
            }
            if (input.getValue("sexo") == null || input.getString("sexo").isEmpty()) {
                output.setValue(VAR1, 2);
            }
            val.add(DATA_NASCIMENTO_FORMAT, new StringRule(10, 10), ValidationMessage.getPreenchaTodoCampo());
            String dataNascimento = input.getString(DATA_NASCIMENTO_FORMAT);
            String[] data = dataNascimento.split("/");
            if (dataNascimento != null && !dataNascimento.isEmpty()
                    && input.getString(DATA_NASCIMENTO_FORMAT).length() < 10) {
                output.setValue(VAR1, 2);//se data de nascimento for incompleta ou vazia
            } else {
                if (dataNascimento == null || dataNascimento.isEmpty()) {
                    output.setValue(VAR1, 2);//se data de nascimento for incompleta ou vazia
                } else {
                    if (!Validador.isValidData(dataNascimento)) {
                        val.add(DATA_NASCIMENTO_FORMAT, new StringRule(1, 1), ValidationMessage.getDataInvalida());
                    } else {
                        Calendar dataNasc = CalendarUtil.setDateCalendar(dataNascimento);
                        if (dataNasc.after(Calendar.getInstance())) {
                            val.add(DATA_NASCIMENTO_FORMAT, new StringRule(1, 1), ValidationMessage.getInformeDataInferiorDataAtual());
                        }
                    }
                }
            }
            if (data.length != 3) {
                val.add(DATA_NASCIMENTO_FORMAT, new StringRule(1, 1), ValidationMessage.getDataInvalida());//forçar a validar uma data inválida
            }

            //Validador de CPF
            if ((input.getValue(CPF) == null || input.getString(CPF).isEmpty())) {
                val.add(CPF, new RequiredFieldRule(), ValidationMessage.getInformeCpf());
                output.setValue(VAR1, 2);
            } else if ((input.getValue(CPF) != null || !input.getString("cpf").isEmpty())) {
                val.add(CPF, new StringRule(14, 14), ValidationMessage.getPreenchaTodoCampo());
                if (input.getString(CPF).length() < 14) {
                    output.setValue(VAR1, 2);
                }
            }

            //Validador de CPF
            String cpf = (String) input.getValue(CPF);
            //val.add("cpf", new CPFRule(), ValidationMessage.getCpfInvalido());
            if (cpf != null && !cpf.isEmpty()) {
                if (Validador.existCPF(cpf, idUsuario)) {
//                    output.setValue("cpfError", ValidationMessage.getCpfExistente());//se cpf existente eu abro a div
                    output.setValue(VAR1, 2);//se cpf existente eu abro a div
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
                    output.setValue("var3", 2);//nao resolvido se abre ou não
                }
            }

//            String login = input.getString("login");
//            String senha = input.getString("senha");
//            Long idTipoUsuario = null;
//            if (input.getValue("idTipoUsuario") == null) {
//                idTipoUsuario = new Long(1);//É porque é do tipo usuário. Campo select na jsp disabled
//            } else {
//                idTipoUsuario = input.getLong("idTipoUsuario");
//            }
            //se o tipo de usuário for diferente de 2=(Aluno) valida login e senha
            //if (idTipoUsuario != 2) {
                //Seto para abrir a aba se acaso algum dos campos tiverem invalidos
//                if ((input.getValue(SENHA) != null || !input.getString(SENHA).isEmpty())
//                        || (input.getValue(SENHA2) != null || !input.getString(SENHA2).isEmpty())
//                        || (input.getValue(LOGIN) != null || !input.getString(LOGIN).isEmpty())) {
//                    if ((input.getString(LOGIN).length() < 6 && !input.getString(LOGIN).isEmpty())
//                            || (input.getString(SENHA).length() < 6 && !input.getString(SENHA).isEmpty())
//                            || (input.getString(SENHA2).length() < 6 && !input.getString(SENHA2).isEmpty())) {
//                        output.setValue("var5", 2);
//                    }
//                }
//
//                if ((login != null && !login.isEmpty())
//                        || (input.getValue(SENHA) != null && !input.getString(SENHA).isEmpty())
//                        || (input.getValue(SENHA2) != null && !input.getString(SENHA2).isEmpty())) {
//                    String loginUsuario = input.getString("loginUsuario");
//                    String idDoUsuario = null;
//                    if (idUsuario != null) {
//                        idDoUsuario = idUsuario.toString();
//                    }
//                    if (!login.equalsIgnoreCase(loginUsuario)) {
//                        val.add(LOGIN, new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
//                        val.add(LOGIN, new StringRule(6, 60), ValidationMessage.getCampoMinimo6Maximo52Caracteres());
//
//                        if (ServiceLocator.getUsuarioService().verificaLogin(login, idDoUsuario)) {
//                            val.add(LOGIN, new StringRule(1, 1), ValidationMessage.getLoginExistente());
//                        }
//                    }
//                    if ((input.getString(SENHA) != null && !input.getString(SENHA).isEmpty())
//                            || (input.getString(SENHA2) != null && !input.getString(SENHA2).isEmpty())
//                            || (!login.equalsIgnoreCase(loginUsuario))) {
//                        val.add(SENHA, new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
//                        val.add(SENHA, new StringRule(6, 60), ValidationMessage.getCampoMinimo6Maximo52Caracteres());
//
//                        val.add(SENHA2, new StringRule(6, 60), ValidationMessage.getCampoMinimo6Maximo52Caracteres());
//                        val.add(SENHA2, new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
//                        val.add(SENHA, new EqualRule(SENHA, SENHA2), ValidationMessage.getSenhaDiferentes());
//                    }
//                }
            //}

            output.setValue("id", idUsuario);

            FuncionarioAction.preload(output, input);
            output.setValue("retornoVerificaCpf", input.getValue("retornoVerificaCpf"));
//            output.setValue("retornoVerificaLogin", input.getValue("retornoVerificaLogin"));
            output.setValue(DATA_NASCIMENTO_FORMAT, input.getValue(DATA_NASCIMENTO_FORMAT));
//            output.setValue("loginAux", input.getValue("loginAux"));
//            output.setValue(LOGIN, input.getValue(LOGIN));
//            output.setValue(SENHA, input.getValue(SENHA));
        }
    }
}