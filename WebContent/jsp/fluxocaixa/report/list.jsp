<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/fluxocaixa/report/list.js"></script>

<div class="title_bottom">
    <%@include  file="topo.jsp" %>
    <mtw:form action="caixaReportRead.do" name="relatorioCaixaForm" >
        <h3>Fluxo de Caixa Mensal</h3>
        <%@include  file="filtro.jsp" %>
        <div id="content" valign="top" >
            <%@include file="corpo.jsp" %>
        </div>
    </mtw:form>
</div>