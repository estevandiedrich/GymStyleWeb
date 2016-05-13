<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/excluir.js" type="text/javascript"></script>

<mtw:bean value="paginator" var="pag">
    <table width="100%" class="displaytag">
        <thead>
            <tr>
                <th width="3%" style="font-style: italic;font-size: 10px" title="Entrada/Retirada da Conta Bancária!">E/R</th>
                <th width="3%" style="font-style: italic;font-size: 10px" title="Forma de Pagamento!">FP</th>
                <th width="40%" >Descrição</th>
                <th width="20%" >Data-Hora</th>
                <th width="3%" >-</th>
                <th width="14%">Valor</th>
                <th  width="17%">Ação</th>
            </tr>
        </thead>
        <tbody id="corpoTable">
            <mtw:bean value="pag.list">
                <mtw:list value="pag.list">
                    <mtw:isEmpty>
                        <tr class="sub2">
                            <td colspan="8">Registros não encontrados</td>
                        </tr>
                    </mtw:isEmpty>
                    <mtw:loop var="row" counterStart="0" counter="i">
                        <tr class="<c:choose><c:when test="${i%2==0}">odd</c:when><c:otherwise>even</c:otherwise></c:choose>" title="Operador ${row.usuarioRegistrou.usuario}!">
                            <td>
                                <mtw:if test="row.entrada"><img src="images/up.png" title="Entrada!" style="cursor: help;"/></mtw:if>
                                <mtw:if test="row.entrada" negate="true" ><img src="images/down.png"  style="cursor: help;" title="Retirada!"/></mtw:if>
                            </td>
                            <td><mtw:out value="formaDePagamento.image"/></td>
                            <td align="center" ><mtw:out value="descricao" max="30"/></td>
                            <td align="center" ><mtw:out value="dataHora.time"/></td>
                            <td align="center">
                                <c:if test="${row.registroCaixa!=0}">
                                    <mtw:if test="row.entrada"><div class="retiradaConta" title="Entrada na Conta Bancária!"></div></mtw:if>
                                    <mtw:if test="row.entrada" negate="true" ><div class="entradaConta" title="Entrada do Caixa!"></div></mtw:if>
                                </c:if>
                            </td>
                            <td align="right" style="padding-right: 13px">
                                <label <mtw:if test="entrada" negate="true">class="error"</mtw:if> <mtw:if test="entrada" negate="false">class="oks"</mtw:if> > <mtw:if test="entrada" negate="true">-</mtw:if> <mtw:out value="valor" formatter="realFormatter"/></label>
                            </td>
                            <td align="center">
                                <mtw:if test="row.edit" >
                                    <mtw:hasAuthorization permission="entradaContaBancaria,retiradaContaBancaria">
                                    <a class='update' title='Editar' 
                                       href="javascript:showEditar(<mtw:out value="row.entrada" />,${row.id},<mtw:out value="row.valor" />,<mtw:out value="row.formaDePagamento.id" />,'<mtw:out value="row.descricao" />',<mtw:out value="row.registroCaixa"/>);" >Editar</a>
                                    </mtw:hasAuthorization>
                                    |<mtw:hasAuthorization permission="registroContaBancariaDelete"><a class='delete' title='Excluir' href="javascript:excluirRegistroContaBancaria(${row.id});">Excluir</a></mtw:hasAuthorization>
                                </mtw:if>                            
                            </td>
                        </tr>
                    </mtw:loop>
                    <tr>
                        <td align="right" width="100%" colspan="7">
                            <%@include file="paginacaoPaginator.jsp" %>
                        </td>
                    </tr>
                </mtw:list>
            </mtw:bean>
        </tbody>
    </table>
</mtw:bean>
