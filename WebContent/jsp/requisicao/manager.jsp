<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/requisicao/manager.js"></script>

<div class="title_bottom" style="width: 100%">
    <table class="topo" width="100%">
        <tr>
            <td><h1>Atualizando Catraca</h1></td>
            <td align="right" id="filtro" width="10%">
                <div id="item"><a id="list" href="requisicaoRead.do">Listar</a></div>
            </td>
        </tr>
    </table>
    <form action="${retorno}" method="POST">
        <table class="faixasForm" width="98%">
            <tr>
                <td class="one" width="100%">
                    <br><h1 style="font-size: 16px"><mtw:out value="tipo"/></h1>
                </td>
            </tr>            
            <tr>
                <td width="100%">
                    <div class="faixasForm" style="width: 98%">
                        <h3></h3>
                        <table width="100%" class="displaytag">
                            <tr class="odd">
                                <th><mtw:label klass="obrig" value="Nome"/></th>
                                <th><mtw:label klass="obrig" value="CPF"/></th>
                                <th>
                                    <mtw:hasAuthorization permission="reciboReport">
                                        <c:if test="${recibo!=null || recibos!=null}" >
                                            <mtw:label klass="obrig" value="Recibo"/>
                                        </c:if>
                                    </mtw:hasAuthorization>
                                </th>
                            </tr>
                            <tr class="sub4">
                                <td class="one" width="40%" >
                                    <mtw:out value="usuario.usuario" />
                                </td>
                                <td class="one" width="30%" >
                                    <mtw:out value="usuario.cpf" />
                                </td>
                                <td align="center" width="30%" >
                                    <mtw:hasAuthorization permission="reciboReport" >
                                        <c:if test="${recibos!=null}" >
                                            <div style="width: 195px" class="faixasForm">
                                                <table width="100px" class="displaytag">
                                                    <tbody>
                                                        <mtw:list value="recibos">
                                                            <mtw:isEmpty>
                                                                <tr class="sub"><td colspan="2">Dados Vazios</td></tr>
                                                            </mtw:isEmpty>
                                                            <mtw:loop var="row" counterStart="1" counter="i">
                                                                <tr class="
                                                                    <c:choose>
                                                                        <c:when test="${i%2==0}">even</c:when>
                                                                        <c:otherwise>odd</c:otherwise>
                                                                    </c:choose>
                                                                    ">
                                                                    <td class="one" align="left">
                                                                        <a class="pdf" href="reciboReport.do?idPagamento=${row.id}&valor=${row.valorPago}" 
                                                                           target="_blank" title="Gerar recibo PDF!" style="width: 60px;padding-left: 20px" >Imprimir <mtw:out value="vencimento.time" formatter="mesFormatter"/></a>
                                                                    </td>
                                                                </tr>
                                                            </mtw:loop>
                                                        </mtw:list>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </c:if>
                                        <c:if test="${recibo!=null}" >
                                            <a class="pdf" href="reciboReport.do?idPagamento=${recibo.id}&valor=${recibo.valorPago}" target="_blank" title="Gerar recibo PDF!" style="width: 60px;padding-left: 20px" >Imprimir <mtw:out value="recibo.vencimento.time" formatter="mesFormatter"/></a>
                                        </c:if>
                                    </mtw:hasAuthorization>
                                </td>
                            </tr>
                        </table>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="100%" align="left">
                    <div id="corpo" style="width: 100%">
                        <input type="hidden" name="idRequisicao" id="idRequisicao" value="<mtw:out value="idRequisicao"/>"/>
                        <input type="hidden" name="idUsuario" id="idUsuario" value="<mtw:out value="usuario.id"/>"/>
                        <input type="hidden" name="idPlanoUsuario" id="idPlanoUsuario" value="<mtw:out value="idPlanoUsuario"/>"/>
                        <div id="carregando"  style="width: 100%">
                            <!--%@include  file="carregando.jsp" %-->
                        </div>
                        <div id="result" style="width: 100%" >
                            <!--input type="hidden" name="contador" id="contador" value="<!--mtw:out value="contador"/-->
                            <%@include file="destinos.jsp" %>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
        <table width="100%" >
            <tr>
                <td width="100%" class="panelButtonForm" colspan="2">
                    <input type="submit" name="voltar" value="voltar" class="botao" />
                </td>
            </tr>
        </table>
    </form>
</div>

