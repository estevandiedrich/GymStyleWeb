package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.bancobackup.BancoBackupDeleteAction;
import br.com.rwtech.gymstyleweb.controller.action.bancobackup.BancoBackupGerarAction;
import br.com.rwtech.gymstyleweb.controller.action.bancobackup.BancoBackupReadFilesAction;
import br.com.rwtech.gymstyleweb.controller.action.bancobackup.BancoBackupRestoreAction;
import org.mentawai.authorization.Permission;
import org.mentawai.core.ActionConfig;
import org.mentawai.core.Forward;
import org.mentawai.core.Redirect;
import org.mentawai.filter.AuthorizationFilter;

/**
 *
 * @author Éder Faria
 */
public class ActionBancoBackup extends Action {

    public static Action instance = null;
    public static Action addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionBancoBackup();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionBancoBackup() {
    }

    protected void createActions() {
        ActionConfig ac = null;
        String banco = "bancobackup";
        ac = new ActionConfig(BANCO_BACKUP_READ, BancoBackupReadFilesAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(BANCO_BACKUP_READ)));
        ac.addConsequence(ERROR, new Forward(JSP + banco + LIST_PAGE));
        ac.addConsequence(SUCCESS, new Forward(JSP + banco + LIST_PAGE));
        am.addActionConfig(ac);

        ac = new ActionConfig(BANCO_BACKUP_GERAR, BancoBackupGerarAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(BANCO_BACKUP_GERAR)));
        ac.addConsequence(SUCCESS, new Redirect(BANCO_BACKUP_READ + DO));
        ac.addConsequence(ERROR, new Redirect(BANCO_BACKUP_READ + DO));
        am.addActionConfig(ac);

        ac = new ActionConfig(BANCO_BACKUP_RESTAURAR, BancoBackupRestoreAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(BANCO_BACKUP_RESTAURAR)));
        ac.addConsequence(SUCCESS, new Redirect(BANCO_BACKUP_READ + DO));
        ac.addConsequence(ERROR, new Redirect(BANCO_BACKUP_READ + DO));
        am.addActionConfig(ac);

        ac = new ActionConfig(BANCO_BACKUP_DELETE, BancoBackupDeleteAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(BANCO_BACKUP_DELETE)));
        ac.addConsequence(ERROR, new Redirect(BANCO_BACKUP_READ + DO));
        ac.addConsequence(SUCCESS, new Redirect(BANCO_BACKUP_READ + DO));
        am.addActionConfig(ac);
    }
}