package br.com.rwtech.gymstyleweb.controller.action.modalidade;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.util.Filtro;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class ModalidadeReadAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        String consequence = SUCCESS;
        if (input.getValue(Filtro.CRITERIO_NOME) == null) {
            consequence = SHOW;
        }

        input.setValue(Filtro.CRITERIO_ATIVO, "true");
        output.setValue(PAGINATOR, ServiceLocator.getModalidadeService().paginator((Map<String, Object>) input));
        setMensagem();
        return consequence;
    }
}
