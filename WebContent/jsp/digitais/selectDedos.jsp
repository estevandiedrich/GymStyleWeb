<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<mtw:select klass="selectOptions" id="dedo" name="dedo" list="dedos" emptyField="true" 
            emptyFieldValue="Selecione..." extra="onchange=eventoSelectMao();"  />