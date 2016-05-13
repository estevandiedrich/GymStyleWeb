package br.com.rwtech.gymstyleweb.controller.action.banco;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import java.util.Map;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class BancoAjaxReadAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        output.setValue("bancos", ServiceLocator.getBancoService().readByCriteria((Map<String, Object>) input));
        return SUCCESS;
    }
}
