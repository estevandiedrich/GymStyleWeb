package br.com.rwtech.gymstyleweb.controller.action.usuario;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import java.util.Map;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder
 */
public class UsuarioAjaxReadAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        output.setValue("usuarios", ServiceLocator.getUsuarioService().readByCriteriaSimple((Map<String, Object>) input));
        return Ac.VENDA;
    }
}
