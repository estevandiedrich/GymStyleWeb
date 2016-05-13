package br.com.rwtech.gymstyleweb.controller.action.usuario.update;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Dedo;
import br.com.rwtech.gymstylecore.model.pojo.ImpressaoDigital;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.service.IdentificacaoService;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.funcionario.FuncionarioAction;
import br.com.rwtech.gymstyleweb.controller.action.requisicao.RequisicaoUsuarioAction;
import br.com.rwtech.gymstyleweb.controller.action.usuario.UsuarioAction;
import java.util.ArrayList;
import java.util.List;
import org.mentawai.core.ApplicationManager;
import org.mentawai.core.BaseAction;
import org.mentawai.core.Input;
import org.mentawai.core.Output;

/**
 *
 * @author Éder Faria
 */
public class UpdateIdentificacaoAction extends BaseAction {

    protected String post(Long idUsuario) {
        String consequence = SHOW;
        String acao = (String) input.getValue(UsuarioAction.ACAO);
        if (acao != null && acao.equals(UsuarioAction.INFORMACAO)) {
            return UsuarioAction.INFORMACAO;
        }

        boolean alterouCartao = false;
        String mudouDigitais = input.getString("mudouDigitais");

        String cartaoProximidade = input.getString("cartaoProximidade");
        String cartaoProximidade2 = input.getString("cartaoProximidade2");
        if ((cartaoProximidade != null) && (cartaoProximidade2 != null)) {
            if (!cartaoProximidade.equalsIgnoreCase(cartaoProximidade2)) {
                session.setAttribute("mensagem", "Cartão de proximidade alterado!");
                alterouCartao = true;
                ServiceLocator.getUsuarioService().updateCartaoProximidade(idUsuario, cartaoProximidade);
            }
        }

        String digitais = input.getString("templates");
        digitais = digitais.replaceAll(" ", "");

        String[] dedos = digitais.split("#");
        
        System.out.println("Excluindo digitais");

        List<ImpressaoDigital>listDigital =new ArrayList<>();
        for (String aux : dedos) {
            
            String[] val = aux.split("\\|");
            if (!val[0].isEmpty() && val.length == 3) {
                ImpressaoDigital impressaoDigital = new ImpressaoDigital();
                impressaoDigital.setDedo(new Dedo(Integer.parseInt(val[0])));
                impressaoDigital.setPrimeiroTemplate(val[1]);
                impressaoDigital.setSegundoTemplate(val[2]);
                listDigital.add(impressaoDigital);
            }
        }
          ServiceLocator.getImpressaoDigitalService().atualizarDigitais(idUsuario, listDigital);
        
//        System.out.println("Excluindo digitais");
//        ServiceLocator.getImpressaoDigitalService().deleteDigitaisUsuario(idUsuario);
//        for (String aux : dedos) {
//            ImpressaoDigital impressaoDigital = new ImpressaoDigital();
//            String[] val = aux.split("\\|");
//            if (!val[0].isEmpty() && val.length == 3) {
//                impressaoDigital.setDedo(new Dedo(Integer.parseInt(val[0])));
//                impressaoDigital.setPrimeiroTemplate(val[1]);
//                impressaoDigital.setSegundoTemplate(val[2]);
//                System.out.println("Criando digitais");
//                //Cria as novas digitais na tabela principal
//                ServiceLocator.getImpressaoDigitalService().create(impressaoDigital, idUsuario);
//            }
//        }
//        System.out.println("Excluindo digitais ESPELHO");
//        //Exclui as digitais da tabela espelho
//        ServiceLocator.getImpressaoDigitalEspelhoService().deleteDigitaisUsuario(idUsuario);

        if (acao != null && acao.equals(UsuarioAction.PLANO)) {
            consequence = UsuarioAction.PLANO;
        } else if (acao != null && acao.equals(FuncionarioAction.ACESSO)) {
            consequence = FuncionarioAction.ACESSO;
        } else {
            if (alterouCartao || !mudouDigitais.isEmpty()) {
                if (RequisicaoUsuarioAction.atualizar(idUsuario, output, (Usuario) getUserSession())) {
                    consequence = UsuarioAction.ATUALIZAR;
                } else {
                    consequence = SUCCESS;
                    session.setAttribute("mensagem", "Alterado com sucesso!");
                }
            } else {
                consequence = SUCCESS;
                session.setAttribute("mensagem", "Alterado com sucesso!");
            }
        }
        if (consequence == null) {
            consequence = Ac.EXCEPTION;
        }
        return consequence;
    }

    public static void preload(Output output, Input input) {
        ApplicationManager.getRealPath();
        Long id = (Long) output.getValue("id");
        if (id != null) {
            if (id != null && id > 0) {
                Usuario pojo = ServiceLocator.getUsuarioService().readById(id);
                if (pojo != null) {
                    output.setValue("pojo", pojo);
                    output.setValue("pojoInfo", pojo);//pojo é para aparecer as informações do usuário em cima na tela
                    output.setValue("infoCpf", pojo.getCpf());//pojo é para aparecer as informações do usuário em cima na tela

                    String login = pojo.getLogin();
                    //Cadastro de usuario
                    output.setValue("loginUsuario", login);
                    output.setValue("loginAux", login);

                    if (input.getValue("login") == null) {
                        if (pojo.getLogin() == null || pojo.getLogin().isEmpty()) {
                            //Setar login com um espaço para não aparecer cache na aplicação web.
                            //Dentro de validação contem esta validação para remover os espaços e ser vazio
                            output.setValue("login", " ");
                            pojo.setLogin(" ");
                        } else {
                            output.setValue("login", pojo.getLogin());
                        }
                        output.setValue("senha", "");
                        pojo.setSenha("");
//                    }else{
//                        pojo.setLogin(input.getString("login"));
//                        pojo.setSenha(input.getString("senha"));
                    }

                    List<ImpressaoDigital> digitais = null;
                    if (input.getValue("templates") == null) {
                        digitais = ServiceLocator.getImpressaoDigitalService().readDigitaisUsuario(id);
                    } else {
                        digitais = ServiceLocator.getImpressaoDigitalEspelhoService().readDigitaisUsuario(id);
                    }
                    if (digitais != null) {
                        String templates = "";
                        for (ImpressaoDigital dig : digitais) {
                            templates += dig.getFormatoTemplateWeb();
                        }
                        output.setValue("templates", templates);
                        if (digitais != null) {
                            output.setValue("digitais", digitais);
                        } else {
                            output.setValue("digitais", new ArrayList<ImpressaoDigital>());
                        }
                    }

                    output.setValue("cartaoProximidade2", pojo.getCartaoProximidade());
                    if (input.getValue("cartaoProximidade") == null) {
                        output.setValue("cartaoProximidade", pojo.getCartaoProximidade());
                    }
                }
                output.setValue("opcoes", IdentificacaoService.getOpcoes());
                output.setValue("opcao", "h");
                output.setValue("dispositivos", IdentificacaoService.getDispositivos());
                output.setValue("dedos", IdentificacaoService.getDedos());
            }
        }
    }
}
