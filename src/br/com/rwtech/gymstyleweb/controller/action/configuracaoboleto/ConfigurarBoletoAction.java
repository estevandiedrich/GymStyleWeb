/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rwtech.gymstyleweb.controller.action.configuracaoboleto;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.ConfiguracaoBoleto;
import java.util.HashMap;
import java.util.Map;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.mentawai.core.BaseAction;
import org.mentawai.core.Input;
import org.mentawai.core.Output;

/**
 *
 * @author Software1
 */
public class ConfigurarBoletoAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;

        if (isPost()) {
            Long id = input.getLong("id");

            ConfiguracaoBoleto boleto = (ConfiguracaoBoleto) input.getValue("VOconfiguracaoboleto");
            //BancosSuportados.valueOf(input.getString("banco"));

            if (input.getValue("bancoSelect") != null && !input.getString("bancoSelect").isEmpty()) {
                boleto.setBanco(input.getString("bancoSelect"));
            }
            if (input.getValue("aceite") != null && !input.getString("aceite").isEmpty()) {
                boleto.setTituloAceite(Titulo.EnumAceite.valueOf(input.getString("aceite")));
            }
            if (input.getValue("tipoDocumento") != null && !input.getString("tipoDocumento").isEmpty()) {
                boleto.setTituloTipoDocumento(TipoDeTitulo.valueOf(input.getString("tipoDocumento")));
            }
//            boleto.setTituloValorCobrado(Validador.getMoney(input.getString("valor11")));
            if (id != null && id > 0) {
                ServiceLocator.getConfiguracaoBoletoService().update(boleto);
            } else {
                ServiceLocator.getConfiguracaoBoletoService().create(boleto);
            }
            consequence = SUCCESS;
        } else {
            preload(output, input, false);
        }
        return consequence;
    }

    public static void preload(Output output, Input input, Boolean isValidation) {
        output.setValue("bancos", bancos());
        output.setValue("tipoDeTitulos", tipoDeTitulos());
        output.setValue("aceite", "A");

        Long id = input.getLong("id");
        Map<String, String> carteiras = null;
        if (id != null && id > 0) {
            if (!isValidation) {
                ConfiguracaoBoleto pojo = ServiceLocator.getConfiguracaoBoletoService().read();
                output.setValue("pojo", pojo);
                if (pojo.getBanco() != null) {
                    output.setValue("bancoSelect", pojo.getBanco().toString());
                    carteiras = CarteirasAjaxAction.getCarteiras(pojo.getBanco().toString());
                }
                if (pojo.getTituloTipoDocumento() != null) {
                    output.setValue("tipoDocumento", pojo.getTituloTipoDocumento().toString());
                }
                if (pojo.getTituloAceite() != null) {
                    output.setValue("aceite", pojo.getTituloAceite().toString());
                }
            } else {
                if (input.getValue("bancoSelect") != null && !input.getString("bancoSelect").isEmpty()) {
                    carteiras = CarteirasAjaxAction.getCarteiras(input.getString("bancoSelect"));
                }
            }
        }
        if (carteiras == null) {
            carteiras = new HashMap<String, String>();
        }
        output.setValue("carteiras", carteiras);
    }

    public static Map<String, String> bancos() {
        Map<String, String> map = new HashMap<String, String>();
//        for (BancosSuportados b : BancosSuportados.values()) {
        ///          map.put(b.toString(), b.create().getNome());
        //   }
        map.put(Banco.BANCO_DO_BRASIL, "BANCO DO BRASIL S.A.");
        map.put(Banco.BANCO_SANTANDER, "BANCO SANTANDER (BRASIL) S. A.");
        map.put(Banco.CAIXA_ECONOMICA_FEDERAL, "CAIXA ECONOMICA FEDERAL");
        map.put(Banco.BANCO_BRADESCO, "BANCO BRADESCO S.A.");
        map.put(Banco.BANCO_ITAU, "BANCO ITAÚ S.A.");
        map.put(Banco.HSBC, "HSBC BANK BRASIL S.A.");
        map.put(Banco.UNIBANCO, "UNIBANCO-UNIAO DE BANCOS BRASILEIROS S.A.");
        //map.put(Banco.BANCO_DO_NORDESTE_DO_BRASIL, "BANCO DO NORDESTE DO BRASIL S.A.");
        //map.put(Banco.BANCO_DO_ESTADO_DO_ESPIRITO_SANTO, "BANCO DO ESTADO DO ESPIRITO SANTO S.A.");
        //map.put(Banco.BANCO_DO_ESTADO_DO_RIO_GRANDE_DO_SUL, "BANCO DO ESTADO DO RIO GRANDE DO SUL S.A.");
        //map.put(Banco.BANCO_INTEMEDIUM, "BANCO INTERMEDIUM S.A.");
        //map.put(Banco.NOSSA_CAIXA, "BANCO NOSSA CAIXA S.A.");
        //map.put(Banco.BANCO_ABN_AMRO_REAL, "BANCO ABN AMRO REAL S.A.");
        //map.put(Banco.MERCANTIL_DO_BRASIL, "BANCO MERCANTIL DO BRASIL S.A.");
        //map.put(Banco.BANCO_SAFRA, "BANCO SAFRA S.A.");
        //map.put(Banco.BANCO_RURAL, "BANCO RURAL S.A.");
        //map.put(Banco.BANCO_SICREDI, "BANCO COOPERATIVO SICREDI S.A.");
        //map.put(Banco.BANCOOB, "BANCO COOPERATIVO DO BRASIL S.A. - BANCOOB");

        return map;
    }

    public static class Banco {

        public static String BANCO_DO_BRASIL = "BANCO_DO_BRASIL";
        public static String BANCO_DO_NORDESTE_DO_BRASIL = "BANCO_DO_NORDESTE_DO_BRASIL";
        public static String BANCO_DO_ESTADO_DO_ESPIRITO_SANTO = "BANCO_DO_ESTADO_DO_ESPIRITO_SANTO";
        public static String BANCO_SANTANDER = "BANCO_SANTANDER";
        public static String BANCO_DO_ESTADO_DO_RIO_GRANDE_DO_SUL = "BANCO_DO_ESTADO_DO_RIO_GRANDE_DO_SUL";
        public static String BANCO_INTEMEDIUM = "BANCO_INTEMEDIUM";
        public static String CAIXA_ECONOMICA_FEDERAL = "CAIXA_ECONOMICA_FEDERAL";
        public static String NOSSA_CAIXA = "NOSSA_CAIXA";
        public static String BANCO_BRADESCO = "BANCO_BRADESCO";
        public static String BANCO_ITAU = "BANCO_ITAU";
        public static String BANCO_ABN_AMRO_REAL = "BANCO_ABN_AMRO_REAL";
        public static String MERCANTIL_DO_BRASIL = "MERCANTIL_DO_BRASIL";
        public static String HSBC = "HSBC";
        public static String UNIBANCO = "UNIBANCO";
        public static String BANCO_SAFRA = "BANCO_SAFRA";
        public static String BANCO_RURAL = "BANCO_RURAL";
        public static String BANCO_SICREDI = "BANCO_SICREDI";
        public static String BANCOOB = "BANCOOB";
    }

    public static Map<String, String> tipoDeTitulos() {
        Map<String, String> map = new HashMap<String, String>();
        for (TipoDeTitulo t : TipoDeTitulo.values()) {
            map.put(t.toString(), t.getSigla());
        }
        return map;
    }
}
