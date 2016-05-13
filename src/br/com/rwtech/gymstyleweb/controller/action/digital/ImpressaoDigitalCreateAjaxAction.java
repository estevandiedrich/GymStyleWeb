package br.com.rwtech.gymstyleweb.controller.action.digital;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Dedo;
import br.com.rwtech.gymstylecore.model.pojo.ImpressaoDigital;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class ImpressaoDigitalCreateAjaxAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        Long idUsuario = input.getLong("idUsuario");
        String digitais = input.getString("templates");
        String[] dedos = digitais.split("#");

        ServiceLocator.getImpressaoDigitalEspelhoService().deleteDigitaisUsuario(idUsuario);
        for (String aux : dedos) {
            ImpressaoDigital impressaoDigital = new ImpressaoDigital();
            String[] val = aux.split("\\|");
            if (!val[0].isEmpty() && val.length == 3) {
                impressaoDigital.setDedo(new Dedo(Integer.parseInt(val[0])));
                impressaoDigital.setPrimeiroTemplate(val[1]);
                impressaoDigital.setSegundoTemplate(val[2]);
                // se não tem o dedo cadastrado no banco cadastra
//                if (!ServiceLocator.getImpressaoDigitalService().readDigitalByIdDedo(impressaoDigital.getDedo().getId(), idUsuario)) {
                ServiceLocator.getImpressaoDigitalEspelhoService().create(impressaoDigital, idUsuario);
                //              }
            }
        }
        return SUCCESS;
    }
}
