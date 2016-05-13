<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="style/styleModal.css" type="text/css"/>

<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>
<script src="js/pagamento/list.js"></script>

<div id="windowAbrirCaixa" ><div id="statusAbrirCaixa"> <%@include file="../fluxocaixa/abrirCaixaForm.jsp" %> </div></div>
<div class="title_bottom">
    <%@include  file="topo.jsp" %>
    <mtw:form method="post" action="pagamentoRead.do" name="listForm">
        <mtw:hasAuthorization permission="managerFluxoCaixa">
            <c:if test="${idFluxoCaixa==0}">
                <table width="100%">
                    <tr>
                        <td width="100%" align="center">
                            <div class="msgCaixaFechado" align="center" style="font-style: italic;margin: 5px;width: 600px">
                                <font class="errors" >Caixa fechado. É necessário abrir o caixa para efetuar pagamento!</font>
                            </div>
                        </td>
                        <td align="right" id="filtro"  width="0%">
                        </td>
                    </tr>
                </table>
            </c:if>
        </mtw:hasAuthorization>
        <h3>Listar</h3>
        <%@include  file="filtro.jsp" %>
        <div id="content">
            <%@include file="corpo.jsp" %>
        </div>
    </mtw:form>
</div>