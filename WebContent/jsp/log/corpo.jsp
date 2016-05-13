<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<mtw:bean value="paginator" var="pag">
    <div id="content" valign="top">
        <table width="100%" class="displaytag">
            <thead>
                <tr>
                    <th width="20%">
                        <%@include file="../template/orderbyPaginator.jsp" %> Data
                    </th>
                    <th width="10%">Tipo</th><th width="40%">Descrição</th><th width="30%">Usuário</th></tr>
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
                                <td>
                                    <mtw:out value="data.time"/>
                                </td>
                                <td>
                                    <mtw:out value="tipo"/>
                                </td>
                                <td>
                                    <mtw:out value="descricao"/>
                                </td>
                                <td>
                                    <label title="<mtw:out value="usuario.usuario"/>"><mtw:out value="usuario.usuario" max="30"/></label>                          
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
    </div>
</mtw:bean>
