<%@include file="../../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<tr>
    <td width="88%" valign="top">
        <div class="faixasForm" style="width: 99.5%;margin-left: 1px;background-color: #ffffff">
            <table width="100%" >
                <tr>
                    <td width="90%" >
                        <h1 id="soleo-f" class="mais" title="Selecione">Sóleo</h1>
                    </td>
                    <td width="10%" align="center" ><h4>Treino F</h4></td>
                </tr>
            </table>
            <div id="div-soleo-f" class="grupo">
                <table width="100%" class="displaytag">
                    <%@include file="../../headerTable.jsp" %>
                    <tbody>
                        <mtw:list value="soleos" var="pojo">
                            <mtw:isEmpty>
                                <%@include file="../../isEmpty.jsp" %>
                            </mtw:isEmpty>
                            <mtw:loop var="row" counterStart="1" counter="i">
                                <tr class=" 
                                    <c:choose>
                                        <c:when test="${i%2!=0}">odd</c:when>
                                        <c:otherwise>even</c:otherwise>
                                    </c:choose>
                                    ">
                                    <td>
                                        <input type="hidden" name="nomeExercicio<mtw:out value="id"/>-f" id="nomeExercicio<mtw:out value="id"/>-f" value="<mtw:out value="exercicio"/>"/>
                                        <input type="hidden" name="${row.id}ordem-f" value="<mtw:out value="${row.id}ordem-f"/>"/>
                                        <mtw:out value="exercicio"/>
                                    </td>
                                    <td><mtw:input name="${row.id}serie-f" klass="inputTime" maxlength="3" /></td>
                                    <td><mtw:input name="${row.id}repeticao-f" klass="inputRepeticao" maxlength="11" /></td>
                                    <td><mtw:input name="${row.id}carga-f" klass="inputCarga" maxlength="15" /></td>
                                    <td align="center" class="columnError" ><div id="${row.id}exercicio-error-f" ></div></td>
                                </tr>
                            </mtw:loop>
                        </mtw:list>
                    </tbody>
                </table>
            </div>
        </div>
    </td>
</tr>
