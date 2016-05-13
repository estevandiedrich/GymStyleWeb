package br.com.rwtech.gymstyleweb.controller.action.pagamentos;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.Filtro;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import br.com.rwtech.gymstyleweb.controller.action.SessaoAtr;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class PagamentosReadAction extends ReadAction {

    @Override
    public String execute() throws Exception {

        String consequence = SUCCESS;
        //busca no banco todos os usuários que possui planos
        Long idCriterioPlano = null;
        if (input.getValue(Filtro.CRITERIO_NOME) == null) {
            consequence = SHOW;
        } else {
            //System.out.println("Emtrou - " + new Timestamp(System.currentTimeMillis()) + " - Criterio Nome:" + input.getString("criterioNome"));
        }
        if (input.getValue("criterioPlano") != null) {
            idCriterioPlano = input.getLong("criterioPlano");
        }

        Map<String, Object> map = ServiceLocator.getUsuarioPlanoService().paginatorUsuPlaAbertos((Map<String, Object>) input);
        output.setValue(PAGINATOR, map);
        List<Usuario> usuarios = (List<Usuario>) map.get("list");
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usu = usuarios.get(i);
            if (usu.getPlanos() != null && !usu.getPlanos().isEmpty()) {
                Boolean contem = false;
                for (Plano plano : usu.getPlanos()) {
                    if (!plano.getCancelado()) {//plano diferente de cancelado
                        //if (!plano.getFinalizado()) {//plano em aberto
                        if (idCriterioPlano == null || idCriterioPlano == -1 || plano.getId().equals(idCriterioPlano)) {
                            output.setValue("usuarioParcela" + usu.getId(), "<a id='pagamentos' title='Mais Recente' href='verParcelaPlanoUsuario.do?idPlanoUsuario=" + plano.getIdPlanoUsuario() + "'>Mais Recente</a>|");
                            output.setValue("usuario" + usu.getId(), "<a id='pagamentos' title='Parcelas' href='verPlanoUsuario.do?idPlanoUsuario=" + plano.getIdPlanoUsuario() + "'>Outras</a>");
                            output.setValue("usuarioPlano" + usu.getId(), plano.getPlano());
                            contem = true;
                        }
                        break;
                        //}
                    }
                }
            }
        }

        setSelecionado(Ac.PAGAMENTO_READ);
        session.setAttribute(SessaoAtr.ID_FLUXO_CAIXA, ServiceLocator.getFluxoCaixaService().idCaixaAberto());
        output.setValue("permissao", ServiceLocator.getPermissaoService().readByName(Ac.MANAGER_CAIXA));

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