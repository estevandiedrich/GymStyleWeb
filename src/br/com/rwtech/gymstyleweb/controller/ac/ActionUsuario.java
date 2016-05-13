package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.usuario.UsuarioAction;
import br.com.rwtech.gymstyleweb.controller.action.usuario.UsuarioCreateAction;
import br.com.rwtech.gymstyleweb.controller.action.usuario.UsuarioDeleteAction;
import br.com.rwtech.gymstyleweb.controller.action.usuario.UsuarioReadAction;
import br.com.rwtech.gymstyleweb.controller.action.usuario.update.UsuarioUpdateIdentificacaoAction;
import br.com.rwtech.gymstyleweb.controller.action.usuario.update.UsuarioUpdateInformacaoAction;
import br.com.rwtech.gymstyleweb.controller.action.usuario.update.UsuarioUpdatePlanoAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.usuario.ValidationIdentificacao;
import br.com.rwtech.gymstyleweb.controller.action.validation.usuario.ValidationUsuarioInformacao;
import br.com.rwtech.gymstyleweb.controller.action.validation.usuario.ValidationUsuarioPlano;
import org.mentawai.authorization.Permission;
import org.mentawai.core.ActionConfig;
import org.mentawai.core.Chain;
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
public class ActionUsuario extends Ac {

    private ApplicationManager am;
    public static ActionUsuario instance = null;

    public static ActionUsuario addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionUsuario();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionUsuario() {
    }

    protected void createActions() {
        //------  USUARIO  ---------------------------------------------------------
        ActionConfig ac = null;
        ac = new ActionConfig(USUARIO_READ, UsuarioReadAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(USUARIO_READ)));
        ac.addConsequence(SHOW, new Forward(JSP + USUARIO + LIST_PAGE));
        ac.addConsequence(SUCCESS, new Forward(JSP + USUARIO + CORPO_JSP));
        am.addActionConfig(ac);

        ActionConfig acCreUsu = new ActionConfig(USUARIO_CREATE_INFORMACAO, UsuarioCreateAction.class);
        acCreUsu.addFilter(new AuthorizationFilter(new Permission(USUARIO_MANAGER)));
        acCreUsu.addFilter(new VOFilter(Usuario.class, VO + USUARIO));
        acCreUsu.addFilter(new OVFilter(OV + USUARIO));
        acCreUsu.addConsequence(SUCCESS, new Redirect(USUARIO_READ + DO));
        acCreUsu.addConsequence(SHOW, new Forward(JSP + USUARIO + "/create" + CREATE_PAGE));
        acCreUsu.addConsequence(ERROR, new Forward(JSP + USUARIO + "/create" + CREATE_PAGE));
        acCreUsu.addFilter(new ValidationUsuarioInformacao());
        am.addActionConfig(acCreUsu);

        ac = new ActionConfig(USUARIO_CREATE_INFORMACAO, UsuarioCreateAction.class, DEFINIR);
        ac.addConsequence(SUCCESS, new Forward(JSP + USUARIO + "/foto/eventoFotoCreate.jsp"));
        ac.addFilter(new FileUploadFilter());
        am.addActionConfig(ac);

        ac = new ActionConfig(USUARIO_CREATE_INFORMACAO, UsuarioCreateAction.class, PREVIEW);
        ac.addConsequence(SUCCESS, new StreamConsequence("image/jpg"));
        am.addActionConfig(ac);

        ActionConfig acUpdInf = new ActionConfig(USUARIO_UPDATE_INFORMACAO, UsuarioUpdateInformacaoAction.class);
        acUpdInf.addFilter(new AuthorizationFilter(new Permission(USUARIO_MANAGER)));
        acUpdInf.addFilter(new VOFilter(Usuario.class, VO + USUARIO));
        acUpdInf.addFilter(new OVFilter(OV + USUARIO));
        acUpdInf.addConsequence(SUCCESS, new Redirect(USUARIO_READ + DO));
        acUpdInf.addConsequence(SHOW, new Forward(JSP + USUARIO + "/update/informacoes.page"));
        acUpdInf.addConsequence(ERROR, new Forward(JSP + USUARIO + "/update/informacoes.page"));
        acUpdInf.addFilter(new ValidationUsuarioInformacao());

        ActionConfig acUpdIde = new ActionConfig(USUARIO_UPDATE_IDENTIFICACAO, UsuarioUpdateIdentificacaoAction.class);
        acUpdIde.addFilter(new AuthorizationFilter(new Permission(USUARIO_MANAGER)));
        acUpdIde.addConsequence(SUCCESS, new Redirect(USUARIO_READ + DO));
        acUpdIde.addConsequence(SHOW, new Forward(JSP + USUARIO + "/update/identificacao.page"));
        acUpdIde.addConsequence(ERROR, new Forward(JSP + USUARIO + "/update/identificacao.page"));
        acUpdIde.addFilter(new ValidationIdentificacao());

        ActionConfig acUpdPla = new ActionConfig(USUARIO_UPDATE_PLANO, UsuarioUpdatePlanoAction.class);
        acUpdPla.addFilter(new AuthorizationFilter(new Permission(USUARIO_MANAGER)));
        acUpdPla.addConsequence(SUCCESS, new Redirect(USUARIO_READ + DO));
        acUpdPla.addConsequence(SHOW, new Forward(JSP + USUARIO + "/plano/planos.page"));
        acUpdPla.addConsequence(ERROR, new Forward(JSP + USUARIO + "/plano/planos.page"));
        acUpdPla.addFilter(new ValidationUsuarioPlano());
        am.addActionConfig(acUpdPla);

        //Usuario Create para identificacao
        acCreUsu.addConsequence(UsuarioAction.IDENTIFICACAO, new Chain(acUpdIde));

        acUpdInf.addConsequence(UsuarioAction.ATUALIZAR, new Chain(ActionRequisicao.get(am).getRequisicaoManager()));
        acUpdInf.addConsequence(UsuarioAction.IDENTIFICACAO, new Chain(acUpdIde));
        acUpdInf.addConsequence(UsuarioAction.PAGAMENTOS, new Chain(ActionPagamento.get(am).getAcaoVerPlanoUsuario()));
        acUpdInf.addConsequence(UsuarioAction.INESPERADO, new Redirect(USUARIO_READ + DO));

        acUpdIde.addConsequence(UsuarioAction.ATUALIZAR, new Chain(ActionRequisicao.get(am).getRequisicaoManager()));
        acUpdIde.addConsequence(UsuarioAction.INFORMACAO, new Chain(acUpdInf));
        acUpdIde.addConsequence(UsuarioAction.PLANO, new Chain(acUpdPla));

        acUpdPla.addConsequence(UsuarioAction.ATUALIZAR, new Chain(ActionRequisicao.get(am).getRequisicaoManager()));
        acUpdPla.addConsequence(UsuarioAction.IDENTIFICACAO, new Chain(acUpdIde));
        acUpdPla.addConsequence(UsuarioAction.PAGAMENTOS, new Chain(ActionPagamento.get(am).getAcaoVerPlanoUsuario()));

        am.addActionConfig(acUpdInf);
        am.addActionConfig(acUpdIde);
        am.addActionConfig(acUpdPla);

        ac = new ActionConfig(USUARIO_UPDATE_INFORMACAO, UsuarioUpdateInformacaoAction.class, "definir");
        ac.addConsequence(SUCCESS, new Forward(JSP + USUARIO + "/foto/eventoFotoUpdate.jsp"));
        ac.addFilter(new FileUploadFilter());
        am.addActionConfig(ac);

        ac = new ActionConfig(USUARIO_UPDATE_INFORMACAO, UsuarioUpdateInformacaoAction.class, "preview");
        ac.addConsequence(SUCCESS, new StreamConsequence("image/jpg"));
        am.addActionConfig(ac);

        ac = new ActionConfig(USUARIO_DELETE, UsuarioDeleteAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(USUARIO_DELETE)));
        ac.addConsequence(SUCCESS, new Redirect(USUARIO_READ + DO));
        ac.addConsequence(ERROR, new Redirect(USUARIO_READ + DO));
        ac.addConsequence(UsuarioAction.ATUALIZAR, new Chain(ActionRequisicao.get(am).getRequisicaoManager()));
        am.addActionConfig(ac);
    }
}
