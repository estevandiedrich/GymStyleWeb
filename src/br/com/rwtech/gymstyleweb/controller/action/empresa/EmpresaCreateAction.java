package br.com.rwtech.gymstyleweb.controller.action.empresa;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Empresa;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class EmpresaCreateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;
        if (isPost()) {
            Empresa pojo = (Empresa) input.getValue("VOexercicio");
            ServiceLocator.getEmpresaService().create(pojo);
            session.setAttribute("mensagem", "Exercício criado com sucesso!");
            consequence = SUCCESS;
        } else {
            EmpresaAction.preload(output);
        }
        return consequence;
    }
}