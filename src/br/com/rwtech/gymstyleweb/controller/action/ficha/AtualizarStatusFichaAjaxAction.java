package br.com.rwtech.gymstyleweb.controller.action.ficha;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class AtualizarStatusFichaAjaxAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        Long idFicha = input.getLong("idFicha");
        Long idUsuario = input.getLong("idUsuario");
        Boolean status = input.getBoolean("status");
        ServiceLocator.getFichaService().updateStatusAtiva(idUsuario, idFicha, status);
        return SUCCESS;
    }
}
