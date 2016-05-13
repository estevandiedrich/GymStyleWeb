/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rwtech.gymstyleweb.controller.action.contabancaria;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.ContaBancaria;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Software1
 */
public class ContaBancariaCreateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;
        if (isPost()) {
            ContaBancaria pojo = (ContaBancaria) input.getValue("VOcontabancaria");
            pojo.setBanco(ServiceLocator.getBancoService().readById(input.getLong("bancoId")));
            ServiceLocator.getContaBancariaService().create(pojo);
            session.setAttribute("mensagem", "Conta Bancária criada com sucesso!");
            consequence = SUCCESS;
        } else {
            ContaBancariaAction.preload(output);
        }
        return consequence;
    }
}