package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstylecore.model.dao.PermissaoDAO;
import br.com.rwtech.gymstyleweb.controller.GroupManager;

/**
 *
 * @author Éder Faria
 */
public class TesteAction {

    public static void main(String[] args) {
        new PermissaoDAO().drop();
        GroupManager.getInstance();//.addAdmin();
    }    
}
