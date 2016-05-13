<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="title_bottom">
    <h1>Aluno / Planos</h1>
    <mtw:form action="usuariosPlanoRead.do">
        <div class="faixasForm">
            <div class="faixasForm">
                <table width="100%" class="displaytag">
                    <mtw:bean value="usuario">
                        <thead>
                            <tr><th>Aluno</th><th>CPF</th><th>Status</th></tr>
                        </thead>
                        <tr class="even">
                            <td width="30%">
                                <mtw:out value="usuario"/>
                                <input type="hidden" name="id" value="<mtw:out value="usuario.id" />"/>
                            </td>
                            <td width="40%">
                                <mtw:out value="cpf"/>
                            </td>
                            <td width="30%">
                                <mtw:hasAuthorization permission="vincularPlano" >
                                    <mtw:out value="usuarioVincular${usuario.id}"/>
                                </mtw:hasAuthorization>
                            </td>
                        </tr>
                    </mtw:bean>
                </table>
            </div>
            <div class="faixasForm">
                <table width="100%" class="displaytag">
                    <mtw:bean value="list">
                        <thead>
                            <tr><th>Plano</th><th>Início</th><th>Término</th><th>-</th></tr>
                        </thead>
                        <tbody>
                            <mtw:list value="list" orderBy="idPlanoUsuario" desc="true">
                                <mtw:isEmpty>  
                                    <tr class="sub">
                                        <td colspan="5">
                                            Dados Vazios
                                        </td>
                                    </tr>
                                </mtw:isEmpty>
                                <mtw:loop var="plano" counterStart="1" counter="i">  
                                    <tr class="
                                        <c:choose>
                                            <c:when test="${i%2!=0}">odd</c:when>
                                            <c:otherwise>even</c:otherwise>
                                        </c:choose>
                                        ">
                                        <td width="30%" class="one" >
                                            <mtw:hasAuthorization permission="planoManager" >
                                                <a class="info" href="planoUpdate.do?id=<mtw:out value="id"/>" title="Visualizar Plano <mtw:out value="plano" />!" ><mtw:out value="plano" max="20"/></a>
                                            </mtw:hasAuthorization>
                                        </td>
                                        <td  width="20%" >
                                            <mtw:out value="planoInicio${plano.idPlanoUsuario}" formatter="dateFormatter"/>
                                        </td>
                                        <td  width="20%" >
                                            <mtw:out value="planoFim${plano.idPlanoUsuario}" formatter="dateFormatter"/>
                                        </td>
                                        <td  width="30%" >
                                            <mtw:hasAuthorization permission="verPlanoUsuario" >
                                                <mtw:out value="planoPagar${plano.idPlanoUsuario}"/>
                                            </mtw:hasAuthorization>
                                            <mtw:out value="planoCancelado${plano.idPlanoUsuario}"/>
                                            <mtw:out value="planoQuitado${plano.idPlanoUsuario}"/>
                                            <mtw:hasAuthorization permission="cancelarPlano" >
                                                <mtw:out value="planoCancelar${plano.idPlanoUsuario}"/>
                                            </mtw:hasAuthorization>
                                        </td>
                                    </tr>
                                </mtw:loop>  
                            </mtw:list>  
                        </tbody>
                    </mtw:bean>  
                </table>
            </div>
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