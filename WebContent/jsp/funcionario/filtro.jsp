<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <table width="98%" class="faixasForm" >
        <tr><td><br></td></tr>
            <!--td class="one">
                < mtw:label klass="obrig" value="Matrícula:"/></td>
            <td>
                < mtw:input klass="inputNumber" name="criterioMatricula" id="criterioMatricula" extra="onkeyup=submitFormRead()" maxlength="15" />
            </td-->
            
        <tr>
            <td align="left" width="10%" class="one">
                <mtw:label klass="obrig" value="Funcionário:" />
            </td>
            <td align="left" width="10%">
                <mtw:input type="text" klass="input" name="criterioNome" id="criterioNome" extra="onkeyup=consultaNome()" maxlength="70" />
            </td>
            <td>
                <mtw:label klass="obrig" value="CPF:"/></td>
            <td>
                <mtw:input klass="inputNumber" name="criterioCpf" id="criterioCpf" extra="onkeyup=submitFormRead()" />
            </td>
            <td align="left" width="10%" >
                <label class="obrig"> Inativos</label> <input type="checkbox" id="criterioAtivo" name="criterioAtivo" onclick="submitFormRead()" />
            </td>
            <td align="left"width="10%">
                <mtw:buttonAction id="filtrar" value="Filtrar" name="filtrar" onclick="submitFormRead()"/>
            </td>
            <td align="left"width="10%"></td>
        </tr>
        <tr><td><br></td></tr>
    </table>
</div>
