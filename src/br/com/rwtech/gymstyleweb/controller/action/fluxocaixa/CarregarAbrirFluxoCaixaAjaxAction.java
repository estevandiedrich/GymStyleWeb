package br.com.rwtech.gymstyleweb.controller.action.fluxocaixa;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.FluxoCaixa;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class CarregarAbrirFluxoCaixaAjaxAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        //map.put(JSP, map)
        input.setValue("criterioInicio", CalendarUtil.getDataCurrente());
        input.setValue("criterioFim", CalendarUtil.getDataCurrente());
        input.setValue("criterioUltimo", "true");

        List<FluxoCaixa> fluxos = ServiceLocator.getFluxoCaixaService().readByCriteria((Map<String, Object>) input);
        if (fluxos != null && !fluxos.isEmpty() && fluxos.get(0).getFechamento() == null) {
            output.setValue("aberto", true);
        } else {
            output.setValue("aberto", false);
        }

        FluxoCaixa fluxo = null;
        if (!fluxos.isEmpty()) {
            fluxo = fluxos.get(0);
        }
        output.setValue("pojo", fluxo);

        output.setValue("formasPagamentosEntrada", ServiceLocator.getFormaPagamentoService().readList());
        output.setValue("contas", ServiceLocator.getContaBancariaService().readList());

        return SUCCESS;
    }
}
