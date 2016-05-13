package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstylecore.model.pojo.TipoUsuario;
import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.tipousuario.TipoUsuarioCreateAction;
import br.com.rwtech.gymstyleweb.controller.action.tipousuario.TipoUsuarioDeleteAction;
import br.com.rwtech.gymstyleweb.controller.action.tipousuario.TipoUsuarioReadAction;
import br.com.rwtech.gymstyleweb.controller.action.tipousuario.TipoUsuarioUpdateAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationTipoUsuario;
import org.mentawai.authorization.Permission;
import org.mentawai.core.ActionConfig;
import org.mentawai.core.Forward;
import org.mentawai.core.Redirect;
import org.mentawai.filter.AuthorizationFilter;
import org.mentawai.filter.VOFilter;

/**
 *
 * @author Éder Faria
 */
public class ActionTipoUsuario extends Action {

    public static Action instance = null;
    public static Action addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionTipoUsuario();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionTipoUsuario() {
    }

    protected void createActions() {
//------  TIPO USUARIO  ---------------------------------------------------------
        ActionConfig ac = null;
        String TIPO_USUARIO = "tipoUsuario";
        ac = new ActionConfig(TIPO_USUARIO_READ, TipoUsuarioReadAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(TIPO_USUARIO_READ)));
        ac.addConsequence(SUCCESS, new Forward(JSP + TIPO_USUARIO + LIST_PAGE));
        am.addActionConfig(ac);

        ac = new ActionConfig(TIPO_USUARIO_CREATE, TipoUsuarioCreateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(TIPO_USUARIO_CREATE)));
        ac.addFilter(new VOFilter(TipoUsuario.class, VO + TIPO_USUARIO));
        ac.addConsequence(SUCCESS, new Redirect(TIPO_USUARIO_READ + DO));
        ac.addConsequence(ERROR, new Forward(JSP + TIPO_USUARIO + CREATE_PAGE));
        am.addActionConfig(ac);
        ac.addFilter(new ValidationTipoUsuario());

        ac = new ActionConfig(TIPO_USUARIO_UPDATE, TipoUsuarioUpdateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(TIPO_USUARIO_UPDATE)));
        ac.addFilter(new VOFilter(TipoUsuario.class, VO + TIPO_USUARIO));
        ac.addConsequence(SUCCESS, new Redirect(TIPO_USUARIO_READ + DO));
        ac.addConsequence(ERROR, new Forward(JSP + TIPO_USUARIO + UPDATE_PAGE));
        am.addActionConfig(ac);
        ac.addFilter(new ValidationTipoUsuario());

        ac = new ActionConfig(TIPO_USUARIO_DELETE, TipoUsuarioDeleteAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(TIPO_USUARIO_DELETE)));
        ac.addConsequence(SUCCESS, new Redirect(TIPO_USUARIO_READ + DO));
        ac.addConsequence(ERROR, new Redirect(TIPO_USUARIO_READ + DO));
        am.addActionConfig(ac);
    }
}
