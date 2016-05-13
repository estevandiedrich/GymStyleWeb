package br.com.rwtech.gymstyleweb.controller.action.dispositivo;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Requisicao;
import br.com.rwtech.gymstylecore.model.pojo.Resposta;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.pojo.tipos.TipoRequisicaoResposta;
import br.com.rwtech.gymstylecore.model.util.Contador;
import java.util.Calendar;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class BuscarEventosDispositivoAction extends BaseAction {

    private static int CONT = 80;//equivalente a 20 segundos
    @Override
    public String execute() throws Exception {
        String consequence = SHOW;
        Long id = input.getLong("id");
        if (isPost()) {
            consequence = SUCCESS;
            Requisicao requisicao = new Requisicao();
            requisicao.setDataHora(Calendar.getInstance());
            requisicao.setDestino(id);
            requisicao.setStatus(Boolean.FALSE);
            requisicao.setTipo(TipoRequisicaoResposta.VERIFICAR_EVENTOS_OFFLINE);
            requisicao.setOperador((Usuario) getUserSession());
            ServiceLocator.getRequisicaoService().create(requisicao);
            int i = 0;
            session.setAttribute("mensagem", "Tempo de busca excedido!");
            while (i < CONT) {
                Resposta pojo = ServiceLocator.getRespostaService().readByDestino(requisicao.getId());
                if (pojo != null) {
                    if (pojo.getMensagemErro() == null || pojo.getMensagemErro().isEmpty()) {
                        consequence = SUCCESS;
                        session.setAttribute("mensagem", "Eventos importados com sucesso!");
                    } else {
                        session.setAttribute("mensagem", "Eventos não encontrados!");
                        consequence = ERROR;
                    }
                    break;
                }
                Contador contador = Contador.getInstance();
                contador.run();
                i++;
            }
        }
        if (id == null) {
            consequence = ERROR;
        }
        return consequence;
    }
}