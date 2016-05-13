<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<mtw:if test="planos.size" value="0" negate="true">
    <mtw:hasAuthorization permission="requisicaoManager" >
        <mtw:if test="sincronizado" negate="true">
            <a href="requisicaoAtualizarUsuario.do?id=<mtw:out value="id"/>">
                <img src="images/sinc_error.png" title="Não Sincronizado. Selecione para sincronizar!" style="cursor: pointer"/>
            </a>
        </mtw:if>
        <mtw:if test="sincronizado" negate="false">
            <a href="requisicaoAtualizarUsuario.do?id=<mtw:out value="id"/>">
                <img src="images/sinc_ok.png" title="Sincronizado! Selecione para sincronizar!" style="cursor: pointer"/>
            </a>
        </mtw:if>
    </mtw:hasAuthorization>
</mtw:if>