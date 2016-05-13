<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/avaliacaofisica/usuarioList.js"></script>

<div class="title_bottom">
    <%@include file="topo.jsp" %>
    <mtw:form method="post" action="usuarioAvaliacaoFisicaRead.do" name="listForm">
        <h3>Listar</h3>
        <%@include file="filtro.jsp" %>
        <div id="content" valign="top">
            <%@include file="corpo.jsp" %>
        </div>
    </mtw:form>
</div>