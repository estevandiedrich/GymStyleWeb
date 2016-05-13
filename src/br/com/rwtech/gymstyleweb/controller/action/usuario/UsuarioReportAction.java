
package br.com.rwtech.gymstyleweb.controller.action.usuario;

import br.com.rwtech.gymstylecore.model.ConnectionManager;
import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.mentawai.core.BaseAction;

public class UsuarioReportAction extends BaseAction{

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;
        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put("nome", input.getString("nome"));
        List<Usuario> contatos = ServiceLocator.getUsuarioService().readByCriteria(criteria);
        try{
            Map parameters = new HashMap();
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(contatos);
            InputStream is = UsuarioReportAction.class.getResourceAsStream("/gymstyle/controller/action/ReportTeste.jasper");

//            JasperPrint report = JasperFillManager.fillReport(is, parameters, ds);
            JasperPrint report = JasperFillManager.fillReport(is, parameters, ConnectionManager.getInstance().getConnection());

            byte[] saida = JasperExportManager.exportReportToPdf(report);
            output.setValue("stream", saida);
            consequence = "PDF";
        }catch(Exception e){
            e.printStackTrace();
        }
        return consequence;
    }
}