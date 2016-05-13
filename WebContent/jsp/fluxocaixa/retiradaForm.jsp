<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/fluxocaixa/retirada.js"></script>

<h1>Retirada do Caixa</h1>
<mtw:input name="idRegistroRetirada" id="idRegistroRetirada" type="hidden" />
<div class="faixasForm">
    <input name="chequeCaixa" id="chequeCaixa" type="hidden" value="<mtw:out value="chequeCaixa" />" />
    <input name="dinheiroCixa" id="dinheiroCaixa" type="hidden" value="<mtw:out value="dinheiroCaixa" />" />
    <input type="hidden" name="valorDinheiroRetirada" id="valorDinheiroRetirada" value="0" />
    <input type="hidden" name="valorChequeRetirada" id="valorChequeRetirada" value="0" />

    <table width="100%" class="displaytag">
        <tr>
            <td colspan="3"><h3></h3></td>
        </tr>
        <tr>
            <td align="left" class="one" width="15%" ><mtw:label klass="obrig" value="Descrição:"/></td>
            <td align="left" width="45%">
                <mtw:input klass="inputDescricao" name="descricaoRetirada" id="descricaoRetirada" maxlength="70" value="RETIRADA DE CAIXA"/></td>
            <td width="40%"><div id="descricaoRetiradaError" class="campoError" >Campo Obrigatório</div></td>
        </tr>
        <tr>
            <td align="left" class="one" style="width: 120px"  ><mtw:label klass="obrig" value="Forma de Retirada:"/></td>
            <td  align="left"><mtw:select klass="selectOptions" name="formaPagamentoRetirada" id="formaPagamentoRetirada" list="formasPagamentosRetirada" defValue="1" emptyField="true" defEmptyFieldValue="0" emptyFieldValue="Selecione..."/></td>
            <td  align="left"><div id="formaPagamentoRetiradaError" class="campoError" >Campo Obrigatório</div></td>
        </tr>
        <tr>
            <td align="left" class="one"><mtw:label klass="obrig" value="Valor:"/></td>
            <td align="left" >
                <mtw:input klass="inputNumber" type="text" name="valorRetiradaCaixa" id="valorRetiradaCaixa" maxlength="13" value="0,00"/>
            </td>
            <td align="left" ><div id="valorRetiradaError" class="campoError" >Campo Obrigatório</div></td>
        </tr>
    </table>
    <br>

    <table width="100%">
        <tr>
            <mtw:isEmpty test="contas" negate="true">            
                <td align="left" width="50%">
                    <table style="height: 90px">
                        <tr>
                            <td align="left" valign="top"  class="one" colspan="2"><a class="mais" id="linkDetalhesContaRetirada" onclick="mostraContaRetirada()" ><mtw:label klass="obrig" value="Entrada na Conta:"/></a></td>
                        </tr>
                        <tr id="detalhesContaRetirada" style="display: none;">
                            <td align="left" class="one" ><mtw:label klass="obrig" value="Conta:"/></td>
                            <td align="left" class="one" ><mtw:select klass="selectOptions" name="contaRetirada" id="contaRetirada" list="contas" emptyField="true" defEmptyFieldValue="0" emptyFieldValue="Selecione..."/></td>
                        </tr>
                    </table>
                </td>
            </mtw:isEmpty>
            <td align="left" width="50%">
                <table style="height: 90px;width: 300px" >
                    <tr>
                        <td align="left" valign="top"  class="one" colspan="2"><a class="mais" id="linkDetalhesRetiradaValorCaixa" onclick="mostraRetiradaValorCaixa()" ><mtw:label klass="obrig" value="Valor em Caixa:"/></a></td>
                    </tr>
                    <tr>
                        <td align="left" class="one detalhesRetiradaValorCaixa odd" ><mtw:label klass="obrig" value="Dinheiro:"/></td>
                        <td align="left" class="one detalhesRetiradaValorCaixa" >R$ <mtw:out value="dinheiroCaixa" formatter="realFormatter"/></td>
                    </tr>
                    <tr>
                        <td align="left" class="one detalhesRetiradaValorCaixa" ><mtw:label klass="obrig" value="Cheque:"/></td>
                        <td align="left" class="one detalhesRetiradaValorCaixa" >R$ <mtw:out value="chequeCaixa" formatter="realFormatter"/></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>

    <table width="100%" class="displaytag">
        <tr class="even">
            <td width="100%" class="panelButtonForm">
                <input type="button" class="botao" onclick="closeShowRetirada()" value="Cancelar"/>
                <input type="button" class="botao" onclick="cadastrarRetirada()" value="Confirmar"/>
            </td>
        </tr>
    </table>
</div>