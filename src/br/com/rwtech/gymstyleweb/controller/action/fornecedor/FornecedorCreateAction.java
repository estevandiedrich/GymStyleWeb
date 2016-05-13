package br.com.rwtech.gymstyleweb.controller.action.fornecedor;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Fornecedor;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class FornecedorCreateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;
        if (isPost()) {
            Fornecedor pojo = (Fornecedor) input.getValue("VOfornecedor");
            pojo.setAtivo(Boolean.TRUE);
            ServiceLocator.getFornecedorService().create(pojo);
            session.setAttribute("mensagem", "Fornecedor criado com sucesso!");
            consequence = SUCCESS;
        } else {
            FornecedorAction.preload(output);
        }
        return consequence;
    }
}