package br.com.rwtech.gymstyleweb.controller.action.fornecedor;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Fornecedor;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class FornecedorUpdateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;
        Long id = input.getLong("id");
        if (isPost()) {
            Fornecedor pojo = (Fornecedor) input.getValue("VOfornecedor");
            pojo.setAtivo(Boolean.TRUE);
            ServiceLocator.getFornecedorService().update(pojo);
            session.setAttribute("mensagem", "Fornecedor alterado com sucesso!");
            consequence = SUCCESS;
        } else {
            FornecedorAction.preload(output);
            Fornecedor pojo = ServiceLocator.getFornecedorService().readById(id);
            output.setValue("pojo", pojo);
            //output.setValue("grupoMuscularExer", pojo.getGrupoMuscular().getId());
        }
        return consequence;
    }
}