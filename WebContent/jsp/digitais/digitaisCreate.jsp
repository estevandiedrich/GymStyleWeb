<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table heigth="100%" width="100%">
    <tr>
        <td colspan="3">
            <h4>Cadastrar</h4>
        </td>
    </tr>
    <tr>
        <td colspan="3">
            <h3>Solicitação enviada</h3>
        </td>
    </tr>
    <tr>
        <td colspan="3" align="center">
            <mtw:radiobuttons list="opcoes" name="opcao"   extra="onchange=eventoSelectOpcao();" />
        </td>
    </tr>
    <tr>
        <td class="one" align="right"><mtw:label value="Dedo:"/></td>
        <td class="one" align="left">
            <mtw:select klass="selectOptions" id="dedo" name="dedo" list="dedos" emptyField="true" emptyFieldValue="Selecione..." extra="onchange=eventoSelectMao();"  />
        </td>
        <td>
            <mtw:buttonAction name="capturar" id="btCapturar" klass="button" value="Capturar" onclick="capturarDigital();"/>
        </td>
    </tr>
    <tr>
        <td colspan="3" align="center">
            <img id="mao" class="mao" src="images/mao.png" height="150px" width="150px" /> 
        </td>
    </tr>
    <tr>
        <td class="one" align="right"><mtw:label value="Catracas:"/></td>
        <td colspan="2" align="left">
            <mtw:radiobuttons list="dispositivos" name="dispositivo" defValue="1" />
        </td>
    </tr>
</table>
