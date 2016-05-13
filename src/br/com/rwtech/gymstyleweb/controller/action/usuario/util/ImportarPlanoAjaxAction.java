package br.com.rwtech.gymstyleweb.controller.action.usuario.util;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
import org.mentawai.core.BaseAction;

/**
 * @author Éder Faria
 */
public class ImportarPlanoAjaxAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        Long id = input.getLong("id");
        if (id != null && id != 0) {
            Plano plano = ServiceLocator.getPlanoService().readById(id);
            output.setValue("infoPlano", plano);
        }
        return SUCCESS;
    }
}
