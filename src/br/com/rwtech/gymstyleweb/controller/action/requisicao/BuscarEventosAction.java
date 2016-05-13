package br.com.rwtech.gymstyleweb.controller.action.requisicao;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Requisicao;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.pojo.tipos.TipoRequisicaoResposta;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class BuscarEventosAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        Long id = input.getLong("id");
        Requisicao requisicao = new Requisicao();
        requisicao.setDestino(id);
        requisicao.setOperador((Usuario) getUserSession());
        requisicao.setStatus(Boolean.FALSE);
        requisicao.setTipo(TipoRequisicaoResposta.VERIFICAR_EVENTOS_OFFLINE);
        ServiceLocator.getRequisicaoService().create(requisicao);
        output.setValue("idRequisicao", requisicao.getId());
        addMessage("Buscando eventos!");

        output.setValue("selecionado", "atualizacoesRead");

        return SUCCESS;
    }
}
