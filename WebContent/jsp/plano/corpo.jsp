<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/excluir.js" type="text/javascript"></script>

<mtw:bean value="paginator" var="pag">
    <table width="100%" class="displaytag">
        <thead>
            <tr><th><%@include file="../template/orderbyPaginator.jsp" %>Plano</th><th>Valor</th>
                <mtw:hasAuthorization permission="planoManager,planoDelete" >
                    <th>Ação</th>
                </mtw:hasAuthorization>
            </tr>
        </thead>
        <tbody>
            <mtw:bean value="pag.list">
                <mtw:list value="pag.list">
                    <mtw:isEmpty>
                        <tr class="sub">
                            <td colspan="3">
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
                            <td width="65%">
                                <label title="<mtw:out value="plano"/>!"><mtw:out value="plano" max="60"/></label>
                            </td>
                            <td width="20%">
                                <mtw:out value="valorTotal" formatter="realFormatter"/>
                            </td>
                            <mtw:hasAuthorization permission="planoManager,planoDelete" >
                                <td class="acao">
                                    <mtw:hasAuthorization permission="planoManager" ><a class='update' title='Editar' href="planoUpdate.do?id=${row.id}">Editar</a></mtw:hasAuthorization>
                                    |<mtw:hasAuthorization permission="planoDelete" ><a class='delete' title='Excluir' href="javascript:excluirPlano(${row.id});">Excluir</a></mtw:hasAuthorization>
                                    </td>
                            </mtw:hasAuthorization>
                        </tr>
                    </mtw:loop>
                    <tr>
                        <td align="right" width="100%" colspan="3">
                            <%@include file="../template/paginacaoPaginator.jsp" %>
                        </td>
                    </tr>
                </mtw:list>
            </mtw:bean>
        </tbody>
    </table>
</mtw:bean>
