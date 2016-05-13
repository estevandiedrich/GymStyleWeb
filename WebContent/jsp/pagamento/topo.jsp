<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<table width="100%">
    <tr>
        <td  width="75%">
            <h1>Pagamentos</h1>
        </td>
        <td align="center" id="filtro"  width="12%">
            <div id="item">
                <a id="list" href="pagamentoRead.do">Listar</a>
            </div>
        </td>
        <td align="right" width="13%">
            <c:if test="${idFluxoCaixa==0}">
                <mtw:hasAuthorization permission="managerFluxoCaixa">
                    <mtw:hasAuthorization permission="abrirFluxoCaixa">
                        <div id="item">
                            <div onclick="showAbrirCaixa()" id="abrirCaixaMsg" >Abrir Caixa</div>
                        </div>
                    </mtw:hasAuthorization>
                </mtw:hasAuthorization>
            </c:if>
        </td>
    </tr>
</table>