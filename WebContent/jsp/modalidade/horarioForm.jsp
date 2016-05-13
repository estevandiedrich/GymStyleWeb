<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/modalidade/createHorario.js"></script>

<table width="98%" align="center" class="faixasForm">
    <tr>
        <td class="one">
            <mtw:label klass="obrig" value="Identificação:"/>
        </td>
        <td width="25%">
            <mtw:input type="hidden" name="idHorario" id="idHorario" />
            <mtw:input klass="input" type="text" name="identificacao" maxlength="49"/>
        </td>
        <td width="50%">
            <font class="errors"><mtw:outError field="identificacao" ><mtw:out/></mtw:outError></font>
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
                        <td><mtw:input klass="inputTime" name="entrada1" id="entrada1" size="5"/></td>
                    </tr>
                    <tr>
                        <td class="one"></td>
                        <td><mtw:label klass="obrig" value="Saída:"/></td>
                        <td><mtw:input klass="inputTime" name="saida2" id="saida2" size="5"/></td>
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
                        <td><mtw:input klass="inputTime" name="entrada3" id="entrada3" size="5"/></td>
                    </tr>
                    <tr>
                        <td class="one"></td>
                        <td><mtw:label klass="obrig" value="Saída:"/></td>
                        <td><mtw:input klass="inputTime" name="saida4" id="saida4" size="5"/></td>
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
                        <td><mtw:input klass="inputTime" name="entrada5" id="entrada5" size="5"/></td>
                    </tr>
                    <tr>
                        <td class="one"></td>
                        <td><mtw:label klass="obrig" value="Saída:"/></td>
                        <td><mtw:input klass="inputTime" name="saida6" id="saida6" size="5"/></td>
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
                        <td><mtw:input klass="inputTime" name="entrada7" id="entrada7" size="5"/></td>
                    </tr>
                    <tr>
                        <td class="one"></td>
                        <td><mtw:label klass="obrig" value="Saída:"/></td>
                        <td><mtw:input klass="inputTime" name="saida8" id="saida8" size="5"/></td>
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
            <mtw:buttonAction klass="aplicar" name="aplicar" value="Aplicar" onclick="eventoAplicarFaixas()"/>
        </td>
    </tr>
</table>
<mtw:input type="hidden" name="horarioError"/>
<font class="errors"><mtw:outError field="horarioError" ><mtw:out/></mtw:outError></font>
<h1>Dias da Semana</h1>
<table width="95%" align="center" class="faixasForm" onloadstart="verificarCheckDiasSemana()">
    <tr>
        <td class="chek">
            <input type="checkbox" name="todos" id="todos" <c:if test="${todos}">checked="true"</c:if> onclick="eventoTodosAplicarFaixas()" /> <mtw:label klass="obrig" value="Todos"/>
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
            <input type="checkbox" name="domingo" id="domingo" onclick="verificaTodosNovo('')" <c:if test="${domingo}">checked="true"</c:if> value="<mtw:out value="domingo"/>" /> <mtw:label klass="obrig" value="Domingo"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="domingo1" id="domingo1" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="domingo2" id="domingo2" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="domingo3" id="domingo3" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="domingo4" id="domingo4" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="domingo5" id="domingo5" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="domingo6" id="domingo6" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="domingo7" id="domingo7" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="domingo8" id="domingo8" size="5" extra="title=Saída!"/>
        </td>
    </tr>
    <tr>
        <td class="chek">
            <input type="checkbox" name="segunda" id="segunda" onclick="verificaTodosNovo('')" <c:if test="${segunda}">checked="true"</c:if> value="0"/> <mtw:label klass="obrig" value="Segunda"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="segunda1" id="segunda1" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="segunda2" id="segunda2" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="segunda3" id="segunda3" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="segunda4" id="segunda4" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="segunda5" id="segunda5" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="segunda6" id="segunda6" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="segunda7" id="segunda7" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="segunda8" id="segunda8" size="5" extra="title=Saída!"/>
        </td>
    </tr>
    <tr>
        <td class="chek">
            <input type="checkbox" name="terca" id="terca" onclick="verificaTodosNovo('')" <c:if test="${terca}">checked="true"</c:if> id="terca" VALUE="0"/> <mtw:label klass="obrig" value="Terça"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="terca1" id="terca1" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="terca2" id="terca2" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="terca3" id="terca3" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="terca4" id="terca4" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="terca5" id="terca5" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="terca6" id="terca6" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="terca7" id="terca7" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="terca8" id="terca8" size="5" extra="title=Saída!"/>
        </td>
    </tr>
    <tr>
        <td class="chek">
            <input TYPE="checkbox" name="quarta" id="quarta" onclick="verificaTodosNovo('')"  <c:if test="${quarta}">checked="true"</c:if>  id="quarta" VALUE="0"/> <mtw:label klass="obrig" value="Quarta"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="quarta1" id="quarta1" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="quarta2" id="quarta2" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="quarta3" id="quarta3" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="quarta4" id="quarta4" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="quarta5" id="quarta5" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="quarta6" id="quarta6" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="quarta7" id="quarta7" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="quarta8" id="quarta8" size="5" extra="title=Saída!"/>
        </td>
    </tr>
    <tr>
        <td class="chek">
            <input type="checkbox" name="quinta" id="quinta" onclick="verificaTodosNovo('')" <c:if test="${quinta}">checked="true"</c:if>  value="0"/> <mtw:label klass="obrig" value="Quinta"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="quinta1" id="quinta1" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="quinta2" id="quinta2" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="quinta3" id="quinta3" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="quinta4" id="quinta4" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="quinta5" id="quinta5" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="quinta6" id="quinta6" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="quinta7" id="quinta7" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="quinta8" id="quinta8" size="5" extra="title=Saída!"/>
        </td>
    </tr>
    <tr>
        <td class="chek">
            <input type="checkbox" name="sexta" id="sexta" onclick="verificaTodosNovo('')"  <c:if test="${sexta}">checked="true"</c:if>  value="0"/> <mtw:label klass="obrig" value="Sexta"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="sexta1" id="sexta1" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="sexta2" id="sexta2" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="sexta3" id="sexta3" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="sexta4" id="sexta4" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="sexta5" id="sexta5" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="sexta6" id="sexta6" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="sexta7" id="sexta7" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="sexta8" id="sexta8" size="5" extra="title=Saída!"/>
        </td>
    </tr>
    <tr>
        <td class="chek">
            <input type="checkbox" name="sabado" id="sabado" onclick="verificaTodosNovo('')"  <c:if test="${sabado}">checked="true"</c:if>  id="sabado" value="0"/> <mtw:label klass="obrig" value="Sábado"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="sabado1" id="sabado1" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="sabado2" id="sabado2" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="sabado3" id="sabado3" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="sabado4" id="sabado4" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="sabado5" id="sabado5" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="sabado6" id="sabado6" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="sabado7" id="sabado7" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="sabado8" id="sabado8" size="5" extra="title=Saída!"/>
        </td>
    </tr>
    <tr>
        <td class="chek">
            <input type="checkbox" name="feriado" id="feriado" onclick="verificaTodosNovo('')"  <c:if test="${feriado}">checked="true"</c:if>  id="feriado" value="0"/> <mtw:label klass="obrig" value="Feriado"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="feriado1" id="feriado1" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="feriado2" id="feriado2" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="feriado3" id="feriado3" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="feriado4" id="feriado4" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="feriado5" id="feriado5" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="feriado6" id="feriado6" size="5" extra="title=Saída!"/>
        </td>
        <td>
            <mtw:input klass="inputTime" name="feriado7" id="feriado7" size="5" extra="title=Entrada!"/>
            <mtw:input klass="inputTime" name="feriado8" id="feriado8" size="5" extra="title=Saída!"/>
        </td>
    </tr>
</table>