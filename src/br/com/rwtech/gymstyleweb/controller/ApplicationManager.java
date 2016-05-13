package br.com.rwtech.gymstyleweb.controller;

import br.com.rwtech.gymstylecore.model.VerificaStatusBancoService;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.ac.ActionAjax;
import br.com.rwtech.gymstyleweb.controller.ac.ActionAvaliacaoFisica;
import br.com.rwtech.gymstyleweb.controller.ac.ActionBancoBackup;
import br.com.rwtech.gymstyleweb.controller.ac.ActionCategoria;
import br.com.rwtech.gymstyleweb.controller.ac.ActionConfiguracao;
import br.com.rwtech.gymstyleweb.controller.ac.ActionConfiguracaoBoleto;
import br.com.rwtech.gymstyleweb.controller.ac.ActionContaBancaria;
import br.com.rwtech.gymstyleweb.controller.ac.ActionDispositivo;
import br.com.rwtech.gymstyleweb.controller.ac.ActionEmpresa;
import br.com.rwtech.gymstyleweb.controller.ac.ActionEventos;
import br.com.rwtech.gymstyleweb.controller.ac.ActionExercicio;
import br.com.rwtech.gymstyleweb.controller.ac.ActionFicha;
import br.com.rwtech.gymstyleweb.controller.ac.ActionFluxoCaixa;
import br.com.rwtech.gymstyleweb.controller.ac.ActionFornecedor;
import br.com.rwtech.gymstyleweb.controller.ac.ActionFuncionario;
import br.com.rwtech.gymstyleweb.controller.ac.ActionLiberar;
import br.com.rwtech.gymstyleweb.controller.ac.ActionLog;
import br.com.rwtech.gymstyleweb.controller.ac.ActionModalidade;
import br.com.rwtech.gymstyleweb.controller.ac.ActionPagamento;
import br.com.rwtech.gymstyleweb.controller.ac.ActionPerfilAcesso;
import br.com.rwtech.gymstyleweb.controller.ac.ActionPlano;
import br.com.rwtech.gymstyleweb.controller.ac.ActionProduto;
import br.com.rwtech.gymstyleweb.controller.ac.ActionRelatorio;
import br.com.rwtech.gymstyleweb.controller.ac.ActionRequisicao;
import br.com.rwtech.gymstyleweb.controller.ac.ActionTipoUsuario;
import br.com.rwtech.gymstyleweb.controller.ac.ActionUsuario;
import br.com.rwtech.gymstyleweb.controller.ac.ActionUsuarioPlano;
import br.com.rwtech.gymstyleweb.controller.ac.ActionVenda;
import br.com.rwtech.gymstyleweb.controller.action.HelpAction;
import br.com.rwtech.gymstyleweb.controller.action.LoginAction;
import br.com.rwtech.gymstyleweb.controller.action.MainAction;
import br.com.rwtech.gymstyleweb.controller.action.RealFormatter;
import br.com.rwtech.gymstyleweb.controller.action.SincronizadosFalseAction;
import br.com.rwtech.gymstyleweb.controller.action.TesteAction;
import br.com.rwtech.gymstyleweb.controller.action.log.LogFilter;
import br.com.rwtech.gymstyleweb.controller.action.log.LogoutGymStyleWebAction;
import br.com.rwtech.gymstyleweb.controller.action.usuario.util.LoadImageAction;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationLogin;
import java.util.Locale;
import org.mentawai.core.ActionConfig;
import org.mentawai.core.Forward;
import org.mentawai.core.Redirect;
import org.mentawai.core.StreamConsequence;
import org.mentawai.filter.AuthenticationFilter;
import org.mentawai.filter.AuthorizationFilter;
import org.mentawai.formatter.DateFormatter;
import org.mentawai.formatter.FormatterManager;
import org.mentawai.i18n.LocaleManager;

/**
 *
 * @author Éder Faria
 */
public class ApplicationManager extends org.mentawai.core.ApplicationManager {

    private final String INDEX_JSP = "index.jsp";
    public static final String LOGOUT = "logout";

    @Override
    public void init() {

        //Coloca-se aqui, para quando ocorrer o deploy irá atualizar o banco e não toda vez que logar
        VerificaStatusBancoService.analisar();
        //GroupManager.getInstance().updateFalse();
        setReqCharEncoding("UTF8");
    }

    @Override
    public void loadLocales() {
        LocaleManager.add(new Locale("pt", "BR"));
//        LocaleManager.add(new Locale("en", "US"));
    }

    @Override
    public void loadFormatters() {
        FormatterManager.addFormatter("dateFormatter", new DateFormatter("dd/MM/yyyy"));
        FormatterManager.addFormatter("mesFormatter", new DateFormatter("MMMM/yyyy"));
        FormatterManager.addFormatter("realFormatter", new RealFormatter());
    }

    @Override
    public void loadActions() {
        //Adicionando os filtros básicos.atualizar
        this.addGlobalFilter(new AuthenticationFilter());
        this.addGlobalConsequence(AuthenticationFilter.LOGIN, new Forward("logout.jsp"));
        this.addGlobalConsequence(AuthorizationFilter.ACCESSDENIED, new Forward("logout.jsp"));
        this.addGlobalFilter(new LogFilter());

        ActionConfig ac;

        ac = new ActionConfig("teste", TesteAction.class);
        ac.addConsequence(SUCCESS, new Forward("teste.page"));
        this.addActionConfig(ac);

        ac = new ActionConfig(Ac.LOGIN, LoginAction.class);
        ac.addConsequence(SUCCESS, new Redirect(Ac.MAIN + Ac.DO));
        ac.addConsequence(ERROR, new Forward(INDEX_JSP));
        ac.addFilter(new ValidationLogin());
        this.addActionConfig(ac);

        ac = new ActionConfig(LOGOUT, LogoutGymStyleWebAction.class);
        ac.addConsequence(SUCCESS, new Forward(INDEX_JSP));
        this.addActionConfig(ac);

        ac = new ActionConfig(Ac.INDEX, LogoutGymStyleWebAction.class);
        ac.addConsequence(SUCCESS, new Forward(INDEX_JSP));
        this.addActionConfig(ac);

        ac = new ActionConfig(Ac.MAIN, MainAction.class);
        ac.addConsequence(SUCCESS, new Forward(Ac.JSP + "/template/main.page"));
        ac.addConsequence(ERROR, new Forward(INDEX_JSP));
        this.addActionConfig(ac);

        ac = new ActionConfig(Ac.SINCRONIZADOS_FALSE, SincronizadosFalseAction.class);
        ac.addConsequence(SUCCESS, new Redirect(Ac.REQUISICAO_ATUALIZAR_TODOS + Ac.DO));
        this.addActionConfig(ac);

        ac = new ActionConfig(Ac.HELP, HelpAction.class);
        ac.addConsequence(SUCCESS, new Forward("driver/help.page"));
        ac.addConsequence(ERROR, new Forward(INDEX_JSP));
        this.addActionConfig(ac);

        ac = new ActionConfig(Ac.LOAD_IMAGE, LoadImageAction.class);
        ac.addConsequence(SUCCESS, new StreamConsequence("image/jpg"));
        this.addActionConfig(ac);

        //tem que chamar a action de requisicoes, pq requisicoes abaixo precisaram de instancias de alguma action de requisicoes.
        ActionRequisicao.addActions(this);
        ActionRequisicao.addActions(this);
        ActionPagamento.addActions(this);

        ActionUsuario.addActions(this);
        ActionFuncionario.addActions(this);

        ActionFicha.addActions(this);
        ActionTipoUsuario.addActions(this);
        ActionDispositivo.addActions(this);

        ActionPerfilAcesso.addActions(this);
        ActionModalidade.addActions(this);
        ActionExercicio.addActions(this);
        ActionEmpresa.addActions(this);
        ActionContaBancaria.addActions(this);
        ActionBancoBackup.addActions(this);
        ActionAvaliacaoFisica.addActions(this);
        ActionPlano.addActions(this);
        ActionConfiguracao.addActions(this);

        ActionLiberar.addActions(this);

        ActionEventos.addActions(this);
        ActionLog.addActions(this);
        ActionRelatorio.addActions(this);
        ActionUsuarioPlano.addActions(this);
        ActionFluxoCaixa.addActions(this);
        ActionConfiguracaoBoleto.addActions(this);

        ActionCategoria.addActions(this);
        ActionFornecedor.addActions(this);
        ActionProduto.addActions(this);
        ActionVenda.addActions(this);

        ActionAjax.addActions(this);
    }
}
