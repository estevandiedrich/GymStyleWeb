<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="style/styleModal.css" type="text/css"/>
<script src="js/venda/manager.js" type="text/javascript"></script>

<%@include file="consultaUsuario.jsp" %>
<%@include file="consultaProduto.jsp" %>

<mtw:bean value="pojo" ></mtw:bean>
<table width="100%"><tr><td width="100%"><h1>Vendas de Produtos</h1></td></tr></table>
<div id="divMovimentacao" class="faixasForm">
    <div class="faixasForm">
        <table width="100%">
            <tr><td><h1 class="menos" id="labelAluno">Consulta Aluno</h1></td></tr>
            <tr>
                <td width="10%" >
                    <div id="divAluno" class="faixasForm">
                        <table width="100%">
                            <tr>
                                <td width="10%" class="one"><mtw:label klass="obrig" value="Aluno:"/></td>
                                <td width="10%" align="left">
                                    <mtw:input type="hidden" name="uduarioId" id="usuarioId" />
                                    <mtw:input klass="inputDisabledSelected" extra="readonly='true'" type="text" name="usuarioNome" id="usuarioNome" value="Clique para consultar..."/>
                                </td>
                                <td width="15%">
                                    <input type="button" name="Adicionar" class="consultar" id="consultaUsuario" value="" />
                                    <input type="button" name="Adicionar" class="remover" id="limparUsuario" value="" />
                                </td>
                                <td width="45%"></td>
                            </tr>
                        </table>
                    </div>
                </td>
            </tr>
        </table>
    </div>

    <div class="faixasForm">
        <table width="100%">
            <tr><td colspan="3"><h1>Lançamentos de Produtos</h1></td></tr>
            <tr>
                <td><mtw:label klass="obrig" value="Código:"/></td>
                <td><mtw:label klass="obrig" value="Produto:"/></td>
                <td></td>
                <td><mtw:label klass="obrig" value="Qtde:"/></td>
                <td><mtw:label klass="obrig" value="Preço:"/></td>
                <td><mtw:label klass="obrig" value="Total:"/></td>
            </tr>
            <tr>
                <td width="10%">
                    <mtw:input type="hidden" name="codigoProduto" id="idProduto" value=""/>
                    <mtw:input klass="inputTime" name="codigoProduto" id="codigoProduto" value=""/>
                </td>
                <td width="10%" align="left">
                    <input class="inputDisabledSelected" readonly='true' style="text-align: left;width: 150px" name="produto" id="produto" value=""/>
                </td>
                <td width="10%"><input type="button" name="Adicionar" id="consultaProduto" class="consultar" value="" /></td>
                <td width="10%" align="left">
                    <mtw:input klass="inputTime" name="qtde" value=""/>
                </td>
                <td width="10%" align="left">
                    <mtw:input klass="inputNumber" name="preco" value=""/>
                </td>
                <td width="10%" align="left">
                    <mtw:input klass="inputNumber" name="total" value=""/>
                </td>
                <td width="50%"><input type="button" name="Adicionar" class="adicionarVenda" value="Adicionar" /></td>
            </tr>
        </table>
    </div>                
    <div class="faixasForm" style="max-height: 250px;overflow: auto">
        <mtw:bean value="lista">
            <table width="100%" class="displaytag">
                <mtw:bean value="lista">
                    <thead>
                        <tr>
                            <th width="8%" >Item</th>
                            <th width="45%" >Produto</th>
                            <th width="5%" >Qtde</th>
                            <th width="15%">Preço Unitário</th>
                            <th width="15%">Total</th>
                            <th  width="12%">Ação</th>
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

                                    <td align="center" >
                                        <mtw:if test="reg.edit" >
                                            <a class='update' title='Editar' 
                                               href="javascript:showEditar(<mtw:out value="reg.entrada" />,${reg.id},<mtw:out value="reg.valor" />,<mtw:out value="reg.formaDePagamento.id" />,'<mtw:out value="reg.descricao" />',<mtw:isNull test="reg.registroContaBancaria">0</mtw:isNull><mtw:isNull test="reg.registroContaBancaria" negate="true"><mtw:out value="reg.registroContaBancaria.contaBancaria.id" /></mtw:isNull>);" >Editar</a>
                                            |<a class='delete' title='Excluir' href="javascript:excluirRegistroCaixa(${reg.id});">Excluir</a>                                
                                        </mtw:if>
                                    </td>
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
