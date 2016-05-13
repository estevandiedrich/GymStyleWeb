<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div style="width: 400px;height: 70px;">
    <table class="faixasForm" width="100%" height="100%">
        <tr>
            <td><img src="images/carregando.gif"/></td>
            <td>
                <label>Sincronizando...</label>
                <input type="hidden" name="idRequisicao" id="idRequisicao" value="<mtw:out value="idRequisicao"/>"/>
                <input type="hidden" name="idPlanoUsuario" id="idPlanoUsuario" value="<mtw:out value="idPlanoUsuario"/>"/>
                <input type="hidden" name="contador" id="contador" value="<mtw:out value="contador"/>"/>
            </td>
        </tr>
    </table>
</div>
