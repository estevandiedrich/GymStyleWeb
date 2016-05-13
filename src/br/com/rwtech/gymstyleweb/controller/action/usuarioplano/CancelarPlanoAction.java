package br.com.rwtech.gymstyleweb.controller.action.usuarioplano;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Pagamento;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.StatusAcesso;
import br.com.rwtech.gymstylecore.model.util.Validador;
import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.requisicao.RequisicaoUsuarioAction;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class CancelarPlanoAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        Long idPlanoUsuario = (input.getLong("idPlanoUsuario"));
        String consequence = SHOW;

        if (idPlanoUsuario != null && idPlanoUsuario > 0) {
            Map<String, Object> map = ServiceLocator.getPlanoService().readPlanoUsuarioByIdPlanoUsuario(idPlanoUsuario);
            if (isPost()) {
                int tam = 0;
                if (!input.getString("pagamentoSize").isEmpty()) {
                    tam = input.getInt("pagamentoSize");
                }
                for (int i = 1; i < tam; i++) {
                    if (input.getValue("parcela" + i) != null) {
                        Pagamento pagamento = ServiceLocator.getPagamentoService().readById(input.getLong("idParcela" + i));
                        pagamento.setValor(new Double(0));
                        pagamento.setValorPago(new Double(0));
                        pagamento.setPagamento(null);
                        ServiceLocator.getPagamentoService().update(pagamento);
                    } else if (input.getValue("zerarParcela" + i) != null) {
                        Pagamento pagamento = ServiceLocator.getPagamentoService().readById(input.getLong("idParcela" + i));
                        pagamento.setValorPago(new Double(0));
                        pagamento.setPagamento(null);
                        ServiceLocator.getPagamentoService().update(pagamento);
                    }
                }

                Double valorCancelamento = Validador.getMoney(input.getString("valorTotalPagamento"));
                ServiceLocator.getUsuarioPlanoService().cancelarPlano(idPlanoUsuario, valorCancelamento);
                consequence = SUCCESS;
                session.setAttribute("mensagem", "Plano cancelado com sucesso!");

                //Verificando usuario
                Usuario usuario = (Usuario) map.get("usuario");
                Long id = usuario.getId();

                Boolean cadastrar = Boolean.TRUE;
                StatusAcesso statusAcesso = ServiceLocator.getFuncionarioService().contemAcesso(id);
                if (statusAcesso.equals(StatusAcesso.NAO_CONTEM)) {
                    cadastrar = Boolean.FALSE;
                }
                
                //Se ele tinha acesso
                if (cadastrar) {
                    RequisicaoUsuarioAction.create(id, output, (Usuario) getUserSession());
                    consequence = Ac.ATUALIZAR;
                } else {
                    RequisicaoUsuarioAction.delete(id, output, (Usuario) getUserSession());
                    consequence = Ac.EXCLUIR;
                }

                output.setValue("idUsuario", usuario.getId());
                output.setValue("retorno", "usuariosPlanoRead.do");
            } else {
                output.setValue("mapa", map);
                Plano plano = (Plano) map.get("plano");
                List<Pagamento> pagamentos = null;
                if (plano.getPagamentos() != null) {
                    pagamentos = plano.getPagamentos();
                } else {
                    pagamentos = new ArrayList<Pagamento>();
                }
                output.setValue("pagamentos", pagamentos);
                Double valor = new Double(0);
                Double valorPago = new Double(0);
                for (Pagamento pagamento : pagamentos) {
                    valor += pagamento.getValor();
                    if (pagamento.getValorPago() != null) {
                        valorPago += pagamento.getValorPago();
                    }
                }
                output.setValue("valorPagamento", valor);
                output.setValue("valorPagoPagamento", valorPago);
                Double valorFinal = valor - valorPago;
                if (valorFinal < 0) {
//                valorFinal = valorFinal * -1;
                    valorFinal = new Double(0);
                }
                output.setValue("selecionado", "usuarioPlanoRead");
                output.setValue("valorTotalPagamento", Validador.getMoney(valorFinal));
                output.setValue("qtdeParcelas", pagamentos.size());
            }
        } else {
            consequence = ApplicationManager.LIST;
        }
        return consequence;
    }
}