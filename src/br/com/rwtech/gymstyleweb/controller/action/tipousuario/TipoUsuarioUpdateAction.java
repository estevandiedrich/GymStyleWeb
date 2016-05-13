
package br.com.rwtech.gymstyleweb.controller.action.tipousuario;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.TipoUsuario;
import org.mentawai.core.BaseAction;

public class TipoUsuarioUpdateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;
        TipoUsuario tipoUsuario = (TipoUsuario) input.getValue("VOtipoUsuario");

        ServiceLocator.getTipoUsuarioService().update(tipoUsuario);
        session.setAttribute("mensagem","TIpo de Usuário alterado com sucesso!");

        consequence = SUCCESS;
        return consequence;
    }
}
