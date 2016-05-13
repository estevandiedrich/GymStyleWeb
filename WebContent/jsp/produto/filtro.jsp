<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <table width="98%" class="faixasForm" >
        <tr><td><br></td></tr>
        <tr>
            <td class="one"><mtw:label klass="obrig" value="Categoria:"/></td>
            <td><mtw:select klass="selectOptions" name="criterioCategoria" id="criterioCategoria" list="categorias" emptyField="true" emptyFieldValue="Selecione..."/></td>
            <td align="left" width="10%" class="one"><mtw:label klass="obrig" value="Status:" /></td>
            <td align="left" width="10%">
                <input type="radio" name="status" id="statusAtivo" checked="checked" value="s"/>Ativo
                <input type="radio" name="status" id="statusInativo" value="n"/>Inativo
            </td>
            <td align="left" width="10%"></td>
            <td align="left" width="10%"></td>
        </tr>
        <tr>
            <td align="left" width="10%" class="one">
                <mtw:label klass="obrig" value="Código:" />
            </td>
            <td align="left" width="10%">
                <mtw:input type="text" klass="inputNumber" name="criterioCodigo" id="criterioCodigo" extra="onkeypress=return somenteNumero(event)" maxlength="70"/>
            </td>
            <td align="left" width="10%" class="one">
                <mtw:label klass="obrig" value="Nome:" />
            </td>
            <td align="left" width="10%">
                <mtw:input type="text" klass="input" name="criterioNome" id="criterioNome" extra="onkeyup=submitFormRead()" maxlength="70"/>
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
