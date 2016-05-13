<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<mtw:bean value="paginator" var="pag">
    <table width="100%" class="displaytag">
        <mtw:bean value="pag.list">
            <thead>
                <tr><th width="3%">-</th><th width="10%" >Matrícula</th><th width="46%" ><%@include file="../template/orderbyPaginator.jsp" %>Aluno</th>
                    <th width="17%" >CPF</th><th width="24%" colspan="2">Ação</th></tr>
            </thead>
            <tbody>
                <mtw:list value="pag.list">
                    <mtw:isEmpty>
                        <tr class="sub">
                            <td colspan="6">
                                Dados Vazios
                            </td>
                        </tr>
                    </mtw:isEmpty>
                    <mtw:loop var="usuario" counterStart="1" counter="i">
                        <tr class="
                            <c:choose>
                                <c:when test="${i%2!=0}">odd</c:when>
                                <c:otherwise>even</c:otherwise>
                            </c:choose>
                            ">
                            <td>
                                <%@include file="../requisicao/link.jsp" %>
                            </td>
                            <td>
                                <label title="<mtw:out value="matricula"/>"><mtw:out value="matricula" max="30"/></label>
                            </td>
                            <td>
                                <label title="<mtw:out value="usuario"/>"><mtw:out value="usuario" max="40"/></label>
                            </td>
                            <td>
                                <mtw:out value="cpf"/>
                            </td>
                            <td width="12%">
                                <mtw:hasAuthorization permission="usuarioPlanosRead" >
                                    <mtw:out value="usuarioPlano${usuario.id}"/>
                                </mtw:hasAuthorization>
                            </td>
                            <td width="12%" align="center" >
                                <mtw:hasAuthorization permission="vincularPlano" >
                                    <mtw:out value="usuarioVincular${usuario.id}"/>
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
            </tbody>
        </mtw:bean>
    </table>
</mtw:bean>
