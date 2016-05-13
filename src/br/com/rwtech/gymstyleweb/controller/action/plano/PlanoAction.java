package br.com.rwtech.gymstyleweb.controller.action.plano;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Modalidade;
import java.util.HashMap;
import java.util.List;
import org.mentawai.core.Output;

/**
 *
 * @author Éder Faria
 */
public class PlanoAction {

    public static void preload(Output output) {
        List<Modalidade> lista = ServiceLocator.getModalidadeService().readByCriteria(new HashMap<String, Object>());
        output.setValue("lista", lista);
        for (int i = 0; i < lista.size(); i++) {
            int j = i + 1;
            Modalidade mod = lista.get(i);
            //esse ajuste é para conseguir printar o ultimo selecionado no dia dentro do input
            String valorFinal = "valorFinal" + j;
            String chek = "chek";
            output.setValue(1 + chek + j, false);
            output.setValue(2 + chek + j, false);
            output.setValue(3 + chek + j, false);
            output.setValue(4 + chek + j, false);
            output.setValue(5 + chek + j, false);
            output.setValue(6 + chek + j, false);
            output.setValue(7 + chek + j, false);
            if (mod.getValor7() != null && mod.getValor7() > 0) {
                output.setValue(valorFinal, mod.getValor7());
                output.setValue(7 + chek + j, true);
            } else if (mod.getValor6() != null && mod.getValor6() > 0) {
                output.setValue(valorFinal, mod.getValor6());
                output.setValue(6 + chek + j, true);
            } else if (mod.getValor5() != null && mod.getValor5() > 0) {
                output.setValue(valorFinal, mod.getValor5());
                output.setValue(5 + chek + j, true);
            } else if (mod.getValor4() != null && mod.getValor4() > 0) {
                output.setValue(valorFinal, mod.getValor4());
                output.setValue(4 + chek + j, true);
            } else if (mod.getValor3() != null && mod.getValor3() > 0) {
                output.setValue(valorFinal, mod.getValor3());
                output.setValue(3 + chek + j, true);
            } else if (mod.getValor2() != null && mod.getValor2() > 0) {
                output.setValue(valorFinal, mod.getValor2());
                output.setValue(2 + chek + j, true);
            } else if (mod.getValor1() != null && mod.getValor1() > 0) {
                output.setValue(valorFinal, mod.getValor1());
                output.setValue(1 + chek + j, true);
            }
        }
    }
}