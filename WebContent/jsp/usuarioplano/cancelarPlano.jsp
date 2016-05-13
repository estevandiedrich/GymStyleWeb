<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/usuarioplano/cancelarPlano.js"></script>

<div style="width:100%" align="left" class="title_bottom">
    <h1>Cancelar Plano</h1>    
    <mtw:form action="cancelarPlano.do" name="formCancelarPlano" klass="faixasForm">
        <br>
        <table width="100%">
            <tr>
                <td class="one">                    
                    <table class="faixasForm" width="97.3%" style="margin-left: 18px">
                        <tr>
                            <td colspan="4">
                                <h3></h3>
                            </td>
                        </tr>
                        <tr>
                            <td class="one" style="width: 10%">
                                <mtw:label klass="obrig" value="Aluno:"/>
                                <input type="hidden" name="idUsuario" value="<mtw:out value="mapa.usuario.id"/>"/>
                                <input type="hidden" name="idPlanoUsuario" value="<mtw:out value="mapa.plano.idPlanoUsuario"/>"/>
                            </td>
                            <td style="width: 30%">
                                <mtw:out value="mapa.usuario.usuario"/>
                            </td>
                            <td class="one" style="width: 25%">
                                <mtw:label klass="obrig" value="Duração do Plano:"/>
                            </td>
                            <td style="width: 35%" align="left">
                                <mtw:out value="mapa.plano.duracaoPlano.duracao"/>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <table width="100%" align="center" class="form">
                        <tr>
                            <td colspan="2" >
                                <div id="planoUsuarioForm">
                                    <%@include file="infoPlano.jsp" %>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                        <font class="errors" ><mtw:out value="cheksErrors"/></font>
                </td>
            </tr>
            <tr>
                <td colspan="2"><%@include file="cancelarPagamentos.jsp" %></td>
            </tr>
        </table>
    </td>
</tr>
<tr>
    <td width="100%" colspan="2">
        <div class="panelButtonForm" >
            <mtw:buttonAction action="usuarioPlanosRead.do?id=${mapa.usuario.id}" klass="botao" name="Voltar" value="Voltar"/>
            <mtw:hasAuthorization permission="cancelarPlano" >
                <mtw:buttonAction klass="botao" name="Cancelar" value="Cancelar Plano" onclick="eventoSubmitForm()"/>
            </mtw:hasAuthorization>
        </div>
    </td>
</tr>
</table>
</mtw:form>
</div>