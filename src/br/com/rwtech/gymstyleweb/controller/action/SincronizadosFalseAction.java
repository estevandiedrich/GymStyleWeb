package br.com.rwtech.gymstyleweb.controller.action;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Eder Faria
 */
public class SincronizadosFalseAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        ServiceLocator.getUsuarioService().updateAllSincronizadoFalse();
        return SUCCESS;
    }
}
