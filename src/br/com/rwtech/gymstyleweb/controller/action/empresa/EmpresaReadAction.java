package br.com.rwtech.gymstyleweb.controller.action.empresa;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Empresa;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;

/**
 *
 * @author Éder Faria
 */
public class EmpresaReadAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        String consequence = SUCCESS;
        Long id = new Long(1);
        Empresa pojo = ServiceLocator.getEmpresaService().readById(id);
        output.setValue("pojo", pojo);
        setMensagem();
        return consequence;
    }
}
