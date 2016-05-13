package br.com.rwtech.gymstyleweb.controller.action.exercicio;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Exercicio;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class ExercicioCreateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;
        if (isPost()) {
            Exercicio pojo = (Exercicio) input.getValue("VOexercicio");
            pojo.setGrupoMuscular(ServiceLocator.getGrupoMuscularService().readById(input.getLong("grupoMuscularExer")));
            ServiceLocator.getExercicioService().create(pojo);
            session.setAttribute("mensagem", "Exercício criado com sucesso!");
            consequence = SUCCESS;
        } else {
            ExercicioAction.preload(output);
        }
        return consequence;
    }
}