package br.com.rwtech.gymstyleweb.controller.action.validation;

import br.com.rwtech.gymstyleweb.controller.action.contabancaria.ContaBancariaAction;
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
public class ValidationContaBancaria extends ValidationFilter {

    private Output output;
    private Input input;
    private static String CAMPO_BANCO_ID = "bancoId";
    private static String BANCO_NOME = "bancoNome";
    private static String CAMPO_AGENCIA = "agencia";
    private static String CAMPO_NUMERO_CONTA = "numeroConta";
    private static String CAMPO_TITULAR = "titular";

    public void prepareValidator(Validator val, Action action, String innerAction) {
        output = action.getOutput();
        input = action.getInput();

        if (isPost(action)) {
            val.requiredFields(ValidationMessage.getCampoObrigatorio(), CAMPO_AGENCIA, CAMPO_NUMERO_CONTA, CAMPO_TITULAR, CAMPO_BANCO_ID);

            val.add(CAMPO_AGENCIA, new StringRule(3, 30), ValidationMessage.getInformeMinimo3());
            val.add(CAMPO_NUMERO_CONTA, new StringRule(3, 30), ValidationMessage.getInformeMinimo3());
            val.add(CAMPO_TITULAR, new StringRule(6, 52), ValidationMessage.getCampoMinimo6Maximo52Caracteres());

            output.setValue(BANCO_NOME, input.getValue(BANCO_NOME));
            output.setValue(CAMPO_BANCO_ID, input.getValue(CAMPO_BANCO_ID));
            ContaBancariaAction.preload(output);
        }
    }
}