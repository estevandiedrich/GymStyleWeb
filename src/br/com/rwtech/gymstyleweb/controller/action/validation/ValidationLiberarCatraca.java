package br.com.rwtech.gymstyleweb.controller.action.validation;

import br.com.rwtech.gymstyleweb.controller.action.liberar.LiberarDispositivoAction;
import org.mentawai.core.Action;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.RequiredFieldRule;
import org.mentawai.validation.Validator;

/**
 *
 * @author Éder Faria
 */
public class ValidationLiberarCatraca extends ValidationFilter {

    @Override
    public void prepareValidator(Validator val, Action action, String innerAction) {

        if (isPost(action)) {
            val.add("justificativa", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
            val.add("dispositivo", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
            LiberarDispositivoAction.preload(action.getOutput());
        }
    }
}
