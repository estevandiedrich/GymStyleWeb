package br.com.rwtech.gymstyleweb.controller.action.pagamentos;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.FormaDePagamento;
import br.com.rwtech.gymstylecore.model.pojo.Pagamento;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
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
public class PagarNovaParcelaAction extends PagamentoAction {

    @Override
    public String execute() throws Exception {
        Long idPlanoUsuario = (input.getLong(ID_PLANO_USUARIO));
        //se chegou aqui é porque parcelas mensais ja se passaram de um mes.
        //ServiceLocator.getPagamentoService().deletePagamentosNULLByIdPlanoUsuario(idPlanoUsuario);
        String consequence = SHOW;
        Plano plano = (Plano) ServiceLocator.getPlanoService().readPlanoUsuarioByIdPlanoUsuario(idPlanoUsuario).get(PLANO);
        if (isPost()) {
            Pagamento pojo = new Pagamento();
            pojo.setPagamento(Calendar.getInstance());
            pojo.setNumeroParcela(1);

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
            FormaDePagamento forma = (ServiceLocator.getFormaPagamentoService().readById(input.getLong(FORMA_DE_PAGAMENTO)));
            pojo.setFormaDePagamento(forma);

            Pagamento pag = null;
            if (plano.getPagamentos().size() >= 2) {
                for (int i = 0; i < plano.getPagamentos().size(); i++) {
                    if (i == 1) {
                        //Recebe a segunda parcela do plano para pegar a data de vencimento
                        pag = plano.getPagamentos().get(i);
                        break;
                    }
                }
            }

            int diaVencimento = (ServiceLocator.getConfiguracaoService().readByCampo(DIA_VENCIMENTO).getValorInteiro());
            int tolerancia = (ServiceLocator.getConfiguracaoService().readByCampo(TOLERANCIA).getValorInteiro());

            Calendar dataAtual = Calendar.getInstance();
            pojo.setInicioAcesso(dataAtual);

            Calendar dataVencimento = Calendar.getInstance();
            pojo.setVencimento(dataVencimento);

            pojo.setFimAcesso(Calendar.getInstance());

            //Se ele pegou o dia de vencimento da segunda parcela, ele aponta para esse dia a ser criado a nova parcela
            if (pag != null) {
                diaVencimento = pag.getVencimento().get(Calendar.DAY_OF_MONTH);
                tolerancia = pag.getTolerancia();
            }
            pojo.setTolerancia(tolerancia);
            pojo.getVencimento().set(Calendar.DAY_OF_MONTH, diaVencimento);
            pojo.getFimAcesso().set(Calendar.DAY_OF_MONTH, diaVencimento);

            pojo.getFimAcesso().add(Calendar.MONTH, 1);
            pojo.getFimAcesso().add(Calendar.DAY_OF_MONTH, tolerancia);//Adiciona a tolerancia

            pojo.getVencimento().add(Calendar.MONTH, 1);//Aponta para o próximo mês
            pojo.getFimAcesso().add(Calendar.MONTH, 1);
            ServiceLocator.getPagamentoService().create(pojo, idPlanoUsuario);

            //Criando a parcela 1
            pojo = new Pagamento();
            pojo.setValorPago(plano.getValorTotal());
            pojo.setValor(plano.getValor());

            dataAtual = Calendar.getInstance();
            dataAtual.set(dataAtual.get(Calendar.YEAR), dataAtual.get(Calendar.MONTH), diaVencimento);
            dataAtual.add(Calendar.MONTH, 1);
            pojo.setNumeroParcela(1);
            pojo.setVencimento(dataAtual);
            pojo.setInicioAcesso(dataAtual);
            pojo.setFimAcesso(Calendar.getInstance());
            pojo.getFimAcesso().set(Calendar.DAY_OF_MONTH, diaVencimento);
            pojo.getFimAcesso().add(Calendar.MONTH, 2);
            pojo.setTolerancia(tolerancia);
            pojo.getFimAcesso().add(Calendar.DAY_OF_MONTH, pojo.getTolerancia());
            pojo.setFormaDePagamento(forma);

            ServiceLocator.getPagamentoService().create(pojo, idPlanoUsuario);
            
            //Se tem permissão de gerenciar fluxo de caixa, pode se entrar aqui
            if (ServiceLocator.getPermissaoService().ativo(Ac.MANAGER_CAIXA)) {
                //Na sessão tem o id do fluxo de caixa aberto
                ServiceLocator.getRegistroCaixaService().registrarPagamento(pojo.getId(), (Long) session.getAttribute(SessaoAtr.ID_FLUXO_CAIXA), input.getString(NOME_USUARIO));
                if (input.getValue(CONTA) != null && input.getLong(CONTA) != 0) {
                    ServiceLocator.getRegistroContaBancariaService().registrarPagamento(pojo, (Long) input.getLong(CONTA), input.getString(NOME_USUARIO));
                }
            }

            RequisicaoUsuarioAction.create(input.getLong(ID_USUARIO), output, (Usuario) getUserSession(), false);
            output.setValue(RETORNO, Ac.PAGAMENTO_READ + Ac.DO);
            output.setValue(RECIBO, pojo);
            session.setAttribute(MENSAGEM, "Pagamento realizado com sucesso!");
            consequence = SUCCESS;
        } else {
            output.setValue(MAPA, ServiceLocator.getPlanoService().readPlanoUsuarioByIdPlanoUsuario(idPlanoUsuario));
            output.setValue(FORMA_DE_PAGAMENTO_MAP, ServiceLocator.getFormaPagamentoService().readList());
            if (idPlanoUsuario == null || idPlanoUsuario == -1) {
                consequence = LIST;
                session.setAttribute(MENSAGEM, "Não foi possÍvel efetuar o pagamento!");
            }
        }
        setSelecionado(Ac.PAGAMENTO_READ);
        return consequence;
    }
}