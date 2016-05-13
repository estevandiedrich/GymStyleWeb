<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/excluir.js" type="text/javascript"></script>

<mtw:bean value="paginator" var="pag">
    <table width="100%" class="displaytag">
        <thead>
            <tr>
                <th width="5%" ><label style="font-size: 8px">Código</label></th>
                <th width="35%"><%@include file="../template/orderbyPaginator.jsp" %> Nome</th>
                <th width="23%">Categoria</th>
                <th width="12%" ><label style="font-size: 13px">Preço de<br>Venda</label></th>
                <th width="10%" ><label style="font-size: 13px">Estoque<br>Atual</label></th>
                <th width="15%">Ação</th>
            </tr>
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
                                <c:when test="${i%2==0}">odd</c:when>
                                <c:otherwise>even</c:otherwise>
                            </c:choose>
                            ">
                            <td><label><mtw:out value="codigo" /></label></td>
                            <td><label title="<mtw:out value="nome"/>!"><mtw:out value="nome" max="30"/></label></td>
                            <td><label title="<mtw:out value="categoria.nome"/>!"><mtw:out value="categoria.nome" max="13"/></label></td>
                            <td><mtw:out value="precoVenda" formatter="realFormatter"/></td>
                            <td><mtw:out value="estoqueAtual"/></td>
                            <td class="acao">
                                <mtw:hasAuthorization permission="produtoUpdate" >
                                    <a class='update' title='Editar' href="produtoUpdate.do?id=${row.id}">Editar</a>
                                </mtw:hasAuthorization>
                                |<mtw:hasAuthorization permission="produtoDelete" >
                                    <a class='delete' title='Excluir' href="javascript:excluirProduto(${row.id});">Excluir</a>
                                </mtw:hasAuthorization>
                            </td>
                        </tr>
                    </mtw:loop>
                    <tr>
                        <td align="right" width="100%" colspan="6" >
                            <%@include file="../template/paginacaoPaginator.jsp" %>
                        </td>
                    </tr>
                </mtw:list>
            </mtw:bean>
        </tbody>
    </table>
</mtw:bean>
