<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="title_bottom">
    <%@include file="topo.jsp" %>
    <h3>Listar</h3>
    <div id="content" valign="top">
        <table width="100%" class="displaytag">
            <mtw:bean value="list">
                <thead>
                    <tr><th>Descrição</th><th>Valor</th><th>Ação</th></tr>
                </thead>
                <tbody>
                    <mtw:list value="list">
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
                                <td>
                                    <mtw:out value="descricao"/>
                                </td>
                                <td>
                                    <label title="<mtw:out value="valor" />"><mtw:out value="valor" max="40"/></label>
                                </td>
                                <td>
                                    <mtw:hasAuthorization permission="empresaRead">
                                        <a class='update' title='Editar' href="configuracaoUpdate.do?id=${row.id}">Editar</a>
                                    </mtw:hasAuthorization>
                                </td>
                            </tr>
                        </mtw:loop>
                    </mtw:list>
                </tbody>
            </mtw:bean>
        </table>
    </div>
</div>