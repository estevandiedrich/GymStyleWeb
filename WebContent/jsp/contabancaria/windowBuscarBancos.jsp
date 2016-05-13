<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="windowBuscarBancos">
    <div class="faixasForm" id="statusBuscarBancos">
        <h1>Bancos</h1>
        <h3>Listar</h3>
        <table width="90%"  class="faixasForm">
            <tbody>
                <tr class="sub">
                    <td align="left" width="10%" class="one"><mtw:label klass="obrig" value="Código:" /></td>
                    <td align="left" width="30%">
                        <mtw:input type="text" klass="inputNumber" name="criterioCodigo" id="criterioCodigo" extra="onkeyup=consultaBancos()" maxlength="70"/>
                    </td>
                    <td align="left" width="10%" class="one"><mtw:label klass="obrig" value="Nome:" /></td>
                    <td align="left" width="30%">
                        <mtw:input type="text" klass="input" name="criterioNome" id="criterioNome" extra="onkeyup=consultaBancos()" maxlength="70"/>
                    </td>
                    <td align="left" width="10%"><mtw:buttonAction id="filtrar" value="Filtrar" name="filtrar" onclick="consultaBancos()"/></td>
                    <td align="left" width="10%"></td>
                </tr>
            </tbody>
        </table>
        <div id="bancoAjax"><%@include file="bancos.jsp" %></div>
        <table width="100%" >
            <tr>
                <td width="100%" class="panelButtonForm">
                    <input type="button" onclick="javascript:closeShowBuscarBancos()" class="botao" name="Fechar" value="Fechar"/>
                </td>
            </tr>
        </table>

    </div>
</div>