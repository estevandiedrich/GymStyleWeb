package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.produto.venda.VendaManagerAction;
import org.mentawai.authorization.Permission;
import org.mentawai.core.ActionConfig;
import org.mentawai.core.Forward;
import org.mentawai.filter.AuthorizationFilter;

/**
 *
 * @author Éder Faria
 */
public class ActionVenda extends Action {

    public static Action instance = null;

    public static Action addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionVenda();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionVenda() {
    }

    protected void createActions() {

        ActionConfig ac = null;
        ac = new ActionConfig(VENDA_MANAGER, VendaManagerAction.class);
        //ac.addFilter(new AuthorizationFilter(new Permission(VENDA_MANAGER)));
        ac.addConsequence(SHOW, new Forward(JSP + VENDA + MANAGER_PAGE));
        ac.addConsequence(SUCCESS, new Forward(JSP + VENDA + MANAGER_PAGE));
        am.addActionConfig(ac);

//        ac = new ActionConfig(PRODUTO_CREATE, ProdutoCreateAction.class);
//        ac.addFilter(new AuthorizationFilter(new Permission(CREATE + POJO)));
//        ac.addFilter(new VOFilter(Produto.class, VO + POJO));
//        ac.addFilter(new OVFilter(OV + POJO));
//        ac.addConsequence(SUCCESS, new Redirect(POJO + READ + DO));
//        ac.addConsequence(ERROR, new Forward(JSP + POJO + CREATE_PAGE));
//        ac.addConsequence(SHOW, new Forward(JSP + POJO + CREATE_PAGE));
//        ac.addFilter(new ValidationProduto());
//        am.addActionConfig(ac);
//
//        ac = new ActionConfig(PRODUTO_UPDATE, ProdutoUpdateAction.class);
//        ac.addFilter(new AuthorizationFilter(new Permission(UPDATE + POJO)));
//        ac.addFilter(new VOFilter(Produto.class, VO + POJO));
//        ac.addFilter(new OVFilter(OV + POJO));
//        ac.addConsequence(SUCCESS, new Redirect(POJO + READ + DO));
//        ac.addConsequence(ERROR, new Forward(JSP + POJO + UPDATE_PAGE));
//        ac.addConsequence(SHOW, new Forward(JSP + POJO + UPDATE_PAGE));
//        ac.addFilter(new ValidationProduto());
//        am.addActionConfig(ac);
//
//        ac = new ActionConfig(PRODUTO_DELETE, ProdutoDisabledAction.class);
//        ac.addFilter(new AuthorizationFilter(new Permission(DELETE + POJO)));
//        ac.addConsequence(ERROR, new Redirect(POJO + READ + DO));
//        ac.addConsequence(SUCCESS, new Redirect(POJO + READ + DO));
//        am.addActionConfig(ac);
    }
}
