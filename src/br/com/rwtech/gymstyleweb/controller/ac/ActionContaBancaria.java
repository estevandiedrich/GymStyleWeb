package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstylecore.model.pojo.ContaBancaria;
import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.contabancaria.ContaBancariaCreateAction;
import br.com.rwtech.gymstyleweb.controller.action.contabancaria.ContaBancariaDeleteAction;
import br.com.rwtech.gymstyleweb.controller.action.contabancaria.ContaBancariaReadAction;
import br.com.rwtech.gymstyleweb.controller.action.contabancaria.ContaBancariaUpdateAction;
import br.com.rwtech.gymstyleweb.controller.action.contabancaria.ManagerContaBancariaAction;
import br.com.rwtech.gymstyleweb.controller.action.contabancaria.registros.EntradaRetiradaContaBancariaAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.contabancaria.registros.RegistroContaBancariaDeleteAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationContaBancaria;
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
public class ActionContaBancaria extends Action {

    public static Action instance = null;
    public static Action addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionContaBancaria();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionContaBancaria() {
    }

    protected void createActions() {
        ActionConfig ac = null;
        String path = "contabancaria";
        ac = new ActionConfig(CONTA_BANCARIA_READ, ContaBancariaReadAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(CONTA_BANCARIA_READ)));
        ac.addConsequence(SHOW, new Forward(JSP + path + LIST_PAGE));
        ac.addConsequence(SUCCESS, new Forward(JSP + path + CORPO_JSP));
        am.addActionConfig(ac);

        ac = new ActionConfig(CONTA_BANCARIA_CREATE, ContaBancariaCreateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(CONTA_BANCARIA_MANAGER)));
        ac.addFilter(new VOFilter(ContaBancaria.class, VO + path));
        ac.addFilter(new OVFilter(OV + path));
        ac.addConsequence(SUCCESS, new Redirect(CONTA_BANCARIA_READ + DO));
        ac.addConsequence(ERROR, new Forward(JSP + path + CREATE_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + path + CREATE_PAGE));
        am.addActionConfig(ac);
        ac.addFilter(new ValidationContaBancaria());

        ac = new ActionConfig(CONTA_BANCARIA_UPDATE, ContaBancariaUpdateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(CONTA_BANCARIA_MANAGER)));
        ac.addFilter(new VOFilter(ContaBancaria.class, VO + path));
        ac.addFilter(new OVFilter(OV + path));
        ac.addConsequence(SUCCESS, new Redirect(CONTA_BANCARIA_READ + DO));
        ac.addConsequence(ERROR, new Forward(JSP + path + UPDATE_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + path + UPDATE_PAGE));
        am.addActionConfig(ac);
        ac.addFilter(new ValidationContaBancaria());

        ac = new ActionConfig(CONTA_BANCARIA_DELETE, ContaBancariaDeleteAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(CONTA_BANCARIA_DELETE)));
        ac.addConsequence(ERROR, new Redirect(CONTA_BANCARIA_READ + DO));
        ac.addConsequence(SUCCESS, new Redirect(CONTA_BANCARIA_READ + DO));
        am.addActionConfig(ac);

        ac = new ActionConfig(MANAGER_CONTA_BANCARIA, ManagerContaBancariaAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(MANAGER_CONTA_BANCARIA)));
        ac.addConsequence(SHOW, new Forward(JSP + path + "/manager" + CORPO_JSP));
        ac.addConsequence(SUCCESS, new Forward(JSP + path + "/manager" + MANAGER_PAGE));
        ac.addFilter(new ValidationContaBancaria());
        am.addActionConfig(ac);

        ac = new ActionConfig(ENTRADA_RETIRADA_CONTA_BANCARIA, EntradaRetiradaContaBancariaAjaxAction.class);
//        ac.addFilter(new AuthorizationFilter(new Permission(CONTA_BANCARIA_MANAGER)));
//        ac.addFilter(new AuthorizationFilter(new Permission(ENTRADA_CONTA_BANCARIA)));
//        ac.addFilter(new AuthorizationFilter(new Permission(RETIRADA_CONTA_BANCARIA)));
        ac.addConsequence(ERROR, new Forward(JSP + path + "/retorno.jsp"));
        ac.addConsequence(SUCCESS, new Forward(JSP + path + "/retorno.jsp"));
        am.addActionConfig(ac);

        ac = new ActionConfig(DELETE_REGISTRO_CONTA_BANCARIA, RegistroContaBancariaDeleteAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(DELETE_REGISTRO_CONTA_BANCARIA)));
        ac.addConsequence(ERROR, new Forward(JSP + path + "/retorno.jsp"));
        ac.addConsequence(SUCCESS, new Forward(JSP + path + "/retorno.jsp"));

        am.addActionConfig(ac);
    }
}