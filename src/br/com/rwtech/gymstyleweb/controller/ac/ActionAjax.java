package br.com.rwtech.gymstyleweb.controller.ac;

import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import br.com.rwtech.gymstyleweb.controller.action.banco.BancoAjaxReadAction;
import br.com.rwtech.gymstyleweb.controller.action.contabancaria.ContaBancariaReadByIdAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.contabancaria.registros.RegistroContaBancariaReadAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.digital.ImpressaoDigitalAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.digital.ImpressaoDigitalCreateAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.digital.ImpressaoDigitalUsuarioReadAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.digital.ImpressaoDigitalUsuarioSelectAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.perfilacesso.ImportarPerfilAcessoAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.usuario.UsuarioAjaxReadAction;
import br.com.rwtech.gymstyleweb.controller.action.usuario.foto.UsuarioButtomCreateFotoAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.usuario.foto.UsuarioButtonUpdateFotoAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.usuario.util.BuscaAppletAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.usuario.util.ImportarPlanoAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.usuario.util.VerificaCpfAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.usuario.util.VerificaLoginAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.usuario.util.VerificaMatriculaAjaxAction;
import br.com.rwtech.gymstyleweb.controller.action.usuarioplano.ParcelasAjaxAction;
import org.mentawai.core.ActionConfig;
import org.mentawai.core.Forward;

/**
 *
 * @author Software1
 */
public class ActionAjax extends Action {

    public static Action instance = null;
    public static Action addActions(ApplicationManager am) {
        if (instance == null) {
            instance = new ActionAjax();
            instance.am = am;
            instance.createActions();
        }
        return (instance);
    }

    private ActionAjax() {
    }

    protected void createActions() {
        //AJAX PACELAS
        ActionConfig ac = new ActionConfig(PARCELAS_AJAX, ParcelasAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + "usuarioplano/parcelas.jsp"));
        am.addActionConfig(ac);

        //AJAX PACELAS
        ac = new ActionConfig(VERIFICA_CPF, VerificaCpfAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + "usuario/verificaCpf.jsp"));
        am.addActionConfig(ac);

        //AJAX PACELAS
        ac = new ActionConfig(VERIFICA_MATRICULA, VerificaMatriculaAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + "usuario/verificaMatricula.jsp"));
        am.addActionConfig(ac);

        //AJAX PACELAS
        ac = new ActionConfig(VERIFICA_LOGIN, VerificaLoginAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + "usuario/verificaLogin.jsp"));
        am.addActionConfig(ac);

        //AJAX DIGITAIS
        ac = new ActionConfig(DIGITAL_AJAX, ImpressaoDigitalAjaxAction.class, "read");
        ac.addConsequence(SUCCESS, new Forward(JSP + "digitais/digitalRead.jsp"));
        am.addActionConfig(ac);

        //AJAX DIGITAIS
        ac = new ActionConfig(DIGITAL_AJAX, ImpressaoDigitalAjaxAction.class, "delete");
        ac.addConsequence(SUCCESS, new Forward(JSP + "digitais/digitalRead.jsp"));
        am.addActionConfig(ac);

        //AJAX DIGITAIS CREATE VIA CATRACA
        ac = new ActionConfig(DIGITAL_AJAX, ImpressaoDigitalAjaxAction.class, "create");
        ac.addConsequence(SUCCESS, new Forward(JSP + "digitais/resultSuccess.jsp"));
        ac.addConsequence(ERROR, new Forward(JSP + "digitais/returnError.jsp"));
        am.addActionConfig(ac);

        //AJAX DIGITAIS CREATE VIA HAMISTER
        ac = new ActionConfig(DIGITAL_CREATE, ImpressaoDigitalCreateAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + "digitais/resultSuccess.jsp"));
        am.addActionConfig(ac);

        //AJAX DIGITAIS READ DIGITAIS USUARIO
        ac = new ActionConfig(DIGITAIS_USUARIO, ImpressaoDigitalUsuarioReadAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + "digitais/templates.jsp"));
        am.addActionConfig(ac);

        //AJAX DIGITAIS PARA O SELECT DO CADASTRAR USUÁRIO.
        ac = new ActionConfig(SELECT_DEDOS, ImpressaoDigitalUsuarioSelectAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + "digitais/selectDedos.jsp"));
        am.addActionConfig(ac);

        //AJAX QUE RETORNA O APPLET
        ac = new ActionConfig(BUSCA_APPLET, BuscaAppletAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + "digitais/applet.jsp"));
        am.addActionConfig(ac);

        //IMPORTAR PLANO
        ac = new ActionConfig(IMPORTAR_PLANO_AJAX, ImportarPlanoAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + "usuario/plano/infoPlanoUsuario.jsp"));
        am.addActionConfig(ac);

        //IMPORTAR HORARIO AJAX
        ac = new ActionConfig(IMPORTAR_HORARIO_AJAX, ImportarPerfilAcessoAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + "modalidade/horarioForm.jsp"));
        am.addActionConfig(ac);

        //IMPORTAR BUTTOM FOTO CREATE
        ac = new ActionConfig(IMPORTAR_BUTTON_FOTO_CREATE_AJAX, UsuarioButtomCreateFotoAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + "usuario/foto/buttonCameraCreate.jsp"));
        am.addActionConfig(ac);

        //IMPORTAR BUTTOM FOTO UPDATE
        ac = new ActionConfig(IMPORTAR_BUTTON_FOTO_UPDATE_AJAX, UsuarioButtonUpdateFotoAjaxAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + "usuario/foto/buttonCameraUpdate.jsp"));
        am.addActionConfig(ac);

        //CONSULTA BANCOS
        ac = new ActionConfig(BANCO_AJAX_READ, BancoAjaxReadAction.class);
        ac.addConsequence(SUCCESS, new Forward(JSP + "contabancaria/bancos.jsp"));
        am.addActionConfig(ac);

        //CONSULTA USUARIOS
        ac = new ActionConfig(USUARIO_AJAX_READ, UsuarioAjaxReadAction.class);
        ac.addConsequence(VENDA, new Forward(JSP + "venda/usuarios.jsp"));
        am.addActionConfig(ac);

        //CONSULTA INFORMAÇOES DA CONTA
        ac = new ActionConfig(CONTA_BANCARIA_READ_BY_ID_AJAX, ContaBancariaReadByIdAjaxAction.class);
        ac.addConsequence(SHOW, new Forward(JSP + "contabancaria/manager/infoConta.jsp"));
        am.addActionConfig(ac);

        //CONSULTA BANCOS
        ac = new ActionConfig(REGISTRO_CONTA_BANCARIA_READ_AJAX, RegistroContaBancariaReadAjaxAction.class);
        ac.addConsequence(SHOW, new Forward(JSP + "contabancaria/manager" + CORPO_JSP));
        am.addActionConfig(ac);
    }
}