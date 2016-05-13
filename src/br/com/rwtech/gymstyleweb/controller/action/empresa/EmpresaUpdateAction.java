package br.com.rwtech.gymstyleweb.controller.action.empresa;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Empresa;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class EmpresaUpdateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;
        Long id = input.getLong("id");
        if (isPost()) {
            Empresa pojo = (Empresa) input.getValue("VOempresa");

            ServiceLocator.getEmpresaService().update(pojo);
            session.setAttribute("mensagem", "Academia alterada com sucesso!");
            consequence = SUCCESS;
        } else {
            EmpresaAction.preload(output);
            Empresa pojo = ServiceLocator.getEmpresaService().readById(id);
            output.setValue("pojo", pojo);
        }
        return consequence;
    }
}
