package br.com.rwtech.gymstyleweb.view.report;

import br.com.rwtech.gymstylecore.model.ConnectionManager;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class ReportOriginalAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;
        try {
            Map parameters = new HashMap();
            InputStream is = ReportOriginalAction.class.getResourceAsStream("/br/com/rwtech/gymstyleweb/view/report/eventos/ReportTeste.jasper");

            JasperPrint report = JasperFillManager.fillReport(is, parameters, ConnectionManager.getInstance().getConnection());

            byte[] saida = JasperExportManager.exportReportToPdf(report);
            output.setValue("stream", saida);
            consequence = "PDF";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consequence;
    }
}