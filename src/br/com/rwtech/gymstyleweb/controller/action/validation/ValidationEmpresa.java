package br.com.rwtech.gymstyleweb.controller.action.validation;

import br.com.rwtech.gymstyleweb.controller.action.empresa.EmpresaAction;
import org.mentawai.core.Action;
import org.mentawai.core.Output;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.RequiredFieldRule;
import org.mentawai.rule.StringRule;
import org.mentawai.validation.Validator;

/**
 *
 * @author Éder Faria
 */
public class ValidationEmpresa extends ValidationFilter {

    private Output output = null;

    @Override
    public void prepareValidator(Validator val, Action action, String innerAction) {
        output = action.getOutput();
        if (isPost(action)) {
            val.requiredFields(ValidationMessage.getCampoObrigatorio(), "razaoSocial", "cnpj");
            val.add("razaoSocial", new StringRule(2, 42), ValidationMessage.getCampoMaximo42Caracteres());
            val.add("cnpj", new StringRule(18, 18), ValidationMessage.getCampoMaximo42Caracteres());

            val.add("nomeFantasia", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
            //val.add("cidade", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
        }
        EmpresaAction.preload(output);
    }
}
