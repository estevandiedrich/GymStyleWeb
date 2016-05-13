package br.com.rwtech.gymstyleweb.controller.action.exercicio;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class ExercicioDeleteAction extends BaseAction {

    @Override
    public String execute() throws Exception {

        String consequence = ERROR;
        if (ServiceLocator.getExercicioService().delete(input.getLong("id"))) {
            session.setAttribute("mensagem", "Exercício excluído com sucesso!");
            consequence = SUCCESS;
        } else {
            session.setAttribute("mensagem", "Exercício vinculado a ficha. Não é possivel excluir!");
        }
        return consequence;
    }
}