package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstylecore.model.pojo.Modalidade;
import br.com.rwtech.gymstyleweb.controller.action.modalidade.ModalidadeCreateAction;
import br.com.rwtech.gymstyleweb.controller.action.modalidade.ModalidadeDeleteAction;
import br.com.rwtech.gymstyleweb.controller.action.modalidade.ModalidadeReadAction;
import br.com.rwtech.gymstyleweb.controller.action.modalidade.ModalidadeUpdateAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationModalidade;
import org.mentawai.authorization.Permission;
import org.mentawai.core.ActionConfig;
import org.mentawai.core.ApplicationManager;
import org.mentawai.core.Forward;
import org.mentawai.core.Redirect;
import org.mentawai.filter.AuthorizationFilter;
import org.mentawai.filter.OVFilter;
import org.mentawai.filter.VOFilter;

/**
 *
 * @author Éder Faria
 */
public class ActionModalidade extends Action{
    public static Action instance = null;

    public static Action addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionModalidade();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }
    
    private ActionModalidade() {
    }

    @Override
    protected void createActions() {
        ActionConfig ac = null;
        ac = new ActionConfig(MODALIDADE_READ, ModalidadeReadAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(MODALIDADE_READ)));
        ac.addConsequence(SHOW, new Forward(JSP + MODALIDADE + LIST_PAGE));
        ac.addConsequence(SUCCESS, new Forward(JSP + MODALIDADE + CORPO_JSP));
        am.addActionConfig(ac);

        ac = new ActionConfig(MODALIDADE_CREATE, ModalidadeCreateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(MODALIDADE_MANAGER)));
        ac.addFilter(new VOFilter(Modalidade.class, VO + MODALIDADE));
        ac.addFilter(new OVFilter(OV + MODALIDADE));
        ac.addConsequence(SUCCESS, new Redirect(MODALIDADE_READ + DO));
        ac.addConsequence(ERROR, new Forward(JSP + MODALIDADE + CREATE_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + MODALIDADE + CREATE_PAGE));
        am.addActionConfig(ac);
        ac.addFilter(new ValidationModalidade());

        ac = new ActionConfig(MODALIDADE_UPDATE, ModalidadeUpdateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(MODALIDADE_MANAGER)));
        ac.addFilter(new VOFilter(Modalidade.class, VO + MODALIDADE));
        ac.addFilter(new OVFilter(OV + MODALIDADE));
        ac.addConsequence(SUCCESS, new Redirect(MODALIDADE_READ + DO));
        ac.addConsequence(ERROR, new Forward(JSP + MODALIDADE + UPDATE_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + MODALIDADE + UPDATE_PAGE));
        am.addActionConfig(ac);
        ac.addFilter(new ValidationModalidade());

        ac = new ActionConfig(MODALIDADE_DELETE, ModalidadeDeleteAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(MODALIDADE_DELETE)));
        ac.addConsequence(SUCCESS, new Redirect(MODALIDADE_READ + DO));
        ac.addConsequence(ERROR, new Redirect(MODALIDADE_READ + DO));
        am.addActionConfig(ac);
    }
    
    
}
