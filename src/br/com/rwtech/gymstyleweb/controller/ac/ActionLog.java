package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.log.LogReadAction;
import org.mentawai.authorization.Permission;
import org.mentawai.core.ActionConfig;
import org.mentawai.core.Forward;
import org.mentawai.filter.AuthorizationFilter;

/**
 *
 * @author Éder Faria
 */
public class ActionLog extends Action {
    public static Action instance = null;

    public static Action addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionLog();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionLog() {
    }

    protected void createActions() {
        ActionConfig ac = null;
        String LOG = "log";
        ac = new ActionConfig(LOG_READ, LogReadAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(LOG_READ)));
        ac.addConsequence(SHOW, new Forward(JSP + LOG + LIST_PAGE));
        ac.addConsequence(SUCCESS, new Forward(JSP + LOG + CORPO_JSP));
        am.addActionConfig(ac);
    }
}