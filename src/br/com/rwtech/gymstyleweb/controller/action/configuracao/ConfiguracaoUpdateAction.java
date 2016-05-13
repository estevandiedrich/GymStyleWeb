package br.com.rwtech.gymstyleweb.controller.action.configuracao;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Configuracao;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class ConfiguracaoUpdateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        Long id = input.getLong("id");
        String consequence = SHOW;
        if (isPost()) {
            Configuracao pojo = (Configuracao) input.getValue("VOconfiguracao");
            ServiceLocator.getConfiguracaoService().update(pojo);
            session.setAttribute("mensagem", "Configuração alterada com sucesso!");
            consequence = SUCCESS;
        } else {
            ConfiguracaoAction.preload(output);
            Configuracao pojo = ServiceLocator.getConfiguracaoService().readById(id);
            output.setValue("pojo", pojo);
        }
        return consequence;
    }
}