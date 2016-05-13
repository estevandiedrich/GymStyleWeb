package br.com.rwtech.gymstyleweb.controller.action.tipousuario;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.TipoUsuario;
import org.mentawai.core.BaseAction;

public class TipoUsuarioCreateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;

        TipoUsuario tipoUsuario = (TipoUsuario) input.getValue("VOtipoUsuario");

        ServiceLocator.getTipoUsuarioService().create(tipoUsuario);
        session.setAttribute("mensagem", "Tipo Usuário criado com sucesso!");
        consequence = SUCCESS;
        return consequence;
    }
}
