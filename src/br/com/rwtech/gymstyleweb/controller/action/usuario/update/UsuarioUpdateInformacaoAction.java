package br.com.rwtech.gymstyleweb.controller.action.usuario.update;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Requisicao;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.pojo.tipos.TipoRequisicaoResposta;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstylecore.model.util.StatusPlano;
import br.com.rwtech.gymstylecore.model.util.Validador;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.usuario.UsuarioAction;
import org.apache.commons.fileupload.FileItem;
import org.mentawai.core.BaseAction;
import org.mentawai.core.Input;
import org.mentawai.core.Output;
import org.mentawai.core.StreamConsequence;

public class UsuarioUpdateInformacaoAction extends BaseAction {

    static final long serialVersionUID = 1L;
    private byte[] foto = null;

    @Override
    public String execute() throws Exception {
        Long idUsuario = input.getLong("id");
        String cpf = (String) input.getValue("cpf");
        if (idUsuario == -1) {
            idUsuario = (Long) session.getAttribute("idUsuario");
            session.removeAttribute("idUsuario");
        }
        String consequence = SHOW;
        if (idUsuario != null) {
            if (isPost() && input.getValue("usuario") != null && !Validador.existCPF(cpf, idUsuario)) {
                //postergar = false;
                Usuario pojo = (Usuario) input.getValue("VOusuario");
                pojo.setFoto(foto);
                pojo.setDataNascimento(CalendarUtil.setDateCalendar((String) input.getValue("dataNascimentoFormat")));
                pojo.setDataCadastro(CalendarUtil.setDateCalendar((String) input.getValue("dataCadastroFormat")));
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


                ServiceLocator.getUsuarioService().update(pojo);
                output.setValue("idPlanoUsuario", ServiceLocator.getUsuarioPlanoService().readIdUltimoPlanoUsuario(idUsuario));//esse id será passado para o manager de requisicao buscar os dispositivos a serem atualizados

                if (input.getValue(UsuarioAction.ACAO) != null && input.getString(UsuarioAction.ACAO).equals(UsuarioAction.IDENTIFICACAO)) {
                    consequence = UsuarioAction.IDENTIFICACAO;
                } else {
                    session.setAttribute("mensagem", "Aluno Alterado com Suceso!");
                    consequence = SUCCESS;
                }

            } else {
                output.setValue("id", idUsuario);
                this.preload(output, input);
                if (output.getValue("pojoInfo") == null) {
                    consequence = UsuarioAction.INESPERADO;
                    session.setAttribute("mensagem", "Não foi possível Alterar. Tente novamente!");
                }
                output.setValue("retornoVerificaMatricula", "");
            }
        } else {
            consequence = UsuarioAction.INESPERADO;
            session.setAttribute("mensagem", "Não foi possível Alterar. Tente novamente!");
        }
        return consequence;
    }

    public void atualizar(Long idUsuario) {
        Requisicao requisicao = new Requisicao();
        requisicao.setOperador((Usuario) getUserSession());
        requisicao.setParametro(idUsuario);
        requisicao.setStatus(Boolean.FALSE);
        StatusPlano status = ServiceLocator.getUsuarioPlanoService().readStatusUltimoPlano(idUsuario);
        if (status.equals(StatusPlano.CANCELADO) || status.equals(StatusPlano.FORA_PERIODO_ACESSO)) {
            requisicao.setTipo(TipoRequisicaoResposta.EXCLUIR_USUARIO);
        } else {
            requisicao.setTipo(TipoRequisicaoResposta.CADASTRAR_USUARIO);
        }
        requisicao.setDestino(null);
        ServiceLocator.getRequisicaoService().create(requisicao);
        output.setValue("idPlanoUsuario", ServiceLocator.getUsuarioPlanoService().readIdUltimoPlanoUsuario(idUsuario));//esse id será passado para o manager de requisicao buscar os dispositivos a serem atualizados
        output.setValue("idRequisicao", requisicao.getId());
        output.setValue("retorno", Ac.PAGAMENTO_READ + Ac.DO);
        session.setAttribute("mensagem", "Pagamento realizado com sucesso!");
    }

    public void setMensagem() {
        String msg = "";
        if (session.getAttribute("mensagem") != null) {
            msg = session.getAttribute("mensagem").toString();
            session.setAttribute("mensagem", "");
        }
        if (!msg.equalsIgnoreCase("")) {
            addMessage(msg);
        }
    }
    private String PREVIEW = Ac.USUARIO_UPDATE_INFORMACAO + ".preview.do";

    public String definir() {
        output.setValue(UsuarioAction.NAVEGADOR, session.getAttribute(UsuarioAction.NAVEGADOR));
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

        if (foto != null) {
            output.setValue(UsuarioAction.FOTO, PREVIEW);
        } else {
            output.setValue(UsuarioAction.FOTO, "images/user.png");
        }
        return SUCCESS;
    }

    public String preview() {
        output.setValue(UsuarioAction.NAVEGADOR, session.getAttribute(UsuarioAction.NAVEGADOR));
        output.setValue(StreamConsequence.STREAM_KEY, foto);
        output.setValue(StreamConsequence.CONTENT_LENGTH_KEY, foto.length);
//        output.setValue(StreamConsequence.STREAM, foto);
//        output.setValue(StreamConsequence.CONTENT_LENGTH, foto.length);
        return SUCCESS;
    }

    public void preload(Output output, Input input) {
        UsuarioAction.preload(output, input);
        ApplicationManager.getRealPath();

        Long id = (Long) output.getValue("id");
        output.setValue("login", "");
        output.setValue("senha", "");

        if (id != null) {
            if (id != null && id > 0) {
                Usuario pojo = ServiceLocator.getUsuarioService().readById(id);
                if (pojo.getMatricula() == null) {
                    pojo.setMatricula(ServiceLocator.getUsuarioService().readNextMatricula());
                }
                output.setValue("pojo", pojo);
                output.setValue("pojoInfo", pojo);//pojo é para aparecer as informações do usuário em cima na tela

                if (pojo != null) {
                    output.setValue("cartaoProximidade2", pojo.getCartaoProximidade());//pojo é para aparecer as informações do usuário em cima na tela
                    if (pojo.getDataNascimento() != null) {
                        output.setValue("dataNascimentoFormat", CalendarUtil.getDateCalendar(pojo.getDataNascimento()));
                    }
                    if (pojo.getDataCadastro() != null) {
                        output.setValue("dataCadastroFormat", CalendarUtil.getDateCalendar(pojo.getDataCadastro()));
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
                        output.setValue("foto", Ac.USUARIO_UPDATE_INFORMACAO + ".preview.do");
                    } else {
                        output.setValue("foto", "images/user.png");
                    }
                    output.setValue("select", Ac.USUARIO_READ);
                }
            }
        }
    }
}