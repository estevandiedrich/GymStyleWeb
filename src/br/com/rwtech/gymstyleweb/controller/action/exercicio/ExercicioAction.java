package br.com.rwtech.gymstyleweb.controller.action.exercicio;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.Output;

/**
 *
 * @author Éder Faria
 */
public class ExercicioAction {

    public static void preload(Output output) {
        output.setValue("grupos", ServiceLocator.getGrupoMuscularService().readList());
    }
}
