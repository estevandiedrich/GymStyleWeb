package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.liberar.LiberarDeleteAction;
import br.com.rwtech.gymstyleweb.controller.action.liberar.LiberarDispositivoAction;
import br.com.rwtech.gymstyleweb.controller.action.liberar.LiberarInfoAction;
import br.com.rwtech.gymstyleweb.controller.action.liberar.LiberarReadAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationLiberarCatraca;
import org.mentawai.authorization.Permission;
import org.mentawai.core.ActionConfig;
import org.mentawai.core.Forward;
import org.mentawai.core.Redirect;
import org.mentawai.filter.AuthorizationFilter;

/**
 *
 * @author Éder Faria
 */
public class ActionLiberar extends Action {

    public static ActionLiberar instance = null;

    public static Action addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionLiberar();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionLiberar() {
    }

    @Override
    protected void createActions() {
        ActionConfig ac = null;
        ac = new ActionConfig(LIBERAR_CATRACA, LiberarDispositivoAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(LIBERAR_MANAGER)));
        ac.addConsequence(SHOW, new Forward(JSP + LIBERAR + "/liberar.page"));
        ac.addConsequence(ERROR, new Forward(JSP + LIBERAR + "/liberar.page"));
        ac.addConsequence(SUCCESS, new Redirect(LIBERAR_READ + DO));
        ac.addFilter(new ValidationLiberarCatraca());
        am.addActionConfig(ac);

        ac = new ActionConfig(LIBERAR_INFO, LiberarInfoAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(LIBERAR_READ)));
        ac.addConsequence(SHOW, new Forward(JSP + LIBERAR + "/info.page"));
        ac.addConsequence(ERROR, new Redirect(LIBERAR_READ + DO));
        am.addActionConfig(ac);

        ac = new ActionConfig(LIBERAR_READ, LiberarReadAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(LIBERAR_READ)));
        ac.addConsequence(SHOW, new Forward(JSP + LIBERAR + LIST_PAGE));
        ac.addConsequence(SUCCESS, new Forward(JSP + LIBERAR + CORPO_JSP));
        am.addActionConfig(ac);

        ac = new ActionConfig(LIBERAR_DELETE, LiberarDeleteAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(LIBERAR_DELETE)));
        ac.addConsequence(SUCCESS, new Redirect(LIBERAR_READ + DO));
        ac.addConsequence(ERROR, new Redirect(LIBERAR_READ + DO));
        am.addActionConfig(ac);
    }
}
