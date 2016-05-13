package br.com.rwtech.gymstyleweb.controller.action.funcionario;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.util.Filtro;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class FuncionarioReadAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        String consequence = SUCCESS;
        if (input.getValue(Filtro.CRITERIO_NOME) == null) {
            input.setValue(Filtro.CRITERIO_TIPO_USUARIO, "funcionario");
            input.setValue(Filtro.CRITERIO_ATIVO_FUNCIONARIO, "true");
            consequence = SHOW;
        }

        output.setValue(PAGINATOR, ServiceLocator.getUsuarioService().paginator((Map<String, Object>) input));
        output.setValue(Filtro.CRITERIO_NOME, input.getString(Filtro.CRITERIO_NOME));
        output.setValue(Filtro.CRITERIO_ATIVO_FUNCIONARIO, input.getString(Filtro.CRITERIO_ATIVO_FUNCIONARIO));
        setMensagem();

        return consequence;
    }
}
