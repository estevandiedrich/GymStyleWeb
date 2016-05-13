package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstylecore.model.pojo.Empresa;
import br.com.rwtech.gymstylecore.model.pojo.Exercicio;
import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.empresa.EmpresaCreateAction;
import br.com.rwtech.gymstyleweb.controller.action.empresa.EmpresaDeleteAction;
import br.com.rwtech.gymstyleweb.controller.action.empresa.EmpresaReadAction;
import br.com.rwtech.gymstyleweb.controller.action.empresa.EmpresaUpdateAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationEmpresa;
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
public class ActionEmpresa extends Action {
    public static Action instance = null;

    public static Action addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionEmpresa();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionEmpresa() {
    }

    protected void createActions() {
        ActionConfig ac = null;
        ac = new ActionConfig(EMPRESA_READ, EmpresaReadAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(EMPRESA_READ)));
        ac.addConsequence(SUCCESS, new Forward(JSP + EMPRESA + LIST_PAGE));
        am.addActionConfig(ac);

        ac = new ActionConfig(EMPRESA_CREATE, EmpresaCreateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(EMPRESA_MANAGER)));
        ac.addFilter(new VOFilter(Exercicio.class, VO + EMPRESA));
        ac.addFilter(new OVFilter(OV + EMPRESA));
        ac.addConsequence(SUCCESS, new Redirect(EMPRESA_READ + DO));
        ac.addConsequence(ERROR, new Forward(JSP + EMPRESA + CREATE_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + EMPRESA + CREATE_PAGE));
        am.addActionConfig(ac);
        ac.addFilter(new ValidationEmpresa());

        ac = new ActionConfig(EMPRESA_UPDATE, EmpresaUpdateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(EMPRESA_MANAGER)));
        ac.addFilter(new VOFilter(Empresa.class, VO + EMPRESA));
        ac.addFilter(new OVFilter(OV + EMPRESA));
        ac.addConsequence(SUCCESS, new Redirect(EMPRESA_READ + DO));
        ac.addConsequence(ERROR, new Forward(JSP + EMPRESA + UPDATE_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + EMPRESA + UPDATE_PAGE));
        am.addActionConfig(ac);
        ac.addFilter(new ValidationEmpresa());

        ac = new ActionConfig(EMPRESA_DELETE, EmpresaDeleteAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(EMPRESA_DELETE)));
        ac.addConsequence(ERROR, new Redirect(EMPRESA_READ + DO));
        ac.addConsequence(SUCCESS, new Redirect(EMPRESA_READ + DO));
        am.addActionConfig(ac);

    }
}