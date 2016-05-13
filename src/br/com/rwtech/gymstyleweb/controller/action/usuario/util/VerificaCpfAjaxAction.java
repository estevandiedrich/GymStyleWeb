package br.com.rwtech.gymstyleweb.controller.action.usuario.util;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.Validador;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class VerificaCpfAjaxAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String tipo = input.getString("tipo");
        String href = "";
        if (tipo.equalsIgnoreCase("aluno")) {
            href = "href='"+Ac.USUARIO_UPDATE_INFORMACAO+".do?id=";
        } else {
            href = "href='"+Ac.FUNCIONARIO_UPDATE_INFORMACAO+".do?id=";
        }

        String cpf = input.getString("cpf");
        cpf = cpf.replace("_", "");
        cpf = cpf.replace("-", "");
        cpf = cpf.replace(".", "");
        //06332235659
        output.setValue("retornoVerificaCpf", "");
        if (cpf.length() == 11) {
            Map<String, Object> mapa = new HashMap<String, Object>();
            mapa.put("criterioCpf", cpf);
            mapa.put("criterioAtivo", "truefalse");
            List<Usuario> usuarios = ServiceLocator.getUsuarioService().readByCriteria(mapa);
            if (usuarios != null && !usuarios.isEmpty()) {
                Usuario usu = usuarios.get(0);
                String usuario = usu.getUsuario();
                if (usuario.length() > 15) {
                    usuario = usuario.substring(0, 15) + "...";
                }
                output.setValue("retornoVerificaCpf", "<table class='errors' style='height: 25px;width: 250px;'><tbody>"
                        + "<tr><td><font class='errors'>CPF Existente: "
                        + ((usu.getIsAdm() || usu.getIsInstrutor() || usu.getIsSecretaria()) ? "Funcionário" : "")
                        + "</font></td></tr><tr><td>"
                        + "<a class='errors' style='text-decoration: underline;' "
                        + href + usu.getId() + "'"
                        + " title='Selecione para Importar Dados!'>'"
                        //+ (usu.getAtivoAluno() ? "" : " Inativo: ")
                        + "<span class='error' style='font-style: italic'>"+usuario+"</span>- Importar?"
                        + "'</a>"
                        + "</td></tr></tbody></table>");
            } else {
                if (!Validador.isValidCPF(cpf)) {
                    output.setValue("retornoVerificaCpf", "<font class='errors'>" + ValidationMessage.getCpfInvalido() + "</font>");
                }
            }
        }
        return SUCCESS;
    }
}
