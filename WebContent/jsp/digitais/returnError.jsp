<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="faixasForm" style="width: 400px;height: 130px;">
    <h1 class="menos"  title="Fechar" onclick="fecharRetorno();" >Fechar</h1>
    <table class="form" style="width: 100%;">
        <tr>
            <td>
                <h1 class="error">Não foi possível capturar digital na catraca!</h1>                
                <input type="hidden" name="resultErro" id="resultErro" value="true"/>
            </td>
        </tr>
        <tr>
            <td><font class="errors"><mtw:out  value="error" /></font></td>
        </tr>
    </table>
</div>
