<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/usuarioplano/cancelarPagamentos.js"></script>

<div class="faixasForm">
    <h3></h3>
    <div id="tabelaPagamentos">
        <h2>Resumo de Parcelas do Plano</h2>
        <table width="100%" class="displaytag" >
            <thead><tr>
                    <th>#</th><th>Vencimento</th>
                    <th>
                        <input type="checkbox" name="todos" id="todos" title="Marcar/Desmarcar todos - Anular Parcelas!" 
                               <c:if test="${todos}">checked="true"</c:if>onclick="eventoTodosPagamentos()" />
                    </th>
                    <th>Valor Parcela</th>
                    <th>
                        <input type="checkbox" name="todosZerar" id="todosZerar"  title="Marcar/Desmarcar todos - Cancelar Valores Pagos!" 
                               <c:if test="${todosZerar}">checked="true"</c:if> onclick="eventoTodosPagos()" />
                    </th>
                    <th>Valor Pago</th><th>Pagamento</th>
                </tr>
            </thead>
            <mtw:bean value="pagamento">
                <mtw:list value="pagamentos" var="pag">
                    <tbody>
                        <mtw:isEmpty>
                            <tr class="odd">
                                <td colspan="7">
                                    Parcelas Inexistentes
                                </td>                               
                            </tr>
                        </mtw:isEmpty>
                        <mtw:loop var="pagamento" counter="i" counterStart="1" >
                            <tr class="
                                <c:choose>
                                    <c:when test="${i%2==0}">odd</c:when>
                                    <c:otherwise>even</c:otherwise>
                                </c:choose>
                                " title="A anulação de parcelas implicará no valor recebido!">
                                <td  align="center">
                                    <!--mtw:out value="numeroParcela"/-->
                                    <mtw:out value="i"/>
                                    <input type="hidden" name="idParcela${i}" id="idParcela${i}" value="<mtw:out value="pagamento.id"/>" />
                                    <input type="hidden" name="numeroParcela${i}" id="numeroParcela${i}" value="<mtw:out value="pagamento.numeroParcela"/>" />
                                </td>
                                <td align="center" >
                                    <label  name="vencimento" ><mtw:out value="vencimento.time" formatter="dateFormatter"/></label>
                                    <label> (<mtw:out value="i"/>/<mtw:out value="qtdeParcelas"/>)</label>
                                </td>
                                <td align="center">
                                    <input type="checkbox" name="parcela${i}" id="parcela${i}" onclick="eventoAnularParcela(${i})"
                                           <mtw:if test="parcelaChek${i}">checked="true"</mtw:if> title="Anular Parcela!"/>
                                </td>
                                <td align="center" width="20%">
                                    <div id="valorPar${i}"><mtw:out value="valor" formatter="realFormatter"/></div>
                                    <input type="hidden" name="valorParcela${i}" id="valorParcela${i}" value="<mtw:out value="valor"/>" />
                                    <input type="hidden" name="valorParcela2${i}" id="valorParcela2${i}" value="<mtw:out value="valor"/>" />
                                </td>
                                <c:if test="${pagamento.pagamento != null}" >
                                    <td align="center" width="3%">
                                        <input type="checkbox" name="zerarParcela${i}" id="zerarParcela${i}" onclick="eventoZerarParcela(${i})" 
                                               <mtw:if test="parcelaChek${i}">checked="true"</mtw:if> title="Cancelar Valor Pago!"/>
                                    </td>
                                    <td align="center" width="20%">
                                        <div id="valorPag${i}">
                                            <mtw:if test="pagamento.pagamento" value="null" negate="true">
                                                <mtw:out value="valorPago" formatter="realFormatter" />
                                            </mtw:if>
                                        </div>
                                        <input type="hidden" name="valorPago${i}" id="valorPago${i}" value="<mtw:out value="valorPago"/>" />
                                        <input type="hidden" name="valorPago2${i}" id="valorPago2${i}" value="<mtw:out value="valorPago"/>" />
                                    </td>
                                </c:if>
                                <c:if test="${pagamento.pagamento == null}" >
                                    <td align="center" width="23%" colspan="2"></td>
                                </c:if>

                                <td align="center" width="20%">
                                    <div id="pagamento${i}"><mtw:out value="pagamento.pagamento.time" formatter="dateFormatter"/></div>
                                    <input type="hidden" name="pagamento2${i}" id="pagamento2${i}" 
                                           value="<mtw:out value="pagamento.pagamento.time" formatter="dateFormatter"/>" />
                                </td>
                            </tr>
                        </mtw:loop>            
                    </mtw:list>
                    <tr>
                        <th colspan="3">Resumo Final</th>
                        <th><label title="Valor total de todas as parcelas!">Total (Parcela)</label></th>
                        <th  colspan="2" ><label title="Valor total pago!">Total (Pagos)</label></th>
                        <th><label title="Resumo do valor a ser recebido de parcelas pendentes!">Valor Á Receber</label></th>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <input type="hidden" id="pagamentoSize"  name="pagamentoSize" value="<mtw:out value="pagamentos.size"/>"/>
                        </td>
                        <td style="background: #FFF"><div id="valorPagamento"><mtw:out value="valorPagamento" formatter="realFormatter"/></div></td>
                        <td style="background: #FFF"  colspan="2"><div id="valorPagoPagamento" 
                                                                       <c:if test="${valorPagamento > valorPagoPagamento}">class="error"</c:if>
                                                                       <c:if test="${valorPagamento < valorPagoPagamento}" >style="color: green"</c:if>
                                                                       <c:if test="${valorPagamento == valorPagoPagamento}" >style="color: black"</c:if>
                                                                       ><mtw:out value="valorPagoPagamento" formatter="realFormatter"/></div></td>
                        <td style="background: #FFF">
                            <mtw:input id="valorTotalPagamento" name="valorTotalPagamento" klass="inputNumber" />
                        </td>
                    </tr>
                </mtw:bean>
            </tbody>
        </table>
    </div>
</div>