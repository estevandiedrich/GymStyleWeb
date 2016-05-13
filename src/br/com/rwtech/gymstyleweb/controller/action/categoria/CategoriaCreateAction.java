package br.com.rwtech.gymstyleweb.controller.action.categoria;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Categoria;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class CategoriaCreateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;
        if (isPost()) {
            Categoria pojo = (Categoria) input.getValue("VO"+Ac.CATEGORIA);
            ServiceLocator.getCategoriaService().create(pojo);
            session.setAttribute("mensagem", "Categoria criada com sucesso!");
            consequence = SUCCESS;
        }
        return consequence;
    }
}