package br.com.rwtech.gymstyleweb.controller.action.perfilacesso;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class PerfilAcessoDeleteAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;

        if (ServiceLocator.getPerfilAcessoService().delete(input.getLong("id"))) {
            session.setAttribute("mensagem", "Perfil de Acesso excluído com sucesso!");
            consequence = SUCCESS;
        } else {
            session.setAttribute("mensagem", "Não foi possível excluir Perfil de Acesso!");
        }
        return (consequence);
    }
}
