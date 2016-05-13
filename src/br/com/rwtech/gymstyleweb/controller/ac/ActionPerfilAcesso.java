package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstylecore.model.pojo.PerfilAcesso;
import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.perfilacesso.PerfilAcessoCreateAction;
import br.com.rwtech.gymstyleweb.controller.action.perfilacesso.PerfilAcessoDeleteAction;
import br.com.rwtech.gymstyleweb.controller.action.perfilacesso.PerfilAcessoReadAction;
import br.com.rwtech.gymstyleweb.controller.action.perfilacesso.PerfilAcessoUpdateAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationPerfilAcesso;
import org.mentawai.core.ActionConfig;
import org.mentawai.core.Forward;
import org.mentawai.core.Redirect;
import org.mentawai.filter.OVFilter;
import org.mentawai.filter.PaginatorFilter;
import org.mentawai.filter.VOFilter;

/**
 *
 * @author Éder Faria
 */
public class ActionPerfilAcesso extends Action {

    public static Action instance = null;
    public static Action addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionPerfilAcesso();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionPerfilAcesso() {
    }

    protected void createActions() {
//------  PERFIL DE ACESSO  ---------------------------------------------------------
        ActionConfig ac = null;
        String PERFIL_ACESSO = "perfilAcesso";
        ac = new ActionConfig(PERFIL_ACESSO_READ, PerfilAcessoReadAction.class);
        //ac.addFilter(new AuthorizationFilter(new Permission(PERFIL_ACESSO_READ)));
        ac.addConsequence(SUCCESS, new Forward(JSP + PERFIL_ACESSO.toLowerCase() + LIST_PAGE));
        ac.addFilter(new PaginatorFilter(LIST, 10));
        am.addActionConfig(ac);

        ac = new ActionConfig(PERFIL_ACESSO_CREATE, PerfilAcessoCreateAction.class);
        //ac.addFilter(new AuthorizationFilter(new Permission(PERFIL_ACESSO_MANAGER)));
        ac.addFilter(new VOFilter(PerfilAcesso.class, VO + PERFIL_ACESSO));
        ac.addFilter(new OVFilter(OV + PERFIL_ACESSO));
        ac.addConsequence(SUCCESS, new Redirect(PERFIL_ACESSO_READ + DO));
        ac.addConsequence(ERROR, new Forward(JSP + PERFIL_ACESSO.toLowerCase() + CREATE_PAGE));
        am.addActionConfig(ac);
        ac.addFilter(new ValidationPerfilAcesso());

        ac = new ActionConfig(PERFIL_ACESSO_UPDATE, PerfilAcessoUpdateAction.class);
        //ac.addFilter(new AuthorizationFilter(new Permission(PERFIL_ACESSO_MANAGER)));
        ac.addFilter(new VOFilter(PerfilAcesso.class, VO + PERFIL_ACESSO));
        ac.addFilter(new OVFilter(OV + PERFIL_ACESSO));
        ac.addConsequence(SUCCESS, new Redirect(PERFIL_ACESSO_READ + DO));
        ac.addConsequence(ERROR, new Forward(JSP + PERFIL_ACESSO.toLowerCase() + UPDATE_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + PERFIL_ACESSO.toLowerCase() + UPDATE_PAGE));
        am.addActionConfig(ac);
        ac.addFilter(new ValidationPerfilAcesso());

        ac = new ActionConfig(PERFIL_ACESSO_DELETE, PerfilAcessoDeleteAction.class);
        //ac.addFilter(new AuthorizationFilter(new Permission(PERFIL_ACESSO_DELETE)));
        ac.addConsequence(SUCCESS, new Redirect(PERFIL_ACESSO_READ + DO));
        ac.addConsequence(ERROR, new Redirect(PERFIL_ACESSO_READ + DO));
        am.addActionConfig(ac);
    }
}