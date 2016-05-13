package br.com.rwtech.gymstyleweb.controller.action.tipousuario;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

public class TipoUsuarioDeleteAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;

        if (ServiceLocator.getTipoUsuarioService().delete(input.getLong("id"))) {
            session.setAttribute("mensagem", "Tipo de Usuário excluído com sucesso!");
            consequence = SUCCESS;
        }else{
            session.setAttribute("mensagem", "Não foi possível excluir Tipo de Usuário!");
        }


        return (consequence);
    }
}