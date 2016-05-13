<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="style/styleModal.css" type="text/css"/>
<script type="text/javascript" src="js/fluxocaixa/report/list.js"></script>
<script type="text/javascript" src="js/fluxocaixa/manager.js"></script>
<script src="js/excluir.js" type="text/javascript"></script>

<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/maskMoney.js" type="text/javascript"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>

<mtw:bean value="pojo" >
    <div id="windowFecharCaixa" ><div id="statusFecharCaixa"> <%@include file="fecharCaixaForm.jsp" %> </div></div>
    <div id="windowAbrirCaixa" ><div id="statusAbrirCaixa"> <%@include file="abrirCaixaForm.jsp" %> </div></div>
    <div id="windowRetirada" ><div id="statusRetirada"> <%@include file="retiradaForm.jsp" %> </div></div>
    <div id="windowEntrada" ><div id="statusEntrada"> <%@include file="entradaForm.jsp" %> </div></div>

    <table width="100%"><tr><td width="100%"><h1>Gerenciamento</h1></td></tr></table>
    <div>
        <table width="100%" >
            <tr>
                <td width="20%" valign="bottom">
                    <mtw:input type="hidden" id="abaSelect" name="abaSelect" value="eventoMovimentacao"/>
                    <input class="abaCurrent" id="eventoMovimentacao" title="Movimentação!" type="button" value="Movimentação" />
                    <mtw:hasAuthorization permission="caixaReportRead">
                        <input class="aba" id="eventoHistorico" type="button" title="Fluxo de Caixa!" value="Historico" />
                    </mtw:hasAuthorization>
                    <mtw:input type="hidden" name="id" id="idFluxoCaixa" />
                </td>
                <td width="50%" align="center" valign="bottom">
                    <mtw:if test="aberto" negate="true"><div class="msgCaixaFechado" style="width: 400px;height: 40px;">Caixa Fechado.<span class="errors"><br>É necessário abrir o caixa para efetuar movimentações!</span></div></mtw:if>
                    <mtw:if test="aberto" ><div class="msgCaixaAberto" style="height: 25px">Caixa Aberto</div></mtw:if>
                    </td>
                    <td width="30%"  align="right">
                    <mtw:if test="aberto" negate="true">
                        <mtw:hasAuthorization permission="abrirFluxoCaixa">
                            <div onclick="showAbrirCaixa()" id="abrirCaixaMsg" >Abrir Caixa</div>
                        </mtw:hasAuthorization>
                    </mtw:if>
                    <mtw:if test="aberto">
                        <mtw:hasAuthorization permission="fecharFluxoCaixa">
                            <div onclick="showFecharCaixa()" id="fecharCaixaMsg" >Fechar Caixa</div>
                        </mtw:hasAuthorization>
                    </mtw:if>
                </td>
            </tr>
        </table>
    </div>
</mtw:bean>

<div id="divMovimentacao" class="faixasForm">
    <div class="faixasForm">
        <table width="100%">
            <tr><td colspan="3"><h1>Opções</h1></td></tr>
            <tr>
                <td width="14%" >
                    <mtw:if test="aberto">
                        <mtw:hasAuthorization permission="entradaFluxoCaixa">
                            <div class="entradaCaixa" onclick="showEntrada()"> Entrada</div>
                        </mtw:hasAuthorization>
                    </mtw:if>
                    <mtw:if test="aberto" negate="true" ><div class="entradaCaixaInativo" onclick="msgCaixaFechadoShow()" > Entrada</div></mtw:if>
                    </td>
                    <td width="14%" >
                    <mtw:if test="aberto" >
                        <mtw:hasAuthorization permission="retiradaFluxoCaixa">
                            <div class="retiradaCaixa" onclick="showRetirada()"> Retirada</div>
                        </mtw:hasAuthorization>
                    </mtw:if>
                    <mtw:if test="aberto" negate="true" ><div class="retiradaCaixaInativo" onclick="msgCaixaFechadoShow()" > Retirada</div></mtw:if>
                    </td>
                    <td width="82%"></td>
                </tr>
            </table>
        </div>
        <div class="faixasForm" style="max-height: 250px;overflow: auto">
        <mtw:bean value="lista">
            <table width="100%" class="displaytag">
                <mtw:bean value="lista">
                    <thead>
                        <tr>
                            <th width="3%" style="font-style: italic;font-size: 10px" title="Entrada/Retirada do caixa!">E/R</th>
                            <th width="3%" style="font-style: italic;font-size: 10px" title="Forma de Pagamento!">FP</th>
                            <th width="38%" >Descrição</th>
                            <th width="20%">Data-Hora</th>
                            <th width="3%" >-</th>
                            <th width="14%">Valor</th>
                            <mtw:if test="aberto">
                                <th  width="19%">Ação</th>
                            </mtw:if>
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
                                        <c:when test="${i%2==0}">odd</c:when>
                                        <c:otherwise>even</c:otherwise>
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
                                        <mtw:if test="reg.entrada" negate="true">
                                            <label class="error" <c:if test="${reg.valor<0}" >style="font-weight: bold"</c:if> >
                                                <c:if test="${reg.valor>0}" >-</c:if><mtw:out value="reg.valor" formatter="realFormatter"/>
                                                </label>                                            
                                        </mtw:if>
                                        <mtw:if test="reg.entrada" negate="false">
                                            <label <c:if test="${reg.valor<0}" >class="error" style="font-weight: bold"</c:if><c:if test="${reg.valor>=0}" >class="oks"</c:if> >
                                                <mtw:out value="reg.valor" formatter="realFormatter"/>
                                            </label>                                            
                                        </mtw:if>
                                    </td>
                                    <mtw:if test="aberto">
                                        <td align="center" >
                                            <mtw:if test="reg.edit" >
                                                <mtw:hasAuthorization permission="entradaFluxoCaixa,retiradaFluxoCaixa">
                                                    <a class='update' title='Editar' href="javascript:showEditar(<mtw:out value="reg.entrada" />,${reg.id},<mtw:out value="reg.valor" />,<mtw:out value="reg.formaDePagamento.id" />,'<mtw:out value="reg.descricao" />',<mtw:isNull test="reg.registroContaBancaria">0</mtw:isNull><mtw:isNull test="reg.registroContaBancaria" negate="true"><mtw:out value="reg.registroContaBancaria.contaBancaria.id" /></mtw:isNull>);" >Editar</a>
                                                </mtw:hasAuthorization>
                                                |
                                                <mtw:hasAuthorization permission="registroCaixaDelete">
                                                    <a class='delete' title='Excluir' href="javascript:excluirRegistroCaixa(${reg.id});">Excluir</a>
                                                </mtw:hasAuthorization>
                                            </mtw:if>
                                        </td>
                                    </mtw:if>
                                </tr>
                            </mtw:loop>
                        </mtw:list>
                    </mtw:bean>
            </table>
        </mtw:bean>
    </div>
    <div class="faixasForm">
        <mtw:bean value="pojo">
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
                    <td class="odd" align="left" colspan="8"><label class="mais" id="inicial" style="cursor: pointer;" >Detalhamento</label></td>
                </tr>
            </table>
            <div id="detalhesValorInicial" style="display: none">
                <table width="100%" class="displaytag" >
                    <tr>
                        <td width="50%" valign="top" >
                            <table width="100%" class="displaytag" >
                                <tr>
                                    <th colspan="4"><mtw:if test="abertura" value="null" negate="true">Caixa aberto em: <mtw:out value="abertura.time" /></mtw:if></th>
                                    </tr>
                                <mtw:if test="usuarioAbriu" value="null" negate="true">
                                    <tr>
                                        <td class="odd" width="22%" ><mtw:label klass="obrig" value="Operador:"/></td>
                                        <td class="odd" colspan="3"><mtw:out value="usuarioAbriu.usuario" /></td>
                                    </tr>
                                </mtw:if>
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
                                    <mtw:if test="usuarioFechou" value="null" negate="true">
                                        <tr>
                                            <td class="odd" width="22%" ><mtw:label klass="obrig" value="Operador:"/></td>
                                            <td class="odd" colspan="3"><mtw:out value="usuarioFechou.usuario" /></td>
                                        </tr>
                                    </mtw:if>
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
            </div>
        </mtw:bean>
    </div>
</div>

<div id="divHistorico">
    <mtw:form action="caixaReportRead.do" name="relatorioCaixaForm" >
        <h3>Fluxo de Caixa Mensal</h3>
        <%@include  file="report/filtro.jsp" %>
        <div id="content" valign="top" >
            <%@include file="report/corpo.jsp" %>
        </div>
    </mtw:form>
</div>