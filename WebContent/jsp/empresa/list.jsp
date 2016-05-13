<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="title_bottom">
    <%@include  file="topo.jsp" %>
    <h3>Listar</h3>
    <div id="content" valign="top">
        <table width="100%" class="displaytag">
            <thead><tr><th>Razão Social</th><th>Nome Fantasia</th><th>Ação</th></tr></thead>
            <tbody>
                <mtw:bean value="pojo">
                    <tr class="even">
                        <td width="50%" ><mtw:out value="razaoSocial"/></td>
                        <td width="40%" ><mtw:out value="nomeFantasia"/></td>
                        <td width="10%">
                            <mtw:hasAuthorization permission="empresaManager">
                                <a class='update' title='Editar'  href="empresaUpdate.do?id=1">Editar</a>
                            </mtw:hasAuthorization>
                        </td>
                    </tr>
                </mtw:bean>
            </tbody>
        </table>
    </div>
</div>