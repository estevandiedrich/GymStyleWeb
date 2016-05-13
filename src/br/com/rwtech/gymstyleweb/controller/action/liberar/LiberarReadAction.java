package br.com.rwtech.gymstyleweb.controller.action.liberar;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class LiberarReadAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        String consequence = SUCCESS;
        if (input.getValue("criterioJustificativa") == null) {
            consequence = SHOW;
            input.setValue("orderBy", "true");
        }
        output.setValue(PAGINATOR, ServiceLocator.getLiberarService().paginator((Map<String, Object>) input));
        setMensagem();
        return consequence;
    }
}
