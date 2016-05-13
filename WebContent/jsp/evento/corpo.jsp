<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<mtw:bean value="paginator" var="pag" >
    <table width="100%" class="displaytag">
        <thead>
            <tr>
                <th width="20%" align="center" colspan="2" >
                    <input type='checkbox' <c:if test="${pag.orderBy}">checked="true"</c:if> name='orderBy'
                           value='1' id="orderBy" class="orderBy" onclick="submitRelatorioFormEventoRead()" />
                        <label for="orderBy" style="background-color: #fff;" title="<mtw:out value="pag.order"/>"></label>
                    Data - Hora</th>
                <th width="30%">Nome</th>
                <th width="30%">Descrição</th>
                <th width="20%">Catraca</th></tr>
        </thead>
        <tbody>
            <mtw:list value="pag.list" >
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
                            <mtw:if test="usuario" value="null" negate="true">
                                <mtw:if test="entrada" value="true" negate="false">
                                    <img src="images/entrada.png" title="Entrada!"/>
                                </mtw:if>
                                <mtw:if test="entrada" value="true" negate="true">
                                    <img src="images/saida.png" title="Saída"/>
                                </mtw:if>
                            </mtw:if>

                            <mtw:if test="motivo.motivo" value="Passagem Liberada na Catraca" negate="false">
                                <mtw:if test="entrada" value="true" negate="false">
                                    <img src="images/entrada.png" title="Entrada!"/>
                                </mtw:if>
                                <mtw:if test="entrada" value="true" negate="true">
                                    <img src="images/saida.png" title="Saída"/>
                                </mtw:if>
                            </mtw:if>
                        </td>
                        <td>
                            <mtw:out value="dataHora.time" />
                        </td>
                        <td>
                            <mtw:out value="usuario.usuario"/>
                        </td>
                        <td>
                            <mtw:if test="motivo.motivo" value="Passagem Liberada na Catraca" negate="true">
                                <mtw:if test="motivo.motivo" value="Liberado" negate="false">
                                    <mtw:if test="realizado" value="true" negate="false">
                                        <font class="liberado" style="font-size: 13px"><mtw:out value="motivo.motivo"/></font>
                                    </mtw:if>
                                    <mtw:if test="realizado" value="true" negate="true">
                                        <font class="error" style="padding-left: 10px;font-size: 13px" title="Não realizado!"><mtw:out value="motivo.motivo"/></font>
                                    </mtw:if>
                                </mtw:if>
                            </mtw:if>

                            <mtw:if test="motivo.motivo" value="Passagem Liberada na Catraca" negate="true">
                                <mtw:if test="motivo.motivo" value="Liberado" negate="true">
                                    <font class="error" ><mtw:out value="motivo.motivo"/></font>
                                </mtw:if>
                            </mtw:if>

                            <mtw:if test="motivo.motivo" value="Passagem Liberada na Catraca" negate="false">
                                <mtw:if test="realizado" value="true" negate="false">
                                    <font class="liberado" ><mtw:out value="motivo.motivo"/></font>
                                </mtw:if>
                                <mtw:if test="realizado" value="true" negate="true">
                                    <font class="error" title="Não realizado!"><mtw:out value="motivo.motivo"/></font>
                                </mtw:if>
                            </mtw:if>
                        </td>
                        <td>
                            <mtw:out value="dispositivo"/>
                        </td>
                    </tr>
                </mtw:loop>
                <tr>
                    <td align="right" width="100%" colspan="5">
                        <%@include file="paginacaoEventos.jsp" %>
                    </td>
                </tr>
            </mtw:list>
        </tbody>
    </table>
</mtw:bean>
