package br.com.rwtech.gymstyleweb.controller.action.contabancaria;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import java.util.Map;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class ManagerContaBancariaAction extends BaseAction {

    @Override
    public String execute() {
        String consequence = SHOW;
        consequence = SUCCESS;
        input.setValue("criterioInicio", CalendarUtil.getDataCurrente(1));//todo filtro inciar com o dia 1 e o dia atual
        input.setValue("criterioFim", CalendarUtil.getDataCurrente());
        Map<Long, String> contas = ServiceLocator.getContaBancariaService().readList();
        output.setValue("contas", contas);
        if (contas.size() > 0) {
            if (input.getValue("id") != null) {
                output.setValue("conta", input.getLong("id"));
            } else {
                output.setValue("conta", contas.keySet().iterator().next());
            }
        }
        Map<Long, String> map = ServiceLocator.getFormaPagamentoService().readList();
        map.remove(Long.valueOf(2));//Boleto
        map.remove(Long.valueOf(3));//Cartao Debito
        map.remove(Long.valueOf(4));//Cartao Credito
        output.setValue("formasPagamentos", map);
        output.setValue("idCaixaAberto",ServiceLocator.getFluxoCaixaService().idCaixaAberto());
 
        output.setValue("list", ServiceLocator.getContaBancariaService().readByCriteria((Map<String, Object>) input));
        return consequence;

    }
}
