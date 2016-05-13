package br.com.rwtech.gymstyleweb.controller.action.categoria;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class CategoriaDeleteAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;
        if (ServiceLocator.getCategoriaService().delete(input.getLong("id"))) {
            session.setAttribute("mensagem", "Categoria excluída com sucesso!");
            consequence = SUCCESS;
        }
        return consequence;
    }
}