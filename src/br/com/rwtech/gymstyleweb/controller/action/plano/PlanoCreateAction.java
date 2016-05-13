package br.com.rwtech.gymstyleweb.controller.action.plano;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Modalidade;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
import br.com.rwtech.gymstylecore.model.util.Validador;
import java.util.ArrayList;
import java.util.List;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class PlanoCreateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;

        List<Modalidade> modalidades = new ArrayList<Modalidade>();
        int tam = input.getInt("tamanho");
        for (int i = 1; i <= tam; i++) {
            Long idModalidade = input.getLong("modalidades" + i);
            if (idModalidade != null && idModalidade != -1) {
                Modalidade mod = ServiceLocator.getModalidadeService().readById(idModalidade);
                if (input.getValue("valor" + i) != null) {
                    mod.setQtdeAcessos(input.getInt("valor" + i));
                }
                modalidades.add(mod);
            }
        }

        if (isPost() && !modalidades.isEmpty()) {
            Plano pojo = (Plano) input.getValue("VOplano");
            pojo.setDescontoReal(Validador.getMoney(input.getString("descontoReal")));
            pojo.setDescontoPercentual(Validador.getInteger(input.getString("descontoPercentual")));
            pojo.setValorMatricula(Validador.getMoney(input.getString("valorMatricula")));
            pojo.setValor(Validador.getMoney(input.getString("valor")));
            pojo.setValorTotal(Validador.getMoney(input.getString("valorTotal")));
            pojo.setModalidades(modalidades);
            ServiceLocator.getPlanoService().create(pojo);
            session.setAttribute("mensagem", "Plano criado com sucesso!");
            consequence = SUCCESS;
        } else {
            if (isPost() && modalidades.isEmpty()) {
                output.setValue("modalidadesError", "Selecione ao menos uma modalidade!");
            }
            PlanoAction.preload(output);
        }
        output.setValue("formaDeDesconto2", true);
        return consequence;
    }
}