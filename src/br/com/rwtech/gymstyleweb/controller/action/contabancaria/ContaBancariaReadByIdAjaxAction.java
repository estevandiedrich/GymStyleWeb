package br.com.rwtech.gymstyleweb.controller.action.contabancaria;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class ContaBancariaReadByIdAjaxAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        output.setValue("infoConta", ServiceLocator.getContaBancariaService().readById((input.getLong("idConta"))));
        return SHOW;
    }
}
