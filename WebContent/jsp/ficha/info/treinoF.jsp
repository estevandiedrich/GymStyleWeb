<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<mtw:if test="treinoF">
    <tr>
        <td width="100%" valign="top" colspan="7">
            <h3></h3>
            <div class="faixasForm" style="width: 99.5%;margin-left: 1px;min-height: 60px">
                <table width="100%" class="displaytag">
                    <thead>
                        <tr>
                            <th width="5%" style="font-size: 10px">Ordem</th>
                            <th width="25%">Exercício</th>
                            <th width="10%">Série</th>
                            <th width="15%">Repetição</thhd>
                            <th width="20%">Carga</th>
                            <th width="15%">Grupo Muscular</th>
                        </tr>
                    </thead>
                    <tbody>
                        <mtw:list value="TreinoF.series" var="p">
                            <mtw:isEmpty>
                                <tr class="sub"><td colspan="7">Dados Vazios</td></tr>
                            </mtw:isEmpty>
                            <mtw:loop var="row" counterStart="1" counter="i">
                                <tr class="
                                    <mtw:if test="corF${row.exercicio.id}" value="1" >
                                        <c:choose>
                                            <c:when test="${i%2!=0}">color1-2</c:when>
                                            <c:otherwise>color1</c:otherwise>
                                        </c:choose>
                                    </mtw:if>
                                    <mtw:if test="corF${row.exercicio.id}" value="2" >
                                        <c:choose>
                                            <c:when test="${i%2!=0}">color2</c:when>
                                            <c:otherwise>color2-2</c:otherwise>
                                        </c:choose>
                                    </mtw:if>" >
                                    <td><mtw:out value="i"/></td>
                                    <td><mtw:out value="exercicio.exercicio"/></td>
                                    <td><mtw:out value="serie"/></td>
                                    <td><mtw:out value="repeticao"/></td>
                                    <td><mtw:out value="carga"/></td>
                                    
                                    <mtw:if test="row.exercicio.grupoMuscular.grupoMuscular" value="null" negate="true">
                                        <td rowspan="<mtw:out value="rowGrupoF${row.exercicio.id}"/>"
                                            class="<mtw:if test="corF${row.exercicio.id}" value="1" >color1</mtw:if><mtw:if test="corF${row.exercicio.id}" value="2" >color2</mtw:if>" >
                                            <mtw:out value="row.exercicio.grupoMuscular.grupoMuscular"/>
                                        </td>
                                    </mtw:if>
                                </tr>
                            </mtw:loop>
                        </mtw:list>
                    </tbody>
                </table>
            </div>
        </td>
    </tr>
</mtw:if>