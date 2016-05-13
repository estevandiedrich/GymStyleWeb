package br.com.rwtech.gymstyleweb.controller.action.pagamentos.ajax;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class ReadStatusImprimirPagamentoAjax extends BaseAction {

    @Override
    public String execute() throws Exception {
        Long id = input.getLong("id");
        Boolean status = ServiceLocator.getPagamentoService().readStatusImprimirPagamento(id);
        output.setValue("status", status);
        output.setValue("id", id);

        return SUCCESS;
    }
}
