package br.com.rwtech.gymstyleweb.controller.action.produto.venda;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import java.util.Map;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class VendaManagerAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        output.setValue("usuarios", ServiceLocator.getUsuarioService().readByCriteriaSimple((Map<String, Object>) input));//Consulta Aluno
        output.setValue("produtos", ServiceLocator.getProdutoService().readByCriteria((Map<String, Object>) input));//Consulta Aluno
        return SUCCESS;
    }
}
