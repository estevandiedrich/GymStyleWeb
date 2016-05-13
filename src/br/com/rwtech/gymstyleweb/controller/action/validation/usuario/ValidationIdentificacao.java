package br.com.rwtech.gymstyleweb.controller.action.validation.usuario;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Permissao;
import br.com.rwtech.gymstylecore.model.util.Validador;
import br.com.rwtech.gymstyleweb.controller.action.funcionario.update.FuncionarioUpdateIdentificacaoAction;
import br.com.rwtech.gymstyleweb.controller.action.usuario.update.UpdateIdentificacaoAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationMessage;
import java.util.List;
import java.util.Map;
import org.mentawai.core.Action;
import org.mentawai.core.Input;
import org.mentawai.core.Output;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.EqualRule;
import org.mentawai.rule.IntegerRule;
import org.mentawai.rule.RequiredFieldRule;
import org.mentawai.rule.StringRule;
import org.mentawai.validation.Validator;

/**
 *
 * @author Éder Faria
 */
public class ValidationIdentificacao extends ValidationFilter {

    private static String LOGIN = "login";
    private static String SENHA = "senha";
    private static String SENHA2 = "senha2";
    private Output output = null;
    private Input input = null;
    private static String CARTAO_PROXIMIDADE = "cartaoProximidade";
    private static String MSG_DIGITAIS = "mensagemDigitais";
    private static int TAM = -5;//Valor abaixo de zero, pois nunca será digitado

    public void prepareValidator(Validator val, Action action, String innerAction) {
        output = action.getOutput();
        input = action.getInput();
        if (isPost(action) && input.getValue(CARTAO_PROXIMIDADE) != null) {
            Long idUsuario = input.getLong("id");

            String cartaoProximidade = input.getString(CARTAO_PROXIMIDADE);
            if ((cartaoProximidade != null && !cartaoProximidade.isEmpty())) {
                //   val.add("cartaoProximidade", new StringRule(10, 10), ValidationMessage.getPreenchaTodoCampo());
                //expressão regular para validar o número 
                if ((cartaoProximidade.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+"))
                        && (!cartaoProximidade.equalsIgnoreCase("0")
                        && !cartaoProximidade.equalsIgnoreCase("00")
                        && !cartaoProximidade.equalsIgnoreCase("000")
                        && !cartaoProximidade.equalsIgnoreCase("0000")
                        && !cartaoProximidade.equalsIgnoreCase("00000")
                        && !cartaoProximidade.equalsIgnoreCase("000000")
                        && !cartaoProximidade.equalsIgnoreCase("0000000")
                        && !cartaoProximidade.equalsIgnoreCase("00000000")
                        && !cartaoProximidade.equalsIgnoreCase("000000000")
                        && !cartaoProximidade.equalsIgnoreCase("0000000000"))) {
                    //se for um numero entra aqui
                    if (Validador.existCartaoProximidade(cartaoProximidade, idUsuario)) {
                        //Isso é para forçar ele entrar
                        val.add(CARTAO_PROXIMIDADE, new IntegerRule(TAM, TAM), ValidationMessage.getCartaoDeOutroAluno(cartaoProximidade, idUsuario));
                        openDivCatraca();
                    }
                } else {
                    val.add(CARTAO_PROXIMIDADE, new StringRule(1, 1), ValidationMessage.getInformeValorNumerico());
                    openDivCatraca();
                }
            }

            if (input.getString(CARTAO_PROXIMIDADE).isEmpty() && input.getInt("tamanhoDigitais") == 0) {
                val.add(CARTAO_PROXIMIDADE, new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
                output.setValue("msgDigitais", ValidationMessage.getMensagem("Informe Digitais ou Cartão de Proximidade!"));
            }

            //se for vazia, é por que está correta, senao é por que tem erro
            if (input.getValue(MSG_DIGITAIS) != null && !input.getString(MSG_DIGITAIS).isEmpty()) {
                val.add(MSG_DIGITAIS, new StringRule(1, 1), input.getString(MSG_DIGITAIS));//retornar a mesma mensagem anteriormente na caixa de texto
            }

            output.setValue("id", idUsuario);

            UpdateIdentificacaoAction.preload(output, input);

            //se tem login é pq é cadastro de funcionario
            if (input.getValue(LOGIN) != null) {
                String login = input.getString(LOGIN);
                String senha = input.getString(SENHA);
                if ((input.getValue(SENHA) != null || !input.getString(SENHA).isEmpty())
                        || (input.getValue(SENHA2) != null || !input.getString(SENHA2).isEmpty())
                        || (input.getValue(LOGIN) != null || !input.getString(LOGIN).isEmpty())) {
                    if ((input.getString(LOGIN).length() < 6 && !input.getString(LOGIN).isEmpty())
                            || (input.getString(SENHA).length() < 6 && !input.getString(SENHA).isEmpty())
                            || (input.getString(SENHA2).length() < 6 && !input.getString(SENHA2).isEmpty())) {
                        output.setValue("varSistema", 2);
                    }
                }
                if (login != null) {
                    if (login.length() == 1) {
                        login = login.replace(" ", "");
                    }

                    if ((!login.isEmpty())
                            || (input.getValue(SENHA) != null && !input.getString(SENHA).isEmpty())
                            || (input.getValue(SENHA2) != null && !input.getString(SENHA2).isEmpty())) {
                        String loginUsuario = input.getString("loginUsuario");
                        String idDoUsuario = null;
                        if (idUsuario != null) {
                            idDoUsuario = idUsuario.toString();
                        }
                        if (!login.equalsIgnoreCase(loginUsuario)) {
                            val.add(LOGIN, new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
                            val.add(LOGIN, new StringRule(6, 60), ValidationMessage.getCampoMinimo6Maximo52Caracteres());

                            if (ServiceLocator.getUsuarioService().verificaLogin(login, idDoUsuario)) {
                                val.add(LOGIN, new StringRule(1, 1), ValidationMessage.getLoginExistente());
                            }
                        }
                        if ((input.getString(SENHA) != null && !input.getString(SENHA).isEmpty())
                                || (input.getString(SENHA2) != null && !input.getString(SENHA2).isEmpty())
                                || (!login.equalsIgnoreCase(loginUsuario))) {
                            val.add(SENHA, new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
                            val.add(SENHA, new StringRule(6, 60), ValidationMessage.getCampoMinimo6Maximo52Caracteres());

                            val.add(SENHA2, new StringRule(6, 60), ValidationMessage.getCampoMinimo6Maximo52Caracteres());
                            val.add(SENHA2, new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
                            val.add(SENHA, new EqualRule(SENHA, SENHA2), ValidationMessage.getSenhaDiferentes());
                        }
                        output.setValue("varSistema", 2);
                    }
                }
                String log = input.getString(LOGIN);
                if (log.isEmpty()) {
                    log = " ";
                }
                output.setValue(LOGIN, log);

                String senhas = input.getString(SENHA);
                if (senhas.isEmpty()) {
                    senhas = " ";
                }

                output.setValue(SENHA, senhas);

                output.setValue("retornoVerificaLogin", input.getValue("retornoVerificaLogin"));
                output.setValue("loginAux", input.getValue("loginAux"));

                Map<String, Permissao> cadastros = FuncionarioUpdateIdentificacaoAction.getPermissoesCadastro();
                List<Permissao> configuracao = ServiceLocator.getPermissaoService().readbyCriteria(Boolean.TRUE, Permissao.GRUPO_CONFIGURACAO);
                List<Permissao> gerenciar = ServiceLocator.getPermissaoService().readbyCriteria(Boolean.TRUE, Permissao.GRUPO_GERENCIAR);
                List<Permissao> relatorio = ServiceLocator.getPermissaoService().readbyCriteria(Boolean.TRUE, Permissao.GRUPO_RELATORIO);

                for (String per : cadastros.keySet()) {
                    if (input.getValue(per + "Read") != null) {
                        output.setValue(per + "ReadCheck", "checked");
                    }
                    if (input.getValue(per + "Manager") != null) {
                        output.setValue(per + "ManagerCheck", "checked");
                    }
                    if (input.getValue(per + "Delete") != null) {
                        output.setValue(per + "DeleteCheck", "checked");
                    }
                }
                checkedRetorno(gerenciar);
                checkedRetorno(relatorio);
                checkedRetorno(configuracao);

                output.setValue(Permissao.GRUPO_CADASTRO, cadastros);
                output.setValue(Permissao.GRUPO_GERENCIAR, gerenciar);
                output.setValue(Permissao.GRUPO_RELATORIO, relatorio);
                output.setValue(Permissao.GRUPO_CONFIGURACAO, configuracao);
            }
        }
    }

    private void checkedRetorno(List<Permissao> list) {
        for (Permissao per : list) {
            if (input.getValue(per.getNome()) != null) {
                output.setValue(per.getNome() + "Check", "checked");
            }
        }
    }

    /* Via onload a div é aberta para mostrar */
    public void openDivSistema() {
        output.setValue("varSistema", 2);
    }

    /* Via onload a div é aberta para mostrar */
    public void openDivCatraca() {
        output.setValue("varCatraca", 2);
    }
}