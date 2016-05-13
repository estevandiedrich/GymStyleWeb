package br.com.rwtech.gymstyleweb.controller.action;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstyleweb.controller.GroupManager;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.contabancaria.ContaBancariaReadAction;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import org.mentawai.action.BaseLoginAction;
import org.mentawai.authorization.AuthorizationManager;
import org.mentawai.authorization.Group;
import org.mentawai.authorization.Permission;

public class LoginAction extends BaseLoginAction {

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String username = input.getString("username");
        String password = input.getString("password");
        if (isPost()) {
            if (username != null && password != null) {
                //if (VerificaStatusBancoService.analisar()) { //Colocar no init do aplicationManager para atualizar o banco ao fazer deploy
                Usuario usuario = ServiceLocator.getUsuarioService().login(username, password);
                if (usuario != null) {
                    consequence = SUCCESS;

                    ContaBancariaReadAction.confereContas(this.getApplication());
                    //Colocado este try para tentar setar a sessão antes de extourar o erro na tela
                    try {
                        this.setUserSession(usuario); //Mentawai pede.
                    } catch (Exception e) {
                    }
                    session.setAttribute(SessaoAtr.ID_FLUXO_CAIXA, ServiceLocator.getFluxoCaixaService().idCaixaAberto());

                    if (usuario.getTipoUsuario() == 2) {
                        consequence = ERROR;
                        setMensagemErroLogar();
                    } else {
                        //AuthorizationManager.loadPermissions(idGrupo, usuarioAutenticado.getPermissoes());
                        Group group = GroupManager.getInstance().addPermissions(usuario.getId());
                        group.getPermissions();
                        
                        for (Permission per : group.getPermissions()) {
                            if(per.getName().equalsIgnoreCase(Ac.PAGAMENTO_ULTIMO_PLANOS_READ)){
                                //para verificar nos ultimos eventos
                                this.getSession().setAttribute(Ac.PAGAMENTO_ULTIMO_PLANOS_READ, true);
                                break;
                            }
                        }
                        
                        AuthorizationManager.addGroup(group);
                        this.setUserGroup(group);
                    }
                    //se chegar com error é pq é usuário Aluno
                    if (!consequence.equalsIgnoreCase(ERROR)) {
                        //Esvaziar os campos de login e senha do usuário
                        usuario.setLogin("");
                        usuario.setSenha("");
                        //AuthorizationManager
                        //Facilidade - jogar o objeto usuario para o atributo tb da sessao.
                        this.getSession().setAttribute(SessaoAtr.USUARIO_LOGADO, usuario);
                        this.getSession().setAttribute(SessaoAtr.DATA_LOGIN, formato.format(new Date()));
                    }
                } else {
                    setMensagemErroLogar();
                }
            }
        }
        output.setValue("password", "");
        return consequence;
    }

    private void setMensagemErroLogar() {
        addMessage("Não foi possível realizar o acesso!");
        output.setValue("loginError", "Não foi possível realizar o acesso.");
        output.setValue("username", "");
        output.setValue("password", "");

    }
}
