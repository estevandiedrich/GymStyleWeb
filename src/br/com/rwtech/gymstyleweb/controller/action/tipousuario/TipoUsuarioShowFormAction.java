package br.com.rwtech.gymstyleweb.controller.action.tipousuario;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.TipoUsuario;
import org.mentawai.core.BaseAction;

public class TipoUsuarioShowFormAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        Long id = input.getLong("id");
        if (id != null && id > 0) {
            TipoUsuario tipoUsuario = ServiceLocator.getTipoUsuarioService().readById(id);
            output.setValue("pojo", tipoUsuario);
        }
        output.setValue("select", "tipoUsuarioRead");
        return SUCCESS;
    }
}