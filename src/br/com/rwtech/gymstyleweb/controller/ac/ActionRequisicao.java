package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.requisicao.RequisicaoAtualizarTodos;
import br.com.rwtech.gymstyleweb.controller.action.requisicao.RequisicaoAtualizarUsuarioAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.requisicao.RequisicaoManagerAtualizarUsuarioAction;
import br.com.rwtech.gymstyleweb.controller.action.requisicao.RequisicaoManagerExcluirUsuarioAction;
import br.com.rwtech.gymstyleweb.controller.action.requisicao.RequisicaoReadAction;
import br.com.rwtech.gymstyleweb.controller.action.requisicao.RequisicaoReenviarAction;
import br.com.rwtech.gymstyleweb.controller.action.requisicao.RequisicaoUsuarioAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationConfiguracao;
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
public class ActionRequisicao extends Action {

    private ActionConfig acaoRequisicaoManager = null;
    private ActionConfig acaoExcluirUsuario = null;
    public static ActionRequisicao instance = null;

    public static ActionRequisicao addActions(ApplicationManager am) {
        return (get(am));
    }

    public static ActionRequisicao get(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionRequisicao();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionRequisicao() {
    }

    public ActionConfig getRequisicaoManager() {
        if (acaoRequisicaoManager == null) {
            createActions();
        }
        return acaoRequisicaoManager;
    }

    public ActionConfig getAcaoExcluirUsuario() {
        if (acaoExcluirUsuario == null) {
            createActions();
        }
        return acaoExcluirUsuario;
    }

    protected void createActions() {
        acaoRequisicaoManager = new ActionConfig(REQUISICAO_MANAGER, RequisicaoManagerAtualizarUsuarioAction.class);
        acaoRequisicaoManager.addFilter(new AuthorizationFilter(new Permission(REQUISICAO_MANAGER)));
        acaoRequisicaoManager.addConsequence(SHOW, new Forward(JSP + REQUISICAO + MANAGER_PAGE));
        acaoRequisicaoManager.addConsequence(LIST, new Forward(JSP + REQUISICAO + DESTINOS_JSP));
        acaoRequisicaoManager.addConsequence(ERROR, new Forward(JSP + REQUISICAO + ERROR_JSP));
        acaoRequisicaoManager.addConsequence(SUCCESS, new Forward(JSP + REQUISICAO + RESULT_JSP));
        am.addActionConfig(acaoRequisicaoManager);

        acaoExcluirUsuario = new ActionConfig(REQUISICAO_MANAGER_EXCLUIR, RequisicaoManagerExcluirUsuarioAction.class);
        acaoRequisicaoManager.addFilter(new AuthorizationFilter(new Permission(REQUISICAO_MANAGER)));
        acaoExcluirUsuario.addConsequence(SHOW, new Forward(JSP + REQUISICAO + "/managerExcluir.page"));
        acaoExcluirUsuario.addConsequence(LIST, new Forward(JSP + REQUISICAO + DESTINOS_JSP));
        acaoExcluirUsuario.addConsequence(ERROR, new Forward(JSP + REQUISICAO + ERROR_JSP));
        acaoExcluirUsuario.addConsequence(SUCCESS, new Forward(JSP + REQUISICAO + RESULT_JSP));
        am.addActionConfig(acaoExcluirUsuario);

        ActionConfig ac = null;
        ac = new ActionConfig(REQUISICAO_ATUALIZAR_USUARIO, RequisicaoUsuarioAction.class);
        ac.addConsequence(ERROR, new Redirect(REQUISICAO_READ + DO));
        ac.addConsequence(SUCCESS, new Chain(acaoRequisicaoManager));
        am.addActionConfig(ac);
        ac.addFilter(new ValidationConfiguracao());

        ac = new ActionConfig(REQUISICAO_ATUALIZAR_TODOS, RequisicaoAtualizarTodos.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + REQUISICAO + "/listTodos.page"));
        am.addActionConfig(ac);

        ac = new ActionConfig(REQUISICAO_REENVIAR, RequisicaoReenviarAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + REQUISICAO + "/sucessoSincronizacao.jsp"));
        ac.addConsequence(ERROR, new Forward(JSP + REQUISICAO + "/errorSincronizacao.jsp"));
        am.addActionConfig(ac);

        ac = new ActionConfig(REQUISICAO_READ, RequisicaoReadAction.class);
        acaoRequisicaoManager.addFilter(new AuthorizationFilter(new Permission(REQUISICAO_MANAGER)));
        ac.addConsequence(SHOW, new Forward(JSP + REQUISICAO + LIST_PAGE));
        ac.addConsequence(SUCCESS, new Forward(JSP + REQUISICAO + CORPO_JSP));
        am.addActionConfig(ac);

        //AJAX ATUALIZAR TODOS
        ac = new ActionConfig(ATUALIZAR_USUARIO, RequisicaoAtualizarUsuarioAjaxAction.class);
        ac.addConsequence(SHOW, new Forward(JSP + "requisicao/sincronizando.jsp"));
        ac.addConsequence(ERROR, new Forward(JSP + "requisicao/naoSincronizado.jsp"));
        ac.addConsequence(MODULO_CHEIO, new Forward(JSP + "requisicao/errorModuloLotado.jsp"));
        ac.addConsequence(SUCCESS, new Forward(JSP + "requisicao/sincronizado.jsp"));
        am.addActionConfig(ac);
    }
}
