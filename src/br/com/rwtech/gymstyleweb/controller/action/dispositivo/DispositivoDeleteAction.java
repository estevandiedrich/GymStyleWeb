package br.com.rwtech.gymstyleweb.controller.action.dispositivo;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class DispositivoDeleteAction extends BaseAction {

    @Override
    public String execute() throws Exception {

        String consequence = ERROR;

        if (ServiceLocator.getDispositivoService().disabled(input.getLong("id"))) {
            session.setAttribute("mensagem", "Dispositivo excluído com sucesso!");
            consequence = SUCCESS;
        } else {
            session.setAttribute("mensagem", "Não foi possível excluir Dispositivo!");
        }
        return (consequence);
    }
}
