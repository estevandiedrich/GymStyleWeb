<%@include file="../../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<tr>
    <td width="88%" valign="top">
        <div class="faixasForm" style="width: 99.5%;margin-left: 1px;background-color: #ffffff">
            <table width="100%" >
                <tr>
                    <td width="90%" >
                        <h1 id="adutores-c" class="mais" title="Selecione!">Adutores</h1>
                    </td>
                    <td width="10%" align="center" ><h4>Treino C</h4></td>
                </tr>
            </table>
            <div id="div-adutores-c" class="grupo" >
                <table width="100%" class="displaytag">
                    <%@include file="../../headerTable.jsp" %>
                    <tbody>
                        <mtw:list value="adutores" var="pojo">
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
                                        <input type="hidden" name="nomeExercicio<mtw:out value="id"/>-c" id="nomeExercicio<mtw:out value="id"/>-c" value="<mtw:out value="exercicio"/>"/>
                                        <input type="hidden" name="${row.id}ordem-c" value="<mtw:out value="${row.id}ordem-c"/>"/>
                                        <mtw:out value="exercicio"/>
                                    </td>
                                    <td><mtw:input name="${row.id}serie-c" klass="inputTime" maxlength="3" /></td>
                                    <td><mtw:input name="${row.id}repeticao-c" klass="inputRepeticao" maxlength="11" /></td>
                                    <td><mtw:input name="${row.id}carga-c" klass="inputCarga" maxlength="15" /></td>
                                    <td align="center" class="columnError" ><div id="${row.id}exercicio-error-c" ></div></td>
                                </tr>
                            </mtw:loop>
                        </mtw:list>
                    </tbody>
                </table>
            </div>
        </div>
    </td>
</tr>
