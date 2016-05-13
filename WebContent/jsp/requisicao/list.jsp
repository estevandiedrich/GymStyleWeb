<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/requisicao/list.js"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>

<div class="title_bottom">
    <%@include  file="topo.jsp" %>
    <!--mtw:form method="post" action="requisicaoRead.do" name="listForm"-->
    <h3>Listar</h3>
    <table width="100%">
        <tr><td class="title_bottom"><%@include  file="filtro.jsp" %></td></tr>
        <tr>
            <td><div id="content"><%@include file="corpo.jsp" %></div></td>
        </tr>
    </table>
    <!--/mtw:form-->
</div>