package br.com.rwtech.gymstyleweb.controller.action.digital;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Dedo;
import br.com.rwtech.gymstylecore.model.pojo.ImpressaoDigital;
import br.com.rwtech.gymstylecore.model.pojo.Requisicao;
import br.com.rwtech.gymstylecore.model.pojo.Resposta;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.pojo.tipos.TipoRequisicaoResposta;
import br.com.rwtech.gymstylecore.model.util.Contador;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class ImpressaoDigitalAjaxAction extends BaseAction {

    // O Tempo estimado de aguardar da web é de no máximo 34 segundos
    // 30 sgundos descritos abaixo
    // 4 Segundos aguardado a resposta de exclusão de início de captura
    // Senao respondeu a exclusão, ja encerra a comunicação
    private static float TEMPO = 120;// 100 * 0,250 = 25 segundos

    @Override
    public String execute() throws Exception {
        String id = input.getString("id");
        Map<String, Object> filtro = new HashMap<String, Object>();
        filtro.put("criterioUsuario", id);
        output.setValue("digitais", ServiceLocator.getImpressaoDigitalEspelhoService().readByCriteria(filtro));
        return SUCCESS;
    }

    public String read() {
        String id = input.getString("id");
        Map<String, Object> filtro = new HashMap<String, Object>();
        filtro.put("criterioUsuario", id);
        List<ImpressaoDigital> digitais = ServiceLocator.getImpressaoDigitalEspelhoService().readByCriteria(filtro);
        output.setValue("digitais", digitais);

        Boolean dedoDireito = false;
        Boolean dedoEsquerdo = false;
        for (ImpressaoDigital dig : digitais) {
            if (!dedoDireito) {
                int valor = Dedo.getValorDedo(dig.getDedo().getDedo());
                if (valor >= 1 && valor <= 5) {
                    dedoDireito = true;
                }
            }
            if (!dedoEsquerdo) {
                int valor = Dedo.getValorDedo(dig.getDedo().getDedo());
                if (valor >= 6 && valor <= 10) {
                    dedoEsquerdo = true;
                }
            }
        }
        String errorsDigitais = "";
        int tam = digitais.size();
        if (tam != 2 && tam != 0) {
            errorsDigitais = "Cadastre até duas digitais" + ValidationMessage.getImageCampoObrigatorio();
        } else if (tam == 2) {
            if ((!dedoDireito && dedoEsquerdo) || (dedoDireito && !dedoEsquerdo)) {
                errorsDigitais = "Informe uma digital de cada mão" + ValidationMessage.getImageCampoObrigatorio();
            }
        }
        output.setValue("mensagemDigitais", errorsDigitais);
        return SUCCESS;
    }

    public String create() {
        String consequence = ERROR;
        Long idUsuario = input.getLong("id");
        Long idDispositivo = input.getLong("idDispositivo");
        Long idDedo = input.getLong("idDedo");
        Usuario operador = (Usuario) getUserSession();
        if (idDispositivo != null) {
            //Enviar requisição de exclusão na catraca antes de enviar o pedido de digital, para não ter conflito de digital na catraca
            Requisicao requisicao = new Requisicao(idUsuario, idDedo, Boolean.FALSE, idDispositivo, operador, TipoRequisicaoResposta.EXCLUIR_USUARIO);
            ServiceLocator.getRequisicaoService().create(requisicao);
            //Se o usuário foi excluído da catraca eu seto localmente que o usuário não está sincronizado no Banco de Dados

            /* 
             * Este função aguarda por 4 segundos por uma comunicação de exclusão do aluno
             * Caso não responda é porque a catraca esta fora da rede
             *             
             */
            if (ServiceLocator.getRequisicaoService().verificaRespReqTipoExcluirUsuario(requisicao.getId())) {
                //Altera o status do usuário para falso(Não Sincronizado)
                ServiceLocator.getUsuarioService().updateSincronizado(idUsuario, Boolean.FALSE);
                //}
                //Envia a requisição de pedido de obter digital na catraca
                requisicao = new Requisicao(idUsuario, idDedo, Boolean.FALSE, idDispositivo, operador, TipoRequisicaoResposta.OBTER_IMPRESSAO_DIGITAL);
                ServiceLocator.getRequisicaoService().create(requisicao);

                if (requisicao.getId() != null) {
                    int i = 0;
                    //Tenho que colocar 80 para dar vinte segundo, porque o contador olha no banco a cada 0,250 s
                    while (i < TEMPO) {
                        Resposta pojo = ServiceLocator.getRespostaService().readByDestino(requisicao.getId());
                        if (pojo != null) {
                            if (pojo.getMensagemErro() == null || pojo.getMensagemErro().isEmpty()) {
                                consequence = SUCCESS;
                            } else {
                                output.setValue("error", pojo.getMensagemErro());
                            }
                            break;
                        }
                        Contador contador = Contador.getInstance();
                        contador.run();
                        i++;
                    }
                }
            }
        }
        return consequence;
    }

    public String delete() {
        Long idDelete = Long.valueOf(input.getString("idDelete"));
        ServiceLocator.getImpressaoDigitalEspelhoService().delete(idDelete);
        String idUsuario = input.getString("id");
        Map<String, Object> filtro = new HashMap<String, Object>();
        filtro.put("criterioUsuario", idUsuario);
        output.setValue("digitais", ServiceLocator.getImpressaoDigitalEspelhoService().readByCriteria(filtro));
        ServiceLocator.getUsuarioService().updateSincronizado(Long.valueOf(idUsuario), Boolean.FALSE);

        return SUCCESS;
    }
}