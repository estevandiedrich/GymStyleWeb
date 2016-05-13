package br.com.rwtech.gymstyleweb.controller.action.requisicao;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Dispositivo;
import br.com.rwtech.gymstylecore.model.pojo.Requisicao;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.pojo.tipos.TipoRequisicaoResposta;
import br.com.rwtech.gymstylecore.model.util.StatusAcesso;
import br.com.rwtech.gymstylecore.model.util.StatusPlano;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import java.util.Set;
import org.mentawai.core.BaseAction;
import org.mentawai.core.Output;

/**
 *
 * @author Éder Faria
 */
public class RequisicaoUsuarioAction extends BaseAction {

    public static int TIME = 70; //17,5; Antes estava 40 Conferir na pagina web o Time do Java Script
    public static int TIME_EXCLUIR = 20;//== 5 segundos == 20 * 0,250 valor default para um destino
    private static String ID_PLANO_USUARIO = "idPlanoUsuario";
    private static String ID_REQ = "idRequisicao";
    private static String ID_USUARIO = "idUsuario";
    private static String RETORNO = "retorno";
    private static String EXCLUINDO = "Excluindo";
    private static String CADASTRANDO = "Cadastrando";
    private static String TIPO = "tipo";

    @Override
    public String execute() throws Exception {
        Long idUsuario = input.getLong("id");
        String consequence = SUCCESS;

        if (!atualizar(idUsuario, output, (Usuario) getUserSession())) {
            consequence = ERROR;
            session.setAttribute("mensagem", "Aluno não possui plano!");
        }
        if (input.getValue(RETORNO) != null) {
            output.setValue(RETORNO, input.getString(RETORNO));
        }
        return consequence;
    }

    /*
     * Cria a requisicao de usuario
     * Acaso o retorno for true, foi criada a requisição para atualizar o usuário
     */
    public static Boolean atualizar(Long idUsuario, Output output, Usuario usuarioSessao) {
        StatusAcesso statusAcesso = ServiceLocator.getFuncionarioService().contemAcesso(idUsuario);
        StatusPlano statusPlano = ServiceLocator.getUsuarioPlanoService().readStatusUltimoPlano(idUsuario);
        if ((statusAcesso.equals(StatusAcesso.NAO_CONTEM)) && (statusPlano.equals(StatusPlano.NAO_CONTEM))) {
            return Boolean.FALSE;
        }

        Requisicao requisicao = new Requisicao();
        requisicao.setOperador(usuarioSessao);
        requisicao.setParametro(idUsuario);
        requisicao.setStatus(Boolean.FALSE);

        if (statusAcesso.equals(StatusAcesso.ATIVO)
                || statusPlano.equals(StatusPlano.QUITADO)
                || statusPlano.equals(StatusPlano.ABERTO)) {
            requisicao.setTipo(TipoRequisicaoResposta.CADASTRAR_USUARIO);
            output.setValue(TIPO, CADASTRANDO);
        } else {
            requisicao.setTipo(TipoRequisicaoResposta.EXCLUIR_USUARIO);
            output.setValue(TIPO, EXCLUINDO);
        }

        requisicao.setDestino(null);
        ServiceLocator.getRequisicaoService().create(requisicao);
        output.setValue(ID_PLANO_USUARIO, ServiceLocator.getUsuarioPlanoService().readIdUltimoPlanoUsuario(idUsuario));//esse id será passado para o manager de requisicao buscar os dispositivos a serem atualizados
        output.setValue(ID_REQ, requisicao.getId());
        output.setValue(ID_USUARIO, idUsuario);
        output.setValue(RETORNO, Ac.REQUISICAO_READ + Ac.DO);//Retorno default

        ServiceLocator.getUsuarioService().updateSincronizado(idUsuario, Boolean.FALSE);
        return Boolean.TRUE;
    }
    /*
     * Cria a requisicao de usuario
     * Acaso o retorno for true, foi criada a requisição para atualizar o usuário
     * 
     */

    public static Boolean create(Long idUsuario, Output output, Usuario usuarioSessao) {
        return create(idUsuario, output, usuarioSessao, Boolean.TRUE);
    }

    public static Boolean create(Long idUsuario, Output output, Usuario usuarioSessao, Boolean verifica) {
        if (verifica) {
            StatusAcesso statusAcesso = ServiceLocator.getFuncionarioService().contemAcesso(idUsuario);
            StatusPlano statusPlano = ServiceLocator.getUsuarioPlanoService().readStatusUltimoPlano(idUsuario);
            if (statusAcesso.equals(StatusAcesso.NAO_CONTEM)) {
                if (statusPlano.equals(StatusPlano.NAO_CONTEM)) {
                    return Boolean.FALSE;
                }
            }
        }

        Requisicao requisicao = new Requisicao();
        requisicao.setOperador(usuarioSessao);
        requisicao.setParametro(idUsuario);
        requisicao.setStatus(Boolean.FALSE);
        requisicao.setTipo(TipoRequisicaoResposta.CADASTRAR_USUARIO);
        output.setValue(TIPO, CADASTRANDO);
        requisicao.setDestino(null);
        ServiceLocator.getRequisicaoService().create(requisicao);
        output.setValue(ID_PLANO_USUARIO, ServiceLocator.getUsuarioPlanoService().readIdUltimoPlanoUsuario(idUsuario));//esse id será passado para o manager de requisicao buscar os dispositivos a serem atualizados
        output.setValue(ID_REQ, requisicao.getId());
        output.setValue(ID_USUARIO, idUsuario);
        output.setValue(RETORNO, Ac.REQUISICAO_READ + ".do");//Retorno default

        ServiceLocator.getUsuarioService().updateSincronizado(idUsuario, Boolean.FALSE);
        return Boolean.TRUE;
    }

    /*
     * Cria a requisicao de usuario
     * Acaso o retorno for true, foi criada a requisição para atualizar o usuário
     */
    public static void delete(Long idUsuario, Output output, Usuario usuarioSessao) {
        Requisicao requisicao = new Requisicao();
        requisicao.setOperador(usuarioSessao);
        requisicao.setParametro(idUsuario);
        requisicao.setStatus(Boolean.FALSE);
        requisicao.setTipo(TipoRequisicaoResposta.EXCLUIR_USUARIO);
        output.setValue(TIPO, EXCLUINDO);
        requisicao.setDestino(null);
        ServiceLocator.getRequisicaoService().create(requisicao);
        output.setValue(ID_PLANO_USUARIO, ServiceLocator.getUsuarioPlanoService().readIdUltimoPlanoUsuario(idUsuario));//esse id será passado para o manager de requisicao buscar os dispositivos a serem atualizados
        output.setValue(ID_REQ, requisicao.getId());
        output.setValue(ID_USUARIO, idUsuario);
        output.setValue(RETORNO, Ac.USUARIO_READ + Ac.DO);//Retorno Default
        ServiceLocator.getUsuarioService().updateSincronizado(idUsuario, Boolean.FALSE);
    }

    /* Retorna todos os destinos de acesso de um usuario atraves de seu id e idPlanoUsuario */
    public static Set<Dispositivo> getDispositivosById(Long idUsuario, Long idPlanoUsuario) {
        Set<Dispositivo> dis = null;
        if (idPlanoUsuario != null && idPlanoUsuario > 0) {
            dis = ServiceLocator.getUsuarioPlanoService().readDispositivosPlanoUsuario(idPlanoUsuario);
        }

        if (dis == null) {
            dis = ServiceLocator.getFuncionarioService().getDispositivosByAcessoIdUsuario(idUsuario);
        } else {
            dis.addAll(ServiceLocator.getFuncionarioService().getDispositivosByAcessoIdUsuario(idUsuario));
        }
        return dis;
    }
}
