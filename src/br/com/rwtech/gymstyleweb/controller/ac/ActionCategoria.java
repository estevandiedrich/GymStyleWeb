package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstylecore.model.pojo.Categoria;
import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.categoria.CategoriaCreateAction;
import br.com.rwtech.gymstyleweb.controller.action.categoria.CategoriaDeleteAction;
import br.com.rwtech.gymstyleweb.controller.action.categoria.CategoriaReadAction;
import br.com.rwtech.gymstyleweb.controller.action.categoria.CategoriaUpdateAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationCategoria;
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
public class ActionCategoria extends Action {

    public static Action instance = null;
    public static Action addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionCategoria();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionCategoria() {
    }

    protected void createActions() {
        ActionConfig ac = null;
        ac = new ActionConfig(Ac.CATEGORIA_READ, CategoriaReadAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(CATEGORIA_READ)));
        ac.addConsequence(SHOW, new Forward(JSP + CATEGORIA.toLowerCase() + LIST_PAGE));
        ac.addConsequence(SUCCESS, new Forward(JSP + CATEGORIA.toLowerCase() + CORPO_JSP));
        am.addActionConfig(ac);

        ac = new ActionConfig(Ac.CATEGORIA_CREATE, CategoriaCreateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(CATEGORIA_CREATE)));
        ac.addFilter(new VOFilter(Categoria.class, VO + CATEGORIA));
        ac.addFilter(new OVFilter(OV + CATEGORIA));
        ac.addConsequence(SUCCESS, new Redirect(Ac.CATEGORIA_READ + DO));
        ac.addConsequence(ERROR, new Forward(JSP + CATEGORIA.toLowerCase() + CREATE_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + CATEGORIA.toLowerCase() + CREATE_PAGE));
        ac.addFilter(new ValidationCategoria());
        am.addActionConfig(ac);

        ac = new ActionConfig(Ac.CATEGORIA_UPDATE, CategoriaUpdateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(CATEGORIA_UPDATE)));
        ac.addFilter(new VOFilter(Categoria.class, VO + CATEGORIA));
        ac.addFilter(new OVFilter(OV + CATEGORIA));
        ac.addConsequence(SUCCESS, new Redirect(Ac.CATEGORIA_READ + DO));
        ac.addConsequence(ERROR, new Forward(JSP + CATEGORIA.toLowerCase() + UPDATE_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + CATEGORIA.toLowerCase() + UPDATE_PAGE));
        ac.addFilter(new ValidationCategoria());
        am.addActionConfig(ac);

        ac = new ActionConfig(Ac.CATEGORIA_DELETE, CategoriaDeleteAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(CATEGORIA_DELETE)));
        ac.addConsequence(SUCCESS, new Redirect(Ac.CATEGORIA_READ + DO));
        ac.addConsequence(ERROR, new Redirect(Ac.CATEGORIA_READ + DO));
        am.addActionConfig(ac);
    }
}
