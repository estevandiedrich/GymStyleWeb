package br.com.rwtech.gymstyleweb.controller.action.contabancaria;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.util.Filtro;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.Map;
import org.mentawai.core.Context;

/**
 *
 * @author Éder Faria
 */
public class ContaBancariaReadAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        String consequence = SUCCESS;
        if (input.getValue(Filtro.CRITERIO_ATIVO) == null) {
            input.setValue(Filtro.CRITERIO_ATIVO, "true");
            consequence = SHOW;
        }
        output.setValue(PAGINATOR, ServiceLocator.getContaBancariaService().paginator((Map<String, Object>) input));
        confereContas(this.getApplication());
        setMensagem();

        return consequence;
    }

    public static void confereContas(Context session) {
        //Se criar ou excluir, é necessário conferir as contas bancarias para mostrar o gerenciar
        Map<Long, String> contas = ServiceLocator.getContaBancariaService().readList();
        if (contas.size() > 0) {
            session.setAttribute("temContasBancaria", true);
        } else {
            session.setAttribute("temContasBancaria", false);
        }
    }
}
