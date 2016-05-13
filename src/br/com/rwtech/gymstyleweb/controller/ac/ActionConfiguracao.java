package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstylecore.model.pojo.Configuracao;
import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.configuracao.ConfiguracaoReadAction;
import br.com.rwtech.gymstyleweb.controller.action.configuracao.ConfiguracaoUpdateAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationConfiguracao;
import org.mentawai.authorization.Permission;
import org.mentawai.core.ActionConfig;
import org.mentawai.core.Forward;
import org.mentawai.core.Redirect;
import org.mentawai.filter.AuthorizationFilter;
import org.mentawai.filter.OVFilter;
import org.mentawai.filter.VOFilter;

/**
 *
 * @author Éder Faria
 */
public class ActionConfiguracao extends Action {

    public static Action instance = null;
    public static Action addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionConfiguracao();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionConfiguracao() {
    }

    protected void createActions() {
        ActionConfig ac = null;
        String CONFIGURACAO = "configuracao";
        ac = new ActionConfig(CONFIGURACAO_READ, ConfiguracaoReadAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(CONFIGURACAO_READ)));
        ac.addConsequence(SUCCESS, new Forward(JSP + CONFIGURACAO + LIST_PAGE));
        am.addActionConfig(ac);

        ac = new ActionConfig(CONFIGURACAO_UPDATE, ConfiguracaoUpdateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(CONFIGURACAO_MANAGER)));
        ac.addFilter(new VOFilter(Configuracao.class, VO + CONFIGURACAO));
        ac.addFilter(new OVFilter(OV + CONFIGURACAO));
        ac.addConsequence(SUCCESS, new Redirect(CONFIGURACAO_READ + DO));
        ac.addConsequence(ERROR, new Forward(JSP + CONFIGURACAO.toLowerCase() + UPDATE_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + CONFIGURACAO.toLowerCase() + UPDATE_PAGE));
        ac.addFilter(new ValidationConfiguracao());
        am.addActionConfig(ac);

    }
}