package br.com.rwtech.gymstyleweb.controller.action.validation;

import br.com.rwtech.gymstyleweb.controller.action.dispositivo.DispositivoAction;
import org.mentawai.core.Action;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.RequiredFieldRule;
import org.mentawai.validation.Validator;

/**
 *
 * @author Éder Faria
 */
public class ValidationDispositivo extends ValidationFilter {

    @Override
    public void prepareValidator(Validator val, Action action, String innerAction) {

        if (isPost(action)) {
            val.add("dispositivo", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
            DispositivoAction.preload(action.getOutput());
        }
    }
}
