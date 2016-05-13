package br.com.rwtech.gymstyleweb.controller.action.usuario.util;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class VerificaLoginAjaxAction extends BaseAction {

    private static String RETORNO_VERIFICA_LOGIN = "retornoVerificaLogin";

    @Override
    public String execute() throws Exception {
        String login = input.getString("login");
        String idUsuario = input.getString("idUsuario");
        output.setValue(RETORNO_VERIFICA_LOGIN, "");
        if (login.length() >= 6) {
            String loginUsuario = input.getString("loginUsuario");
            if (!login.equalsIgnoreCase(loginUsuario)) {
                if (ServiceLocator.getUsuarioService().verificaLogin(login, idUsuario)) {
                    output.setValue(RETORNO_VERIFICA_LOGIN, "Login cadastrado anteriormente.Digite um novo!");
                }
            }
        }
        return SUCCESS;
    }
}
