package br.com.rwtech.gymstyleweb.view.report.recibo;

import br.com.rwtech.gymstylecore.model.util.JExtenso;
import br.com.rwtech.gymstyleweb.view.report.ReportAction;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class ReciboReportAction extends ReportAction {

    @Override
    public String getJasper() {
        return ("ReportRecibo.jasper");
    }

    @Override
    public String getPasta() {
        return ("recibo");
    }

    @Override
    public String execute() throws Exception {
        if (input.getValue("idPagamento") != null && input.getValue("valor") != null) {
            Map parameters = new HashMap();
            parameters.put("valorExtenso", new JExtenso(input.getDouble("valor")).toString());
            parameters.put("idPagamento", input.getLong("idPagamento"));
            compile(parameters);
        }
        return CONSEQUENCE;
    }
}