<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/usuario/list.js"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>

<div class="title_bottom">
    <%@include  file="topo.jsp" %>
    <!--mtw:form method="post" action="usuarioRead.do" name="listForm"-->
    <div>
        <h3>Listar</h3>
        <%@include  file="filtro.jsp" %>
        <div id="content">
            <%@include file="corpo.jsp" %>
        </div>
    </div>
    <!--/mtw:form-->
</div>