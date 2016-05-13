package br.com.rwtech.gymstyleweb.controller.action.pagamentos.ajax;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class PagamentosUpdateImprimirAjaxtAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        Long id = input.getLong("id");
        Boolean imprime = input.getBoolean("imprime");
        Integer status = input.getInt("status");
        ServiceLocator.getPagamentoService().updateImprimir(imprime, status, id);
        return SUCCESS;
    }
}
