<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/usuario/infoPlanoUsuario.js"></script>

<div class="faixasForm">
    <mtw:bean value="infoPlano">
        <h3></h3>
        <table width="100%" align="center" class="form">
            <tr>
                <td align="center" width="10%" valign="middle" >
                    <font id="eventoDetalhesPlano" class="mais" title="Visualizar detalhes!" style="font-weight: bold">Plano:</font>
                </td>
                <td align="left" width="30%">
                    <mtw:out value="plano" />
                </td>
                <td align="right" width="10%">
                    <mtw:label klass="obrig" value="Matricula:" textAlign="right"/>
                </td>
                <td align="left" width="10%" class="one">
                    <input name="valorMatricula" id="valorMatricula" type="hidden" value="<mtw:out  value="valorMatricula" formatter="realFormatter"/>"/>
                    <mtw:out  value="valorMatricula" formatter="realFormatter"/>
                </td>
                <td align="right" width="20%">
                    <mtw:label klass="obrig" value="Valor Plano:" textAlign="right"/>
                </td>
                <td align="left" width="20%" class="one">
                    <mtw:input  name="valorTotal" id="valorTotal" type="hidden"/>
                    <mtw:out  value="valorTotal" formatter="realFormatter"/>
                </td>
            </tr>
        </table>
        <div id="corpoInfoPlano" style="display: inline">
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