<%@include file="../../WEB-INF/imports.jspf" %>
<mtw:isEmpty test="retornoVerificaMatricula" negate="true">
    <mtw:out value="retornoVerificaMatricula"/>
</mtw:isEmpty>
<mtw:input type="hidden" name="retornoVerificaMatricula" value="retornoVerificaMatricula"/>