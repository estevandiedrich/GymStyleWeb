<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <table width="98%" class="faixasForm">
        <tr><td><br></td></tr>
        <tr>
            <td class="one">
                <mtw:label klass="obrig" value="Matrícula:"/></td>
            <td>
                <mtw:inputMask klass="inputNumber" name="criterioMatricula" id="criterioMatricula" textAlign="right" extra="onkeyup=submitFormRead()" maxlength="15" />
            </td>
            <td align="left" width="10%" class="one"></td>
            <td align="left" width="10%"></td>
            <td class="one"></td>
            <td align="left"width="10%"></td>
            <td align="left"width="70%"></td>
        </tr>
        <tr>
            <td align="left" width="10%" class="one">
                <mtw:label klass="obrig" value="Nome:" />
            </td>
            <td align="left" width="10%">
                <mtw:input type="text" klass="input" name="criterioNome" id="criterioNome" extra="onkeyup=consultaNome()" maxlength="70" />
            </td>
            <td class="one">
                <mtw:label klass="obrig" value="CPF:"/></td>
            <td>
                <mtw:inputMask klass="inputNumber" name="criterioCpf" id="criterioCpf" textAlign="right" maskDefined="CPF" extra="onkeyup=submitFormRead()" />
            </td>
            <td align="left"width="10%">
                <mtw:buttonAction id="filtrar" value="Filtrar" name="filtrar" onclick="submitFormRead()"/>
            </td>
            <td align="left"width="70%">
            </td>
        </tr>
        <tr><td><br></td></tr>
    </table>
</div>
