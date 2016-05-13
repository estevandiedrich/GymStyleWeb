package br.com.rwtech.gymstyleweb.controller.action.validation;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.Action;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.RequiredFieldRule;
import org.mentawai.validation.Validator;

/**
 *
 * @author Éder Faria
 */
public class ValidationVincularPlano extends ValidationFilter {

    public void prepareValidator(Validator val, Action action, String innerAction) {
        if (isPost(action)) {
            val.requiredFields(ValidationMessage.getCampoObrigatorio(), "planoSelect");
            val.requiredFields(ValidationMessage.getImageCampoObrigatorio(), "periodoSelect", "diaVencimento", "tolerancia");
            val.add("tamanhoLista", new RequiredFieldRule(), ValidationMessage.getInformeAoMenosUmaParcela());
        }

        action.getOutput().setValue("planos", ServiceLocator.getPlanoService().readList());
        action.getOutput().setValue("periodos", ServiceLocator.getDuracaoPlanoService().readList());
        action.getOutput().setValue("formasDeDesconto", action.getInput().getValue("formasDeDesconto"));

    }
}
