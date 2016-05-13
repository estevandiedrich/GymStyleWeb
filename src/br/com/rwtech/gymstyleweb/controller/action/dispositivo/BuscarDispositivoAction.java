package br.com.rwtech.gymstyleweb.controller.action.dispositivo;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Requisicao;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.pojo.tipos.TipoRequisicaoResposta;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class BuscarDispositivoAction extends BaseAction {

    @Override
    public String execute() throws Exception {

        String consequence = SHOW;
        if (isPost()) {
            consequence = SUCCESS;
            Usuario usuario = (Usuario) getUserSession();
            Requisicao requisicao = new Requisicao(null, null, Boolean.FALSE, null, usuario, TipoRequisicaoResposta.DESCOBRIR_DISPOSITIVOS_NA_REDE);
            ServiceLocator.getRequisicaoService().create(requisicao);
            session.setAttribute("mensagem", "Buscando dispositivos!");
        }
        return consequence;
    }
}