package br.com.rwtech.gymstyleweb.controller.action.digital;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.ImpressaoDigital;
import java.util.List;
import java.util.Map;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class ImpressaoDigitalUsuarioReadAjaxAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        List<ImpressaoDigital> digitais = ServiceLocator.getImpressaoDigitalEspelhoService().readByCriteria((Map<String, Object>) input);
        String templates = "";
        for (ImpressaoDigital dig : digitais) {
            templates += dig.getDedo().getId()
                    + "|" + dig.getPrimeiroTemplate()
                    + "|" + dig.getSegundoTemplate()
                    + "#";
        }
        output.setValue("templates", templates);
        return SUCCESS;
    }
}
