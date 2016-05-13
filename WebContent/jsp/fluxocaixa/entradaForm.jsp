<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/fluxocaixa/entrada.js"></script>

<h1>Entrada do Caixa</h1>
<mtw:input name="idRegistroEntrada" id="idRegistroEntrada" type="hidden" />

<div class="faixasForm" width="100%">
    <table width="100%" class="displaytag">
        <tr>
            <td colspan="3"><h3></h3></td>
        </tr>
        <tr>
            <td align="left" class="one" width="115px">
                <mtw:label klass="obrig" value="Descrição:"/>
            </td>
            <td align="left" width="400px">
                <mtw:input klass="inputDescricao" name="descricaoEntrada" id="descricaoEntrada" maxlength="70" value="ENTRADA DE CAIXA"/></td>
            <td width="230px">
                <div id="descricaoError" class="campoError" >Campo Obrigatório</div>
            </td>
        </tr>
        <tr>
            <td align="left" class="one" ><mtw:label klass="obrig" value="Forma de Pagamento:"/></td>
            <td align="left"><mtw:select klass="selectOptions" name="formaPagamentoEntrada" id="formaPagamentoEntrada" list="formasPagamentosEntrada" emptyField="true" defEmptyFieldValue="0" emptyFieldValue="Selecione..." defValue="1" /></td>
            <td align="left"><div id="formaPagamentoError" class="campoError" >Campo Obrigatório</div></td>
        </tr>
        <tr>
            <td align="left" class="one"><mtw:label klass="obrig" value="Valor:"/></td>
            <td align="left">
                <mtw:input klass="inputNumber" type="text" name="valorEntradaCaixa" id="valorEntradaCaixa" maxlength="13" value="0,00" />
            </td>
            <td align="left"><div id="valorError" class="campoError">Campo Obrigatório</div></td>
        </tr>
    </table>
    <br>
    <mtw:isEmpty test="contas" negate="true">
        <table style="height: 60px">
            <tr>
                <td align="left" valign="top"  class="one" colspan="2"><a class="mais" id="linkDetalhesContaEntrada" onclick="mostraContaEntrada()" ><mtw:label klass="obrig" value="Retirado da Conta:"/></a></td>
            </tr>
            <tr id="detalhesContaEntrada" style="display: none;">
                <td align="left" class="one" ><mtw:label klass="obrig" value="Conta:"/></td>
                <td align="left" class="one" ><mtw:select klass="selectOptions" name="contaEntrada" id="contaEntrada" list="contas" emptyField="true" defEmptyFieldValue="0" emptyFieldValue="Selecione..."/></td>
            </tr>
        </table>
    </mtw:isEmpty>
    <table width="100%" class="displaytag">
        <tr class="even">
            <td width="100%" class="panelButtonForm">
                <input type="button" class="botao" onclick="closeShowEntrada()" value="Cancelar"/>
                <input type="button" class="botao" onclick="cadastrarEntrada()" value="Confirmar"/>
            </td>
        </tr>
    </table>
</div>