package br.com.rwtech.gymstyleweb.controller.action.fluxocaixa.relatorio;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.FluxoCaixa;
import br.com.rwtech.gymstylecore.model.pojo.RegistroCaixa;
import java.util.Calendar;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Eder Faria
 */
public class ReadCaixaAjaxAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        FluxoCaixa pojo = ServiceLocator.getFluxoCaixaService().readById(input.getLong("id"));
        int ano = input.getInt("ano");
        int mes = input.getInt("mes");
        int dia = input.getInt("dia");
//        System.out.println(ano + "/" + mes + "/" + dia);
        for (RegistroCaixa registroCaixa : pojo.getRegistros()) {
//            System.out.println("----------------------------------------");
//            System.out.println(registroCaixa.getDataHora().get(Calendar.YEAR));
//            System.out.println(registroCaixa.getDataHora().get(Calendar.MONTH) + 1);
//            System.out.println(registroCaixa.getDataHora().get(Calendar.DAY_OF_MONTH));
//            System.out.println("----------------------------------------");
            if ((registroCaixa.getDataHora().get(Calendar.YEAR) == ano)
                    && ((registroCaixa.getDataHora().get(Calendar.MONTH) + 1) == mes)
                    && (registroCaixa.getDataHora().get(Calendar.DAY_OF_MONTH) == dia)) {
                registroCaixa.setDentroPeriodo(Boolean.TRUE);
            }
        }
        output.setValue("pojo", pojo);
        return SUCCESS;
    }
}
