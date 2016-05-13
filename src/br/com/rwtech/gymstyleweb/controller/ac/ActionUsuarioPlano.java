package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.pagamentos.FinalizarPlanoAction;
import br.com.rwtech.gymstyleweb.controller.action.usuarioplano.CancelarPlanoAction;
import br.com.rwtech.gymstyleweb.controller.action.usuarioplano.UsuarioPlanosReadAction;
import br.com.rwtech.gymstyleweb.controller.action.usuarioplano.UsuariosPlanoReadAction;
import br.com.rwtech.gymstyleweb.controller.action.usuarioplano.VincularPlanoAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationVincularPlano;
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
public class ActionUsuarioPlano extends Ac {

    protected ApplicationManager am;
    public static ActionUsuarioPlano instance = null;

    public static ActionUsuarioPlano addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionUsuarioPlano();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionUsuarioPlano() {
    }

    protected void createActions() {
        //LISTAR PLANOS VINCULADOS
        ActionConfig ac = new ActionConfig(USUARIO_PLANOS_READ, UsuarioPlanosReadAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(USUARIO_PLANOS_READ)));
        ac.addConsequence(SUCCESS, new Forward(JSP + "usuarioplano" + "/listUsuarioPlanos.page"));
        am.addActionConfig(ac);

        //LISTAR PLANOS VINCULADOS
        ac = new ActionConfig(USUARIOS_PLANO_READ, UsuariosPlanoReadAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(USUARIO_PLANOS_READ)));
        ac.addConsequence(SHOW, new Forward(JSP + "usuarioplano" + LIST_PAGE));
        ac.addConsequence(SUCCESS, new Forward(JSP + "usuarioplano" + CORPO_JSP));
        am.addActionConfig(ac);

        //CANCELAR PLANO USUARIO
        ac = new ActionConfig(CANCELAR_PLANO, CancelarPlanoAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(CANCELAR_PLANO)));
        ac.addConsequence(SHOW, new Forward(JSP + "usuarioplano/cancelarPlano.page"));
        ac.addConsequence(ERROR, new Forward(JSP + "usuarioplano/cancelarPlano.page"));
        ac.addConsequence(Ac.ATUALIZAR, new Chain(ActionRequisicao.get(am).getRequisicaoManager()));
        ac.addConsequence(Ac.EXCLUIR, new Chain(ActionRequisicao.get(am).getAcaoExcluirUsuario()));
        ac.addConsequence(LIST, new Redirect(USUARIOS_PLANO_READ + DO));
        am.addActionConfig(ac);

        ac = new ActionConfig(FINALIZAR_PLANO, FinalizarPlanoAction.class);
        ac.addConsequence(SUCCESS, new Redirect(USUARIOS_PLANO_READ + DO));
        am.addActionConfig(ac);

        //VINCULAR PLANO
        ac = new ActionConfig(VINCULAR_PLANO, VincularPlanoAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(VINCULAR_PLANO)));
        ac.addConsequence(SHOW, new Forward(JSP + "usuarioplano/vincularPlano.page"));
        ac.addConsequence(ERROR, new Forward(JSP + "usuarioplano/vincularPlano.page"));
        ac.addConsequence(EXCEPTION, new Redirect(USUARIOS_PLANO_READ + DO));
        ac.addConsequence(VincularPlanoAction.ATUALIZAR, new Chain(ActionRequisicao.get(am).getRequisicaoManager()));
        ac.addConsequence(SUCCESS, new Chain(ActionPagamento.get(am).getAcaoVerPlanoUsuario()));
        ac.addFilter(new ValidationVincularPlano());
        am.addActionConfig(ac);
    }
}
