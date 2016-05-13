package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.fluxocaixa.AbrirFluxoCaixaAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.fluxocaixa.CarregarAbrirFluxoCaixaAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.fluxocaixa.EntradaFluxoCaixaAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.fluxocaixa.FecharFluxoCaixaAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.fluxocaixa.ManagerFluxoCaixaAction;
import br.com.rwtech.gymstyleweb.controller.action.fluxocaixa.RegistroCaixaDeleteAction;
import br.com.rwtech.gymstyleweb.controller.action.fluxocaixa.RetiradaFluxoCaixaAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.fluxocaixa.relatorio.ReadCaixaAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.fluxocaixa.relatorio.ReadCaixasAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.fluxocaixa.relatorio.RelatorioCaixaActionRead;
import br.com.rwtech.gymstyleweb.view.report.caixa.CaixaReportAction;
import org.mentawai.authorization.Permission;
import org.mentawai.core.ActionConfig;
import org.mentawai.core.Forward;
import org.mentawai.core.Redirect;
import org.mentawai.core.StreamConsequence;
import org.mentawai.filter.AuthorizationFilter;

/**
 *
 * @author Éder Faria
 */
public class ActionFluxoCaixa extends Action {
    public static Action instance = null;

    public static Action addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionFluxoCaixa();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionFluxoCaixa() {
    }

    protected void createActions() {
        ActionConfig ac = null;
        String FLUXO_CAIXA = "fluxocaixa";
        ac = new ActionConfig(MANAGER_CAIXA, ManagerFluxoCaixaAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(MANAGER_CAIXA)));
        ac.addConsequence(ERROR, new Forward(JSP + FLUXO_CAIXA + MANAGER_PAGE));
        ac.addConsequence(SUCCESS, new Forward(JSP + FLUXO_CAIXA + MANAGER_PAGE));
        am.addActionConfig(ac);

        ac = new ActionConfig(CARREGAR_ABRIR_CAIXA, CarregarAbrirFluxoCaixaAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + FLUXO_CAIXA + "/abrirCaixaForm.jsp"));
        am.addActionConfig(ac);

        ac = new ActionConfig(READ_CAIXAS_AJAX, ReadCaixasAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + FLUXO_CAIXA + "/report/caixas.jsp"));
        am.addActionConfig(ac);

        ac = new ActionConfig(READ_CAIXA_AJAX, ReadCaixaAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + FLUXO_CAIXA + "/report/caixa.jsp"));
        am.addActionConfig(ac);

        ac = new ActionConfig(ABRIR_CAIXA, AbrirFluxoCaixaAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + FLUXO_CAIXA + RETORNO_JSP));
        am.addActionConfig(ac);

        ac = new ActionConfig(FECHAR_CAIXA, FecharFluxoCaixaAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + FLUXO_CAIXA + RETORNO_JSP));
        am.addActionConfig(ac);

        ac = new ActionConfig(ENTRADA_CAIXA, EntradaFluxoCaixaAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + FLUXO_CAIXA + RETORNO_JSP));
        am.addActionConfig(ac);

        ac = new ActionConfig(RETIRADA_CAIXA, RetiradaFluxoCaixaAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + FLUXO_CAIXA + RETORNO_JSP));
        am.addActionConfig(ac);

        ac = new ActionConfig(REPORT_CAIXA, RelatorioCaixaActionRead.class);
        ac.addFilter(new AuthorizationFilter(new Permission(REPORT_CAIXA)));
        ac.addConsequence(SUCCESS, new Forward(JSP + FLUXO_CAIXA + "/report" + LIST_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + FLUXO_CAIXA + "/report" + CORPO_JSP));
        am.addActionConfig(ac);

        //RELATORIOS
        ac = new ActionConfig(REPORT_CAIXA_PDF, CaixaReportAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(REPORT_CAIXA_PDF)));
        ac.addConsequence(PDF, new StreamConsequence(APPLICATION_PDF));
        ac.addConsequence(ERROR, new Forward(ERROR_REPORT_PAGE));
        am.addActionConfig(ac);

        ac = new ActionConfig(DELETE_REGISTRO_CAIXA, RegistroCaixaDeleteAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(DELETE_REGISTRO_CAIXA)));
        ac.addConsequence(ERROR, new Redirect(MANAGER_CAIXA + DO));
        ac.addConsequence(SUCCESS, new Redirect(MANAGER_CAIXA + DO));
        am.addActionConfig(ac);

    }
}