package br.com.rwtech.gymstyleweb.controller.action.pagamentos;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Pagamento;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
import br.com.rwtech.gymstylecore.model.pojo.tipos.Imprimir;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class UltimoPlanoAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        Long idPlanoUsuario = (input.getLong("idPlanoUsuario"));
        Long idUsuario = null;

        if (idPlanoUsuario == null || idPlanoUsuario == -1) {
            idUsuario = (input.getLong("idUsuario"));
            idPlanoUsuario = ServiceLocator.getUsuarioPlanoService().readIdUltimoPlanoUsuario(idUsuario);
        }

        String consequence = SHOW;
        if (idPlanoUsuario == null || idPlanoUsuario <= 0) {
            consequence = LIST;//Será redirecionado para a tela de consultar histórico
        } else {
            Map<String, Object> map = ServiceLocator.getPlanoService().readPlanoUsuarioByIdPlanoUsuario(idPlanoUsuario);
            if (map != null) {
                output.setValue("mapa", map);
                Plano plano = (Plano) map.get("plano");
                if (plano != null) {
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
                        valorFinal = new Double(0);
                    }
                    output.setValue("selecionado", "usuarioPlanoRead");
                    output.setValue("valorTotalPagamento", valorFinal);
                    output.setValue("qtdeParcelas", pagamentos.size());

                    Imprimir imprimir = Imprimir.NAO_IMPRIMIR;
                    output.setValue("finalizar", false);
                    for (Pagamento pojo : pagamentos) {
                        if (pojo.getPagamento() == null) {//é pq nao foi pago
                            output.setValue("finalizar", true);
                            break;
                        }
                        //Verificar status da parcela para imprimir/ entrada/saida
                        if (imprimir == Imprimir.NAO_IMPRIMIR) {//so entrará aqui uma vez
                            if (pojo.getImprimir()) {
                                if (pojo.getImprimirEntrada()) {
                                    imprimir = Imprimir.ENTRADA;
                                } else {
                                    imprimir = Imprimir.SAIDA;
                                }
                            }
                        }
                    }
                    output.setValue("imprimir", imprimir.getValor());
                } else {
                    consequence = ERROR;
                }
            } else {
                consequence = ERROR;//Será redirecionado para a tela de consultar histórico
            }
        }
        return consequence;
    }
}
