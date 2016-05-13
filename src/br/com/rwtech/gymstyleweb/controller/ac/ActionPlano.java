package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstylecore.model.pojo.Plano;
import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.plano.PlanoCreateAction;
import br.com.rwtech.gymstyleweb.controller.action.plano.PlanoDeleteAction;
import br.com.rwtech.gymstyleweb.controller.action.plano.PlanoReadAction;
import br.com.rwtech.gymstyleweb.controller.action.plano.PlanoUpdateAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationPlano;
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
public class ActionPlano extends Action {

    public static Action instance = null;
    public static Action addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionPlano();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionPlano() {
    }

    protected void createActions() {
        ActionConfig ac = null;
        ac = new ActionConfig(PLANO_READ, PlanoReadAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(PLANO_READ)));
        ac.addConsequence(SHOW, new Forward(JSP + PLANO.toLowerCase() + LIST_PAGE));
        ac.addConsequence(SUCCESS, new Forward(JSP + PLANO.toLowerCase() + CORPO_JSP));
        am.addActionConfig(ac);

        ac = new ActionConfig(PLANO_CREATE, PlanoCreateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(PLANO_MANAGER)));
        ac.addFilter(new VOFilter(Plano.class, VO + PLANO));
        ac.addFilter(new OVFilter(OV + PLANO));
        ac.addConsequence(SUCCESS, new Redirect(PLANO_READ + DO));
        ac.addConsequence(ERROR, new Forward(JSP + PLANO.toLowerCase() + CREATE_PAGE));
        am.addActionConfig(ac);
        ac.addFilter(new ValidationPlano());

        ac = new ActionConfig(PLANO_UPDATE, PlanoUpdateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(PLANO_MANAGER)));
        ac.addFilter(new VOFilter(Plano.class, VO + PLANO));
        ac.addFilter(new OVFilter(OV + PLANO));
        ac.addConsequence(SUCCESS, new Redirect(PLANO_READ + DO));
        ac.addConsequence(ERROR, new Forward(JSP + PLANO.toLowerCase() + UPDATE_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + PLANO.toLowerCase() + UPDATE_PAGE));
        am.addActionConfig(ac);
        ac.addFilter(new ValidationPlano());

        ac = new ActionConfig(PLANO_DELETE, PlanoDeleteAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(PLANO_DELETE)));
        ac.addConsequence(SUCCESS, new Redirect(PLANO_READ + DO));
        ac.addConsequence(ERROR, new Redirect(PLANO_READ + DO));
        am.addActionConfig(ac);
    }
}