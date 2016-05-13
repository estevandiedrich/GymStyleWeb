package br.com.rwtech.gymstyleweb.controller.action.requisicao;

import br.com.rwtech.gymstylecore.model.ConnectionManagerLog;
import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Log;
import br.com.rwtech.gymstylecore.model.pojo.Requisicao;
import br.com.rwtech.gymstylecore.model.pojo.Resposta;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.pojo.tipos.TipoRequisicaoResposta;
import br.com.rwtech.gymstylecore.model.util.Contador;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.log.LogAction;
import java.util.Calendar;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class RequisicaoReenviarAction extends BaseAction {

    private static int TAM = RequisicaoUsuarioAction.TIME;

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;
        Long idRequisicao = input.getLong("idRequisicao");
        output.setValue("idRequisicao", idRequisicao);

        Requisicao requisicao = ServiceLocator.getRequisicaoService().readById(idRequisicao);
        requisicao.setStatus(Boolean.FALSE);
        requisicao.setDataHora(Calendar.getInstance());
        ServiceLocator.getRequisicaoService().create(requisicao);

        ServiceLocator.getUsuarioService().updateSincronizado((Long) requisicao.getParametro(), Boolean.FALSE);

        int i = 0;
        while (i <= TAM) {
            Resposta pojo = ServiceLocator.getRespostaService().readByDestino(requisicao.getId());
            if (pojo != null) {
                if (pojo.getTipoResposta().equals(TipoRequisicaoResposta.EXCLUIR_USUARIO)) {
                    if (pojo.getMensagemErro() != null
                            && !pojo.getMensagemErro().isEmpty()
                            && !pojo.getMensagemErro().equalsIgnoreCase("USUARIO_NAO_ENCONTRADO")) {
                        output.setValue("mensagem", pojo.getMensagemErro());
                        output.setValue("idRequisicao", idRequisicao);
                        consequence = ERROR;
                    } else {
                        consequence = SUCCESS;
                        createLog(requisicao);
                    }
                } else {
                    if (pojo.getMensagemErro() != null && !pojo.getMensagemErro().isEmpty()) {
                        output.setValue("mensagem", pojo.getMensagemErro());
                        output.setValue("idRequisicao", idRequisicao);
                        consequence = ERROR;
                    } else {
                        consequence = SUCCESS;
                        ServiceLocator.getUsuarioService().updateSincronizado((Long) requisicao.getParametro(), Boolean.TRUE);
                        createLog(requisicao);
                    }
                    break;
                }
            }

            if (!consequence.equalsIgnoreCase(SUCCESS)) {
                Contador contador = Contador.getInstance();
                contador.run();
            }
            i++;
        }
        if (i > TAM) {
            output.setValue("mensagem", "Tempo excedido!");
        }
        return consequence;
    }

    private void createLog(Requisicao requisicao) {
        ServiceLocator.getUsuarioService().updateSincronizado((Long) requisicao.getParametro(), Boolean.TRUE);
        Usuario usuario = ServiceLocator.getUsuarioService().readByIdSimple((Long) requisicao.getParametro());
        //Gera o log de sincronização aqui
        ServiceLocator.getLogService().create(ConnectionManagerLog.getInstance().getConnection(),
                new Log(LogAction.getMap(Ac.ATUALIZAR_USUARIO) + " - " + usuario.getUsuario(), LogAction.ATUALIZOU, (Usuario) getUserSession()));
    }
}