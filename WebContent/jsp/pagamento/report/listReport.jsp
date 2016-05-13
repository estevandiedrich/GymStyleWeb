<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/pagamento/report/listReport.js"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>

<div class="title_bottom">
    <%@include  file="topo.jsp" %>
    <mtw:form action="pagamentoReportRead.do" name="relatorioForm" >
        <h3>Listar</h3>
        <%@include  file="filtroReport.jsp" %>
        <div id="content" valign="top" >
            <%@include file="corpoPagamento.jsp" %>
        </div>
    </mtw:form>
</div>