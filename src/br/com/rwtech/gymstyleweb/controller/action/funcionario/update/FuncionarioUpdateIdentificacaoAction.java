package br.com.rwtech.gymstyleweb.controller.action.funcionario.update;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Permissao;
import br.com.rwtech.gymstyleweb.controller.GroupManager;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.usuario.UsuarioAction;
import br.com.rwtech.gymstyleweb.controller.action.usuario.update.UpdateIdentificacaoAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class FuncionarioUpdateIdentificacaoAction extends UpdateIdentificacaoAction {

    @Override
    public String execute() throws Exception {
        Long idUsuario = input.getLong("id");
        String consequence = SHOW;
        if (idUsuario != null) {
            if (isPost() && input.getValue("cartaoProximidade") != null) {
                consequence = post(idUsuario);
                salvarPermissoes();
                if (consequence.equalsIgnoreCase(UsuarioAction.ATUALIZAR)) {
                    output.setValue("retorno", Ac.FUNCIONARIO_READ + Ac.DO);
                }
            } else {
                output.setValue("id", idUsuario);
                ServiceLocator.getImpressaoDigitalService().preDigitais(idUsuario);
                //Dentro do preload, buscará as digitais da tabela digitais_espelho
                this.preload(output, input);
                Map<String, Permissao> cadastros = getPermissoesCadastro();
                output.setValue(Permissao.GRUPO_CADASTRO, cadastros);
                output.setValue(Permissao.GRUPO_GERENCIAR, ServiceLocator.getPermissaoService().readbyCriteria(Boolean.TRUE, Permissao.GRUPO_GERENCIAR));
                output.setValue(Permissao.GRUPO_RELATORIO, ServiceLocator.getPermissaoService().readbyCriteria(Boolean.TRUE, Permissao.GRUPO_RELATORIO));
                output.setValue(Permissao.GRUPO_CONFIGURACAO, ServiceLocator.getPermissaoService().readbyCriteria(Boolean.TRUE, Permissao.GRUPO_CONFIGURACAO));

                checkedRetorno(idUsuario);
            }
        } else {
            consequence = Ac.EXCEPTION;
            session.setAttribute("mensagem", "Não foi possível Alterar. Tente novamente!");
        }
        return consequence;
    }

    public void salvarPermissoes() {
        if (input.getValue("login") != null) {
            Long idUsuario = input.getLong("id");
            if (!input.getString("login").isEmpty()) {
                if (!input.getString("senha").isEmpty()) {
                    ServiceLocator.getUsuarioService().updateLoginSenha(idUsuario, input.getString("login"), input.getString("senha"));
                }
                //vai entrar aqui se houver permissoes
                List<Permissao> list = new ArrayList<>();
                list.addAll(checkados(Permissao.GRUPO_CONFIGURACAO));
                list.addAll(checkados(Permissao.GRUPO_GERENCIAR));
                list.addAll(checkados(Permissao.GRUPO_RELATORIO));
                for (String per : getPermissoesCadastro().keySet()) {
                    if (input.getValue(per + "Read") != null) {
                        list.add(ServiceLocator.getPermissaoService().readByName(per + "Read"));
                    }
                    if (input.getValue(per + "Manager") != null) {
                        list.add(ServiceLocator.getPermissaoService().readByName(per + "Manager"));
                    }
                    if (input.getValue(per + "Delete") != null) {
                        list.add(ServiceLocator.getPermissaoService().readByName(per + "Delete"));
                    }
                }
                if (!list.isEmpty()) {
                    ServiceLocator.getUsuarioPermissaoService().create(list, idUsuario);
                }
            } else {
                ServiceLocator.getUsuarioService().updateLoginSenha(idUsuario, "", "");
                ServiceLocator.getUsuarioPermissaoService().deleteByIdUsuario(idUsuario);
            }
        }
    }

    private List<Permissao> checkados(String grupo) {
        List<Permissao> list = ServiceLocator.getPermissaoService().readbyCriteria(Boolean.TRUE, grupo);
        List<Permissao> lista = new ArrayList<>();
        for (Permissao per : list) {
            if (input.getValue(per.getNome()) != null) {
                lista.add(ServiceLocator.getPermissaoService().readByName(per.getNome()));
            }
        }
        return lista;
    }

    public static Map<String, Permissao> getPermissoesCadastro() {
        Map<String, Permissao> set = new HashMap<>();
        List<Permissao> list = ServiceLocator.getPermissaoService().readbyCriteria(Boolean.TRUE, Permissao.GRUPO_CADASTRO);
        for (Permissao per : list) {
            String aux = per.getNome();
            aux = aux.replace(Ac.READ, "");
            aux = aux.replace(Ac.MANAGER, "");
            aux = aux.replace(Ac.DELETE, "");
            set.put(aux, per);
        }
        return set;
    }

    private void checkedRetorno(Long idUsuario) {
        List<Permissao> list = ServiceLocator.getUsuarioPermissaoService().readByIdUsuario(idUsuario);
        if (list.isEmpty()) {
            list = GroupManager.getInstance().getPermissoesDefault(idUsuario);
        }
        for (Permissao per : list) {
            output.setValue(per.getNome() + "Check", "checked");
        }
    }
}
