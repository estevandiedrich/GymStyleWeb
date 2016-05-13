package br.com.rwtech.gymstyleweb.controller.action.log;

import org.mentawai.action.LogoutAction;

/**
 *
 * @author Éder Faria
 */
public class LogoutGymStyleWebAction extends LogoutAction{

    @Override
    public String execute() throws Exception {
//        Usuario usuario = (Usuario) getUserSession();
//        if (usuario != null) {
            //ServiceLocator.getRequisicaoService().clearRequisicoesByIdUser(usuario.getId());
//        }
        super.execute();
        return SUCCESS;
    }
}
