package br.com.rwtech.gymstyleweb.controller.action.bancobackup;

import br.com.rwtech.gymstylecore.model.ConnectionDB;
import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.FileBackup;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class BancoBackupReadFilesAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        Map mapa = ServiceLocator.getDataBaseService().readFiles();

        setMensagem();
        List<FileBackup> lista = new ArrayList<FileBackup>();
        //inverte a lista
        for (File file : new ArrayList<File>(mapa.values())) {
            lista.add(0, new FileBackup(file));
        }

        output.setValue("lista", lista);
        output.setValue("versao", ConnectionDB.VERSAO);
        return SUCCESS;
    }
}
