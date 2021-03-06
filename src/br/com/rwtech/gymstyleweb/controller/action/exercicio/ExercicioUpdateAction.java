package br.com.rwtech.gymstyleweb.controller.action.exercicio;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Exercicio;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class ExercicioUpdateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;
        Long id = input.getLong("id");
        if (isPost()) {
            Exercicio pojo = (Exercicio) input.getValue("VOexercicio");
            pojo.setGrupoMuscular(ServiceLocator.getGrupoMuscularService().readById(input.getLong("grupoMuscularExer")));
            ServiceLocator.getExercicioService().update(pojo);
            session.setAttribute("mensagem", "Exercício alterado com sucesso!");
            consequence = SUCCESS;
        } else {
            ExercicioAction.preload(output);
            Exercicio pojo = ServiceLocator.getExercicioService().readById(id);
            output.setValue("pojo", pojo);
            output.setValue("grupoMuscularExer", pojo.getGrupoMuscular().getId());
        }
        return consequence;
    }
}