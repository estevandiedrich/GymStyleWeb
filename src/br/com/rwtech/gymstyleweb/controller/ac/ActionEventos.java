package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.evento.EventoReadAction;
import br.com.rwtech.gymstyleweb.controller.action.evento.UltimosEventosAction;
import org.mentawai.authorization.Permission;
import org.mentawai.core.ActionConfig;
import org.mentawai.core.Forward;
import org.mentawai.filter.AuthorizationFilter;

/**
 *
 * @author Éder Faria
 */
public class ActionEventos extends Action {

    public static Action instance = null;

    public static Action addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionEventos();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionEventos() {
    }

    protected void createActions() {
        //ULTIMOS EVENTOS
        ActionConfig ac = new ActionConfig(ULTIMOS_EVENTOS, UltimosEventosAction.class);
        //ac.addFilter(new AuthorizationFilter(new Permission(ULTIMOS_EVENTOS)));
        ac.addConsequence(SUCCESS, new Forward(JSP + "template/ultimosEventos.jsp"));
        ac.addConsequence(SHOW, new Forward(JSP + "template/ultimosEventos.jsp"));
        ac.addConsequence(ERROR, new Forward(JSP + "expirou.page"));
        am.addActionConfig(ac);

        //EVENTO
        ac = new ActionConfig(EVENTO_READ, EventoReadAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(EVENTO_READ)));
        ac.addConsequence(ERROR, new Forward(JSP + "evento/" + LIST_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + "evento/" + LIST_PAGE));
        ac.addConsequence(SUCCESS, new Forward(JSP + "evento" + CORPO_JSP));//retorno é um ajax
        am.addActionConfig(ac);

    }
}