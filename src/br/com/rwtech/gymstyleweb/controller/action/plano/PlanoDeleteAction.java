package br.com.rwtech.gymstyleweb.controller.action.plano;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class PlanoDeleteAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;

        if (ServiceLocator.getPlanoService().disabled(input.getLong("id"))) {
            session.setAttribute("mensagem", "Plano excluído com sucesso!");
            consequence = SUCCESS;
        } else {
            session.setAttribute("mensagem", "Não foi possível excluir Plano!");
        }
        return (consequence);
    }
}
