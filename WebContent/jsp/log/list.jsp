<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/log/list.js"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>

<div class="title_bottom">
    <%@include  file="topo.jsp" %>
    <!--mtw:form method="post" action="logRead.do" name="listForm"-->
    <mtw:bean value="paginator" var="pag">
        <h3>Listar</h3>
        <%@include  file="filtro.jsp" %>
        <div id="content" valign="top">
            <%@include file="corpo.jsp" %>
        </div>
    </mtw:bean>
    <!--/mtw:form-->
</div>