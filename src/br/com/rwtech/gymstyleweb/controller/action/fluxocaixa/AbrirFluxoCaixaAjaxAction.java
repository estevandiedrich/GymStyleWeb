package br.com.rwtech.gymstyleweb.controller.action.fluxocaixa;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.FluxoCaixa;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.Validador;
import br.com.rwtech.gymstyleweb.controller.action.SessaoAtr;
import java.util.Calendar;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class AbrirFluxoCaixaAjaxAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        FluxoCaixa fluxo = new FluxoCaixa();
        fluxo.setCaixa(ServiceLocator.getCaixaService().readById(Long.valueOf(1)));
        fluxo.setViBoleto(Validador.getMoney(input.getString("viBoleto")));
        fluxo.setViCartao(Validador.getMoney(input.getString("viCartao")));
        fluxo.setViCheque(Validador.getMoney(input.getString("viCheque")));
        fluxo.setViDeposito(Validador.getMoney(input.getString("viDeposito")));
        fluxo.setViDinheiro(Validador.getMoney(input.getString("viDinheiro")));
        fluxo.setValorInicial(Validador.getMoney(input.getString("viInicial")));
        fluxo.setAbertura(Calendar.getInstance());
        fluxo.setUsuarioAbriu((Usuario) getUserSession());
        ServiceLocator.getFluxoCaixaService().create(fluxo);
        session.setAttribute(SessaoAtr.MENSAGEM, "Caixa aberto com sucesso!");
        session.setAttribute(SessaoAtr.ID_FLUXO_CAIXA, ServiceLocator.getFluxoCaixaService().idCaixaAberto());
        return SUCCESS;
    }
}
