<%@include file="../../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<tr>
    <td width="100%" valign="top">
        <h3>Membros Superiores</h3>
        <div class="faixasForm" style="width: 99.5%;margin-left: 1px;background-color: #ffffff">
            <table width="100%" >
                <tr>
                    <td width="90%" >
                        <h1 id="peitoral-e" class="mais" title="Selecione!">Peitoral</h1>
                    </td>
                    <td width="10%" align="center" ><h4>Treino E</h4></td>
                </tr>
            </table>
            <div id="div-peitoral-e" class="grupo">
                <table width="100%" class="displaytag">
                    <%@include file="../../headerTable.jsp" %>
                    <tbody>
                        <mtw:list value="peitoral" var="pojo">
                            <mtw:isEmpty>
                                <%@include file="../../isEmpty.jsp" %>
                            </mtw:isEmpty>
                            <mtw:loop var="row" counterStart="0" counter="i">
                                <tr class="
                                    <c:choose>
                                        <c:when test="${i%2!=0}">odd</c:when>
                                        <c:otherwise>even</c:otherwise>
                                    </c:choose>
                                    ">
                                    <td>
                                        <input type="hidden" name="nomeExercicio<mtw:out value="id"/>-e" id="nomeExercicio<mtw:out value="id"/>-e" value="<mtw:out value="exercicio"/>"/>
                                        <input type="hidden" name="${row.id}ordem-e" value="<mtw:out value="${row.id}ordem-e"/>"/>
                                        <mtw:out value="exercicio"/>
                                    </td>
                                    <td><mtw:input name="${row.id}serie-e" klass="inputTime" maxlength="3" /></td>
                                    <td><mtw:input name="${row.id}repeticao-e" klass="inputRepeticao" maxlength="11" /></td>
                                    <td><mtw:input name="${row.id}carga-e" klass="inputCarga" maxlength="15" /></td>
                                    <td align="center" class="columnError" ><div id="${row.id}exercicio-error-e" ></div></td>
                                </tr>
                            </mtw:loop>
                        </mtw:list>
                    </tbody>
                </table>
            </div>
        </div>
    </td>
</tr>
