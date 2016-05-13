package br.com.rwtech.gymstyleweb.controller.action.perfilacesso;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.Output;

/**
 *
 * @author Éder Faria
 */
public class PerfilAcessoAction {

    public static void preload(Output output) {
        output.setValue("dispositivos", ServiceLocator.getDispositivoService().readList());
    }
}