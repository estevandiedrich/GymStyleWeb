package br.com.rwtech.gymstyleweb.controller.action.contabancaria.registros;

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
public class EntradaRetiradaContaBancariaAjaxAction extends BaseAction {

    private String ID_CONTA_BANCARIA = "idContaBancaria";
    private String ID_REGISTRO_ENTRADA = "idRegistroEntrada";

    @Override
    public String execute() throws Exception {

        Usuario userSession = (Usuario) getUserSession();

        RegistroContaBancaria regContaBancaria = null;
        Long idCaixaAberto = ServiceLocator.getFluxoCaixaService().idCaixaAberto();
        boolean create = true;
        if (input.getString(ID_REGISTRO_ENTRADA) != null && !input.getString(ID_REGISTRO_ENTRADA).isEmpty()) {
            create = false;
            regContaBancaria = ServiceLocator.getRegistroContaBancariaService().readById(input.getLong(ID_REGISTRO_ENTRADA));
        } else {
            regContaBancaria = new RegistroContaBancaria();
        }

        regContaBancaria.setDataHora(Calendar.getInstance());
        regContaBancaria.setDescricao(input.getString("descricao"));
        regContaBancaria.setEntrada(input.getBoolean("entrada"));
        regContaBancaria.setRetirada(input.getBoolean("retirada"));
        regContaBancaria.setValor(Validador.getMoney(input.getString("valor")));
        regContaBancaria.setFormaDePagamento(ServiceLocator.getFormaPagamentoService().readById(input.getLong("formaPagamento")));
        regContaBancaria.setUsuarioRegistrou(userSession);

        RegistroCaixa regCaixaFk = null;
        if (input.getBoolean("registroCaixa")) {
            if (regContaBancaria.getRegistroCaixa() == null || regContaBancaria.getRegistroCaixa() == 0) {
                regCaixaFk = new RegistroCaixa();
            } else {
                regCaixaFk = ServiceLocator.getRegistroCaixaService().readById(regContaBancaria.getRegistroCaixa());
            }
            regCaixaFk.setUsuarioRegistrou(userSession);
            regCaixaFk.setDataHora(regContaBancaria.getDataHora());
            regCaixaFk.setValor(regContaBancaria.getValor());
            regCaixaFk.setDescricao("TRANFERÊNCIA CONTA BANCARIA");//(registro.getDescricao());
            regCaixaFk.setFormaDePagamento(regContaBancaria.getFormaDePagamento());
            regCaixaFk.setRetirada(Boolean.FALSE);
            regCaixaFk.setEntrada(Boolean.TRUE);
            regCaixaFk.setEdit(false);

            if (idCaixaAberto > 0) {//Caixa tem q estar aberto para registrar
                if (regCaixaFk.getId() == null) {
                    ServiceLocator.getRegistroCaixaService().create(regCaixaFk, idCaixaAberto);
                } else {
                    ServiceLocator.getRegistroCaixaService().update(regCaixaFk, idCaixaAberto);
                }
                regContaBancaria.setRegistroCaixa(regCaixaFk.getId());
            }
        } else {
            if (regContaBancaria.getRegistroCaixa() != null && regContaBancaria.getRegistroCaixa() > 0) {
                ServiceLocator.getRegistroCaixaService().delete(regContaBancaria.getRegistroCaixa());
            }
            regContaBancaria.setRegistroCaixa(null);
        }

        if (create) {
            ServiceLocator.getRegistroContaBancariaService().create(regContaBancaria, input.getLong(ID_CONTA_BANCARIA));
        } else {
            ServiceLocator.getRegistroContaBancariaService().update(regContaBancaria, input.getLong(ID_CONTA_BANCARIA));
        }

        if (regContaBancaria.getRegistroCaixa() != null) {
            ServiceLocator.getRegistroCaixaService().updateRegContaBancariaFk(regContaBancaria.getId(), regCaixaFk.getId());
        }

        return SUCCESS;
    }
}