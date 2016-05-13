<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<mtw:bean value="paginator" var="pag">
    <table width="100%" class="displaytag">
        <mtw:bean value="list">
            <thead>
                <tr>
                    <c:if test="${aluno}">
                        <th width="10%" >Matrícula</th>
                    </c:if>
                    <th width="50%" >
                        <%@include file="../template/orderbyPaginator.jsp" %><c:if test="${aluno}"><img src="images/alunoPeq.png" title="Aluno!"/> Aluno Ativo</c:if>
                        <c:if test="${aluno==false}"><img src="images/funcPeq.png" title="Funcionário!"/> Funcionário</c:if>
                    </th>
                    <th width="23%" >CPF</th>
                    <th width="7%" >Status</th>
                    <th width="10%" >Ação</th>
                </tr>
            </thead>
            <tbody>
                <mtw:list value="pag.list">
                    <mtw:isEmpty>
                        <tr class="sub">
                            <td colspan="5">
                                Dados não encontrados
                            </td>
                        </tr>
                    </mtw:isEmpty>
                    <mtw:loop var="usuario" counterStart="0" counter="i">
                        <tr class="
                            <c:choose>
                                <c:when test="${i%2==0}">odd</c:when>
                                <c:otherwise>even</c:otherwise>
                            </c:choose>
                            ">
                            <c:if test="${aluno}">
                                <td><mtw:out value="matricula"/></td>
                            </c:if>
                            <td>
                                <mtw:out value="usuario"/>
                            </td>
                            <td>
                                <mtw:out value="cpf"/>
                            </td>
                            <td>
                                <mtw:if test="sincronizado" negate="true">
                                    <img src="images/sinc_error.png" title="Não Sincronizado!"/>
                                </mtw:if>
                                <mtw:if test="sincronizado" negate="false">
                                    <img src="images/sinc_ok.png" title="Sincronizado!"/>
                                </mtw:if>
                            </td>
                            <td>
                                <a id='atualizar' title='Atualizar Aluno na catraca!' href='requisicaoAtualizarUsuario.do?id=${usuario.id}&retorno=requisicaoRead.do?consultaAluno=<c:if test="${aluno}">true</c:if><c:if test="${aluno==false}">false</c:if>'>Atualizar</a>
                            </td>
                        </tr>
                    </mtw:loop>
                    <tr>
                        <td align="right" width="100%" colspan="5">
                            <%@include file="../template/paginacaoPaginator.jsp" %>
                        </td>
                    </tr>
                </mtw:list>
            </tbody>
        </mtw:bean>
    </table>
</mtw:bean>
