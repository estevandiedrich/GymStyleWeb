package br.com.rwtech.gymstyleweb.controller.action.funcionario;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstyleweb.controller.action.usuario.UsuarioAction;
import java.util.LinkedHashMap;
import java.util.Map;
import org.mentawai.core.BaseAction;
import org.mentawai.core.Input;
import org.mentawai.core.Output;

/**
 * @author Éder Faria
 */
public class FuncionarioAction extends BaseAction {

    public static String INESPERADO = UsuarioAction.INESPERADO;
    public static String ATUALIZAR = UsuarioAction.ATUALIZAR;
    public static String INFORMACAO = UsuarioAction.INFORMACAO;
    public static String IDENTIFICACAO = UsuarioAction.IDENTIFICACAO;
    public static String ACESSO = "acesso";
    public static String ACAO = UsuarioAction.ACAO;
    public static String NAVEGADOR = UsuarioAction.NAVEGADOR;
    public static String ID_USUARIO = "idUsuario";
    public static String CPF = "cpf";
    public static String ID = "id";
    public static String FOTO = "foto";
    public static String MENSAGEM = "mensagem";

    public static void preload(Output output, Input input) {

        output.setValue("tipoUsuarioOptions", getTipoUsuarios());
        output.setValue("sexos", UsuarioAction.getSexos());
        output.setValue("estados", UsuarioAction.getEstados());
        output.setValue("redesSociais", ServiceLocator.getRedeSocialService().readList());
        output.setValue("estadosCivis", ServiceLocator.getEstadoCivilService().readList());

        Long id = (Long) output.getValue("id");
        if (id != null && id > 0) {
            Usuario pojo = ServiceLocator.getUsuarioService().readById(id);
            if (pojo != null) {
                output.setValue("pojoInfo", pojo);//pojo é para aparecer as informações do usuário em cima na tela
                output.setValue("infoCpf", pojo.getCpf());
                output.setValue("loginUsuario", pojo.getLogin());
                output.setValue("loginAux", pojo.getLogin());

                if (pojo.getDataNascimento() != null) {
                    output.setValue("dataNascimentoFormat", pojo.getDataNascimento().getTime());
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
            }
        }
    }

    public static Map<Long, String> getTipoUsuarios() {
        Map<Long, String> mapa = new LinkedHashMap<Long, String>();
        mapa.put(Long.valueOf(1), "Administrador(a)");
        //mapa.put(Long.valueOf(2), "Aluno(a)");
        mapa.put(Long.valueOf(3), "Instrutor(a)");
        mapa.put(Long.valueOf(4), "Secretario(a)");
        return mapa;
    }
}