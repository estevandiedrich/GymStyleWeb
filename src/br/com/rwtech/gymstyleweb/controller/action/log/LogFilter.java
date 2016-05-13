package br.com.rwtech.gymstyleweb.controller.action.log;

import br.com.rwtech.gymstylecore.model.ConnectionManagerLog;
import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Dispositivo;
import br.com.rwtech.gymstylecore.model.pojo.Liberar;
import br.com.rwtech.gymstylecore.model.pojo.Log;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.LoginAction;
import br.com.rwtech.gymstyleweb.controller.action.SessaoAtr;
import br.com.rwtech.gymstyleweb.controller.action.usuarioplano.VincularPlanoAction;
import java.util.Map;
import org.mentawai.core.Action;
import org.mentawai.core.BaseAction;
import org.mentawai.core.Filter;
import org.mentawai.core.Input;
import org.mentawai.core.InvocationChain;
import org.mentawai.core.Output;

/**
 *
 * @author Éder Faria
 */
public class LogFilter implements Filter {

    private Action action = null;

    @Override
    public String filter(InvocationChain chain) throws Exception {
        String consequence = "";
        action = chain.getAction();
        String acao = "";
        acao = chain.getActionName();
        Usuario user = null;
        String usuario = "";

        if (acao.equalsIgnoreCase(ApplicationManager.LOGOUT)) {
            user = (Usuario) action.getSession().getAttribute(SessaoAtr.USUARIO_LOGADO);
            if (user != null) {
                usuario = user.getUsuario();
            }
        }

        Long id = null;
        Input input = action.getInput();
        Output output = action.getOutput();
        String conteudo = "";
        if (acao.endsWith("Create")) {
        } else if (acao.endsWith("Delete")) {
            conteudo = getNameObjectDelete(input, acao);
            id = input.getLong("id");
        } else if (acao.endsWith("Update")) {
        }
        //========================================================================================================================
        // ANTES da ACAO ser executada
        consequence = chain.invoke(); // action será eventualmente executada aqui (depois de todos os filtros da cadeia)

        if (user == null) {
            if (action.getSession().hasAttribute(SessaoAtr.USUARIO_LOGADO)) {
                user = (Usuario) action.getSession().getAttribute(SessaoAtr.USUARIO_LOGADO);
                if (user != null) {
                    usuario = user.getUsuario();
                }
            }
        }
        //========================================================================================================================
        Log log = null;
        if (acao.equalsIgnoreCase(ApplicationManager.LOGOUT)) {
            if (consequence.equals(BaseAction.SUCCESS)) {
                log = new Log("Saiu da aplicação.", "Saída", user);
            }
        }
        if (acao.equalsIgnoreCase(ApplicationManager.LOGIN)) {
            if (BaseAction.isPost(action)) {
                if (consequence.equals(BaseAction.SUCCESS)) {
                    log = new Log("Entrou na aplicação.", "Entrada", user);
                }
            }
        }

        if (acao.equalsIgnoreCase(Ac.BANCO_BACKUP_RESTAURAR)) {
            log = new Log(LogAction.getMap(Ac.BANCO_BACKUP_RESTAURAR), LogAction.RESTAUROU, user);
            //======================================================================================================
        } else if (acao.equalsIgnoreCase(Ac.USUARIO_CREATE_INFORMACAO)) {
            if (BaseAction.isPost(action)) {
                log = new Log(LogAction.getMap(Ac.USUARIO_CREATE_INFORMACAO), LogAction.CRIOU, user);
            }
        } else if (acao.equalsIgnoreCase(Ac.FUNCIONARIO_CREATE_INFORMACAO)) {
            if (BaseAction.isPost(action)) {
                log = new Log(LogAction.getMap(Ac.FUNCIONARIO_CREATE_INFORMACAO), LogAction.CRIOU, user);
            }
        } else if (acao.equalsIgnoreCase(Ac.USUARIO_UPDATE_INFORMACAO)) {
            if (BaseAction.isPost(action)) {
                log = new Log(LogAction.getMap(Ac.USUARIO_UPDATE_INFORMACAO), LogAction.ALTEROU, user);
            }
        } else if (acao.equalsIgnoreCase(Ac.FUNCIONARIO_UPDATE_INFORMACAO)) {
            if (BaseAction.isPost(action)) {
                log = new Log(LogAction.getMap(Ac.FUNCIONARIO_UPDATE_INFORMACAO), LogAction.ALTEROU, user);
            }
        } else if (acao.equalsIgnoreCase(Ac.USUARIO_UPDATE_IDENTIFICACAO)) {
            if (BaseAction.isPost(action)) {
                log = new Log(LogAction.getMap(Ac.USUARIO_UPDATE_IDENTIFICACAO), LogAction.ALTEROU, user);
            }
        } else if (acao.equalsIgnoreCase(Ac.FUNCIONARIO_UPDATE_IDENTIFICACAO)) {
            if (BaseAction.isPost(action)) {
                log = new Log(LogAction.getMap(Ac.FUNCIONARIO_UPDATE_IDENTIFICACAO), LogAction.ALTEROU, user);
            }
        } else if (acao.equalsIgnoreCase(Ac.FUNCIONARIO_UPDATE_ACESSO)) {
            if (BaseAction.isPost(action)) {
                log = new Log(LogAction.getMap(Ac.FUNCIONARIO_UPDATE_ACESSO), LogAction.ALTEROU, user);
            }
        } else if (acao.equalsIgnoreCase(Ac.USUARIO_UPDATE_PLANO)) {
            if (BaseAction.isPost(action)) {
                log = new Log(LogAction.getMap(Ac.USUARIO_UPDATE_PLANO), LogAction.ALTEROU, user);
            }
            //======================================================================================================
        } else if (acao.equalsIgnoreCase(Ac.BANCO_BACKUP_GERAR)) {
            log = new Log(LogAction.getMap(Ac.BANCO_BACKUP_GERAR), LogAction.CRIOU, user);
        } else if (acao.equalsIgnoreCase(Ac.BANCO_BACKUP_DELETE)) {
            log = new Log(LogAction.getMap(Ac.BANCO_BACKUP_DELETE), LogAction.EXCLUIU, user);
        } else if (acao.equalsIgnoreCase(Ac.BANCO_BACKUP_READ)) {
            log = new Log(LogAction.getMap(Ac.BANCO_BACKUP_READ), LogAction.CONSULTOU, user);
        } else if (acao.equalsIgnoreCase(Ac.CONFIGURAR_BOLETO)) {
            log = new Log(LogAction.getMap(Ac.CONFIGURAR_BOLETO), LogAction.CONFIGUROU, user);
        } else if (acao.equalsIgnoreCase(Ac.CONFIGURACAO_BOLETO_READ)) {
            log = new Log(LogAction.getMap(Ac.CONFIGURACAO_BOLETO_READ), LogAction.CONSULTOU, user);
        } else //==========================================================================================================
        //=======   AÇÔES A SER IGNORADAS   ========================================================================
        //==========================================================================================================
        if (acao.equalsIgnoreCase(Ac.VERIFICA_CPF)
                || acao.equalsIgnoreCase(Ac.VERIFICA_LOGIN)
                || acao.equalsIgnoreCase(Ac.MAIN)
                || acao.equalsIgnoreCase(Ac.DIGITAL_AJAX)
                || acao.equalsIgnoreCase(Ac.DIGITAL_CREATE)
                || acao.equalsIgnoreCase(Ac.DIGITAIS_USUARIO)
                || acao.equalsIgnoreCase(Ac.MAIN)
                || acao.equalsIgnoreCase(Ac.ULTIMOS_EVENTOS)
                || acao.equalsIgnoreCase(Ac.REQUISICAO_MANAGER)
                || acao.equalsIgnoreCase(Ac.REQUISICAO_ATUALIZAR_USUARIO)
                || acao.equalsIgnoreCase(Ac.REQUISICAO_READ)
                || acao.equalsIgnoreCase(Ac.ATUALIZAR_USUARIO)
                || acao.equalsIgnoreCase(Ac.ATUALIZAR_STATUS_FICHA)
                //|| acao.equalsIgnoreCase(Ac.AUTENTICAO_PAGAMENTO_READ)
                || acao.equalsIgnoreCase(Ac.PAGAMENTO_UPDATE_IMPRIMIR_AJAX)
                || acao.equalsIgnoreCase(Ac.PAGAMENTO_UPDATE_IMPRIMIR_ENTRADA_AJAX)
                || acao.equalsIgnoreCase(Ac.READ_STATUS_IMPRIMIR_PAGAMENTO)
                || acao.equalsIgnoreCase(Ac.STATUS_FICHA)
                || acao.equalsIgnoreCase(Ac.VERIFICA_MATRICULA)
                || acao.equalsIgnoreCase(Ac.CONFIGURAR_CATRACA)
                || acao.equalsIgnoreCase(Ac.CONTA_BANCARIA_READ_BY_ID_AJAX)
                || acao.equalsIgnoreCase(Ac.READ_CAIXA_AJAX)
                || acao.equalsIgnoreCase(Ac.READ_CAIXAS_AJAX)
                || acao.equalsIgnoreCase(Ac.REGISTRO_CONTA_BANCARIA_READ_AJAX)
                || acao.equalsIgnoreCase(Ac.CARREGAR_ABRIR_CAIXA)
                || acao.equalsIgnoreCase(Ac.REENVIAR_CONFIGURAR_CATRACA)
                || acao.equalsIgnoreCase(Ac.IMPRIMIR_PAGAMENTO)
                || acao.equalsIgnoreCase(Ac.IMPORTAR_PLANO_AJAX)
                || acao.equalsIgnoreCase(Ac.INDEX)
                || acao.equalsIgnoreCase(Ac.BUSCA_APPLET)
                || acao.equalsIgnoreCase(Ac.SELECT_DEDOS)
                || acao.equalsIgnoreCase(Ac.SINCRONIZADOS_FALSE)
                || acao.equalsIgnoreCase(Ac.PARCELAS_AJAX)) {
            log = null;//Nao resgistra
        } else if (acao.endsWith("Caixa")) {
            log = getLogByCaixa(user, input, output, acao, consequence);
//        } else if (acao.endsWith("ContaBancar")) {
//            log = getLogByCaixa(user, input, output, acao, consequence);
        } else if (acao.endsWith("Create")) {
            input = action.getInput();
            output = action.getOutput();
            log = getLogByCreate(user, input, acao, consequence);//nao posso usar a acao privada pq após o invoque uma outra acao ja vem em seguida e repreenche a mesma.
        } else if (acao.endsWith("Delete")) {
            log = getLogByDelete(user, conteudo, consequence, acao);
        } else if (acao.endsWith("Update")) {
            log = getLogByUpdate(user, input, acao, consequence);
        } else if (acao.endsWith("Info")) {
            log = getLogByInfo(user, input, output, acao);
        } else if (acao.endsWith("Read")) {
            log = getLogByRead(user, input, output, acao, consequence);
        } else {
            if (log == null) {
                log = getAcao(input, acao, user, consequence);
            }
        }
        if (user == null && log != null) {
            log.setUsuario((Usuario) action.getSession().getAttribute(SessaoAtr.USUARIO_LOGADO));
        }
        if (log != null && log.getUsuario() != null) {
            ServiceLocator.getLogService().create(ConnectionManagerLog.getInstance().getConnection(), log);
        }

        return consequence;
    }

    @Override
    public void destroy() {
    }

    public Log getLogByDelete(Usuario user, String conteudo, String consequence, String acao) {
        Log log = null;
        if (consequence.equals(BaseAction.SUCCESS)) {
            String aux = LogAction.getMap(acao) + conteudo;
            log = new Log(aux, LogAction.EXCLUIU, user);
        }
        return log;
    }

    public Log getLogByRead(Usuario user, Input input, Output output, String nomeAcao, String consequence) {
        Log log = null;
        String aux = LogAction.getMap(nomeAcao);
        if (consequence.equals(BaseAction.SHOW)) {// Se for SHOW é pq entrou na consulta.
            if (!nomeAcao.equalsIgnoreCase(Ac.REQUISICAO_READ)) {

                if (nomeAcao.equalsIgnoreCase(Ac.USUARIO_FICHAS_READ)) {
                    if (input.getValue("idUsuario") != null) {
                        aux += getUser(input, "idUsuario");
                    } else {
                        aux += " - " + ServiceLocator.getUsuarioService().readByIdSimple((Long) output.getValue("idUsuario")).getUsuario();
                    }
                } else if (nomeAcao.equalsIgnoreCase(Ac.USUARIO_PLANOS_READ)) {
                    if (input.getValue("id") != null) {
                        aux += " - " + ServiceLocator.getUsuarioService().readByIdSimple(input.getLong("id")).getUsuario();
                    } else {
                        aux += " - " + ServiceLocator.getUsuarioService().readByIdSimple((Long) output.getValue("id")).getUsuario();
                    }
                }
                log = new Log(aux, LogAction.CONSULTOU, user);
            }
        } else if (aux.equalsIgnoreCase("Academia")) {
            log = new Log(aux, LogAction.CONSULTOU, user);
        }
        return log;
    }

    public Log getLogByCaixa(Usuario user, Input input, Output output, String nomeAcao, String consequence) {
        Log log = null;
        String aux = LogAction.getMap(nomeAcao);
        if (nomeAcao.equalsIgnoreCase(Ac.MANAGER_CAIXA)) {
            log = new Log(aux, LogAction.CONSULTOU, user);
        } else if (nomeAcao.equalsIgnoreCase(Ac.ABRIR_CAIXA)) {
            log = new Log(aux, LogAction.ABERTURA, user);
        } else if (nomeAcao.equalsIgnoreCase(Ac.FECHAR_CAIXA)) {
            log = new Log(aux, LogAction.FECHAMENTO, user);
        } else if (nomeAcao.equalsIgnoreCase(Ac.ENTRADA_CAIXA)) {
            log = new Log(aux, LogAction.ENTRADA, user);
        } else if (nomeAcao.equalsIgnoreCase(Ac.RETIRADA_CAIXA)) {
            log = new Log(aux, LogAction.RETIRADA, user);
        } else if (nomeAcao.equalsIgnoreCase(Ac.DELETE_REGISTRO_CAIXA)) {
            log = new Log(aux, LogAction.EXCLUIU, user);
        } else {
            log = new Log(aux, LogAction.CONSULTOU, user);
        }
        return log;
    }

    public Log getLogByInfo(Usuario user, Input input, Output output, String nomeAcao) {
        String aux = LogAction.getMap(nomeAcao);
        if (nomeAcao.equalsIgnoreCase(Ac.FICHA_INFO)) {
            aux += getUser(input, "idUsuario");
        } else if (nomeAcao.equalsIgnoreCase(Ac.AVALIACAO_FISICA_INFO)) {
            aux += getUser(input, "id");
        }
        Log log = new Log(aux, LogAction.VISUALIZOU, user);
        return log;
    }

    public Log getLogByCreate(Usuario user, Input input, String nomeAcao, String consequence) {
        Log log = null;
        if (BaseAction.isPost(action)) {
            if (consequence.equals(BaseAction.SUCCESS)) {
                String aux = LogAction.getMap(nomeAcao);
                if (nomeAcao.equalsIgnoreCase(Ac.USUARIO_CREATE_INFORMACAO)) {
                    aux += " - " + input.getString("usuario");
                } else if (nomeAcao.equalsIgnoreCase(Ac.EXERCICIO_CREATE)) {
                    aux += " - " + input.getString("exercicio");
                } else if (nomeAcao.equalsIgnoreCase(Ac.MODALIDADE_CREATE)) {
                    aux += " - " + input.getString("modalidade");
                } else if (nomeAcao.equalsIgnoreCase(Ac.PLANO_CREATE)) {
                    aux += " - " + input.getString("plano");
                } else if (nomeAcao.equalsIgnoreCase(Ac.AVALIACAO_FISICA_CREATE)) {
                    aux += getUser(input, "id");
                } else if (nomeAcao.equalsIgnoreCase(Ac.FICHA_CREATE)) {
                    aux += getUser(input, "idUsuario");
                }
                log = new Log(aux, LogAction.CRIOU, user);
            }
        }
        return log;
    }

    public Log getLogByUpdate(Usuario user, Input input, String acao, String consequence) {
        Log log = null;
        if (BaseAction.isPost(action)) {
            if (consequence.equals(BaseAction.SUCCESS)) {
                String aux = LogAction.getMap(acao);
                if (acao.equalsIgnoreCase(Ac.USUARIO_UPDATE_INFORMACAO)) {
                    aux += " - " + input.getString("usuario");
                } else if (acao.equalsIgnoreCase(Ac.EXERCICIO_UPDATE)) {
                    aux += " - " + input.getString("exercicio");
                } else if (acao.equalsIgnoreCase(Ac.MODALIDADE_UPDATE)) {
                    aux += " - " + input.getString("modalidade");
                } else if (acao.equalsIgnoreCase(Ac.PLANO_UPDATE)) {
                    aux += " - " + input.getString("plano");
                } else if (acao.equalsIgnoreCase(Ac.CONFIGURACAO_UPDATE)) {
                    aux += " - " + input.getString("descricao");
                } else if (acao.equalsIgnoreCase(Ac.FICHA_UPDATE)) {
                    aux += getUser(input, "idUsuario");
                }
                log = new Log(aux, LogAction.ALTEROU, user);
            }
        }
        return log;
    }

    private String getUser(Input input, String campo) {
        String aux = "";
        if (input.getValue(campo) != null && input.getLong(campo) > 0) {
            Usuario usu = ServiceLocator.getUsuarioService().readByIdSimple(input.getLong(campo));
            if (usu != null && !usu.getUsuario().isEmpty()) {
                aux += " - " + usu.getUsuario();
            }
        }
        return aux;
    }

    /*
     * Esse método é chamado antes do invoke, antes da actoin ser executada
     * 
     */
    public String getNameObjectDelete(Input input, String acao) {
        String aux = " - ";
        Long id = input.getLong("id");
        if (acao.equalsIgnoreCase(Ac.USUARIO_DELETE)) {
            aux += ServiceLocator.getUsuarioService().readByIdSimple(id).getUsuario();
        } else if (acao.equalsIgnoreCase(Ac.MODALIDADE_DELETE)) {
            aux += ServiceLocator.getModalidadeService().readById(id).getModalidade();
        } else if (acao.equalsIgnoreCase(Ac.EXERCICIO_DELETE)) {
            aux += ServiceLocator.getExercicioService().readById(id).getExercicio();
        } else if (acao.equalsIgnoreCase(Ac.PLANO_DELETE)) {
            aux += ServiceLocator.getPlanoService().readById(id).getPlano();
        } else if (acao.equalsIgnoreCase(Ac.DELETE_REGISTRO_CAIXA)) {
            aux = "";
        } else if (acao.equalsIgnoreCase(Ac.DELETE_REGISTRO_CONTA_BANCARIA)) {
            aux = "";
        } else if (acao.equalsIgnoreCase(Ac.AVALIACAO_FISICA_DELETE)) {
//            aux += ServiceLocator.getAvaliacaoFisicaService().readById(id).get();
            aux = "";
        } else if (acao.equalsIgnoreCase(Ac.DISPOSITIVO_DELETE)) {
            aux += ServiceLocator.getDispositivoService().readById(id).getDispositivo();
        } else if (acao.equalsIgnoreCase(Ac.FICHA_DELETE)) {
            aux += getUser(input, "idUsuario");
        } else if (acao.equalsIgnoreCase(Ac.LIBERAR_DELETE)) {
            Liberar lib = ServiceLocator.getLiberarService().readById(id);
            aux += lib.getDataHora() + ": " + lib.getJustificativa();
        }
        return aux;
    }

    public Log getAcao(Input input, String nomeAcao, Usuario user, String consequence) {
        Log log = null;
        String aux = LogAction.getMap(nomeAcao);
        if (nomeAcao.equalsIgnoreCase(Ac.LANCAR_PARCELAS_PLANO_USUARIO)) {
            log = new Log(aux, LogAction.PAGAMENTO, user);
        } else if (nomeAcao.equalsIgnoreCase(Ac.IMPORTAR_HORARIO_AJAX)) {
            log = new Log(aux, "Importar", user);
        } else if (nomeAcao.equalsIgnoreCase(Ac.USUARIO_AVALIACOES)) {
            aux += getUser(input, "id");
            log = new Log(aux, LogAction.CONSULTOU, user);
        } else if (nomeAcao.equalsIgnoreCase(Ac.BUSCAR_EVENTOS)) {
            aux += " - " + ServiceLocator.getDispositivoService().readById(input.getLong("id")).getDispositivo();
            log = new Log(aux, LogAction.BUSCA, user);
        } else if (nomeAcao.equalsIgnoreCase(Ac.DISPOSITIVO_BUSCAR)) {
            log = new Log(aux, LogAction.BUSCA, user);
        } else if (nomeAcao.equalsIgnoreCase(Ac.VER_PARCELA_PLANO_USUARIO)) {
            log = new Log(aux, LogAction.VISUALIZOU, user);
        } else if (nomeAcao.equalsIgnoreCase(Ac.PAGAR_PARCELA_PLANO_USUARIO)) {
            log = new Log(aux, LogAction.PAGAMENTO, user);
        } else if (nomeAcao.equalsIgnoreCase(Ac.PAGAMENTOS_PLANO_USUARIO)) {
            Usuario usuario = null;
            if (input.getValue("idPlanoUsuario") != null) {
                Map<String, Object> map = ServiceLocator.getPlanoService().readPlanoUsuarioByIdPlanoUsuario(input.getLong("idPlanoUsuario"));
                usuario = (Usuario) map.get("usuario");
            } else {
                aux += getUser(input, "idUsuario");
            }
            if (usuario != null) {
                aux += " - " + usuario.getUsuario();
            }
            log = new Log(aux, LogAction.CONSULTOU, user);
        } else if (nomeAcao.equalsIgnoreCase(Ac.VER_PLANO_USUARIO)) {
            log = new Log(aux, LogAction.VISUALIZOU, user);
        } else if (nomeAcao.equalsIgnoreCase(Ac.CANCELAR_PLANO)) {
            if (consequence.equals(BaseAction.SUCCESS)) {
                Usuario usuario = ServiceLocator.getUsuarioPlanoService().readUsuarioByIdPlano(input.getLong("idPlanoUsuario"));
                aux += " - " + usuario.getUsuario();
                log = new Log(aux, LogAction.CANCELOU, user);
            }
        } else if (nomeAcao.equalsIgnoreCase(Ac.REQUISICAO_MANAGER_EXCLUIR)) {
        } else if (nomeAcao.equalsIgnoreCase(Ac.VINCULAR_PLANO)) {
            if (consequence.equals(BaseAction.SUCCESS) || consequence.equals(VincularPlanoAction.ATUALIZAR)) {
                aux += getUser(input, "id");
                log = new Log(aux, LogAction.VINCULAR, user);
            }
        } else if (nomeAcao.equalsIgnoreCase(Ac.REQUISICAO_ATUALIZAR_TODOS)) {
            log = new Log(aux, LogAction.CONSULTOU, user);
        } else if (nomeAcao.equalsIgnoreCase(Ac.EVENTO_REPORT)) {
            log = new Log(aux, LogAction.CONSULTOU, user);
        } else if (nomeAcao.equalsIgnoreCase(Ac.FICHA_REPORT)) {
            log = new Log(aux, LogAction.CONSULTOU, user);
        } else if (nomeAcao.equalsIgnoreCase(Ac.RECIBO_REPORT)) {
            log = new Log(aux, LogAction.CONSULTOU, user);
        } else if (nomeAcao.equalsIgnoreCase(Ac.REPORT_CAIXA_PDF)) {
            log = new Log(aux, LogAction.CONSULTOU, user);
        } else if (nomeAcao.equalsIgnoreCase(Ac.LIBERAR_CATRACA)) {
            if (consequence.equals(BaseAction.SUCCESS)) {
                if (input.getValue("dispositivo") != null) {
                    Dispositivo dis = ServiceLocator.getDispositivoService().readById(input.getLong("dispositivo"));
                    if (dis != null) {
                        aux += " - " + (dis.getDispositivo() != null ? dis.getDispositivo() : (!dis.foraDaRede() ? dis.getEnderecoIp() : dis.getMac()));
                    }
                    log = new Log(aux, LogAction.LIBEROU, user);
                }
            }
        } else {
            log = new Log(aux, "", user);
        }
        return log;
    }
}
