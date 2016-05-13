package br.com.rwtech.gymstyleweb.controller.action.configuracao;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Configuracao;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.List;

/**
 *
 * @author Éder Faria
 */
public class ConfiguracaoReadAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        String consequence = SUCCESS;
        List<Configuracao> lista = ServiceLocator.getConfiguracaoService().readByCriteria(null);
        output.setValue("list", lista);
        setMensagem();

        return consequence;
    }
}
