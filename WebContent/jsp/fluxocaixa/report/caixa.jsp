<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<mtw:bean value="pojo" var="pojo" >
    <div class="faixasForm form">
        <h3>Registros</h3>
        <mtw:bean value="registros">
            <div style="max-height: 250px;overflow: auto">
                <table width="100%" class="displaytag">
                    <thead>
                        <tr>
                            <th width="3%" style="font-style: italic;font-size: 10px" title="Entrada/Retirada do caixa!">E/R</th>
                            <th width="3%" style="font-style: italic;font-size: 10px" title="Forma de Pagamento!">FP</th>
                            <th width="40%" >Descrição</th>
                            <th width="20%">Data-Hora</th>
                            <th width="3%" >-</th>
                            <th width="14%">Valor</th>
                        </tr>
                    </thead>
                    <tbody>
                        <mtw:list value="registros">
                            <mtw:isEmpty>
                                <tr class="sub">
                                    <td colspan="7">
                                        Registros não encontrados
                                    </td>
                                </tr>
                            </mtw:isEmpty>
                            <mtw:loop var="reg" counterStart="1" counter="i">
                                <tr class="
                                    <c:choose>
                                        <c:when test="${reg.id%2==0}">even</c:when>
                                        <c:otherwise>odd</c:otherwise>
                                    </c:choose>
                                    " title="Operador ${reg.usuarioRegistrou.usuario}!">
                                    <td width="4%">
                                        <mtw:if test="reg.entrada"><img src="images/up.png" title="Entrada!" style="cursor: help;"/></mtw:if>
                                        <mtw:if test="reg.entrada" negate="true" ><img src="images/down.png"  style="cursor: help;" title="Retirada!"/></mtw:if>
                                    </td>
                                    <td width="4%" ><mtw:out value="reg.formaDePagamento.image"/></td>
                                    <td align="center" ><label title="<mtw:out value="reg.descricao"/>" ><mtw:out value="reg.descricao" max="30"/></label></td>
                                    <td align="center" ><mtw:out value="reg.dataHora.time"/></td>
                                    <td align="center">
                                        <mtw:isNull test="reg.registroContaBancaria" negate="true">
                                            <mtw:if test="reg.entrada"><div class="retiradaConta" title="Retirada da Conta Bancária!"></div></mtw:if>
                                            <mtw:if test="reg.entrada" negate="true" ><div class="entradaConta" title="Entrada na Conta Bancária!"></div></mtw:if>
                                        </mtw:isNull>
                                        <mtw:isNull test="reg.parcela" negate="true">
                                            <div class="parcela" title="Parcela de Aluno!"></div>
                                        </mtw:isNull>
                                    </td>
                                    <td align="right" style="padding-right: 13px">
                                        <label <mtw:if test="entrada" negate="true">class="error"</mtw:if> <mtw:if test="entrada" negate="false">class="oks"</mtw:if> ><mtw:if test="entrada" negate="true">-</mtw:if> <mtw:out value="reg.valor" formatter="realFormatter"/></label>
                                    </td>
                                </tr>
                            </mtw:loop>
                        </mtw:list>
                    </tbody>
                </table>
            </div>
        </mtw:bean>

        <table width="100%" class="displaytag">
            <tr><th colspan="8">Resumo</th></tr>
            <tr>
                <td class="even" width="13%" ><label class="obrig" >Inicial:</label></td>
                <td class="odd"  width="12%" align="right" style="padding-right: 13px"><mtw:out value="valorInicial" formatter="realFormatter"/></td>
                <td class="sub"  width="13%" ><label class="obrig">Entradas :</label></td>
                <td class="odd"  width="12%" align="right" style="padding-right: 13px"><label class="oks"><mtw:out value="valorEntrada" formatter="realFormatter"/></label></td>
                <td class="sub2" width="13%"><label class="obrig">Retiradas :</label></td>
                <td class="odd"  width="12%" align="right" style="padding-right: 13px"><label class="error"><c:if test="${valorRetirada>0}">-</c:if> <mtw:out value="valorRetirada" formatter="realFormatter"/></label></td>
                <td class="even" width="13%" ><label class="obrig">Saldo Atual:</label></td>
                <td class="odd"  width="12%" align="right" style="padding-right: 13px"><mtw:out value="valorFinal" formatter="realFormatter"/></td>
            </tr>
            <tr>
                <td class="odd" align="left" colspan="8">
                    <table width="100%" class="displaytag" >
                        <tr>
                            <td width="50%" valign="top" >
                                <table width="100%" class="displaytag" >
                                    <tr>
                                        <th colspan="4">Caixa aberto em: <mtw:out value="abertura.time" /></th>
                                    </tr>

                                    <tr>
                                        <td class="sub2" width="22%" ><mtw:label klass="obrig" value="Dinheiro:"/></td>
                                        <td class="sub2" width="28%" align="right" style="padding-right: 13px"><mtw:out value="viDinheiro" formatter="realFormatter"/></td>
                                        <td class="even" width="22%"><mtw:label klass="obrig" value="Cheque:"/></td>
                                        <td class="even" width="28%" align="right" style="padding-right: 13px"><mtw:out value="viCheque" formatter="realFormatter"/></td>
                                    </tr>
                                    <tr class="even">
                                        <td class="sub"><mtw:label klass="obrig" value="Boleto:"/></td>
                                        <td class="sub" align="right" style="padding-right: 13px"><mtw:out value="viBoleto" formatter="realFormatter"/></td>
                                        <td class="sub3"><mtw:label klass="obrig" value="Cartão:"/></td>
                                        <td class="sub3" align="right" style="padding-right: 13px"><mtw:out value="viCartao" formatter="realFormatter"/></td>
                                    </tr>
                                    <tr class="odd">
                                        <td class="odd"><mtw:label klass="obrig" value="Depósito:"/></td>
                                        <td class="odd" align="right" style="padding-right: 13px" ><mtw:out value="viDeposito" formatter="realFormatter"/></td>
                                        <td class="one" colspan="2"></td>
                                    </tr>
                                </table>
                            </td>
                            <td width="50%" valign="top" >
                                <mtw:if test="fechamento" value="null" negate="true">
                                    <table width="100%" class="displaytag" >
                                        <tr><th colspan="4">Caixa Fechado em: <mtw:out value="fechamento.time" /></th></tr>
                                        <tr>
                                            <td class="sub2" width="22%" ><mtw:label klass="obrig" value="Dinheiro:"/></td>
                                            <td class="sub2" width="28%" align="right" style="padding-right: 13px"><mtw:out value="vfDinheiro" formatter="realFormatter"/></td>
                                            <td class="even" width="22%"><mtw:label klass="obrig" value="Cheque:"/></td>
                                            <td class="even" width="28%" align="right" style="padding-right: 13px"><mtw:out value="vfCheque" formatter="realFormatter"/></td>
                                        </tr>
                                        <tr class="even">
                                            <td class="sub"><mtw:label klass="obrig" value="Boleto:"/></td>
                                            <td class="sub" align="right" style="padding-right: 13px"><mtw:out value="vfBoleto" formatter="realFormatter"/></td>
                                            <td class="sub3"><mtw:label klass="obrig" value="Cartão:"/></td>
                                            <td class="sub3" align="right" style="padding-right: 13px"><mtw:out value="vfCartao" formatter="realFormatter"/></td>
                                        </tr>
                                        <tr>
                                            <td class="odd"><mtw:label klass="obrig" value="Depósito:"/></td>
                                            <td class="odd" align="right" style="padding-right: 13px"><mtw:out value="vfDeposito" formatter="realFormatter"/></td>
                                            <td align="left" class="odd" colspan="2"></td>
                                        </tr>
                                    </table>
                                </mtw:if>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </div>
</mtw:bean>