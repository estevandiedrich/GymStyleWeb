package br.com.rwtech.gymstyleweb.controller.action.perfilacesso;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.PerfilAcesso;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class PerfilAcessoReadAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        String consequence = SUCCESS;
        List<PerfilAcesso> lista = ServiceLocator.getPerfilAcessoService().readByCriteria((Map<String, Object>) input);
        output.setValue("criterioNome", input.getString("criterioNome"));
        output.setValue("list", lista);
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
