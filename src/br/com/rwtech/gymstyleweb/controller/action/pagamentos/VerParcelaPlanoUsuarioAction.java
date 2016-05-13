package br.com.rwtech.gymstyleweb.controller.action.pagamentos;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Pagamento;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.SessaoAtr;
import java.util.Calendar;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class VerParcelaPlanoUsuarioAction extends PagamentoAction {

    public static String NOVO_MES = "NOVO_MES";

    @Override
    public String execute() throws Exception {
        Long idPlanoUsuario = (input.getLong(ID_PLANO_USUARIO));
        Long idParcela = null;
        if ((input.getString(ID_PARCELA) != null)) {
            idParcela = input.getLong(ID_PARCELA);
        }
        String consequence = SHOW;

        Map<String, Object> mapa = ServiceLocator.getPlanoService().readPlanoUsuarioByIdPlanoUsuario(idPlanoUsuario);
        output.setValue("mapa", mapa);
        Usuario usuario = (Usuario) mapa.get("usuario");
        Pagamento pagamento = null;
        Plano planoUsuario = (Plano) mapa.get(PLANO);
        String duracao = planoUsuario.getDuracaoPlano().getDuracao();
        if (planoUsuario != null && !planoUsuario.getCancelado()) {
            if (!planoUsuario.getFinalizado()) {
                int i = 0;
                Calendar dataAtual = Calendar.getInstance();
                dataAtual.set(Calendar.DAY_OF_MONTH, 1);// aponta para o mes atual
                while (i < planoUsuario.getPagamentos().size()) {
                    Pagamento pag = planoUsuario.getPagamentos().get(i);
                    // encontra a primeira a parcela sem pagar
                    // e que seja apartir da data atual
                    i++;
                    if (idParcela == null) {
                        if (planoUsuario.getDuracaoPlano().getDuracao().equalsIgnoreCase(MENSAL)) {
                            //Plano Mensal sempre buscar a proxima parcela em aberto deixando antigas se houver pra trás
                            if (pag.getPagamento() == null && pag.getVencimento().after(dataAtual)) {
                                pagamento = pag;
                                Plano atual = ServiceLocator.getPlanoService().readById(planoUsuario.getId());
                                if (pagamento.getNumeroParcela() > 1) {//parcela 0 e 1 sao de quando foram vinculadas no plano
                                    pagamento.setValor(atual.getValorTotal());
                                }
//                                if (pagamento.getNumeroParcela() == 0) {//Somente para plano mensal
//                                    pagamento.setValor(planoUsuario.getValorMatricula() + atual.getValorTotal());
//                                } else {
//                                    pagamento.setValor(atual.getValorTotal());
//                                }
                                output.setValue(QTDE_PARCELA, planoUsuario.getPagamentos().size());
                                output.setValue(TIPO_PLANO, planoUsuario.getDuracaoPlano().getDuracao());
                                break;
                            }
                        } else if (pag.getPagamento() == null) {//PLanos diferentes de mensal, sempre buscar a próxima em aberto
                            pagamento = pag;
                            output.setValue(QTDE_PARCELA, planoUsuario.getPagamentos().size());
                            output.setValue(TIPO_PLANO, planoUsuario.getDuracaoPlano().getDuracao());
                            break;
                        }
                    } else {
                        //para visuzalizar a parcela de pagamento especifica
                        if (idParcela.equals(pag.getId())) {
                            pagamento = pag;
                            output.setValue(QTDE_PARCELA, planoUsuario.getPagamentos().size());
                            output.setValue(TIPO_PLANO, planoUsuario.getDuracaoPlano().getDuracao());
                            break;
                        }
                    }
                }
                if (i == planoUsuario.getPagamentos().size() && !planoUsuario.getDuracaoPlano().getDuracao().startsWith(MENSAL)) {
                    output.setValue("ultimaParcela", true);
                }
                if (pagamento != null && idParcela == null) {
                    if (duracao.equalsIgnoreCase(MENSAL)) {
                        output.setValue(QTDE_PARCELA, 1);//para aparecer 1/1 - QTDE
                        if ((pagamento.getVencimento().get(Calendar.MONTH) < CalendarUtil.getDateCurrent().get(Calendar.MONTH))
                                && (pagamento.getVencimento().get(Calendar.YEAR) <= CalendarUtil.getDateCurrent().get(Calendar.YEAR))) {
                            return NOVO_MES;//para aqui mesmo
                        } else if ((pagamento.getVencimento().get(Calendar.YEAR) < CalendarUtil.getDateCurrent().get(Calendar.YEAR))) {
                            return NOVO_MES;//para aqui mesmo
                        }
                    }
                }
            }
        }

        if (pagamento == null) {
            if (duracao.equalsIgnoreCase(MENSAL)) {
                consequence = NOVO_MES;
            } else {
                consequence = ERROR;
            }
        } else {
//            Double valorAPagar = new Double(0);
//            if (planoUsuario.getDuracaoPlano().getDuracao().equalsIgnoreCase(MENSAL)
//                    && pagamento.getNumeroParcela() != 1) {
//                valorAPagar = planoUsuario.getValorTotal();
//            } else {
//                valorAPagar = pagamento.getValor();
//            }
//            valorAPagar += pagamento.getMulta() - pagamento.getMulta();
//            pagamento.setValor(valorAPagar);
//            output.setValue(VALOR_A_PAGAR, valorAPagar);

            output.setValue(VALOR_A_PAGAR, pagamento.getValor());
            output.setValue("pagamento", pagamento);
        }
        output.setValue("id", idPlanoUsuario);
        output.setValue(PLANO, planoUsuario);
        output.setValue(IMPRIMIR, 3);
        //output.setValue("plano", usuario.getPlanos().get(usuario.getPlanos().size() - 1));
        output.setValue(FORMA_DE_PAGAMENTO_MAP, ServiceLocator.getFormaPagamentoService().readList());
        setSelecionado(Ac.PAGAMENTO_READ);

        output.setValue("contas", ServiceLocator.getContaBancariaService().readList());
        output.setValue("perFluCx", ServiceLocator.getPermissaoService().readByName(Ac.MANAGER_CAIXA));
        session.setAttribute(SessaoAtr.ID_FLUXO_CAIXA, ServiceLocator.getFluxoCaixaService().idCaixaAberto());

        return consequence;
    }
}