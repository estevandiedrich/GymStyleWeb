package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.funcionario.FuncionarioAction;
import br.com.rwtech.gymstyleweb.controller.action.funcionario.FuncionarioCreateAction;
import br.com.rwtech.gymstyleweb.controller.action.funcionario.FuncionarioDeleteAction;
import br.com.rwtech.gymstyleweb.controller.action.funcionario.FuncionarioReadAction;
import br.com.rwtech.gymstyleweb.controller.action.funcionario.update.FuncionarioUpdateAcessoAction;
import br.com.rwtech.gymstyleweb.controller.action.funcionario.update.FuncionarioUpdateIdentificacaoAction;
import br.com.rwtech.gymstyleweb.controller.action.funcionario.update.FuncionarioUpdateInformacaoAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.usuario.ValidationFuncionarioAcesso;
import br.com.rwtech.gymstyleweb.controller.action.validation.usuario.ValidationFuncionarioInformacao;
import br.com.rwtech.gymstyleweb.controller.action.validation.usuario.ValidationIdentificacao;
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
public class ActionFuncionario extends Ac {

    private static ApplicationManager am;
    public static ActionFuncionario instance = null;

    public static ActionFuncionario addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionFuncionario();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionFuncionario() {
    }

    protected void createActions() {
        ActionConfig ac = null;
        ac = new ActionConfig(FUNCIONARIO_READ, FuncionarioReadAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(FUNCIONARIO_READ)));
        ac.addConsequence(SHOW, new Forward(JSP + FUNCIONARIO + LIST_PAGE));
        ac.addConsequence(SUCCESS, new Forward(JSP + FUNCIONARIO + CORPO_JSP));
        am.addActionConfig(ac);

        ActionConfig acUpdIden = new ActionConfig(FUNCIONARIO_UPDATE_IDENTIFICACAO, FuncionarioUpdateIdentificacaoAction.class);
        acUpdIden.addFilter(new AuthorizationFilter(new Permission(FUNCIONARIO_MANAGER)));
        acUpdIden.addConsequence(SUCCESS, new Redirect(FUNCIONARIO_READ + DO));
        acUpdIden.addConsequence(EXCEPTION, new Redirect(FUNCIONARIO_READ + DO));
        acUpdIden.addConsequence(SHOW, new Forward(JSP + FUNCIONARIO + "/update/identificacao.page"));
        acUpdIden.addConsequence(ERROR, new Forward(JSP + FUNCIONARIO + "/update/identificacao.page"));
        acUpdIden.addFilter(new ValidationIdentificacao());

        ActionConfig acUpdAces = new ActionConfig(FUNCIONARIO_UPDATE_ACESSO, FuncionarioUpdateAcessoAction.class);
        acUpdAces.addFilter(new AuthorizationFilter(new Permission(FUNCIONARIO_MANAGER)));
        acUpdAces.addConsequence(SUCCESS, new Redirect(FUNCIONARIO_READ + DO));
        acUpdIden.addConsequence(EXCEPTION, new Redirect(FUNCIONARIO_READ + DO));
        acUpdAces.addConsequence(SHOW, new Forward(JSP + FUNCIONARIO + "/update/acesso.page"));
        acUpdAces.addConsequence(ERROR, new Forward(JSP + FUNCIONARIO + "/update/acesso.page"));
        acUpdAces.addFilter(new ValidationFuncionarioAcesso());

        ActionConfig acUpdInf = new ActionConfig(FUNCIONARIO_UPDATE_INFORMACAO, FuncionarioUpdateInformacaoAction.class);
        acUpdInf.addFilter(new AuthorizationFilter(new Permission(FUNCIONARIO_MANAGER)));
        acUpdInf.addFilter(new VOFilter(Usuario.class, VO + FUNCIONARIO));
        acUpdInf.addFilter(new OVFilter(OV + FUNCIONARIO));
        acUpdInf.addConsequence(SUCCESS, new Redirect(FUNCIONARIO_READ + DO));
        acUpdIden.addConsequence(EXCEPTION, new Redirect(FUNCIONARIO_READ + DO));
        acUpdInf.addConsequence(SHOW, new Forward(JSP + FUNCIONARIO + "/update/informacoes.page"));
        acUpdInf.addConsequence(ERROR, new Forward(JSP + FUNCIONARIO + "/update/informacoes.page"));
        acUpdInf.addFilter(new ValidationFuncionarioInformacao());

        acUpdInf.addConsequence(FuncionarioAction.ATUALIZAR, new Chain(ActionRequisicao.get(am).getRequisicaoManager()));
        acUpdInf.addConsequence(FuncionarioAction.IDENTIFICACAO, new Chain(acUpdIden));

        acUpdIden.addConsequence(FuncionarioAction.ATUALIZAR, new Chain(ActionRequisicao.get(am).getRequisicaoManager()));
        acUpdIden.addConsequence(FuncionarioAction.INFORMACAO, new Chain(acUpdInf));
        acUpdIden.addConsequence(FuncionarioAction.ACESSO, new Chain(acUpdAces));

        acUpdAces.addConsequence(FuncionarioAction.ATUALIZAR, new Chain(ActionRequisicao.get(am).getRequisicaoManager()));
        acUpdAces.addConsequence(FuncionarioAction.IDENTIFICACAO, new Chain(acUpdIden));

        am.addActionConfig(acUpdInf);
        am.addActionConfig(acUpdIden);
        am.addActionConfig(acUpdAces);

        ac = new ActionConfig(FUNCIONARIO_UPDATE_INFORMACAO, FuncionarioUpdateInformacaoAction.class, DEFINIR);
        ac.addConsequence(SUCCESS, new Forward(JSP + FUNCIONARIO + "/foto/eventoFotoUpdate.jsp"));
        ac.addFilter(new FileUploadFilter());
        am.addActionConfig(ac);

        ac = new ActionConfig(FUNCIONARIO_UPDATE_INFORMACAO, FuncionarioUpdateInformacaoAction.class, PREVIEW);
        ac.addConsequence(SUCCESS, new StreamConsequence("image/jpg"));
        am.addActionConfig(ac);

        ActionConfig acCreInf = new ActionConfig(FUNCIONARIO_CREATE_INFORMACAO, FuncionarioCreateAction.class);
        acCreInf.addFilter(new AuthorizationFilter(new Permission(FUNCIONARIO_MANAGER)));
        acCreInf.addFilter(new VOFilter(Usuario.class, VO + FUNCIONARIO));
        acCreInf.addFilter(new OVFilter(OV + FUNCIONARIO));
        acCreInf.addConsequence(SUCCESS, new Redirect(FUNCIONARIO_READ + DO));
        acUpdIden.addConsequence(EXCEPTION, new Redirect(FUNCIONARIO_READ + DO));
        acCreInf.addConsequence(SHOW, new Forward(JSP + FUNCIONARIO + "/create" + CREATE_PAGE));
        acCreInf.addConsequence(ERROR, new Forward(JSP + FUNCIONARIO + "/create" + CREATE_PAGE));
        acCreInf.addFilter(new ValidationFuncionarioInformacao());

        acCreInf.addConsequence(FuncionarioAction.IDENTIFICACAO, new Chain(acUpdIden));
        am.addActionConfig(acCreInf);


        ac = new ActionConfig(FUNCIONARIO_CREATE_INFORMACAO, FuncionarioCreateAction.class, DEFINIR);
        ac.addConsequence(SUCCESS, new Forward(JSP + FUNCIONARIO + "/foto/eventoFotoCreate.jsp"));
        ac.addFilter(new FileUploadFilter());
        am.addActionConfig(ac);

        ac = new ActionConfig(FUNCIONARIO_CREATE_INFORMACAO, FuncionarioCreateAction.class, PREVIEW);
        ac.addConsequence(SUCCESS, new StreamConsequence("image/jpg"));
        am.addActionConfig(ac);

        ac = new ActionConfig(FUNCIONARIO_DELETE, FuncionarioDeleteAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(FUNCIONARIO_DELETE)));
        ac.addConsequence(SUCCESS, new Redirect(FUNCIONARIO_READ + DO));
        ac.addConsequence(ERROR, new Redirect(FUNCIONARIO_READ + DO));
        ac.addConsequence(FuncionarioAction.ATUALIZAR, new Chain(ActionRequisicao.get(am).getRequisicaoManager()));
        am.addActionConfig(ac);
    }
}