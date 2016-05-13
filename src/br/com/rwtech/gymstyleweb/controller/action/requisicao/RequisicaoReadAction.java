package br.com.rwtech.gymstyleweb.controller.action.requisicao;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.util.Filtro;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.sql.Timestamp;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class RequisicaoReadAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        String consequence = SUCCESS;
        Boolean consultaAluno = input.getBoolean("consultaAluno");
        if (input.getValue(Filtro.CRITERIO_NOME) == null) {
            input.setValue(Filtro.CRITERIO_ATIVOS, "true");
            if (input.getValue("consultaAluno") == null) {
                consultaAluno = true;
            }
            consequence = SHOW;
        }
        if (consultaAluno) {
            output.setValue(PAGINATOR, ServiceLocator.getUsuarioPlanoService().paginatorUsuPlaAbertoAtivo((Map<String, Object>) input));
            output.setValue("aluno", true);
        } else {
            output.setValue(PAGINATOR, ServiceLocator.getFuncionarioService().paginatorFuncionarioComAcesso((Map<String, Object>) input));
            output.setValue("aluno", false);
        }

        setMensagem();
        if (input.getValue("orderBy") != null) {
            output.setValue("order", "Decrescente!");
            output.setValue("orderBy", true);
        } else {
            output.setValue("order", "Crescente!");
        }
        return consequence;
    }
}
