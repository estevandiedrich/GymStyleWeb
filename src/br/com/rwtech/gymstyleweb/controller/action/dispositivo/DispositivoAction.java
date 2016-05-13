package br.com.rwtech.gymstyleweb.controller.action.dispositivo;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.mentawai.core.Output;

/**
 *
 * @author Éder
 */
public class DispositivoAction {

    public static void preload(Output output) {
        output.setValue("modos", ServiceLocator.getModoOperacaoService().readList());
        output.setValue("entradas", getEntradas());
        output.setValue("imprimes", getImprimir());
        output.setValue("modosAcessos", getModosAcessos());
    }

    public static Map getEntradas() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(0, "Entrada Sentido Anti-Horário");
        map.put(1, "Entrada Sentido Horário");
        return map;
    }

    public static Map getImprimir() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(0, "Não");
        map.put(1, "Sim");
        return map;
    }

    public static Map getModosAcessos() {
        Map<Integer, String> mapa = new LinkedHashMap<Integer, String>();
        int i = 1;
        mapa.put(i++, "Entrada Controlada / Saída Controlada");
        mapa.put(i++, "Entrada Controlada / Saída Liberada");
        mapa.put(i++, "Entrada Controlada / Saída Bloqueada");

        mapa.put(i++, "Entrada Bloqueada / Saída Controlada");
        mapa.put(i++, "Entrada Bloqueada / Saída Liberada");
        mapa.put(i++, "Entrada Bloqueada / Saída Bloqueada");

        mapa.put(i++, "Entrada Liberada / Saída Controlada");
        mapa.put(i++, "Entrada Liberada / Saída Liberada");
        mapa.put(i++, "Entrada Liberada / Saída Bloqueada");
        return mapa;
    }
}