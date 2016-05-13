package br.com.rwtech.gymstyleweb.controller.action.contabancaria;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class ContaBancariaDeleteAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;
        ServiceLocator.getContaBancariaService().delete(input.getLong("id"));
        if (ServiceLocator.getContaBancariaService().disabled(input.getLong("id"))) {
            session.setAttribute("mensagem", "Conta Bancária desabilitada com sucesso!");
            consequence = SUCCESS;
        }
        return consequence;
    }
}