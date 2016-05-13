package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstylecore.model.pojo.Fornecedor;
import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.fornecedor.FornecedorCreateAction;
import br.com.rwtech.gymstyleweb.controller.action.fornecedor.FornecedorDeleteAction;
import br.com.rwtech.gymstyleweb.controller.action.fornecedor.FornecedorReadAction;
import br.com.rwtech.gymstyleweb.controller.action.fornecedor.FornecedorUpdateAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationFornecedor;
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
public class ActionFornecedor  extends Ac {

    private static ApplicationManager am;
    public static ActionFornecedor instance = null;

    public static ActionFornecedor addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionFornecedor();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionFornecedor() {
    }

    protected void createActions() {
        ActionConfig ac = null;
        ac = new ActionConfig(FORNECEDOR_READ, FornecedorReadAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(FORNECEDOR_READ)));
        ac.addConsequence(SHOW, new Forward(JSP + FORNECEDOR + LIST_PAGE));
        ac.addConsequence(SUCCESS, new Forward(JSP + FORNECEDOR + CORPO_JSP));
        am.addActionConfig(ac);

        ac = new ActionConfig(FORNECEDOR_CREATE, FornecedorCreateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(FORNECEDOR_MANAGER)));
        ac.addFilter(new VOFilter(Fornecedor.class, VO + FORNECEDOR));
        ac.addFilter(new OVFilter(OV + FORNECEDOR));
        ac.addConsequence(SUCCESS, new Redirect(FORNECEDOR_READ + DO));
        ac.addConsequence(ERROR, new Forward(JSP + FORNECEDOR + CREATE_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + FORNECEDOR + CREATE_PAGE));
        ac.addFilter(new ValidationFornecedor());
        am.addActionConfig(ac);

        ac = new ActionConfig(FORNECEDOR_UPDATE, FornecedorUpdateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(FORNECEDOR_MANAGER)));
        ac.addFilter(new VOFilter(Fornecedor.class, VO + FORNECEDOR));
        ac.addFilter(new OVFilter(OV + FORNECEDOR));
        ac.addConsequence(SUCCESS, new Redirect(FORNECEDOR_READ + DO));
        ac.addConsequence(ERROR, new Forward(JSP + FORNECEDOR + UPDATE_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + FORNECEDOR + UPDATE_PAGE));
        ac.addFilter(new ValidationFornecedor());
        am.addActionConfig(ac);

        ac = new ActionConfig(FORNECEDOR_DELETE, FornecedorDeleteAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(FORNECEDOR_DELETE)));
        ac.addConsequence(ERROR, new Redirect(FORNECEDOR_READ + DO));
        ac.addConsequence(SUCCESS, new Redirect(FORNECEDOR_READ + DO));
        am.addActionConfig(ac);
    }
}