/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rwtech.gymstyleweb.controller.action.fluxocaixa.relatorio;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.FluxoCaixa;
import java.util.List;
import java.util.Map;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Software1
 */
public class ReadCaixasAjaxAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        List<FluxoCaixa> list = ServiceLocator.getFluxoCaixaService().readByCriteria((Map<String, Object>) input);
        output.setValue(LIST, list);
        return SUCCESS;
    }
}
