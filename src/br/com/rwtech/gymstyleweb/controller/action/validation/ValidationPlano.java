package br.com.rwtech.gymstyleweb.controller.action.validation;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Modalidade;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
import br.com.rwtech.gymstylecore.model.util.Validador;
import br.com.rwtech.gymstyleweb.controller.action.plano.PlanoAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class ValidationPlano extends ValidationFilter {

    private Input input = null;
    private Output output = null;

    public void prepareValidator(Validator val, Action action, String innerAction) {
        input = action.getInput();
        output = action.getOutput();
        if (isPost(action)) {
            val.add("plano", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
            val.add("plano", new StringRule(3, 70), ValidationMessage.getInformeMinimo3());
//            val.add("enderecoIp", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
//            String plano = input.getString("plano");
//            if (!Validador.isValidName(plano)) {
//                val.add("plano", new StringRule(1,1), ValidationMessage.getInformeNomeValido());
//            }

            validaMoeda("valorMatricula", val);
            validaMoeda("valorTotal", val);


            //ou se checa todos ou verifica quais estao selecionados
            int tamanho = input.getInt("tamanho");
            if (action.getInput().getValue("todos") != null) {
                output.setValue("todos", true);
            } else {
                for (int i = 1; i <= tamanho; i++) {
                    if (input.getValue("modalidades" + i) != null) {
                        output.setValue("checados" + i, true);
                    } else {
                        output.setValue("checados" + i, false);
                    }
                }
            }
            output.setValue("lista", ServiceLocator.getModalidadeService().readByCriteria(new HashMap<String, Object>()));

            for (int i = 1; i <= tamanho; i++) {
                int num = input.getInt("valor" + i);
                switch (num) {
                    case 1:
                        output.setValue(1 + "chek" + i, true);
                        break;
                    case 2:
                        output.setValue(2 + "chek" + i, true);
                        break;
                    case 3:
                        output.setValue(3 + "chek" + i, true);
                        break;
                    case 4:
                        output.setValue(4 + "chek" + i, true);
                        break;
                    case 5:
                        output.setValue(5 + "chek" + i, true);
                        break;
                    case 6:
                        output.setValue(6 + "chek" + i, true);
                        break;
                    case 7:
                        output.setValue(7 + "chek" + i, true);
                        break;
                }
            }
            if (input.getValue("formasDeDesconto") != null) {
                if (input.getString("formasDeDesconto").equalsIgnoreCase("R$")) {
                    output.setValue("formaDeDesconto1", true);
                } else {
                    output.setValue("formaDeDesconto2", true);
                }
            }

            if (input.getValue("id") != null && !input.getString("id").isEmpty()) {
                Long id = input.getLong("id");
                Plano pojo = ServiceLocator.getPlanoService().readById(id);
                if (pojo.getModalidades() != null && pojo.getModalidades().size() > 0) {
                    List<Modalidade> modalidadesOff = new ArrayList<Modalidade>();
                    for (int j = 0; j < pojo.getModalidades().size(); j++) {
                        Modalidade mod = pojo.getModalidades().get(j);
                        if (!mod.getAtivo()) {
                            modalidadesOff.add(mod);
                        }
                    }
                    output.setValue("modalidadesOff", modalidadesOff);
                }

            }

        } else {
            PlanoAction.preload(action.getOutput());
        }
    }

    public void validaMoeda(String campo, Validator val) {
        if (input.getValue(campo) != null && !input.getString(campo).isEmpty()) {
            if (!Validador.isValidMoney(input.getString(campo))) {
                val.add(campo, new StringRule(1, 1), ValidationMessage.getImage("Informe Valor Válido"));
            }
        }
    }
}