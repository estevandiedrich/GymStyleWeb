package br.com.rwtech.gymstyleweb.controller.action.dispositivo;

import br.com.rwtech.gymstylecore.model.ConnectionManagerLog;
import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Dispositivo;
import br.com.rwtech.gymstylecore.model.pojo.Log;
import br.com.rwtech.gymstylecore.model.pojo.Requisicao;
import br.com.rwtech.gymstylecore.model.pojo.Resposta;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.pojo.tipos.ModoAcesso;
import br.com.rwtech.gymstylecore.model.pojo.tipos.TipoRequisicaoResposta;
import br.com.rwtech.gymstylecore.model.util.Contador;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.log.LogAction;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class DispositivoRequisicaoAction extends BaseAction {

    public static String SINCRONIZANDO = "SINCRONIZANDO";
    public static Integer CONT = 20;

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;
        Long idRequisicao = null;
        if (input.getValue("idRequisicao") != null
                && !input.getString("idRequisicao").equalsIgnoreCase("undefined")) {
            idRequisicao = input.getLong("idRequisicao");
        }
        //na primeira vez passa se direto pq o idRequisicao esta no output
        int cont = 0;
        //entra aqui para tratar a configuração da catraca
        if ((idRequisicao != null && idRequisicao != -1)) {
            output.setValue("idDispositivo", input.getString("idDispositivo"));
            cont = input.getInt("contador");

            Requisicao requisicao = null;
            if (cont == -10) {//reenviar requisicao
                requisicao = ServiceLocator.getRequisicaoService().readById(idRequisicao);
                ServiceLocator.getRequisicaoService().create(requisicao);
                idRequisicao = requisicao.getId();
                cont = 1;
            }
            Boolean reqConfigurar = false;
            if (idRequisicao == null || idRequisicao == -1) {
                reqConfigurar = true;
            } else {
                Resposta pojo = ServiceLocator.getRespostaService().readByDestino(idRequisicao);
                requisicao = ServiceLocator.getRequisicaoService().readById(idRequisicao);
                if (pojo != null) {
                    if (pojo.getMensagemErro() == null || pojo.getMensagemErro().isEmpty()) {
                        reqConfigurar = true;
                        Long idDispositivo = input.getLong("idDispositivo");
                        Dispositivo dispositivo = ServiceLocator.getDispositivoService().readById(idDispositivo);
                        if (dispositivo != null) {
                            Long param = (Long) requisicao.getParametro();
                            ModoAcesso.setRequisicaoByParametro(dispositivo, param);
                            
                            ServiceLocator.getDispositivoService().update(dispositivo);
                        }
                    } else {
                        output.setValue("mensagem", pojo.getMensagemErro());
                    }
                } else {
                    consequence = SINCRONIZANDO;
                    output.setValue("idRequisicao", idRequisicao);
                    if (cont > CONT) {
                        output.setValue("mensagemSentido", "TEMPO ESGOTADO");
                    }
                }
            }
            if (!reqConfigurar) {
                Contador contador = Contador.getInstance();
                contador.run();
                cont++;//incrementa o cont
            }

            if (reqConfigurar) {
                consequence = SUCCESS;
                //Gera o log de sincronização aqui
                ServiceLocator.getLogService().create(ConnectionManagerLog.getInstance().getConnection(),
                        new Log(LogAction.getMap(Ac.CONFIGURAR_CATRACA), LogAction.ATUALIZOU, (Usuario) getUserSession()));
            }
            if (cont == (CONT + 1)) {
                consequence = ERROR;
            }
        } else {
            //-----------------------------------------------------------------------------------------------------------------------------------------
            Long id = Long.parseLong(input.getString("id"));//id dispositivo
            output.setValue("idDispositivo", id);
            Long imprime = input.getLong("imprime");

            int entradaDirEsq = input.getInt("entradaDirEsq");
            int modoAcesso = input.getInt("modoAcessoPojo");
//            if (entrada != entradaDirEsq) {
            Requisicao reqSentidoEntrada = new Requisicao(
                    ModoAcesso.getValorToRequisicao(((entradaDirEsq != 0) ? true : false),
                    ((imprime != 0) ? true : false),
                    ModoAcesso.getValorToRequisicao(ModoAcesso.getTipo(modoAcesso))),
                    null, Boolean.FALSE, id, (Usuario) getUserSession(), TipoRequisicaoResposta.CONFIGURAR_CATRACA);
            ServiceLocator.getRequisicaoService().create(reqSentidoEntrada);
            if (reqSentidoEntrada != null && reqSentidoEntrada.getId() != null) {
                output.setValue("idRequisicao", reqSentidoEntrada.getId());
                cont = 1;
            } else {
                cont = 5;
            }
        }
        output.setValue("contador", cont);//a primeira vez passa-se aqui
        return consequence;
    }
}
