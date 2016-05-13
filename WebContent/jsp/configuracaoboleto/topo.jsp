<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table class="topo" width="100%">
    <tr>
        <td width="85%">
            <h1>Configurações Boleto</h1>
        </td>
        <td align="right" id="filtro"  width="15%">
            <div id="item">
                <mtw:hasAuthorization permission="configuracaoBoletoRead">
                    <a id="list" href="configuracaoBoletoRead.do">Listar</a>
                </mtw:hasAuthorization>
            </div>
        </td>
    </tr>
</table>