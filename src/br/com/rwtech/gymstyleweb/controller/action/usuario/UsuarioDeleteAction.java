package br.com.rwtech.gymstyleweb.controller.action.usuario;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.StatusAcesso;
import br.com.rwtech.gymstylecore.model.util.StatusPlano;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.requisicao.RequisicaoUsuarioAction;
import org.mentawai.core.BaseAction;

public class UsuarioDeleteAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        output.setValue("selecionado", Ac.USUARIO_READ);
        String consequence = ERROR;
        Long id = input.getLong("id");

        Boolean cadastrar = Boolean.TRUE;
        StatusAcesso statusAcesso = ServiceLocator.getFuncionarioService().contemAcesso(id);
        if (statusAcesso.equals(StatusAcesso.NAO_CONTEM)) {
            cadastrar = Boolean.FALSE;
        }

        Boolean excluir = Boolean.TRUE;
        StatusPlano statusPlano = ServiceLocator.getUsuarioPlanoService().readStatusUltimoPlano(id);
        if (statusPlano.equals(StatusPlano.NAO_CONTEM)) {
            excluir = Boolean.FALSE;
        }

        if (ServiceLocator.getUsuarioService().disabledAluno(id)) {
            session.setAttribute("mensagem", "Aluno excluído com sucesso!");
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

        return (consequence);
    }
}
