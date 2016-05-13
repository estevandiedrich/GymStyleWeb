package br.com.rwtech.gymstyleweb.controller.action.validation;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Pagamento;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
import br.com.rwtech.gymstylecore.model.util.Validador;
import br.com.rwtech.gymstyleweb.controller.action.pagamentos.PagamentoAction;
import java.util.List;
import java.util.Map;
import org.mentawai.core.Action;
import org.mentawai.core.Input;
import org.mentawai.core.Output;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.RequiredFieldRule;
import org.mentawai.validation.Validator;

/**
 *
 * @author Éder Faria
 */
public class ValidationLancarParcelas extends ValidationFilter {

    private Output output;
    private Input input;

    public void prepareValidator(Validator val, Action action, String innerAction) {

        if (isPost(action)) {
            output = action.getOutput();
            input = action.getInput();

            val.add("valorAPagar", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());

            output.setValue(PagamentoAction.FORMA_DE_PAGAMENTO_MAP, ServiceLocator.getFormaPagamentoService().readList());

            Long idPlanoUsuario = (input.getLong("idPlanoUsuario"));
            Map<String, Object> map = ServiceLocator.getPlanoService().readPlanoUsuarioByIdPlanoUsuario(idPlanoUsuario);
            output.setValue("mapa", map);
            Plano plano = (Plano) map.get("plano");
            //===============================================================================================================
            // Se plano igual a null volta a tentar buscar do banco novamente o plano, devido ao erro de conecção com o banco
            //===============================================================================================================
            if (plano == null || plano.getPagamentos() == null) {
                map = ServiceLocator.getPlanoService().readPlanoUsuarioByIdPlanoUsuario(idPlanoUsuario);
                output.setValue("mapa", map);
                plano = (Plano) map.get("plano");
            }
            List<Pagamento> pagamentos = plano.getPagamentos();
            output.setValue("finalizar", false);
            for (Pagamento pojo : pagamentos) {
                if (pojo.getPagamento() == null) {//é pq foi pago
                    output.setValue("finalizar", true);
                    break;
                }
            }

            if ((input.getValue("desconto") != null
                    && Validador.getMoney(input.getString("desconto")) > 0)
                    || (input.getValue("multa") != null
                    && Validador.getMoney(input.getString("multa")) > 0)) {

                val.add("justificativa", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
            }

            Integer tamanho = (input.getInt("pagamentoSize"));
            for (int i = 1; i <= tamanho; i++) {
                output.setValue("parcelaChek" + i, false);
                if (input.getValue("parcela" + i) != null) {
                    output.setValue("parcelaChek" + i, true);
                }
            }

            if (plano.getDuracaoPlano().getDuracao().equalsIgnoreCase("mensal")) {
                //Quando mensal deverá colocar o valor do plano, pois pode tr sido alterado.
                Double valor = ServiceLocator.getPlanoService().readById(plano.getId()).getValorTotal();
                for (int i = 0; i < pagamentos.size(); i++) {
                    Pagamento pag = pagamentos.get(i);
                    if (pag.getNumeroParcela() != 0) {
                        pag.setValor(valor);
                    }
                    if (pag.getPagamento() != null) {
                        pagamentos.remove(i);
                        i--;
                    }
                }
            }
            output.setValue("pagamentos", pagamentos);
            output.setValue("qtdeParcelas", pagamentos.size());

            output.setValue("select", "pagamentoRead");
            output.setValue("selectUm", "pagamento");

//            Boolean cheks = false;
//            if (tamanho != null) {
//                for (int i = 0; i <= tamanho; i++) {
//                    if (input.getValue("parcela" + i) != null) {
//                        cheks = true;
//                    }
//                }
//            }
//
//            if (!cheks) {
//                output.setValue("cheksErrors", "Selecione ao menos uma parcela!");
//            }
//            output.setValue("cheks", cheks);
//            input.setValue("cheks", cheks);
            output.setValue("imprimir", input.getValue("imprimir"));

            output.setValue("contas", ServiceLocator.getContaBancariaService().readList());
        }
    }
}