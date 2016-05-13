package br.com.rwtech.gymstyleweb.controller.action.exercicio;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.util.Filtro;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class ExercicioReadAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        String consequence = SUCCESS;
        if (input.getValue(Filtro.CRITERIO_NOME) == null) {
            consequence = SHOW;
        }
        output.setValue(PAGINATOR, ServiceLocator.getExercicioService().paginator((Map<String, Object>) input));
        output.setValue("grupos", ServiceLocator.getGrupoMuscularService().readList());
        setMensagem();

        return consequence;
    }
}
