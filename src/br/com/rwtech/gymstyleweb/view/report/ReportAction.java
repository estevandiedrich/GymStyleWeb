package br.com.rwtech.gymstyleweb.view.report;

import br.com.rwtech.gymstylecore.model.ConnectionManager;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public abstract class ReportAction extends BaseAction {

    protected static final String CAMINHO = "/br/com/rwtech/gymstyleweb/view/report/";
    protected String CONSEQUENCE = ERROR;
    protected String SUBREPORT_DIR = "SUBREPORT_DIR";
    protected String REPORT_CONNECTION = "REPORT_CONNECTION";

    public void compile(Map parameters, String sql) {
        byte[] saida = null;
        try {
            InputStream is = ReportAction.class.getResourceAsStream(CAMINHO + getPasta() + "/" + getJasper());
            Connection conn = ConnectionManager.getInstance().getConnection();
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
//            JasperPrint report = JasperFillManager.fillReport(is, parameters, ConnectionManager.getInstance().getConnection());
            JasperPrint report = JasperFillManager.fillReport(is, parameters, jrRS);

            saida = JasperExportManager.exportReportToPdf(report);
            output.setValue("stream", saida);
            CONSEQUENCE = "PDF";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void compile(Map parameters, JRDataSource data) {
        byte[] saida = null;
        try {
            InputStream is = ReportAction.class.getResourceAsStream(CAMINHO + getPasta() + "/" + getJasper());
            JasperPrint report = JasperFillManager.fillReport(is, parameters, data);

            saida = JasperExportManager.exportReportToPdf(report);
            output.setValue("stream", saida);
            CONSEQUENCE = "PDF";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        public void compile(Map parameters) {
        byte[] saida = null;
        try {
            InputStream is = ReportAction.class.getResourceAsStream(CAMINHO + getPasta() + "/" + getJasper());
            Connection conn = ConnectionManager.getInstance().getConnection();
            JasperPrint report = JasperFillManager.fillReport(is, parameters, conn);

            saida = JasperExportManager.exportReportToPdf(report);
            output.setValue("stream", saida);
            CONSEQUENCE = "PDF";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract String getPasta();

    public abstract String getJasper();
}
