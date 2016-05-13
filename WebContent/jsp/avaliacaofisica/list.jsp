<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/excluir.js"></script>

<div class="title_bottom">
    <table class="topo" width="100%">
        <tr>
            <td width="75%">
                <h1>Avaliações</h1>
            </td>
        </tr>
    </table>
    <mtw:form action="usuarioAvaliacaoFisicaRead.do">
        <div class="faixasForm">
            <mtw:bean value="usuario">
                <table class="" style="width: 100%">
                    <tr>
                        <td width="84%" class="one"><br><h1>Informações Pessoais</h1></td>
                        <td width="16%" align="center">
                            <mtw:hasAuthorization permission="avaliacaoFisicaManager">
                                <a id='novo' title='Nova Avaliação' href="avaliacaoFisicaCreate.do?id=${usuario.id}">Nova Avaliação</a>
                            </mtw:hasAuthorization>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="faixasForm" width="100%">
                                <table class="displaytag" width="100%">
                                    <tr>
                                        <th>Aluno</th>
                                        <th>Matrícula</th>
                                        <th>Cpf</th>
                                        <th>Idade</th>
                                        <th>Sexo</th>
                                    </tr>
                                    <tr class="odd">
                                        <td>
                                            <mtw:input type="hidden" name="id"/>
                                            <mtw:out value="usuario"/>
                                            <input type="hidden" id="idade" name="idade" value="<mtw:out value="idade" />"/>
                                        </td>
                                        <td><mtw:out value="matricula"/></td>
                                        <td><mtw:out value="cpf"/></td>
                                        <td><mtw:out value="idade"/></td>
                                        <td><mtw:out value="sexo" /><input type="hidden" id="sexo" name="sexo" value="<mtw:out value="sexo" />"</td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
            </mtw:bean>
            <h3>Listar</h3>
            <table whith="100%" class="displaytag">
                <mtw:bean value="list" >
                    <thead>
                        <tr>
                            <th>Data Avaliação</th><th>Instrutor</th><th>Ação</th></tr>
                    </thead>
                    <tbody>
                        <mtw:list value="data" orderBy="id" desc="true">
                            <mtw:isEmpty>
                                <tr class="sub">
                                    <td colspan="6">
                                        Dados Vazios
                                    </td>
                                </tr>
                            </mtw:isEmpty>
                            <mtw:loop var="row" counterStart="0" counter="i">
                                <tr class="
                                    <c:choose>
                                        <c:when test="${i%2!=0}">odd</c:when>
                                        <c:otherwise>even</c:otherwise>
                                    </c:choose>
                                    ">
                                    <td width="25%">
                                        <mtw:out value="dataAvaliacao.time" formatter="dateFormatter"/>
                                    </td>
                                    <td width="50%">
                                        <mtw:out value="instrutor.usuario"/>
                                    </td>
                                    <td width="25%">
                                        <mtw:hasAuthorization permission="avaliacaoFisicaRead">
                                            <a id='ver' title='Ver Avaliação' href="avaliacaoFisicaInfo.do?id=${usuario.id}&idAvaliacao=${row.id}">Ver</a>
                                        </mtw:hasAuthorization>
                                        <mtw:hasAuthorization permission="avaliacaoFisicaDelete"> | <a class='delete' title='Excluir!' href="javascript:excluirAvaliacaoFisica(${usuario.id},${row.id});">Excluir</a></mtw:hasAuthorization>
                                        </td>
                                    </tr>
                            </mtw:loop>
                        </mtw:list>
                    </tbody>
                </mtw:bean>
            </table>
        </div>
        <table width="100%" >
            <tr>
                <td width="100%" class="panelButtonForm">
                    <mtw:submit klass="botao" name="Voltar" value="Voltar"/>
                </td>
            </tr>
        </table>
    </mtw:form>
</div>