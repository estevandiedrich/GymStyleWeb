<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/dispositivo/buscarEvento.js"></script>

<div style="width: 400px;height:60px;" class="faixasForm"  >
    <mtw:form action="buscarEventos.do" name="buscarEventos">
        <table width="100%">
            <tr>
                <td>
                    <img src="images/carregando.gif"/>
                </td>
                <td>
                    <label>Buscando Eventos...</label>
                    <input type="hidden" name="id" id="id" value="<mtw:out value="id"/>"/>
                </td>
            </tr>
        </table>
    </mtw:form>
</div>