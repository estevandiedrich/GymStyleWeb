package br.com.rwtech.gymstyleweb.controller.action.fluxocaixa;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.RegistroCaixa;
import br.com.rwtech.gymstylecore.model.pojo.RegistroContaBancaria;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.Validador;
import java.util.Calendar;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class EntradaFluxoCaixaAjaxAction extends BaseAction {

    private String ID_CONTA_BANCARIA = "idContaBancaria";
    private String ID_FLUXO_CAIXA = "idFluxoCaixa";

    @Override
    public String execute() throws Exception {
        RegistroCaixa regCaixa = null;
        RegistroContaBancaria regContaBancaria = null;

        boolean create = true;
        if (input.getString("idRegistroEntrada") != null && !input.getString("idRegistroEntrada").isEmpty()) {
            create = false;
            regCaixa = ServiceLocator.getRegistroCaixaService().readById(input.getLong("idRegistroEntrada"));
            regContaBancaria = regCaixa.getRegistroContaBancaria();
        } else {
            regCaixa = new RegistroCaixa();
        }

        regCaixa.setDataHora(Calendar.getInstance());
        regCaixa.setDescricao(input.getString("descricao"));
        regCaixa.setEntrada(Boolean.TRUE);
        regCaixa.setRetirada(Boolean.FALSE);
        regCaixa.setEdit(Boolean.TRUE);

        regCaixa.setValor(Validador.getMoney(input.getString("valor")));
        regCaixa.setParcela(null);
        regCaixa.setFormaDePagamento(ServiceLocator.getFormaPagamentoService().readById(input.getLong("formaPagamento")));

        if (input.getString(ID_CONTA_BANCARIA) != null
                && !input.getString(ID_CONTA_BANCARIA).isEmpty()
                && input.getLong(ID_CONTA_BANCARIA) > 0) {
            boolean createRegContaBancaria = false;
            if (regContaBancaria == null) {
                regContaBancaria = new RegistroContaBancaria();
                createRegContaBancaria = true;
            }

            regContaBancaria.setDataHora(regCaixa.getDataHora());
            regContaBancaria.setValor(regCaixa.getValor());
            regContaBancaria.setDescricao(regCaixa.getDescricao());
            regContaBancaria.setFormaDePagamento(regCaixa.getFormaDePagamento());
            regContaBancaria.setRetirada(Boolean.TRUE);
            regContaBancaria.setEntrada(Boolean.FALSE);
            regContaBancaria.setEdit(Boolean.FALSE);
            regContaBancaria.setRegistroCaixa(Long.valueOf(-1));//Para nao ser editável
            regContaBancaria.setContaBancaria(ServiceLocator.getContaBancariaService().readById(input.getLong("idContaBancaria")));
            regContaBancaria.setUsuarioRegistrou((Usuario) getUserSession());

            if (createRegContaBancaria) {
                ServiceLocator.getRegistroContaBancariaService().create(regContaBancaria, input.getLong(ID_CONTA_BANCARIA));
            } else {
                ServiceLocator.getRegistroContaBancariaService().update(regContaBancaria, input.getLong(ID_CONTA_BANCARIA));
            }
        } else {
            if (regContaBancaria != null) {
                ServiceLocator.getRegistroContaBancariaService().delete(regContaBancaria.getId());
            }
            regContaBancaria = null;
        }
        regCaixa.setRegistroContaBancaria(regContaBancaria);
        regCaixa.setUsuarioRegistrou((Usuario) getUserSession());

        if (create) {
            ServiceLocator.getRegistroCaixaService().create(regCaixa, input.getLong(ID_FLUXO_CAIXA));
            session.setAttribute("mensagem", "Entrada do Caixa registrada com sucesso!");
        } else {
            ServiceLocator.getRegistroCaixaService().update(regCaixa, input.getLong(ID_FLUXO_CAIXA));
            session.setAttribute("mensagem", "Entrada do Caixa alterada com sucesso!");
        }

        if (regContaBancaria != null) {
            ServiceLocator.getRegistroContaBancariaService().updateRegContaBancariaFk(regContaBancaria.getId(), regCaixa.getId());
        }

        return SUCCESS;
    }
}
