package br.com.rwtech.gymstyleweb.controller.action.avaliacaofisica;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.AvaliacaoFisica;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class AvaliacaoFisicaReadAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        String consequence = LIST;
//        if (input.getValue("criterioNome") == null) {
//            consequence = SHOW;
//        }
        Long id = input.getLong("id");
        if (id != null && id > 0) {
            input.setValue("criterioAluno", id.toString());
            List<AvaliacaoFisica> lista = ServiceLocator.getAvaliacaoFisicaService().readByCriteria((Map<String, Object>) input);
            output.setValue("list", lista);
            output.setValue("selecionado", "avaliacaoFisicaRead");
            setMensagem();
            AvaliacaoFisicaAction.preload(output, input);
            consequence = SUCCESS;
        }
        return consequence;
    }
}