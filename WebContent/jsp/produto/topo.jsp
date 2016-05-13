<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table class="topo" width="100%">
    <tr>
        <td  width="80%">
            <h1>Produto</h1>
        </td>
        <td align="right" id="filtro"  width="20%">
            <div id="item">
                <mtw:hasAuthorization permission="produtoRead" >
                    <a id="list" href="produtoRead.do">Listar</a>
                    <mtw:hasAuthorization permission="produtoCreate" >
                        <a id="novo" href="produtoCreate.do">Novo</a>
                    </mtw:hasAuthorization>
                </mtw:hasAuthorization>
            </div>
        </td>
    </tr>
</table>