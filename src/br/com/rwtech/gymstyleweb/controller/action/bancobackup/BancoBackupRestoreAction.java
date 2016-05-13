package br.com.rwtech.gymstyleweb.controller.action.bancobackup;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.VerificaStatusBancoService;
import br.com.rwtech.gymstylecore.model.dao.Restaurador;
import br.com.rwtech.gymstyleweb.controller.GroupManager;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class BancoBackupRestoreAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;
        String arquivo = input.getString("arquivo");
        //if (arquivo.contains(ConnectionDB.VERSAO)) {
            if (ServiceLocator.getDataBaseService().restore(arquivo)) {
                consequence = SUCCESS;
                session.setAttribute("mensagem", "Backup restaurado com sucesso!");
            } else {
                session.setAttribute("mensagem", "Não foi possível restaurar!");
            }
        //} else {
        //    session.setAttribute("mensagem", "Versão diferente, não é possível restaurar!");
        //}
        VerificaStatusBancoService.analisar();
        GroupManager.getInstance().refresh();
        GroupManager.getInstance().updateFalse();
        Restaurador.RESTAURANDO = false;
        return consequence;
    }
}
