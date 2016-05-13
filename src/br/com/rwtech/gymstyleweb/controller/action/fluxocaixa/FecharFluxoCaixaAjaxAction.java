package br.com.rwtech.gymstyleweb.controller.action.fluxocaixa;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.FluxoCaixa;
import br.com.rwtech.gymstylecore.model.pojo.RegistroCaixa;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstyleweb.controller.action.SessaoAtr;
import java.util.Calendar;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class FecharFluxoCaixaAjaxAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        if (input.getValue("idFluxoCaixa") != null) {
            FluxoCaixa fluxo = ServiceLocator.getFluxoCaixaService().readById(input.getLong("idFluxoCaixa"));
            if (fluxo != null) {
                fluxo.setValorFinal(input.getDouble("valorFinal"));
                Double viDinheiro = new Double(0);
                Double viCheque = new Double(0);
                Double viCartao = new Double(0);
                Double viBoleto = new Double(0);
                Double viDeposito = new Double(0);
                Double vfDeposito = new Double(0);
                Double vfCheque = new Double(0);
                Double vfDinheiro = new Double(0);
                Double vfCartao = new Double(0);
                Double vfBoleto = new Double(0);
                for (RegistroCaixa regCaixa : fluxo.getRegistros()) {
                    if (regCaixa.getEntrada()) {
                        if (regCaixa.getFormaDePagamento().getFormaDePagamento().equalsIgnoreCase("Dinheiro")) {
                            viDinheiro += regCaixa.getValor();
                        } else if (regCaixa.getFormaDePagamento().getFormaDePagamento().equalsIgnoreCase("Cheque")) {
                            viCheque += regCaixa.getValor();
                        } else if (regCaixa.getFormaDePagamento().getFormaDePagamento().startsWith("Cart")) {
                            viCartao += regCaixa.getValor();
                        } else if (regCaixa.getFormaDePagamento().getFormaDePagamento().equalsIgnoreCase("Deposito")) {
                            viDeposito += regCaixa.getValor();
                        } else if (regCaixa.getFormaDePagamento().getFormaDePagamento().equalsIgnoreCase("Boleto")) {
                            viBoleto += regCaixa.getValor();
                        }
                    } else if (regCaixa.getRetirada()) {
                        if (regCaixa.getFormaDePagamento().getFormaDePagamento().equalsIgnoreCase("Dinheiro")) {
                            vfDinheiro += regCaixa.getValor();
                        } else if (regCaixa.getFormaDePagamento().getFormaDePagamento().equalsIgnoreCase("Cheque")) {
                            vfCheque += regCaixa.getValor();
                        } else if (regCaixa.getFormaDePagamento().getFormaDePagamento().startsWith("Cart")) {
                            vfCartao += regCaixa.getValor();
                        } else if (regCaixa.getFormaDePagamento().getFormaDePagamento().equalsIgnoreCase("Deposito")) {
                            vfDeposito += regCaixa.getValor();
                        } else if (regCaixa.getFormaDePagamento().getFormaDePagamento().equalsIgnoreCase("Boleto")) {
                            vfBoleto += regCaixa.getValor();
                        }
                    }
                }
                fluxo.setVfBoleto((fluxo.getViBoleto() + viBoleto) - vfBoleto);
                fluxo.setVfCartao((fluxo.getViCartao() + viCartao) - vfCartao);
                fluxo.setVfCheque((fluxo.getViCheque() + viCheque) - vfCheque);
                fluxo.setVfDeposito((fluxo.getViDeposito() + viDeposito) - vfDeposito);
                fluxo.setVfDinheiro((fluxo.getViDinheiro() + viDinheiro) - vfDinheiro);

                fluxo.setFechamento(Calendar.getInstance());
                fluxo.setUsuarioFechou((Usuario) getUserSession());

                ServiceLocator.getFluxoCaixaService().update(fluxo);
                session.setAttribute(SessaoAtr.MENSAGEM, "Caixa Fechado com sucesso!");
                session.setAttribute(SessaoAtr.ID_FLUXO_CAIXA, ServiceLocator.getFluxoCaixaService().idCaixaAberto());
            }
        }
        return SUCCESS;
    }
}
