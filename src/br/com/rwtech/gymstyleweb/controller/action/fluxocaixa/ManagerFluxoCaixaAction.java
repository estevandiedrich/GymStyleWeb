package br.com.rwtech.gymstyleweb.controller.action.fluxocaixa;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.FluxoCaixa;
import br.com.rwtech.gymstylecore.model.pojo.RegistroCaixa;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class ManagerFluxoCaixaAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        //map.put(JSP, map)
        if (input.getValue("criterioInicio") == null) {
            input.setValue("criterioInicio", CalendarUtil.getDataCurrente());
            input.setValue("criterioFim", CalendarUtil.getDataCurrente());
        }
        input.setValue("criterioUltimo", "true");

        List<FluxoCaixa> fluxos = ServiceLocator.getFluxoCaixaService().readByCriteria((Map<String, Object>) input);
        if (fluxos != null && !fluxos.isEmpty() && fluxos.get(0).getFechamento() == null) {
            output.setValue("aberto", true);
        } else {
            output.setValue("aberto", false);
        }

        Double dinheiroTotalCaixa = new Double(0);
        Double chequeTotalCaixa = new Double(0);

        Double dinheiroEntrada = new Double(0);
        Double chequeEntrada = new Double(0);
        Double cartaoEntrada = new Double(0);
        Double boletoEntrada = new Double(0);
        Double depositoEntrada = new Double(0);

        Double dinheiroRetirada = new Double(0);
        Double chequeRetirada = new Double(0);
        Double cartaoRetirada = new Double(0);
        Double boletoRetirada = new Double(0);
        Double depositoRetirada = new Double(0);

        FluxoCaixa fluxo = null;
        if (!fluxos.isEmpty()) {
            fluxo = fluxos.get(0);
            List<RegistroCaixa> registros = fluxos.get(0).getRegistros();
            output.setValue("registros", registros);
            for (RegistroCaixa aux : registros) {
                if (aux.getEntrada()) {
                    if (aux.getFormaDePagamento().getFormaDePagamento().equalsIgnoreCase("Cheque")) {
                        chequeTotalCaixa += aux.getValor();
                        chequeEntrada += aux.getValor();
                    }
                    if (aux.getFormaDePagamento().getFormaDePagamento().equalsIgnoreCase("Dinheiro")) {
                        dinheiroTotalCaixa += aux.getValor();
                        dinheiroEntrada += aux.getValor();
                    }
                    if (aux.getFormaDePagamento().getFormaDePagamento().equalsIgnoreCase("Boleto")) {
                        boletoEntrada += aux.getValor();
                    }
                    if (aux.getFormaDePagamento().getFormaDePagamento().equalsIgnoreCase("Deposito")) {
                        depositoEntrada += aux.getValor();
                    }
                    if (aux.getFormaDePagamento().getFormaDePagamento().startsWith("Cart")) {
                        cartaoEntrada += aux.getValor();
                    }
                }
                if (aux.getRetirada()) {
                    if (aux.getFormaDePagamento().getFormaDePagamento().equalsIgnoreCase("Cheque")) {
                        chequeTotalCaixa -= aux.getValor();
                        chequeRetirada += aux.getValor();
                    }
                    if (aux.getFormaDePagamento().getFormaDePagamento().equalsIgnoreCase("Dinheiro")) {
                        dinheiroTotalCaixa -= aux.getValor();
                        dinheiroRetirada += aux.getValor();
                    }
                    if (aux.getFormaDePagamento().getFormaDePagamento().equalsIgnoreCase("Boleto")) {
                        boletoRetirada += aux.getValor();
                    }
                    if (aux.getFormaDePagamento().getFormaDePagamento().equalsIgnoreCase("Deposito")) {
                        depositoRetirada += aux.getValor();
                    }
                    if (aux.getFormaDePagamento().getFormaDePagamento().startsWith("Cart")) {
                        cartaoRetirada += aux.getValor();
                    }

                }
            }
            chequeTotalCaixa += fluxo.getViCheque();
            dinheiroTotalCaixa += fluxo.getViDinheiro();
        } else {
            fluxo = new FluxoCaixa();
        }
        output.setValue("dinheiroEntrada", dinheiroEntrada);
        output.setValue("cartaoEntrada", cartaoEntrada);
        output.setValue("boletoEntrada", boletoEntrada);
        output.setValue("depositoEntrada", depositoEntrada);
        output.setValue("chequeEntrada", chequeEntrada);

        output.setValue("dinheiroRetirada", dinheiroRetirada);
        output.setValue("cartaoRetirada", cartaoRetirada);
        output.setValue("boletoRetirada", boletoRetirada);
        output.setValue("depositoRetirada", depositoRetirada);
        output.setValue("chequeRetirada", chequeRetirada);

        output.setValue("dinheiroFechar", new Double(fluxo.getViDinheiro() + dinheiroEntrada - dinheiroRetirada));
        output.setValue("cartaoFechar", new Double(fluxo.getViCartao() + cartaoEntrada - cartaoRetirada));
        output.setValue("boletoFechar", new Double(fluxo.getViBoleto() + boletoEntrada - boletoRetirada));
        output.setValue("depositoFechar", new Double(fluxo.getViDeposito() + depositoEntrada - depositoRetirada));
        output.setValue("chequeFechar", new Double(fluxo.getViCheque() + chequeEntrada - chequeRetirada));

        output.setValue("pojo", fluxo);

        output.setValue("formasPagamentosEntrada", ServiceLocator.getFormaPagamentoService().readList());
        Map<Long, String> mapRetirada = ServiceLocator.getFormaPagamentoService().readList();
        mapRetirada.remove(Long.valueOf(2));//Boleto
        mapRetirada.remove(Long.valueOf(3));//Cartao Debito
        mapRetirada.remove(Long.valueOf(4));//Cartao Credito
        output.setValue("formasPagamentosRetirada", mapRetirada);
        output.setValue("contas", ServiceLocator.getContaBancariaService().readList());

        output.setValue("chequeCaixa", chequeTotalCaixa);
        output.setValue("dinheiroCaixa", dinheiroTotalCaixa);
        output.setValue("selecionado", "fluxoCaixaRead");


        if (input.getValue("criterioMes") == null) {
            //Relatório Aba Histórico
            Calendar calendar = Calendar.getInstance();
            input.setValue("criterioMes", (calendar.get(Calendar.MONTH) + 1));
            input.setValue("criterioAno", calendar.get(Calendar.YEAR));
        }

        output.setValue("meses", ServiceLocator.getRegistroCaixaService().getMeses());
        output.setValue("anos", ServiceLocator.getRegistroCaixaService().getAnos());
        setMensagem();

        return SUCCESS;
    }
}