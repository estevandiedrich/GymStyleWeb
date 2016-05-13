<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table class="topo" width="100%">
    <tr>
        <td width="75%">
            <h1>Catracas</h1>
        </td>
        <td align="right" id="filtro" width="25%">
            <div id="item">
                <mtw:hasAuthorization permission="dispositivoRead">
                    <a id="list" href="dispositivoRead.do" title="Listar Dispositivos!">Listar</a>
                    <mtw:hasAuthorization permission="dispositivoManager">
                        <a id="novo" href="dispositivoBuscar.do" title="Buscar Dispositivos na Rede!">Buscar</a>
                    </mtw:hasAuthorization>
                </mtw:hasAuthorization>
            </div>
        </td>
    </tr>
</table>