package br.com.rwtech.gymstyleweb.controller.action.validation;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Pagamento;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
import br.com.rwtech.gymstylecore.model.util.Validador;
import br.com.rwtech.gymstyleweb.controller.action.pagamentos.PagamentoAction;
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
public class ValidationPagarParcela extends ValidationFilter {

    private Input input;
    private Output output;

    public void prepareValidator(Validator val, Action action, String innerAction) {

        input = action.getInput();
        output = action.getOutput();
        if (isPost(action)) {
            val.add("valorAPagar", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());

            if ((input.getValue("desconto") != null
                    && Validador.getMoney(input.getString("desconto")) > 0)
                    || (input.getValue("multa") != null
                    && Validador.getMoney(input.getString("multa")) > 0)) {

                val.add("justificativa", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
            }

            output.setValue(PagamentoAction.FORMA_DE_PAGAMENTO_MAP, ServiceLocator.getFormaPagamentoService().readList());

            Long idPlanoUsuario = (input.getLong("idPlanoUsuario"));
            Map<String, Object> mapa = ServiceLocator.getPlanoService().readPlanoUsuarioByIdPlanoUsuario(idPlanoUsuario);
            output.setValue("mapa", mapa);

            Long idParcela = (input.getLong("idParcela"));
            Pagamento pagamento = ServiceLocator.getPagamentoService().readById(idParcela);

            if (pagamento == null) {
                pagamento = new Pagamento();
            }

            if (input.getValue("ultimaParcela") != null) {
                output.setValue("ultimaParcela", true);
            }

            pagamento.setDesconto(Validador.getMoney(action.getInput().getString("desconto")));
            pagamento.setMulta(Validador.getMoney(action.getInput().getString("multa")));

            Plano plano = (Plano) mapa.get("plano");
            action.getOutput().setValue("plano", plano);

            output.setValue("select", "pagamentoRead");
            output.setValue("selectUm", "pagamento");

            String tipo = (String) input.getValue("tipoPlano");
            if (tipo.equalsIgnoreCase("Mensal")) {
                if (pagamento.getNumeroParcela() != null && pagamento.getNumeroParcela() != 0) {
                    pagamento.setValor(plano.getValorTotal());
                } else {
                    pagamento.setValor(input.getDouble("valorParcela"));
                }
            }
            output.setValue("tipoPlano", tipo);
            output.setValue("pagamento", pagamento);
            output.setValue("evento", input.getValue("evento"));
            output.setValue("imprimir", input.getValue("imprimir"));
            
            output.setValue("contas", ServiceLocator.getContaBancariaService().readList());
        }
    }
}