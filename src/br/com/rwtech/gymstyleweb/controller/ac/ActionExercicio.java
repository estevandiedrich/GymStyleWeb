package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstylecore.model.pojo.Exercicio;
import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.exercicio.ExercicioCreateAction;
import br.com.rwtech.gymstyleweb.controller.action.exercicio.ExercicioDeleteAction;
import br.com.rwtech.gymstyleweb.controller.action.exercicio.ExercicioReadAction;
import br.com.rwtech.gymstyleweb.controller.action.exercicio.ExercicioUpdateAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationExercicio;
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
public class ActionExercicio extends Action {

    public static Action instance = null;

    public static Action addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionExercicio();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionExercicio() {
    }

    protected void createActions() {
        ActionConfig ac = null;
        ac = new ActionConfig(EXERCICIO_READ, ExercicioReadAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(EXERCICIO_READ)));
        ac.addConsequence(SHOW, new Forward(JSP + EXERCICIO + LIST_PAGE));
        ac.addConsequence(SUCCESS, new Forward(JSP + EXERCICIO + CORPO_JSP));
        am.addActionConfig(ac);

        ac = new ActionConfig(EXERCICIO_CREATE, ExercicioCreateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(EXERCICIO_MANAGER)));
        ac.addFilter(new VOFilter(Exercicio.class, VO + EXERCICIO));
        ac.addFilter(new OVFilter(OV + EXERCICIO));
        ac.addConsequence(SUCCESS, new Redirect(EXERCICIO_READ + DO));
        ac.addConsequence(ERROR, new Forward(JSP + EXERCICIO + CREATE_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + EXERCICIO + CREATE_PAGE));
        am.addActionConfig(ac);
        ac.addFilter(new ValidationExercicio());

        ac = new ActionConfig(EXERCICIO_UPDATE, ExercicioUpdateAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(EXERCICIO_MANAGER)));
        ac.addFilter(new VOFilter(Exercicio.class, VO + EXERCICIO));
        ac.addFilter(new OVFilter(OV + EXERCICIO));
        ac.addConsequence(SUCCESS, new Redirect(EXERCICIO_READ + DO));
        ac.addConsequence(ERROR, new Forward(JSP + EXERCICIO + UPDATE_PAGE));
        ac.addConsequence(SHOW, new Forward(JSP + EXERCICIO + UPDATE_PAGE));
        am.addActionConfig(ac);
        ac.addFilter(new ValidationExercicio());

        ac = new ActionConfig(EXERCICIO_DELETE, ExercicioDeleteAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(EXERCICIO_DELETE)));
        ac.addConsequence(ERROR, new Redirect(EXERCICIO_READ + DO));
        ac.addConsequence(SUCCESS, new Redirect(EXERCICIO_READ + DO));
        am.addActionConfig(ac);
    }
}