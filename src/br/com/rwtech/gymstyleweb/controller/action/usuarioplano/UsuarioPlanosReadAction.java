package br.com.rwtech.gymstyleweb.controller.action.usuarioplano;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Pagamento;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;

/**
 *
 * @author Éder Faria
 */
public class UsuarioPlanosReadAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        Usuario usuario = ServiceLocator.getUsuarioService().readById(input.getLong("id"));
        output.setValue("usuario", usuario);
        output.setValue("list", usuario.getPlanos());
        output.setValue("usuarioVincular" + usuario.getId(), "<a id='vincular' title='Vincular'  href='vincularPlano.do?id=" + usuario.getId() + "'>Vincular Plano</a>");
        boolean planoEmAberto = false;
        if (usuario.getPlanos() != null && !usuario.getPlanos().isEmpty()) {
            usuario.setPlanos(ServiceLocator.getUsuarioPlanoService().readPlanosToUsuario(usuario.getId(), Boolean.TRUE));
            int qtdePlanos = usuario.getPlanos().size();
            for (int j = 0; j < usuario.getPlanos().size(); j++) {
                //Lista trazida em ordem decrescente. Ultimo plano primeiro na posição zero
                Plano plano = usuario.getPlanos().get(j);
                if (plano.getCancelado()) {
                    output.setValue("planoCancelado" + plano.getIdPlanoUsuario(), "<a id='cancelado' title='Cancelado' href='#'>Cancelado</a>");
                } else {
                    if (j == (0)) {// só se pode cancelar o ultimo plano em aberto que foi trazido do banco por ordem descrescente
                        output.setValue("planoCancelar" + plano.getIdPlanoUsuario(), "<a id='cancelarPlano' class='cancelarPlano' title='Cancelar'  href='cancelarPlano.do?idPlanoUsuario="
                                + plano.getIdPlanoUsuario() + "'>Cancelar</a>");
                    }
                }
                if (plano.getFinalizado()) {
                    output.setValue("planoQuitado" + plano.getIdPlanoUsuario(), "<a id='quitado' title='Plano Quitado' href='#'>Quitado</a>");
                } else if (!plano.getCancelado()) {
                    //Qualquer plano diferente de zero(Ultimo plano). Seta para ser finalizado
                    if (j != 0) {
                        //Se conter planos antigos em aberto, seta se para false. Medida de reajuste se houver
                        ServiceLocator.getUsuarioPlanoService().cancelarPlano(plano.getIdPlanoUsuario(), new Double(0));
                        output.setValue("planoCancelado" + plano.getIdPlanoUsuario(), "<a id='cancelado' title='Cancelado'  href='#'>Cancelado</a>");
                    } else {
                        output.setValue("planoPagar" + plano.getIdPlanoUsuario(), "<a id='pagamentos' title='Ver'  href='verPlanoUsuario.do?idPlanoUsuario=" + plano.getIdPlanoUsuario() + "'>Pagar plano</a>");
                        planoEmAberto = true;
                    }
                }

                if (!plano.getPagamentos().isEmpty()) {
                    for (int i = 0; i < plano.getPagamentos().size(); i++) {
                        Pagamento pagamento = plano.getPagamentos().get(i);
                        if (pagamento.getPagamento() != null) {
                            if (i == 0) {
                                output.setValue("planoInicio" + plano.getIdPlanoUsuario(), pagamento.getInicioAcesso().getTime());
                            }
                            output.setValue("planoFim" + plano.getIdPlanoUsuario(), pagamento.getFimAcesso().getTime());
                        }
                        //Tem que ir até o fim por que o plano pode ter pagamentos para tras em aberto
                    }
                }
            }
        }

        if (planoEmAberto) {
            output.setValue("usuarioVincular" + usuario.getId(),
                    "<a id='plano' title='Plano em Aberto!' style='cursor: default'   href='#'>Plano em Aberto</a>");//se tiver plano em aberto nao se pode vincular
        }
        output.setValue("selecionado", "usuarioPlanoRead");

        if (input.getValue("orderBy") != null) {
            output.setValue("order", "Decrescente!");
            output.setValue("orderBy", true);
        } else {
            output.setValue("order", "Crescente!");
        }
        setMensagem();

        return SUCCESS;
    }
}
