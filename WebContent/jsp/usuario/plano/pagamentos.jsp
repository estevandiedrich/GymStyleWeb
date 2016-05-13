<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="faixasForm">
    <h3></h3>
    <mtw:buttonAction id="gerar" name="gerar" value="Gerar Pagamentos" onclick="refreshTabelaPagamentos();" title="Selecione Duração do Plano para gerar parcelas!" />
    <font class="errors"><mtw:outError field="tamanhoLista" ><mtw:out/></mtw:outError></font>
    <div id="errorDuracao"></div>
    <div id="tabelaPagamentos"></div>
</div>