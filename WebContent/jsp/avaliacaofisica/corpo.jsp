<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<mtw:bean value="paginator" var="pag">
    <table width="100%" class="displaytag">
        <mtw:bean value="pag.list">
            <thead>
                <tr>
                    <th  width="4%" >-</th>
                    <th width="10%" >Matrícula</th>
                    <th width="54%" ><%@include file="../template/orderbyPaginator.jsp" %> Nome</th>
                    <th width="8%" ><label style="font-size: 11px;font-style: italic" title="Aluno / Funcionário!">A/F</label></th>
                    <th width="24%" >Ação</th>
                </tr>
            </thead>
            <tbody>
                <mtw:list value="pag.list">
                    <mtw:isEmpty>
                        <tr class="sub">
                            <td colspan="5">
                                Dados Vazios
                            </td>
                        </tr>
                    </mtw:isEmpty>
                    <mtw:loop var="row" counterStart="0" counter="i">
                        <tr class="
                            <c:choose>
                                <c:when test="${i%2==0}">odd</c:when>
                                <c:otherwise>even</c:otherwise>
                            </c:choose>
                            ">
                            <td>
                                <%@include file="../requisicao/link.jsp" %>
                            </td>
                            <td>
                                <mtw:out value="matricula"/>
                            </td>
                            <td>
                                <mtw:out value="usuario"/>
                            </td>
                            <td>
                                <mtw:if test="aluno" ><mtw:if test="ativoAluno" ><img src="images/alunoPeq.png" title="Aluno!"/></mtw:if></mtw:if>
                                <mtw:if test="funcionario" ><mtw:if test="ativoFuncionario" ><img src="images/funcPeq.png" title="Funcionário!"/></mtw:if></mtw:if>
                            </td>
                            <td>
                                <mtw:hasAuthorization permission="avaliacaoFisicaRead">
                                    <a id='plano' title='Avaliações' href="usuarioAvaliacoes.do?id=${row.id}">Avaliações</a>
                                </mtw:hasAuthorization>
                                | <mtw:hasAuthorization permission="avaliacaoFisicaManager">
                                    <a id='novo' title='Nova Avaliação' href="avaliacaoFisicaCreate.do?id=${row.id}">Nova Avaliação</a>
                                </mtw:hasAuthorization>
                            </td>
                        </tr>
                    </mtw:loop>
                    <tr>
                        <td align="right" width="100%" colspan="5">
                            <%@include file="../template/paginacaoPaginator.jsp" %>
                        </td>
                    </tr>
                </mtw:list>
            </tbody>
        </mtw:bean>
    </table>
</mtw:bean>
