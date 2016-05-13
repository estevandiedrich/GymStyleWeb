<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/excluir.js"></script>
<script src="js/ficha/info.js"></script>
<script type="text/javascript" src="js/ficha/fichaUsuarioList.js"></script>

<div class="title_bottom">
    <table class="topo" width="100%">
        <tr>
            <td  width="90%">
                <h1>Fichas do Aluno</h1>
            </td>
            <td  width="10%" align="right">
                <a id="list" href="fichaRead.do" title="Listar Dispositivos!">Listar</a>
            </td>
        </tr>
    </table>
    <mtw:form action="fichaRead.do">
        <div class="faixasForm">
            <mtw:input type="hidden" name="idUsuario" id="idUsuario"/>
            <mtw:input type="hidden" name="fichaValue" id="fichaValue" value="-1"/>
            <mtw:bean value="usuario">
                <div class="faixasForm" style="width: 97%;min-height: 100px" >
                    <table style="width: 98%">
                        <tr>
                            <td width="85%"><h1>Informações Pessoais</h1></td>
                            <td width="15%" align="center">
                                <a id='novo' title='Nova Ficha' href="fichaCreate.do?idUsuario=${usuario.id}">Nova Ficha</a>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <table width="100%" class="displaytag">
                                    <thead>
                                        <tr>
                                            <th width="50%">Aluno</th>
                                            <th width="20%">CPF</th>
                                            <th width="15%">Telefone</th>
                                            <th width="15%">Celular</th>
                                        </tr>
                                    </thead>
                                    <tbody id="tBody">
                                        <tr class="odd">
                                            <td align="center" class="one"><mtw:out value="usuario"/></td>
                                            <td align="center"><mtw:out value="cpf"/></td>
                                            <td align="center"><mtw:out value="telefone"/></td>
                                            <td align="center">
                                                <mtw:out value="celular"/>
                                                <%--<mtw:input type="hidden" name="idUsuario" value="id"/>--%>
                                                <mtw:input type="hidden" name="idUsuario" value="${usuario.id}"/>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                    </table>
                </div>
            </mtw:bean>
            <h3>Listar</h3>
            <div id="msgFicha" style="display: none">
                <h1><font class="errors">Ficha ativa fora do periodo de acesso<img src="images/alert.png" /></font></h1>
            </div>
            <table width="100%" class="displaytag">
                <mtw:bean value="list" >
                    <thead>
                        <tr>
                            <th title="Ativar Impressão de Ficha!"><img src="images/print.png"/></th>
                            <th>Período Inicial</th>
                            <th>Período Final</th>
                            <th>Dias de Treino</th><th>Ação</th></tr>
                    </thead>
                    <tbody>
                        <mtw:list value="list" orderBy="id" desc="true">
                            <mtw:isEmpty>
                                <tr class="sub">
                                    <td colspan="5">
                                        Dados Vazios
                                    </td>
                                </tr>
                            </mtw:isEmpty>
                            <mtw:loop   var="row" counterStart="0" counter="i">
                                <tr class="
                                    <c:choose>
                                        <c:when test="${i%2!=0}">odd</c:when>
                                        <c:otherwise>even</c:otherwise>
                                    </c:choose>
                                    ">
                                    <td width="5%">
                                        <input type="radio" name="ativa" id="ativa${row.id}" <mtw:if test="ativa">checked</mtw:if>
                                               onclick="atualizaStatusFicha(${row.id});" title="Ativar/Desativar ficha!" />
                                    </td>
                                    <td width="14%">
                                        <mtw:out value="periodoInicial.time" formatter="dateFormatter"/>
                                    </td>
                                    <td width="14%">
                                        <mtw:out value="periodoFinal.time" formatter="dateFormatter"/>                                        
                                    </td>
                                    <td width="24%" align="center">
                                        <div class="faixasForm" style="width: 130px;" >
                                            <table class="filho" width="100%">
                                                <tr style="height: 22px">
                                                    <mtw:if test="treinaSegunda${row.id}"><td style="background-color:#66CDAA" title="Segunda!">S</td></mtw:if>
                                                    <mtw:if test="treinaSegunda${row.id}" negate="true"><td class="odd" title="Segunda!">S</td></mtw:if>

                                                    <mtw:if test="treinaTerca${row.id}"><td style="background-color:#66CDAA" title="Terça!">T</td></mtw:if>
                                                    <mtw:if test="treinaTerca${row.id}"  negate="true"><td class="odd" title="Terça!">T</td></mtw:if>

                                                    <mtw:if test="treinaQuarta${row.id}"><td style="background-color:#66CDAA" title="Quarta!">Q</td></mtw:if>
                                                    <mtw:if test="treinaQuarta${row.id}" negate="true"><td class="odd" title="Quarta!">Q</td></mtw:if>

                                                    <mtw:if test="treinaQuinta${row.id}"><td style="background-color:#66CDAA" title="Quinta!">Q</td></mtw:if>
                                                    <mtw:if test="treinaQuinta${row.id}" negate="true"><td class="odd" title="Quinta!" >Q</td></mtw:if>

                                                    <mtw:if test="treinaSexta${row.id}"><td style="background-color:#66CDAA" title="Sexta!">S</td></mtw:if>
                                                    <mtw:if test="treinaSexta${row.id}" negate="true"><td class="odd" title="Sexta!">S</td></mtw:if>

                                                    <mtw:if test="treinaSabado${row.id}"><td style="background-color:#66CDAA" title="Sábado!">S</td></mtw:if>
                                                    <mtw:if test="treinaSabado${row.id}" negate="true"><td class="odd" title="Sábado!">S</td></mtw:if>

                                                    <mtw:if test="treinaDomingo${row.id}"><td style="background-color:#66CDAA" title="Domingo!">D</td></mtw:if>
                                                    <mtw:if test="treinaDomingo${row.id}" negate="true"><td class="odd" title="Domingo!">D</td></mtw:if>
                                                </tr>
                                            </table>
                                        </div>
                                    </td>
                                    <td width="44%" align="center">
                                        <table width="100%" >
                                            <tr>
                                                <td>
                                                <mtw:hasAuthorization permission="fichaManager"><a class='update' title='Editar Ficha!' href="fichaUpdate.do?idUsuario=${usuario.id}&idFicha=${row.id}">Editar</a></mtw:hasAuthorization> |
                                                </td>
                                                <td>
                                                <mtw:hasAuthorization permission="fichaManager"><a class='ordenar' title='Ordenar Exercícios!' href="ordenarFicha.do?idUsuario=${usuario.id}&idFicha=${row.id}">Ordenar</a></mtw:hasAuthorization> |
                                                </td>
                                                <td>
                                                <mtw:hasAuthorization permission="fichaDelete"><a class='delete' title='Excluir!' href="javascript:excluirFicha(${usuario.id},${row.id});">Excluir</a></mtw:hasAuthorization> |
                                                </td>
                                                <td>
                                                <mtw:hasAuthorization permission="fichaRead"><a id='ver' title='Ver Ficha!' href="fichaInfo.do?idUsuario=${usuario.id}&idFicha=${row.id}">Ver</a></mtw:hasAuthorization> |
                                                </td>
                                                <td>
                                                <mtw:hasAuthorization permission="fichaReport"><input type="button" title="Imprimir em PDF!" class="pdf" style="margin: 0px;width: 70px;padding-left: 12px" value="Imprimir" onclick="imprimirFicha('${usuario.id}','${row.id}','')" /></mtw:hasAuthorization>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </mtw:loop>  
                        </mtw:list>  
                    </tbody>
                </mtw:bean>
            </table>
            <table width="100%" >
                <tr>
                    <td width="100%" class="panelButtonForm">
                        <mtw:submit klass="botao" name="Voltar" value="Voltar"/>
                    </td>
                </tr>
            </table>
        </div>
    </mtw:form>
</div>