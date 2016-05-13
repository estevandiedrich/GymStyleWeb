<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="js/excluir.js" type="text/javascript"></script>

<mtw:bean value="paginator" var="pag">
    <table width="100%" class="displaytag">
        <thead>
            <tr><th><%@include file="../template/orderbyPaginator.jsp" %> Nome</th><th>Ação</th></tr>
        </thead>
        <tbody>
            <mtw:bean value="pag.list">
                <mtw:list value="pag.list">
                    <mtw:isEmpty>
                        <tr class="sub">
                            <td colspan="2">
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
                            <td width="80%"><label title="<mtw:out value="nome"/>!"><mtw:out value="nome" /></label></td>
                            <td width="20%">
                                <mtw:hasAuthorization permission="categoriaManager"><a class='update' title='Editar'  href="categoriaUpdate.do?id=${row.id}">Editar</a></mtw:hasAuthorization>
                                |<mtw:hasAuthorization permission="categoriaDelete"><a class='delete' title='Excluir' href="javascript:excluirCategoria(${row.id});">Excluir</a></mtw:hasAuthorization>
                            </td>
                        </tr>
                    </mtw:loop>
                    <tr>
                        <td align="right" width="100%" colspan="2" >
                            <%@include file="../template/paginacaoPaginator.jsp" %>
                        </td>
                    </tr>
                </mtw:list>
            </mtw:bean>
        </tbody>
    </table>
</mtw:bean>
