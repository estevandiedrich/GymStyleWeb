<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<br>
<table width='100%' height='100%'>
    <tr>
        <td>
            <img src="images/error.png"/>
        </td>
        <td>
            Não Sincronizado - Configurar Catraca
            <a id='atualizar' title="Reenviar?" href="#" onclick="reenviarRequisicaoConfigurar(<mtw:out value="idRequisicaoConfigurar"/>);">Reenviar</a>
            <input type='hidden' name='contadorConfigurar' id='contadorConfigurar' value='<mtw:out value="contadorConfigurar"/>'/>
            <input type='hidden' name='idRequisicaoConfigurar' id='idRequisicaoConfigurar' value='<mtw:out value="idRequisicaoConfigurar"/>'/>
        </td>
    </tr>
    <tr>
        <td>
            <br><br>
        </td>
    </tr>
</table>