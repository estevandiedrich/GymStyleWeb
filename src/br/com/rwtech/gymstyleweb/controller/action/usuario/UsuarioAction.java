package br.com.rwtech.gymstyleweb.controller.action.usuario;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Dispositivo;
import br.com.rwtech.gymstylecore.model.pojo.ImpressaoDigital;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.mentawai.core.ApplicationManager;
import org.mentawai.core.BaseAction;
import org.mentawai.core.Input;
import org.mentawai.core.Output;

public class UsuarioAction extends BaseAction {

    public static String ATUALIZAR = "atualizar";
    public static String INFORMACAO = "informacao";
    public static String IDENTIFICACAO = "identificacao";
    public static String PLANO = "plano";
    public static String INESPERADO = "inesperado";
    public static String PAGAMENTOS = "pagamentos";
    public static String FOTO = "foto";
    public static String ACAO = "acao";
    public static String NAVEGADOR = "navegador";

    public static void preload(Output output, Input input) {

        setSelecionado(output);
        output.setValue("url", ApplicationManager.getRealPath());

        output.setValue("sexos", getSexos());
        output.setValue("estados", getEstados());
        output.setValue("opcoes", getOpcoes());
        output.setValue("opcao", "h");
        output.setValue("dispositivos", getDispositivos());
        output.setValue("redesSociais", ServiceLocator.getRedeSocialService().readList());
        output.setValue("estadosCivis", ServiceLocator.getEstadoCivilService().readList());

        output.setValue("mascaraTel", getMascara());
        output.setValue("mascaraCel", getMascara());

        Long id = (Long) output.getValue("id");
        if (id != null && id > 0) {
            Usuario pojo = ServiceLocator.getUsuarioService().readById(id);
            if (pojo != null) {
                output.setValue("matricula", id);
                setSelecionado(output, Ac.USUARIO_READ);
                output.setValue("pojoInfo", pojo);//pojo é para aparecer as informações do usuário em cima na tela
                output.setValue("infoCpf", pojo.getCpf());
                output.setValue("loginUsuario", pojo.getLogin());
                output.setValue("loginAux", pojo.getLogin());

                if (pojo.getDataNascimento() != null) {
                    output.setValue("dataNascimentoFormat", pojo.getDataNascimento().getTime());
                }
                if(pojo.getDataCadastro() != null) {
                	output.setValue("dataCadastroFormat", pojo.getDataCadastro().getTime());
                }
                if (input.getValue("idTipoUsuario") == null) {
                    if (pojo.getTipoUsuario() != null) {
                        output.setValue("idTipoUsuario", pojo.getTipoUsuario());
                    }
                }

                if (input.getValue("login") == null) {
                    if (pojo.getLogin() == null || pojo.getLogin().isEmpty()) {
                        output.setValue("login", "");
                        pojo.setLogin("");
                    }
                    output.setValue("senha", "");
                    pojo.setSenha("");
                }

                if (pojo.getImpressoesDigitais() != null) {
                    output.setValue("digitais", pojo.getImpressoesDigitais());
                } else {
                    output.setValue("digitais", new ArrayList<ImpressaoDigital>());
                }

                output.setValue("vincularPlanoUsuario", true);


                Plano plano = ServiceLocator.getUsuarioPlanoService().readUltimoPlano(pojo.getId());
                if (plano != null) {
                    if (!plano.getCancelado()) {
                        if (plano.getFinalizado()) {
                            output.setValue("planoQuitado", "Plano Quitado.");
                        }
                        if (!plano.getFinalizado()) {
                            output.setValue("vincularPlanoUsuario", false);
                            output.setValue("infoPlano", plano);
                        }
                    }
                }
            }
        } else {
            output.setValue("matricula", ServiceLocator.getUsuarioService().readNextMatricula());
        }
    }

    public static void setSelecionado(Output output) {
        setSelecionado(output, "usuarioCreate");
    }

    public static void setSelecionado(Output output, String ac) {
        output.setValue("selecionado", ac);
    }

    public static Map<String, String> getOpcoes() {
        Map<String, String> mapa = new LinkedHashMap<String, String>();
        mapa.put("H", "Hamster");
        mapa.put("C", "Catraca");
        return mapa;
    }

    public static Map<String, String> getMascara() {
        Map<String, String> mapa = new LinkedHashMap<String, String>();
        mapa.put("sim", "Habilitar Máscara de 11 campos");
        return mapa;
    }

    public static Map<Long, String> getDispositivos() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("criterioOnline", "true");
        List<Dispositivo> lista = ServiceLocator.getDispositivoService().readByCriteria(map);
        Map<Long, String> mapa = new LinkedHashMap<Long, String>();
        for (Dispositivo pojo : lista) {
            if (pojo.getDispositivo() == null || pojo.getDispositivo().isEmpty()) {
                pojo.setDispositivo(pojo.getEnderecoIp());
            }
            mapa.put(pojo.getId(), pojo.getDispositivo());
        }
        return mapa;
    }

    public static Map<String, String> getSexos() {
        Map<String, String> mapa = new LinkedHashMap<String, String>();
        mapa.put("M", "Masculino");
        mapa.put("F", "Feminino");
        return mapa;
    }

    public static Map<String, String> getEstados() {
        Map<String, String> mapa = new LinkedHashMap<String, String>();
        mapa.put("AC", "Acre – AC");
        mapa.put("AL", "Alagoas – AL");
        mapa.put("AP", "Amapá – AP");
        mapa.put("AM", "Amazonas – AM");
        mapa.put("BA", "Bahia – BA");
        mapa.put("CA", "Ceará – CE");
        mapa.put("DF", "Distrito Federal – DF");
        mapa.put("ES", "Espírito Santo – ES");
        mapa.put("GO", "Goiás – GO");
        mapa.put("MA", "Maranhão – MA");
        mapa.put("MT", "Mato Grosso – MT");
        mapa.put("MS", "Mato Grosso do Sul – MS");
        mapa.put("MG", "Minas Gerais – MG");
        mapa.put("PA", "Pará – PA");
        mapa.put("PB", "Paraíba – PB");
        mapa.put("BR", "Paraná – PR");
        mapa.put("PE", "Pernambuco – PE");
        mapa.put("PI", "Piauí – PI");
        mapa.put("RR", "Roraima – RR");
        mapa.put("RO", "Rondônia – RO");
        mapa.put("RJ", "Rio de Janeiro – RJ");
        mapa.put("RN", "Rio Grande do Norte – RN");
        mapa.put("RS", "Rio Grande do Sul – RS");
        mapa.put("SC", "Santa Catarina – SC");
        mapa.put("SP", "São Paulo – SP");
        mapa.put("SE", "Sergipe – SE");
        mapa.put("TO", "Tocantins – TO");
        return mapa;
    }
}