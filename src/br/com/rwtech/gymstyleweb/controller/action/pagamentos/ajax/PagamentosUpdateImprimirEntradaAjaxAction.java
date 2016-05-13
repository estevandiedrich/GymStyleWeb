package br.com.rwtech.gymstyleweb.controller.action.pagamentos.ajax;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class PagamentosUpdateImprimirEntradaAjaxAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        Long id = input.getLong("id");
        Boolean status = input.getBoolean("status");
        ServiceLocator.getPagamentoService().updateImprimirEntrada(status, id);
        return SUCCESS;
    }
}
