package br.com.rwtech.gymstyleweb.controller.action.dispositivo.ajax;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Dispositivo;
import br.com.rwtech.gymstylecore.model.pojo.Requisicao;
import br.com.rwtech.gymstylecore.model.pojo.Resposta;
import br.com.rwtech.gymstylecore.model.pojo.tipos.ModoAcesso;
import br.com.rwtech.gymstylecore.model.util.Contador;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class ReenviarConfigurarDispositivoAjaxAction extends BaseAction {

    public static Integer CONT = 20;

    @Override
    public String execute() throws Exception {
        String consequence = Ac.SINCRONIZANDO;
        Long idRequisicaoSentido = input.getLong("idRequisicaoConfigurar");
        //na primeira vez passa se direto pq o idRequisicao esta no out put
        int cont = 0;
        if ((idRequisicaoSentido != null && idRequisicaoSentido != -1)) {

            cont = input.getInt("contadorConfigurar");

            if (cont == -10) {//reenviar requisicao
                Requisicao req = ServiceLocator.getRequisicaoService().readById(idRequisicaoSentido);
                req.setStatus(Boolean.FALSE);
                ServiceLocator.getRequisicaoService().create(req);
                idRequisicaoSentido = req.getId();
                cont = 1;
            }
            Resposta pojo = ServiceLocator.getRespostaService().readByDestino(idRequisicaoSentido);
            if (pojo != null) {
                if (pojo.getMensagemErro() == null || pojo.getMensagemErro().isEmpty()) {
                    consequence = SUCCESS;
                    Long idDispositivo = input.getLong("idDispositivo");
                    Dispositivo dispositivo = ServiceLocator.getDispositivoService().readById(idDispositivo);
                    if (dispositivo != null) {
                        Requisicao req = ServiceLocator.getRequisicaoService().readById(idRequisicaoSentido);
                        Long param = (Long) req.getParametro();
                        //Seta os parametros da requisicao por referencia
                        ModoAcesso.setRequisicaoByParametro(dispositivo, param);
                        ServiceLocator.getDispositivoService().update(dispositivo);
                    }
                    cont = CONT + 1;//para finalizar o contador
                } else {
                    output.setValue("mensagem", pojo.getMensagemErro());
                }
            } else {
                consequence = Ac.SINCRONIZANDO;
                output.setValue("idRequisicaoConfigurar", idRequisicaoSentido);
                if (cont > CONT) {
                    output.setValue("mensagemSentido", "TEMPO ESGOTADO");
                }
            }
            if (!consequence.equalsIgnoreCase(SUCCESS)) {
                Contador contador = Contador.getInstance();
                contador.run();
                cont++;//incrementa o cont
            }
            if (cont == (CONT + 1) && !consequence.equalsIgnoreCase(SUCCESS)) {
                consequence = ERROR;
            }
        }
        output.setValue("contadorConfigurar", cont);//a primeira vez passa-se aqui
        return consequence;
    }
}