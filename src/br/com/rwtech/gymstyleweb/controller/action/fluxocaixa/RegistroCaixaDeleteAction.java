package br.com.rwtech.gymstyleweb.controller.action.fluxocaixa;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author ÉderFaria
 */
public class RegistroCaixaDeleteAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;
        ServiceLocator.getRegistroContaBancariaService().deleteByRegCaixa(input.getLong("id"));
        if (ServiceLocator.getRegistroCaixaService().delete(input.getLong("id"))) {
            session.setAttribute("mensagem", "Registro de Caixa excluído com sucesso!");
            consequence = SUCCESS;
        } else {
            session.setAttribute("mensagem", "Não foi possivel excluir!");
        }
        return consequence;
    }
}