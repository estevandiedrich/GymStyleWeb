package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstylecore.model.pojo.Dispositivo;
import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.dispositivo.BuscarDispositivoAction;
import br.com.rwtech.gymstyleweb.controller.action.dispositivo.BuscarEventosDispositivoAction;
import br.com.rwtech.gymstyleweb.controller.action.dispositivo.DispositivoCreateAction;
import br.com.rwtech.gymstyleweb.controller.action.dispositivo.DispositivoDeleteAction;
import br.com.rwtech.gymstyleweb.controller.action.dispositivo.DispositivoReadAction;
import br.com.rwtech.gymstyleweb.controller.action.dispositivo.DispositivoRequisicaoAction;
import br.com.rwtech.gymstyleweb.controller.action.dispositivo.DispositivoUpdateAction;
import br.com.rwtech.gymstyleweb.controller.action.dispositivo.LimparDispositivoAction;
import br.com.rwtech.gymstyleweb.controller.action.dispositivo.ajax.ReenviarConfigurarDispositivoAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.requisicao.ApagarUsuarioDispositivoAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationDispositivo;
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
public class ActionDispositivo extends Action {

    public static Action instance = null;
    public static Action addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionDispositivo();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionDispositivo() {
    }

    protected void createActions() {
        ActionConfig ac = null;
        ac = new ActionConfig(Ac.DISPOSITIVO_READ, DispositivoReadAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(DISPOSITIVO_READ)));
        ac.addConsequence(SHOW, new Forward(JSP + DISPOSITIVO + LIST_PAGE));
        ac.addConsequence(SUCCESS, new Forward(JSP + DISPOSITIVO + CORPO_JSP));
        am.addActionConfig(ac);

        ac = new ActionConfig(Ac.DISPOSITIVO_CREATE, DispositivoCreateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(DISPOSITIVO_MANAGER)));
        ac.addFilter(new VOFilter(Dispositivo.class, VO + DISPOSITIVO));
        ac.addFilter(new OVFilter(OV + DISPOSITIVO));
        ac.addConsequence(SUCCESS, new Redirect(DISPOSITIVO_READ + DO));
        ac.addConsequence(SHOW, new Forward(JSP + DISPOSITIVO + CREATE_PAGE));
        ac.addConsequence(ERROR, new Forward(JSP + DISPOSITIVO + CREATE_PAGE));
        ac.addFilter(new ValidationDispositivo());
        am.addActionConfig(ac);

        ActionConfig alterarEntrada = new ActionConfig(Ac.CONFIGURAR_CATRACA, DispositivoRequisicaoAction.class);
        alterarEntrada.addConsequence(SHOW, new Forward(JSP + DISPOSITIVO + MANAGER_PAGE));
        alterarEntrada.addConsequence(DispositivoRequisicaoAction.SINCRONIZANDO, new Forward(JSP + DISPOSITIVO + "/sincronizando.jsp"));
        alterarEntrada.addConsequence(SUCCESS, new Forward(JSP + DISPOSITIVO + "/sucessoSincronizacao.jsp"));
        alterarEntrada.addConsequence(ERROR, new Forward(JSP + DISPOSITIVO + "/errorSincronizacao.jsp"));
        am.addActionConfig(alterarEntrada);

        ActionConfig reenviarAlterarSentidoCatraca = new ActionConfig(Ac.REENVIAR_CONFIGURAR_CATRACA, ReenviarConfigurarDispositivoAjaxAction.class);
        reenviarAlterarSentidoCatraca.addConsequence(Ac.SINCRONIZANDO, new Forward(JSP + DISPOSITIVO + "/ajax/sincronizandoSentido.jsp"));
        reenviarAlterarSentidoCatraca.addConsequence(SUCCESS, new Forward(JSP + DISPOSITIVO + "/ajax/sucesso.jsp"));
        reenviarAlterarSentidoCatraca.addConsequence(ERROR, new Forward(JSP + DISPOSITIVO + "/ajax/error.jsp"));
        am.addActionConfig(reenviarAlterarSentidoCatraca);

        ac = new ActionConfig(Ac.DISPOSITIVO_UPDATE, DispositivoUpdateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(DISPOSITIVO_MANAGER)));
        ac.addFilter(new VOFilter(Dispositivo.class, VO + DISPOSITIVO));
        ac.addFilter(new OVFilter(OV + DISPOSITIVO));
        ac.addConsequence(SUCCESS, new Redirect(DISPOSITIVO_READ + DO));
        ac.addConsequence(SHOW, new Forward(JSP + DISPOSITIVO + UPDATE_PAGE));
        ac.addConsequence(ERROR, new Forward(JSP + DISPOSITIVO + UPDATE_PAGE));
        ac.addConsequence(SINCRONIZACAO, am.chain(alterarEntrada));
        ac.addFilter(new ValidationDispositivo());
        am.addActionConfig(ac);

        ac = new ActionConfig(Ac.DISPOSITIVO_DELETE, DispositivoDeleteAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(DISPOSITIVO_DELETE)));
        ac.addConsequence(SUCCESS, new Redirect(DISPOSITIVO_READ + DO));
        ac.addConsequence(ERROR, new Redirect(DISPOSITIVO_READ + DO));
        am.addActionConfig(ac);

        ac = new ActionConfig(Ac.BUSCAR_EVENTOS, BuscarEventosDispositivoAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(Ac.DISPOSITIVO_MANAGER)));
        ac.addConsequence(ERROR, new Redirect(DISPOSITIVO_READ + DO));
        ac.addConsequence(SHOW, new Forward(JSP + DISPOSITIVO + "/buscarEventos.page"));
        ac.addConsequence(SUCCESS, new Redirect(DISPOSITIVO_READ + DO));
        am.addActionConfig(ac);

        ac = new ActionConfig(Ac.DISPOSITIVO_BUSCAR, BuscarDispositivoAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(DISPOSITIVO_MANAGER)));
        ac.addConsequence(ERROR, new Redirect(DISPOSITIVO_READ + DO));
        ac.addConsequence(SHOW, new Forward(JSP + DISPOSITIVO + "/buscarDispositivos.page"));
        ac.addConsequence(SUCCESS, new Redirect(DISPOSITIVO_READ + DO));
        am.addActionConfig(ac);

        ac = new ActionConfig(Ac.APAGAR_USUARIO_DISPOSITIVO, ApagarUsuarioDispositivoAction.class);
        ac.addConsequence(ERROR, new Redirect(DISPOSITIVO_READ + DO));
        ac.addConsequence(SUCCESS, new Redirect(DISPOSITIVO_READ + DO));
        am.addActionConfig(ac);

        ac = new ActionConfig(Ac.LIMPAR_DISPOSITIVO, LimparDispositivoAction.class);
        ac.addConsequence(ERROR, new Redirect(DISPOSITIVO_READ + DO));
        ac.addConsequence(SUCCESS, new Redirect(DISPOSITIVO_READ + DO));
        am.addActionConfig(ac);
    }
}
