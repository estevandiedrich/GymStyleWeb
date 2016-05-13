package br.com.rwtech.gymstyleweb.controller.action.modalidade;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class ModalidadeDeleteAction extends BaseAction {

    @Override
    public String execute() throws Exception {

        String consequence = ERROR;

        if (ServiceLocator.getModalidadeService().disabled(input.getLong("id"))) {
            session.setAttribute("mensagem", "Modalidade excluída com sucesso!");
            consequence = SUCCESS;
        } else {
            session.setAttribute("mensagem", "Não foi possível excluir Modalidade!");
        }
        return (consequence);
    }
}