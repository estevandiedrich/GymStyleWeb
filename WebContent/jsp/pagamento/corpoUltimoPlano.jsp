<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<mtw:bean value="paginator" var="pag">
    <table width="100%" class="displaytag">
        <mtw:bean value="pag.list">
            <thead>
                <tr>
                    <th>-</th>
                    <th>Matrícula</th>
                    <th>
                        <%@include file="../template/orderbyPaginator.jsp" %>Aluno
                    </th>
                    <th>Plano</th>
                    <th>Ação</th></tr>
            </thead>
            <tbody>
                <mtw:list value="pag.list">
                    <mtw:isEmpty>
                        <tr class="sub">
                            <td colspan="4">
                                Dados Vazios
                            </td>
                        </tr>
                    </mtw:isEmpty>
                    <mtw:loop var="usuario" counterStart="1" counter="i">
                        <tr class="
                            <c:choose>
                                <c:when test="${i%2==0}">odd</c:when>
                                <c:otherwise>even</c:otherwise>
                            </c:choose>
                            ">
                            <td width="3%" >
                                <%@include file="../requisicao/link.jsp" %>
                            </td>
                            <td width="10%">
                                <mtw:out value="matricula"/>
                            </td>
                            <td width="32%">
                                <mtw:out value="usuario"/>
                            </td>
                            <td class="obrig"width="40%">
                                <label title=" <mtw:out value="usuarioPlano${usuario.id}"/>!"> <mtw:out value="usuarioPlano${usuario.id}" max="30"/></label>
                            </td>
                            <td width="15%">
                                <mtw:hasAuthorization permission="pagamentoUltimoPlanosRead" >
                                    <mtw:out value="usuarioPlanoVer${usuario.id}"/><!-- ta vindo o link da action-->
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
