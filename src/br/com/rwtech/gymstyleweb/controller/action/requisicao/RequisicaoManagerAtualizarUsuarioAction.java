package br.com.rwtech.gymstyleweb.controller.action.requisicao;

import br.com.rwtech.gymstylecore.model.ConnectionManagerLog;
import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Dispositivo;
import br.com.rwtech.gymstylecore.model.pojo.Log;
import br.com.rwtech.gymstylecore.model.pojo.Requisicao;
import br.com.rwtech.gymstylecore.model.pojo.Resposta;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.pojo.tipos.TipoRequisicaoResposta;
import br.com.rwtech.gymstylecore.model.util.Contador;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.log.LogAction;
import java.util.List;
import java.util.Set;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class RequisicaoManagerAtualizarUsuarioAction extends BaseAction {

    private static int TEMPO_FINAL = RequisicaoUsuarioAction.TIME;//== 10 segundos == 40 * 0,250 valor default para um destino
    private static int TEMPO_ATUALIZACAO = RequisicaoUsuarioAction.TIME;//== 10 segundos == 40 * 0,250
    private static String MENSAGEM = "mensagem";
    private static String ID_PLANO_USUARIO = "idPlanoUsuario";
    private static String ID_USUARIO = "idUsuario";
    private static String ID_REQUISICAO = "idRequisicao";
    private static String CONTADOR = "contador";

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;
        //na primeira vez passa se direto pq o idRequisicao esta no out put
        if (input.getValue(ID_REQUISICAO) != null && input.getInt(ID_REQUISICAO) != -1) {
            Long idRequisicao = input.getLong(ID_REQUISICAO);
            Long idPlanoUsuario = input.getLong(ID_PLANO_USUARIO);
            Long idUsuario = input.getLong(ID_USUARIO);
            int contador = input.getInt(CONTADOR);

            //----------------------------------------------------------------------------------------------------
            // o loop será encerrado quando o contador for maior q tamanho dos segundos 
            // ou todos responderem com sucesso
            // ai o retorno vai vazio sem o id da requisicao
            Set<Dispositivo> dispositivos = RequisicaoUsuarioAction.getDispositivosById(idUsuario, idPlanoUsuario);
            if (dispositivos != null && !dispositivos.isEmpty()) {
                for (Dispositivo dispositivo : dispositivos) {
                    output.setValue("dispositivo" + dispositivo.getId(),
                            "<img src='images/carregandoPeq.gif' title='Aguardando Sincronização...'/>");
                }
                //TEMPO final de atualização recebe q quantidade de destinos * o tempo de atualização por destino == 10s
                TEMPO_FINAL = dispositivos.size() * TEMPO_ATUALIZACAO;

            } else {
                contador = TEMPO_FINAL;
                output.setValue(CONTADOR, contador);//a primeira vez passa-se aqui
            }

            output.setValue(LIST, dispositivos);
            //----------------------------------------------------------------------------------------------------
            //Verificar dispositivos fora da rede
            if (dispositivos != null && !dispositivos.isEmpty()) {
                int qtdeIpsFora = 0;
                for (Dispositivo dispositivo : dispositivos) {
                    if (dispositivo.foraDaRede()) {
                        qtdeIpsFora++;
                    }
                }
                if (qtdeIpsFora == dispositivos.size()) {
                    contador = TEMPO_FINAL;
                    output.setValue(CONTADOR, contador);//a primeira vez passa-se aqui
                }
            }
            //----------------------------------------------------------------------------------------------------
            consequence = LIST;
            List<Requisicao> lista = ServiceLocator.getRequisicaoService().readRequisicaoByReferencia(idRequisicao);
            if (!lista.isEmpty()) {
                int qtde = 0;
                for (Requisicao requisicao : lista) {
                    Resposta pojo = ServiceLocator.getRespostaService().readByDestino(requisicao.getId());
                    if (pojo == null) {
                        if (contador == TEMPO_FINAL) {
                            output.setValue("dispositivo" + requisicao.getDestino(),
                                    "<div id='status" + requisicao.getId() + "'>"
                                    + "<a id='sinc_error' title='Não Sincronizado!' href='#'></a>"
                                    + "<a id='atualizar' title='Reenviar?' href='#' onclick='reenviarRequisicao(" + requisicao.getId() + ")'>Reenviar</a>"
                                    + "</div>");
                        } else {
                            output.setValue("dispositivo" + requisicao.getDestino(),
                                    "<img src='images/carregandoPeq.gif' title='Sincronizando...'/>");
                        }
                    } else {
                        if (pojo.getTipoResposta().equals(TipoRequisicaoResposta.EXCLUIR_USUARIO)) {
                            if (pojo.getMensagemErro() != null
                                    && !pojo.getMensagemErro().isEmpty()
                                    && !pojo.getMensagemErro().equalsIgnoreCase("USUARIO_NAO_ENCONTRADO")) {
                                output.setValue("dispositivo" + requisicao.getDestino(),
                                        "<div id='status" + requisicao.getId() + "'>"
                                        + "<a id='sinc_error' title='Não Sincronizado! " + pojo.getMensagemErro() + "' href='#'></a>"
                                        + "<a id='atualizar' title='Reenviar?' href='#' onclick='reenviarRequisicao(" + requisicao.getId() + ")'>Reenviar</a>"
                                        + "</div>");
                                consequence = ERROR;
                            } else {
                                output.setValue("dispositivo" + requisicao.getDestino(),
                                        "<div id='status" + requisicao.getId() + "'><a id='sinc_ok' title='Sincronizado com sucesso!' href='#' onclick=\"reenviarRequisicao(" + requisicao.getId() + ")\"></a></div>");
                                consequence = SUCCESS;
                            }
                        } else {
                            if (pojo.getMensagemErro() != null && !pojo.getMensagemErro().isEmpty()) {
                                output.setValue("dispositivo" + requisicao.getDestino(),
                                        "<div id='status" + requisicao.getId() + "'>"
                                        + "<a id='sinc_error' title='Não Sincronizado! " + pojo.getMensagemErro() + "' href='#'></a>"
                                        + "<a id='atualizar' title='Reenviar?' href='#' onclick='reenviarRequisicao(" + requisicao.getId() + ")'>Reenviar</a>"
                                        + "</div>");
                                consequence = ERROR;
                            } else {
                                output.setValue("dispositivo" + requisicao.getDestino(),
                                        "<div id='status" + requisicao.getId() + "'><a id='sinc_ok' title='Sincronizado com sucesso!' href='#' onclick=\"reenviarRequisicao(" + requisicao.getId() + ")\"></a></div>");
                                consequence = SUCCESS;
                            }
                        }
                        qtde++;//se passar todos aqui ele vai retornar success e parar o loop.
                    }
                }
                if (qtde == lista.size()) {
                    consequence = SUCCESS;
                    output.setValue(CONTADOR, TEMPO_FINAL + 1);// aqui para o loop acima do contador no manager é q chama essa action
                    ServiceLocator.getUsuarioService().updateSincronizado(idUsuario, Boolean.TRUE);
                    ServiceLocator.getUsuarioService().atualizarCartaoCatraca(idUsuario);

                    Usuario usuario = ServiceLocator.getUsuarioService().readByIdSimple(idUsuario);
                    //Gera o log de sincronização aqui
                    if (usuario != null) {
                        ServiceLocator.getLogService().create(ConnectionManagerLog.getInstance().getConnection(),
                                new Log(LogAction.getMap(Ac.ATUALIZAR_USUARIO) + " - " + usuario.getUsuario(), LogAction.ATUALIZOU, (Usuario) getUserSession()));
                    }

                }
                if (!consequence.equalsIgnoreCase(SUCCESS)) {
                    Contador cont = Contador.getInstance();
                    cont.run();
                }
            } else {
                consequence = LIST;
                Contador cont = Contador.getInstance();
                cont.run();
                if (contador == TEMPO_FINAL) {
                    Requisicao req = ServiceLocator.getRequisicaoService().readById(idRequisicao);//busca a requisicao mãe
                    //em seguida ele seta a mae para true para o nucleo nao criar
                    req.setStatus(Boolean.TRUE);
                    req.setDestino(null);
                    ServiceLocator.getRequisicaoService().update(req);

                    //req.setStatus(Boolean.FALSE);// Aqui ele volta para o status false para criar apenas as requisicoes filhas
                    //Manter o status de requisicoes filhas para true mesmo e quando reenviar atualização
                    //O nucleo verifica resposta para as novas criadas
                    req.setReferencia(req.getId());// Aponta a referencia pra ela mesma, para a seguir criar as novas requisicoes
                    for (Dispositivo dispositivo : dispositivos) {
                        req.setDestino(dispositivo.getId());
                        Requisicao reqAux = ServiceLocator.getRequisicaoService().create(req);
                        //aqui ele cria as requisicoes filhas
//                        output.setValue("dispositivo" + dispositivo.getId(),
//                                "<div id='status" + reqAux.getId() + "'>"
//                                + "<a id='sinc_error' title='Verifique se o Nucleo está rodando.Selecione para reenviar novamente!'"
//                                + " href='requisicaoAtualizarUsuario.do?id=" + idUsuario + "'>Reenviar</a></div>");
                        output.setValue("dispositivo" + dispositivo.getId(),
                                "<div id='status" + reqAux.getId() + "'>"
                                + "<a id='sinc_error' title='Verifique se o GymStyleCore está rodando.Selecione para reenviar novamente!!' href='#'></a>"
                                + "<a id='atualizar' title='Reenviar?' href='#' onclick='reenviarRequisicao(" + reqAux.getId() + ")'>Reenviar</a>"
                                + "</div>");
                    }
                }
            }
            output.setValue(ID_REQUISICAO, idRequisicao);
            output.setValue(ID_PLANO_USUARIO, idPlanoUsuario);//esse id será passado para o manager de requisicao buscar os dispositivos a serem atualizados
            output.setValue(ID_USUARIO, idUsuario);//esse id será passado para o manager de requisicao buscar os dispositivos a serem atualizados
            output.setValue(CONTADOR, contador);
        } else {
            Long idUsuario = input.getLong("id");
            if (idUsuario == null || idUsuario == -1) {
                idUsuario = input.getLong(ID_USUARIO);//ta vindo de um outro cenário onde o id 
            }
            Usuario usuario = ServiceLocator.getUsuarioService().readById(idUsuario);
            output.setValue("usuario", usuario);//a primeira vez passa-se aqui
            output.setValue(CONTADOR, 1);//a primeira vez passa-se aqui
            ServiceLocator.getUsuarioService().updateSincronizado(idUsuario, Boolean.FALSE);
            setMensagem();
        }
        output.setValue("tempoFinal", TEMPO_FINAL);

        return consequence;
    }

    public void setMensagem() {
        String msg = "";
        if (session.getAttribute(MENSAGEM) != null) {
            msg = session.getAttribute(MENSAGEM).toString();
            session.setAttribute(MENSAGEM, "");
        }
        if (!msg.equalsIgnoreCase("")) {
            addMessage(msg);
        }
    }
}
