package br.com.rwtech.gymstyleweb.controller.action.validation;

import br.com.rwtech.gymstyleweb.controller.action.perfilacesso.PerfilAcessoAction;
import org.mentawai.core.Action;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.RequiredFieldRule;
import org.mentawai.validation.Validator;

/**
 *
 * @author Éder Faria
 */
public class ValidationPerfilAcesso extends ValidationFilter {

    public void prepareValidator(Validator val, Action action, String innerAction) {

        if (isPost(action)) {
            val.add("perfilAcesso", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());

            if (action.getInput().getValue("todos") != null) {
                action.getOutput().setValue("todos", "checked='checked'");
            } else {
                action.getOutput().setValue("todos", "");
            }
            if (action.getInput().getValue("domingo") != null) {
                action.getOutput().setValue("domingo", "checked='checked'");
            } else {
                action.getOutput().setValue("domingo", "");
            }

            action.getOutput().setValue("todos", false);
            action.getOutput().setValue("domingo", false);
            action.getOutput().setValue("segunda", false);
            action.getOutput().setValue("terca", false);
            action.getOutput().setValue("quarta", false);
            action.getOutput().setValue("quinta", false);
            action.getOutput().setValue("sexta", false);
            action.getOutput().setValue("sabado", false);
            action.getOutput().setValue("feriado", false);

            boolean domingo = false;
            boolean segunda = false;
            boolean terca = false;
            boolean quarta = false;
            boolean quinta = false;
            boolean sexta = false;
            boolean sabado = false;
            boolean feriado = false;
            String dia = "domingo";
            if (action.getInput().getValue(dia) != null) {
                domingo = true;
                action.getOutput().setValue(dia, true);
            }
            dia = "segunda";
            if (action.getInput().getValue(dia) != null) {
                segunda = true;
                action.getOutput().setValue(dia, true);
            }
            dia = "terca";
            if (action.getInput().getValue(dia) != null) {
                terca = true;
                action.getOutput().setValue(dia, true);
            }
            dia = "quarta";
            if (action.getInput().getValue(dia) != null) {
                quarta = true;
                action.getOutput().setValue(dia, true);
            }
            dia = "quinta";
            if (action.getInput().getValue(dia) != null) {
                quinta = true;
                action.getOutput().setValue(dia, true);
            }
            dia = "sexta";
            if (action.getInput().getValue(dia) != null) {
                sexta = true;
                action.getOutput().setValue(dia, true);
            }
            dia = "sabado";
            if (action.getInput().getValue(dia) != null) {
                sabado = true;
                action.getOutput().setValue(dia, true);
            }
            dia = "feriado";
            if (action.getInput().getValue(dia) != null) {
                feriado = true;
                action.getOutput().setValue(dia, true);
            }

            if (domingo && segunda && terca && quarta && quinta && sexta && sabado && feriado) {
                action.getOutput().setValue("todos", true);
            }
        }
        PerfilAcessoAction.preload(action.getOutput());
    }
}