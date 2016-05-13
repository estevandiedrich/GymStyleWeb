package br.com.rwtech.gymstyleweb.controller.action.validation;

import br.com.rwtech.gymstylecore.model.util.Validador;
import org.mentawai.core.Action;
import org.mentawai.core.Input;
import org.mentawai.core.Output;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.RequiredFieldRule;
import org.mentawai.rule.StringRule;
import org.mentawai.validation.Validator;

/**
 *
 * @author Éder Faria
 */
public class ValidationConfiguracao extends ValidationFilter {

    private Input input;
    private Output output;

    public void prepareValidator(Validator val, Action action, String innerAction) {

        if (isPost(action)) {
            input = action.getInput();
            output = action.getOutput();
            val.add("valor", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
            if (input.getValue("valor") != null && !input.getString("valor").isEmpty()) {
                String valor = input.getString("valor");
                String campo = input.getString("campo");

                if (campo.equalsIgnoreCase("rodapeRecibo")) {
                    // Ja foi validado fora val.add("valor", new RequiredFieldRule(), ValidationMessage.getImageCampoObrigatorio());
                } else if (campo.equalsIgnoreCase("tolerancia") || campo.equalsIgnoreCase("diaVencimento")) {
                    if (!Validador.isValidMoney(valor)) {
                        val.add("valor", new StringRule(1, 1), ValidationMessage.getInformeValorNumerico());
                    } else {
                        int num = Integer.parseInt(valor);
                        if (num < 0 || num > 31) {
                            val.add("valor", new StringRule(1, 1), ValidationMessage.getInformeValorEntre0a30());
                        }
                    }
                }

            }
            output.setValue("descricao", input.getString("descricao"));
            output.setValue("campo", input.getString("campo"));
            output.setValue("valor", input.getString("valor"));
        }
    }
}
