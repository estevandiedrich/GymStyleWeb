<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table class="topo" width="100%">
    <tr>
        <td  width="75%">
            <h1>Planos</h1>
        </td>
        <td align="right" id="filtro"  width="25%">
            <div id="item">
                <mtw:hasAuthorization permission="planoRead" >
                    <a id="list" href="planoRead.do">Listar</a>
                    <mtw:hasAuthorization permission="planoManager" >
                        <a id="novo" href="planoCreate.do">Novo</a>
                    </mtw:hasAuthorization>
                </mtw:hasAuthorization>
            </div>
        </td>
    </tr>
</table>