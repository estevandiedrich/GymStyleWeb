package br.com.rwtech.gymstyleweb.controller.action.usuario.update;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.usuario.UsuarioAction;

/**
 * @author Éder Faria
 */
public class UsuarioUpdateIdentificacaoAction extends UpdateIdentificacaoAction {

    @Override
    public String execute() throws Exception {
        Long idUsuario = input.getLong("id");
        String consequence = SHOW;
        if (idUsuario != null) {
            if (isPost() && input.getValue("cartaoProximidade") != null) {
                consequence = post(idUsuario);
                if (consequence.equalsIgnoreCase(UsuarioAction.ATUALIZAR)) {
                    output.setValue("retorno", Ac.USUARIO_READ + Ac.DO);//Retorno default
                }
            } else {
                output.setValue("id", idUsuario);
                ServiceLocator.getImpressaoDigitalService().preDigitais(idUsuario);
                //Dentro do preload, buscará as digitais da tabela digitais_espelho
                this.preload(output, input);
                output.setValue("retornoVerificaMatricula", "");
            }
        } else {
            consequence = UsuarioAction.INESPERADO;
            session.setAttribute("mensagem", "Não foi possível Alterar. Tente novamente!");
        }
        return consequence;
    }
}