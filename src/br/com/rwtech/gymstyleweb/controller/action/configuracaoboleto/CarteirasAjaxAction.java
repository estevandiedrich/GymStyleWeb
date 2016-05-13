package br.com.rwtech.gymstyleweb.controller.action.configuracaoboleto;

import br.com.rwtech.gymstyleweb.controller.action.configuracaoboleto.ConfigurarBoletoAction.Banco;
import java.util.HashMap;
import java.util.Map;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeCobranca;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class CarteirasAjaxAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        output.setValue("carteiras", getCarteiras(input.getString("banco")));
        return SUCCESS;
    }

    public static Map<String, String> getCarteiras(String banco) {
        if (banco.equalsIgnoreCase(Banco.BANCO_DO_BRASIL)) { 
            return getCarteirasBancoBrasil();
        } else if (banco.equalsIgnoreCase(Banco.BANCO_SANTANDER)) {
            return getCarteirasBancoSantander();
        } else if (banco.equalsIgnoreCase(Banco.CAIXA_ECONOMICA_FEDERAL)) {
            return getCarteirasCaixaEconomica();
        } else if (banco.equalsIgnoreCase(Banco.BANCO_BRADESCO)) {
            return getCarteirasBancoBradesco();
        } else if (banco.equalsIgnoreCase(Banco.BANCO_ITAU)) {
            return getCarteirasBancoItau();
        } else if (banco.equalsIgnoreCase(Banco.HSBC)) {
            return getCarteirasHsbc();
        } else if (banco.equalsIgnoreCase(Banco.UNIBANCO)) {
            return getCarteirasUnibanco();
//        } else if (banco.equalsIgnoreCase(Banco.BANCO_DO_NORDESTE_DO_BRASIL)) {
//        } else if (banco.equalsIgnoreCase(Banco.BANCO_DO_ESTADO_DO_ESPIRITO_SANTO)) {
//        } else if (banco.equalsIgnoreCase(Banco.BANCO_DO_ESTADO_DO_RIO_GRANDE_DO_SUL)) {
//        } else if (banco.equalsIgnoreCase(Banco.BANCO_INTEMEDIUM)) {
//        } else if (banco.equalsIgnoreCase(Banco.NOSSA_CAIXA)) {
//        } else if (banco.equalsIgnoreCase(Banco.BANCO_ABN_AMRO_REAL)) {
//        } else if (banco.equalsIgnoreCase(Banco.MERCANTIL_DO_BRASIL)) {
//        } else if (banco.equalsIgnoreCase(Banco.BANCO_SAFRA)) {
//        } else if (banco.equalsIgnoreCase(Banco.BANCO_RURAL)) {
//        } else if (banco.equalsIgnoreCase(Banco.BANCO_SICREDI)) {
//        } else if (banco.equalsIgnoreCase(Banco.BANCOOB)) {
        } else {
            return new HashMap<String, String>();
        }
    }

    public static Map<String, String> getCarteirasBancoSantander() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("101", "Carteira 101 - Cobrança Simples Rápida com Registro - RCR");
        map.put("102", "Carteira 102 - Cobrança Simples Sem Registro - CSR");
        map.put("201", "Carteira 201 - Cobrança Penhor Rápida com Registro - RCR");
//        map.put("","Carteira COB - Cobrança simples, sem registro (antiga)");
//        map.put("","Carteira CSR - Cobrança Simples sem Registro (antiga)");
//        map.put("","Carteira ECR - Cobrança Simples com Registro (antiga)");
        return map;
    }

    public static Map<String, String> getCarteirasBancoBradesco() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("02", "Carteira 02");
        map.put("03", "Carteira 03");
        map.put("04", "04 - Pré-Impressos");
        map.put("05", "05 - Recebimento Programado Bradesco - RPB");
        map.put("06", "06 - Sem Registro");
        map.put("07", "Carteira 07");
        map.put("09", "09 - Com Registro");
        map.put("12", "12 - Carteira 12");
        map.put("16", "16 - Sem Registro com Protesto");
        map.put("17", "Carteira 17");
        map.put("19", "19 - Com Registro");
        map.put("22", "22 - Sem Registro - Pagável somente no Bradesco");
        return map;
    }

    public static Map<String, String> getCarteirasCaixaEconomica() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("01", "Carteira 01");
        map.put("01", "Carteira 01, Nosso Número de 18 dígitos - Cobrança simples");
        map.put("02", "Carteira 02 - Cobrança sem Registro - SIGCB");
//Carteira Banco Rural correspondente Caixa Econômica Federal - Cobrança com registro
//Carteira CR - Cobrança rápida
//Carteira CS - Cobrança simples
//Carteira RG - Cobrança com Registro - SIGCB
//Carteira SR - Cobrança sem registro
//Carteira SR, Nosso número 16 dígitos - Cobrança sem registro
        return map;
    }

    public static Map<String, String> getCarteirasBancoItau() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("102", "102 - Sem Registro Com Emissão Integral - Carnê");
        map.put("103", "103 - Sem Registro Com Emissão/Entrega - Carnê");
        map.put("104", "104 - Escritural Eletrônica - Carnê");
        map.put("105", "105 - Escritural Eletrônica - Dólar - Carnê");
        map.put("106", "106 - S/Registro C/Emissão/Entrega-15 dígitos-Carnê");
        map.put("107", "107 - S / Registro C / Emissão Integral - 15 Posições - Carnê");
        map.put("108", "108 - Direta Eletrônica Emissão Integral - Carnê");
        map.put("109", "109 - Direta Eletrônica Sem Emissão - Simples");
        map.put("110", "110 - Direta Eletrônica Sem Emissão - Simples");
        map.put("111", "111 - Direta Eletrônica Sem Emissão - Simples");
        map.put("112", "112 - Escritural Eletrônica - simples / contratual");
        map.put("113", "113 - Escritural Eletrônica - TR - Carnê");
        map.put("114", "114 - Escritural Eletrônica - Seguros");
        map.put("115", "115 - Carteira 115");
        map.put("120", "120 - S / Registro Emissão Integral C / IOF 2% - Carnê");
        map.put("121", "121 - Direta Eletrônica Emissão Parcial - Simples / Contra");
        map.put("122", "122 - S / Registro S / Emissão 15 Dígitos C/IOF 2%");
        map.put("126", "126 - Direta Eletrônica Sem Emissão - Seguros");
        map.put("129", "129 - S / Registro Emissão Parcial Seguros C/IOF 2%");
        map.put("131", "131 - Direta Eletrônica c / Valor em Aberto");
        map.put("139", "139 - S / Registro Emissão Parcial Seguros C/IOF 4%");
        map.put("140", "140 - S / Registro Emissão Integral C / IOF 4% - Carnê");
        map.put("141", "141 - S / Registro Emissão Integral C / IOF 7% - Carnê");
        map.put("142", "142 - S / Registro S / Emissão 15 DÍGITOS C/IOF 4%");
        map.put("143", "143 - S / Registro S / Emissão 15 DÍGITOS C/IOF 7%");
        map.put("146", "146 - Descrição Não Disponível");
        map.put("147", "147 - Escritural Eletrônica - Dólar");
        map.put("150", "150 - Direta Eletrônica Sem Emissão - Dólar");
        map.put("166", "166 - Escritural Eletrônica - TR");
        map.put("168", "168 - Direta Eletrônica Sem Emissão - TR");
        map.put("169", "169 - S / Registro Emissão Parcial Seguros C/IOF 7%");
        map.put("172", "172 - Sem Registro Com Emissão Integral");
        map.put("173", "173 - Sem Registro Com Emissão/Entrega");
        map.put("174", "174 - Sem Registro Emissão Parcial Com Protesto Borderô");
        map.put("175", "175 - Sem Registro Sem Emissão");
        return map;
    }

    public static Map<String, String> getCarteirasUnibanco() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "Cobranca registrada");
        map.put("2", "Não registrada");
        return map;
    }

    public static Map<String, String> getCarteirasHsbc() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "Cobrança com registro (CSB)");
        map.put("2", "Sem registro(CNR)");
        return map;
    }

    public static Map<String, String> getCarteirasBancoBrasil() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("11", "11 - Cobrança Simples - Com Registro");
        map.put("11-4", "11-4 - Cobrança Simples - C/Registro Convênio 4 dígitos");
        map.put("12", "12 - Cobrança Indexada - Com Registro");
        map.put("12-4", "12-4 - Cobrança Indexada - C/Registro Convênio 4 dígitos");
        map.put("12-7", "12-7 - Cobrança Indexada - C/Registro Convênio 7 dígitos");
        map.put("15", "15 - Cobrança de Prêmios de Seguro - Com Registro");
        map.put("15-4", "15-4 - Cob.Prêmios Seguro - C/Registro Convênio 4 dígitos");
        map.put("16", "16 - Cobranca Simples");
        map.put("16-14", "16-17 - Cobranca Simples - Nosso Número 17 Dígitos");
        map.put("16-4", "16-4 - Cobranca Simples - Convênio 4 dígitos");
        map.put("17", "17 - Cobranca Direta Especial - Com Registro");
        map.put("17-4", "17-4 - Cob. Direta Esp. - C/Registro Convênio 4 dígitos");
        map.put("17-7", "17-7 - Cob. Direta Esp. - C/Registro Convênio 7 dígitos");
        map.put("18", "18 - Cobranca Simples - Nosso Número 11 Dígitos");
        map.put("18-17", "18-17 - Cobranca Simples - Nosso Número 17 Dígitos");
        map.put("18-4", "18-4 - Cobranca Simples - Convênio 4 dígitos");
        map.put("18-7", "18-7 - Cobranca Simples - Convênio 7 dígitos");
        map.put("31", "31 - Cobrança Caucionada/Vinculada - Com Registro");
        map.put("31-4", "31-4 - Cob.Cauc./Vinc. - C/Registro Convênio 4 dígitos");
        map.put("51", "51 - Cobrança Descontada - Com Registro");
        map.put("51-4", "51-4 - Cob. Descontada - C/Registro Convênio 4 dígitos");
        return map;
    }
}
