<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/venda/usuarioAjax.js"></script>

<div id="windowConsultaUsuario">
    <div class="faixasForm" id="statusConsultaUsuario">
        <h1>Alunos / Funcionários</h1>
        <h3>Listar</h3>
        <table width="90%"  class="faixasForm">
            <tbody>
                <tr class="sub">
                    <td align="left" width="10%" class="one"><mtw:label klass="obrig" value="Nome:" /></td>
                    <td align="left" width="30%">
                        <mtw:input type="text" klass="input" name="criterioNome" id="criterioNome" extra="onkeyup=consultaUsuarios()" maxlength="70"/>
                    </td>
                    <td align="left" width="10%"><mtw:buttonAction id="filtrar" value="Filtrar" name="filtrar" onclick="consultaUsuarios()"/></td>
                    <td align="left" width="50%"></td>
                </tr>
            </tbody>
        </table>
        <div id="usuarioAjax"><%@include file="usuarios.jsp" %></div>
        <table width="100%" >
            <tr>
                <td width="100%" class="panelButtonForm">
                    <input type="button" onclick="javascript:closeShowConsultaUsuario()" class="botao" name="Fechar" value="Fechar"/>
                </td>
            </tr>
        </table>
    </div>
</div>