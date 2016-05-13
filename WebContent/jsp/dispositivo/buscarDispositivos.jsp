<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/dispositivo/buscarDispositivo.js"></script>

<div style="width: 400px;height:60px;" class="faixasForm"  >
    <mtw:form action="dispositivoBuscar.do" name="buscarDispositivos">
        <table width="100%" >
            <tr>
                <td>
                    <img src="images/carregando.gif"/>
                </td>
                <td>
                    <label>Buscando Dispositivos...</label>
                </td>
            </tr>
        </table>
    </mtw:form>
</div>