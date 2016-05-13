/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rwtech.gymstyleweb.controller.action.contabancaria.registros;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Software1
 */
public class RegistroContaBancariaDeleteAction extends BaseAction {

    @Override
    public String execute() throws Exception {

        String consequence = ERROR;
        ServiceLocator.getRegistroCaixaService().deleteByRegContaBancaria(input.getLong("id"));
        if (ServiceLocator.getRegistroContaBancariaService().delete(input.getLong("id"))) {
            session.setAttribute("mensagem", "Registro de Conta Bancária excluído com sucesso!");
            consequence = SUCCESS;
        } else {
            session.setAttribute("mensagem", "Não foi possivel excluir!");
        }
        return consequence;
    }
}