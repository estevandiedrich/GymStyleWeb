<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/excluir.js" type="text/javascript"></script>

<mtw:bean value="paginator" var="pag">
    <table width="100%" class="displaytag">
        <thead>
            <tr><th width="30%"><%@include file="../template/orderbyPaginator.jsp" %> Nome</th>
                <th width="25%">Cidade</th><th width="25%">Telefone</th><th width="20%">Ação</th></tr>
        </thead>
        <tbody>
            <mtw:bean value="pag.list">
                <mtw:list value="pag.list">
                    <mtw:isEmpty>
                        <tr class="sub"><td colspan="4">Dados Vazios</td></tr>
                    </mtw:isEmpty>
                    <mtw:loop var="row" counterStart="0" counter="i">
                        <tr class="
                            <c:choose>
                                <c:when test="${i%2==0}">odd</c:when>
                                <c:otherwise>even</c:otherwise>
                            </c:choose>
                            ">
                            <td width="35%" ><mtw:out value="nome" max="20"/></td>
                            <td width="25%" ><mtw:out value="cidade" max="20"/></td>
                            <td width="20%" ><mtw:out value="telefone" max="14"/></td>
                            <td width="20%">
                                <mtw:hasAuthorization permission="fornecedorManager">
                                    <a class='update' title='Editar' href="fornecedorUpdate.do?id=${row.id}">Editar</a>
                                </mtw:hasAuthorization>
                                |<mtw:hasAuthorization permission="fornecedorDelete"><a class='delete' title='Excluir' href="javascript:excluirFornecedor(${row.id});">Excluir</a></mtw:hasAuthorization>
                                </td>
                            </tr>
                    </mtw:loop>
                    <tr>
                        <td align="right" width="100%" colspan="4">
                            <%@include file="../template/paginacaoPaginator.jsp" %>
                        </td>
                    </tr>
                </mtw:list>
            </mtw:bean>
        </tbody>
    </table>
</mtw:bean>
