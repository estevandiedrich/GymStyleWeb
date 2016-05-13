package br.com.rwtech.gymstyleweb.controller.action.fluxocaixa.relatorio;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.report.RegistroCaixaReport;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class RelatorioCaixaActionRead extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;
        int mes = 0;
        int ano = 0;
        if (input.getValue("criterioMes") == null) {
            Calendar calendar = Calendar.getInstance();
            ano = calendar.get(Calendar.YEAR);
            mes = calendar.get(Calendar.MONTH) + 1;
            input.setValue("criterioMes", mes);
            input.setValue("criterioAno", ano);
            consequence = SUCCESS;
        } else {
            ano = input.getInt("criterioAno");
            mes = input.getInt("criterioMes");
        }

        List<RegistroCaixaReport> list = ServiceLocator.getRegistroCaixaService().readRegistrosByMes(mes, ano);
        output.setValue("list", list);
        Double entradaTotal = new Double(0);
        Double retiradaTotal = new Double(0);
        Double saldoAcumuladoTotal = new Double(0);

        for (RegistroCaixaReport reg : list) {
            entradaTotal += reg.getEntrada();
            retiradaTotal += reg.getRetirada();
            saldoAcumuladoTotal = reg.getSaldoAcumulado();
        }

        output.setValue("entradaTotal", entradaTotal);
        output.setValue("retiradaTotal", retiradaTotal);
        output.setValue("saldoAcumuladoTotal", saldoAcumuladoTotal);

        output.setValue("meses", ServiceLocator.getRegistroCaixaService().getMeses());
        output.setValue("anos", ServiceLocator.getRegistroCaixaService().getAnos());

        return consequence;
    }
}
