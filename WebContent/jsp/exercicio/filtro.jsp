<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <table width="98%" class="faixasForm" >
        <tr><td><br></td></tr>
        <tr>
            <td align="left" width="10%" class="one">
                <mtw:label klass="obrig" value="Exercício:" />
            </td>
            <td align="left" width="10%">
                <mtw:input type="text" klass="input" name="criterioNome" id="criterioNome" extra="onkeyup=submitFormRead()" maxlength="70"/>
            </td>
            <td align="left" width="30%"><mtw:label klass="obrig" value="Grupo Muscular:"/></td>
            <td>
                <mtw:select klass="selectOptions" name="criterioGrupoMuscular" id="criterioGrupoMuscular" list="grupos" 
                            emptyField="true" emptyFieldValue="Selecione..." extra="onchange=submitFormRead()"/>
            </td>
            <td align="left"width="10%">
                <mtw:buttonAction id="filtrar" value="Filtrar" name="filtrar" onclick="submitFormRead()"/>
            </td>
            <td align="left"width="20%">
            </td>
        </tr>
        <tr><td><br></td></tr>
    </table>
</div>
