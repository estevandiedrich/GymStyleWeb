<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script language="JavaScript" charset="utf-8" src="js/template/menu.js"></script>
<div id="internetExplorer" class="errors" style="display: none">Utilize de preferência os navegadores Chrome ou Mozila Firefox!</div>
<div id="fundoMenu" >
    <ul id="menuSuperior">
        <!--li><a href="boletoReport.do" target="blank" title="Tela Inicial!">Boleto</a></li-->
        <li><a href="main.do" title="Tela Inicial!">Início</a></li>
        <li class="menu_right" style="margin-left: 60px"><a href="#" class="drop">Cadastros</a>
            <!--div class="dropdown_3columns"-->
            <div class="dropdown_2columns"><!-- Begin 2 columns container -->
                <mtw:hasAuthorization permission="dispositivoRead,modalidadeRead,planoRead,usuarioRead,funcionarioRead">
                    <div class="col_2">
                        <h2>Acesso</h2>
                    </div>
                    <div class="col_1">
                        <ul class="greybox">
                            <mtw:hasAuthorization permission="dispositivoRead">
                                <li><a href="dispositivoRead.do" title="Catracas!">Catracas</a></li>
                            </mtw:hasAuthorization>
                            <mtw:hasAuthorization permission="modalidadeRead">
                                <li><a href="modalidadeRead.do" title="Modalidades!">Modalidades</a></li>
                            </mtw:hasAuthorization>
                            <mtw:hasAuthorization permission="planoRead">
                                <li><a href="planoRead.do" title="Planos!">Planos</a></li>
                            </mtw:hasAuthorization>
                        </ul>
                    </div>
                    <div class="col_1">
                        <ul class="greybox">
                            <mtw:hasAuthorization permission="usuarioRead">
                                <li><a href="usuarioRead.do" title="Alunos!">Alunos</a></li>
                            </mtw:hasAuthorization>
                            <mtw:hasAuthorization permission="funcionarioRead">
                                <li><a href="funcionarioRead.do" title="Funcionários!">Funcionários</a></li>
                            </mtw:hasAuthorization>
                        </ul>
                    </div>
                </mtw:hasAuthorization>
                <!--div class="col_2">
                    <p>Hi and welcome here ! This is a showcase of the possibilities of this awesome Mega Drop Down Menu.</p> 
                </div-->
                <mtw:hasAuthorization permission="exercicioRead,empresaRead,fichaRead,avaliacaoFisicaReadRead,fichaRead,managerFluxoCaixa">
                    <div class="col_2">
                        <h2>Administração</h2>
                    </div>
                    <div class="col_1">
                        <ul class="greybox">
                            <mtw:hasAuthorization permission="exercicioRead">
                                <li><a href="exercicioRead.do" title="Exercícios!">Exercícios</a></li>
                            </mtw:hasAuthorization>
                            <mtw:hasAuthorization permission="fichaRead">
                                <li><a href="fichaRead.do" title="Fichas de Treinos!">Fichas de Treino</a></li>
                            </mtw:hasAuthorization>
                            <mtw:hasAuthorization permission="managerFluxoCaixa">
                                <mtw:hasAuthorization permission="contaBancariaRead">
                                    <li><a href="contaBancariaRead.do" title="Conta Bancária!">Conta Bancária</a></li>
                                </mtw:hasAuthorization>
                            </mtw:hasAuthorization>
                        </ul>
                    </div>
                    <div class="col_1">
                        <ul class="greybox">
                            <mtw:hasAuthorization permission="avaliacaoFisicaRead">
                                <li><a href="usuarioAvaliacaoFisicaRead.do" title="Avaliações Físicas!">Avaliações Físicas</a></li>
                            </mtw:hasAuthorization>
                            <mtw:hasAuthorization permission="empresaRead">
                                <li><a href="empresaRead.do" title="Academia!">Academia</a></li>
                            </mtw:hasAuthorization>
                        </ul>
                    </div>
                </mtw:hasAuthorization>
                <!--div class="col_2">
                    <h2>Vendas</h2>
                </div>
                <div class="col_1">
                    <ul class="greybox">
                        <li><a href="categoriaRead.do" title="Categoria!">Categoria</a></li>
                    </ul>
                </div>
                <div class="col_1">
                    <ul class="greybox">
                        <li><a href="produtoRead.do" title="Produto!">Produto</a></li>
                    </ul>
                </div-->
            </div>
        </li><!-- End 3 columns Item -->
        <!-- ******************************************************************************************** -->
        <mtw:hasAuthorization permission="autenticacaoPagamentoRead,bancoBackupRead,managerFluxoCaixa,pagamentoRead,usuariosPlanoRead,liberarManager,liberarRead,managerContaBancaria" >
            <li><a href="#" class="drop">Gerenciar</a><!-- Begin Home Item -->
                <div class="dropdown_2columns">
                    <div class="col_1">
                        <ul class="greybox">
                            <mtw:hasAuthorization permission="requisicaoManager" >
                                <li><a href="requisicaoRead.do" title="Atualizar Alunos!">Atualizações</a></li>
                            </mtw:hasAuthorization>
                            <mtw:hasAuthorization permission="autenticacaoPagamentoRead" >
                                <li><a href="autenticacaoPagamentoRead.do" title="Autenticação de Recibo!">Autenticar Recibo</a></li>
                            </mtw:hasAuthorization>
                            <mtw:hasAuthorization permission="bancoBackupRead" >
                                <li><a href="bancoBackupRead.do" title="Backup!">Backup</a></li>
                            </mtw:hasAuthorization>
                            <mtw:hasAuthorization permission="managerFluxoCaixa" >
                                <mtw:hasAuthorization permission="caixaReportRead">
                                    <li><a href="managerFluxoCaixa.do" title="Movimentação do Caixa!">Caixa</a></li>
                                </mtw:hasAuthorization>
                            </mtw:hasAuthorization>
                        </ul>
                    </div>
                    <div class="col_1">
                        <ul class="greybox">
                            <mtw:hasAuthorization permission="pagamentoRead" >
                                <li><a href="pagamentoRead.do" title="Pagamentos!">Pagamentos</a></li>
                            </mtw:hasAuthorization>
                            <mtw:hasAuthorization permission="usuarioPlanosRead" >
                                <li><a href="usuariosPlanoRead.do" title="Planos dos Alunos!">Planos dos Alunos</a></li>
                            </mtw:hasAuthorization>
                            <mtw:hasAuthorization permission="liberarManager" >
                                <li><a href="liberarCatraca.do" title="Liberar Catraca!">Liberar Catraca</a></li>
                            </mtw:hasAuthorization>
                            <mtw:hasAuthorization permission="liberarManager" negate="true" >
                                <mtw:hasAuthorization permission="liberarRead" >
                                    <li><a href="liberarRead.do" title="Liberar Catraca!">Liberar Catraca</a></li>
                                </mtw:hasAuthorization>
                            </mtw:hasAuthorization>
                            <mtw:hasAuthorization permission="managerFluxoCaixa" >
                                <mtw:hasAuthorization permission="managerContaBancaria" >
                                    <mtw:if test="temContasBancaria">
                                        <li><a href="managerContaBancaria.do" title="Gerenciar Conta Bancária!">Conta Bancária</a></li>
                                    </mtw:if>
                                </mtw:hasAuthorization>
                            </mtw:hasAuthorization>
                        </ul>
                    </div>
                </div>
            </li>
        </mtw:hasAuthorization>
        <mtw:hasAuthorization permission="configuracaoRead,configuracaoBoletoRead" >
            <li class="menu_right"><a href="#" class="drop">Configurações</a>
                <div class="dropdown_1column">
                    <div class="col_1">
                        <ul class="greybox">
                            <mtw:hasAuthorization permission="configuracaoRead" >
                                <li><a href="configuracaoRead.do" title="Configurações de Pagamento!">Pagamento</a></li>
                            </mtw:hasAuthorization>
                            <mtw:hasAuthorization permission="configuracaoBoletoRead" >
                            <li><a href="configuracaoBoletoRead.do" title="Configurações de Boleto!">Boleto</a></li>
                            </mtw:hasAuthorization>
                        </ul>
                    </div>
                </div>
            </li>
        </mtw:hasAuthorization>
        <li class="menu_right"><a href="#" class="drop">Ajuda</a><!-- Begin 3 columns Item -->
            <div class="dropdown_1column"><!-- Begin 2 columns container -->
                <div class="col_1"><h1>Manuais</h1></div>
                <div class="col_1">
                    <ul class="greybox">
                        <li><a id="pdfTopo" href="manuais/ManualGymStyleWebV1.7.pdf" target="blank" title="Manual - GymStyleWeb">GymStyleWEB</a></li>
                        <li><a id="pdfTopo" href="manuais/ManualGymStyleNucleo.pdf" target="blank" title="Manual - GymStyleCore(Núcleo)">GymStyleCore</a></li>
                        <li><a id="pdfTopo" href="manuais/ManualCatracaGymStyle_2.0.pdf" target="blank" title="Manual - Catraca">Catraca</a></li>
                    </ul>
                </div>
            </div>
        </li><!-- End 3 columns Item -->
        <li class="menuSuperior_right">
            <a href="javascript:encerrarSessao()" title="Sair da Aplicação!">Sair</a>
        </li>
        <mtw:hasAuthorization permission="eventoRead,pagamentoReportRead,caixaReportRead,pagamentoUltimoPlanosRead,logRead" >
            <li class="menuSuperior_right"><a href="#" class="drop">Relatórios</a>
                <div class="dropdown_1column align_right">
                    <div class="col_1">
                        <ul class="greybox">
                            <mtw:hasAuthorization permission="eventoRead" >
                                <li><a href="eventoRead.do" title="Consulta Acessos!">Acessos</a></li>
                            </mtw:hasAuthorization>
                            <mtw:hasAuthorization permission="pagamentoReportRead" >
                                <li><a href="pagamentoReportRead.do" title="Pagamentos!">Pagamentos</a></li>
                            </mtw:hasAuthorization>
                            <mtw:hasAuthorization permission="managerFluxoCaixa" >
                                <mtw:hasAuthorization permission="caixaReportRead">
                                    <li><a href="caixaReportRead.do" title="Fluxo de Caixa Mensal!">Fluxo de Caixa</a></li>
                                </mtw:hasAuthorization>
                            </mtw:hasAuthorization>
                            <mtw:hasAuthorization permission="pagamentoUltimoPlanosRead">
                                <li><a href="pagamentoUltimoPlanosRead.do" title="Pagamento do Plano do Usuário!">Histórico</a></li>
                            </mtw:hasAuthorization>
                            <mtw:hasAuthorization permission="logRead">
                                <li><a href="logRead.do" title="Pagamento do Plano do Usuário!">Registros de Log</a></li>
                            </mtw:hasAuthorization>
                        </ul>
                    </div>
                </div>
            </li>
        </mtw:hasAuthorization>
    </ul>
    <!--%@include file="../../menuOriginal.jsp" %-->
</div>
