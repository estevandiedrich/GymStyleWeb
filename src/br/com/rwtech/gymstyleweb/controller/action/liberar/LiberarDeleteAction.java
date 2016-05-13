package br.com.rwtech.gymstyleweb.controller.action.liberar;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class LiberarDeleteAction extends BaseAction {

    @Override
    public String execute() throws Exception {

        String consequence = ERROR;

        if (ServiceLocator.getLiberarService().delete(input.getLong("id"))) {
            session.setAttribute("mensagem", "Liberação excluída com sucesso!");
            consequence = SUCCESS;
        }else{
            session.setAttribute("mensagem", "Não foi possível excluir Liberação da catraca!");
        }
        return (consequence);
    }
}
