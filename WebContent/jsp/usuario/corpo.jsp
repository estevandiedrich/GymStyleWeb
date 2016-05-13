<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/excluir.js" type="text/javascript"></script>
<mtw:bean value="paginator" var="pag">
    <table width="100%" class="displaytag">
        <thead>
            <tr>
                <th width="3%" >-</th>
                <th width="7%" title="Matrícula!" style="font-size: 10px">Matrícula</th>
                <th width="31%"><%@include file="../template/orderbyPaginator.jsp" %>Aluno</th>
                <th width="13%">CPF</th><th width="13%">Telefone</th><th width="13%">Celular</th><th width="14%">Ação</th>
            </tr>
        </thead>
        <tbody>
            <mtw:bean value="pag.list">
                <mtw:list value="pag.list">
                    <mtw:isEmpty>
                        <tr class="sub"><td colspan="8">Dados Vazios</td></tr>
                    </mtw:isEmpty>
                    <mtw:loop var="usuario" counterStart="0" counter="i">
                        <tr class="
                            <c:choose>
                                <c:when test="${i%2==0}">odd</c:when>
                                <c:otherwise>even</c:otherwise>
                            </c:choose>
                            ">
                            <td><%@include file="../requisicao/link.jsp" %></td>
                            <td><label><mtw:out value="matricula" max="40"/></label></td>
                            <td><label title="<mtw:out value="usuario"/>"><mtw:out value="usuario" max="25"/></label></td>
                            <td><mtw:out value="cpf"/></td>
                            <td><mtw:out value="telefone"/></td>
                            <td><mtw:out value="celular"/></td>
                            <td>
                                <mtw:hasAuthorization permission="usuarioManager" >
                                    <a class='update' title='Editar' href="usuarioUpdateInformacao.do?id=${usuario.id}">Editar</a>
                                </mtw:hasAuthorization>
                                <mtw:if test="ativo"></mtw:if>
                                <c:if test="${usuario.id!=1}" >
                                    <c:if test="${criterioAtivoAluno}">
                                        <mtw:hasAuthorization permission="usuarioDelete" >| <a class='delete' title='Excluir' href="javascript:excluirUsuario(${usuario.id},<mtw:if test="planos.size" value="0" >false</mtw:if><mtw:if test="planos.size" value="0" negate="true">true</mtw:if>);">Excluir</a></mtw:hasAuthorization>
                                    </c:if>                                    
                                </c:if>
                            </td>
                        </tr>
                    </mtw:loop>
                    <tr>
                        <td align="right" width="100%" colspan="7">
                            <%@include file="../template/paginacaoPaginator.jsp" %>
                        </td>
                    </tr>
                </mtw:list>
            </mtw:bean>
        </tbody>
    </table>
</mtw:bean>
