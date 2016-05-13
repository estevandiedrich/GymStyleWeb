package br.com.rwtech.gymstyleweb.controller.ac;

/**
 * Mapeamento das actions Quando acrescentar novas actios, adicionar em grupo
 * manager collections e atualizar o script de povoamento de permissoes
 *
 * @author Éder Faria
 */
public class Ac {

    //==================================================================================
    public static String JSP = "jsp/";
    public static String LIST_PAGE = "/list.page";
    public static String CORPO_JSP = "/corpo.jsp";
    public static String MANAGER_PAGE = "/manager.page";
    public static String CREATE_PAGE = "/createForm.page";
    public static String UPDATE_PAGE = "/updateForm.page";
    public static String ERROR_REPORT_PAGE = JSP + "relatorios/errorReport.page";
    public static String INFO_PAGE = "/info.page";
    public static String OV = "OV";
    public static String VO = "VO";
    public static String PDF = "PDF";
    public static String APPLICATION_PDF = "application/pdf";
    public static String REPORT = "Report";
    public static String RESTORE = "Restore";
    public static String MANAGER = "Manager";
    public static String RETORNO_AJAX_JSP = "/retornoAjax.jsp";
    public static String RETORNO_JSP = "/retorno.jsp";
    public static String RESULT_JSP = "/result.jsp";
    public static String ERROR_JSP = "/error.jsp";
    public static String DESTINOS_JSP = "/destinos.jsp";
    public static String MODULO_CHEIO = "MODULO_CHEIO";
    public static String SINCRONIZACAO = "SINCRONIZACAO";
    public static String ATUALIZAR = "ATUALIZAR";
    public static String EXCLUIR = "EXCLUIR";
    public static String SUCCESS = org.mentawai.core.ApplicationManager.SUCCESS;
    public static String SHOW = org.mentawai.core.ApplicationManager.SHOW;
    public static String ERROR = org.mentawai.core.ApplicationManager.ERROR;
    public static String LIST = org.mentawai.core.ApplicationManager.LIST;
    public static String EXCEPTION = org.mentawai.core.ApplicationManager.EXCEPTION;
    public static String SINCRONIZANDO = "SINCRONIZANDO";
    //==================================================================================
    public static String DEFINIR = "definir";
    public static String PREVIEW = "preview";
    public static String DO = ".do";
    public static String CREATE = "Create";
    public static String DELETE = "Delete";
    public static String UPDATE = "Update";
    public static String READ = "Read";
    public static String INFO = "Info";
    public static String MAIN = "main";
    public static String LOAD_IMAGE = "loadImage";
    public static String INDEX = "index";
    public static String LOGIN = "login";
    public static String HELP = "help";
    //REQUISICAO
    public static String REQUISICAO = "requisicao";
    public static String REQUISICAO_MANAGER = REQUISICAO + MANAGER;
    public static String REQUISICAO_MANAGER_EXCLUIR = "requisicaoManagerExcluir";
    public static String REQUISICAO_ATUALIZAR_USUARIO = "requisicaoAtualizarUsuario";
    public static String REQUISICAO_ATUALIZAR_TODOS = "requisicaoAtualizarTodos";
    public static String REQUISICAO_REENVIAR = "requisicaoReenviar";
    public static String REQUISICAO_READ = "requisicao" + READ;
    //PAGAMENTOS
    public static String PAGAMENTO = "pagamento";
    public static String PAGAMENTO_FILTER = "pagamentoFilter";
    public static String PAGAMENTO_REPORT = "pagamentoReport";
    public static String PAGAMENTO_READ = "pagamento" + READ;
    public static String AUTENTICAO_PAGAMENTO_READ = "autenticacaoPagamento" + READ;
    public static String PAGAMENTO_ULTIMO_PLANOS_READ = "pagamentoUltimoPlanosRead";
    public static String PAGAMENTOS_PLANO_USUARIO = "pagamentosPlanoUsuario";
    public static String VER_PLANO_USUARIO = "verPlanoUsuario";
    public static String LANCAR_PARCELAS_PLANO_USUARIO = "lancarParcelasPlanoUsuario";
    public static String VER_PARCELA_PLANO_USUARIO = "verParcelaPlanoUsuario";
    public static String PAGAR_PARCELA_PLANO_USUARIO = "pagarParcelaPlanoUsuario";
    public static String PAGAR_PARCELA_MANAGER = "pagarParcelaManager";
    public static String PAGAR_NOVA_PARCELA = "pagarNovaParcela";
    public static String FINALIZAR_PLANO = "finalizarPlano";
    public static String NOVA_PARCELA_MENSAL = "novaParcelaMensal";
    public static String IMPRIMIR_PAGAMENTO = "imprimirPagamento";
    public static String READ_STATUS_IMPRIMIR_PAGAMENTO = "readStatusImprimirPagamento";
    //LOG
    public static String LOG_READ = "log" + READ;
    //USUARIO
    public static String ABA_INFORMACAO = "Informacao";
    public static String ABA_IDENTIFICACAO = "Identificacao";
    public static String ABA_AUTENTICACAO = "Autenticacao";
    public static String ABA_PLANO = "Plano";
    public static String ABA_ACESSO = "Acesso";
    public static String USUARIO = "usuario";
    public static String USUARIO_READ = USUARIO + READ;
    public static String USUARIO_MANAGER = USUARIO + MANAGER;
    public static String USUARIO_UPDATE_INFORMACAO = USUARIO + UPDATE + ABA_INFORMACAO;
    public static String USUARIO_UPDATE_IDENTIFICACAO = USUARIO + UPDATE + ABA_IDENTIFICACAO;
    public static String USUARIO_UPDATE_PLANO = USUARIO + UPDATE + ABA_PLANO;
    public static String USUARIO_CREATE_INFORMACAO = USUARIO + CREATE + ABA_INFORMACAO;
    public static String USUARIO_DELETE = USUARIO + DELETE;
    public static String SINCRONIZADOS_FALSE = "sincronizadosFalse";
    //FUNCIONARIO
    public static String FUNCIONARIO = "funcionario";
    public static String FUNCIONARIO_MANAGER = FUNCIONARIO + MANAGER;
    public static String FUNCIONARIO_READ = FUNCIONARIO + READ;
    public static String FUNCIONARIO_UPDATE_INFORMACAO = FUNCIONARIO + UPDATE + ABA_INFORMACAO;
    public static String FUNCIONARIO_UPDATE_AUTENTICACAO = FUNCIONARIO + UPDATE + ABA_AUTENTICACAO;
    public static String FUNCIONARIO_UPDATE_IDENTIFICACAO = FUNCIONARIO + UPDATE + ABA_IDENTIFICACAO;
    public static String FUNCIONARIO_UPDATE_ACESSO = FUNCIONARIO + UPDATE + ABA_ACESSO;
    public static String FUNCIONARIO_CREATE_INFORMACAO = FUNCIONARIO + CREATE + ABA_INFORMACAO;
    public static String FUNCIONARIO_DELETE = FUNCIONARIO + DELETE;
    public static String GERENCIAR_PERMISSOES = "identificacaoSoftware" + MANAGER;
    //DISPOSITIVO
    public static String DISPOSITIVO = "dispositivo";
    public static String DISPOSITIVO_MANAGER = DISPOSITIVO + MANAGER;//Permissao
    public static String DISPOSITIVO_READ = DISPOSITIVO + READ;
    public static String DISPOSITIVO_CREATE = DISPOSITIVO + CREATE;
    public static String CONFIGURAR_CATRACA = "configurarCatraca";
    public static String REENVIAR_CONFIGURAR_CATRACA = "reenviarConfigurarCatraca";
    public static String DISPOSITIVO_UPDATE = DISPOSITIVO + UPDATE;
    public static String DISPOSITIVO_DELETE = DISPOSITIVO + DELETE;
    ;
    public static String BUSCAR_EVENTOS = "buscarEventos";
    public static String DISPOSITIVO_BUSCAR = "dispositivoBuscar";
    public static String APAGAR_USUARIO_DISPOSITIVO = "apagarUsuarioDispositivo";
    public static String LIMPAR_DISPOSITIVO = "limparDispositivo";
    //LIBERAR
    public static String LIBERAR = "liberar";
    public static String LIBERAR_MANAGER = LIBERAR + MANAGER;
    public static String LIBERAR_CATRACA = LIBERAR + "Catraca";
    public static String LIBERAR_READ = LIBERAR + READ;
    public static String LIBERAR_DELETE = LIBERAR + DELETE;
    public static String LIBERAR_INFO = LIBERAR + INFO;
    //USUARIO FICHA
    public static String FICHA = "ficha";
    public static String FICHA_INFO = "fichaInfo";
    public static String FICHA_MANAGER = FICHA + MANAGER;
    public static String FICHA_READ = FICHA + READ;
    public static String USUARIO_FICHAS_READ = "usuarioFichas" + READ;
    public static String FICHA_CREATE = FICHA + CREATE;
    public static String FICHA_UPDATE = FICHA + UPDATE;
    public static String ORDENAR = "ordenar";
    public static String ORDENAR_FICHA = "ordenarFicha";
    public static String FICHA_DELETE = FICHA + DELETE;
    public static String ATUALIZAR_STATUS_FICHA = "atualizarStatusFicha";
    public static String STATUS_FICHA = "statusFicha";
    //CATEGORIA
    public static String CATEGORIA = "categoria";
    public static String CATEGORIA_MANAGER = CATEGORIA + MANAGER;
    public static String CATEGORIA_CREATE = CATEGORIA + CREATE;
    public static String CATEGORIA_UPDATE = CATEGORIA + UPDATE;
    public static String CATEGORIA_READ = CATEGORIA + READ;
    public static String CATEGORIA_DELETE = CATEGORIA + DELETE;
    //PRODUTO
    public static String PRODUTO = "produto";
    public static String PRODUTO_MANAGER = PRODUTO + MANAGER;
    public static String PRODUTO_CREATE = PRODUTO + CREATE;
    public static String PRODUTO_UPDATE = PRODUTO + UPDATE;
    public static String PRODUTO_READ = PRODUTO + READ;
    public static String PRODUTO_DELETE = PRODUTO + DELETE;
    //PRODUTO
    public static String FORNECEDOR = "fornecedor";
    public static String FORNECEDOR_MANAGER = FORNECEDOR + MANAGER;
    public static String FORNECEDOR_CREATE = FORNECEDOR + CREATE;
    public static String FORNECEDOR_UPDATE = FORNECEDOR + UPDATE;
    public static String FORNECEDOR_READ = FORNECEDOR + READ;
    public static String FORNECEDOR_DELETE = FORNECEDOR + DELETE;
    //VENDAS
    public static String VENDA = "venda";
    public static String VENDA_MANAGER = VENDA + MANAGER;
    //TIPO_USUARIO
    public static String TIPO_USUARIO = "tipoUsuario";
    public static String TIPO_USUARIO_MANAGER = TIPO_USUARIO + MANAGER;
    public static String TIPO_USUARIO_CREATE = TIPO_USUARIO + CREATE;
    public static String TIPO_USUARIO_UPDATE = TIPO_USUARIO + UPDATE;
    public static String TIPO_USUARIO_READ = TIPO_USUARIO + READ;
    public static String TIPO_USUARIO_DELETE = TIPO_USUARIO + DELETE;
    //PERFIL_ACESSO
    public static String PERFIL_ACESSO = "perfilAcesso";
    public static String PERFIL_ACESSO_MANAGER = PERFIL_ACESSO + MANAGER;
    public static String PERFIL_ACESSO_CREATE = PERFIL_ACESSO + CREATE;
    public static String PERFIL_ACESSO_UPDATE = PERFIL_ACESSO + UPDATE;
    public static String PERFIL_ACESSO_DELETE = PERFIL_ACESSO + DELETE;
    public static String PERFIL_ACESSO_READ = PERFIL_ACESSO + READ;
    //PLANO
    public static String PLANO = "plano";
    public static String PLANO_MANAGER = PLANO + MANAGER;
    public static String PLANO_CREATE = PLANO + CREATE;
    public static String PLANO_UPDATE = PLANO + UPDATE;
    public static String PLANO_DELETE = PLANO + DELETE;
    public static String PLANO_READ = PLANO + READ;
    //MODALIDADE
    public static String MODALIDADE = "modalidade";
    public static String MODALIDADE_MANAGER = MODALIDADE + MANAGER;
    public static String MODALIDADE_CREATE = MODALIDADE + CREATE;
    public static String MODALIDADE_UPDATE = MODALIDADE + UPDATE;
    public static String MODALIDADE_DELETE = MODALIDADE + DELETE;
    public static String MODALIDADE_READ = MODALIDADE + READ;
    //CONTA_BANCARIA
    public static String CONTA_BANCARIA = "contaBancaria";
    public static String CONTA_BANCARIA_MANAGER = CONTA_BANCARIA + MANAGER;
    public static String ENTRADA_CONTA_BANCARIA = "entradaContaBancaria";
    public static String RETIRADA_CONTA_BANCARIA = "retiradaContaBancaria";
    public static String CONTA_BANCARIA_CREATE = CONTA_BANCARIA + CREATE;
    public static String CONTA_BANCARIA_UPDATE = CONTA_BANCARIA + UPDATE;
    public static String CONTA_BANCARIA_DELETE = CONTA_BANCARIA + DELETE;
    public static String CONTA_BANCARIA_READ = CONTA_BANCARIA + READ;
    public static String CONTA_BANCARIA_READ_BY_ID_AJAX = "contaBancariaReadbyIdAjax";
    public static String REGISTRO_CONTA_BANCARIA_READ_AJAX = "regContaBancariaReadAjax";
    public static String MANAGER_CONTA_BANCARIA = "managerContaBancaria";
    public static String ENTRADA_RETIRADA_CONTA_BANCARIA = "entradaRetiradaContaBancaria";
    public static String DELETE_REGISTRO_CONTA_BANCARIA = "registroContaBancariaDelete";
    //EXERCICIO
    public static String EXERCICIO = "exercicio";
    public static String EXERCICIO_MANAGER = EXERCICIO + MANAGER;
    public static String EXERCICIO_CREATE = EXERCICIO + CREATE;
    public static String EXERCICIO_UPDATE = EXERCICIO + UPDATE;
    public static String EXERCICIO_DELETE = EXERCICIO + DELETE;
    public static String EXERCICIO_READ = EXERCICIO + READ;
    //EMPRESA
    public static String EMPRESA = "empresa";
    public static String EMPRESA_MANAGER = EMPRESA + MANAGER;
    public static String EMPRESA_CREATE = EMPRESA + CREATE;
    public static String EMPRESA_UPDATE = EMPRESA + UPDATE;
    public static String EMPRESA_DELETE = EMPRESA + DELETE;
    public static String EMPRESA_READ = EMPRESA + READ;
    //BANCO
    public static String BANCO_BACKUP_GERAR = "bancoGerarBackup";
    public static String BANCO_BACKUP_RESTAURAR = "bancoRestaurarBackup";
    public static String BANCO_BACKUP_DELETE = "bancoBackup" + DELETE;
    public static String BANCO_BACKUP_READ = "bancoBackup" + READ;
    //AVALIACAO_FISICA
    public static String AVALIACAO_FISICA = "avaliacaoFisica";
    public static String AVALIACAO_FISICA_MANAGER = AVALIACAO_FISICA + MANAGER;
    public static String AVALIACAO_FISICA_CREATE = AVALIACAO_FISICA + CREATE;
    public static String AVALIACAO_FISICA_UPDATE = AVALIACAO_FISICA + UPDATE;
    public static String AVALIACAO_FISICA_DELETE = AVALIACAO_FISICA + DELETE;
    public static String AVALIACAO_FISICA_READ = AVALIACAO_FISICA + READ;
    public static String AVALIACAO_FISICA_INFO = AVALIACAO_FISICA + INFO;
    public static String USUARIO_AVALIACAO_FISICA_READ = "usuarioAvaliacaoFisica" + READ;
    public static String USUARIO_AVALIACOES = "usuarioAvaliacoes";
    //CONFIGURACAO
    public static String CONFIGURACAO = "configuracao";
    public static String CONFIGURACAO_MANAGER = CONFIGURACAO + MANAGER;
    public static String CONFIGURACAO_CREATE = CONFIGURACAO + CREATE;
    public static String CONFIGURACAO_UPDATE = CONFIGURACAO + UPDATE;
    public static String CONFIGURACAO_DELETE = CONFIGURACAO + DELETE;
    public static String CONFIGURACAO_READ = CONFIGURACAO + READ;
    //USUARIO PLANO
    public static String USUARIO_PLANOS_READ = "usuarioPlanosRead";
    public static String USUARIOS_PLANO_READ = "usuariosPlanoRead";
    public static String CANCELAR_PLANO = "cancelarPlano";
    public static String VINCULAR_PLANO = "vincularPlano";
    //RELATORIO
    public static String GERAR_RELATORIO = "gerarRelatorio";
    public static String USUARIO_REPORT = "usuarioReport";
    public static String EVENTO_REPORT = "eventoReport";
    public static String FICHA_REPORT = "fichaReport";
    public static String RECIBO_REPORT = "reciboReport";
    public static String BOLETO_REPORT = "boletoReport";
    public static String PAGAMENTO_REPORT_READ = "pagamentoReportRead";
    //EVENTO
    public static String ULTIMOS_EVENTOS = "ultimosEventos";
    public static String EVENTO_READ = "eventoRead";
    //CONFIGURCAO BOLETO
    public static String CONFIGURAR_BOLETO = "configurarBoleto";
    public static String CONFIGURACAO_BOLETO_READ = "configuracaoBoletoRead";
    public static String CARTEIRAS_AJAX = "carteirasAjax";
    //FLUXO_CAIXA
    public static String MANAGER_CAIXA = "managerFluxoCaixa";
    public static String CARREGAR_ABRIR_CAIXA = "carregarAbrirFluxoCaixa";
    public static String ABRIR_CAIXA = "abrirFluxoCaixa";
    public static String FECHAR_CAIXA = "fecharFluxoCaixa";
    public static String ENTRADA_CAIXA = "entradaFluxoCaixa";
    public static String RETIRADA_CAIXA = "retiradaFluxoCaixa";
    public static String REPORT_CAIXA = "caixaReportRead";
    public static String REPORT_CAIXA_PDF = "caixaReportPDF";
    public static String DELETE_REGISTRO_CAIXA = "registroCaixaDelete";
    public static String READ_CAIXAS_AJAX = "readCaixasAjax";
    public static String READ_CAIXA_AJAX = "readCaixaAjax";
    //AJAX
    public static String AJAX = "Ajax";
    public static String PAGAMENTO_UPDATE_IMPRIMIR_AJAX = "pagamentoUpdateImprimir" + AJAX;
    public static String PAGAMENTO_UPDATE_IMPRIMIR_ENTRADA_AJAX = "pagamentoUpdateImprimirEntrada" + AJAX;
    public static String PARCELAS_AJAX = "parcelas" + AJAX;
    public static String VERIFICA_CPF = "verificaCpf";
    public static String VERIFICA_LOGIN = "verificaLogin";
    public static String VERIFICA_MATRICULA = "verificaMatricula";
    public static String DIGITAL_AJAX = "digital" + AJAX;
    public static String DIGITAL_CREATE = "digitalCreate" + AJAX;
    public static String DIGITAIS_USUARIO = "digitaisUsuario" + AJAX;
    public static String SELECT_DEDOS = "selectDedos" + AJAX;
    public static String BUSCA_APPLET = "buscaApplet" + AJAX;
    public static String IMPORTAR_PLANO_AJAX = "importarPlano" + AJAX;
    public static String IMPORTAR_HORARIO_AJAX = "importarHorario" + AJAX;
    public static String IMPORTAR_BUTTON_FOTO_CREATE_AJAX = "importarButtomFotoCreate" + AJAX;
    public static String IMPORTAR_BUTTON_FOTO_UPDATE_AJAX = "importarButtomFotoUpdate" + AJAX;
    public static String ATUALIZAR_USUARIO = "atualizarUsuario";
    public static String BANCO_AJAX_READ = "banco" + AJAX + READ;
    public static String USUARIO_AJAX_READ = USUARIO + AJAX + READ;
    public static String PRODUTO_AJAX_READ = PRODUTO + AJAX + READ;
}
