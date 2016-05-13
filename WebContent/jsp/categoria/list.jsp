<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/categoria/list.js"></script>

<div class="title_bottom">
    <%@include  file="topo.jsp" %>
    <!--mtw:form method="post" action="categoriaRead.do" name="listForm"-->
    <h3>Listar</h3>
    <%@include  file="filtro.jsp" %>
    <div id="content" valign="top">
        <%@include file="corpo.jsp" %>
    </div>
    <!--/mtw:form-->
</div>