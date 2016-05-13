package br.com.rwtech.gymstyleweb.controller.action;

import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class ReadAction extends BaseAction {

    protected String SELECIONADO = "selecionado";
    protected String PAGINATOR = "paginator";

//    protected String CONSEQUENCE = SUCCESS;
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

    public void setSelecionado(String selecionado) {
        output.setValue(SELECIONADO, selecionado);
    }
}
