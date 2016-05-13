package br.com.rwtech.gymstyleweb.controller.action.contabancaria.registros;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import java.util.Map;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class RegistroContaBancariaReadAjaxAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        if (input.getValue("criterioConta") != null) {
            output.setValue("paginator", ServiceLocator.getRegistroContaBancariaService().paginator((Map<String, Object>) input));
            output.setValue("saldoConta", ServiceLocator.getContaBancariaService().readtValorTotalContaBancaria(input.getLong("criterioConta")));
            output.setValue("totalPeriodo", ServiceLocator.getContaBancariaService().readValorPeriodoContaBancaria((Map<String, Object>) input));
        }
        return SHOW;
    }
}
