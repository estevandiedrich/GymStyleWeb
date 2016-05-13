<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/usuarioplano/list.js"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>

<div class="title_bottom">
    <%@include  file="topo.jsp" %>
    <!--mtw:form method="post" action="usuariosPlanoRead.do" name="listForm"-->
    <h3>Listar</h3>
    <%@include  file="filtro.jsp" %>
    <div id="content">
        <%@include file="corpo.jsp" %>
    </div>
    <!--/mtw:form-->
</div>