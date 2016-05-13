package br.com.rwtech.gymstyleweb.controller.action.requisicao;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.List;

/**
 *
 * @author Éder Faria
 */
public class RequisicaoAtualizarTodos extends ReadAction {

    @Override
    public String execute() throws Exception {
        //input.setValue("criterioUsuarioAtivo", true);

        String consequence = SUCCESS;
        List<Usuario> usuarios = ServiceLocator.getUsuarioPlanoService().readUsuPlaAtivo();
        output.setValue("list", usuarios);
        if (input.getValue("orderBy") != null) {
            output.setValue("order", "Decrescente!");
            output.setValue("orderBy", true);
        } else {
            output.setValue("order", "Crescente!");
        }
        output.setValue("selecionado", "atualizacoesRead");
        setMensagem();
        return consequence;
    }
}
