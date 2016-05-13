<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<br>
<table width='400px' height='100%' >
    <tr>
        <td>
            <img src='images/carregando.gif'/>
        </td>
        <td align="left">
            <label>Sincronizando Configurar Catraca...</label>
            <input type='hidden' name='contadorConfigurar' id='contadorConfigurar' value='<mtw:out value="contadorConfigurar"/>'/>
            <input type='hidden' name='idRequisicaoConfigurar' id='idRequisicaoConfigurar' value='<mtw:out value="idRequisicaoConfigurar"/>'/>
        </td>
    </tr>
    <tr>
        <td>
            <br>
        </td>
    </tr>
</table>