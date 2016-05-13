<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<mtw:bean value="paginator" var="pag" >
    <table width="100%" class="displaytag">
        <thead>
            <tr>
                <th align="center" width="15%">
                    <input type='checkbox' <c:if test="${pag.orderBy}">checked="true"</c:if>  name='orderBy'
                           value='1' id="orderBy" class="orderBy" onclick="submitRelatorioFormPagamentoReportRead()" />
                    <label for="orderBy" style="background-color: #fff;" title="<mtw:out value="pag.order"/>"></label>
                    Vencimento</th>
                <th width="10%">Pagamento</th>
                <th width="12%">Valor Pago</th>
                <th width="4%" class="information" title="Forma de Pagamento!">FP</th>
                <th width="20%">Aluno</th>
                <th width="20%">Operador</th>


            </tr>
        </thead>
        <tbody>
            <mtw:list value="pag.list" >
                <mtw:isEmpty>
                    <tr class="sub">
                        <td colspan="6">
                            Dados Vazios
                        </td>
                    </tr>
                </mtw:isEmpty>
                <mtw:loop var="row" counterStart="0" counter="i">
                    <tr class="
                        <c:choose>
                            <c:when test="${i%2==0}">odd</c:when>
                            <c:otherwise>even</c:otherwise>
                        </c:choose>
                        ">
                        <td width="12%">
                            <mtw:out value="vencimento.time" formatter="dateFormatter"/>
                        </td>
                        <td width="15%">
                            <mtw:if test="realizado">
                                <mtw:out value="pagamento.time" />
                            </mtw:if>
                            <mtw:if test="realizado" negate="true">-</mtw:if>
                        </td>
                        <td width="15%">
                            <mtw:if test="realizado">
                                <mtw:out value="valorPago" formatter="realFormatter"/>
                            </mtw:if>
                            <mtw:if test="realizado" negate="true">-</mtw:if>
                        </td>
                        <td width="4%">
                            <mtw:if test="realizado">
                                <mtw:if test="pag.pojoPagamento.forma${row.id}" value="Boleto"><img src="images/boleto.png" class="information" title="<mtw:out value="pag.pojoPagamento.forma${row.id}"/>!"/></mtw:if>
                                <mtw:if test="pag.pojoPagamento.forma${row.id}" value="Dinheiro"><img src="images/money.png" class="information" title="<mtw:out value="pag.pojoPagamento.forma${row.id}"/>!"/></mtw:if>
                                <mtw:if test="pag.pojoPagamento.forma${row.id}" value="Cartão (Débito)"><img src="images/cartao.png" class="information" title="<mtw:out value="pag.pojoPagamento.forma${row.id}"/>!"/></mtw:if>
                                <mtw:if test="pag.pojoPagamento.forma${row.id}" value="Cartão (Crédito)"><img src="images/cartao.png" class="information" title="<mtw:out value="pag.pojoPagamento.forma${row.id}"/>!"/></mtw:if>
                                <mtw:if test="pag.pojoPagamento.forma${row.id}" value="Cheque"><img src="images/cheque.png" class="information" title="<mtw:out value="pag.pojoPagamento.forma${row.id}"/>!"/></mtw:if>
                            </mtw:if>
                            <mtw:if test="realizado" negate="true">-</mtw:if>
                        </td>
                        <td width="20%">
                            <label title="<mtw:out value="pag.pojoPagamento.usuario${row.id}" />"><mtw:out value="pag.pojoPagamento.usuario${row.id}" max="20"/></label>
                        </td>
                        <td width="15%">
                            <label title="<mtw:out value="funcionario" />"><mtw:out value="funcionario" max="20"/></label>
                        </td>
                    </tr>
                </mtw:loop>
                <tr>
                    <td align="right" width="100%" colspan="6">
                        <%@include file="paginacaoPagamentos.jsp" %>
                    </td>
                </tr>
            </mtw:list>
        </tbody>
    </table>
</mtw:bean>