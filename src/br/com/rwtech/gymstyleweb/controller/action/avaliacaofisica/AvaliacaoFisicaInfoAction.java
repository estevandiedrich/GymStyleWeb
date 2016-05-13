package br.com.rwtech.gymstyleweb.controller.action.avaliacaofisica;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.AvaliacaoFisica;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class AvaliacaoFisicaInfoAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;
        Long idUsuario = input.getLong("id");
        Long idAvaliacao = input.getLong("idAvaliacao");

        if (idUsuario == null || idUsuario < 0) {
            consequence = LIST;
        } else {
            Usuario usuario = ServiceLocator.getUsuarioService().readById(idUsuario);
            AvaliacaoFisica avaliacao = ServiceLocator.getAvaliacaoFisicaService().readById(idAvaliacao);
            output.setValue("usuario", usuario);
            output.setValue("avaliacao", avaliacao);

            output.setValue("selecionado", "avaliacaoFisicaRead");

            AvaliacaoFisicaAction.preload(output, input);
            setMensagem();

            consequence = SUCCESS;
        }
        return consequence;
    }

    public void setMensagem() {
        String msg = "";
        if (session.getAttribute("mensagem") != null) {
            msg = session.getAttribute("mensagem").toString();
            session.setAttribute("mensagem", "");
        }
        if (!msg.equalsIgnoreCase("")) {
            addMessage(msg);
        }
    }
}