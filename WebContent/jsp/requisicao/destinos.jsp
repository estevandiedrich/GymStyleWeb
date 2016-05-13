<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="faixasForm" style="width: 98%">
    <mtw:bean value="list">
        <h3>Destinatários</h3>
        <div style="width: 100%">
            <table class="displaytag" width="100%">
                <mtw:bean value="list" >
                    <thead>
                        <tr><th>Catraca</th><th>Endereço IP</th><th>Status</th></tr>
                    </thead>
                    <tbody>
                        <mtw:list value="list" >
                            <mtw:isEmpty>
                                <tr class="sub2" >
                                    <td colspan="6" valign="center"><div id="listaVazia"><img src='images/carregandoPeq.gif'/>Buscando destinos...</div></td>
                                </tr>
                            </mtw:isEmpty>
                            <mtw:loop var="row" counterStart="0" counter="i">
                                <tr class="
                                    <c:choose>
                                        <c:when test="${i%2!=0}">sub4</c:when>
                                        <c:otherwise>sub5</c:otherwise>
                                    </c:choose>
                                    " style="height: 35px;">
                                    <td width="40%"><mtw:out value="dispositivo"/></td>
                                    <td width="30%">
                                        <mtw:if test="enderecoIp" value="0.0.0.0" negate="true">
                                            <mtw:out value="enderecoIp"/>
                                        </mtw:if>
                                        <mtw:if test="enderecoIp" value="0.0.0.0">
                                            <table class="displaytag" style="width: 100%;">
                                                <tr class="sub" >
                                                    <td valign="center">
                                                        <mtw:out value="enderecoIp"/>
                                                    </td>
                                                </tr>
                                                <tr class="sub2" >
                                                    <td valign="center" style="font-size: 12;font-style: italic">
                                                        <img src='images/offline.png'/> Catraca fora da rede!
                                                    </td>
                                                </tr>
                                            </table>
                                        </mtw:if>
                                    </td>
                                    <td width="30%" style="font-size: 12;font-style: italic">
                                        <mtw:if test="enderecoIp" value="0.0.0.0" negate="true">
                                            <mtw:out value="dispositivo${row.id}"/><!--$ {contador*0.250}/$ {tempoFinal*0.250}-->
                                        </mtw:if>
                                        <mtw:if test="enderecoIp" value="0.0.0.0">
                                            <table class="displaytag" style="width: 100%">
                                                <tr>
                                                    <td width="20%" align="right"><img src='images/error.png'/></td>
                                                    <td width="60%" align="center" style="font-size: 12;font-style: italic">
                                                        <mtw:hasAuthorization permission="dispositivoBuscar" >
                                                            <a href="dispositivoBuscar.do">Clique aqui para buscar dispositivos</a>
                                                        </mtw:hasAuthorization>
                                                    </td>
                                                    <td width="20%" align="right"></td>
                                                </tr>
                                            </table>
                                        </mtw:if>
                                    </td>
                                </tr>
                            </mtw:loop>
                        </mtw:list>
                    </tbody>
                </mtw:bean>
            </table>
        </div>
    </mtw:bean>
</div>
<input type="hidden" name="contador" id="contador" value="<mtw:out value="contador"/>"/>
<input type="hidden" name="tempoFinal" id="tempoFinal" value="<mtw:out value="tempoFinal"/>"/>