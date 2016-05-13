<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/contabancaria/entrada.js"></script>

<h1>Entrada da Conta Bancária</h1>
<mtw:input name="idRegistroEntrada" id="idRegistroEntrada" type="hidden" />

<div class="faixasForm">
    <table width="100%" class="displaytag">
        <tr>
            <td colspan="4"><h3></h3></td>
        </tr>
        <tr>
            <td align="left" class="one"><mtw:label klass="obrig" value="Descrição:"/></td>
            <td align="left" colspan="2">
                <mtw:input klass="inputDescricao" name="descricaoEntrada" id="descricaoEntrada" maxlength="70" value="ENTRADA CONTA BANCÁRIA"/></td>
            <td><div id="descricaoError" class="campoError" >Campo Obrigatório</div></td>
        </tr>
        <tr>
            <td align="left" class="one" style="width: 120px"  ><mtw:label klass="obrig" value="Forma de Pagamento:"/></td>
            <td align="left"><mtw:select klass="selectOptions" name="formaPagamentoEntrada" id="formaPagamentoEntrada" list="formasPagamentos" emptyField="true" defEmptyFieldValue="0" emptyFieldValue="Selecione..." defValue="1" /></td>
            <td align="left"><div id="formaPagamentoError" class="campoError" >Campo Obrigatório</div></td>
        </tr>
        <tr>
            <td align="left" class="one"><mtw:label klass="obrig" value="Valor:"/></td>
            <td align="left">
                <mtw:input klass="inputNumber" type="text" name="valorEntradaContaBancaria" id="valorEntradaContaBancaria" maxlength="13" value="0,00" />
            </td>
            <td align="left"><div id="valorError" class="campoError">Campo Obrigatório</div></td>
        </tr>
    </table>
    <br>
    <table width="100%" class="displaytag">
        <tr class="even">
            <td width="100%" class="panelButtonForm">
                <input type="button" class="botao" onclick="closeShowEntrada()" value="Cancelar"/>
                <input type="button" class="botao" onclick="cadastrarEntrada()" value="Confirmar"/>
            </td>
        </tr>
    </table>
</div>