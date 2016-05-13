package br.com.rwtech.gymstyleweb.controller;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Permissao;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.mentawai.authorization.Group;
import org.mentawai.authorization.Permission;

public final class GroupManager {

    private List<Group> grupos;
    private final String REPORT = "Report";
    private final String PDF = "PDF";
    public static GroupManager instance;

    public static GroupManager getInstance() {
        if (instance == null) {
            instance = new GroupManager();
        }
        return instance;
    }

    private GroupManager() {
        refresh();
        grupos = new ArrayList<>();
    }

    public List<Group> getGroups() {
        return grupos;
    }

    public void refresh() {
        List<Permissao> collection = new ArrayList<>();
        //----------------------------------------------------------------------
        //------------   RELATORIOS   ------------------------------------------
        //----------------------------------------------------------------------

        collection.add(new Permissao(Ac.PAGAMENTO_REPORT, "Pagamento - Imprimir (PDF)", Permissao.GRUPO_RELATORIO));
        collection.add(new Permissao(Ac.PAGAMENTO_REPORT_READ, "Pagamento - Consulta", Permissao.GRUPO_RELATORIO));
        collection.add(new Permissao(Ac.LOG_READ, "Registros de Log", Permissao.GRUPO_RELATORIO));
        collection.add(new Permissao(Ac.REPORT_CAIXA, "Fluxo de Caixa - Consulta", Permissao.GRUPO_RELATORIO, false));
        collection.add(new Permissao(Ac.REPORT_CAIXA_PDF, "Fluxo de Caixa - Imprimir (PDF)", Permissao.GRUPO_RELATORIO, false));
        collection.add(new Permissao(Ac.EVENTO_READ, "Acessos", Permissao.GRUPO_RELATORIO));
        collection.add(new Permissao(Ac.EVENTO_REPORT, "Acessos - Imprimir (PDF)", Permissao.GRUPO_RELATORIO));
        collection.add(new Permissao(Ac.FICHA_REPORT, "Ficha - Imprimir", Permissao.GRUPO_RELATORIO));
        collection.add(new Permissao(Ac.RECIBO_REPORT, "Recibo - Imprimir(PDF)", Permissao.GRUPO_RELATORIO));
        collection.add(new Permissao(Ac.PAGAMENTO_ULTIMO_PLANOS_READ, "Histórico", Permissao.GRUPO_RELATORIO));

        collection.add(new Permissao(Ac.GERAR_RELATORIO));
        collection.add(new Permissao(Ac.USUARIO_REPORT));
        //collection.add(new Permissao(Ac.BOLETO_REPORT));

        //----------------------------------------------------------------------
        //------------   CADASTROS   -------------------------------------------
        //----------------------------------------------------------------------

        collection.add(new Permissao(Ac.USUARIO_READ, "Aluno", Permissao.GRUPO_CADASTRO));
        collection.add(new Permissao(Ac.USUARIO_MANAGER, "Aluno", Permissao.GRUPO_CADASTRO));
        collection.add(new Permissao(Ac.USUARIO_DELETE, "Aluno", Permissao.GRUPO_CADASTRO));

        collection.add(new Permissao(Ac.FUNCIONARIO_READ, "Funcionario", Permissao.GRUPO_CADASTRO));
        collection.add(new Permissao(Ac.FUNCIONARIO_MANAGER, "Funcionario", Permissao.GRUPO_CADASTRO));
        collection.add(new Permissao(Ac.FUNCIONARIO_DELETE, "Funcionario", Permissao.GRUPO_CADASTRO));

        collection.add(new Permissao(Ac.DISPOSITIVO_READ, "Catraca", Permissao.GRUPO_CADASTRO));
        collection.add(new Permissao(Ac.DISPOSITIVO_MANAGER, "Catraca", Permissao.GRUPO_CADASTRO));
        collection.add(new Permissao(Ac.DISPOSITIVO_DELETE, "Catraca", Permissao.GRUPO_CADASTRO));

        collection.add(new Permissao(Ac.FICHA_READ, "Ficha", Permissao.GRUPO_CADASTRO));
        collection.add(new Permissao(Ac.FICHA_MANAGER, "Ficha", Permissao.GRUPO_CADASTRO));
        collection.add(new Permissao(Ac.FICHA_DELETE, "Ficha", Permissao.GRUPO_CADASTRO));

        collection.add(new Permissao(Ac.FORNECEDOR_READ, "Fornecedor", Permissao.GRUPO_CADASTRO, false));
        collection.add(new Permissao(Ac.FORNECEDOR_MANAGER, "Fornecedor", Permissao.GRUPO_CADASTRO, false));
        collection.add(new Permissao(Ac.FORNECEDOR_DELETE, "Fornecedor", Permissao.GRUPO_CADASTRO, false));

        /* Versão 1.8
         collection.add(new Permissao(Ac.CATEGORIA_MANAGER, "Categoria", Permissao.GRUPO_CADASTRO));
         collection.add(new Permissao(Ac.CATEGORIA_READ, "Categoria", Permissao.GRUPO_CADASTRO));
         collection.add(new Permissao(Ac.CATEGORIA_DELETE, "Categoria", Permissao.GRUPO_CADASTRO));

         collection.add(new Permissao(Ac.PRODUTO_MANAGER, "Produto", Permissao.GRUPO_CADASTRO));
         collection.add(new Permissao(Ac.PRODUTO_READ, "Produto", Permissao.GRUPO_CADASTRO));
         collection.add(new Permissao(Ac.PRODUTO_DELETE, "Produto", Permissao.GRUPO_CADASTRO));
         */

        //collection.add(new Permissao(Ac.VENDA_MANAGER));

        //        collection.add(new Permissao(Ac.PERFIL_ACESSO_CREATE));
        //        collection.add(new Permissao(Ac.PERFIL_ACESSO_UPDATE));
        //        collection.add(new Permissao(Ac.PERFIL_ACESSO_DELETE));
        //        collection.add(new Permissao(Ac.PERFIL_ACESSO_READ));

        collection.add(new Permissao(Ac.PLANO_MANAGER, "Plano", Permissao.GRUPO_CADASTRO));
        collection.add(new Permissao(Ac.PLANO_DELETE, "Plano", Permissao.GRUPO_CADASTRO));
        collection.add(new Permissao(Ac.PLANO_READ, "Plano", Permissao.GRUPO_CADASTRO));

        collection.add(new Permissao(Ac.MODALIDADE_READ, "Modalidade", Permissao.GRUPO_CADASTRO));
        collection.add(new Permissao(Ac.MODALIDADE_MANAGER, "Modalidade", Permissao.GRUPO_CADASTRO));
        collection.add(new Permissao(Ac.MODALIDADE_DELETE, "Modalidade", Permissao.GRUPO_CADASTRO));

        collection.add(new Permissao(Ac.CONTA_BANCARIA_MANAGER, "Conta Bancária", Permissao.GRUPO_CADASTRO, false));
        collection.add(new Permissao(Ac.CONTA_BANCARIA_DELETE, "Conta Bancária", Permissao.GRUPO_CADASTRO, false));
        collection.add(new Permissao(Ac.CONTA_BANCARIA_READ, "Conta Bancária", Permissao.GRUPO_CADASTRO, false));

        collection.add(new Permissao(Ac.EXERCICIO_MANAGER, "Exercício", Permissao.GRUPO_CADASTRO));
        collection.add(new Permissao(Ac.EXERCICIO_DELETE, "Exercício", Permissao.GRUPO_CADASTRO));
        collection.add(new Permissao(Ac.EXERCICIO_READ, "Exercício", Permissao.GRUPO_CADASTRO));

        collection.add(new Permissao(Ac.EMPRESA_MANAGER, "Academia", Permissao.GRUPO_CADASTRO));
        collection.add(new Permissao(Ac.EMPRESA_DELETE, "Academia", Permissao.GRUPO_CADASTRO));
        collection.add(new Permissao(Ac.EMPRESA_READ, "Academia", Permissao.GRUPO_CADASTRO));

        collection.add(new Permissao(Ac.AVALIACAO_FISICA_MANAGER, "Avaliação Física", Permissao.GRUPO_CADASTRO));
        collection.add(new Permissao(Ac.AVALIACAO_FISICA_DELETE, "Avaliação Física", Permissao.GRUPO_CADASTRO));
        collection.add(new Permissao(Ac.AVALIACAO_FISICA_READ, "Avaliação Física", Permissao.GRUPO_CADASTRO));

        //----------------------------------------------------------------------
        //------------   CONFIGURAÇÃO   ----------------------------------------
        //----------------------------------------------------------------------
        collection.add(new Permissao(Ac.CONFIGURACAO_BOLETO_READ, "Configuração Boleto - Consultar ", Permissao.GRUPO_CONFIGURACAO, false));
        collection.add(new Permissao(Ac.CONFIGURAR_BOLETO, "Configuração Boleto -  Configurar", Permissao.GRUPO_CONFIGURACAO, false));
        collection.add(new Permissao(Ac.CONFIGURACAO_READ, "Configuração - Consultar", Permissao.GRUPO_CONFIGURACAO));
        collection.add(new Permissao(Ac.CONFIGURACAO_MANAGER, "Configuração - Alterar", Permissao.GRUPO_CONFIGURACAO));

        //----------------------------------------------------------------------
        //------------   GERENCIAR   -------------------------------------------
        //----------------------------------------------------------------------
        collection.add(new Permissao(Ac.USUARIO_PLANOS_READ, "Planos dos Alunos", Permissao.GRUPO_GERENCIAR));
        collection.add(new Permissao(Ac.CANCELAR_PLANO, "Plano - Cancelar", Permissao.GRUPO_GERENCIAR));
        collection.add(new Permissao(Ac.VINCULAR_PLANO, "Plano - Vincular", Permissao.GRUPO_GERENCIAR));

        collection.add(new Permissao(Ac.PAGAMENTO_READ, "Pagamentos", Permissao.GRUPO_GERENCIAR));
        collection.add(new Permissao(Ac.AUTENTICAO_PAGAMENTO_READ, "Autenticar Recibo", Permissao.GRUPO_GERENCIAR));
        collection.add(new Permissao(Ac.PAGAMENTO_ULTIMO_PLANOS_READ));
        collection.add(new Permissao(Ac.PAGAMENTOS_PLANO_USUARIO));

        collection.add(new Permissao(Ac.PAGAR_PARCELA_MANAGER, "Pagar parcela(s)", Permissao.GRUPO_GERENCIAR));

        collection.add(new Permissao(Ac.REQUISICAO_MANAGER, "Atualizações", Permissao.GRUPO_GERENCIAR));
//        collection.add(new Permissao(Ac.REQUISICAO_MANAGER_EXCLUIR));
//        collection.add(new Permissao(Ac.REQUISICAO_ATUALIZAR_USUARIO, "Atualizar Cadastro", Permissao.GRUPO_GERENCIAR));
//        collection.add(new Permissao(Ac.REQUISICAO_ATUALIZAR_TODOS, "Atualizar em Massa", Permissao.GRUPO_GERENCIAR));
//        collection.add(new Permissao(Ac.REQUISICAO_REENVIAR));
//        collection.add(new Permissao(Ac.REQUISICAO_READ, "Atualizar", Permissao.GRUPO_GERENCIAR));

        collection.add(new Permissao(Ac.MANAGER_CAIXA, "Fluxo de Caixa - Movimentar", Permissao.GRUPO_GERENCIAR, false));
        collection.add(new Permissao(Ac.ABRIR_CAIXA, "Fluxo de Caixa - Abrir", Permissao.GRUPO_GERENCIAR, false));
        collection.add(new Permissao(Ac.FECHAR_CAIXA, "Fluxo de Caixa - Fechar", Permissao.GRUPO_GERENCIAR, false));
        collection.add(new Permissao(Ac.ENTRADA_CAIXA, "Fluxo de Caixa - Entrada", Permissao.GRUPO_GERENCIAR, false));
        collection.add(new Permissao(Ac.RETIRADA_CAIXA, "Fluxo de Caixa - Retirada", Permissao.GRUPO_GERENCIAR, false));
        collection.add(new Permissao(Ac.DELETE_REGISTRO_CAIXA, "Fluxo de Caixa - Excluir Registro", Permissao.GRUPO_GERENCIAR, false));

        collection.add(new Permissao(Ac.BANCO_BACKUP_GERAR, "Backup - Gerar", Permissao.GRUPO_GERENCIAR));
        collection.add(new Permissao(Ac.BANCO_BACKUP_RESTAURAR, "Backup - Restaurar", Permissao.GRUPO_GERENCIAR));
        collection.add(new Permissao(Ac.BANCO_BACKUP_DELETE, "Backup - Excluir", Permissao.GRUPO_GERENCIAR));
        collection.add(new Permissao(Ac.BANCO_BACKUP_READ, "Backup - Consultar", Permissao.GRUPO_GERENCIAR));

        collection.add(new Permissao(Ac.MANAGER_CONTA_BANCARIA, "Conta Bancária - Movimentar", Permissao.GRUPO_GERENCIAR, false));
        collection.add(new Permissao(Ac.ENTRADA_CONTA_BANCARIA, "Conta Bancária - Entrada", Permissao.GRUPO_GERENCIAR, false));
        collection.add(new Permissao(Ac.RETIRADA_CONTA_BANCARIA, "Conta Bancária - Retirada", Permissao.GRUPO_GERENCIAR, false));
        collection.add(new Permissao(Ac.DELETE_REGISTRO_CONTA_BANCARIA, "Conta Bancária - Excluir Registro", Permissao.GRUPO_GERENCIAR, false));

        collection.add(new Permissao(Ac.LIBERAR_MANAGER, "Liberar Catraca - Cadastrar", Permissao.GRUPO_GERENCIAR));
        collection.add(new Permissao(Ac.LIBERAR_READ, "Liberar Catraca - Consultar", Permissao.GRUPO_GERENCIAR));
        collection.add(new Permissao(Ac.LIBERAR_DELETE, "Liberar Catraca - Excluir", Permissao.GRUPO_GERENCIAR));
        collection.add(new Permissao(Ac.GERENCIAR_PERMISSOES, "Identificação - Software", Permissao.GRUPO_GERENCIAR));

        collection.add(new Permissao(Ac.LOGIN));

        Collections.sort(collection);

        //        for (Permissao a : collection) {
        //            System.out.println(a.getDescricao());
        //        }
        ServiceLocator.getPermissaoService().create(collection);
    }

    public void updateFalse() {
        String[] acoes = new String[]{Ac.MANAGER_CAIXA,
            Ac.ABRIR_CAIXA, Ac.FECHAR_CAIXA, Ac.ENTRADA_CAIXA,
            Ac.RETIRADA_CAIXA, Ac.DELETE_REGISTRO_CAIXA,
            Ac.CONFIGURACAO_BOLETO_READ, Ac.CONFIGURAR_BOLETO,
            Ac.CONTA_BANCARIA_MANAGER, Ac.CONTA_BANCARIA_READ,
            Ac.CONTA_BANCARIA_DELETE, Ac.RETIRADA_CONTA_BANCARIA, Ac.ENTRADA_CONTA_BANCARIA, Ac.DELETE_REGISTRO_CONTA_BANCARIA,
            Ac.MANAGER_CONTA_BANCARIA,
            Ac.REPORT_CAIXA, Ac.REPORT_CAIXA_PDF,
            Ac.FORNECEDOR_MANAGER, Ac.FORNECEDOR_READ, Ac.FORNECEDOR_DELETE};
        for (String acao : acoes) {
            ServiceLocator.getPermissaoService().update(new Permissao(acao, Boolean.FALSE));
        }
    }

    public Group addPermissions(Long usuario) {
        Group group;
        group = new Group(TipoUsuario.ADMINISTRADOR.toString());
        group.setPermissions(getPermissions(usuario));
        return group;
    }

    private Set<Permission> getPermissions(Long id) {
        Set<Permission> permissoes = new HashSet<>();
        List<Permissao> lista = ServiceLocator.getUsuarioPermissaoService().readByIdUsuario(id);
        if (lista.isEmpty()) {
            lista = getPermissoesDefault(id);
        }
        for (Permissao per : lista) {
            //System.out.println(per.getDescricao());
            permissoes.add(new Permission(per.getNome()));
        }
        return permissoes;
    }

    public List<Permissao> getPermissoesDefault(Long id) {
        List<Permissao> lista = ServiceLocator.getPermissaoService().readbyCriteria(Boolean.TRUE);
        //Senão for administrador, remover as permissoes abaixo
        Usuario usuario = ServiceLocator.getUsuarioService().readById(id);
        if (!usuario.getIsAdm()) {
            String[] auxs = {REPORT, PDF, Ac.REPORT_CAIXA, Ac.REPORT_CAIXA_PDF, Ac.PAGAMENTO_REPORT_READ, Ac.PAGAMENTO_REPORT,
                Ac.LOG_READ, Ac.BANCO_BACKUP_GERAR, Ac.BANCO_BACKUP_RESTAURAR, Ac.BANCO_BACKUP_DELETE};
            for (String aux : auxs) {
                for (int i = 0; i < lista.size(); i++) {
                    if (lista.get(i).getNome().equalsIgnoreCase(aux)) {
                        lista.remove(i);
                        break;
                    }
                }
            }
        }
        return lista;
    }

    public static enum TipoUsuario {

        ALUNO, ADMINISTRADOR, SECRETARIA, INSTRUTOR
    }
}
