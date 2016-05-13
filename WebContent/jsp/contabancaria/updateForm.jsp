<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="style/styleModal.css" type="text/css"/>
<script type="text/javascript" src="js/contabancaria/bancoAjax.js"></script>

<div class="title_bottom">
    <%@include file="topo.jsp" %>
    <h3>Editar</h3>
    <mtw:form method="post" action="contaBancariaUpdate.do">
        <mtw:bean value="pojo">
            <table width="80%" class="faixasForm">
                <tr><td><br></td></tr>
                <tr>
                    <td class="one" width="30%" >
                        <mtw:label klass="obrig" value="Banco:"/>
                        <mtw:input klass="input" type="hidden" name="id" id="id"/>
                        <mtw:input type="hidden" name="bancoId" id="bancoId" />
                    </td>
                    <td width="29%" ><mtw:input klass="inputDisabledSelected" extra="readonly='true'" type="text" name="bancoNome" id="bancoNome" value="Clique em Selecionar ..."/></td>
                    <td width="23%" ><input type="button" id="filtrar"  value="Selecionar" name="filtrar" style="width:90px" onclick="javascript:showBuscarBancos()"/></td>
                    <td width="23%" ><font class="errors"><mtw:outError field="bancoId" ><mtw:out/></mtw:outError></font></td>
                </tr>
                <tr>
                    <td class="one"><mtw:label klass="obrig" value="Agência:"/></td>
                    <td><mtw:input klass="input" type="text" name="agencia" id="agencia" maxlength="10"/></td>
                    <td ><font class="errors"><mtw:outError field="agencia" ><mtw:out/></mtw:outError></font></td>
                </tr>
                <tr>
                    <td class="one"><mtw:label klass="obrig" value="Número da Conta:"/></td>
                    <td><mtw:input klass="input" type="text" name="numeroConta" id="numeroConta" maxlength="20"/></td>
                    <td><font class="errors"><mtw:outError field="numeroConta" ><mtw:out/></mtw:outError></font></td>
                </tr>
                <tr>
                    <td class="one"><mtw:label klass="obrig" value="Titular:"/></td>
                    <td><mtw:input klass="input" type="text" name="titular" id="titular" maxlength="52"/></td>
                    <td ><font class="errors"><mtw:outError field="titular" ><mtw:out/></mtw:outError></font></td>
                </tr>
                <tr>
                    <td><br></td>
                </tr>
            </table>
            <table width="100%">
                <tr>
                    <td width="100%" class="panelButtonForm">
                        <mtw:buttonAction action="contaBancariaRead.do" klass="botao" name="Cancelar" value="Cancelar"/>
                        <mtw:submit klass="botao" value="Salvar"/>
                    </td>
                </tr>
            </table>
            <%@include  file="windowBuscarBancos.jsp" %>
        </mtw:bean>
    </mtw:form>
</div>