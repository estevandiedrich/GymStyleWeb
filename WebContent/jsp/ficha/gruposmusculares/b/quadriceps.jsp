<%@include file="../../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<tr>
    <td width="88%" valign="top">
        <h3>Membros Inferiores</h3>
        <div class="faixasForm" style="width: 99.5%;margin-left: 1px;background-color: #ffffff">
            <table width="100%" >
                <tr>
                    <td width="90%" >
                        <h1 id="quadriceps-b" class="mais" title="Selecione!">Quadríceps</h1>
                    </td>
                    <td width="10%" align="center" ><h4>Treino B</h4></td>
                </tr>
            </table>
            <div id="div-quadriceps-b" class="grupo">
                <table width="100%" class="displaytag">
                    <%@include file="../../headerTable.jsp" %>
                    <tbody>
                        <mtw:list value="quadriceps" var="pojo">
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
                                        <input type="hidden" name="nomeExercicio<mtw:out value="id"/>-b" id="nomeExercicio<mtw:out value="id"/>-b" value="<mtw:out value="exercicio"/>"/>
                                        <input type="hidden" name="${row.id}ordem-b" value="<mtw:out value="${row.id}ordem-b"/>"/>
                                        <mtw:out value="exercicio"/>
                                    </td>
                                    <td><mtw:input name="${row.id}serie-b" klass="inputTime" maxlength="3" /></td>
                                    <td><mtw:input name="${row.id}repeticao-b" klass="inputRepeticao" maxlength="11" /></td>
                                    <td><mtw:input name="${row.id}carga-b" klass="inputCarga" maxlength="15" /></td>
                                    <td align="center" class="columnError" ><div id="${row.id}exercicio-error-b" ></div></td>
                                </tr>
                            </mtw:loop>
                        </mtw:list>
                    </tbody>
                </table>
            </div>
        </div>
    </td>
</tr>
