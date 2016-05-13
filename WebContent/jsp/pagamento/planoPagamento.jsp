<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="style/styleModal.css" type="text/css"/>
<script src="js/pagamento/planoPagamento.js"></script>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/maskMoney.js" type="text/javascript"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>


<div id="windowConfirmPag" ><div id="statusConfirmPag"> <%@include file="confirmPag.jsp" %> </div></div>
<div id="windowAbrirCaixa" ><div id="statusAbrirCaixa"> <%@include file="../fluxocaixa/abrirCaixaForm.jsp" %> </div></div>

<div style="width:100%" align="left" class="title_bottom">
    <table width="100%">
        <tr>
            <td  width="85%">
                <h1>Pagar Parcelas</h1>
            </td>
            <td align="right" id="filtro"  width="15%">
                <mtw:hasAuthorization permission="managerFluxoCaixa">
                    <mtw:hasAuthorization permission="abrirFluxoCaixa">
                        <c:if test="${idFluxoCaixa==0}">
                            <div id="item">
                                <div onclick="showAbrirCaixa()" id="abrirCaixaMsg" >Abrir Caixa</div>
                            </div>
                        </c:if>
                    </mtw:hasAuthorization>
                </mtw:hasAuthorization>
            </td>
        </tr>
    </table>
    <mtw:hasAuthorization permission="managerFluxoCaixa">
        <c:if test="${idFluxoCaixa==0}">
            <input type="hidden" value="CaixaFechado" name="fechado" id="fechado"/>
            <table width="100%">
                <tr>
                    <td width="100%" align="center">
                        <div class="msgCaixaFechado" id="msgCaixaFechado" align="center" style="font-style: italic;margin: 5px;width: 600px">
                            <font class="errors" >Caixa fechado. É necessário abrir o caixa para efetuar pagamento!</font>
                        </div>
                    </td>
                    <td align="right" id="filtro"  width="0%">
                    </td>
                </tr>
            </table>
        </c:if>
    </mtw:hasAuthorization>
    <mtw:form action="lancarParcelasPlanoUsuario.do" name="formPagarParcelas">
        <table width="99%" align="center" class="faixasForm">
            <tr>
                <td colspan="3" >
                    <div id="planoUsuarioForm">
                        <table width="97%" align="center" class="faixasForm">
                            <tr>
                                <td colspan="5" >
                                    <h3></h3>
                                </td>
                            </tr>
                            <tr>
                                <td class="one" style="width: 5%">
                                    <mtw:label klass="obrig" value="Aluno:"/>
                                    <input type="hidden" name="idUsuario" value="<mtw:out value="mapa.usuario.id"/>"/>
                                    <mtw:input type="hidden" name="nomeUsuario" value="${mapa.usuario.usuario}"/>
                                    <input type="hidden" name="idPlanoUsuario" value="<mtw:out value="mapa.plano.idPlanoUsuario"/>"/>
                                </td>
                                <td style="width: 33%">
                                    <mtw:out value="mapa.usuario.usuario"/>
                                </td>
                                <td class="one" style="width: 23%">
                                    <mtw:label klass="obrig" value="Duração do Plano:"/>
                                </td>
                                <td style="width: 13%">
                                    <mtw:out value="mapa.plano.duracaoPlano.duracao"/>
                                    <mtw:bean value="mapa.plano.duracaoPlano">
                                        <mtw:input name="duracao" id="duracao" type="hidden"/>
                                    </mtw:bean>
                                </td>
                                <td class="one" valign="right"  width="25%">
                                    <mtw:hasAuthorization permission="pagamentosPlanoUsuario" >
                                        <a id='plano' title='Ver Plano' href='pagamentosPlanoUsuario.do?idPlanoUsuario=<mtw:out value="idPlanoUsuario"/>'>Ver Histórico de Parcelas</a>
                                    </mtw:hasAuthorization>
                                </td>

                            </tr>
                            <tr>
                                <td colspan="4" >
                                    <br>
                                </td>
                            </tr>
                        </table>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="3" >
                    <div id="planoUsuarioForm">
                        <%@include file="infoPlano.jsp" %>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2"><%@include file="pagamentos.jsp" %></td>
            </tr>
            <tr>
                <td colspan="2">
                    <table width="100%">
                        <tr>
                            <td width="40%" align="left"><div class="errors" id="chekErrors" style="width: 280px;margin-left: 10px; display: none" >Selecione ao menos uma parcela</div></td>
                            <td width="60%" align="right"><div id="msgImprimirEntradaSaida" class="errors msgImprimirEntradaSaida" style="display: none">Para Imprimir comprovante, selecione entrada ou saída</div></td>
                        </tr>
                    </table>
                    <div>
                        <table width="100%" class="displaytag">
                            <tr>
                                <td>
                                    <div class="faixasForm" style="width: 250px;height: 170px;">
                                        <h3></h3>
                                        <table width="100%">
                                            <tr class="odd">
                                                <td><mtw:label klass="obrig" value="Valor:" /></td>
                                                <td align="center">
                                                    <input type="hidden" name="valorSoma"  id="valorSoma" value="0" />
                                                    <div id="valorSomado"><mtw:out value="valorSoma" formatter="realFormatter"/></div>
                                                </td>
                                            </tr>     
                                            <tr class="even">
                                                <td><mtw:label klass="obrig" value="Desconto:" /></td>
                                                <td>
                                                    <mtw:input name="desconto" id="desconto" klass="inputNumber" maxlength="8" extra="onkeyup=eventoAjustarParcelas()" />
                                                </td>
                                            </tr>     
                                            <tr class="odd">
                                                <td><mtw:label klass="obrig" value="Acréscimo:" /></td>
                                                <td>
                                                    <mtw:input name="multa" id="multa" klass="inputNumber" maxlength="8" extra="onkeyup=eventoAjustarParcelas()" />
                                                </td>
                                            </tr>                                                            
                                            <tr class="even">
                                                <td><mtw:label klass="obrig" value="Valor Total:" /></td>
                                                <td>
                                                    <mtw:input name="valorAPagar"  id="valorAPagar" klass="inputNumber" maxlength="10" value="0,00" />
                                                    <mtw:input type="hidden" value="false" name="cheks" id="cheks"/>
                                                </td>
                                            </tr>
                                            <tr class="odd">
                                                <td colspan="2">
                                            <font class="errors"><mtw:outError field="valorAPagar" ><mtw:out/></mtw:outError></font></font>
                                                </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="faixasForm" style="width: 230px;height: 170px;">
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
                                                <tr class="odd"><td class="one" align="left"></td></tr>
                                            </table>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="faixasForm" style="width: 220px;height: 170px;">
                                            <h3></h3>
                                            <table width="100%">
                                                <tr class="odd">
                                                    <td>
                                                        <label class="obrig" style="font-size:12px">Imprimir Comprovante na Catraca:</label>
                                                    </td>
                                                </tr>
                                                <tr class="even">
                                                    <td class="one" align="left">
                                                        <input type="radio" name="imprimir" id="imprime1" onclick="imprimirPagamento();mostraMsg(1)" value="1" <c:if test="${imprimir==1}" >checked="true"</c:if>  > Entrada do aluno
                                                    </td>
                                                </tr>
                                                <tr class="odd">
                                                    <td class="one" align="left">
                                                        <input type="radio" name="imprimir" id="imprime2" onclick="imprimirPagamento();mostraMsg(2)" value="2" <c:if test="${imprimir==2}" >checked="true"</c:if> > Saída do Aluno
                                                    </td>
                                                </tr>
                                                <tr class="even">
                                                    <td class="one" align="left">
                                                        <input type="radio" name="imprimir" id="imprime3" onclick="imprimirPagamento();desmarcarTodosImprimir();mostraMsg(3)" value="3" <c:if test="${imprimir==3}" >checked="true"</c:if> > Não Imprimir
                                                    </td>
                                                </tr>
                                                <tr class="odd">
                                                    <td class="one" align="left">
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
                    <td width="100%" colspan="2">
                        <div class="faixasForm" style="width:97%;">
                            <table width="100%" class="displaytag">
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
                            <tr class="odd">
                                <td class="even" align="left">
                            <font class="errors"><mtw:outError field="justificativa" ><mtw:out/></mtw:outError></font>
                                <table width="100%">
                                    <tr>
                                        <td width="60%" align="left"><div class="errors" id="chekJustificativaErrors" style="width: 480px;margin-left: 10px; display: none" >Preencha justificativa quando aplicar desconto ou acréscimo!</div></td>
                                        <td width="40%" align="right"><font class="errors"><mtw:outError field="justificativa" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                </table>
                                </td>
                                </tr>
                            </table>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td width="100%" colspan="2">
                        <div class="panelButtonForm" >
                        <mtw:buttonAction action="pagamentoRead.do" klass="botao" name="Cancelar" value="Voltar"/>
                        <mtw:hasAuthorization permission="managerFluxoCaixa">
                            <div id="efetuarPagamento" <c:if test="${idFluxoCaixa==0}">style="display: none"</c:if>>
                            </mtw:hasAuthorization>
                            <mtw:if test="finalizar" value="true">
                                <mtw:hasAuthorization permission="pagarParcelaManager">
                                    <mtw:buttonAction name="finalizarPlano" klass="botao" onclick="showConfirmPag()" value="Efetuar Pagamento" />
                                </mtw:hasAuthorization>
                            </mtw:if>
                            <mtw:hasAuthorization permission="managerFluxoCaixa">
                            </div>
                        </mtw:hasAuthorization>
                    </div>
                </td>
            </tr>
        </table>
    </mtw:form>
</div>