package br.com.rwtech.gymstyleweb.controller.action.ficha;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class FichaDeleteAction extends BaseAction {

    @Override
    public String execute() throws Exception {

        String consequence = ERROR;
        if (ServiceLocator.getFichaService().delete(input.getLong("id"))) {
            session.setAttribute("mensagem", "Ficha excluída com sucesso!");
            consequence = SUCCESS;
        } else {
            session.setAttribute("mensagem", "Não foi possível excluir Ficha!");
        }
        return consequence;
    }
}
