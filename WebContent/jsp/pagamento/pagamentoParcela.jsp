<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="style/styleModal.css" type="text/css"/>
<script src="js/pagamento/parcela.js"></script>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/maskMoney.js" type="text/javascript"></script>
<!--script src="js/maskedinput-1.2.2.js" type="text/javascript"></script-->

<div id="windowConfirmPag" ><div id="statusConfirmPag"> <%@include file="confirmPagamento.jsp" %> </div></div>
<div id="windowAbrirCaixa" ><div id="statusAbrirCaixa"> <%@include file="../fluxocaixa/abrirCaixaForm.jsp" %> </div></div>

<div style="width:100%" align="left" class="title_bottom">
    <table width="100%">
        <tr>
            <td  width="70%">
                <h1>Pagar Parcela mais Recente</h1>
            </td>
            <td align="center" id="filtro" width="17%" valign="center">
                <div id="item">
                    <a id="list" href="pagamentoRead.do">Listar</a>
                </div>
            </td>
            <mtw:hasAuthorization permission="managerFluxoCaixa">
                <mtw:hasAuthorization permission="abrirFluxoCaixa">
                    <td align="center" id="filtro" width="13%">
                        <c:if test="${idFluxoCaixa==0}">
                            <div id="item"><div onclick="showAbrirCaixa()" id="abrirCaixaMsg" >Abrir Caixa</div></div>
                        </c:if>
                    </td>
                </mtw:hasAuthorization>
            </mtw:hasAuthorization>
        </tr>
    </table>
    <mtw:hasAuthorization permission="managerFluxoCaixa">
        <c:if test="${idFluxoCaixa==0}">
            <input type="hidden" value="CaixaFechado" name="fechado" id="fechado"/>
            <table width="100%">
                <tr>
                    <td width="100%" align="center">
                        <div class="msgCaixaFechado" align="center" style="font-style: italic;margin: 5px;width: 600px">
                            <font class="errors" >Caixa fechado. É necessário abrir o caixa para efetuar pagamento!</font>
                        </div>
                    </td>
                    <td align="left" id="filtro" width="0%"></td>
                </tr>
            </table>
        </c:if>
    </mtw:hasAuthorization>
    <mtw:form action="pagarParcelaPlanoUsuario.do" name="formPagarParcela">
        <table class="faixasForm" width="99%">
            <tr>
                <td style="padding-top: 10px">
                    <table width="98%" align="center" class="faixasForm">
                        <tr><td colspan="3"><h3></h3></td></tr><tr>
                            <td class="one" style="padding-bottom: 10px" width="50%">
                                <mtw:label klass="obrig" value="Aluno:"/>
                                <mtw:out value="mapa.usuario.usuario"/>
                            </td>
                            <td class="one" style="padding-bottom: 10px" width="25%">
                                <mtw:label klass="obrig" value="Matrícula:"/>
                                <mtw:out value="mapa.usuario.matricula"/>
                            </td>
                            <td class="one" style="padding-bottom: 10px" valign="left" width="25%">
                                <mtw:hasAuthorization permission="pagamentosPlanoUsuario">
                                    <a id='plano' title='Ver Plano' href='pagamentosPlanoUsuario.do?idPlanoUsuario=<mtw:out value="idPlanoUsuario"/>'>Ver Histórico de Parcelas</a>
                                </mtw:hasAuthorization>
                            </td>
                        </tr>
                    </table>
                    <mtw:input type="hidden" name="idPlanoUsuario" value="idPlanoUsuario"/>
                    <mtw:input type="hidden" name="idUsuario" value="${mapa.usuario.id}"/>
                    <mtw:input type="hidden" name="nomeUsuario" value="${mapa.usuario.usuario}"/>
                </td>
            </tr>
            <tr><td colspan="4"><%@include file="infoPlano.jsp" %></td></tr>
            <tr>
                <td>
                    <div class="faixasForm">
                        <div id="tabelaPagamentos">
                            <table width="100%" class="displaytag">
                                <mtw:bean value="pagamento" var="pagamento">
                                    <tbody>
                                        <tr>
                                            <td colspan="3" align="left">
                                                <h1>Parcela</h1>
                                                <mtw:isEmpty test="evento" negate="true">
                                                    <div class="form2" style="text-align: center;height: 60px">
                                                        <label class="error"><mtw:out value="evento" /></label>
                                                        <mtw:input name="evento" type="hidden"/>
                                                    </div>
                                                </mtw:isEmpty>
                                                <mtw:if test="tipoPlano" value="Mensal" negate="true">
                                                    <div class="faixasForm">
                                                        <table width="100%" class="displaytag">
                                                            <tbody>
                                                                <tr class="odd">
                                                                    <td align="center" width="25%">
                                                                        <mtw:label klass="obrig" value="Número da Parcela:"/>
                                                                    </td>
                                                                    <td  align="left" class="one" width="75%">
                                                                        <mtw:if test="tipoPlano" value="Mensal" negate="true"><mtw:out value="numeroParcela"/> / <mtw:out value="qtdeParcela"/></mtw:if>
                                                                        <mtw:if test="ultimaParcela" value="true">
                                                                            <mtw:if test="tipoPlano" value="Mensal" negate="true">
                                                                    <font class="errors" style="margin-left: 30px">Ultima Parcela!</font>
                                                                </mtw:if>
                                                                <mtw:input type="hidden" name="ultimaParcela" id="ultimaParcela"/>
                                                            </mtw:if>
                                                            </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </mtw:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="3">
                                                <mtw:input type="hidden" name="qtdeParcela"/>
                                                <mtw:input type="hidden" name="numeroParcela"/>
                                                <input type="hidden" name="idParcela" id="idParcela" value="<mtw:out value="pagamento.id"/>" />
                                                <div class="faixasForm">
                                                    <table width="100%" class="displaytag">
                                                        <tbody>
                                                            <tr>
                                                                <th width="25%">Valor da Parcela</th>
                                                                <th width="25%">Vencimento</th>
                                                                <th width="25%">Acesso até</th>
                                                                <th width="25%">Plano</th>
                                                            </tr>
                                                            <tr class="odd">
                                                                <td>
                                                                    <input type="hidden" name="valorParcela" id="valorParcela" value="<mtw:out value="valor"/>"/>
                                                                    <mtw:out value="valor" formatter="realFormatter"/>
                                                                </td>
                                                                <td>
                                                                    <input name="vencimento.time" type="hidden" value="<mtw:out value="vencimento.time" formatter="dateFormatter"/>" />
                                                                    <mtw:out value="vencimento.time" formatter="dateFormatter"/>
                                                                </td>
                                                                <td>
                                                                    <input name="fimAcesso.time" type="hidden" value="<mtw:out value="fimAcesso.time" formatter="dateFormatter"/>" />
                                                                    <mtw:out value="fimAcesso.time" formatter="dateFormatter"/>
                                                                </td>
                                                                <td>
                                                                    <mtw:out value="tipoPlano"/>
                                                                    <mtw:input type="hidden" name="tipoPlano" id="tipoPlano"/>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="faixasForm" style="width: 250px;height: 150px;">
                                                    <h3></h3>
                                                    <table width="100%">
                                                        <tr class="odd">
                                                            <td>
                                                                <mtw:label klass="obrig" value="Desconto:" />
                                                            </td>
                                                            <td>
                                                                <mtw:input name="desconto" id="desconto" klass="inputNumber" extra="onkeyup=eventoAjustarParcelas()" maxlength="11"/>
                                                            </td>
                                                        </tr>     
                                                        <tr class="even">
                                                            <td>
                                                                <mtw:label klass="obrig" value="Acréscimo:" />
                                                            </td>
                                                            <td>
                                                                <mtw:input name="multa" id="multa" klass="inputNumber" maxlength="11" extra="onkeyup=eventoAjustarParcelas()"  />
                                                            </td>
                                                        </tr>                                                            
                                                        <tr class="odd">
                                                            <td>
                                                                <mtw:label klass="obrig" value="Valor Total:" />
                                                            </td>
                                                            <td>
                                                                <mtw:input name="valorAPagar" id="valorAPagar" klass="inputNumber" value="0,00" maxlength="11" />
                                                            </td>
                                                        </tr>
                                                        <tr class="even">
                                                            <td colspan="2">
                                                        <font class="errors"><mtw:outError field="valor" ><mtw:out/></mtw:outError></font>
                                                        </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="faixasForm" style="width: 220px;height: 150px;">
                                                    <h3></h3>
                                                    <table width="100%">
                                                        <tr class="odd">
                                                            <td>
                                                                <label class="obrig" style="font-size:12px">Forma de Pagamento:</label>
                                                            </td>
                                                        </tr>
                                                        <tr class="even">
                                                            <td>
                                                                <mtw:select name="formaDePagamento" id="formaDePagamento" list="formaDePagamentoMap" klass="selectOptions" extra="onchange=showConta()"/>
                                                            </td>
                                                        </tr>
                                                        <tr class="odd">
                                                            <td>
                                                                <mtw:isEmpty test="contas" negate="true"><mtw:label klass="rowConta obrig" value="Colocar na Conta:"/></mtw:isEmpty>
                                                            </td>
                                                        </tr>
                                                        <tr class="even">
                                                            <td>
                                                                <mtw:isEmpty test="contas" negate="true"><mtw:select klass="selectOptions rowConta" name="conta" id="conta" list="contas" emptyField="true" defEmptyFieldValue="0" emptyFieldValue="Selecione..."/></mtw:isEmpty>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="faixasForm" style="width: 220px;height: 150px;">
                                                    <h3></h3>
                                                    <table width="100%">
                                                        <tr class="odd">
                                                            <td>
                                                                <label class="obrig" style="font-size:12px">Imprimir Comprovante na Catraca:</label>
                                                            </td>
                                                        </tr>
                                                        <tr class="even">
                                                            <td class="one" align="left">
                                                                <input type="radio" name="imprimir" id="imprime" value="1" <c:if test="${imprimir==1}" >checked="true"</c:if> onclick="mostraMsg(1)" > Entrada do aluno
                                                            </td>
                                                        </tr>
                                                        <tr class="odd">
                                                            <td class="one" align="left">
                                                                <input type="radio" name="imprimir" value="2" <c:if test="${imprimir==2}" >checked="true"</c:if>  onclick="mostraMsg(2)"> Saída do Aluno
                                                            </td>
                                                        </tr>
                                                        <tr class="even">
                                                            <td class="one" align="left">
                                                                <input type="radio" name="imprimir" value="3" <c:if test="${imprimir==3}" >checked="true"</c:if>  onclick="mostraMsg(3)"> Não Imprimir
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </td>

                                        </tr>
                                        <tr>
                                            <td colspan="3">
                                                <div class="faixasForm" style="width:97%;">
                                                    <table width="100%">
                                                        <tr class="odd">
                                                            <th>
                                                                <mtw:label klass="obrig" value="Justificativa:" />
                                                            </th>
                                                        </tr>
                                                        <tr class="odd">
                                                            <td class="one" align="center">
                                                                <mtw:textarea name="justificativa" id="justificativa" cols="80" rows="8" maxlength="254"/>
                                                            </td>
                                                        </tr>
                                                        <tr class="even">
                                                            <td class="one" align="left">
                                                                <div class="errors" id="chekJustificativaErrors" style="width: 470px;margin-left: 5px; display: none" >Preencha justificativa quando aplicar desconto ou acréscimo!</div>                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr><td></td></tr>
                                    </mtw:bean>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="100%" colspan="2">
                    <div class="panelButtonForm" >
                        <mtw:buttonAction action="pagamentoRead.do" klass="botao" name="Cancelar" value="Voltar"/>
                        <mtw:hasAuthorization permission="managerFluxoCaixa">
                            <div id="efetuarPagamento" <c:if test="${idFluxoCaixa==0}">style="display: none"</c:if> >
                            </mtw:hasAuthorization>
                            <input type="button" onclick="submitPagamento()" class="botao" value="Efetuar Pagamento"/>
                            <!--input type="button" class="botao" name="11" value="Gerar Boleto" onclick="imprimirBoleto()" /-->
                            <mtw:hasAuthorization permission="managerFluxoCaixa">
                            </div>
                        </mtw:hasAuthorization>
                        <mtw:hasAuthorization permission="pagarParcelaManager">
                            <mtw:buttonAction action="verPlanoUsuario.do" klass="botao" name="OutrosPagamentos" value="Outros Pagamentos"/>
                        </mtw:hasAuthorization>
                    </div>
                </td>
            </tr>
        </table>
    </mtw:form>
</div>

