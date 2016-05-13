package br.com.rwtech.gymstyleweb.controller.action.validation.usuario;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstyleweb.controller.action.usuario.update.UsuarioUpdatePlanoAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationMessage;
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
public class ValidationUsuarioPlano extends ValidationFilter {

    private String SELECT_PLANO = UsuarioUpdatePlanoAction.SELECT_PLANO;
    private String SELECT_PERIODO = UsuarioUpdatePlanoAction.SELECT_PERIODO;
    private String TOLERANCIA = UsuarioUpdatePlanoAction.TOLERANCIA;
    private String VINCULAR = UsuarioUpdatePlanoAction.VINCULAR;
    private String DIA_VENCIMENTO = UsuarioUpdatePlanoAction.DIA_VENCIMENTO;
    private Output output = null;
    private Input input = null;

    public void prepareValidator(Validator val, Action action, String innerAction) {
        output = action.getOutput();
        input = action.getInput();
        if (isPost(action) && input.getValue(SELECT_PLANO) != null) {
            Long idUsuario = input.getLong("id");

            if (input.getValue(TOLERANCIA) != null && !input.getString(TOLERANCIA).isEmpty()) {
                //Se existe o campo tolerancia, seta se true para aparecer novamentente durante as validacoes
                output.setValue(VINCULAR, true);
            }
            //se selecionou um plano é pq vai vincular um plano ao usuário

            //if (input.getValue(SELECT) != null && !input.getString(SELECT).isEmpty()) {
            output.setValue(VINCULAR, true);//para poder inserir a jsp vincularPlano
            String msg = "";

            if (!msg.isEmpty()) {
                val.add("post", new RequiredFieldRule(), "Informe  " + msg + " para vincular plano;");
            }
            //existe um if na tela conferindo se ja existe
            val.requiredFields(ValidationMessage.getCampoObrigatorio(), SELECT_PLANO);
            val.requiredFields(ValidationMessage.getImageCampoObrigatorio(), DIA_VENCIMENTO, TOLERANCIA, SELECT_PERIODO);
            val.add("tamanhoLista", new RequiredFieldRule(), ValidationMessage.getInformeAoMenosUmaParcela());

            output.setValue("id", idUsuario);
            output.setValue(VINCULAR, input.getValue(VINCULAR));

            UsuarioUpdatePlanoAction.preload(output, input);

            output.setValue("planos", ServiceLocator.getPlanoService().readList());
            output.setValue("periodos", ServiceLocator.getDuracaoPlanoService().readList());

            output.setValue("formasDeDesconto", input.getValue("formasDeDesconto"));
        }
    }
}