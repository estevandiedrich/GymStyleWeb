
package br.com.rwtech.gymstyleweb.controller.action.produto;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Eder Faria
 */
public class ProdutoDisabledAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;
        if (ServiceLocator.getProdutoService().disabled(input.getLong("id"))) {
            session.setAttribute("mensagem", "Produto excluído com sucesso!");
            consequence = SUCCESS;
        }
        return consequence;
    }
}