package br.com.rwtech.gymstyleweb.controller.action.avaliacaofisica;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class AvaliacaoFisicaDeleteAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        output.setValue("selecionado", "avaliacaoFisicaRead");
        String consequence = ERROR;

        if (ServiceLocator.getAvaliacaoFisicaService().delete(input.getLong("id"))) {
            session.setAttribute("mensagem", "Avaliação Física excluída com sucesso!");
            consequence = SUCCESS;
        }else{
            session.setAttribute("mensagem", "Não foi possível excluir Avaliação Física!");
        }

        return (consequence);
    }
}