package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstylecore.model.pojo.AvaliacaoFisica;
import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.avaliacaofisica.AvaliacaoFisicaCreateAction;
import br.com.rwtech.gymstyleweb.controller.action.avaliacaofisica.AvaliacaoFisicaDeleteAction;
import br.com.rwtech.gymstyleweb.controller.action.avaliacaofisica.AvaliacaoFisicaInfoAction;
import br.com.rwtech.gymstyleweb.controller.action.avaliacaofisica.AvaliacaoFisicaReadAction;
import br.com.rwtech.gymstyleweb.controller.action.avaliacaofisica.AvaliacaoFisicaUpdateAction;
import br.com.rwtech.gymstyleweb.controller.action.avaliacaofisica.UsuarioAvaliacaoFisicaReadAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationAvaliacaoFisica;
import org.mentawai.authorization.Permission;
import org.mentawai.core.ActionConfig;
import org.mentawai.core.Chain;
import org.mentawai.core.Forward;
import org.mentawai.core.Redirect;
import org.mentawai.filter.AuthorizationFilter;
import org.mentawai.filter.OVFilter;
import org.mentawai.filter.PaginatorFilter;
import org.mentawai.filter.VOFilter;

/**
 *
 * @author Éder Faria
 */
public class ActionAvaliacaoFisica extends Action {

    public static Action instance = null;

    public static Action addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionAvaliacaoFisica();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionAvaliacaoFisica() {
    }

    protected void createActions() {
        ActionConfig ac = null;
        String AVALIACAO_FISICA = "avaliacaoFisica";
        ac = new ActionConfig(USUARIO_AVALIACAO_FISICA_READ, UsuarioAvaliacaoFisicaReadAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(AVALIACAO_FISICA_READ)));
        ac.addConsequence(SHOW, new Forward(JSP + AVALIACAO_FISICA.toLowerCase() + "/usuarioList.page"));
        ac.addConsequence(SUCCESS, new Forward(JSP + AVALIACAO_FISICA.toLowerCase() + CORPO_JSP));
        am.addActionConfig(ac);

        ActionConfig acaoAvaliacoesRead = new ActionConfig(USUARIO_AVALIACOES, AvaliacaoFisicaReadAction.class);
        acaoAvaliacoesRead.addFilter(new AuthorizationFilter(new Permission(AVALIACAO_FISICA_READ)));
        acaoAvaliacoesRead.addConsequence(SUCCESS, new Forward(JSP + AVALIACAO_FISICA.toLowerCase() + LIST_PAGE));
        acaoAvaliacoesRead.addConsequence(LIST, new Redirect(USUARIO_AVALIACAO_FISICA_READ + DO));
        acaoAvaliacoesRead.addFilter(new PaginatorFilter(LIST, 10));
        am.addActionConfig(acaoAvaliacoesRead);

        ac = new ActionConfig(AVALIACAO_FISICA_INFO, AvaliacaoFisicaInfoAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(AVALIACAO_FISICA_READ)));
        ac.addConsequence(SUCCESS, new Forward(JSP + AVALIACAO_FISICA.toLowerCase() + "/info.page"));
        ac.addConsequence(ERROR, new Redirect(USUARIO_AVALIACAO_FISICA_READ + DO));
        ac.addConsequence(LIST, new Redirect(USUARIO_AVALIACAO_FISICA_READ + DO));
        am.addActionConfig(ac);
        ac.addFilter(new ValidationAvaliacaoFisica());

        ac = new ActionConfig(AVALIACAO_FISICA_CREATE, AvaliacaoFisicaCreateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(AVALIACAO_FISICA_MANAGER)));
        ac.addFilter(new VOFilter(AvaliacaoFisica.class, VO + AVALIACAO_FISICA));
        ac.addFilter(new OVFilter(OV + AVALIACAO_FISICA));
        ac.addConsequence(SUCCESS, new Chain(acaoAvaliacoesRead));
        ac.addConsequence(ERROR, new Forward(JSP + AVALIACAO_FISICA.toLowerCase() + CREATE_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + AVALIACAO_FISICA.toLowerCase() + CREATE_PAGE));
        ac.addConsequence(LIST, new Redirect(USUARIO_AVALIACAO_FISICA_READ + DO));
        am.addActionConfig(ac);
        ac.addFilter(new ValidationAvaliacaoFisica());

        ac = new ActionConfig(AVALIACAO_FISICA_UPDATE, AvaliacaoFisicaUpdateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(AVALIACAO_FISICA_MANAGER)));
        ac.addFilter(new VOFilter(AvaliacaoFisica.class, VO + AVALIACAO_FISICA));
        ac.addFilter(new OVFilter(OV + AVALIACAO_FISICA));
        ac.addConsequence(SUCCESS, new Redirect(AVALIACAO_FISICA_READ + DO));
        ac.addConsequence(ERROR, new Forward(JSP + AVALIACAO_FISICA.toLowerCase() + UPDATE_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + AVALIACAO_FISICA.toLowerCase() + UPDATE_PAGE));
        am.addActionConfig(ac);
        ac.addFilter(new ValidationAvaliacaoFisica());

        ac = new ActionConfig(AVALIACAO_FISICA_DELETE, AvaliacaoFisicaDeleteAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(AVALIACAO_FISICA_DELETE)));
//        ac.addConsequence(SUCCESS, new Redirect(AVALIACAO_FISICA_READ + DO));
        ac.addConsequence(SUCCESS, new Chain(acaoAvaliacoesRead));
        ac.addConsequence(ERROR, new Chain(acaoAvaliacoesRead));
        am.addActionConfig(ac);
    }
}