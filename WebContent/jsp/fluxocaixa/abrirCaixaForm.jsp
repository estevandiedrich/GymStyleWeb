<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/fluxocaixa/abrir.js"></script>

<h1>Abertura do Caixa</h1>
<mtw:bean value="pojo" >

    <div class="faixasForm">
        <table width="100%" class="displaytag">
            <tr>
                <td align="left" class="one" ><mtw:label klass="obrig" value="Abrir o caixa da Seguinte forma:"/></td>
            </tr>
            <tr>
                <td class="one" align="left">
                    <select class="selectOptions" id="opcao" name="opcao" onchange="preencheCampos()">
                        <option value="0">Caixa Anterior</option>
                        <option value="1">Caixa Zerado</option>
                        <option value="2">Valores Informados Abaixo</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="left" class="one" ><h3> </h3></td>
            </tr>
        </table>
        <div class="one">
            <table width="100%" class="displaytag" >
                <tr><th class="one" colspan="2" style="font-style: italic"></th></tr>
                <tr class="odd">
                    <td class="one" width="40%" ><mtw:label klass="obrig" value="Dinheiro:"/></td>
                    <td class="one"  width="60%">
                        <input name="viDinheiro" id="viDinheiro" class="inputDisabledNumber" maxlength="12" 
                               value="<mtw:out value="vfDinheiro" formatter="realFormatter"/>" onkeyup="atualizaSoma()"/>
                        <input name="viDinheiro2" id="viDinheiro2" type="hidden" value="<mtw:out value="vfDinheiro" formatter="realFormatter"/>" />
                    </td>
                </tr>
                <tr class="even">
                    <td class="one"><mtw:label klass="obrig" value="Cheque:"/></td>
                    <td align="center" class="one">
                        <input name="viCheque"  id="viCheque"  class="inputDisabledNumber" maxlength="12"
                               value="<mtw:out value="vfCheque" formatter="realFormatter"/>" onkeyup="atualizaSoma()"/>
                        <input name="viCheque2" id="viCheque2" type="hidden" value="<mtw:out value="vfCheque" formatter="realFormatter" />" />
                    </td>
                </tr>
                <tr class="odd">
                    <td class="one"><mtw:label klass="obrig" value="Boleto:"/></td>
                    <td align="center" class="one">
                        <input name="viBoleto" id="viBoleto" class="inputDisabledNumber" maxlength="12"
                               value="<mtw:out value="vfBoleto" formatter="realFormatter"/>" onkeyup="atualizaSoma()"/>
                        <input name="viBoleto2" id="viBoleto2" type="hidden" value="<mtw:out value="vfBoleto" formatter="realFormatter" />" />
                    </td>
                </tr>
                <tr class="even">
                    <td class="one"><mtw:label klass="obrig" value="Cartão:"/></td>
                    <td align="center" class="one">
                        <input name="viCartao" id="viCartao" class="inputDisabledNumber" maxlength="12" 
                               value="<mtw:out value="vfCartao" formatter="realFormatter"/>" onkeyup="atualizaSoma()"/>
                        <input name="viCartao2" id="viCartao2" type="hidden" value="<mtw:out value="vfCartao" formatter="realFormatter" />" />
                    </td>
                </tr>
                <tr class="odd">
                    <td class="one"><mtw:label klass="obrig" value="Depósito:"/></td>
                    <td align="center" class="one">
                        <input name="viDeposito" id="viDeposito" class="inputDisabledNumber" maxlength="12" 
                               value="<mtw:out value="vfDeposito" formatter="realFormatter"/>" onkeyup="atualizaSoma()"/>
                        <input name="viDeposito2" id="viDeposito2" type="hidden" value="<mtw:out value="vfDeposito" formatter="realFormatter" />" />
                    </td>
                </tr>
                <tr class="even">
                    <td class="one"><mtw:label klass="obrig" value="SALDO INICIAL DO CAIXA:"/></td>
                    <td align="center" class="one">
                        <input name="valorInicialAbrir" id="valorInicialAbrir" class="inputDisabledNumber" style="font-weight: bold" maxlength="12" 
                               value="<mtw:out value="valorFinal" formatter="realFormatter"/>" />
                        <input name="valorInicial2" id="valorInicial2" type="hidden" value="<mtw:out value="valorFinal" formatter="realFormatter" />" />
                    </td>
                </tr>
            </table>
        </div>
        <table width="100%" class="displaytag">
            <tr class="even">
                <td width="100%" class="panelButtonForm">
                    <input type="button" class="botao" onclick="closeShowAbrirCaixa(false)" value="Cancelar"/>
                    <input type="button" class="botao" onclick="confirmarAbrirCaixa()" value="Confirmar"/>
                </td>
            </tr>
        </table>
    </div>
</mtw:bean>