package br.com.rwtech.gymstyleweb.controller.action.funcionario;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.StatusAcesso;
import br.com.rwtech.gymstylecore.model.util.StatusPlano;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.requisicao.RequisicaoUsuarioAction;
import br.com.rwtech.gymstyleweb.controller.action.usuario.UsuarioAction;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class FuncionarioDeleteAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;
        Long id = input.getLong("id");

        Boolean excluir = Boolean.TRUE;
        StatusAcesso statusAcesso = ServiceLocator.getFuncionarioService().contemAcesso(id);
        if (statusAcesso.equals(StatusAcesso.NAO_CONTEM)) {
            excluir = Boolean.FALSE;
        }

        Boolean cadastrar = Boolean.TRUE;
        StatusPlano statusPlano = ServiceLocator.getUsuarioPlanoService().readStatusUltimoPlano(id);
        if (statusPlano.equals(StatusPlano.NAO_CONTEM)) {
            cadastrar = Boolean.FALSE;
        }

        if (ServiceLocator.getFuncionarioService().disabledFuncionario(id)) {
            session.setAttribute("mensagem", "Funcionário excluído com sucesso!");
            consequence = SUCCESS;
        }

        //Se ele tinha acesso
        if (cadastrar) {
            RequisicaoUsuarioAction.create(id, output, (Usuario) getUserSession());
            consequence = UsuarioAction.ATUALIZAR;
        } else if (excluir) {
            RequisicaoUsuarioAction.delete(id, output, (Usuario) getUserSession());
            consequence = UsuarioAction.ATUALIZAR;
        }
        output.setValue("retorno", Ac.FUNCIONARIO_READ + Ac.DO);

        return (consequence);
    }
}