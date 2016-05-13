package br.com.rwtech.gymstyleweb.controller.action.dispositivo;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Dispositivo;
import br.com.rwtech.gymstylecore.model.pojo.tipos.ModoAcesso;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class DispositivoUpdateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        Long id = input.getLong("id");

        String consequence = SHOW;
        if (isPost()) {
            Integer entrada = input.getInt("entrada");
            Integer modoAcessoValor = input.getInt("modoAcessoValor");
            Boolean imprimir = input.getBoolean("imprimir");

            Dispositivo pojo = (Dispositivo) input.getValue("VOdispositivo");
            pojo.setEntradaDirEsq(entrada);//seta como estava e se o nucleo responder é que será alterado no banco
            pojo.setImprime(imprimir);//seta como estava e se o nucleo responder é que será alterado no banco
            //pojo.setModoAcesso(ModoAcesso.getTipo(input.getInt("modoAcessoPojo")));
            pojo.setModoAcesso(ModoAcesso.getTipo(modoAcessoValor));

            consequence = Ac.SINCRONIZACAO;//tentará sincronizar sempre

            ServiceLocator.getDispositivoService().update(pojo);
            //session.setAttribute("mensagem", "Dispositivo alterado com sucesso!");
        } else {
            DispositivoAction.preload(output);
            Dispositivo pojo = ServiceLocator.getDispositivoService().readById(id);
            output.setValue("pojo", pojo);
            output.setValue("entrada", pojo.getEntradaDirEsq());
            output.setValue("imprimir", pojo.getImprime());
            output.setValue("modo", pojo.getModoOperacao().getId());
            int valor = pojo.getModoAcesso().getValor();
            output.setValue("modoAcessoPojo", valor);
            output.setValue("modoAcessoValor", valor);
        }
        return consequence;
    }
}