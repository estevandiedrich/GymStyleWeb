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
public class LimparDispositivoAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        Long idDispositivo = input.getLong("id");
        if (idDispositivo != null) {
            ServiceLocator.getRequisicaoService().create(new Requisicao(null, null, Boolean.FALSE, idDispositivo, ((Usuario) getUserSession()), TipoRequisicaoResposta.RESET));
            ServiceLocator.getDispositivoService().zerarUltimoEvento(idDispositivo);
            addMessage("Enviada requisição de limpar dados na catraca!");
        } else {
            addMessage("Não foi possível enviar requisição limpar dados na catraca");
        }
        return SUCCESS;
    }
}
