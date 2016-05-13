package br.com.rwtech.gymstyleweb.controller.action.pagamentos;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class PagamentosReportReadAction extends ReadAction {

    public static String VENCIMENTO = "vencimento";
    public static String PAGAMENTO = "pagamento";

    @Override
    public String execute() throws Exception {
        Boolean realizado = true;
        String consequence = VENCIMENTO;
        if (input.getValue("criterioInicio") == null) {
            consequence = SHOW;
            input.setValue("criterioInicio", CalendarUtil.getDataCurrente());
            input.setValue("criterioFim", CalendarUtil.getDataCurrente());
            input.setValue("orderBy", "true");
        } else {
            String tipo = input.getString("tipo");
            if (tipo.equals(VENCIMENTO)) {
                consequence = VENCIMENTO;
            } else {
                consequence = PAGAMENTO;
            }
            realizado = input.getBoolean("realizado");
        }

        output.setValue(PAGINATOR, ServiceLocator.getPagamentoService().paginatorPagamentos((Map<String, Object>) input));
        output.setValue("realizado", realizado);
        if (input.getValue("orderBy") != null) {
            output.setValue("order", "Decrescente!");
            output.setValue("orderBy", true);
        } else {
            output.setValue("order", "Crescente!");
        }
        output.setValue("planos", ServiceLocator.getPlanoService().readList());
        output.setValue("funcionarios", ServiceLocator.getUsuarioService().readList());
        return consequence;
    }
}