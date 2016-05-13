package br.com.rwtech.gymstyleweb.view.report.caixa;

import br.com.rwtech.gymstylecore.model.pojo.report.RegistroCaixaReport;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;

/**
 *
 * @author Éder Faria
 */
public class RegistroCaixaFactory {

    private static JRDataSource data;

    public static JRDataSource createDatasource(List<RegistroCaixaReport> vetor) {
        data = new RegistroCaixaDS(vetor);
        return data;
    }
}
