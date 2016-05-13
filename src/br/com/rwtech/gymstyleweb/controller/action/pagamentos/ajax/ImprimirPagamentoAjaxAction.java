package br.com.rwtech.gymstyleweb.controller.action.pagamentos.ajax;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Requisicao;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.pojo.tipos.TipoRequisicaoResposta;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class ImprimirPagamentoAjaxAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;
        Long id = input.getLong("id");
        Requisicao req = new Requisicao();
        req.setParametro(id);
        req.setStatus(Boolean.FALSE);
        req.setTipo(TipoRequisicaoResposta.IMPRIMIR_PAMENTO);
        req.setOperador((Usuario) getUserSession());
        req.setDestino(input.getLong("idDispositivo"));
        ServiceLocator.getRequisicaoService().create(req);
        consequence = SUCCESS;
        return consequence;
    }
}
