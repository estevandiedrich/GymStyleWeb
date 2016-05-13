package br.com.rwtech.gymstyleweb.controller.action.fornecedor;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class FornecedorDeleteAction extends BaseAction {

    @Override
    public String execute() throws Exception {

        String consequence = ERROR;
        if (ServiceLocator.getFornecedorService().disabled(input.getLong("id"))) {
            session.setAttribute("mensagem", "Fornecedor excluído com sucesso!");
            consequence = SUCCESS;
        }
        return consequence;
    }
}