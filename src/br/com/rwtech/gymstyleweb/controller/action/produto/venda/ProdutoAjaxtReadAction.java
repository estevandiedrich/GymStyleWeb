
package br.com.rwtech.gymstyleweb.controller.action.produto.venda;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import java.util.Map;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class ProdutoAjaxtReadAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        output.setValue("produtos", ServiceLocator.getProdutoService().readByCriteria((Map<String, Object>) input));
        return SUCCESS;
        //return "AJAX";
    }
}
