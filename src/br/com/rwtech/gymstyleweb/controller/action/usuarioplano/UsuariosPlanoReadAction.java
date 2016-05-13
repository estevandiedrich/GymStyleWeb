package br.com.rwtech.gymstyleweb.controller.action.usuarioplano;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.Filtro;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.List;
import java.util.Map;

/**
 * @author Éder Faria
 */
public class UsuariosPlanoReadAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        String consequence = SUCCESS;
        if (input.getValue(Filtro.CRITERIO_NOME) == null) {
            input.setValue("criterioAtivoAluno", "true");
            consequence = SHOW;
        }

        Map<String, Object> map = null;
        if (input.getValue("criterio") != null && !input.getString("criterio").isEmpty()) {
            String criterio = input.getString("criterio");
            if (criterio.equalsIgnoreCase("planos")) {
                map = ServiceLocator.getUsuarioPlanoService().paginatorUsuPla((Map<String, Object>) input);
            } else {
                input.setValue("criterioPendencias", true);
                map = ServiceLocator.getUsuarioService().paginator((Map<String, Object>) input);
            }
            output.setValue("criterio", input.getValue("criterio"));
        } else {
            map = ServiceLocator.getUsuarioService().paginator((Map<String, Object>) input);
        }
        output.setValue(PAGINATOR, map);
        List<Usuario> usuarios = (List<Usuario>) map.get("list");
        for (Usuario usu : usuarios) {
            String pendencias = "";
            pendencias = "Pendências:";
            boolean temPend = false;
            if (usu.getCpf() == null || usu.getCpf().isEmpty()) {
                pendencias += " CPF;";
                temPend = true;
            }
            if ((usu.getCartaoProximidade() == null || usu.getCartaoProximidade().isEmpty())
                    && (usu.getImpressoesDigitais() == null || usu.getImpressoesDigitais().isEmpty())) {
                pendencias += " Cartão Proximidade ou Digitais;";
                temPend = true;
            }
            pendencias += " Selecione para verificar!";
            output.setValue("usuarioVincular" + usu.getId(), "<a class='error' style='width: 60px' title='" + pendencias + "' href='" + Ac.USUARIO_UPDATE_INFORMACAO + ".do?id=" + usu.getId() + "'>Pendências</a>");
            if (usu.getPlanos() != null && !usu.getPlanos().isEmpty()) {
                for (Plano plano : usu.getPlanos()) {
                    if (!temPend) {
                        output.setValue("usuarioVincular" + usu.getId(), "<a id='vincular' title='Vincular'  href='vincularPlano.do?id=" + usu.getId() + "'>Vincular Plano</a>");
                    }
                    output.setValue("usuarioPlano" + usu.getId(), "<a id='plano' title='Ver Planos'  href='usuarioPlanosRead.do?id=" + usu.getId() + "'>Ver Planos</a>");
                    if (!plano.getFinalizado() && !plano.getCancelado()) {
                        //plano em aberto
                        if (!temPend) {
                            output.setValue("usuarioVincular" + usu.getId(), "");//apaga o link
                        }
                        break;
                    }
                }
            } else {
                if (!temPend) {
                    output.setValue("usuarioVincular" + usu.getId(), "<a id='vincular' title='Vincular' href='vincularPlano.do?id=" + usu.getId() + "'>Vincular Plano</a>");
                }
            }
        }
        output.setValue("selecionado", "usuarioPlanoRead");
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
