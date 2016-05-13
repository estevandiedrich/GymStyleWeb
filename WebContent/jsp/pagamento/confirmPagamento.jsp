<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <table style="width: 410px;height: 210px">
        <tr>
            <td>
                <h1>Pagamento de parcela</h1>
            </td>
        </tr>
        <tr>
            <td class="one" valign="top" >
                <h3></h3>
                <table align="center" style="width: 250px;" class="faixasForm">
                    <tr><td><h4 style="font-size: 13px">Valor de:</h4></td></tr>
                    <tr><td><h3></h3></td></tr>
                    <tr>
                        <td class="one"></td>
                    </tr>
                    <tr>
                        <td class="one"><div id="valorConfirm"></div></td>
                    </tr>
                    <tr><td class="one"><br></td></tr>
                </table>
            </td>
        </tr>
        <tr>
            <td align="left" >
                <div class="errors" id="msgStatusImprimir" style="width: 380px;margin-left: 2px;font-size: 15px">O comprovante não será impresso na catraca!</div><br>
            </td>
        </tr>
    </table>
    <table style="width: 410px;padding-left: 8px">
        <tr>
            <td width="100%">
                <div class="panelButtonForm" >
                    <input type="button" class="botao" onclick="closeShowConfirmPag(false)" value="Cancelar"/>
                    <input type="button" class="botao" onclick="eventoSubmitForm()" value="Confirmar Pagamento"/>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>