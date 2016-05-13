package br.com.rwtech.gymstyleweb.controller.action.log;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstylecore.model.util.Filtro;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class LogReadAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        String consequence = SUCCESS;
        if (input.getValue(Filtro.CRITERIO_INICIO) == null) {
            consequence = SHOW;
            input.setValue(Filtro.CRITERIO_INICIO, CalendarUtil.getDataCurrente());
            input.setValue(Filtro.CRITERIO_FIM, CalendarUtil.getDataCurrente());
            input.setValue("orderBy", "true");
        }

        output.setValue(PAGINATOR, ServiceLocator.getLogService().paginator((Map<String, Object>) input));
        setMensagem();

        output.setValue("usuarios", ServiceLocator.getUsuarioService().readList());
        output.setValue("tipos", getTipos());
        return consequence;
    }

    public Map getTipos() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put(LogAction.ALTEROU, LogAction.ALTEROU);
        map.put(LogAction.ATUALIZOU, LogAction.ATUALIZOU);
        map.put(LogAction.CANCELOU, LogAction.CANCELOU);
        map.put(LogAction.CONSULTOU, LogAction.CONSULTOU);
        map.put(LogAction.CRIOU, LogAction.CRIOU);
        map.put(LogAction.EXCLUIU, LogAction.EXCLUIU);
        map.put(LogAction.LIBEROU, LogAction.LIBEROU);
        map.put(LogAction.PAGAMENTO, LogAction.PAGAMENTO);
        map.put(LogAction.VINCULAR, LogAction.VINCULAR);
        map.put(LogAction.VISUALIZOU, LogAction.VISUALIZOU);
        map.put(LogAction.ENTRADA, LogAction.ENTRADA);
        map.put(LogAction.SAIDA, LogAction.SAIDA);
        return map;
    }
}
