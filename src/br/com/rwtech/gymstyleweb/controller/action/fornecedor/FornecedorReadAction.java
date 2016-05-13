package br.com.rwtech.gymstyleweb.controller.action.fornecedor;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.util.Filtro;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class FornecedorReadAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        String consequence = SUCCESS;
        if (input.getValue(Filtro.CRITERIO_NOME) == null) {
            input.setValue(Filtro.CRITERIO_ATIVO, "true");
            consequence = SHOW;
        }
        output.setValue(PAGINATOR, ServiceLocator.getFornecedorService().paginator((Map<String, Object>) input));
        setMensagem();

        return consequence;
    }
}
