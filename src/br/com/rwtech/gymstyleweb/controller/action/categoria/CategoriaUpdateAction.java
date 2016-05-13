package br.com.rwtech.gymstyleweb.controller.action.categoria;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Categoria;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class CategoriaUpdateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;
        Long id = input.getLong("id");
        if (isPost()) {
            Categoria pojo = (Categoria) input.getValue("VO" + Ac.CATEGORIA);
            ServiceLocator.getCategoriaService().update(pojo);
            session.setAttribute("mensagem", "Categoria alterada com sucesso!");
            consequence = SUCCESS;
        } else {
            Categoria pojo = ServiceLocator.getCategoriaService().readById(id);
            output.setValue("pojo", pojo);
        }
        return consequence;
    }
}