
<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<mtw:bean value="paginator" var="pag">
    <table width="100%" class="displaytag">
        <thead>
            <tr>
                <th>
                    <%@include file="../template/orderbyPaginator.jsp" %>Data Hora
                </th>
                <th>Justificativa</th><th>Ação</th></tr>
        </thead>
        <tbody>
            <mtw:bean value="pag.list">
                <mtw:list value="pag.list">
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
                            <td width="20%">
                                <mtw:out value="dataHora.time"/>
                            </td>
                            <td width="65%">
                                <mtw:out value="justificativa" max="50"/>
                            </td>
                            <td width="15%">
                                <mtw:hasAuthorization permission="liberarRead">
                                    <a id='ver' title='Ver' href="liberarInfo.do?id=${row.id}">Ver</a>
                                </mtw:hasAuthorization>
                                <mtw:hasAuthorization permission="liberarDelete">
                                    | <a class='delete' title='Excluir' href="javascript:excluirLiberar(${row.id});">Excluir</a>
                                </mtw:hasAuthorization>
                            </td>
                        </tr>
                    </mtw:loop>
                    <tr>
                        <td align="right" width="100%" colspan="6">
                            <%@include file="../template/paginacaoPaginator.jsp" %>
                        </td>
                    </tr>
                </mtw:list>
            </mtw:bean>
        </tbody>
    </table>
</mtw:bean>