package br.com.rwtech.gymstyleweb.controller.action.validation;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.util.Validador;
import br.com.rwtech.gymstyleweb.controller.action.produto.ProdutoAction;
import org.mentawai.core.Action;
import org.mentawai.core.Input;
import org.mentawai.core.Output;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.IntegerRule;
import org.mentawai.rule.StringRule;
import org.mentawai.validation.Validator;

/**
 *
 * @author Éder Faria
 */
public class ValidationProduto extends ValidationFilter {

    private String NOME = "nome";
    private String ID = "id";
    private String CODIGO = "codigo";
    private String CODIGO_EXISTENTE = "codigoExistente";
    private String VALOR_CUSTO = "valorCusto";
    private String VALOR_VENDA = "valorVenda";
    private String ESTOQUE_MINIMO = "estoqueMinimo";
    private String ESTOQUE_ATUAL = "estoqueAtual";
    private String CATEGORIA_FK = "categoriaFk";
    private Output output = null;
    private Input input = null;

    public void prepareValidator(Validator val, Action action, String innerAction) {
        output = action.getOutput();
        input = action.getInput();
        if (isPost(action)) {
            //val.requiredFields(ValidationMessage.getCampoObrigatorio(), NOME, CODIGO);//, CATEGORIA_FK);
            val.requiredFields(ValidationMessage.getImageCampoObrigatorio(), NOME, CODIGO, VALOR_CUSTO, VALOR_VENDA, ESTOQUE_MINIMO, ESTOQUE_ATUAL);
            val.add(NOME, new StringRule(3, 52), ValidationMessage.getCampoMinimo6Maximo52Caracteres());
            if (ServiceLocator.getProdutoService().exist(input.getString(CODIGO), input.getLong(ID))) {
                val.add(CODIGO, new IntegerRule(-1, -1), ValidationMessage.getImageCampoObrigatorio());
                val.add(CODIGO_EXISTENTE, new IntegerRule(-1, -1), ValidationMessage.getCodigoExistente());
            }
            if (input.getValue(CODIGO) != null && input.getInt(CODIGO) <= 0) {
                val.add(CODIGO, new IntegerRule(-1, -1), ValidationMessage.getImageCampoObrigatorio());
                val.add(CODIGO_EXISTENTE, new IntegerRule(-1, -1), "Informe código maior que zero");
            }
            if (input.getValue(VALOR_CUSTO) != null && input.getValue(VALOR_VENDA) != null) {
                Double valorCusto = Validador.getMoney(input.getString(VALOR_CUSTO));
                Double valorVenda = Validador.getMoney(input.getString(VALOR_VENDA));
                if (valorCusto > valorVenda) {
                    val.add("valorError", new IntegerRule(-1, -1), ValidationMessage.getMensagem("Informe valor de custo inferior ao valor de Venda"));
                }
            }
            if (input.getValue(ESTOQUE_MINIMO) != null && input.getValue(ESTOQUE_ATUAL) != null) {
                if (input.getInt(ESTOQUE_MINIMO) > input.getInt(ESTOQUE_ATUAL)) {
                    val.add("valorError", new IntegerRule(-1, -1), ValidationMessage.getMensagem("Informe estoque mínimo inferior ao estoque atual"));
                }
            }
        }
        ProdutoAction.preload(output);
        output.setValue("alerta", input.getValue("alerta"));
    }
}
