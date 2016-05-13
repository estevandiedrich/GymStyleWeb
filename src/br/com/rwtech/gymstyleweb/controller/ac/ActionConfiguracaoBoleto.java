package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstylecore.model.pojo.ConfiguracaoBoleto;
import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.configuracaoboleto.CarteirasAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.configuracaoboleto.ConfiguracaoBoletoReadAction;
import br.com.rwtech.gymstyleweb.controller.action.configuracaoboleto.ConfigurarBoletoAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationConfiguracaoBoleto;
import br.com.rwtech.gymstyleweb.view.report.boleto.BoletoReportAction;
import org.mentawai.authorization.Permission;
import org.mentawai.core.ActionConfig;
import org.mentawai.core.Forward;
import org.mentawai.core.Redirect;
import org.mentawai.core.StreamConsequence;
import org.mentawai.filter.AuthorizationFilter;
import org.mentawai.filter.OVFilter;
import org.mentawai.filter.VOFilter;

/**
 *
 * @author Éder Faria
 */
public class ActionConfiguracaoBoleto extends Action {

    public static Action instance = null;
    public static Action addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionConfiguracaoBoleto();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionConfiguracaoBoleto() {
    }

    protected void createActions() {
        ActionConfig ac = null;
        String CONFIG_BOLETO = "configuracaoboleto";
        ac = new ActionConfig(CONFIGURAR_BOLETO, ConfigurarBoletoAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(CONFIGURAR_BOLETO)));
        ac.addFilter(new VOFilter(ConfiguracaoBoleto.class, VO + CONFIG_BOLETO));
        ac.addFilter(new OVFilter(OV + CONFIG_BOLETO));
        ac.addFilter(new ValidationConfiguracaoBoleto());
        ac.addConsequence(SHOW, new Forward(JSP + CONFIG_BOLETO + UPDATE_PAGE));
        ac.addConsequence(ERROR, new Forward(JSP + CONFIG_BOLETO + UPDATE_PAGE));
        ac.addConsequence(SUCCESS, new Redirect(CONFIGURACAO_BOLETO_READ + DO));
        am.addActionConfig(ac);

        ac = new ActionConfig(CONFIGURACAO_BOLETO_READ, ConfiguracaoBoletoReadAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(CONFIGURACAO_BOLETO_READ)));
        ac.addConsequence(SUCCESS, new Forward(JSP + CONFIG_BOLETO + LIST_PAGE));
        am.addActionConfig(ac);

        //RELATORIOS
        ac = new ActionConfig(BOLETO_REPORT, BoletoReportAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(BOLETO_REPORT)));
        ac.addConsequence(PDF, new StreamConsequence(APPLICATION_PDF));
        ac.addConsequence(ERROR, new Forward(ERROR_REPORT_PAGE));
        am.addActionConfig(ac);

        //IMPORTAR HORARIO AJAX
        ac = new ActionConfig(CARTEIRAS_AJAX, CarteirasAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + "configuracaoboleto/carteiras.jsp"));
        am.addActionConfig(ac);

    }
}