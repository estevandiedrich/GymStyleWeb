package br.com.rwtech.gymstyleweb.controller.action.ficha;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Ficha;
import br.com.rwtech.gymstylecore.model.pojo.Treino;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.List;

/**
 *
 * @author Éder Faria
 */
public class UsuarioFichasReadAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;
        Long idUsuario = input.getLong("idUsuario");
        if (idUsuario == null || idUsuario == -1) {
            idUsuario = (Long) session.getAttribute("idUsuarioFicha");
            session.removeAttribute("idUsuarioFicha");
        }
        if (idUsuario != null && idUsuario != -1) {
            List<Ficha> lista = ServiceLocator.getFichaService().readFichasByIdUsuario(idUsuario);
            output.setValue("list", lista);
            for (Ficha ficha : lista) {
                if (ficha.getAtiva()) {
                    output.setValue("fichaValue", ficha.getId());
                }
                for (Treino treino : ficha.getTreinos()) {
                    if (treino.getTreinaDomingo()) {
                        output.setValue("treinaDomingo" + ficha.getId(), true);
                    }
                    if (treino.getTreinaSegunda()) {
                        output.setValue("treinaSegunda" + ficha.getId(), true);
                    }
                    if (treino.getTreinaTerca()) {
                        output.setValue("treinaTerca" + ficha.getId(), true);
                    }
                    if (treino.getTreinaQuarta()) {
                        output.setValue("treinaQuarta" + ficha.getId(), true);
                    }
                    if (treino.getTreinaQuinta()) {
                        output.setValue("treinaQuinta" + ficha.getId(), true);
                    }
                    if (treino.getTreinaSexta()) {
                        output.setValue("treinaSexta" + ficha.getId(), true);
                    }
                    if (treino.getTreinaSabado()) {
                        output.setValue("treinaSabado" + ficha.getId(), true);
                    }
                }
            }
            output.setValue("usuario", ServiceLocator.getUsuarioService().readById(idUsuario));
            setMensagem();
            consequence = SUCCESS;
        } else {
            consequence = LIST;
        }
        output.setValue("idUsuario", idUsuario);
        return consequence;
    }
}
