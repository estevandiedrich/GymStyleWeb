<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<mtw:bean value="paginator" var="pag" >
    <table width="100%" class="displaytag" >
        <thead>
            <tr>
                <th align="center" width="8%">Dia</th>
                <!--th width="19%">Saldo Inicial</th-->
                <th width="23%">Entradas</th>
                <th width="23%">Retiradas</th>
                <th width="23%">Saldo do Dia</th>
                <th width="23%">Saldo Acumulado</th>
            </tr>
        </thead>
        <tbody>
            <mtw:list value="list" >
                <mtw:isEmpty>
                    <tr class="sub">
                        <td colspan="5">
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
                        <td <mtw:if test="row.acimaDataAtual" negate="true">align="left"</mtw:if> >
                            <mtw:if test="row.acimaDataAtual" negate="true">
                                <label class="mais" onclick="mostraLinha(<mtw:out value="dia"/>)" id="label<mtw:out value="dia"/>">
                                </mtw:if>
                                <mtw:out value="dia"/>
                                <mtw:if test="row.acimaDataAtual" negate="true"></label></mtw:if>
                        </td>
                        <!--td><label>R$ < mtw:out value="saldoInicial" formatter="realFormatter" /></label></td-->
                        <td>
                            <mtw:if test="row.acimaDataAtual" >-</mtw:if>
                            <mtw:if test="row.acimaDataAtual" negate="true"><label class="oks">R$ <mtw:out value="entrada" formatter="realFormatter" /></label></mtw:if>
                        </td>
                        <td>
                            <mtw:if test="row.acimaDataAtual" >-</mtw:if>
                            <mtw:if test="row.acimaDataAtual" negate="true"><label class="error">R$ <mtw:out value="retirada" formatter="realFormatter" /></label></mtw:if>
                        </td>
                        <td>
                            <mtw:if test="row.acimaDataAtual" >-</mtw:if>
                            <mtw:if test="row.acimaDataAtual" negate="true"><label>R$ <mtw:out value="saldoDia" formatter="realFormatter" /></label></mtw:if>
                        </td>
                        <td>
                            <mtw:if test="row.acimaDataAtual" >-</mtw:if>
                            <mtw:if test="row.acimaDataAtual" negate="true"><label>R$ <mtw:out value="saldoAcumulado" formatter="realFormatter" /></label></mtw:if>
                        </td>
                    </tr>
                    <tr class="sub4 linha" id="linha<mtw:out value="dia"/>" >
                        <td colspan="5"><div  width="100%" id="caixas<mtw:out value="dia"/>"><%@include file="caixas.jsp"%></div></td>
                    </tr>
                </mtw:loop>
            </mtw:list>
        </tbody>
    </table>
    <mtw:isEmpty test="list" negate="true">
        <table width="100%" >
            <tr><th align="center" colspan="5">RESUMO DO MÊS</th></tr>
            <tr class="sub2">
                <td align="center">
                    <table class="displaytag faixasForm" style="width: 400px">
                        <thead>
                        </thead>
                        <tbody>
                            <tr class="sub2">
                                <td width="50%"><label class="obrig">Entrada:</label></td>
                                <td width="50%">
                                    <label class="oks">R$ <mtw:out value="entradaTotal" formatter="realFormatter" /></label>
                                </td>
                            </tr>
                            <tr class="odd">
                                <td><label class="obrig">Retirada:</label></td>
                                <td><label class="error">R$ <mtw:out value="retiradaTotal" formatter="realFormatter" /></label></td>
                            </tr>
                            <tr class="even">
                                <td><label class="obrig">Saldo:</label></td>
                                <td><label>R$ <mtw:out value="saldoAcumuladoTotal" formatter="realFormatter" /></label></td>
                            </tr>
                            <tr class="sub"><td colspan="5"></tr>
                        </tbody>
                    </table>
                </td>
            </tr>
        </table>
    </mtw:isEmpty>
</mtw:bean>