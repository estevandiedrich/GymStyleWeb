package br.com.rwtech.gymstyleweb.controller.action.requisicao;

import br.com.rwtech.gymstylecore.model.DaoLocator;
import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Dispositivo;
import br.com.rwtech.gymstylecore.model.pojo.Log;
import br.com.rwtech.gymstylecore.model.pojo.Requisicao;
import br.com.rwtech.gymstylecore.model.pojo.Resposta;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
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
public class RequisicaoManagerExcluirUsuarioAction extends BaseAction {

    private static int TEMPO_FINAL = RequisicaoUsuarioAction.TIME_EXCLUIR;//== 5 segundos == 20 * 0,250 valor default para um destino
    private static int TEMPO_ATUALIZACAO = TEMPO_FINAL;//== 5 segundos == 20 * 0,250
    private static String ID_PLANO_USUARIO = "idPlanoUsuario";
    private static String ID_USUARIO = "idUsuario";
    private static String ID_REQUISICAO = "idRequisicao";
    private static String CONTADOR = "contador";

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;
        //na primeira vez passa se direto pq o idRequisicao esta no output
        if (input.getValue(ID_REQUISICAO) != null && input.getInt(ID_REQUISICAO) != -1) {
            Long idRequisicao = input.getLong(ID_REQUISICAO);
            Long idPlanoUsuario = input.getLong(ID_PLANO_USUARIO);
            Long idUsuario = input.getLong(ID_USUARIO);
            int contador = input.getInt(CONTADOR);

            Usuario usuario = ServiceLocator.getUsuarioService().readById(idUsuario);
            output.setValue("usuario", usuario);

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
                            output.setValue("dispositivo" + requisicao.getDestino(),"<img src='images/carregandoPeq.gif' title='Sincronizando...'/>");
                        }
                    } else {
                        if (pojo.getMensagemErro() != null && !pojo.getMensagemErro().isEmpty() && !pojo.getMensagemErro().equalsIgnoreCase("USUARIO_NAO_ENCONTRADO")) {
                            output.setValue("dispositivo" + requisicao.getDestino(),
                                    "<div id='status" + requisicao.getId() + "'><a id='sinc_error' title='Não Sincronizado! " + pojo.getMensagemErro() + "' href='#'></a>"
                                    + "<a id='atualizar' title='Reenviar?' href='#' onclick='reenviarRequisicao(" + requisicao.getId() + ")'>Reenviar</a></div>");
                            consequence = ERROR;
                        } else {
                            output.setValue("dispositivo" + requisicao.getDestino(),"<a id='sinc_ok' title='Removido com sucesso!' href='#'></a>");
                            consequence = SUCCESS;
                        }
                        qtde++;//se passar todos aqui ele vai retornar success e parar o loop.
                    }
                }
                if (qtde == lista.size()) {
                    consequence = SUCCESS;
                    output.setValue(CONTADOR, TEMPO_FINAL + 1);// aqui para o loop
                    Usuario usu = ServiceLocator.getUsuarioService().readByIdSimple(idUsuario);
                    ServiceLocator.getLogService().create(new Log(LogAction.getMap(Ac.REQUISICAO_MANAGER_EXCLUIR) + " - " + usu.getUsuario(), LogAction.EXCLUIU, (Usuario) getUserSession()));
                }
                if (!consequence.equals(SUCCESS)) {
                    Contador contime = Contador.getInstance();
                    contime.run();
                }
            } else {
                Contador contime = Contador.getInstance();
                contime.run();
                consequence = LIST;
            }
            output.setValue(ID_REQUISICAO, idRequisicao);
            output.setValue(ID_PLANO_USUARIO, idPlanoUsuario);//esse id será passado para o manager de requisicao buscar os dispositivos a serem atualizados
        } else {
            output.setValue(CONTADOR, 1);//a primeira vez passa-se aqui
            Long idRequisicao = (Long) output.getValue(ID_REQUISICAO);
            Long idPlanoUsuario = (Long) output.getValue(ID_PLANO_USUARIO);
            Long idUsuario = (Long) output.getValue(ID_USUARIO);
            if (idUsuario != null) {
                Usuario usuario = ServiceLocator.getUsuarioService().readById(idUsuario);
                output.setValue("usuario", usuario);
                Set<Dispositivo> dispositivos = ServiceLocator.getUsuarioPlanoService().readDispositivosPlanoUsuario(idPlanoUsuario);
                if (dispositivos != null && !dispositivos.isEmpty()) {
                    for (Dispositivo dispositivo : dispositivos) {
                        Requisicao requisicao = DaoLocator.getRequisicaoDAO().readById(idRequisicao);
                        requisicao.setStatus(Boolean.FALSE);
                        requisicao.setDestino(dispositivo.getId());
                        requisicao.setReferencia(requisicao.getId());
                        ServiceLocator.getRequisicaoService().create(requisicao);
                    }
                }
            } else {
                consequence = ERROR;
            }
            setMensagem();
        }
        output.setValue("tempoFinal", TEMPO_FINAL);

        return consequence;
    }

    public void setMensagem() {
        String msg = "";
        if (session.getAttribute("mensagem") != null) {
            msg = session.getAttribute("mensagem").toString();
            session.setAttribute("mensagem", "");
        }
        if (!msg.equalsIgnoreCase("")) {
            addMessage(msg);
        }
    }
}
