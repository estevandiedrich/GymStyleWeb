<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="js/fluxocaixa/fechar.js"></script>

<h1>Fechar Caixa</h1>
<div style="width: 90%;" class="faixasForm">
    <mtw:bean value="pojo">
        <table width="100%" class="displaytag">
            <tr>
                <th colspan="2">Inicial</th>
                <th colspan="2">Entrada</th>
                <th colspan="2">Saída</th>
                <th colspan="2">Saldo</th>
            </tr>
            <tr>
                <td class="sub4" width="12%" ><label class="obrig">Dinheiro:</label></td>
                <td class="odd" width="12%"><mtw:out value="viDinheiro" formatter="realFormatter"/></td>
                <td class="sub4" width="12%" ><label class="obrig">Dinheiro:</label></td>
                <td class="odd oks" width="12%"><mtw:out value="dinheiroEntrada" formatter="realFormatter"/></td>
                <td class="sub4" width="12%" ><label class="obrig">Dinheiro:</label></td>
                <td class="odd error" width="12%"><mtw:out value="dinheiroRetirada" formatter="realFormatter"/></td>
                <td class="sub4" width="12%" ><label class="obrig">Dinheiro:</label></td>
                <td class="odd" width="12%"><mtw:out value="dinheiroFechar" formatter="realFormatter"/></td>
            </tr>
            <tr>
                <td class="even" width="12%" ><label class="obrig">Cheque:</label></td>
                <td class="odd" width="12%"><mtw:out value="viCheque" formatter="realFormatter"/></td>
                <td class="even" width="12%" ><label class="obrig">Cheque:</label></td>
                <td class="odd oks" width="12%"><mtw:out value="chequeEntrada" formatter="realFormatter"/></td>
                <td class="even" width="12%" ><label class="obrig">Cheque:</label></td>
                <td class="odd error" width="12%"><mtw:out value="chequeRetirada" formatter="realFormatter"/></td>
                <td class="even" width="12%" ><label class="obrig">Cheque:</label></td>
                <td class="odd" width="12%"><mtw:out value="chequeFechar" formatter="realFormatter"/></td>
            </tr>
            <tr>
                <td class="sub" width="12%" ><label class="obrig">Boleto:</label></td>
                <td class="odd" width="12%"><mtw:out value="viBoleto" formatter="realFormatter"/></td>
                <td class="sub" width="12%" ><label class="obrig">Boleto:</label></td>
                <td class="odd oks" width="12%"><mtw:out value="boletoEntrada" formatter="realFormatter"/></td>
                <td class="sub" width="12%" ><label class="obrig">Boleto:</label></td>
                <td class="odd error" width="12%"><mtw:out value="boletoRetirada" formatter="realFormatter"/></td>
                <td class="sub" width="12%" ><label class="obrig">Boleto:</label></td>
                <td class="odd" width="12%"><mtw:out value="boletoFechar" formatter="realFormatter"/></td>
            </tr>
            <tr>
                <td class="sub2" width="12%" ><label class="obrig">Cartão:</label></td>
                <td class="odd" width="12%"><mtw:out value="viCartao" formatter="realFormatter"/></td>
                <td class="sub2" width="12%" ><label class="obrig">Cartão:</label></td>
                <td class="odd oks" width="12%"><mtw:out value="cartaoEntrada" formatter="realFormatter"/></td>
                <td class="sub2" width="12%" ><label class="obrig">Cartão:</label></td>
                <td class="odd error" width="12%"><mtw:out value="cartaoRetirada" formatter="realFormatter"/></td>
                <td class="sub2" width="12%" ><label class="obrig">Cartão:</label></td>
                <td class="odd" width="12%"><mtw:out value="cartaoFechar" formatter="realFormatter"/></td>
            </tr>
            <tr>
                <td class="sub3" width="12%" ><label class="obrig">Depósito:</label></td>
                <td class="odd" width="12%"><mtw:out value="viDeposito" formatter="realFormatter"/></td>
                <td class="sub3" width="12%" ><label class="obrig">Depósito:</label></td>
                <td class="odd oks" width="12%"><mtw:out value="depositoEntrada" formatter="realFormatter"/></td>
                <td class="sub3" width="12%" ><label class="obrig">Depósito:</label></td>
                <td class="odd error" width="12%"><mtw:out value="depositoRetirada" formatter="realFormatter"/></td>
                <td class="sub3" width="12%" ><label class="obrig">Depósito:</label></td>
                <td class="odd" width="12%"><mtw:out value="depositoFechar" formatter="realFormatter"/></td>
            </tr>
            <tfoot>
            <th class="sub" align="center"><label class="obrig">Total:</label></th>
            <th class="odd one" ><input type="hidden" id="valorInicialFechar" name="valorInicialFechar" value="<mtw:out value="valorInicial" />" /><mtw:out value="valorInicial" formatter="realFormatter"/></th>
            <th class="even" align="center"><label class="obrig" style="font-size: 18px">+</label></th>
            <th class="odd" align="center">
                <input type="hidden" id="valorEntradaFechar" name="valorEntradaFechar" value="<mtw:out value="valorEntrada" />" />
            <font class="oks"><mtw:out value="valorEntrada" formatter="realFormatter"/></font>
            </th>
            <th class="even" align="center"><label class="obrig"  style="font-size: 18px">-</label></th>
            <th class="odd" align="center" >
                <input type="hidden" id="valorRetiradaFechar" name="valorRetiradaFechar" value="<mtw:out value="valorRetirada" />" />
            <font class="error"><mtw:out value="valorRetirada" formatter="realFormatter"/></font>
            </th>
            <th class="even" align="center"><label class="obrig"  style="font-size: 18px">=</label></th>
            <th class="odd one"><input type="hidden" id="valorFinalFechar" name="valorFinalFechar" value="<mtw:out value="valorFinal" />" /><label class="obrig"><mtw:out value="valorFinal" formatter="realFormatter"/></label></th>
            </tfoot>
        </table>

        <table width="100%" class="displaytag">
            <tr class="even">
                <td width="100%" class="panelButtonForm">
                    <input type="button" class="botao" onclick="closeShowFecharCaixa()" value="Cancelar"/>
                    <input type="button" class="botao" onclick="confirmarFecharCaixa()" value="Confirmar"/>
                </td>
            </tr>
        </table>
    </mtw:bean>
</div>