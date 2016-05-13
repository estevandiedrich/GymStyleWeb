package br.com.rwtech.gymstyleweb.controller.action.requisicao;

import br.com.rwtech.gymstylecore.model.ConnectionManagerLog;
import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Dispositivo;
import br.com.rwtech.gymstylecore.model.pojo.Log;
import br.com.rwtech.gymstylecore.model.pojo.Requisicao;
import br.com.rwtech.gymstylecore.model.pojo.Resposta;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.log.LogAction;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class RequisicaoAtualizarUsuarioAjaxAction extends BaseAction {

    private static int TEMPO = RequisicaoUsuarioAction.TIME;
    private static int TEMPO_DEFAULT = RequisicaoUsuarioAction.TIME;
    
    private static String CATRACAS_DESTINO_FORA_REDE = "Catracas de destinos fora da rede!";
    private String STATUS = "status";
    private boolean ERROR_SINCRONIZACAO = false;
    private boolean SUCESSO_SINCRONIZACAO = true;

    @Override
    public String execute() throws Exception {
        int qtdeDestinos = 0;
        if (input.getValue("tempo") == null) {
            TEMPO = TEMPO_DEFAULT;
        } else {
            TEMPO = input.getInt("tempo");
        }
        String consequence = SHOW;
        String msgErro = "";

        int contador = 0;
        if (input.getValue("idRequisicao") != null && input.getInt("idRequisicao") != -1) {
            contador = input.getInt("contador");
            if (contador > TEMPO) {
                consequence = ERROR;
            }
            ++contador;//a primeira vez passa-se aqui

            Long idRequisicao = input.getLong("idRequisicao");
            Long idUsuario = input.getLong("idUsuario");
            Long idPlanoUsuario = input.getLong("idPlanoUsuario");

            Set<Dispositivo> dispositivos = RequisicaoUsuarioAction.getDispositivosById(idUsuario, idPlanoUsuario);
            Boolean temCatracaForaRede = false;
            for (Dispositivo dispositivo : dispositivos) {
                if (dispositivo.foraDaRede()) {
                    temCatracaForaRede = true;
                    break;
                }
            }

            List<Requisicao> lista = ServiceLocator.getRequisicaoService().readRequisicaoByReferencia(idRequisicao);
            qtdeDestinos = lista.size();
            if (!lista.isEmpty()) {// && !temCatracaForaRede) {
                TEMPO = qtdeDestinos * TEMPO_DEFAULT;

                int qtde = 0;
                boolean temErro = false;

                List<StatusDispositivo> list = new ArrayList<StatusDispositivo>();
                for (Requisicao requisicao : lista) {
                    Resposta resp = ServiceLocator.getRespostaService().readByDestino(requisicao.getId());
                    Dispositivo dispositivo = ServiceLocator.getDispositivoService().readById(requisicao.getDestino());
                    StatusDispositivo pojo = new StatusDispositivo();
                    pojo.setNome((dispositivo.getDispositivo() == null || dispositivo.getDispositivo().isEmpty() ? dispositivo.getEnderecoIp() : dispositivo.getDispositivo()));
                    if (dispositivo.foraDaRede()) {
                        pojo.setMsg(ERROR_SINCRONIZACAO, "Catraca fora da rede!");
                        temErro = true;
                        qtde++;//Incrementa resposta de cara para este dispositivo
                    } else {
                        if (resp == null) {
                            if (!consequence.equalsIgnoreCase(ERROR)) {
                                consequence = SHOW;
                            }
                            pojo.setMsg(null);
                        } else {
                            if (resp.getMensagemErro() != null && !resp.getMensagemErro().isEmpty()
                                    && !resp.getMensagemErro().equalsIgnoreCase("USUARIO_NAO_ENCONTRADO")) {
                                pojo.setMsg(ERROR_SINCRONIZACAO, resp.getMensagemErro());
                                temErro = true;
                            } else {
                                pojo.setMsg(SUCESSO_SINCRONIZACAO);
                            }
                            qtde++;//Conta quantos tiveram resposta
                        }
                    }

                    list.add(pojo);
                }
                if (qtde < lista.size()) {
                    temErro = true;
                    //Se nao teve resposta de todos os dispositivos, tem que setar que houve erro.
                }
                output.setValue("list", list);//Lista com o status de cada dispositivo durante a sincronização

                if (qtde == lista.size() && !temErro) {
                    consequence = SUCCESS;
                    output.setValue("contador", TEMPO + 1);// aqui para o loop acima CONTADOR no manager é q chama essa action
                    ServiceLocator.getUsuarioService().updateSincronizado(idUsuario, Boolean.TRUE);
                    ServiceLocator.getUsuarioService().atualizarCartaoCatraca(idUsuario);

                    Usuario usuario = ServiceLocator.getUsuarioService().readByIdSimple(idUsuario);
                    //Gera o log de sincronização aqui
                    ServiceLocator.getLogService().create(ConnectionManagerLog.getInstance().getConnection(),
                            new Log(LogAction.getMap(Ac.ATUALIZAR_USUARIO) + " - " + usuario.getUsuario(), LogAction.ATUALIZOU, (Usuario) getUserSession()));
                } else if ((qtde == lista.size() || contador >= TEMPO) && temErro) {
                    consequence = ERROR;
                    output.setValue(STATUS, msgErro);
                    for (StatusDispositivo resp : list) {
                        if (resp.ok == null) {
                            resp.setMsg(ERROR_SINCRONIZACAO, "Tempo Esgotado");//
                        }
                    }
                    output.setValue("list", list);//Lista com o status de cada dispositivo durante a sincronização
                }
            } else {
                //Quando os destinos nao sao encotrados,nao encerra, seguro por quase 1 segundo mostrando a msg na tela de sincronizando
                //Tento ler os destinos por 8 tentativas(2s) e depois encerro decrementando
                if (contador == 8) {
                    contador = TEMPO - 2;
                } else if (contador == TEMPO - 2) {
                    contador = TEMPO - 1;
                } else if (contador == TEMPO - 1) {
                    contador = TEMPO;
                }
                output.setValue(STATUS, CATRACAS_DESTINO_FORA_REDE);
                msgErro = CATRACAS_DESTINO_FORA_REDE;
            }
            output.setValue("idRequisicao", idRequisicao);
            output.setValue("idPlanoUsuario", idPlanoUsuario);
            output.setValue("idUsuario", idPlanoUsuario);
            output.setValue("contador", contador);
        } else {
            Long idUsuario = input.getLong("id");
            RequisicaoUsuarioAction.atualizar(idUsuario, output, (Usuario) getUserSession());
            output.setValue("contador", 1);//a primeira vez passa-se aqui
        }
        output.setValue("linha", input.getValue("linha"));

        if (contador == TEMPO && !consequence.equalsIgnoreCase(SUCCESS)) {
            consequence = ERROR;
            output.setValue(STATUS, msgErro);
        }

        if (consequence.equalsIgnoreCase(SHOW)) {
            int percentual = (contador * 100) / TEMPO;
            if (qtdeDestinos != 0) {
                output.setValue("percentual", " " + (percentual) + "%");
            } else {
                qtdeDestinos = 1;
                output.setValue(STATUS, CATRACAS_DESTINO_FORA_REDE);
            }
        }

        if (!consequence.equalsIgnoreCase(SHOW)) {
            if (qtdeDestinos == 0) {
                output.setValue(STATUS, CATRACAS_DESTINO_FORA_REDE);
            }
        }
        output.setValue("contador", contador);
        output.setValue("tempo", TEMPO);
        //System.out.println("[ " + consequence + " ]");
        return consequence;
    }

    public class StatusDispositivo {

        private String nome;
        private String msg;
        private Boolean ok = null;

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(Boolean ok) {
            setMsg(ok, "");
        }

        public void setMsg(Boolean ok, String msg) {
            if (ok == null) {
                msg = "<img src='images/indicator.gif' title='Sincronizando'/>" + msg;
            } else if (ok) {
                msg = "<img src='images/sinc_ok.png' title='Sincronizado com Sucesso!' />" + msg;
            } else if (!ok) {
                msg = "<img src='images/sinc_error.png' title='Não Sincronizado!' />" + msg;
            }
            this.msg = msg;
            this.ok = ok;
        }
    }
}