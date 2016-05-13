package br.com.rwtech.gymstyleweb.controller.action.pagamentos;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.Filtro;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class PagamentosUltimoPlanoAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        String consequence = SUCCESS;

        if (input.getValue(Filtro.CRITERIO_NOME) == null) {
            consequence = SHOW;
        }
        Map<String, Object> map = ServiceLocator.getUsuarioPlanoService().paginatorUsuPlaAbeQuitados((Map<String, Object>) input);
        output.setValue(PAGINATOR, map);
        List<Usuario> usuarios = (List<Usuario>) map.get("list");
        int i = 0;
        while (i < usuarios.size()) {
            Usuario usu = usuarios.get(i);
            if (usu.getPlanos() != null && !usu.getPlanos().isEmpty()) {
                Plano plano = usu.getPlanos().get(0);//ultimo plano
                output.setValue("usuarioPlano" + usu.getId(), plano.getPlano());
                output.setValue("usuarioPlanoVer" + usu.getId(), "<a id='plano' title='Ver Plano' href='pagamentosPlanoUsuario.do?idPlanoUsuario=" + plano.getIdPlanoUsuario() + "'>Ver</a>");
            }
            i++;
        }
        setSelecionado(Ac.USUARIOS_PLANO_READ);
        if (input.getValue("orderBy") != null) {
            output.setValue("order", "Decrescente!");
            output.setValue("orderBy", true);
        } else {
            output.setValue("order", "Crescente!");
        }
        setMensagem();

        return consequence;
    }
}