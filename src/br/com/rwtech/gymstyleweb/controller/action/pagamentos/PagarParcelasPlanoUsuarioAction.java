package br.com.rwtech.gymstyleweb.controller.action.pagamentos;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Pagamento;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstylecore.model.util.Validador;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.SessaoAtr;
import br.com.rwtech.gymstyleweb.controller.action.requisicao.RequisicaoUsuarioAction;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Éder Faria
 */
public class PagarParcelasPlanoUsuarioAction extends PagamentoAction {

    @Override
    public String execute() throws Exception {
        Long idPlanoUsuario = input.getLong(ID_PLANO_USUARIO);
        // esta sendo colocado na ValidationLancarParcelas. Acaso nao foi selecionado
        //Boolean cheks = input.getBoolean("cheks");
        String consequence = SHOW;
        if (isPost()) {// && cheks) {
            Plano plano = (Plano) ServiceLocator.getPlanoService().readPlanoUsuarioByIdPlanoUsuario(idPlanoUsuario).get(PLANO);
            String tipo = "";
            if (plano != null) {
                tipo = plano.getDuracaoPlano().getDuracao();
            }
            //Long idPlanoUsuario = input.getLong("id");//id do relacionamento
            Integer tamanho = (input.getInt("pagamentoSize"));

            Calendar dataDePagamento = null;
            List<Pagamento> recibos = new ArrayList<Pagamento>();
            if (tamanho != null) {
                int qtde = 0;//para armazenar a qtde q estao marcados
                for (int i = 1; i <= tamanho; i++) {
                    if (input.getValue(PARCELA + i) != null) {
                        qtde++;
                    }
                }
                Double valorAPagar = Validador.getMoney(input.getString(VALOR_A_PAGAR)) / qtde;
                Double desconto = Validador.getMoney(input.getString(DESCONTO)) / qtde;
                Double multa = Validador.getMoney(input.getString(MULTA)) / qtde;
                Boolean naoEntrou = true;


                session.setAttribute(MENSAGEM, "Pagamento efetuado com sucesso!");
                for (int i = 1; i <= tamanho; i++) {
                    if (input.getValue(PARCELA + i) != null) {
                        //Integer numeroParcela = (input.getInt("numeroParcela" + i));
                        consequence = SUCCESS;
                        Long id = input.getLong(ID_PARCELA + i);

                        Pagamento pojo = ServiceLocator.getPagamentoService().readById(id);
                        recibos.add(pojo);
                        int imprimir = input.getInt(IMPRIMIR);
                        switch (imprimir) {
                            case 1:
                                pojo.setImprimir(Boolean.TRUE);
                                pojo.setImprimirEntrada(Boolean.TRUE);
                                break;
                            case 2:
                                pojo.setImprimir(Boolean.TRUE);
                                pojo.setImprimirEntrada(Boolean.FALSE);
                                break;
                            case 3:
                                pojo.setImprimir(Boolean.FALSE);
                                pojo.setImprimirEntrada(Boolean.FALSE);
                                break;
                            default:
                                pojo.setImprimir(Boolean.FALSE);
                                pojo.setImprimirEntrada(Boolean.FALSE);
                                break;
                        }
                        pojo.setPagamento(Calendar.getInstance());
                        pojo.setDesconto(desconto);
                        pojo.setMulta(multa);

                        Double valorParcela = input.getDouble(VALOR_PARCELA + i);
                        pojo.setValor(valorParcela);

//                        if (desconto == 0 && multa == 0) {
//                            pojo.setValorPago(valorParcela);
//                        } else {
//                            pojo.setValorPago(valorAPagar);
//                        }

                        if (desconto == 0 && multa == 0) {
                            pojo.setValorPago(valorParcela);
                        } else {
                            //Se entrar aqui, tem q somar o valor da parcela mais (multa ou menos o desconto). Se a primeira parcela tiver matricula vai somar normal o acrescimo ou desconto
                            Double valorPag = new Double(0);
                            valorPag = (valorParcela + multa) - desconto;
                            pojo.setValorPago(valorPag);
                        }

                        pojo.setJustificativa(input.getString(JUSTIFICATIVA));
                        pojo.setFuncionario((Usuario) getUserSession());

                        pojo.setFormaDePagamento(ServiceLocator.getFormaPagamentoService().readById(input.getLong(FORMA_DE_PAGAMENTO)));

                        ServiceLocator.getPagamentoService().update(pojo);
                        //Se tem permissão de gerenciar fluxo de caixa, pode se entrar aqui
                        if (ServiceLocator.getPermissaoService().ativo(Ac.MANAGER_CAIXA)) {
                            ServiceLocator.getRegistroCaixaService().registrarPagamento(pojo.getId(), (Long) session.getAttribute(SessaoAtr.ID_FLUXO_CAIXA), input.getString(NOME_USUARIO));
                            if (input.getValue(CONTA) != null && input.getLong(CONTA) != 0) {
                                ServiceLocator.getRegistroContaBancariaService().registrarPagamento(pojo, (Long) input.getLong(CONTA), input.getString(NOME_USUARIO));
                            }
                        }

                        if (i == tamanho && !tipo.equalsIgnoreCase(MENSAL)) {//Se ele passar pelo ultimo pagamento ele redireciona para listar pagamentos
                            ServiceLocator.getUsuarioPlanoService().finalizarPlano(idPlanoUsuario);
                            session.setAttribute(MENSAGEM, "Plano finalizado com sucesso!");
                            consequence = SUCCESS;
                        } else if (tipo.equalsIgnoreCase(MENSAL) && i == tamanho) {
                            //se for a ultima parcela cria-se uma nova
                            ServiceLocator.getPagamentoService().createNewPagamento(pojo.getId());
                        }
                        if (naoEntrou) {
                            //---------------------------------------------------------------------------
                            dataDePagamento = pojo.getFimAcesso();// Vai receber o fim de acesso como esta na catraca
                            // Para ser verificado se envia cadastro completo ou somente atuzalizações.
                            //---------------------------------------------------------------------------
                            naoEntrou = false;//é pq ja entrou e armazena a data de pagamento da ultima parcela paga na catraca
                        }
                    }
                }
            }
            output.setValue(VALOR_A_PAGAR, "0,00");
            output.setValue(DESCONTO, "");
            output.setValue(MULTA, "");
            output.setValue(JUSTIFICATIVA, "");

            Usuario usuario = ServiceLocator.getUsuarioService().readByIdSimple(input.getLong(ID_USUARIO));

            RequisicaoUsuarioAction.create(usuario.getId(), output, usuario, false);
            output.setValue(RETORNO, Ac.PAGAMENTO_READ + Ac.DO);
            output.setValue(RECIBOS, recibos);
        }
        if (idPlanoUsuario == null || idPlanoUsuario == -1) {
            consequence = LIST;
        }

        output.setValue(MAPA, ServiceLocator.getPlanoService().readPlanoUsuarioByIdPlanoUsuario(idPlanoUsuario));
        output.setValue(FORMA_DE_PAGAMENTO_MAP, ServiceLocator.getFormaPagamentoService().readList());

        setSelecionado(Ac.PAGAMENTO_READ);

        return consequence;
    }

    public List<Plano> getPlanos() {
        List<Plano> planos = null;
        Plano plano = ServiceLocator.getPlanoService().readById(input.getLong(PLANO_SELECT));
        if (plano != null) {
            planos = new ArrayList<Plano>();
            if (input.getValue(PERIODO_SELECT) != null) {
                plano.setDuracaoPlano(ServiceLocator.getDuracaoPlanoService().readById(input.getLong(PERIODO_SELECT)));
            }

            int i = 1;
            Calendar dataAtual = CalendarUtil.getDateCurrent();
            int ano = dataAtual.get(Calendar.YEAR);
            int dia = dataAtual.get(Calendar.DAY_OF_MONTH);
            plano.setPagamentos(new ArrayList<Pagamento>());
            while (true) {
                if (input.getValue(VENCIMENTO + i) != null) {
                    Pagamento pagamento = new Pagamento();
                    pagamento.setNumeroParcela(i);
                    pagamento.setPostergar(Boolean.FALSE);
                    if (i == 1) {
                        pagamento.setTolerancia(Integer.parseInt(input.getString(TOLERANCIA)));
                        pagamento.setVencimento(Calendar.getInstance());

                        pagamento.setInicioAcesso(pagamento.getVencimento());
                        pagamento.setFimAcesso(CalendarUtil.setDateCalendar(input.getString(VENCIMENTO + (i + 1)) + "/" + ano));
                        pagamento.getFimAcesso().add(Calendar.DAY_OF_MONTH, pagamento.getTolerancia());//aqui ele vai adicionar a tolerancia no periodo
                        pagamento.setValor(input.getDouble("valorTotal1"));
                    } else {
                        pagamento.setValor(input.getDouble("valorTotal" + i));
                        pagamento.setTolerancia(Integer.parseInt(input.getString(TOLERANCIA)));

                        //if
                        pagamento.setVencimento(CalendarUtil.setDateCalendar(input.getString(VENCIMENTO + i) + "/" + ano));
                        if (pagamento.getVencimento().get(Calendar.MONTH) == 0) {
                            pagamento.getVencimento().add(Calendar.YEAR, 1);
                            ano++;
                        }
                        pagamento.setInicioAcesso(CalendarUtil.setDateCalendar(input.getString(VENCIMENTO + i) + "/" + ano));
                        pagamento.setFimAcesso(CalendarUtil.setDateCalendar(input.getString(VENCIMENTO + i) + "/" + ano));
                        pagamento.getFimAcesso().add(Calendar.MONTH, +1);
                        pagamento.getFimAcesso().add(Calendar.DAY_OF_MONTH, pagamento.getTolerancia());//aqui ele vai adicionar a tolerancia no periodo
                    }
                    plano.getPagamentos().add(pagamento);
                } else {
                    break;
                }
                i++;
            }
            planos.add(plano);
        }
        return planos;
    }
}