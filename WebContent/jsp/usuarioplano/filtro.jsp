<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/usuarioplano/filtro.js"></script>

<div>
    <table width="98%" class="faixasForm" >
        <tr><td><br></td></tr>
        <tr>
            <td class="one">
                <mtw:label klass="obrig" value="Matrícula:"/></td>
            <td>
                <mtw:input klass="inputNumber" name="criterioMatricula" id="criterioMatricula" extra="onkeyup=submitFormRead()" maxlength="15" />
            </td>
            <td align="left" colspan="2">
                <mtw:input type="hidden" name="criterioValue" id="criterioValue" value="-1"  />
                <input type="radio" id="criterioPlano" name="criterio" title="Alunos com planos!" value="planos"
                       <mtw:if test="criterio" value="planos">checked=""</mtw:if> onclick="eventoSelectRadioCriterio(0)"/>Plano
                       <input type="radio" id="criterioPendencia" name="criterio" title="Alunos com pendêcias!" value="pendencias" 
                       <mtw:if test="criterio" value="pendencias">checked=""</mtw:if>  onclick="eventoSelectRadioCriterio(1)"/>Pendências
            </td>
        <tr>
        <tr>
            <td align="left" width="10%" class="one">
                <mtw:label klass="obrig" value="Aluno:" />
            </td>
            <td align="left" width="10%">
                <mtw:input type="text" klass="input" name="criterioNome"  id="criterioNome" extra="onkeyup=consultaNome()" maxlength="70"  />
            </td>
            <td class="one"><mtw:label klass="obrig" value="CPF:"/></td>
            <td>
                <mtw:input klass="inputNumber" name="criterioCpf" id="criterioCpf" extra="onkeyup=submitFormRead()" />
            </td>
            <td align="left"width="23%">
                <mtw:buttonAction id="filtrar" value="Filtrar" name="filtrar" onclick="submitFormRead()"/>
            </td>
            <td align="left"width="10%">
            </td>
        </tr>
        <tr><td><br></td></tr>
    </table>
</div>
