package br.com.rwtech.gymstyleweb.controller.action.validation;

import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstylecore.model.util.Validador;
import br.com.rwtech.gymstyleweb.controller.action.avaliacaofisica.AvaliacaoFisicaAction;
import java.util.Calendar;
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
public class ValidationAvaliacaoFisica extends ValidationFilter {

    private static String PESO = "peso";
    private static String ALTURA = "altura";
    private static String IMC = "imc";
    private static String BRACO_DIREITO = "bracoDireito";
    private static String BRACO_ESQUERDO = "bracoEsquerdo";
    private static String COXA_DIREITA = "coxaDireita";
    private static String COXA_ESQUERDA = "coxaEsquerda";
    private static String PANTURRILHA_DIREITA = "panturrilhaDireita";
    private static String PANTURRILHA_ESQUERDA = "panturrilhaEsquerda";
    private static String TORAX = "torax";
    private static String QUADRIL = "quadril";
    private static String CINTURA = "cintura";
    private static String ABDOMEN = "abdomen";
    private static String SUBESCAPULAR = "subescapular";
    private static String TRICIPITAL = "tricipital";
    private static String PEITORAL = "peitoral";
    private static String ABDOMINAL = "abdominal";
    private static String SUPRA_ILIACA = "supraIliaca";
    private static String COXA = "coxa";
    //private static String "panturrilha";
    private static String AXILAR_MEDIA = "axilarMedia";
    private static String GORDURA_ATUAL = "gorduraAtual";
    private static String GORDURA_IDEAL = "gorduraIdeal";
    private static String MASSA_MAGRA = "massaMagra";
    private static String MASSA_GORDA = "massaGorda";
    private static String DESCRICAO = "descricao";
    private Input input;
    private Output output;

    public void prepareValidator(Validator val, Action action, String innerAction) {

        if (isPost(action)) {
            input = action.getInput();
            output = action.getOutput();

            val.requiredFields(ValidationMessage.getImageCampoObrigatorio(), PESO, ALTURA, BRACO_DIREITO, BRACO_ESQUERDO, COXA_DIREITA,
                    COXA_ESQUERDA, PANTURRILHA_DIREITA, PANTURRILHA_ESQUERDA, TORAX, QUADRIL, CINTURA, ABDOMEN, SUBESCAPULAR, TRICIPITAL,
                    PEITORAL, ABDOMINAL, SUPRA_ILIACA, COXA, AXILAR_MEDIA, GORDURA_ATUAL, GORDURA_IDEAL, MASSA_MAGRA, MASSA_GORDA, DESCRICAO);
            //val.add("panturrilha", new RequiredFieldRule(), ValidationMessage.getImageCampoObrigatorio());

            validaMoeda(PANTURRILHA_DIREITA, val);
            validaMoeda(PANTURRILHA_ESQUERDA, val);
            validaMoeda(TORAX, val);
            validaMoeda(QUADRIL, val);
            validaMoeda(CINTURA, val);
            validaMoeda(ABDOMEN, val);
            validaMoeda(SUBESCAPULAR, val);
            validaMoeda(TRICIPITAL, val);
            validaMoeda(PEITORAL, val);
            validaMoeda(ABDOMINAL, val);
            validaMoeda(SUPRA_ILIACA, val);
            validaMoeda(COXA, val);
            validaMoeda(AXILAR_MEDIA, val);
            validaMoeda(PESO, val);
            validaMoeda(ALTURA, val);

            val.add(IMC, new RequiredFieldRule(), "Pressione aplicar para calcular IMC e Composição Corporal!");
            //validaMoeda(IMC, val);
            validaMoeda(BRACO_DIREITO, val);
            validaMoeda(BRACO_ESQUERDO, val);
            validaMoeda(COXA_DIREITA, val);
            validaMoeda(COXA_ESQUERDA, val);

            val.add("dataProximaAvaliacao", new RequiredFieldRule(), ValidationMessage.getImageCampoObrigatorio());
            if (input.getValue("dataProximaAvaliacao") != null) {
                String data = input.getString("dataProximaAvaliacao");
                if (!Validador.isValidData(data)) {
                    val.add("dataProximaAvaliacao", new StringRule(1, 1), ValidationMessage.getDataInvalida());
                } else {
                    Calendar dataAmanha = Calendar.getInstance();
                    dataAmanha.add(Calendar.DAY_OF_MONTH, 1);
                    Calendar dataAvaliacao = CalendarUtil.setDateCalendar(data);
                    if (dataAvaliacao.before(dataAmanha)) {
                        val.add("dataProximaAvaliacao", new StringRule(1, 1), ValidationMessage.getInformeDataSuperiorDataAtual());
                    }
                }
            }
            output.setValue("validado", "1");// para ver se ja foi dado um submit ou nao
            output.setValue("imcValue", input.getValue("imcValue"));// para ver se ja foi dado um submit ou nao

            AvaliacaoFisicaAction.preload(action.getOutput(), action.getInput());
        }
    }

    public void validaMoeda(String campo, Validator val) {
        if (input.getValue(campo) != null && !input.getString(campo).isEmpty()) {
            if (!Validador.isValidMoney(input.getString(campo))) {
                val.add(campo, new StringRule(1, 1), ValidationMessage.getImageObrigatorio("Informe valor válido"));
            }
        }
    }
}
