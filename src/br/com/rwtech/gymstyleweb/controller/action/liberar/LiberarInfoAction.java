package br.com.rwtech.gymstyleweb.controller.action.liberar;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Liberar;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class LiberarInfoAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        Long id = input.getLong("id");
        String consequence = SHOW;
        Liberar pojo = ServiceLocator.getLiberarService().readById(id);
        output.setValue("pojo", pojo);
        return consequence;
    }
}
