package br.com.rwtech.gymstyleweb.controller.action.evento;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstylecore.model.util.Filtro;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Éder Faria
 */
public class EventoReadAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        String consequence = SUCCESS;
        if (input.getValue(Filtro.CRITERIO_INICIO) == null) {
            consequence = SHOW;// é pq acabou de entrar no listar entao retorna SHOW. Depois retornara SUCCESS para o ajax
            input.setValue(Filtro.CRITERIO_INICIO, CalendarUtil.getDataCurrente());
            input.setValue(Filtro.CRITERIO_FIM, CalendarUtil.getDataCurrente());
            input.setValue("orderBy", "true");
        }
        output.setValue(PAGINATOR, ServiceLocator.getEventoService().paginator((Map<String, Object>) input));
        output.setValue("catracas", ServiceLocator.getDispositivoService().readList());
        output.setValue("descricoes", getdescricoes());
        if (input.getValue("orderBy") != null) {
            output.setValue("order", "Decrescente!");
            output.setValue("orderBy", true);
        } else {
            output.setValue("order", "Crescente!");
        }
        return consequence;
    }

    public Map getdescricoes() {
        Map<Integer, String> map = new TreeMap<Integer, String>();
        map.put(0, "Passagem Liberada na Catraca");
        //map.put(1, "Bloqueado");
        map.put(2, "Liberado");
        map.put(3, "Fora da Faixa de Horário");
        //map.put(4, "Falta de Pagamento");
//        map.put(5, "Dupla Entrada / Saída");
        map.put(6, "Fora do Período de Acesso");
//        map.put(7, "Cartão Inválido"); //Nao tem criterio
//        map.put(8, "Senha Inválida");
        map.put(9, "Impressão Digital Inválida");
        map.put(10, "Obstrução da Catraca");
        map.put(11, "Violação da Catraca");
//        map.put(12, "Dupla Passagem"); //Nao tem criterio
//        map.put(13, "Acesso Realizado");
        //map.put(14, "Acesso Bloqueado");
//        map.put(15, "Acesso Bloqueado por Tempo Excedido");
        map.put(16, "Limite de Dias Excedido");
//        map.put(17, "Sentido Bloqueado");
        return map;
    }
}
