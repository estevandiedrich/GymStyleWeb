<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <table width="100%" >
        <tr>
            <td align="left" width="10%" class="one">
                <mtw:label klass="obrig" value="Início:" />
            </td>
            <td align="left" width="30%">
                <mtw:input klass="inputNumber" name="criterioInicio" id="criterioInicio" extra="onkeyup=submitFormRead()" />
            </td>
            <td align="left" width="10%" >
                <mtw:label klass="obrig" value="Descrição:" />
            </td>
            <td align="left" width="10%">
                <mtw:input type="text" klass="input" name="criterioDescricao" id="criterioDescricao" extra="onkeyup=submitFormRead()" maxlength="70" />
            </td>
            <td align="left"width="5%"></td>
            <td align="left"width="10%">
                <mtw:buttonAction id="filtrar" value="Filtrar" name="filtrar" onclick="submitFormRead()"/>
            </td>
        </tr>
        <tr>
            <td align="left" width="10%" class="one">
                <mtw:label klass="obrig" value="Término:" />
            </td>
            <td align="left" width="30%">
                <mtw:input klass="inputNumber" name="criterioFim" id="criterioFim" extra="onkeyup=submitFormRead()" />
            </td>
            <td align="left" width="10%" >
                <mtw:label klass="obrig" value="Usuário:" />
            </td>
            <td align="left" width="10%">
                <mtw:select klass="selectOptions" list="usuarios" name="criterioUsuario"  id="criterioUsuario" emptyField="true" 
                            emptyFieldValue="Selecione..." extra="onchange=submitFormRead()" />
            </td>
            <td align="left" width="5%" >
                <mtw:label klass="obrig" value="Tipo:" />
            </td>
            <td align="left" width="10%">
                <mtw:select klass="selectOptions" list="tipos" name="criterioTipo" id="criterioTipo" emptyField="true" style="max-width:120px" 
                            emptyFieldValue="Selecione..." extra="onchange=submitFormRead()" />
            </td>
            <td align="left"width="20%"></td>
        </tr>
    </table>
</div>
