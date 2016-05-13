<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/modalidade/updateHorario.js"></script>

<table width="98%" align="center" class="faixasForm">
    <tr>
        <td class="one">
            <mtw:label klass="obrig" value="Identificação:"/>
        </td>
        <td width="25%">
            <mtw:input klass="input" type="text" name="identificacaoNovo" maxlength="49"/>
        </td>
        <td width="50%">
            <font class="errors"><mtw:outError field="identificacaoNovo" ><mtw:out/></mtw:outError></font>
        </td>
    </tr>
</table>
<table width="90%" align="center"style="font-size: 13px;margin-left: 10px">
    <tr>
        <td colspan="4"></td>
    </tr>
    <tr>
        <td>
            <div class="top_frame"></div>
            <div class="middle_frame" style="width: 190px;">
                <h4>1°Período</h4>
                <table align="center">
                    <tr>
                        <td class="one"></td>
                        <td><mtw:label klass="obrig" value="Entrada:"/></td>
                        <td><mtw:input klass="inputTime" name="entradaNovo1" id="entradaNovo1" size="5" /></td>
                    </tr>
                    <tr>
                        <td class="one"></td>
                        <td><mtw:label klass="obrig" value="Saída:"/></td>
                        <td><mtw:input klass="inputTime" name="saidaNovo2" id="saidaNovo2" size="5"/></td>
                    </tr>
                </table>
            </div>
            <div class="bottom_frame"></div>
        </td>
        <td>
            <div class="top_frame"></div>
            <div class="middle_frame" style="width: 190px;">
                <h4>2°Período</h4>
                <table align="center">
                    <tr>
                        <td class="one"></td>
                        <td><mtw:label klass="obrig" value="Entrada:"/></td>
                        <td><mtw:input klass="inputTime" name="entradaNovo3" id="entradaNovo3" size="5"/></td>
                    </tr>
                    <tr>
                        <td class="one"></td>
                        <td><mtw:label klass="obrig" value="Saída:"/></td>
                        <td><mtw:input klass="inputTime" name="saidaNovo4" id="saidaNovo4" size="5"/></td>
                    </tr>
                </table>
            </div>
            <div class="bottom_frame"></div>
        </td>
        <td>
            <div class="top_frame"></div>
            <div class="middle_frame" style="width: 190px;">
                <h4>3°Período</h4>
                <table align="center">
                    <tr>
                        <td class="one"></td>
                        <td><mtw:label klass="obrig" value="Entrada:"/></td>
                        <td><mtw:input klass="inputTime" name="entradaNovo5" id="entradaNovo5" size="5"/></td>
                    </tr>
                    <tr>
                        <td class="one"></td>
                        <td><mtw:label klass="obrig" value="Saída:"/></td>
                        <td><mtw:input klass="inputTime" name="saidaNovo6" id="saidaNovo6" size="5"/></td>
                    </tr>
                </table>
            </div>
            <div class="bottom_frame"></div>
        </td>
        <td>
            <div class="top_frame"></div>
            <div class="middle_frame" style="width: 190px;">
                <h4>4°Período</h4>
                <table align="center">
                    <tr>
                        <td class="one"></td>
                        <td><mtw:label klass="obrig" value="Entrada:"/></td>
                        <td><mtw:input klass="inputTime" name="entradaNovo7" id="entradaNovo7"  size="5"/></td>
                    </tr>
                    <tr>
                        <td class="one"></td>
                        <td><mtw:label klass="obrig" value="Saída:"/></td>
                        <td><mtw:input klass="inputTime" name="saidaNovo8" id="saidaNovo8" size="5"/></td>
                    </tr>
                </table>
            </div>
            <div class="bottom_frame"></div>
        </td>
    </tr>
</table>
<table width="95%" align="center">
    <tr>
        <td width="100%"  align="center">
            <mtw:buttonAction klass="aplicar" name="aplicar" value="Aplicar"  onclick="eventoAplicarFaixasNovo()"/>
        </td>
    </tr>
</table>
<mtw:input type="hidden" name="horarioNovoError"/>
<font class="errors"><mtw:outError field="horarioNovoError" ><mtw:out/></mtw:outError></font>
<h1>Dias da Semana</h1>
<table width="95%" align="center" class="faixasForm" id="todoss" onloadstart="verificarCheckDiasSemana()">
    <tr>
        <td class="chek">
            <input type="checkbox" name="todosNovo" id="todosNovo"  <c:if test="${todosNovo}">checked="true"</c:if>  onclick="eventoTodosAplicarFaixasNovo()"/> <mtw:label klass="obrig" value="Todos"/>
        </td>
        <td>
            <h4>1°Período</h4>
        </td>
        <td>
            <h4>2°Período</h4>
        </td>
        <td>
            <h4>3°Período</h4>
        </td>
        <td>
            <h4>4°Período</h4>
        </td>
    </tr>
    <tr>
        <td class="chek">
            <input type="checkbox" name="domingoNovo" id="domingoNovo" onclick="verificaTodosNovo('Novo')" <c:if test="${domingoNovo}">checked="true"</c:if> value="<mtw:out value="domingo"/>" /> <mtw:label klass="obrig" value="Domingo"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="domingo1Novo" id="domingo1Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="domingo2Novo" id="domingo2Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="domingo3Novo" id="domingo3Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="domingo4Novo" id="domingo4Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="domingo5Novo" id="domingo5Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="domingo6Novo" id="domingo6Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="domingo7Novo" id="domingo7Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="domingo8Novo" id="domingo8Novo" size="5" extra="title=Saída!"/>
        </td>
    </tr>
    <tr>
        <td class="chek">
            <input type="checkbox" name="segundaNovo" id="segundaNovo" onclick="verificaTodosNovo('Novo')" <c:if test="${segundaNovo}">checked="true"</c:if> value="0" /> <mtw:label klass="obrig" value="Segunda"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="segunda1Novo" id="segunda1Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="segunda2Novo" id="segunda2Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="segunda3Novo" id="segunda3Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="segunda4Novo" id="segunda4Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="segunda5Novo" id="segunda5Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="segunda6Novo" id="segunda6Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="segunda7Novo" id="segunda7Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="segunda8Novo" id="segunda8Novo" size="5" extra="title=Saída!"/>
        </td>
    </tr>
    <tr>
        <td class="chek">
            <input type="checkbox" name="tercaNovo" id="tercaNovo" onclick="verificaTodosNovo('Novo')" <c:if test="${tercaNovo}">checked="true"</c:if> id="tercaNovo" VALUE="0"/> <mtw:label klass="obrig" value="Terça"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="terca1Novo" id="terca1Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="terca2Novo" id="terca2Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="terca3Novo" id="terca3Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="terca4Novo" id="terca4Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="terca5Novo" id="terca5Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="terca6Novo" id="terca6Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="terca7Novo" id="terca7Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="terca8Novo" id="terca8Novo" size="5" extra="title=Saída!"/>
        </td>
    </tr>
    <tr>
        <td class="chek">
            <input TYPE="checkbox" name="quartaNovo" id="quartaNovo" onclick="verificaTodosNovo('Novo')" <c:if test="${quartaNovo}">checked="true"</c:if>  id="quartaNovo" VALUE="0"/> <mtw:label klass="obrig" value="Quarta"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="quarta1Novo" id="quarta1Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="quarta2Novo" id="quarta2Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="quarta3Novo" id="quarta3Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="quarta4Novo" id="quarta4Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="quarta5Novo" id="quarta5Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="quarta6Novo" id="quarta6Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="quarta7Novo" id="quarta7Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="quarta8Novo" id="quarta8Novo" size="5" extra="title=Saída!"/>
        </td>
    </tr>
    <tr>
        <td class="chek">
            <input type="checkbox" name="quintaNovo"  id="quintaNovo" onclick="verificaTodosNovo('Novo')" <c:if test="${quintaNovo}">checked="true"</c:if>  value="0"/> <mtw:label klass="obrig" value="Quinta"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="quinta1Novo" id="quinta1Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="quinta2Novo" id="quinta2Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="quinta3Novo" id="quinta3Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="quinta4Novo" id="quinta4Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="quinta5Novo" id="quinta5Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="quinta6Novo" id="quinta6Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="quinta7Novo" id="quinta7Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="quinta8Novo" id="quinta8Novo" size="5" extra="title=Saída!"/>
        </td>
    </tr>
    <tr>
        <td class="chek">
            <input type="checkbox" name="sextaNovo"  id="sextaNovo" onclick="verificaTodosNovo('Novo')" <c:if test="${sextaNovo}">checked="true"</c:if>  value="0"/> <mtw:label klass="obrig" value="Sexta"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="sexta1Novo" id="sexta1Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="sexta2Novo" id="sexta2Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="sexta3Novo" id="sexta3Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="sexta4Novo" id="sexta4Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="sexta5Novo" id="sexta5Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="sexta6Novo" id="sexta6Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="sexta7Novo" id="sexta7Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="sexta8Novo" id="sexta8Novo" size="5" extra="title=Saída!"/>
        </td>
    </tr>
    <tr>
        <td class="chek">
            <input type="checkbox" name="sabadoNovo" id="sabadoNovo" onclick="verificaTodosNovo('Novo')"  <c:if test="${sabadoNovo}">checked="true"</c:if>  id="sabadoNovo" value="0"/> <mtw:label klass="obrig" value="Sábado"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="sabado1Novo" id="sabado1Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="sabado2Novo" id="sabado2Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="sabado3Novo" id="sabado3Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="sabado4Novo" id="sabado4Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="sabado5Novo" id="sabado5Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="sabado6Novo" id="sabado6Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="sabado7Novo" id="sabado7Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="sabado8Novo" id="sabado8Novo" size="5" extra="title=Saída!"/>
        </td>
    </tr>
    <tr>
        <td class="chek">
            <input type="checkbox" name="feriadoNovo" id="feriadoNovo" onclick="verificaTodosNovo('Novo')"  <c:if test="${feriadoNovo}">checked="true"</c:if>  id="feriadoNovo" value="0"/> <mtw:label klass="obrig" value="Feriado"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="feriado1Novo" id="feriado1Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="feriado2Novo" id="feriado2Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="feriado3Novo" id="feriado3Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="feriado4Novo" id="feriado4Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="feriado5Novo" id="feriado5Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="feriado6Novo" id="feriado6Novo" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="feriado7Novo" id="feriado7Novo" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="feriado8Novo" id="feriado8Novo" size="5" extra="title=Saída!"/>
        </td>
    </tr>
</table>