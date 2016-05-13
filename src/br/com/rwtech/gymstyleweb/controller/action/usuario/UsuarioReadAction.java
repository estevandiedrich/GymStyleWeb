package br.com.rwtech.gymstyleweb.controller.action.usuario;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.util.Filtro;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.Map;

public class UsuarioReadAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        String consequence = SUCCESS;
        if (input.getValue(Filtro.CRITERIO_NOME) == null) {
            input.setValue(Filtro.CRITERIO_TIPO_USUARIO, "aluno");
            input.setValue(Filtro.CRITERIO_ATIVO_ALUNO, "true");
            consequence = SHOW;
        }

        output.setValue(PAGINATOR, ServiceLocator.getUsuarioService().paginator((Map<String, Object>) input));
        output.setValue(Filtro.CRITERIO_NOME, input.getString(Filtro.CRITERIO_NOME));
        output.setValue(Filtro.CRITERIO_ATIVO_ALUNO, input.getString(Filtro.CRITERIO_ATIVO_ALUNO));
        setSelecionado(Ac.USUARIO_READ);
        setMensagem();

        return consequence;
    }
}
