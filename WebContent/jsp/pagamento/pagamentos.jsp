<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/pagamento/pagamentos.js"></script>
<div class="faixasForm">
    <h3></h3>
    <div id="tabelaPagamentos">
        <table width="100%">
            <tr>
                <td align="left"><h2>Parcelas</h2></td>
                <td align="right"><div class="form2 selecioneImprimir" style="width: 160px;padding: 4px"><img src="images/print.png" title="Habilitar impressão de recibo na catraca!"/> - <span class="selecioneImprimir" >Marque para Imprimir</span></div></td>
            </tr>
        </table>
        <table width="100%" class="displaytag">
            <thead>
                <tr>
                    <th width="4%" ><input type="checkbox" name="todos" title="Marcar/Desmarcar todos!" <c:if test="${todos}">checked="true"</c:if> id="todos" onclick="eventoTodosPagamentos()" /></th>
                        <th width="4%" >#</th>
                        <th width="26%" >Vencimento</th>
                        <th width="16%" >Fim de Acesso</th>
                        <th width="15%" >Valor</th>
                        <th width="15%" >Valor Pago</th>
                        <th width="16%" >Pagamento</th>
                        <th style="width: 4%;font-size: 9px;font-style: italic" ><img src="images/print.png" title="Habilitar impressão de recibo na catraca!"/></th>
                    </tr>
                </thead>
            <mtw:bean value="pagamento">
                <mtw:list value="pagamentos" var="pag">
                    <tbody>
                        <mtw:isEmpty>
                            <tr>
                                <td colspan="7">
                                    Dados não encontrados
                                </td>
                            </tr>
                        </mtw:isEmpty>
                        <mtw:loop var="pagamento" counter="i" counterStart="1" >
                            <tr class="
                                <c:choose>
                                    <c:when test="${i%2==0}">odd</c:when>
                                    <c:otherwise>even</c:otherwise>
                                </c:choose>
                                ">
                                <td align="center">
                                    <mtw:if test="pagamento.pagamento" value="null" negate="false">
                                        <input type="checkbox" name="parcela${i}" id="parcela${i}" onchange="mostrarImprimir(${pagamento.id},${i}),eventoAjustarParcelas(${i})"
                                               <mtw:if test="parcelaChek${i}">checked="true"</mtw:if> title="Adicionar ${i}°-parcela ?"/>
                                    </mtw:if>
                                </td>
                                <td  align="center">
                                    <mtw:out value="i"/>
                                    <input type="hidden" name="idParcela${i}" id="idParcela${i}" value="<mtw:out value="pagamento.id"/>" />
                                    <input type="hidden" name="numeroParcela${i}" id="numeroParcela${i}" value="<mtw:out value="pagamento.numeroParcela"/>"/>
                                </td>
                                <td  align="center">
                                    <label  name="vencimento" ><mtw:out value="vencimento.time" formatter="dateFormatter"/> - </label>
                                    <label> (<mtw:out value="i"/>/<mtw:out value="qtdeParcelas"/>)</label>
                                </td>
                                <td align="center">
                                    <mtw:out value="fimAcesso.time" formatter="dateFormatter"/>
                                </td>
                                <td align="center">
                                    <mtw:out value="valor" formatter="realFormatter"/>
                                    <input type="hidden" name="valorParcela${i}" id="valorParcela${i}" value="<mtw:out value="valor"/>" />
                                </td>
                                <td align="center">
                                    <mtw:if test="pagamento.pagamento" value="null" negate="true">
                                        <mtw:out value="valorPago" formatter="realFormatter" />
                                    </mtw:if>
                                </td>
                                <td align="center">
                                    <mtw:out value="pagamento.pagamento.time" formatter="dateFormatter"/>
                                </td>
                                <td align="center">
                                    <div id="divPagamento${pagamento.id}" <mtw:if test="pagamento.pagamento" value="null" >style="display: none"</mtw:if>>
                                        <input type="checkbox" name="pagamento${pagamento.id}" id="pagamento${pagamento.id}" <mtw:if test="pagamento.imprimir">checked</mtw:if>
                                               onclick="imprimirPagamento();" title="Imprimir Pagamento na Catraca!" />
                                        </div>
                                    </td>
                                </tr>
                        </mtw:loop>
                    </mtw:list>
                    <tr>
                        <td>
                            <input type="hidden" id="pagamentoSize" name="pagamentoSize" value="<mtw:out value="pagamentos.size"/>"/>
                        </td>
                    </tr>
                </mtw:bean>
            </tbody>
        </table>
    </div>
</div>
