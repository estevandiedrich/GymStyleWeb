package br.com.rwtech.gymstyleweb.controller.action.bancobackup;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class BancoBackupDeleteAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;
        if (ServiceLocator.getDataBaseService().delete(input.getString("arquivo"))) {
            consequence = SUCCESS;
        }
        return consequence;
    }
}
