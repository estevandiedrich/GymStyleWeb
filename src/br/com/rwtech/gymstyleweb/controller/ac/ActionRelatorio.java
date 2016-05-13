package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.pagamentos.PagamentosReportReadAction;
import br.com.rwtech.gymstyleweb.controller.action.usuario.UsuarioReportAction;
import br.com.rwtech.gymstyleweb.view.report.ReportAction;
import br.com.rwtech.gymstyleweb.view.report.eventos.EventoReportAction;
import br.com.rwtech.gymstyleweb.view.report.ficha.FichaReportAction;
import org.mentawai.authorization.Permission;
import org.mentawai.core.ActionConfig;
import org.mentawai.core.Forward;
import org.mentawai.core.StreamConsequence;
import org.mentawai.filter.AuthorizationFilter;

/**
 *
 * @author Éder Faria
 */
public class ActionRelatorio extends Action {

    public static Action instance = null;

    public static Action addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionRelatorio();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionRelatorio() {
    }

    protected void createActions() {
        //RELATORIOS
        ActionConfig ac = new ActionConfig(GERAR_RELATORIO, ReportAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(GERAR_RELATORIO)));
        ac.addConsequence(PDF, new StreamConsequence(APPLICATION_PDF));
        ac.addConsequence(ERROR, new Forward(ERROR_REPORT_PAGE));
        am.addActionConfig(ac);

        //RELATORIOS
        ac = new ActionConfig(USUARIO_REPORT, UsuarioReportAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(USUARIO_REPORT)));
        ac.addConsequence(PDF, new StreamConsequence(APPLICATION_PDF));
        ac.addConsequence(ERROR, new Forward(ERROR_REPORT_PAGE));
        am.addActionConfig(ac);

        //RELATORIOS
        ac = new ActionConfig(EVENTO_REPORT, EventoReportAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(EVENTO_REPORT)));
        ac.addConsequence(PDF, new StreamConsequence(APPLICATION_PDF));
        ac.addConsequence(ERROR, new Forward(ERROR_REPORT_PAGE));
        am.addActionConfig(ac);

        //RELATORIOS
        ac = new ActionConfig(FICHA_REPORT, FichaReportAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(FICHA_REPORT)));
        ac.addConsequence(PDF, new StreamConsequence(APPLICATION_PDF));
        ac.addConsequence(ERROR, new Forward(ERROR_REPORT_PAGE));
        am.addActionConfig(ac);

        //PAGAMENTOS REPORT READ ACTION
        ac = new ActionConfig(PAGAMENTO_REPORT_READ, PagamentosReportReadAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(PAGAMENTO_REPORT_READ)));
        ac.addConsequence(ERROR, new Forward(JSP + "pagamento/report/listReport.page"));
        ac.addConsequence(SHOW, new Forward(JSP + "pagamento/report/listReport.page"));
        ac.addConsequence(PagamentosReportReadAction.VENCIMENTO, new Forward(JSP + "pagamento/report/corpoVencimento.jsp"));
        ac.addConsequence(PagamentosReportReadAction.PAGAMENTO, new Forward(JSP + "pagamento/report/corpoPagamento.jsp"));
        am.addActionConfig(ac);

    }
}