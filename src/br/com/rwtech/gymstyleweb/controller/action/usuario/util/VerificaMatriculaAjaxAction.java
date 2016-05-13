/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rwtech.gymstyleweb.controller.action.usuario.util;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.validation.ValidationMessage;
import java.util.List;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Software1
 */
public class VerificaMatriculaAjaxAction extends BaseAction {

    private static int TAM = 22;
    private static String RETORNO_MATRICULA = "retornoVerificaMatricula";

    @Override
    public String execute() throws Exception {
        String matricula = input.getString("matricula");
        matricula = matricula.replace("_", "");
        matricula = matricula.replace("-", "");
        matricula = matricula.replace(".", "");

        output.setValue(RETORNO_MATRICULA, "");
        if (matricula != null && !matricula.isEmpty()) {
            if (input.getInt("matricula") == 0) {
                output.setValue(RETORNO_MATRICULA, "<font class='errors2'>Matricula Inválida</font><img src='images/alert.png' title='Matrícula Inválida!'/>");
            } else {
                Long id = input.getLong("id");
                List<Usuario> usuarios = ServiceLocator.getUsuarioService().readMatricula(matricula, id);
                if (usuarios != null && !usuarios.isEmpty()) {
                    String usuario = usuarios.get(0).getUsuario();
                    if (usuario.length() > TAM) {
                        usuario = usuario.substring(0, TAM) + "...";
                    }
                    output.setValue(RETORNO_MATRICULA,
                            "<table class='errors' style='height: 25px;width: 250px;'><tbody>"
                            + "<tr><td><font class='errors'>Matricula Existente:</font></td></tr>"
                            + "<tr><td><a class='errors2' style='text-decoration: underline;' "
                            + "href='" + Ac.USUARIO_UPDATE_INFORMACAO + ".do?id=" + usuarios.get(0).getId() + "'>'"
                            + usuario + "'</a>"
                            + "</td></tr></tbody></table>");
                }
            }
        } else {
            output.setValue(RETORNO_MATRICULA, "<font class='errors2'>" + ValidationMessage.getCampoObrigatorio() + "</font>");
        }
        return SUCCESS;
    }
}
