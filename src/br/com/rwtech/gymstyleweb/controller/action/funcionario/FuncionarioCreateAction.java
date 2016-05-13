package br.com.rwtech.gymstyleweb.controller.action.funcionario;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstylecore.model.util.Validador;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import org.apache.commons.fileupload.FileItem;
import org.mentawai.core.BaseAction;
import org.mentawai.core.StreamConsequence;

/**
 *
 * @author Éder Faria
 */
public class FuncionarioCreateAction extends BaseAction {

    static final long serialVersionUID = 1L;
    private byte[] foto = null;
    
    private static String PREVIEW = Ac.FUNCIONARIO_CREATE_INFORMACAO + ".preview.do";

    @Override
    public String execute() throws Exception {
        String cpf = (String) input.getValue(FuncionarioAction.CPF);
        String consequence = SHOW;

        if (isPost() && !Validador.existCPF(cpf, null)) {
            Usuario pojo = (Usuario) input.getValue("VOfuncionario");

            pojo.setFoto(foto);
            pojo.setDataNascimento(CalendarUtil.setDateCalendar((String) input.getValue("dataNascimentoFormat")));
            String idTipoUsuario = (String) input.getValue("idTipoUsuario");
            if (!idTipoUsuario.equals("")) {
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
            //No criar funcionario set para false
            pojo.setAtivoAluno(Boolean.FALSE);
            pojo.setIsAluno(Boolean.FALSE);

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
            if (input.getValue(FuncionarioAction.ACAO) != null && input.getString(FuncionarioAction.ACAO).equals(FuncionarioAction.IDENTIFICACAO)) {
                consequence = FuncionarioAction.IDENTIFICACAO;
            } else {
                consequence = SUCCESS;
            }
        } else {
            foto = null;
            FuncionarioAction.preload(output, input);
            output.setValue("retornoVerificaCpf", "");
            output.setValue("retornoVerificaMatricula", "");
        }

        return consequence;
    }

    public String definir() {
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
        output.setValue(FuncionarioAction.FOTO, "images/user.png");
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