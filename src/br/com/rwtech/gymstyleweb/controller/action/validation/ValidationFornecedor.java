package br.com.rwtech.gymstyleweb.controller.action.validation;

import br.com.rwtech.gymstyleweb.controller.action.fornecedor.FornecedorAction;
import org.mentawai.core.Action;
import org.mentawai.core.Input;
import org.mentawai.core.Output;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.StringRule;
import org.mentawai.validation.Validator;

/**
 *
 * @author Éder Faria
 */
public class ValidationFornecedor extends ValidationFilter {

    private static String NOME = "nome";
    private static String CIDADE = "cidade";
    private static String ESTADO = "estado";
    private static String TELEFONE = "telefone";
    //private static String ID = "id";
    //private Input input;
    private Output output;

    @Override
    public void prepareValidator(Validator val, Action action, String innerAction) {

        if (isPost(action)) {
            //input = action.getInput();
            output = action.getOutput();
            val.requiredFields(ValidationMessage.getCampoObrigatorio(), NOME, CIDADE, ESTADO, TELEFONE);
            val.add(NOME, new StringRule(6, 52), ValidationMessage.getCampoMinimo6Maximo52Caracteres());
            FornecedorAction.preload(output);
        }
    }
}
