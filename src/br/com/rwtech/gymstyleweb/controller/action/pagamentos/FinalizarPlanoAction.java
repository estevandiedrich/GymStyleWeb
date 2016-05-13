package br.com.rwtech.gymstyleweb.controller.action.pagamentos;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;

/**
 *
 * @author Éder Faria
 */
public class FinalizarPlanoAction extends PagamentoAction {

    @Override
    public String execute() throws Exception {
        Long id = input.getLong("id");
        String duracao = input.getString("duracao");
        if (duracao != null && duracao.equalsIgnoreCase("Mensal")) {
            ServiceLocator.getPagamentoService().deletePagamentosNULLByIdPlanoUsuario(id);
        }
        ServiceLocator.getUsuarioPlanoService().finalizarPlano(id);
        session.setAttribute(MENSAGEM, "Plano finalizado com sucesso!");
        setSelecionado(Ac.PAGAMENTO_READ);

        return SUCCESS;
    }
}
