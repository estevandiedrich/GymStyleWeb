
package br.com.rwtech.gymstyleweb.controller.action.contabancaria;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import java.util.HashMap;
import org.mentawai.core.Output;

/**
 *
 * @author Éder Faria
 */
public class ContaBancariaAction {

    public static void preload(Output output) {
        output.setValue("bancos", ServiceLocator.getBancoService().readByCriteria(new HashMap<String,Object>()));
    }
}
