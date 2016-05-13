package br.com.rwtech.gymstyleweb.controller.action.log;

import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class LogAction {

    //Ao Addicionar um novo tipo é necessário adicionar no mapa de tipos na classe LogReadAction
    public static String RESTAUROU = "Restaurou";
    public static String CRIOU = "Criação";
    public static String EXCLUIU = "Exclusão";
    public static String BUSCA = "Busca";
    public static String SAIDA = "Saída";
    public static String ALTEROU = "Alteração";
    public static String ATUALIZOU = "Atualizou";
    public static String CONSULTOU = "Consulta";
    public static String CONFIGUROU = "Configurou";
    public static String CANCELOU = "Cancelou";
    public static String VISUALIZOU = "Visualizar";
    public static String VINCULAR = "Vinculou";
    public static String PAGAMENTO = "Pagamento";
    public static String LIBEROU = "Liberar";
    public static String ABERTURA = "Abertura";
    public static String FECHAMENTO = "Fechamento";
    public static String RETIRADA = "Retirada";
    public static String ENTRADA = "Entrada";
    private static Map<String, String> map = null;

    public static Map<String, String> getMapAction() {
        if (map == null) {
            map = new HashMap<String, String>();
            map.put(Ac.MAIN, "Página principal da aplicação");

            map.put(Ac.REQUISICAO_MANAGER, "");
            map.put(Ac.REQUISICAO_MANAGER, "requisicaoManager");
            map.put(Ac.REQUISICAO_MANAGER_EXCLUIR, "Excluiu aluno na catraca");
            map.put(Ac.REQUISICAO_ATUALIZAR_USUARIO, "Atualizou aluno");
            map.put(Ac.REQUISICAO_ATUALIZAR_TODOS, "Atualizar todos os alunos");
            map.put(Ac.REQUISICAO_REENVIAR, "Reenviou atualizar aluno");
            map.put(Ac.REQUISICAO_READ, "requisicao");
            //PAGAMENTOS
            map.put(Ac.PAGAMENTO_FILTER, "Relatório de pagamento");
            map.put(Ac.PAGAMENTO_REPORT, "Gerou: Relatório de  pagamento no formato PDF");
            map.put(Ac.PAGAMENTO_READ, "Pagamentos");
            map.put(Ac.PAGAMENTO_ULTIMO_PLANOS_READ, "Alunos com Planos");
            map.put(Ac.PAGAMENTOS_PLANO_USUARIO, "Ultimo plano do aluno");
            map.put(Ac.VER_PLANO_USUARIO, " Plano do aluno");
            map.put(Ac.LANCAR_PARCELAS_PLANO_USUARIO, "Efetuou pagamento de parcelas");
            map.put(Ac.VER_PARCELA_PLANO_USUARIO, " Parcela do plano");
            map.put(Ac.PAGAR_PARCELA_PLANO_USUARIO, "Efetou o pagamento de Parcela");
            map.put(Ac.PAGAR_NOVA_PARCELA, "Efetuou pagamento de nova parcela");
            map.put(Ac.FINALIZAR_PLANO, "Finalizou Plano");
            map.put(Ac.NOVA_PARCELA_MENSAL, "Nova parcela mensal");
            //LOG
            map.put(Ac.LOG_READ, "Registros de log");
            //USUARIO
            map.put(Ac.USUARIO_READ, "Aluno");
            map.put(Ac.USUARIO_CREATE_INFORMACAO, "Aluno");
            map.put(Ac.USUARIO_UPDATE_INFORMACAO, "Aluno");
            map.put(Ac.USUARIO_UPDATE_IDENTIFICACAO, "Aluno");
            map.put(Ac.USUARIO_UPDATE_PLANO, "Aluno");
            map.put(Ac.USUARIO_DELETE, "Aluno");

            //FUNCIONARIO
            map.put(Ac.FUNCIONARIO_READ, "Funcionário");
            map.put(Ac.FUNCIONARIO_CREATE_INFORMACAO, "Funcionário");
            map.put(Ac.FUNCIONARIO_UPDATE_INFORMACAO, "Funcionário");
            map.put(Ac.FUNCIONARIO_UPDATE_IDENTIFICACAO, "Funcionário");
            map.put(Ac.FUNCIONARIO_UPDATE_ACESSO, "Funcionário");
            map.put(Ac.FUNCIONARIO_DELETE, "Funcionário");

            //CAIXA
            map.put(Ac.MANAGER_CAIXA, "Consultou Caixa");
            map.put(Ac.ABRIR_CAIXA, "Abriu Caixa");
            map.put(Ac.FECHAR_CAIXA, "Fechou o caixa");
            map.put(Ac.ENTRADA_CAIXA, "Entrada com valor no caixa");
            map.put(Ac.RETIRADA_CAIXA, "Retirou valor no caixa");
            map.put(Ac.DELETE_REGISTRO_CAIXA, "Excluiu registro de caixa");
            //CONTA BANCARIA
            map.put(Ac.MANAGER_CONTA_BANCARIA, "Consultou Caixa");
            map.put(Ac.ENTRADA_RETIRADA_CONTA_BANCARIA, "Entrada com valor no caixa");
            //CONTA BANCARIA
            map.put(Ac.CONFIGURACAO_BOLETO_READ, "Consultou Configuração de Boleto");
            map.put(Ac.CONFIGURAR_BOLETO, "Configurou Boleto");

            map.put(Ac.CONTA_BANCARIA_CREATE, "Conta Bancária");
            map.put(Ac.CONTA_BANCARIA_UPDATE, "Conta Bancária");
            map.put(Ac.CONTA_BANCARIA_DELETE, "Conta Bancária");
            map.put(Ac.CONTA_BANCARIA_READ, "Conta Bancária");
            map.put(Ac.CONTA_BANCARIA_READ_BY_ID_AJAX, "Conta Bancária");
            map.put(Ac.REGISTRO_CONTA_BANCARIA_READ_AJAX, "Conta Bancária");
            map.put(Ac.DELETE_REGISTRO_CONTA_BANCARIA, "Excluiu Registro de Conta Bancária");

            //map.put(Ac.RETIRADA_CAIXA, "Retirou valor no caixa");
            //DISPOSITIVO
            map.put(Ac.DISPOSITIVO_READ, "Catraca");
            map.put(Ac.DISPOSITIVO_CREATE, "Catraca");
            map.put(Ac.CONFIGURAR_CATRACA, "Configuração da Catraca");
            map.put(Ac.REENVIAR_CONFIGURAR_CATRACA, "Reenviou configurar catraca");
            map.put(Ac.DISPOSITIVO_UPDATE, "Catraca");
            map.put(Ac.DISPOSITIVO_DELETE, "Catraca");
            map.put(Ac.BUSCAR_EVENTOS, "Buscou eventos da catraca");
            map.put(Ac.DISPOSITIVO_BUSCAR, "Buscou catracas na rede");
            map.put(Ac.APAGAR_USUARIO_DISPOSITIVO, "Apagou aluno da catraca");
            map.put(Ac.LIMPAR_DISPOSITIVO, "Limpou Catraca");
            //LIBERAR
            map.put(Ac.LIBERAR_CATRACA, "Liberou Catraca");
            map.put(Ac.LIBERAR_READ, "Liberações da catraca");
            map.put(Ac.LIBERAR_DELETE, "Liberação");
            map.put(Ac.LIBERAR_INFO, "Liberação");
            //AUTENTICACAO READ
            map.put(Ac.AUTENTICAO_PAGAMENTO_READ, "Autênticação");
            //USUARIO FICHA
            map.put(Ac.FICHA_INFO, "Ficha");
            map.put(Ac.FICHA_READ, "Fichas de Treino");
            map.put(Ac.USUARIO_FICHAS_READ, " Fichas de aluno");
            map.put(Ac.FICHA_CREATE, "Ficha");
            map.put(Ac.FICHA_DELETE, "Ficha");
            map.put(Ac.ATUALIZAR_STATUS_FICHA, "Atualizou:Status da ficha");
            //EMPRESA
            map.put(Ac.EMPRESA_UPDATE, "Academia");
            map.put(Ac.EMPRESA_READ, "Academia");
            //TIPO_USUARIO
            map.put(Ac.TIPO_USUARIO_CREATE, "tipoUsuarioCreate");
            map.put(Ac.TIPO_USUARIO_UPDATE, "tipoUsuario");
            map.put(Ac.TIPO_USUARIO_READ, "tipoUsuario");
            map.put(Ac.TIPO_USUARIO_DELETE, "tipoUsuario");
            //PERFIL_ACESSO
            map.put(Ac.PERFIL_ACESSO_CREATE, "Perfil de acesso");
            map.put(Ac.PERFIL_ACESSO_UPDATE, "Perfil de acesso");
            map.put(Ac.PERFIL_ACESSO_DELETE, "Perfil de acesso");
            map.put(Ac.PERFIL_ACESSO_READ, "Perfil de acesso");
            //PLANO
            map.put(Ac.PLANO_CREATE, "Plano");
            map.put(Ac.PLANO_UPDATE, "Plano");
            map.put(Ac.PLANO_DELETE, "Plano");
            map.put(Ac.PLANO_READ, "Plano");
            //PRODUTO
            map.put(Ac.PRODUTO_CREATE, "Produto");
            map.put(Ac.PRODUTO_UPDATE, "Produto");
            map.put(Ac.PRODUTO_DELETE, "Produto");
            map.put(Ac.PRODUTO_READ, "Produto");
            //CATEGORIA
            map.put(Ac.CATEGORIA_CREATE, "Categoria");
            map.put(Ac.CATEGORIA_UPDATE, "Categoria");
            map.put(Ac.CATEGORIA_DELETE, "Categoria");
            map.put(Ac.CATEGORIA_READ, "Categoria");
            //MODALIDADE
            map.put(Ac.MODALIDADE_CREATE, "Modalidade");
            map.put(Ac.MODALIDADE_UPDATE, "Modalidade");
            map.put(Ac.MODALIDADE_DELETE, "Modalidade");
            map.put(Ac.MODALIDADE_READ, "Modalidade");
            //EXERCICIO
            map.put(Ac.EXERCICIO_CREATE, "Exercício");
            map.put(Ac.EXERCICIO_UPDATE, "Exercício");
            map.put(Ac.EXERCICIO_DELETE, "Exercício");
            map.put(Ac.EXERCICIO_READ, "Exercício");
            //AVALIACAO_FISICA
            map.put(Ac.AVALIACAO_FISICA_CREATE, "Avaliação Física");
            map.put(Ac.AVALIACAO_FISICA_UPDATE, "Avaliação Física");
            map.put(Ac.AVALIACAO_FISICA_DELETE, "Avaliação Física");
            map.put(Ac.AVALIACAO_FISICA_READ, "Avaliação Física");
            map.put(Ac.AVALIACAO_FISICA_INFO, "Avaliação Física");
            map.put(Ac.USUARIO_AVALIACAO_FISICA_READ, "Aluno e Avaliações Físicas");
            map.put(Ac.USUARIO_AVALIACOES, "Avaliações");
            //CONFIGURACAO
            map.put(Ac.CONFIGURACAO_CREATE, "Configuração");
            map.put(Ac.CONFIGURACAO_UPDATE, "Configuração");
            map.put(Ac.CONFIGURACAO_DELETE, "Configuração");
            map.put(Ac.CONFIGURACAO_READ, "Configuração");
            //USUARIO PLANO
            map.put(Ac.USUARIO_PLANOS_READ, "Planos do aluno");
            map.put(Ac.USUARIOS_PLANO_READ, "Alunos com plano");
            map.put(Ac.CANCELAR_PLANO, "Cancelou plano do aluno");
            map.put(Ac.VINCULAR_PLANO, "Vinculou plano ao aluno");
            //RELATORIO
            map.put(Ac.GERAR_RELATORIO, "Gerou relatório");
            map.put(Ac.USUARIO_REPORT, "Gerou relatório");
            map.put(Ac.EVENTO_REPORT, "Relatório de acessos");
            map.put(Ac.PAGAMENTO_REPORT_READ, "Pagamentos");
            map.put(Ac.REPORT_CAIXA, "Relatório de Fluxo de caixa");
            map.put(Ac.REPORT_CAIXA_PDF, "Relatório de Fluxo de caixa");
            //EVENTO
            map.put(Ac.ULTIMOS_EVENTOS, "Ultimos eventos");
            map.put(Ac.EVENTO_READ, "Acessos");
            //BANCO BACKUP
            map.put(Ac.BANCO_BACKUP_DELETE, "Excluiu Backup.");
            map.put(Ac.BANCO_BACKUP_GERAR, "Gerou Backup");
            map.put(Ac.BANCO_BACKUP_READ, "Consultou Backup");
            map.put(Ac.BANCO_BACKUP_RESTAURAR, "Restaurou Backup");
            //AJAX
            map.put(Ac.PARCELAS_AJAX, "parcelasAjax");
            map.put(Ac.VERIFICA_CPF, "verificaCpf");
            map.put(Ac.DIGITAL_AJAX, "digitalAjax");
            map.put(Ac.IMPORTAR_PLANO_AJAX, "Importou Plano");
            map.put(Ac.IMPORTAR_HORARIO_AJAX, "Importou Horário");
            map.put(Ac.ATUALIZAR_USUARIO, "Atualizou aluno");
            //LOGIN
            map.put(Ac.LOGIN, "Entrou na aplicação.");
        }
        return map;
    }

    public static String getMap(String name) {
        String nome = getMapAction().get(name);
        if (nome == null) {
            System.out.println("FALTANDO MSG dessa action:" + name);
            nome = "";
        }
        return nome;
    }
}
