package br.com.rwtech.gymstyleweb.controller.action.pagamentos;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Pagamento;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
import br.com.rwtech.gymstylecore.model.pojo.tipos.Imprimir;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.SessaoAtr;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class VerPlanoUsuarioAction extends PagamentoAction {

    @Override
    public String execute() throws Exception {

        Long idPlanoUsuario = null;
        Long idUsuario = null;
        if (input.getValue(ID_PLANO_USUARIO) != null) {
            idPlanoUsuario = input.getLong(ID_PLANO_USUARIO);
        } else {
            idUsuario = input.getLong("id");
            idPlanoUsuario = ServiceLocator.getUsuarioPlanoService().readIdUltimoPlanoUsuario(idUsuario);
        }
        String consequence = SHOW;
        if (idPlanoUsuario == null || idPlanoUsuario == -1 || idPlanoUsuario == 0) {
            consequence = ERROR;
        } else {
            Map<String, Object> map = ServiceLocator.getPlanoService().readPlanoUsuarioByIdPlanoUsuario(idPlanoUsuario);
            output.setValue("mapa", map);
            output.setValue(FORMA_DE_PAGAMENTO_MAP, ServiceLocator.getFormaPagamentoService().readList());
            Plano plano = (Plano) map.get(PLANO);
            List<Pagamento> pagamentos = plano.getPagamentos();
            Double valorParcela = null;
            Pagamento ultimoPag = null;
            
            /*
             * Se o plano for mensal entrara no IF a seguir
             */
            if (plano.getDuracaoPlano().getDuracao().equalsIgnoreCase(MENSAL)) {
                //Quando mensal deverá colocar o valor do plano, pois pode ter sido alterado.
                Plano planoAtual = ServiceLocator.getPlanoService().readById(plano.getId());
                Double valorAtual = planoAtual.getValorTotal();
                Double matriculaAtual = planoAtual.getValorMatricula();
                Boolean naoTem = true;
                for (int i = 0; i < pagamentos.size(); i++) {
                    Pagamento pag = pagamentos.get(i);
                    valorParcela = pag.getValor();
                    //verifica se ja existe uma parcela para o mes corrente
                    
                    //Nao se pode alterar o valor quando o mes bater pq pode ter sido dado um desconto e fechado o valor alterado da parcela
                    if (pag.getVencimento().get(Calendar.MONTH) == CalendarUtil.getDateCurrent().get(Calendar.MONTH)) {
                        if (pag.getVencimento().get(Calendar.YEAR) == CalendarUtil.getDateCurrent().get(Calendar.YEAR)) {
                            naoTem = false;
                        }
                    }
                    if (pag.getPagamento() != null) {
                        pagamentos.remove(i);
                        i--;
                    } else {
                        if (pag.getVencimento().get(Calendar.YEAR) >= CalendarUtil.getDateCurrent().get(Calendar.YEAR)) {
                            if (pag.getVencimento().get(Calendar.MONTH) >= CalendarUtil.getDateCurrent().get(Calendar.MONTH)) {
                                if (pag.getNumeroParcela()> 1) {//Parcela 0 e 1 sao de quando foram vinculadas ao plano, acima delas pucha-se denovo o valor atual
                                    pag.setValor(valorAtual);//Seta a parcela atual com o valor do plano atual
                                }
//                                if (pag.getNumeroParcela() == 0) {
//                                    pag.setValor(matriculaAtual + valorAtual);//Seta a parcela atual com o valor do plano atual
//                                } else {
//                                    pag.setValor(valorAtual);//Seta a parcela atual com o valor do plano atual
//                                }
                            }
                        }
                    }
                    ultimoPag = pag;
                }

                if (naoTem || (pagamentos.size() == 0)) {
                    //se não existe a parcela daquele mes corrente, cria-se uma nova.
                    int diaVencimento = (ServiceLocator.getConfiguracaoService().readByCampo(DIA_VENCIMENTO).getValorInteiro());
                    int tolerancia = (ServiceLocator.getConfiguracaoService().readByCampo(TOLERANCIA).getValorInteiro());

                    if (ultimoPag != null) {
                        diaVencimento = ultimoPag.getVencimento().get(Calendar.DAY_OF_MONTH);
                        tolerancia = ultimoPag.getTolerancia();
                    }

                    Pagamento novoPagamento = new Pagamento();
                    novoPagamento.setValor(valorAtual);
                    novoPagamento.setDesconto(new Double(0));
                    novoPagamento.setValorPago(null);
                    novoPagamento.setNumeroParcela(2);//Lançar sempre 0 assim definido como Nova Parcela gerada
                    novoPagamento.setFormaDePagamento(null);
                    novoPagamento.setPostergar(Boolean.FALSE);
                    novoPagamento.setTolerancia(tolerancia);

                    novoPagamento.setInicioAcesso(Calendar.getInstance());
                    novoPagamento.setVencimento(Calendar.getInstance());
                    novoPagamento.setFimAcesso(Calendar.getInstance());

                    novoPagamento.getFimAcesso().set(Calendar.DAY_OF_MONTH, diaVencimento);
                    novoPagamento.getFimAcesso().add(Calendar.DAY_OF_MONTH, tolerancia);
                    novoPagamento.getFimAcesso().add(Calendar.MONTH, 1);

                    ServiceLocator.getPagamentoService().create(novoPagamento, idPlanoUsuario);
                    pagamentos.add(novoPagamento);
                }
            }

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
            output.setValue(IMPRIMIR, imprimir.getValor());

            output.setValue("pagamentos", pagamentos);
            output.setValue("qtdeParcelas", pagamentos.size());
            output.setValue("valorSoma", 0);
            output.setValue(MULTA, "0,00");
            output.setValue(DESCONTO, "0,00");
            setSelecionado(Ac.PAGAMENTO_READ);
        }

        output.setValue("contas", ServiceLocator.getContaBancariaService().readList());
        session.setAttribute(SessaoAtr.ID_FLUXO_CAIXA, ServiceLocator.getFluxoCaixaService().idCaixaAberto());
        
        setMensagem();
        return consequence;
    }

}