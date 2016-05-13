package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstylecore.model.pojo.Produto;
import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.produto.ProdutoCreateAction;
import br.com.rwtech.gymstyleweb.controller.action.produto.ProdutoDisabledAction;
import br.com.rwtech.gymstyleweb.controller.action.produto.ProdutoReadAction;
import br.com.rwtech.gymstyleweb.controller.action.produto.ProdutoUpdateAction;
import br.com.rwtech.gymstyleweb.controller.action.produto.venda.ProdutoAjaxtReadAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationProduto;
import org.mentawai.authorization.Permission;
import org.mentawai.core.ActionConfig;
import org.mentawai.core.Forward;
import org.mentawai.core.Redirect;
import org.mentawai.core.StreamConsequence;
import org.mentawai.filter.AuthorizationFilter;
import org.mentawai.filter.FileUploadFilter;
import org.mentawai.filter.OVFilter;
import org.mentawai.filter.VOFilter;

/**
 *
 * @author Éder Faria
 */
public class ActionProduto extends Action {

    public static Action instance = null;
    public static Action addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionProduto();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionProduto() {
    }

    protected void createActions() {
        ActionConfig ac = null;
        ac = new ActionConfig(PRODUTO_READ, ProdutoReadAction.class);
        //ac.addFilter(new AuthorizationFilter(new Permission(PRODUTO_READ)));
        ac.addConsequence(SHOW, new Forward(JSP + PRODUTO + LIST_PAGE));
        ac.addConsequence(SUCCESS, new Forward(JSP + PRODUTO + CORPO_JSP));
        am.addActionConfig(ac);

        ac = new ActionConfig(PRODUTO_CREATE, ProdutoCreateAction.class);
        //ac.addFilter(new AuthorizationFilter(new Permission(PRODUTO_MANAGER)));
        ac.addFilter(new VOFilter(Produto.class, VO + PRODUTO));
        ac.addFilter(new OVFilter(OV + PRODUTO));
        ac.addConsequence(SUCCESS, new Redirect(PRODUTO + READ + DO));
        ac.addConsequence(ERROR, new Forward(JSP + PRODUTO + CREATE_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + PRODUTO + CREATE_PAGE));
        ac.addFilter(new ValidationProduto());
        am.addActionConfig(ac);

        ac = new ActionConfig(Ac.PRODUTO_CREATE, ProdutoCreateAction.class, Ac.DEFINIR);
        ac.addConsequence(SUCCESS, new Forward(JSP + PRODUTO + "/foto/eventoFotoCreate.jsp"));
        ac.addFilter(new FileUploadFilter());
        am.addActionConfig(ac);

        ac = new ActionConfig(Ac.PRODUTO_CREATE, ProdutoCreateAction.class, Ac.PREVIEW);
        ac.addConsequence(SUCCESS, new StreamConsequence("image/jpg"));
        am.addActionConfig(ac);

        ac = new ActionConfig(PRODUTO_UPDATE, ProdutoUpdateAction.class);
        //ac.addFilter(new AuthorizationFilter(new Permission(PRODUTO_MANAGER)));
        ac.addFilter(new VOFilter(Produto.class, VO + PRODUTO));
        ac.addFilter(new OVFilter(OV + PRODUTO));
        ac.addConsequence(SUCCESS, new Redirect(PRODUTO + READ + DO));
        ac.addConsequence(ERROR, new Forward(JSP + PRODUTO + UPDATE_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + PRODUTO + UPDATE_PAGE));
        ac.addFilter(new ValidationProduto());
        am.addActionConfig(ac);

        ac = new ActionConfig(Ac.PRODUTO_UPDATE, ProdutoUpdateAction.class, Ac.DEFINIR);
        ac.addConsequence(SUCCESS, new Forward(JSP + PRODUTO + "/foto/eventoFotoUpdate.jsp"));
        ac.addFilter(new FileUploadFilter());
        am.addActionConfig(ac);

        ac = new ActionConfig(Ac.PRODUTO_UPDATE, ProdutoUpdateAction.class, Ac.PREVIEW);
        ac.addConsequence(SUCCESS, new StreamConsequence("image/jpg"));
        am.addActionConfig(ac);

        ac = new ActionConfig(PRODUTO_DELETE, ProdutoDisabledAction.class);
        //ac.addFilter(new AuthorizationFilter(new Permission(PRODUTO_DELETE)));
        ac.addConsequence(ERROR, new Redirect(PRODUTO + READ + DO));
        ac.addConsequence(SUCCESS, new Redirect(PRODUTO + READ + DO));
        am.addActionConfig(ac);

        ac = new ActionConfig(Ac.PRODUTO_AJAX_READ, ProdutoAjaxtReadAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + "venda/produtos.jsp"));
//        ac.addConsequence("AJAX", new AjaxConsequence(AjaxConsequence.KEY, new JSONObjectAjaxRenderer()));
        am.addActionConfig(ac);
    }
}
