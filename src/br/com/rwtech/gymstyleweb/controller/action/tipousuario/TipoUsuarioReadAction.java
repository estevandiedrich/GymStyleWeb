package br.com.rwtech.gymstyleweb.controller.action.tipousuario;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.TipoUsuario;
import java.util.List;
import java.util.Map;
import org.mentawai.core.BaseAction;

public class TipoUsuarioReadAction  extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;

        List<TipoUsuario> usuarios = ServiceLocator.getTipoUsuarioService().readByCriteria((Map<String, Object>) input);
        output.setValue("criterioTipoUsuario", input.getString("criterioTipoUsuario"));
        output.setValue("tipoUsuarios", usuarios);
        output.setValue("select", "tipoUsuarioRead");
        setMensagem();

        consequence = SUCCESS;
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