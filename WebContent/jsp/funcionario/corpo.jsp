<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/excluir.js" type="text/javascript"></script>
<mtw:bean value="paginator" var="pag">
    <table width="100%" class="displaytag">
        <thead>
            <tr>
                <th>-</th>
                <th><%@include file="../template/orderbyPaginator.jsp" %>Nome</th>
                <th>Telefone</th><th>Celular</th><th>Tipo</th><th>Ação</th>
            </tr>
        </thead>
        <tbody>
            <mtw:bean value="pag.list">
                <mtw:list value="pag.list">
                    <mtw:isEmpty>
                        <tr class="sub">
                            <td colspan="8">
                                Dados Vazios
                            </td>
                        </tr>
                    </mtw:isEmpty>
                    <mtw:loop var="usuario" counterStart="0" counter="i">
                        <tr class="
                            <c:choose>
                                <c:when test="${i%2==0}">odd</c:when>
                                <c:otherwise>even</c:otherwise>
                            </c:choose>
                            ">
                            <td width="3%" >
                                <mtw:if test="sincronizado" negate="true">
                                    <a href="requisicaoAtualizarUsuario.do?id=<mtw:out value="id"/>">
                                        <img src="images/sinc_error.png" title="Não Sincronizado. Selecione para sincronizar!" style="cursor: pointer"/>
                                    </a>
                                </mtw:if>
                                <mtw:if test="sincronizado" negate="false">
                                    <a href="requisicaoAtualizarUsuario.do?id=<mtw:out value="id"/>">
                                        <img src="images/sinc_ok.png" title="Sincronizado! Selecione para sincronizar!" style="cursor: pointer"/>
                                    </a>
                                </mtw:if>
                            </td>
                            <td width="37%">
                                <label title="<mtw:out value="usuario"/>"><mtw:out value="usuario" max="40"/></label>
                            </td>
                            <td width="15%">
                                <mtw:out value="telefone"/>
                            </td>
                            <td width="15%">
                                <mtw:out value="celular"/>
                            </td>
                            <td width="15%">
                                <c:if test="${usuario.tipoUsuario==1}">
                                    <label>Administrador(a)</label>
                                </c:if>
                                <c:if test="${usuario.tipoUsuario==3}">
                                    <label>Instrutor(a)</label>
                                </c:if>
                                <c:if test="${usuario.tipoUsuario==4}">
                                    <label>Secretario(a)</label>
                                </c:if>
                            </td>
                            <td width="15%">
                                <mtw:hasAuthorization permission="funcionarioManager">
                                    <a class='update' title='Editar'  href="funcionarioUpdateInformacao.do?id=${usuario.id}">Editar</a>
                                </mtw:hasAuthorization>
                                <c:if test="${usuario.id!=1}" >
                                    <c:if test="${criterioAtivoFuncionario}">
                                    <mtw:hasAuthorization permission="funcionarioDelete">
                                        | <a class='delete' title='Excluir' href="javascript:excluirFuncionario(${usuario.id});">Excluir</a>
                                    </mtw:hasAuthorization>
                                        </c:if>
                                </c:if>
                            </td>
                        </tr>
                    </mtw:loop>
                    <tr>
                        <td align="right" width="100%" colspan="8">
                            <%@include file="../template/paginacaoPaginator.jsp" %>
                        </td>
                    </tr>
                </mtw:list>
            </mtw:bean>
        </tbody>
    </table>
</mtw:bean>
