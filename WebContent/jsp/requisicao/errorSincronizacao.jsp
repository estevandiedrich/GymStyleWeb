<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table width="100%">
    <tr>
        <td align="center"><div id='sinc_error' style="width: 100px" title='Não Sincronizado!' ><mtw:out value="mensagem"/></div></td>
    </tr>
    <tr>
        <td align="center"><a id='atualizar' title="Reenviar?"  href="#" onclick="reenviarRequisicao(<mtw:out value="idRequisicao"/>);">Reenviar</a></td>
    </tr>
</table>

