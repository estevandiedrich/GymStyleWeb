<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/requisicao/listTodos.js"></script>

<div class="title_bottom">
    <%@include  file="topo.jsp" %>
    <mtw:form method="post" action="requisicaoAtualizarTodos.do" name="listForm">
        <h3>Alunos Ativos</h3>
        <div id="content">
            <table width="100%" class="panelButtonForm">
                <tr >
                    <td width="10%" >
                        <mtw:buttonAction klass="botao" name="todos" id="todos" value="Atualizar Todos" onclick="enviarRequisicoes();desabilita()"/>
                    </td>
                    <td width="10%" >
                        <mtw:buttonAction klass="botao" name="pendentes" id="pendentes" value="Atualizar Pendentes"
                                          onclick="desabilita();enviarRequisicoesPendentes();"/>
                    </td>
                    <td width="10%" >
                        <mtw:buttonAction klass="botaoDes" name="parar"  id="parar" value="Parar Atualizações" disabled="true" onclick="paraAtualizacoes();habilita()"/>
                    </td>
                    <td width="70%" >
                        <input type="hidden" name="tamanho" id="tamanho" value="<mtw:out value="list.size"/>" />
                    </td>
                </tr>
            </table>
            <table width="100%" class="displaytag">
                <mtw:bean value="list">
                    <thead>
                        <tr><th>-</th><th>Aluno</th><th>Matricula</th><th>Destinos</th></tr>
                    </thead>
                    <tbody>
                        <mtw:list value="list">
                            <mtw:isEmpty>  
                                <tr class="sub">
                                    <td colspan="4">
                                        Alunos não possuem plano em aberto.
                                    </td>
                                </tr>
                            </mtw:isEmpty>  
                            <mtw:loop var="usuario" counterStart="0" counter="i" >  
                                <tr class="
                                    <c:choose>
                                        <c:when test="${i%2==0}">odd</c:when>
                                        <c:otherwise>even</c:otherwise>
                                    </c:choose>
                                    " >
                                    <td width="10%"
                                        class="
                                        <c:choose>
                                            <c:when test="${i%2==0}">odd</c:when>
                                            <c:otherwise>even</c:otherwise>
                                        </c:choose>
                                        " id="col1lin${i}" >
                                        <font style="font-size: 12px" >${i+1}</font>
                                    </td>
                                    <td width="44%" id="col3lin${i}" >
                                        <mtw:out value="usuario"/>
                                        <input type="hidden" name="aluno${i}" id="aluno${i}" value="<mtw:out value="id"/>" />
                                    </td>
                                    <td width="13%" id="col2lin${i}" >
                                      <mtw:out value="matricula"/>
                                    </td>
                                    <td width="33%" align="center" id="col4lin${i}" >
                                        <mtw:if test="sincronizado" negate="true">
                                            <img src="images/sinc_error.png" title="Não Sincronizado!" id="refresh${i}"/>
                                        </mtw:if>
                                        <mtw:if test="sincronizado" negate="false">
                                            <img src="images/sinc_ok.png" title="Sincronizado!" id="refresh${i}"/>
                                        </mtw:if>
                                        <div id="result${i}" style="display: none; width: 280px" >
                                            <img src="images/indicator.gif"/>
                                            <mtw:if test="sincronizado" negate="true">
                                                <input type="hidden" name="sincronizado${i}" id="sincronizado${i}" value="0" />
                                            </mtw:if>
                                            <mtw:if test="sincronizado" negate="false">
                                                <input type="hidden" name="sincronizado${i}" id="sincronizado${i}" value="1" />
                                            </mtw:if>
                                        </div>
                                    </td>
                                </tr>
                            </mtw:loop>  
                        </mtw:list>
                    </tbody>
                </mtw:bean>  
            </table>
            <table width="100%" class="panelButtonForm">
                <tr >
                    <td width="10%" >
                        <mtw:buttonAction klass="botao" name="todos" id="todos2" value="Atualizar Todos"
                                          onclick="enviarRequisicoes();desabilita()"/>
                    </td>
                    <td width="10%" >
                        <mtw:buttonAction klass="botao" name="pendentes" id="pendentes2" value="Atualizar Pendentes"
                                          onclick="desabilita();enviarRequisicoesPendentes();"/>
                    </td>
                    <td width="10%" >
                        <mtw:buttonAction klass="botaoDes" name="parar"  id="parar2" value="Parar Atualizações" disabled="true"
                                          onclick="paraAtualizacoes();habilita()"/>
                    </td>
                    <td width="10%" >
                        <mtw:buttonAction klass="botao" name="voltar"  action="requisicaoRead.do" value="Voltar" />
                    </td>
                    <td width="60%" >
                        <input type="hidden" name="tamanho" id="tamanho" value="<mtw:out value="list.size"/>" />
                    </td>
                </tr>
            </table>
        </div>
    </mtw:form>
</div>