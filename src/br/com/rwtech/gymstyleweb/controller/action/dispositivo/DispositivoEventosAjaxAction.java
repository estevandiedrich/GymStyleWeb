package br.com.rwtech.gymstyleweb.controller.action.dispositivo;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Resposta;
import br.com.rwtech.gymstylecore.model.util.Contador;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class DispositivoEventosAjaxAction extends BaseAction {

    private static int CONT = 40;
    
    @Override
    public String execute() throws Exception {
        String consequence = SHOW;
        //na primeira vez passa se direto pq o idRequisicao esta no out put
        if (input.getValue("idRequisicao") != null && input.getInt("idRequisicao") != -1) {
            consequence = ERROR;
            Long idRequisicao = input.getLong("idRequisicao");
            output.setValue("idRequisicao", idRequisicao);
            int i = 0;
            while (i < CONT) {
                Resposta pojo = ServiceLocator.getRespostaService().readByDestino(idRequisicao);
                if (pojo != null) {
                    if (pojo.getMensagemErro() == null || pojo.getMensagemErro().isEmpty()) {
                        consequence = SUCCESS;
                    } else {
                        output.setValue("error", pojo.getMensagemErro());
                    }
                    break;
                } else {
                    Contador contador = Contador.getInstance();
                    contador.run();
                }
                i++;
            }
        }
        return consequence;
    }
}