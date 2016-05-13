package br.com.rwtech.gymstyleweb.controller.action.usuario;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstylecore.model.util.Validador;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import org.apache.commons.fileupload.FileItem;
import org.mentawai.core.BaseAction;
import org.mentawai.core.StreamConsequence;

public class UsuarioCreateAction extends BaseAction {

    static final long serialVersionUID = 1L;
    private byte[] foto = null;

    @Override
    public String execute() throws Exception {
        String cpf = (String) input.getValue("cpf");
        String consequence = SHOW;

        if (isPost() && !Validador.existCPF(cpf, null)) {
            Usuario pojo = (Usuario) input.getValue("VOusuario");

            pojo.setFoto(foto);
            pojo.setDataNascimento(CalendarUtil.setDateCalendar((String) input.getValue("dataNascimentoFormat")));

            pojo.setIsAluno(Boolean.TRUE);
            pojo.setAtivoAluno(Boolean.TRUE);
            pojo.setIsAdm(null);
            pojo.setIsSecretaria(null);
            pojo.setIsInstrutor(null);
            pojo.setAtivoFuncionario(null);

            String idRedeSocial = (String) input.getValue("idRedeSocial");
            if (!idRedeSocial.equals("")) {
                pojo.setRedeSocial(ServiceLocator.getRedeSocialService().readById((Long.valueOf(idRedeSocial))));
            }

            String idEstadoCivil = (String) input.getValue("idEstadoCivil");
            if (!idEstadoCivil.equals("")) {
                pojo.setEstadoCivil(ServiceLocator.getEstadoCivilService().readById((Long.valueOf(idEstadoCivil))));
            }

            ServiceLocator.getUsuarioService().create(pojo);
            input.setValue("id", pojo.getId());
            session.setAttribute("idUsuario", pojo.getId());
            if (input.getValue(UsuarioAction.ACAO) != null && input.getString(UsuarioAction.ACAO).equals(UsuarioAction.IDENTIFICACAO)) {
                consequence = UsuarioAction.IDENTIFICACAO;
            } else {
                consequence = SUCCESS;
            }
        } else {
            foto = null;
            UsuarioAction.preload(output, input);
            output.setValue("retornoVerificaCpf", "");
            output.setValue("retornoVerificaMatricula", "");
        }

        UsuarioAction.setSelecionado(output);
        return consequence;
    }
    private String PREVIEW = Ac.USUARIO_CREATE_INFORMACAO + ".preview.do";

    public String definir() {
        FileItem objImageFile = (FileItem) input.getValue("imagem");
        if (objImageFile != null) {
            foto = objImageFile.get();
            adhere();
            output.setValue(UsuarioAction.FOTO, PREVIEW);
            return SUCCESS;
        } else if (objImageFile == null && foto != null) {
            adhere();
            output.setValue(UsuarioAction.FOTO, PREVIEW);
            return SUCCESS;
        }
        output.setValue(UsuarioAction.FOTO, "images/user.png");
        return SUCCESS;
    }

    public String preview() {
        output.setValue(StreamConsequence.STREAM_KEY, foto);
        output.setValue(StreamConsequence.CONTENT_LENGTH_KEY, foto.length);
//        output.setValue(StreamConsequence.STREAM, foto);
//        output.setValue(StreamConsequence.CONTENT_LENGTH, foto.length);
        return SUCCESS;
    }
}
