<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/pagamento/filtro.js"></script>
<div>
    <table width="98%" class="faixasForm">
        <tr><td><br></td></tr>
        <tr>
            <td class="one"><mtw:label klass="obrig" value="Matrícula:"/></td>
            <td>
                <mtw:input klass="inputNumber" name="criterioMatricula" id="criterioMatricula" extra="onkeyup=submitFormRead()" maxlength="15" />
            </td>
            <!--td class="one"><!--mtw:label klass="obrig" value="Alunos:"/></td-->
            <td align="left" width="10%" class="one" colspan="4">
                <div style="alignment-adjust:middle ">
                    <mtw:input type="hidden" name="criterioDebitoDia" id="criterioDebitoDia" value="-1"  />
                    <input type="radio" id="criterioTodos" name="criterio" title="Todos!" value="todos"
                           checked="" onclick="eventoSelectRadioCriterio(-1)"/>Todos

                    <input type="radio" id="criterioDebito" name="criterio" title="Alunos Em-Débito!" value="emDebito"
                           <mtw:if test="criterio" value="emDebito">checked=""</mtw:if> onclick="eventoSelectRadioCriterio(0)"/>Em-Débito
                    <input type="radio" id="criterioEmDia" name="criterio" title="Alunos Em-Dia!" value="emDia" 
                           <mtw:if test="criterio" value="emDia">checked=""</mtw:if>  onclick="eventoSelectRadioCriterio(1)"/>Em-Dia
                </div>
            </td>
            <td align="left" width="10%"></td>
            <td class="one"></td>
            <td align="left"width="10%"></td>
            <td align="left"width="70%"></td>
        </tr>
        <tr>
            <td align="left" width="10%" class="one">
                <mtw:label klass="obrig" value="Aluno:" />
            </td>
            <td align="left" width="10%">
                <mtw:input type="text" klass="input" name="criterioNome" id="criterioNome" extra="onkeyup=consultaNome()" maxlength="70" />
            </td>
            <td class="one">
                <mtw:label klass="obrig" value="CPF:"/></td>
            <td>
                <mtw:input klass="inputNumber" name="criterioCpf" id="criterioCpf" extra="onkeyup=submitFormRead()" />
            </td>
            <td align="left"width="10%">
                <mtw:buttonAction id="filtrar" value="Filtrar" name="filtrar" onclick="submitFormRead()"/>
            </td>
            <td align="left"width="70%"></td>
        </tr>
        <tr><td><br></td></tr>
    </table>
</div>
