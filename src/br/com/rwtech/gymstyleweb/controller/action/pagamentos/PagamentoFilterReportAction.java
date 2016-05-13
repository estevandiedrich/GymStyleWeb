package br.com.rwtech.gymstyleweb.controller.action.pagamentos;

import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class PagamentoFilterReportAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = SUCCESS;
        input.setValue("criterioInicio", CalendarUtil.getDataCurrente());
        input.setValue("criterioFim", CalendarUtil.getDataCurrente());
        return consequence;
    }
}