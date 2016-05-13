<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table class="topo" width="100%">
    <tr>
        <td width="85%">
            <h1>Configurações</h1>
        </td>
        <td align="right" id="filtro"  width="15%">
            <div id="item">
                <mtw:hasAuthorization permission="configuracaoRead">
                <a id="list" href="configuracaoRead.do">Listar</a>
                </mtw:hasAuthorization>
<!--                <a href="manuais/ManualGymStyleWeb.odt">Manual</a>-->
<!--                <a id="pdfTopo" href="manuais/ManualGymStyleWeb.pdf" target="blank">Manual</a>-->
            </div>
        </td>
    </tr>
</table>