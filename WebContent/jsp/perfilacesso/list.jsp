<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/excluir.js" type="text/javascript"></script>

<div class="title_bottom">
    <%@include  file="topo.jsp" %>
    <mtw:form method="post" action="perfilAcessoRead.do" name="listForm">
        <h3>Listar</h3>
        <%@include  file="filtro.jsp" %>
        <div id="content" valign="top">
            <table width="100%" class="displaytag">
                <mtw:bean value="list">
                    <thead>
                        <tr><th><%@include file="../template/orderby.jsp" %>Perfil de Acesso</th><th>Ação</th></tr>
                    </thead>
                    <tbody>
                        <mtw:list value="data">  
                            <mtw:isEmpty>  
                                <tr class="sub">
                                    <td colspan="2">
                                        Dados Vazios
                                    </td>
                                </tr>
                            </mtw:isEmpty>  
                            <mtw:loop var="row" counterStart="0" counter="i">  
                                <tr class="
                                    <c:choose>
                                        <c:when test="${i%2==0}">odd</c:when>
                                        <c:otherwise>even</c:otherwise>
                                    </c:choose>
                                    ">
                                    <td width="85%">
                                        <mtw:out value="perfilAcesso"/>
                                    </td>
                                    <td class="acao">
                                        <a class='update' title='Editar'  href="perfilAcessoUpdate.do?id=${row.id}">Editar</a>
                                        |<a class='delete' title='Excluir'  href="javascript:excluirPerfilAcesso(${row.id});">Excluir</a>
                                    </td>
                                </tr>
                            </mtw:loop>  
                        </mtw:list>  
                        <tr>
                            <td align="right" width="100%" colspan="2">
                                <%@include file="../template/paginacaoPaginator.jsp" %>
                            </td>
                        </tr>
                    </tbody>
                </mtw:bean>  
            </table>
        </div>
    </mtw:form>
</div>