package br.com.rwtech.gymstyleweb.controller.action.empresa;

import br.com.rwtech.gymstyleweb.controller.action.usuario.UsuarioAction;
import org.mentawai.core.Output;

/**
 *
 * @author Éder Faria
 */
public class EmpresaAction {

    public static void preload(Output output) {
        output.setValue("estados", UsuarioAction.getEstados());
    }
}
