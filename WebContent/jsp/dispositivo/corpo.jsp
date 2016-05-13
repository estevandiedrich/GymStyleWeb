<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/excluir.js" type="text/javascript"></script>

<mtw:bean value="paginator" var="pag">
    <table width="100%" class="displaytag">
        <thead>
            <tr>
                <th style="width: 30%">
                    <%@include file="../template/orderbyPaginator.jsp" %>
                    Catraca
                </th>
                <th style="width: 30%">Endereço</th><th style="width: 14%">Porta</th>
                <mtw:hasAuthorization groups="ADMINISTRADOR,SECRETARIA">            
                    <th style="width: 26%">Ação</th>
                </mtw:hasAuthorization>
            </tr>
        </thead>
        <tbody id="dados">
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
                            <td>
                                <mtw:out value="dispositivo"/>
                            </td>
                            <td>
                                <label title="Mac-'<mtw:out value="mac"/>'"><mtw:out value="enderecoIp"/></label>

                            </td>
                            <td>
                                <mtw:out value="porta"/>
                            </td>
                            <mtw:hasAuthorization permission="dispositivoManager,dispositivoDelete">
                                <td width="25%">
                                    <mtw:hasAuthorization permission="dispositivoManager">
                                        <a class='update' title='Editar' href="dispositivoUpdate.do?id=${row.id}">Editar</a> |
                                    </mtw:hasAuthorization>
                                    <mtw:if test="online" value="true" >
                                        <a id='puxar' title='Buscar Eventos OFFLINE!' href="buscarEventos.do?id=${row.id}">Eventos</a> |
                                    </mtw:if>
                                    <mtw:hasAuthorization permission="dispositivoDelete">
                                        <a class='delete' title='Excluir' href="javascript:excluirDispositivo(${row.id});">Excluir</a>
                                    </mtw:hasAuthorization>
                                   <!--|<a class='clear' title='Limpar Catraca' href="javascript:limpar(${row.id});">Limpar</a>
                                   |<a class='clear' title='Limpar Catraca' href="limparDispositivo.do?id=${row.id}">Limpar</a-->
                                </td>
                            </mtw:hasAuthorization>
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
