<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<input type='checkbox' <mtw:if test="pag.orderBy" value="true">checked="true"</mtw:if>  name='orderBy'
       value='1' id="orderBy" class="orderBy" onclick="submitFormRead()" />
    <label for="orderBy" style="background-color: #fff;" title="<mtw:out value="pag.order"/>"></label>