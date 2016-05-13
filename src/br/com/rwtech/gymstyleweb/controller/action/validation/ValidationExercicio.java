package br.com.rwtech.gymstyleweb.controller.action.validation;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstyleweb.controller.action.exercicio.ExercicioAction;
import org.mentawai.core.*;
import org.mentawai.filter.*;
import org.mentawai.rule.*;
import org.mentawai.validation.Validator;

public class ValidationExercicio extends ValidationFilter {

    private Output output = null;
    private Input input = null;
    private static String CAMPO_GRUPO_MUSCULAR = "grupoMuscular";

    @Override
    public void prepareValidator(Validator val, Action action, String innerAction) {
        output = action.getOutput();
        input = action.getInput();
        if (isPost(action)) {
            Long id = null;

            if (input.getValue("id") != null) {
                id = input.getLong("id");
            }
            String idBanco = null;
            if (id == null) {
                CAMPO_GRUPO_MUSCULAR = "grupoMuscular";
                val.add(CAMPO_GRUPO_MUSCULAR, new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
            } else {
                CAMPO_GRUPO_MUSCULAR = "grupoMuscularExer";
                val.add(CAMPO_GRUPO_MUSCULAR, new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
            }
            idBanco = input.getString(CAMPO_GRUPO_MUSCULAR);

            val.add("exercicio", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
            val.add("exercicio", new StringRule(2, 25), ValidationMessage.getCampoMinimo2Maximo25Caracteres());
            if (ServiceLocator.getExercicioService().readExists(input.getString("exercicio"), id, idBanco)) {
                val.add("exercicio", new StringRule(1, 1), ValidationMessage.getCadastroExistente());
            }
        }
        ExercicioAction.preload(output);
    }
}
