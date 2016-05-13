<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table class="faixasForm" width="98%" height="40px">
    <tr>
        <td class="one">
            <div id="resultConfigurar">
                <br><br>
                <table>
                    <tr>
                        <td><img src="images/error.png"/></td>
                        <td>
                            Não Sincronizado - Configurar Catraca
                            <a id='atualizar' title="Reenviar?" href="#" onclick="reenviarRequisicaoConfigurar(<mtw:out value="idRequisicao"/>);">Reenviar</a>
                        </td>
                    </tr>
                </table>
                <br><br>
            </div>
        </td>
    </tr>
</table>
<input type="hidden" name="contador" id="contador" value="<mtw:out value="contador"/>"/>
<input type="hidden" name="idRequisicao" id="idRequisicao" value="<mtw:out value="idRequisicao"/>"/>
<input type="hidden" name="idDispositivo" id="idDispositivo" value="<mtw:out value="idDispositivo"/>"/>