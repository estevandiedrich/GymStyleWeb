package br.com.rwtech.gymstyleweb.controller.action.requisicao;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Requisicao;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.pojo.tipos.TipoRequisicaoResposta;
import java.util.Calendar;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class ApagarUsuarioDispositivoAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;
        Long idUsuario = input.getLong("idUsuario");
        Long idDispositivo = input.getLong("idDispositivo");
        if (idUsuario != null && idDispositivo != null) {
            Requisicao req = new Requisicao();
            req.setDestino(idDispositivo);
            req.setParametro(idUsuario);
            req.setOperador((Usuario) getUserSession());
            req.setDataHora(Calendar.getInstance());
            req.setStatus(Boolean.FALSE);
            req.setTipo(TipoRequisicaoResposta.EXCLUIR_USUARIO);
            ServiceLocator.getRequisicaoService().create(req);
            consequence = SUCCESS;
        }
        return consequence;
    }
}