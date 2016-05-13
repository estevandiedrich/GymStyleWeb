package br.com.rwtech.gymstyleweb.controller.action.dispositivo;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Dispositivo;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class DispositivoCreateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;

        if (isPost()) {
            Dispositivo pojo = (Dispositivo) input.getValue("VOdispositivo");
            ServiceLocator.getDispositivoService().create(pojo);
            session.setAttribute("mensagem", "Dispositivo criado com sucesso!");
            consequence = SUCCESS;
        } else {
            DispositivoAction.preload(output);
        }
        return consequence;
    }
}
