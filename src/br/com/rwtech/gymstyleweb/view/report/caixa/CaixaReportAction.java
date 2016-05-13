package br.com.rwtech.gymstyleweb.view.report.caixa;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.report.RegistroCaixaReport;
import br.com.rwtech.gymstyleweb.view.report.ReportAction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaixaReportAction extends ReportAction {

    private String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

    @Override
    public String getJasper() {
        return ("ReportCaixa.jasper");
    }

    @Override
    public String getPasta() {
        return ("caixa");
    }

    @Override
    public String execute() throws Exception {
        Map parameters = new HashMap();
        int mes = input.getInt("criterioMes");
        int ano = input.getInt("criterioAno");
        parameters.put("mes", meses[mes-1]);
        parameters.put("ano", ano);

        Double entradaTotal = new Double(0);
        Double retiradaTotal = new Double(0);
        Double saldoAcumuladoTotal = new Double(0);

        List<RegistroCaixaReport> list = ServiceLocator.getRegistroCaixaService().readRegistrosByMes(mes, ano);
        for (RegistroCaixaReport reg : list) {
            entradaTotal += reg.getEntrada();
            retiradaTotal += reg.getRetirada();
            saldoAcumuladoTotal = reg.getSaldoAcumulado();
        }
        parameters.put("entradaTotal", entradaTotal);
        parameters.put("retiradaTotal", retiradaTotal);
        parameters.put("saldoTotal", saldoAcumuladoTotal);


        compile(parameters, RegistroCaixaFactory.createDatasource(list));

        return CONSEQUENCE;
    }
}