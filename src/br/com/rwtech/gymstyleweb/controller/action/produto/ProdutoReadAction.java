package br.com.rwtech.gymstyleweb.controller.action.produto;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.util.Filtro;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class ProdutoReadAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        String consequence = SUCCESS;
        if (input.getValue(Filtro.CRITERIO_NOME) == null) {
            input.setValue(Filtro.CRITERIO_ATIVO, "true");
            consequence = SHOW;
        }
        output.setValue(PAGINATOR, ServiceLocator.getProdutoService().paginator((Map<String, Object>) input));
        output.setValue("categorias", ServiceLocator.getCategoriaService().readList());
        setMensagem();

        return consequence;
    }
}