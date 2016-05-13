package br.com.rwtech.gymstyleweb.controller.action.funcionario.update;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstylecore.model.util.Validador;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.funcionario.FuncionarioAction;
import br.com.rwtech.gymstyleweb.controller.action.usuario.UsuarioAction;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.fileupload.FileItem;
import org.mentawai.core.ApplicationManager;
import org.mentawai.core.BaseAction;
import org.mentawai.core.Input;
import org.mentawai.core.Output;
import org.mentawai.core.StreamConsequence;

/**
 *
 * @author Éder Faria
 */
public class FuncionarioUpdateInformacaoAction extends BaseAction {

    static final long serialVersionUID = 1L;
    private byte[] foto = null;
    private static String PREVIEW = Ac.FUNCIONARIO_UPDATE_INFORMACAO + ".preview.do";

    @Override
    public String execute() throws Exception {
        Long idUsuario = input.getLong(FuncionarioAction.ID);
        String cpf = (String) input.getValue(FuncionarioAction.CPF);
        String consequence = SHOW;
        if (idUsuario != null) {
            if (isPost() && input.getValue("usuario") != null && !Validador.existCPF(cpf, idUsuario)) {
                //postergar = false;
                Usuario pojo = (Usuario) input.getValue("VOfuncionario");
                if (pojo != null) {
                    pojo.setFoto(foto);
                    pojo.setDataNascimento(CalendarUtil.setDateCalendar((String) input.getValue("dataNascimentoFormat")));
                    if (input.getValue("login") != null && !input.getString("login").isEmpty()) {
                        pojo.setSenha(input.getString("senha"));
                    }
                    String idTipoUsuario = (String) input.getValue("idTipoUsuario");
                    if (idUsuario != 1 && !idTipoUsuario.equals("")) {
                        if (idTipoUsuario.equals("1")) {
                            pojo.setIsAdm(Boolean.TRUE);
                            pojo.setIsInstrutor(Boolean.FALSE);
                            pojo.setIsSecretaria(Boolean.FALSE);
                        } else if (idTipoUsuario.equals("3")) {
                            pojo.setIsAdm(Boolean.FALSE);
                            pojo.setIsInstrutor(Boolean.TRUE);
                            pojo.setIsSecretaria(Boolean.FALSE);
                        } else if (idTipoUsuario.equals("4")) {
                            pojo.setIsAdm(Boolean.FALSE);
                            pojo.setIsInstrutor(Boolean.FALSE);
                            pojo.setIsSecretaria(Boolean.TRUE);
                        }
                    } else {
                        pojo.setIsAdm(Boolean.TRUE);
                        pojo.setIsInstrutor(Boolean.FALSE);
                        pojo.setIsSecretaria(Boolean.FALSE);
                    }
                    pojo.setAtivoFuncionario(Boolean.TRUE);
                    //para nao alterar estes campos na tabela
                    pojo.setAtivoAluno(null);
                    pojo.setIsAluno(null);

                    String idRedeSocial = (String) input.getValue("idRedeSocial");
                    if (!idRedeSocial.equals("")) {
                        pojo.setRedeSocial(ServiceLocator.getRedeSocialService().readById((Long.valueOf(idRedeSocial))));
                    }

                    String idEstadoCivil = (String) input.getValue("idEstadoCivil");
                    if (!idEstadoCivil.equals("")) {
                        pojo.setEstadoCivil(ServiceLocator.getEstadoCivilService().readById((Long.valueOf(idEstadoCivil))));
                    }
                    //Set null para nao ser alterado no banco
                    pojo.setMatricula(null);
                    ServiceLocator.getUsuarioService().update(pojo);

                    if (input.getValue(FuncionarioAction.ACAO) != null && input.getString(FuncionarioAction.ACAO).equals(FuncionarioAction.IDENTIFICACAO)) {
                        consequence = FuncionarioAction.IDENTIFICACAO;
                    } else {
                        session.setAttribute(FuncionarioAction.MENSAGEM, "Funcionário alterado com sucesso!");
                        consequence = SUCCESS;
                    }
                }
            } else {
                output.setValue(FuncionarioAction.ID, idUsuario);
                this.preload(output, input);
                if (output.getValue("pojoInfo") == null) {
                    consequence = Ac.EXCEPTION;
                }
            }
        } else {
            consequence = Ac.EXCEPTION;
        }
        return consequence;
    }

    public String definir() {
        output.setValue(FuncionarioAction.NAVEGADOR, session.getAttribute(FuncionarioAction.NAVEGADOR));
        FileItem objImageFile = (FileItem) input.getValue("imagem");
        if (objImageFile != null) {
            foto = objImageFile.get();
            adhere();
            output.setValue(FuncionarioAction.FOTO, PREVIEW);
            return SUCCESS;
        } else if (objImageFile == null && foto != null) {
            adhere();
            output.setValue(FuncionarioAction.FOTO, PREVIEW);
            return SUCCESS;
        }

        if (foto != null) {
            output.setValue(FuncionarioAction.FOTO, PREVIEW);
        } else {
            output.setValue(FuncionarioAction.FOTO, "images/user.png");
        }
        return SUCCESS;
    }

    public String preview() {
        output.setValue(FuncionarioAction.NAVEGADOR, session.getAttribute(FuncionarioAction.NAVEGADOR));
        output.setValue(StreamConsequence.STREAM_KEY, foto);
        output.setValue(StreamConsequence.CONTENT_LENGTH_KEY, foto.length);
//        output.setValue(StreamConsequence.STREAM, foto);
//        output.setValue(StreamConsequence.CONTENT_LENGTH, foto.length);
        return SUCCESS;
    }

    public void preload(Output output, Input input) {
        ApplicationManager.getRealPath();

        output.setValue("url", org.mentawai.core.ApplicationManager.getRealPath());

        output.setValue("tipoUsuarioOptions", FuncionarioAction.getTipoUsuarios());
        output.setValue("sexos", UsuarioAction.getSexos());
        output.setValue("estados", UsuarioAction.getEstados());
        output.setValue("redesSociais", ServiceLocator.getRedeSocialService().readList());
        output.setValue("estadosCivis", ServiceLocator.getEstadoCivilService().readList());

        output.setValue("mascaraTel", getMascara());
        output.setValue("mascaraCel", getMascara());

        Long id = (Long) output.getValue(FuncionarioAction.ID);
        if (id != null && id > 0) {
            Usuario pojo = ServiceLocator.getUsuarioService().readById(id);
            if (pojo != null) {
                output.setValue("matricula", id);
                output.setValue("selecionado", "usuarioRead");
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

                output.setValue("pojo", pojo);
                output.setValue("pojoInfo", pojo);//pojo é para aparecer as informações do usuário em cima na tela

                if (pojo != null) {
                    if (pojo.getDataNascimento() != null) {
                        output.setValue("dataNascimentoFormat", CalendarUtil.getDateCalendar(pojo.getDataNascimento()));
                    }
                    if (pojo.getTipoUsuario() != null) {
                        output.setValue("idTipoUsuario", pojo.getTipoUsuario());
                    }
                    if (pojo.getRedeSocial() != null) {
                        output.setValue("idRedeSocial", pojo.getRedeSocial().getId());
                    }
                    if (pojo.getEstadoCivil() != null) {
                        output.setValue("idEstadoCivil", pojo.getEstadoCivil().getId());
                    }

                    foto = null;
                    if (pojo.getFoto() != null) {
                        foto = pojo.getFoto();
                        adhere();
                        output.setValue(FuncionarioAction.FOTO, PREVIEW);
                    } else {
                        output.setValue(FuncionarioAction.FOTO, "images/user.png");
                    }
                    pojo.setSenha("");//no alterar nao se seta a senha
                }
            }
        } else {
            output.setValue("matricula", ServiceLocator.getUsuarioService().readNextMatricula());
        }

        output.setValue("login", "");
        output.setValue("senha", "");
    }

    public static Map<String, String> getMascara() {
        Map<String, String> mapa = new LinkedHashMap<String, String>();
        mapa.put("sim", "Habilitar Máscara de 11 campos");
        return mapa;
    }
}
