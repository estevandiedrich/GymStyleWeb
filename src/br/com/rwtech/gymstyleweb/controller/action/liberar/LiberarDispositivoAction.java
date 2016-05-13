package br.com.rwtech.gymstyleweb.controller.action.liberar;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Liberar;
import br.com.rwtech.gymstylecore.model.pojo.Requisicao;
import br.com.rwtech.gymstylecore.model.pojo.Resposta;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.pojo.tipos.TipoRequisicaoResposta;
import br.com.rwtech.gymstylecore.model.util.Contador;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.Calendar;
import org.mentawai.core.Output;

/**
 *
 * @author Éder Faria
 */
public class LiberarDispositivoAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;
        if (isPost()) {
            Requisicao req = new Requisicao();
            req.setDataHora(Calendar.getInstance());
            Long idDispositivo = input.getLong("dispositivo");
            if (idDispositivo != null && idDispositivo != -1) {
                Liberar liberar = new Liberar();
                liberar.setOperador((Usuario) getUserSession());
                liberar.setDispositivo(ServiceLocator.getDispositivoService().readById(idDispositivo));
                liberar.setJustificativa(input.getString("justificativa"));
                Calendar horaCriada = Calendar.getInstance();
                liberar.setDataHora(horaCriada);
//                Calendar horaFim = Calendar.getInstance();
//                horaFim.add(Calendar.SECOND, 10);//Adiciona 10 segundos de período de 

                req.setDestino(idDispositivo);
                req.setOperador((Usuario) getUserSession());
                req.setStatus(Boolean.FALSE);
                req.setTipo(TipoRequisicaoResposta.LIBERAR_UM_ACESSO);
                ServiceLocator.getRequisicaoService().create(req);
//                Map<String, Object> mapa = new HashMap<String, Object>();
//                mapa.put("criterioDataInicio", horaCriada.getTime().toString());
//                mapa.put("criterioDataFim", horaFim.getTime().toString());
//                mapa.put("criterioCatraca", idDispositivo.toString());
                int cont = 32;//32 * 0,250 = 8 segundos
                //Na catraca esta com 6 segundos de espera de aguardando passagem
                while (cont > 0) {
//                    //Verifica se existe algum evento de Passagem liberada na catraca naquele período
//                    List<Evento> lista = ServiceLocator.getEventoService().readByCriteria(mapa);
//                    if (lista != null && !lista.isEmpty()) {
//                        ServiceLocator.getLiberarService().create(liberar);
//                        session.setAttribute("mensagem", "Catraca Liberada com Sucesso!");
//                        break;
//                    }
                    Resposta resp = ServiceLocator.getRespostaService().readByDestino(req.getId());
                    if (resp != null) {
                        if (resp.getMensagemErro() != null && !resp.getMensagemErro().isEmpty()) {
                            session.setAttribute("mensagem", "Catraca em Uso, não foi possível Liberar!");
                        } else {
                            ServiceLocator.getLiberarService().create(liberar);
                            session.setAttribute("mensagem", "Catraca Liberada com Sucesso!");
                        }
                        break;
                    }
                    Contador.getInstance().run();
                    cont--;
                }
                consequence = SUCCESS;

                if (cont == 0) {
                    session.setAttribute("mensagem", "Catraca não respondeu, não foi possível liberar!");
                }
            } else {
                session.setAttribute("mensagem", "Catraca não respondeu, não foi possível liberar!");
            }
        } else {
            preload(output);
            setMensagem();
        }
        return consequence;
    }

    public static void preload(Output output) {
        output.setValue("dispositivos", ServiceLocator.getDispositivoService().readList());
    }
}