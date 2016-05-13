<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/usuarioplano/listAntigo.js"></script>
<div class="title_bottom">
    <%@include  file="topo.jsp" %>
    <mtw:form method="post" action="usuarioPlanoRead.do" name="listForm">
        <h3>Listar</h3>
        <%@include  file="filtro.jsp" %>
        <div id="content">
            <table width="100%" class="displaytag">
                <mtw:bean value="list">
                    <thead>
                        <tr><th><%@include file="../template/orderby.jsp" %>Usuário</th><th>CPF</th><th>Ação</th></tr>
                    </thead>
                    <tbody>
                        <mtw:list value="data">  
                            <mtw:isEmpty>  
                                <tr class="sub">
                                    <td colspan="4">
                                        Dados Vazios
                                    </td>
                                </tr>
                            </mtw:isEmpty>
                            <mtw:loop var="usuario" counterStart="1" counter="i">  
                                <tr class="even">
                                    <td width="5px" >
                                        <h5 id="button_${i}" class="mais" onclick="buttomMostrarOcultarLinha(${i})" title="Selecione"><mtw:out value="usuario"/></h5>
                                    </td>
                                    <td >
                                        <mtw:out value="cpf"/>
                                    </td>
                                    <td class="acao">
                                        <h5><mtw:out value="usuario${usuario.id}"/></h5>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4" width="100%">
                                        <div  id="linha_<mtw:out value="i"/>" style="display: none">
                                            <table width="100%" class="displaytag2">
                                                <thead>
                                                    <tr><th>Plano</th><th colspan="2">Período</th><th>Status</th></tr>
                                                </thead>
                                                <tbody>
                                                    <mtw:list value="usuario.planos" orderBy="idPlanoUsuario" desc="true">
                                                        <mtw:isEmpty>  
                                                            <tr>
                                                                <td colspan="3">
                                                                    Usuário não possui planos
                                                                </td>
                                                            </tr>
                                                        </mtw:isEmpty>
                                                        <mtw:loop var="plano" counterStart="1" counter="j">  
                                                            <tr id="linha<mtw:out value="i"/>" class="
                                                                <c:choose>
                                                                    <c:when test="${j%2==0}">sub</c:when>
                                                                    <c:otherwise>sub2</c:otherwise>
                                                                </c:choose>
                                                                ">
                                                                <td>
                                                                    <mtw:out value="plano"/>
                                                                </td>
                                                                <td>
                                                                    <mtw:out value="duracaoPlano.duracao"/>
                                                                </td>
                                                                <td>
                                                                    <mtw:out value="usuarioInicio${plano.idPlanoUsuario}" formatter="dateFormatter"/> - 
                                                                    <mtw:out value="usuarioFim${plano.idPlanoUsuario}" formatter="dateFormatter"/>
                                                                </td>
                                                                <td>
                                                                    <mtw:if test="finalizado" value="false">
                                                                        <a id="plano" title='Plano Aberto' href="#">Aberto</a>
                                                                    </mtw:if>
                                                                    <mtw:if test="finalizado" value="true">
                                                                        <a id="quitado" title='Plano Quitado' href="#">Quitado</a>
                                                                    </mtw:if>
                                                                    <mtw:if test="duracaoPlano.duracao" value="Mensal">
<!--                                                                        <a title='Finalizar' href="finalizarPlano.do?id=${plano.idPlanoUsuario}&duracao=Mensal">
                                                                            Finalizar
                                                                        </a>-->
                                                                    </mtw:if>
                                                                    <mtw:out value="usuarioCancelar${plano.idPlanoUsuario}"/>
                                                                </td>
                                                            </tr>
                                                        </mtw:loop>
                                                    </mtw:list>
                                                </tbody>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                                <tr style="height: 6px">
                                    <td colspan="4"></td>
                                </tr>
                            </mtw:loop>  
                        </mtw:list>  
                        <tr>
                            <td align="right" width="100%" colspan="4">
                                <%@include file="../template/paginacaoPaginator.jsp" %>
                            </td>
                        </tr>
                    </tbody>
                </mtw:bean>  
            </table>
        </div>
    </mtw:form>
</div>