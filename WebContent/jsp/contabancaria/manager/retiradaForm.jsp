<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/contabancaria/retirada.js"></script>

<h1>Retirada da Conta Bancária</h1>
<mtw:input name="idRegistroRetirada" id="idRegistroRetirada" type="hidden" />
<mtw:input name="idRegistroCaixa" id="idRegistroCaixa" type="hidden" value="0" />
<div class="faixasForm">
    <input name="chequeContaBancaria" id="chequeContaBancaria" type="hidden" value="<mtw:out value="chequeContaBancaria" />" />
    <input name="dinheiroContaBancaria" id="dinheiroContaBancaria" type="hidden" value="<mtw:out value="dinheiroContaBancaria" />" />
    <input type="hidden" name="valorDinheiroRetirada" id="valorDinheiroRetirada" value="0" />
    <input type="hidden" name="valorChequeRetirada" id="valorChequeRetirada" value="0" />

    <table width="100%" class="displaytag">
        <tr><td colspan="3"><h3></h3></td></tr>
        <tr>
            <td align="left" class="one" width="15%" ><mtw:label klass="obrig" value="Descrição:"/></td>
            <td align="left" width="45%">
                <mtw:input klass="inputDescricao" name="descricaoRetirada" id="descricaoRetirada" maxlength="70" value="RETIRADA CONTA BANCÁRIA"/>
            </td>
            <td width="40%"><div id="descricaoRetiradaError" class="campoError" >Campo Obrigatório</div></td>
        </tr>
        <tr>
            <td align="left" class="one" style="width: 120px"  ><mtw:label klass="obrig" value="Forma de Retirada:"/></td>
            <td  align="left"><mtw:select klass="selectOptions" name="formaPagamentoRetirada" id="formaPagamentoRetirada" list="formasPagamentos" defValue="1" emptyField="true" defEmptyFieldValue="0" emptyFieldValue="Selecione..."/></td>
            <td  align="left"><div id="formaPagamentoRetiradaError" class="campoError" >Campo Obrigatório</div></td>
        </tr>
        <tr>
            <td align="left" class="one"><mtw:label klass="obrig" value="Valor:"/></td>
            <td align="left" >
                <mtw:input klass="inputNumber" type="text" name="valorRetiradaContaBancaria" id="valorRetiradaContaBancaria" maxlength="13" value="0,00"/>
            </td>
            <td align="left" ><div id="valorRetiradaError" class="campoError" >Campo Obrigatório</div></td>
        </tr>
    </table>
    <c:if test="${idCaixaAberto==0}" >

        <div class="faixasForm" style="width: 250px;margin-left: 26px">
            <div class="errors" style="width: 80px;height: 20px;margin-left: 16px;cursor: pointer" 
                 title="Click para abrir o fluxo de caixa!" onclick="location.href='managerFluxoCaixa.do'">Caixa Fechado <img src="images/alert.png"></div>
            </c:if>
        <table class="faixasForm displaytag" style="width: 230px;">
            <tr><th align="center" colspan="2"><mtw:label klass="obrig" value="Entrada no fluxo de caixa:"/></th></tr>
            <tr>
                <td align="center" class="one odd" width="50%">
                    <input type="radio" id="criterioSim" name="criterio" title="Sim!" value="1" <c:if test="${idCaixaAberto==0}" >disabled="disabled"</c:if> />Sim
                </td>
                <td align="center" class="sub2" width="50%">
                    <input type="radio" id="criterioNao" name="criterio" title="Não!" value="0" checked="cheked" <c:if test="${idCaixaAberto==0}" >disabled="disabled"</c:if> />Não
                </td>
            </tr>
        </table>
        <c:if test="${idCaixaAberto==0}" >
        </div>
    </c:if>
    <table width="100%" class="displaytag">
        <tr class="even">
            <td width="100%" class="panelButtonForm">
                <input type="button" class="botao" onclick="closeShowRetirada()" value="Cancelar"/>
                <input type="button" class="botao" onclick="cadastrarRetirada()" value="Confirmar"/>
            </td>
        </tr>
    </table>
</div>