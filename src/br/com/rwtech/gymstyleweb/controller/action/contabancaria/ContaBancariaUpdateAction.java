package br.com.rwtech.gymstyleweb.controller.action.contabancaria;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.ContaBancaria;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class ContaBancariaUpdateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;
        Long id = input.getLong("id");
        if (isPost()) {
            ContaBancaria pojo = (ContaBancaria) input.getValue("VOcontabancaria");
            pojo.setBanco(ServiceLocator.getBancoService().readById(input.getLong("bancoId")));
            ServiceLocator.getContaBancariaService().update(pojo);
            session.setAttribute("mensagem", "Conta Bancária alterada com sucesso!");
            consequence = SUCCESS;
        } else {
            ContaBancariaAction.preload(output);
            ContaBancaria pojo = ServiceLocator.getContaBancariaService().readById(id);
            output.setValue("pojo", pojo);
            output.setValue("bancoNome", pojo.getBanco().getNome());
            output.setValue("bancoId", pojo.getBanco().getId());
        }
        return consequence;
    }
}