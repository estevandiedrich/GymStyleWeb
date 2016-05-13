<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/pagamento/autenticacao/list.js"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>

<div class="title_bottom">
    <table class="topo" width="100%">
        <tr>
            <td  width="100%">
                <h1>Módulo de Autenticação de Recibo de Pagamento</h1>
            </td>
        </tr>
    </table>    
    <!--mtw:form method="post" action="autenticacaoPagamentoRead.do" name="listForm"-->
    <h3>Listar</h3>
    <div>
        <table width="100%" >
            <tr>
                <td align="left" width="31%" class="one">
                    <mtw:label klass="obrig" value="Número de Autenticação:" />
                </td>
                <td align="left" width="30%">
                    <mtw:input type="text" klass="inputAutenticacao" name="criterioAutenticacao" id="criterioAutenticacao" 
                               extra="onkeyup=consultaAutenticacao()" maxlength="70" />
                </td>
                <td class="one"></td><td></td>
                <td align="left"width="10%">
                    <mtw:buttonAction id="filtrar" value="Filtrar" name="filtrar" onclick="submitFormAutenticacao()"/>
                </td>
                <td align="left"width="10%"></td>
            </tr>
        </table>
    </div>
    <div id="content">
        <%@include file="corpo.jsp" %>
    </div>
    <!--/mtw:form-->
</div>