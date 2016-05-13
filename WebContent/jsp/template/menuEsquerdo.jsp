<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script language="JavaScript" charset="utf-8" src="js/template/menu.js"></script>

<div style="height: 555px;">
    <mtw:hasAuthorization permission="usuarioCreateInformacao,usuarioRead,usuariosPlanoRead,managerFluxoCaixa,pagamentoRead">
        <div style="height: 211px">
            <div id="menu">
                <ul id="theMenu">
                    <mtw:hasAuthorization permission="usuarioManager,usuarioRead,usuariosPlanoRead">
                        <li style="position: static; ">
                            <h4 style="font-size: 14px">Aluno</h4>
                            <ul>
                                <mtw:hasAuthorization permission="usuarioManager">
                                    <li><h9 class="head"><a href="usuarioCreateInformacao.do" id="usuarioCreate" title="Novo Aluno!">Novo</a></h9></li>
                                </mtw:hasAuthorization>
                                <mtw:hasAuthorization permission="usuarioRead">
                            <li><h9 class="head"><a href="usuarioRead.do" id="usuarioRead" title="Editar/ Excluir Aluno!">Editar / Excluir</a></h9></li>
                        </mtw:hasAuthorization>
                        <mtw:hasAuthorization permission="usuarioPlanosRead">
                            <li><h9 class="head"><a href="usuariosPlanoRead.do" id="usuarioPlanoRead" title="Planos dos Alunos!">Planos dos Alunos</a></h9></li>
                        </mtw:hasAuthorization>
                    </ul>
                    </li>
                </mtw:hasAuthorization>
                <mtw:hasAuthorization permission="managerFluxoCaixa,pagamentoRead">
                    <li style="position: static;">
                        <h4 style="font-size: 14px">Financeiro</h4>
                        <mtw:hasAuthorization permission="managerFluxoCaixa">
                            <ul><li><h9 class="head"><a href="managerFluxoCaixa.do" id="fluxoCaixaRead" title="Movimentação de Caixa!">Gerenciar Caixa</a></h9></li></ul>
                        </mtw:hasAuthorization>
                        <mtw:hasAuthorization permission="pagamentoRead">
                        <ul><li><h9 class="head"><a href="pagamentoRead.do" id="pagamentoRead" title="Pagamentos de Parcelas!">Pagamentos</a></h9></li></ul>
                    </mtw:hasAuthorization>
                    </li>
                </mtw:hasAuthorization>
                </ul>
            </div>
        </div>
    </mtw:hasAuthorization>
    <div class="title_bottom">
        <h2 class="head"><a href="eventoRead.do" >Ultimos Acessos</a></h2>
        <div class="tickerr"  id="ticker" style="max-height: 300px;background-color: #FFF;border: solid 2px #eae9e9;margin-top: 10px;padding-bottom: 10px;">
            <input type="hidden" name="ultimo" id="ultimo" value="0"/>
        </div>
    </div>
</div>
</div>
