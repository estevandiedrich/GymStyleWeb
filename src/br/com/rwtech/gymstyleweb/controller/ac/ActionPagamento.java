package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.pagamentos.FinalizarPlanoAction;
import br.com.rwtech.gymstyleweb.controller.action.pagamentos.NovaParcelaMensalAction;
import br.com.rwtech.gymstyleweb.controller.action.pagamentos.PagamentoFilterReportAction;
import br.com.rwtech.gymstyleweb.controller.action.pagamentos.PagamentosReadAction;
import br.com.rwtech.gymstyleweb.controller.action.pagamentos.PagamentosUltimoPlanoAction;
import br.com.rwtech.gymstyleweb.controller.action.pagamentos.PagarNovaParcelaAction;
import br.com.rwtech.gymstyleweb.controller.action.pagamentos.PagarParcelaPlanoUsuarioAction;
import br.com.rwtech.gymstyleweb.controller.action.pagamentos.PagarParcelasPlanoUsuarioAction;
import br.com.rwtech.gymstyleweb.controller.action.pagamentos.UltimoPlanoAction;
import br.com.rwtech.gymstyleweb.controller.action.pagamentos.VerParcelaPlanoUsuarioAction;
import br.com.rwtech.gymstyleweb.controller.action.pagamentos.VerPlanoUsuarioAction;
import br.com.rwtech.gymstyleweb.controller.action.pagamentos.ajax.ImprimirPagamentoAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.pagamentos.ajax.PagamentosUpdateImprimirAjaxtAction;
import br.com.rwtech.gymstyleweb.controller.action.pagamentos.ajax.PagamentosUpdateImprimirEntradaAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.pagamentos.ajax.ReadStatusImprimirPagamentoAjax;
import br.com.rwtech.gymstyleweb.controller.action.pagamentos.autenticacao.PagamentoAutenticacaoRead;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationLancarParcelas;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationPagarParcela;
import br.com.rwtech.gymstyleweb.view.report.pagamentos.PagamentoReportAction;
import br.com.rwtech.gymstyleweb.view.report.recibo.ReciboReportAction;
import org.mentawai.authorization.Permission;
import org.mentawai.core.ActionConfig;
import org.mentawai.core.Chain;
import org.mentawai.core.Forward;
import org.mentawai.core.Redirect;
import org.mentawai.core.StreamConsequence;
import org.mentawai.filter.AuthorizationFilter;

/**
 *
 * @author Éder Faria
 */
public class ActionPagamento  extends Ac {

    private ApplicationManager am;
    private ActionConfig acaoVerPlanoUsuario = null;
    public static ActionPagamento instance = null;

    public static ActionPagamento addActions(ApplicationManager am) {
        return (get(am));
    }

    public static ActionPagamento get(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionPagamento();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionPagamento() {
    }

    public ActionConfig getAcaoVerPlanoUsuario() {
        if (acaoVerPlanoUsuario == null) {
            createActions();
        }
        return acaoVerPlanoUsuario;
    }

    protected void createActions() {
        ActionConfig ac = null;
        //FILTRO DE PAGAMENTOS PARA RELATÓRIOS
        ac = new ActionConfig(PAGAMENTO_FILTER, PagamentoFilterReportAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + PAGAMENTO + "/pagamentoFilter.page"));
        am.addActionConfig(ac);

        //RELATORIOS
        ac = new ActionConfig(PAGAMENTO_REPORT, PagamentoReportAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(PAGAMENTO_REPORT)));
        ac.addConsequence(PDF, new StreamConsequence(APPLICATION_PDF));
        ac.addConsequence(ERROR, new Forward(ERROR_REPORT_PAGE));
        am.addActionConfig(ac);

        //CONSULTA AUTENTICACAO
        ac = new ActionConfig(AUTENTICAO_PAGAMENTO_READ, PagamentoAutenticacaoRead.class);
        ac.addFilter(new AuthorizationFilter(new Permission(PAGAMENTO_READ)));
        ac.addConsequence(SHOW, new Forward(JSP + PAGAMENTO + "/autenticacao" + LIST_PAGE));
        ac.addConsequence(SUCCESS, new Forward(JSP + PAGAMENTO + "/autenticacao" + CORPO_JSP));
        am.addActionConfig(ac);

        //LISTAR USUARIO COM PLANOS EM ABERTO
        ac = new ActionConfig(PAGAMENTO_READ, PagamentosReadAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(PAGAMENTO_READ)));
        ac.addConsequence(SHOW, new Forward(JSP + PAGAMENTO + LIST_PAGE));
        ac.addConsequence(SUCCESS, new Forward(JSP + PAGAMENTO + CORPO_JSP));
        am.addActionConfig(ac);

        //LISTAR USUARIO COM ULTIMOS PLANOS
        ac = new ActionConfig(PAGAMENTO_ULTIMO_PLANOS_READ, PagamentosUltimoPlanoAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(PAGAMENTO_ULTIMO_PLANOS_READ)));
        ac.addConsequence(SHOW, new Forward(JSP + PAGAMENTO + "/pagamentosUltimoPlano.page"));
        ac.addConsequence(SUCCESS, new Forward(JSP + PAGAMENTO + "/corpoUltimoPlano.jsp"));
        am.addActionConfig(ac);

        //VER ULTIMO PLANO USUARIO
        ac = new ActionConfig(PAGAMENTOS_PLANO_USUARIO, UltimoPlanoAction.class);
        ac.addConsequence(SHOW, new Forward(JSP + PAGAMENTO + "/ultimoPlano.page"));
        ac.addConsequence(ERROR, new Redirect(PAGAMENTO_ULTIMO_PLANOS_READ + DO));
        ac.addConsequence(LIST, new Redirect(PAGAMENTO_ULTIMO_PLANOS_READ + DO));
        ac.addConsequence(SUCCESS, new Redirect(PAGAMENTO_ULTIMO_PLANOS_READ + DO));
        am.addActionConfig(ac);

        //NOVO MES MENSAL
        ActionConfig acaoNovoMes = null;
        acaoNovoMes = new ActionConfig(NOVA_PARCELA_MENSAL, NovaParcelaMensalAction.class);
        acaoNovoMes.addConsequence(SUCCESS, new Forward(JSP + PAGAMENTO + "/pagamentoParcela.page"));
        acaoNovoMes.addConsequence(ERROR, new Redirect(PAGAMENTO_READ + DO));
        am.addActionConfig(acaoNovoMes);

        //VER PLANO USUARIO
        acaoVerPlanoUsuario = new ActionConfig(VER_PLANO_USUARIO, VerPlanoUsuarioAction.class);
        acaoVerPlanoUsuario.addConsequence(SHOW, new Forward(JSP + PAGAMENTO + "/planoPagamento.page"));
        acaoVerPlanoUsuario.addConsequence(ERROR, new Redirect(PAGAMENTO_READ + DO));
        acaoVerPlanoUsuario.addConsequence(SUCCESS, new Redirect(PAGAMENTO_READ + DO));
        acaoVerPlanoUsuario.addConsequence(VerParcelaPlanoUsuarioAction.NOVO_MES, new Chain(acaoNovoMes));//"NOVO_MES"
        am.addActionConfig(acaoVerPlanoUsuario);

        //LANÇAR PARCELAS PLANO
        ac = new ActionConfig(LANCAR_PARCELAS_PLANO_USUARIO, PagarParcelasPlanoUsuarioAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(PAGAR_PARCELA_MANAGER)));
        ac.addConsequence(SHOW, new Forward(JSP + PAGAMENTO + "/planoPagamento.page"));
        ac.addConsequence(ERROR, new Forward(JSP + PAGAMENTO + "/planoPagamento.page"));
        ac.addConsequence(SUCCESS, new Chain(ActionRequisicao.get(am).getRequisicaoManager()));
        ac.addConsequence(LIST, new Redirect(PAGAMENTO_READ + DO));
        ac.addFilter(new ValidationLancarParcelas());
        am.addActionConfig(ac);

        //VER PLANO USUARIO
        ActionConfig acaoVerParcela = new ActionConfig(VER_PARCELA_PLANO_USUARIO, VerParcelaPlanoUsuarioAction.class);
        acaoVerParcela.addConsequence(SHOW, new Forward(JSP + PAGAMENTO + "/pagamentoParcela.page"));
        acaoVerParcela.addConsequence(ERROR, new Chain(acaoVerPlanoUsuario));
        acaoVerParcela.addConsequence(VerParcelaPlanoUsuarioAction.NOVO_MES, new Chain(acaoNovoMes));//"NOVO_MES"
        acaoVerParcela.addConsequence(SUCCESS, new Redirect(PAGAMENTO_READ + DO));
        am.addActionConfig(acaoVerParcela);

        //LANÇAR PARCELAS PLANO
        ac = new ActionConfig(PAGAR_PARCELA_PLANO_USUARIO, PagarParcelaPlanoUsuarioAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(PAGAR_PARCELA_MANAGER)));
        ac.addConsequence(SHOW, new Forward(JSP + PAGAMENTO + "/pagamentoParcela.page"));
        ac.addConsequence(ERROR, new Forward(JSP + PAGAMENTO + "/pagamentoParcela.page"));
        ac.addConsequence(SUCCESS, new Chain(ActionRequisicao.get(am).getRequisicaoManager()));
        ac.addConsequence(LIST, new Redirect(PAGAMENTO_READ + DO));
        ac.addFilter(new ValidationPagarParcela());
        am.addActionConfig(ac);

        //pagarNovaParcela
        //só vai chegar aqui quando uma parcela mensal tiver outrapassado o mes e for gerado uma nova parcela
        ac = new ActionConfig(PAGAR_NOVA_PARCELA, PagarNovaParcelaAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(PAGAR_PARCELA_MANAGER)));
        ac.addConsequence(ERROR, new Forward(JSP + PAGAMENTO + "/pagamentoParcela.page"));
        ac.addConsequence(SHOW, new Forward(JSP + PAGAMENTO + "/pagamentoParcela.page"));
        ac.addConsequence(LIST, new Redirect(PAGAMENTO_READ + DO));
        ac.addConsequence(SUCCESS, new Chain(ActionRequisicao.get(am).getRequisicaoManager()));
        ac.addFilter(new ValidationPagarParcela());
        am.addActionConfig(ac);

        ac = new ActionConfig(PAGAMENTO_UPDATE_IMPRIMIR_AJAX, PagamentosUpdateImprimirAjaxtAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + PAGAMENTO + RETORNO_AJAX_JSP));
        am.addActionConfig(ac);

        ac = new ActionConfig(PAGAMENTO_UPDATE_IMPRIMIR_ENTRADA_AJAX, PagamentosUpdateImprimirEntradaAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + PAGAMENTO + RETORNO_AJAX_JSP));
        am.addActionConfig(ac);
        
                //AJAX IMPRIMIR PAGAMENTO
        ac = new ActionConfig(IMPRIMIR_PAGAMENTO, ImprimirPagamentoAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + "pagamento/result.jsp"));
        am.addActionConfig(ac);

        //AJAX STATUS IMPRIMIR PAGAMENTO
        ac = new ActionConfig(READ_STATUS_IMPRIMIR_PAGAMENTO, ReadStatusImprimirPagamentoAjax.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + "pagamento/status.jsp"));
        ac.addConsequence(ERROR, new Forward(JSP + "pagamento/status.jsp"));
        am.addActionConfig(ac);

        //RELATORIOS
        ac = new ActionConfig(RECIBO_REPORT, ReciboReportAction.class);
        ac.addFilter(new AuthorizationFilter(new Permission(RECIBO_REPORT)));
        ac.addConsequence(PDF, new StreamConsequence(APPLICATION_PDF));
        ac.addConsequence(ERROR, new Forward(ERROR_REPORT_PAGE));
        am.addActionConfig(ac);
    }
}
