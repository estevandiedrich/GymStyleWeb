<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table class="topo" width="100%">
    <tr>
        <td  width="85%">
            <h1>Modalidade</h1>
        </td>
        <td align="right" id="filtro"  width="15%">
            <div id="item">
                <mtw:hasAuthorization permission="modalidadeRead">
                    <a id="list" href="modalidadeRead.do">Listar</a>
                    <mtw:hasAuthorization permission="modalidadeManager">
                        <a id="novo" href="modalidadeCreate.do">Novo</a>
                    </mtw:hasAuthorization>
                </mtw:hasAuthorization>
            </div>
        </td>
    </tr>
</table>