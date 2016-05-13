<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div style="width: 400px;height: 70px;">
    <table class="faixasForm" width="100%" height="100%">
        <tr>
            <td>
                <img src="images/carregando.gif"/>
            </td>
            <td>
                <label>Capturando digital na catraca...</label>
                <input type="hidden" name="idRequisicao" id="idRequisicao" value="<mtw:out value="idRequisicao"/>"/>
            </td>
        </tr>
    </table>
</div>
