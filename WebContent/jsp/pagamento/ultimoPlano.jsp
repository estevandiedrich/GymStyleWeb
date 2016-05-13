<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/pagamento/ultimoPlano.js"></script>
<div style="width:100%" align="left" class="title_bottom">
    <h1>Plano</h1>
    <mtw:form action="pagamentoUltimoPlanosRead.do" name="form">
        <table class="faixasForm" width="98%">
            <tr>
                <td>
                    <div>
                        <table width="100%">
                            <tr>
                                <td width="55%">
                                    <div class="faixasForm" width="100%">
                                        <h3></h3>
                                        <table width="100%" class="displaytag">
                                            <tr>
                                                <th colspan="2">
                                                    <mtw:label klass="obrig" value="Informações:" />
                                                </th>
                                            </tr>
                                            <tr class="odd">
                                                <td class="one" style="width: 35%" align="left">
                                                    <mtw:label klass="obrig" value="Aluno:"/>
                                                    <input type="hidden" name="idUsuario" value="<mtw:out value="mapa.usuario.id"/>"/>
                                                    <input type="hidden" name="idPlanoUsuario" value="<mtw:out value="mapa.plano.idPlanoUsuario"/>"/>
                                                </td>
                                                <td style="width: 65%" class="one" align="left">
                                                    <mtw:out value="mapa.usuario.usuario"/>
                                                </td>
                                            </tr>
                                            <tr class="even">
                                                <td class="one" align="left">
                                                    <mtw:label klass="obrig" value="Matrícula:"/>
                                                </td>
                                                <td class="one" align="left">
                                                    <mtw:out value="mapa.usuario.matricula"/>
                                                </td>
                                            </tr>
                                            <tr class="odd">
                                                <td class="one" align="left">
                                                    <mtw:label klass="obrig" value="Tipo de Plano:"/>
                                                </td>
                                                <td class="one" align="left">
                                                    <mtw:out value="mapa.plano.duracaoPlano.duracao"/>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </td>
                                <td width="45%">
                                    <div class="faixasForm" width="100%" >
                                        <h3></h3>
                                        <table width="100%" class="displaytag">
                                            <tr>
                                                <th colspan="3" align="left" class="one">
                                                    <img src="images/print.png" /> <mtw:label klass="obrig" value="Imprimir Comprovante na Catraca:" />
                                                </th>
                                            </tr>
                                            <tr class="odd">
                                                <td width="33%" class="one" align="left">
                                                    <input type="radio" name="imprimir" id="imprime1" onclick="imprimirPagamento()" value="1" <c:if test="${imprimir==1}" >checked="true"</c:if> > Entrada do aluno
                                                </td>
                                            </tr>
                                            <tr class="even">
                                                <td width="34%" class="one" align="left">
                                                    <input type="radio" name="imprimir" id="imprime2" onclick="imprimirPagamento()" value="2" <c:if test="${imprimir==2}" >checked="true"</c:if> > Saída do Aluno
                                                </td>
                                            </tr>
                                            <tr class="odd">
                                                <td width="33%" class="one" align="left">
                                                    <input type="radio" name="imprimir" id="imprime3" onclick="imprimirPagamento();" value="3" <c:if test="${imprimir==3}" >checked="true"</c:if> > Não Imprimir
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="faixasForm">
                        <h3></h3>
                        <div id="tabelaPagamentos">
                            <table width="100%">
                                <tr>
                                    <td width="50%" align="left"><h2>Parcelas</h2></td>
                                    <td width="50%" align="right">
                                        <div id="msgImprimirEntradaSaida" class="errors" style="display: none">Selecione imprimir na entrada ou saída</div>
                                        <div id="msgSelecioneParcela" class="errors" style="display: none">Selecione Parcela para Imprimir</div>
                                    </td>
                                </tr>
                            </table>
                            <table width="100%" class="displaytag">
                                <thead><tr>
                                        <th width="4%">#</th>
                                        <th width="15%">Vencimento</th>
                                        <th width="11%">Fim de Acesso</th>
                                        <th width="10%">Valor Parcela</th>
                                        <th width="10%">Valor Pago</th>
                                        <th width="13%">Pagamento</th>
                                        <th width="20%">Funcionário</th>
                                        <th style="width: 4%;font-size: 9px;font-style: italic" ><img src="images/print.png" title="Habilitar impressão de comprovante na catraca!"/></th>
                                            <mtw:hasAuthorization permission="reciboReport">
                                            <th style="width: 8%;" >Recibo</th>
                                        </mtw:hasAuthorization>
                                    </tr>
                                </thead>
                                <mtw:bean value="pagamento">
                                    <mtw:list value="pagamentos" var="pag" >
                                        <tbody>
                                            <mtw:isEmpty>
                                                <tr class="even"><td colspan="9">Parcelas Inexistentes</td></tr>
                                            </mtw:isEmpty>
                                            <mtw:loop var="pagamento" counter="i" counterStart="1" >
                                                <tr class="
                                                    <c:choose>
                                                        <c:when test="${i%2==0}">odd</c:when>
                                                        <c:otherwise>even</c:otherwise>
                                                    </c:choose>
                                                    ">
                                                    <td align="center">
                                                        <mtw:out value="i"/>
                                                        <!--a class="imprimir" title="Imprimir Pagamento na Catraca?" onclick="imprimirPagamentoCatraca(<!--mtw:out value="pagamento.id" />)"></a-->
                                                        <input type="hidden" name="idParcela${i}" id="idParcela${i}" value="<mtw:out value="pagamento.id"/>" />
                                                        <input type="hidden" name="numeroParcela${i}" id="numeroParcela${i}" value="<mtw:out value="pagamento.numeroParcela"/>" />
                                                    </td>
                                                    <td align="center">
                                                        <label name="vencimento" ><mtw:out value="vencimento.time" formatter="dateFormatter"/></label>
                                                        <label> (<mtw:out value="i"/>/<mtw:out value="qtdeParcelas"/>)</label><br>
                                                    </td>
                                                    <td align="center">
                                                        <label name="fimAcesso" ><mtw:out value="fimAcesso.time" formatter="dateFormatter"/></label>
                                                    </td>
                                                    <td align="center">
                                                        <div id="valorPar${i}"><mtw:out value="valor" formatter="realFormatter"/></div>
                                                        <input type="hidden" name="valorParcela${i}" id="valorParcela${i}" value="<mtw:out value="valor"/>" />
                                                        <input type="hidden" name="valorParcela2${i}" id="valorParcela2${i}" value="<mtw:out value="valor"/>" />
                                                    </td>
                                                    <c:if test="${pagamento.pagamento != null}" >
                                                        <td align="center">
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
                                                        <td align="center"></td>
                                                    </c:if>
                                                    <td align="center">
                                                        <div id="pagamento${i}"><mtw:out value="pagamento.pagamento.time" formatter="dateFormatter"/></div>
                                                        <input type="hidden" name="pagamento2${i}" id="pagamento2${i}"
                                                               value="<mtw:out value="pagamento.pagamento.time" formatter="dateFormatter"/>" />
                                                    </td>
                                                    <td align="center">
                                                        <label title="<mtw:out value="funcionario" />!" ><mtw:out value="funcionario" max="15" /></label>
                                                    </td>
                                                    <td align="center">
                                                        <div id="divPagamento${pagamento.id}" <mtw:if test="pagamento.pagamento" value="null" >style="display: none"</mtw:if>>
                                                            <input type="checkbox" name="pagamentoChek${pagamento.id}" id="pagamentoChek${pagamento.id}" <mtw:if test="pagamento.imprimir">checked</mtw:if>
                                                                   onclick="imprimirPagamento();" title="Imprimir Pagamento na Catraca!" />
                                                            </div>
                                                        </td>
                                                    <mtw:hasAuthorization permission="reciboReport">
                                                        <td align="center">
                                                            <c:if test="${pagamento.pagamento != null}" >
                                                                <a class="pdf" href="reciboReport.do?idPagamento=${pagamento.id}&valor=${pagamento.valorPago}" target="_blank" title="Gerar recibo PDF!" style="width: 60px;padding-left: 20px" >Ver</a>
                                                            </c:if>
                                                        </td>
                                                    </mtw:hasAuthorization>
                                                </tr>
                                            </mtw:loop>
                                        </mtw:list>
                                        <tr>
                                            <th colspan="3"></th>
                                            <th>Total (Parcela)</th>
                                            <th>Total (Pagos)</th>
                                            <th colspan="4"></th>
                                        </tr>
                                        <tr>
                                            <td colspan="3"><input type="hidden" id="pagamentoSize" name="pagamentoSize" value="<mtw:out value="pagamentos.size"/>"/></td>
                                            <td style="background: #FFF"><div id="valorPagamento"><mtw:out value="valorPagamento" formatter="realFormatter"/></div></td>
                                            <td style="background: #FFF"><div id="valorPagoPagamento"
                                                                              <c:if test="${valorPagamento > valorPagoPagamento}">class="error"</c:if>
                                                                              <c:if test="${valorPagamento < valorPagoPagamento}" >style="color: green"</c:if>
                                                                              <c:if test="${valorPagamento == valorPagoPagamento}" >style="color: black"</c:if>
                                                                              ><mtw:out value="valorPagoPagamento" formatter="realFormatter"/></div></td>
                                            <td style="background: #FFF" colspan="4">
                                            </td>
                                        </tr>
                                    </mtw:bean>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="98%">
                    <div class="panelButtonForm" width="98%">
                        <mtw:buttonAction action="pagamentoUltimoPlanosRead.do" klass="botao" name="Voltar" value="Voltar"/>
                    </div>
                </td>
            </tr>
        </table>
    </mtw:form>
</div>