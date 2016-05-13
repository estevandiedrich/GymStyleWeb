package br.com.rwtech.gymstyleweb.controller.action.validation;

import br.com.rwtech.gymstyleweb.controller.action.configuracaoboleto.ConfigurarBoletoAction;
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
public class ValidationConfiguracaoBoleto extends ValidationFilter {

    private Output output = null;
    private Input input = null;

    public void prepareValidator(Validator val, Action action, String innerAction) {
        output = action.getOutput();
        input = action.getInput();
        if (isPost(action)) {
            val.requiredFields(ValidationMessage.getCampoObrigatorio(), "cedenteRazaoSocial", "cedenteCnpj", "bancoSelect",
                    "bancoAgencia", "bancoNumero", "bancoCarteira", "tipoDocumento");

            val.add("cedenteRazaoSocial", new StringRule(2, 42), ValidationMessage.getCampoMaximo42Caracteres());
            val.add("cedenteCnpj", new StringRule(18, 18), ValidationMessage.getCampoMaximo42Caracteres());

            //val.add("bancoAgenciaVariacao", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
            //val.add("bancoNumeroDigito", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());

        }
        ConfigurarBoletoAction.preload(output, input, true);

        output.setValue("bancoCarteira", input.getValue("bancoCarteira"));
    }
}
