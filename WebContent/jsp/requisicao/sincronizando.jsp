<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<input type="hidden" name="idRequisicao<mtw:out value="linha"/>" id="idRequisicao<mtw:out value="linha"/>" value="<mtw:out value="idRequisicao"/>"/>
<input type="hidden" name="idPlanoUsuario<mtw:out value="linha"/>" id="idPlanoUsuario<mtw:out value="linha"/>" value="<mtw:out value="idPlanoUsuario"/>"/>
<input type="hidden" name="contador<mtw:out value="linha"/>" id="contador<mtw:out value="linha"/>" value="<mtw:out value="contador"/>"/>
<input type="hidden" name="tempo<mtw:out value="linha"/>" id="tempo<mtw:out value="linha"/>" value="<mtw:out value="tempo"/>"/>
<input type="hidden" name="sincronizado<mtw:out value="linha"/>" id="sincronizado<mtw:out value="linha"/>" value="0" />

<div class="faixasForm">
    <table width="100px" class="displaytag">
        <thead>
            <tr>
                <th><mtw:out value="percentual"/> Catraca</th>
                <th>Status</th></tr>
        </thead>
        <tbody>
            <mtw:bean value="list">
                <mtw:list value="list">
                    <mtw:isEmpty>
                        <tr class="sub">
                            <td><img src="images/carregandoPeq.gif"</td><td>Buscando Destinos</td>
                        </tr>
                    </mtw:isEmpty>
                    <mtw:loop var="row" counterStart="0" counter="i">
                        <tr class="
                            <c:choose>
                                <c:when test="${i%2==0}">odd</c:when>
                                <c:otherwise>even</c:otherwise>
                            </c:choose>
                            ">
                            <td width="50%" ><mtw:out value="nome" /></td>
                            <td width="50%" ><mtw:out value="msg" /></td>
                        </tr>
                    </mtw:loop>
                </mtw:list>
            </mtw:bean>
        </tbody>
    </table>
</div>