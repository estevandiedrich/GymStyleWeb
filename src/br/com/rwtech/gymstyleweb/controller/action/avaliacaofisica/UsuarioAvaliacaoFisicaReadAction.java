package br.com.rwtech.gymstyleweb.controller.action.avaliacaofisica;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.util.Filtro;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class UsuarioAvaliacaoFisicaReadAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        String consequence = SUCCESS;
        if (input.getValue(Filtro.CRITERIO_NOME) == null) {
            input.setValue("criterioAlunoOuFuncionario", "true");
            consequence = SHOW;
        }
        output.setValue(PAGINATOR, ServiceLocator.getUsuarioService().paginator((Map<String, Object>) input));
        setSelecionado(Ac.AVALIACAO_FISICA_READ);
        setMensagem();
        if (input.getValue("orderBy") != null) {
            output.setValue("order", "Decrescente!");
            output.setValue("orderBy", true);
        } else {
            output.setValue("order", "Crescente!");
        }
        return consequence;
    }
}