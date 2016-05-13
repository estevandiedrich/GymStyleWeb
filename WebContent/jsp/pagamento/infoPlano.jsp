<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/pagamento/infoPlano.js"></script>
<div class="faixasForm">
    <mtw:bean value="mapa.plano">
        <h3></h3>
        <table width="95%" align="center" class="form">
            <tr>
                <td align="center" width="15%" valign="middle" >
                    <font id="eventoDetalhesPlano" class="mais" title="Visualizar detalhes!" style="font-weight: bold">Plano:</font>
                </td>
                <td align="left" width="45%">
                    <label title="<mtw:out value="plano"/>!"><mtw:out value="plano" max="40"/></label>
                </td>
                <td align="right" width="25%">
                    <mtw:label klass="obrig" value="Valor Plano:" textAlign="right"/>
                </td>
                <td align="left" width="10%" class="one">
                    <mtw:input  name="valorTotal" id="valorTotal" type="hidden"/>
                    <mtw:out  value="valorTotal" formatter="realFormatter"/>
                </td>
            </tr>
        </table>
        <div id="informacoesPlano" style="display:none ">
            <div class="faixasForm">
                <table width="100%" class="displaytag">
                    <tr class="even">
                        <th colspan="2">
                        </th>
                    </tr>
                    <tr class="odd">
                        <td >
                            <mtw:label klass="obrig" value="Matrícula:"/>
                        </td>
                        <td align="center" width="25%">
                            <mtw:out  value="valorMatricula" formatter="realFormatter" />
                        </td>
                    </tr>
                </table>
                <table width="100%" class="displaytag">
                    <mtw:bean value="modalidades">
                        <tr><th>Modalidades</th><th>Valor</th></tr>
                        <mtw:list value="modalidades">  
                            <tbody>
                                <mtw:isEmpty>  
                                    <tr>
                                        <td colspan="3" >
                                            Modalidades não cadastradas
                                        </td>
                                    </tr>
                                </mtw:isEmpty>
                                <mtw:loop var="row" counterStart="1" counter="i">  
                                    <tr class="
                                        <c:choose>
                                            <c:when test="${i%2==0}">odd</c:when>
                                            <c:otherwise>even</c:otherwise>
                                        </c:choose>
                                        ">
                                        <td  align="center">
                                            <mtw:out value="modalidade"/>
                                        </td>
                                        <td  align="center" width="25%">
                                            <mtw:if test="qtdeAcessos" value="1">
                                                <mtw:out value="valor1" formatter="realFormatter"/>
                                            </mtw:if>
                                            <mtw:if test="qtdeAcessos" value="2">
                                                <mtw:out value="valor2" formatter="realFormatter"/>
                                            </mtw:if>
                                            <mtw:if test="qtdeAcessos" value="3">
                                                <mtw:out value="valor3" formatter="realFormatter"/>
                                            </mtw:if>
                                            <mtw:if test="qtdeAcessos" value="4">
                                                <mtw:out value="valor4" formatter="realFormatter"/>
                                            </mtw:if>
                                            <mtw:if test="qtdeAcessos" value="5">
                                                <mtw:out value="valor5" formatter="realFormatter"/>
                                            </mtw:if>
                                            <mtw:if test="qtdeAcessos" value="6">
                                                <mtw:out value="valor6" formatter="realFormatter"/>
                                            </mtw:if>
                                            <mtw:if test="qtdeAcessos" value="7">
                                                <mtw:out value="valor7" formatter="realFormatter"/>
                                            </mtw:if>
                                        </td>
                                    </tr>
                                </mtw:loop>                                            
                            </tbody>
                        </mtw:list>  
                    </mtw:bean>
                </table>
            </div>
            <div class="faixasForm">
                <table width="100%" class="displaytag">
                    <tr class="odd" >
                        <th colspan="2">Resumo</th>
                    </tr>
                    <tr class="even" >
                        <td align="right" >
                            <mtw:label klass="obrig" value="Valor Total:" textAlign="right"/>
                        </td>
                        <td align="center" width="25%" >
                            <mtw:out value="valor" formatter="realFormatter" />
                        </td>
                    </tr>
                    <tr class="odd">
                        <td align="right">
                            <mtw:label klass="obrig" value="Desconto(R$):"/>
                        </td>
                        <td align="center" width="25%">
                            <mtw:out value="descontoReal" formatter="realFormatter" />
                        </td>
                    </tr>
                    <tr class="even">
                        <td align="right">
                            <mtw:label klass="obrig" value="Desconto(%):"/>
                        </td>
                        <td align="center" width="25%">
                            <mtw:out value="descontoPercentual"/>%
                        </td>
                    </tr>
                </table>
                <table width="100%" class="displaytag">
                    <tr class="odd" >
                        <th colspan="2">Observação</th>
                    </tr>
                    <tr class="even">
                        <td align="center" >
                            <mtw:textarea name="observacao" id="observacao" cols="120" rows="7" style="max-width: 700px;max-height: 100px;"/>
                        </td>
                    </tr>
                </table>
                <table width="100%" class="displaytag">
                    <tr class="odd" >
                        <th colspan="2">Resumo Final</th>
                    </tr>
                    <tr class="odd">
                        <td align="right" >
                            <mtw:label klass="obrig" value="Valor Final:" textAlign="right"/>
                        </td>
                        <td align="center" width="25%" >
                            <mtw:out value="valorTotal" formatter="realFormatter" />
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </mtw:bean>
</div>