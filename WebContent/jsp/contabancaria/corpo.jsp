
<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/excluir.js" type="text/javascript"></script>
<mtw:bean value="paginator" var="pag">
    <table width="100%" class="displaytag">
        <thead>
            <tr><th><%@include file="../template/orderbyPaginator.jsp" %> Banco</th><th>Agência</th><th>Conta</th><th>Ação</th></tr>
        </thead>
        <tbody>
            <mtw:bean value="pag.list">
                <mtw:list value="pag.list">
                    <mtw:isEmpty>
                        <tr class="sub">
                            <td colspan="4">
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
                            <td width="34%" title="<mtw:out value="banco.nome" />!"><mtw:out value="banco.nome"  max="35"/></td>
                            <td width="20%" ><mtw:out value="agencia"/></td>
                            <td width="20%" title="<mtw:out value="numeroConta" />!" ><mtw:out value="numeroConta" max="15"/></td>
                            <td width="26%">
                                <mtw:hasAuthorization permission="managerContaBancaria">
                                <a id="plano" href="managerContaBancaria.do?id=<mtw:out value="id"/>" title="Gerenciar Conta!" >Gerenciar</a>
                                </mtw:hasAuthorization>
                                <mtw:hasAuthorization permission="contaBancariaManager">| <a class='update' title='Editar'  href="contaBancariaUpdate.do?id=${row.id}">Editar</a></mtw:hasAuthorization>
                                | <mtw:hasAuthorization permission="contaBancariaDelete"><a class='delete' title='Excluir' href="javascript:excluirContaBancaria(${row.id});">Excluir</a></mtw:hasAuthorization>
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
