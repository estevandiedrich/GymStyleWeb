<%@include file="../../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<tr>
    <td width="88%" valign="top">
        <div class="faixasForm" style="width: 99.5%;margin-left: 1px;background-color: #ffffff">
            <table width="100%" >
                <tr>
                    <td width="90%" >
                        <h1 id="gastrocnemicos-a" class="mais" title="Selecione">Gastrocnêmicos</h1>
                    </td>
                    <td width="10%" align="center" ><h4>Treino A</h4></td>
                </tr>
            </table>
            <div id="div-gastrocnemicos-a" class="grupo">
                <table width="100%" class="displaytag">
                    <%@include file="../../headerTable.jsp" %>
                    <tbody id="tBody">
                        <mtw:list value="gastronemicos" var="pojo">
                            <mtw:isEmpty>
                                <%@include file="../../isEmpty.jsp" %>
                            </mtw:isEmpty>
                        <input name="idsGastronemicos-a" id="idsGastronemicos-a" type="hidden" value="<mtw:loop var="row" counterStart="0" counter="i"><mtw:out value="id"/>-</mtw:loop>">
                        <mtw:loop var="row" counterStart="1" counter="i">
                            <tr class="
                                <c:choose>
                                    <c:when test="${i%2!=0}">odd</c:when>
                                    <c:otherwise>even</c:otherwise>
                                </c:choose>
                                ">
                                <td>
                                    <input type="hidden" name="nomeExercicio<mtw:out value="id"/>-a" id="nomeExercicio<mtw:out value="id"/>-a" value="<mtw:out value="exercicio"/>"/>
                                    <input type="hidden" name="${row.id}ordem-a" value="<mtw:out value="${row.id}ordem-a"/>"/>
                                    <mtw:out value="exercicio"/>
                                </td>
                                <td><mtw:input name="${row.id}serie-a" klass="inputTime" maxlength="3" /></td>
                                <td><mtw:input name="${row.id}repeticao-a" klass="inputRepeticao" maxlength="11" /></td>
                                <td><mtw:input name="${row.id}carga-a" klass="inputCarga" maxlength="15" /></td>
                                <td align="center" class="columnError" ><div id="${row.id}exercicio-error-a" ></div></td>
                            </tr>
                        </mtw:loop>
                    </mtw:list>
                    </tbody>
                </table>
            </div>
        </div>
    </td>
</tr>
