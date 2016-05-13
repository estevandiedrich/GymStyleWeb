package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.ficha.AtualizarStatusFichaAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.ficha.FichaCreateAction;
import br.com.rwtech.gymstyleweb.controller.action.ficha.FichaDeleteAction;
import br.com.rwtech.gymstyleweb.controller.action.ficha.FichaReadAction;
import br.com.rwtech.gymstyleweb.controller.action.ficha.FichaUpdateAction;
import br.com.rwtech.gymstyleweb.controller.action.ficha.FichaUsuarioInfoAction;
import br.com.rwtech.gymstyleweb.controller.action.ficha.OrdenarFichaAction;
import br.com.rwtech.gymstyleweb.controller.action.ficha.StatusFichaAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.ficha.UsuarioFichasReadAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationFicha;
import org.mentawai.authorization.Permission;
import org.mentawai.core.ActionConfig;
import org.mentawai.core.Chain;
import org.mentawai.core.Forward;
import org.mentawai.core.Redirect;
import org.mentawai.filter.AuthorizationFilter;

/**
 *
 * @author Éder Faria
 */
public class ActionFicha extends Action {
    public static Action instance = null;

    public static Action addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionFicha();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionFicha() {
    }

    protected void createActions() {
        ActionConfig ac = null;
        ac = new ActionConfig(FICHA_INFO, FichaUsuarioInfoAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(FICHA_READ)));
        ac.addConsequence(SUCCESS, new Forward(JSP + FICHA + INFO_PAGE));
        ac.addConsequence(LIST, new Redirect(FICHA + READ + DO));
        am.addActionConfig(ac);

        ac = new ActionConfig(FICHA_READ, FichaReadAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(FICHA_READ)));
        ac.addConsequence(SHOW, new Forward(JSP + FICHA + LIST_PAGE));
        ac.addConsequence(SUCCESS, new Forward(JSP + FICHA + CORPO_JSP));
        am.addActionConfig(ac);

        ActionConfig acUsuFicha = new ActionConfig(USUARIO_FICHAS_READ, UsuarioFichasReadAction.class);
        acUsuFicha.addFilter(new AuthorizationFilter(new Permission(FICHA_READ)));
        acUsuFicha.addConsequence(SUCCESS, new Forward(JSP + FICHA + "/fichasUsuarioList.page"));
        acUsuFicha.addConsequence(LIST, new Redirect(FICHA + READ + DO));
        am.addActionConfig(acUsuFicha);

        ActionConfig acOrdenar = new ActionConfig(ORDENAR_FICHA, OrdenarFichaAction.class);
        acOrdenar.addFilter(new AuthorizationFilter(new Permission(FICHA_MANAGER)));
        acOrdenar.addConsequence(ERROR, new Forward(JSP + FICHA + "/ordenar.page"));
        acOrdenar.addConsequence(SHOW, new Forward(JSP + FICHA + "/ordenar.page"));
        acOrdenar.addConsequence(SUCCESS, new Redirect(USUARIO_FICHAS_READ + DO));
        acOrdenar.addConsequence(LIST, new Redirect(FICHA + READ + DO));
        am.addActionConfig(acOrdenar);

        ac = new ActionConfig(FICHA_CREATE, FichaCreateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(FICHA_MANAGER)));
        ac.addConsequence(ERROR, new Forward(JSP + FICHA + CREATE_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + FICHA + CREATE_PAGE));
        ac.addConsequence(ORDENAR, new Chain(acOrdenar));
        ac.addConsequence(SUCCESS, new Redirect(USUARIO_FICHAS_READ + DO));
        ac.addConsequence(LIST, new Redirect(FICHA + READ + DO));
        ac.addFilter(new ValidationFicha());
        am.addActionConfig(ac);

        ac = new ActionConfig(FICHA_UPDATE, FichaUpdateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(FICHA_MANAGER)));
        ac.addConsequence(ERROR, new Forward(JSP + FICHA + UPDATE_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + FICHA + UPDATE_PAGE));
        ac.addConsequence(ORDENAR, new Chain(acOrdenar));
        ac.addConsequence(SUCCESS, new Redirect(USUARIO_FICHAS_READ + DO));
        ac.addConsequence(LIST, new Redirect(FICHA + READ + DO));
        am.addActionConfig(ac);
        ac.addFilter(new ValidationFicha());

        ac = new ActionConfig(FICHA_DELETE, FichaDeleteAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(FICHA_DELETE)));
        ac.addConsequence(SUCCESS, new Chain(acUsuFicha));
        ac.addConsequence(ERROR, new Chain(acUsuFicha));
        am.addActionConfig(ac);

        ac = new ActionConfig(ATUALIZAR_STATUS_FICHA, AtualizarStatusFichaAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + "ficha/retorno.jsp"));
        am.addActionConfig(ac);

        ac = new ActionConfig(STATUS_FICHA, StatusFichaAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + "ficha/statusFicha.jsp"));
        am.addActionConfig(ac);
    }
}
