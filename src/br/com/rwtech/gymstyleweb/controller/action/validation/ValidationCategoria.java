package br.com.rwtech.gymstyleweb.controller.action.validation;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.Action;
import org.mentawai.core.Input;
import org.mentawai.core.Output;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.IntegerRule;
import org.mentawai.rule.StringRule;
import org.mentawai.validation.Validator;

/**
 *
 * @author Éder Faria
 */
public class ValidationCategoria extends ValidationFilter {

    private static String NOME = "nome";
    private static String ID = "id";
    private Input input;
    private Output output;

    public void prepareValidator(Validator val, Action action, String innerAction) {

        if (isPost(action)) {
            input = action.getInput();
            output = action.getOutput();
            val.requiredFields(ValidationMessage.getCampoObrigatorio(), NOME);

            val.add(NOME, new StringRule(6, 52), ValidationMessage.getCampoMinimo6Maximo52Caracteres());

            if (ServiceLocator.getCategoriaService().exist(input.getString(NOME), input.getLong(ID))) {
                val.add(NOME, new IntegerRule(1, 1), ValidationMessage.getCadastroExistente());
            }
        }
    }
}
