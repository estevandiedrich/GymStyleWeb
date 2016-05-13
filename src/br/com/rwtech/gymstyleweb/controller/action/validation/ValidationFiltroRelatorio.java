package br.com.rwtech.gymstyleweb.controller.action.validation;

import org.mentawai.core.Action;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.StringRule;
import org.mentawai.validation.Validator;

/**
 *
 * @author Éder Faria
 */
public class ValidationFiltroRelatorio extends ValidationFilter {

    private String CRITERIO_INICIO = "criterioInicio";
    private String CRITERIO_FIM = "criterioFim";

    @Override
    public void prepareValidator(Validator val, Action action, String innerAction) {

        if (isPost(action)) {
            val.requiredFields(ValidationMessage.getImageCampoObrigatorio(), CRITERIO_INICIO, CRITERIO_FIM);
            val.add(CRITERIO_INICIO, new StringRule(10, 10), ValidationMessage.getPreenchaTodoCampo());
            val.add(CRITERIO_FIM, new StringRule(10, 10), ValidationMessage.getPreenchaTodoCampo());
        }
    }
}
