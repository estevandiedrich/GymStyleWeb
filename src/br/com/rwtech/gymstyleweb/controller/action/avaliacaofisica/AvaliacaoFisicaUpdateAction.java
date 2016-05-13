package br.com.rwtech.gymstyleweb.controller.action.avaliacaofisica;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.AvaliacaoFisica;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class AvaliacaoFisicaUpdateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        Long id = input.getLong("id");
        output.setValue("selecionado", "avaliacaoFisicaRead");

        String consequence = SHOW;
        if (isPost()) {
            AvaliacaoFisica pojo = (AvaliacaoFisica) input.getValue("VOavaliacaoFisica");
            ServiceLocator.getAvaliacaoFisicaService().update(pojo);
            session.setAttribute("mensagem", "Avaliação Física alterada com sucesso!");

            consequence = SUCCESS;
        } else {
            AvaliacaoFisicaAction.preload(output, input);
        }
        AvaliacaoFisica pojo = ServiceLocator.getAvaliacaoFisicaService().readById(id);
        output.setValue("pojo", pojo);
        return consequence;
    }
}