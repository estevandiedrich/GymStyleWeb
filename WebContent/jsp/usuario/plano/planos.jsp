
<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/usuario/plano.js"></script>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/maskMoney.js" type="text/javascript"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>


<div class="title_bottom">
    <mtw:form method="post" action="usuarioUpdatePlano.do" name="form">
        <mtw:bean value="pojo">
            <%@include file="../topo.jsp" %>
            <h3>Editar</h3>
            <table width="100%">
                <tr>
                    <td width="100%">
                        <mtw:input type="hidden" name="id" id="id"/>
                    </td>
                </tr>
                <tr>
                    <td class="title_bottom">
                        <mtw:buttonAction klass="aba" action="usuarioUpdateInformacao.do" id="eventoInformacoes" name="informacao" value="Informações Pessoais"/>
                        <mtw:buttonAction klass="aba" action="usuarioUpdateIdentificacao.do" id="eventoIdentificacao" name="identificacao" value="Identificação"/>
                        <input class="abaCurrent" id="eventoPlanos" type="button" value="Planos"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div id="planos">
                            <table width="100%">
                                <tr>
                                    <td>
                                        <div class="title_bottom">
                                            <table width="100%">
                                                <tr>
                                                    <td>
                                                        <%@include file="../info.jsp" %>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="100%">
                                                        <mtw:input type="hidden" name="vincularPlanoUsuario" value="vincularPlanoUsuario"/>
                                                        <c:if test="${vincularPlanoUsuario == true}">
                                                            <fieldset class="field">
                                                                <legend></legend>
                                                                <div>
                                                                    <mtw:hasAuthorization permission="vincularPlano" >
                                                                        <%@include file="vincularPlano.jsp" %>
                                                                    </mtw:hasAuthorization>
                                                                    <mtw:hasAuthorization permission="vincularPlano" negate="true">
                                                                        <table width="100%" >
                                                                            <tr>
                                                                                <td align="center" class="errors" style="padding: 10px;"><font class="errors" style="padding: 10px;">Você não possui permissão de Vincular plano!</font></td>
                                                                            </tr>
                                                                        </table>
                                                                    </mtw:hasAuthorization>
                                                                </div>
                                                            </fieldset>
                                                        </c:if>
                                                        <c:if test="${vincularPlanoUsuario == false}">
                                                            <table width="100%">
                                                                <tr>
                                                                    <td>
                                                                        <div class="faixasForm">
                                                                            <mtw:bean value="infoPlano">
                                                                                <h3></h3>
                                                                                <table width="100%" align="center" class="form">
                                                                                    <tr>
                                                                                        <td align="center" width="10%" valign="middle" >
                                                                                    <font style="font-weight: bold">Plano:</font>
                                                                                    </td>
                                                                                    <td align="left" width="20%">
                                                                                        <mtw:out value="plano" />
                                                                                    </td>
                                                                                    <td align="right" width="10%">
                                                                                        <mtw:label klass="obrig" value="Matricula:" textAlign="right"/>
                                                                                    </td>
                                                                                    <td align="left" width="10%" >
                                                                                        <mtw:input name="valorMatricula" id="valorMatricula" type="hidden"/>
                                                                                        <mtw:out  value="valorMatricula" formatter="realFormatter"/>
                                                                                    </td>
                                                                                    <td align="right" width="10%">
                                                                                        <mtw:label klass="obrig" value="Valor Plano:" textAlign="right"/>
                                                                                    </td>
                                                                                    <td align="left" width="10%" >
                                                                                        <mtw:input  name="valorTotal" id="valorTotal" type="hidden"/>
                                                                                        <mtw:out  value="valorTotal" formatter="realFormatter"/>
                                                                                    </td>
                                                                                    <td align="left" width="20%">
                                                                                        <mtw:hasAuthorization permission="cancelarPlano" >
                                                                                            <a class="cancelarPlano" id="cancelarPlano" href="cancelarPlano.do?idPlanoUsuario=<mtw:out value="idPlanoUsuario"/>">Cancelar Plano</a>
                                                                                        </mtw:hasAuthorization>
                                                                                    </td>
                                                                                    </tr>
                                                                                </table>
                                                                                <div id="corpoInfoPlano" >
                                                                                    <div class="faixasForm">
                                                                                        <table width="100%" class="displaytag">
                                                                                            <mtw:bean value="modalidades">
                                                                                                <tr><th>Modalidade</th><th>Valor</th></tr>
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
                                                                                                                    <c:when test="${i%2==0}">even</c:when>
                                                                                                                    <c:otherwise>odd</c:otherwise>
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
                                                                                            <mtw:bean value="pagamentos">
                                                                                                <tr><th>#</th><th>Vencimento</th><th>Fim de Acesso</th><th>Valor</th><th>Pagamento</th><th>Valor</th></tr>
                                                                                                <mtw:list value="pagamentos">  
                                                                                                    <tbody>
                                                                                                        <mtw:isEmpty>  
                                                                                                            <tr>
                                                                                                                <td colspan="3" >
                                                                                                                    Pagamentos não cadastradas
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
                                                                                                                    <mtw:out value="i"/>
                                                                                                                </td>
                                                                                                                <td  align="center">
                                                                                                                    <mtw:out value="vencimento.time" formatter="dateFormatter"/>
                                                                                                                </td>
                                                                                                                <td  align="center">
                                                                                                                    <mtw:out value="fimAcesso.time" formatter="dateFormatter"/>
                                                                                                                </td>
                                                                                                                <td  align="center" width="15%">
                                                                                                                    <mtw:out value="valor" formatter="realFormatter"/>
                                                                                                                </td>
                                                                                                                <td  align="center" width="25%">
                                                                                                                    <mtw:out value="pagamento.time" formatter="dateFormatter"/>
                                                                                                                </td>
                                                                                                                <td  align="center" width="15%">
                                                                                                                    <mtw:if test="pagamento" value="null" negate="true">
                                                                                                                        <mtw:out value="valorPago" formatter="realFormatter"/>
                                                                                                                    </mtw:if>
                                                                                                                    <mtw:if test="pagamento" value="null" >
                                                                                                                        <a id='pagamentos' title='Ver' href='verParcelaPlanoUsuario.do?idPlanoUsuario=<mtw:out value="infoPlano.idPlanoUsuario"/>&idParcela=<mtw:out value="id"/>'>Pagar parcela</a>
                                                                                                                    </mtw:if>
                                                                                                                </td>
                                                                                                            </tr>
                                                                                                        </mtw:loop>                                            
                                                                                                    </tbody>
                                                                                                </mtw:list>  
                                                                                            </mtw:bean>
                                                                                        </table>
                                                                                    </div>
                                                                                </div>
                                                                            </mtw:bean>
                                                                        </div>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </c:if>
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
                    <td width="100%" class="panelButtonForm">
                        <mtw:buttonAction klass="botao" action="usuarioRead.do" name="Cancelar" value="Cancelar"/>
                        <mtw:buttonAction klass="botao" action="usuarioUpdateIdentificacao.do" name="anterior" value="Anterior"/>
                        <mtw:buttonAction klass="botaoDes" name="proximo" disabled="true" value="Próximo"/>
                        <mtw:if test="planosVazios" >
                            <mtw:buttonAction klass="botaoDes" name="Salvar" disabled="true" value="Salvar"/>
                        </mtw:if>
                        <mtw:if test="planosVazios" negate="true">
                            <c:if test="${vincularPlanoUsuario == true}"><mtw:submit klass="botao" value="Salvar" name="buttonSalvarUpdateUsuario" /></c:if>
                            <c:if test="${vincularPlanoUsuario == false}"><mtw:buttonAction klass="botao" action="usuarioRead.do" name="sair" value="Sair"/></c:if>
                        </mtw:if>
                    </td>
                </tr>
            </table>
        </mtw:bean>
    </mtw:form>
</div>