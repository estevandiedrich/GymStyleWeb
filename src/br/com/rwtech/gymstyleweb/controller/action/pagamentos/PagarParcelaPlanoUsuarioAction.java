package br.com.rwtech.gymstyleweb.controller.action.pagamentos;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Pagamento;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.Validador;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.SessaoAtr;
import br.com.rwtech.gymstyleweb.controller.action.requisicao.RequisicaoUsuarioAction;
import java.util.Calendar;

/**
 *
 * @author Éder Faria
 */
public class PagarParcelaPlanoUsuarioAction extends PagamentoAction {

    @Override
    public String execute() throws Exception {
        Long idPlanoUsuario = (input.getLong(ID_PLANO_USUARIO));
        String consequence = SHOW;
        if (isPost()) {
            //Long idPlanoUsuario = input.getLong("id");//id do relacionamento
            Pagamento pojo = ServiceLocator.getPagamentoService().readById(input.getLong(ID_PARCELA));
            pojo.setPagamento(Calendar.getInstance());
            if (!input.getString(DESCONTO).isEmpty()) {
                Double desconto = Validador.getMoney(input.getString(DESCONTO));
                pojo.setDesconto(desconto);
            }
            if (!input.getString(MULTA).isEmpty()) {
                Double multa = Validador.getMoney(input.getString(MULTA));
                pojo.setMulta(multa);
            }
            pojo.setValor((input.getDouble(VALOR_PARCELA)));
            pojo.setValorPago(Validador.getMoney(input.getString(VALOR_A_PAGAR)));

            if (input.getInt(IMPRIMIR) == 1) {
                pojo.setImprimir(true);
                pojo.setImprimirEntrada(true);
            } else if (input.getInt(IMPRIMIR) == 2) {
                pojo.setImprimir(true);
                pojo.setImprimirEntrada(false);
            } else if (input.getInt(IMPRIMIR) == 3) {
                pojo.setImprimir(false);
                pojo.setImprimirEntrada(false);
            }

            pojo.setJustificativa(input.getString(JUSTIFICATIVA));
            pojo.setFuncionario((Usuario) getUserSession());
            pojo.setFormaDePagamento(ServiceLocator.getFormaPagamentoService().readById(input.getLong(FORMA_DE_PAGAMENTO)));

            ServiceLocator.getPagamentoService().update(pojo);
            //Se tem permissão de gerenciar fluxo de caixa, pode se entrar aqui
            if (ServiceLocator.getPermissaoService().ativo(Ac.MANAGER_CAIXA)) {
                //Na sessão tem o id do fluxo de caixa aberto
                ServiceLocator.getRegistroCaixaService().registrarPagamento(pojo.getId(), (Long) session.getAttribute(SessaoAtr.ID_FLUXO_CAIXA), input.getString(NOME_USUARIO));
                if (input.getValue(CONTA) != null && input.getLong(CONTA) != 0) {
                    ServiceLocator.getRegistroContaBancariaService().registrarPagamento(pojo, (Long) input.getLong(CONTA), input.getString(NOME_USUARIO));
                }
            }

            if (input.getValue(TIPO_PLANO) != null && input.getString(TIPO_PLANO).equalsIgnoreCase(MENSAL)) {
                ServiceLocator.getPagamentoService().createNewPagamento(pojo.getId());
            }

            if (input.getValue("ultimaParcela") != null
                    && (input.getValue(TIPO_PLANO) != null && !input.getString(TIPO_PLANO).equalsIgnoreCase(MENSAL))) {//Se for ultima parcela finaliza o plano
                ServiceLocator.getUsuarioPlanoService().finalizarPlano(idPlanoUsuario);
                session.setAttribute(MENSAGEM, "Plano finalizado com sucesso!");
                consequence = SUCCESS;
            } else {
                session.setAttribute(MENSAGEM, "Pagamento de parcela efetuado com sucesso!");
                consequence = SUCCESS;
            }

            //Atualização de usuário a seguir após o pagamentos
            Usuario usuario = ServiceLocator.getUsuarioService().readByIdSimple(input.getLong("idUsuario"));
            RequisicaoUsuarioAction.create(usuario.getId(), output, usuario, false);
            output.setValue(RETORNO, Ac.PAGAMENTO_READ + ".do");
            output.setValue(RECIBO, pojo);
        } else {
            output.setValue(MAPA, ServiceLocator.getPlanoService().readPlanoUsuarioByIdPlanoUsuario(idPlanoUsuario));
            output.setValue(FORMA_DE_PAGAMENTO_MAP, ServiceLocator.getFormaPagamentoService().readList());
            if (idPlanoUsuario == null || idPlanoUsuario == -1) {
                consequence = LIST;
                session.setAttribute(MENSAGEM, "Não foi possível efetuar o pagamento!");
            }
        }
        setSelecionado(Ac.PAGAMENTO_READ);
        return consequence;
    }
}