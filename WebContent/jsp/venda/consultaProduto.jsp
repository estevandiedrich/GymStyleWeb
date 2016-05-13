<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/venda/produtoAjax.js"></script>

<div id="windowConsultaProduto">
    <div class="faixasForm" id="statusConsultaProduto">
        <h1>Produtos</h1>
        <h3>Listar</h3>
        <table width="90%"  class="faixasForm">
            <tbody>
                <tr class="sub">
                    <td align="left" width="10%" class="one"><mtw:label klass="obrig" value="Código:" /></td>
                    <td align="left" width="20%">
                        <mtw:input type="text" klass="inputNumber" name="criterioCodigo" id="criterioCodigo" extra="onkeyup=consultaProdutos()" maxlength="70"/>
                    </td>
                    <td align="left" width="10%" class="one"><mtw:label klass="obrig" value="Nome:" /></td>
                    <td align="left" width="30%">
                        <mtw:input type="text" klass="input" name="criterioNome" id="criterioNomeProduto" extra="onkeyup=consultaProdutos()" maxlength="70"/>
                    </td>
                    <td align="left" width="10%"><mtw:buttonAction id="filtrar" value="Filtrar" name="filtrar" onclick="consultaProdutos()"/></td>
                    <td align="left" width="20%"></td>
                </tr>
            </tbody>
        </table>
        <div id="produtosAjax"><%@include file="produtos.jsp" %></div>
        <table width="100%" >
            <tr>
                <td width="100%" class="panelButtonForm">
                    <input type="button" onclick="javascript:closeShowConsultaProduto()" class="botao" name="Fechar" value="Fechar"/>
                </td>
            </tr>
        </table>
    </div>
</div>