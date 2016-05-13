<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/usuario/planos.js"></script>

<!-- Para usar a mascara de moedas é necessário usar estas duas libs-->
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/maskMoney.js" type="text/javascript"></script>
<!-- Para usar a mascara de moedas é necessário usar estas duas libs-->

<div style="width:100%" align="left">
    <h3></h3>
    <table width="95%" align="center" class="faixasForm">
        <tr>
            <td colspan="3">
                <h1>Vincular Plano</h1>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <mtw:if test="planosVazios" >
                    <div style="padding-left: 20px;">
                        <table class="faixasForm" width="70%" style="height: 40px">
                            <tr>
                                <td class="faixasForm" align="center" ><font class="errors">É necessário cadastrar planos para vincular</font></td>
                            <td class="faixasForm" align="center" style="background-color: #FFF"><a id="novo" href="planoCreate.do">Cadastrar Plano</a></td>
                            </tr>
                        </table>
                        <br>
                    </div>
                </mtw:if>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <mtw:input name="post" type="hidden" /><!-- Somente para segurar o post de vincular plano no cadastro de usuario; Sempre vai ser vazio-->
        <font class="errors"><mtw:outError field="post" ><mtw:out/></mtw:outError></font>
        </td>
        </tr>
        <tr>
            <td>
                <div class="faixasForm">
                    <h3></h3>
                    <table width="100%" align="center" style="height: 30px;padding-bottom: 6px" class="form">
                        <tr>
                            <td width="10%"  align="left"><mtw:label klass="obrig" value="Plano:"/></td>
                            <td width="35%"  align="left">
                                <mtw:select name="planoSelect" id="planoSelect" list="planos" klass="selectOptions"
                                            extra="onchange=refreshImportarPlanoUsuario();eventoSelectPlano();"
                                            emptyField="true" emptyFieldValue="Selecione..." />
                            </td>
                            <td width="55%" align="left">
                        <font class="errors"><mtw:outError field="planoSelect" ><mtw:out/></mtw:outError></font>
                        </td>
                        </tr>
                    </table>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="3" >
                <div id="planoUsuarioForm" style="display: none">
                    <%@include file="infoPlanoUsuario.jsp" %>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2" >
                <table width="100%">
                    <tr>
                        <td width="50%">
                            <table class="faixasForm" style="height: 150px" width="100%">
                                <tr>
                                    <td class="one" width="50%"><mtw:label klass="obrig" value="Dia de Vencimento:"/></td>
                                    <td align="left" width="40%"><mtw:input name="diaVencimento"  id="diaVencimento" value="10" klass="inputTelefone" extra="onkeyup=verificaMaximo(this.name,30);" maxlength="3"/></td>
                                    <td align="left" width="10%"><font class="error"><mtw:outError field="diaVencimento" ><mtw:out/></mtw:outError></font></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value='Tolerância(<span style="font-size: 12px;font-style: italic">Dias<span>):'/></td>
                        <td><mtw:input name="tolerancia"  id="tolerancia" value="0" klass="inputTelefone" extra="onkeyup=verificaMaximo(this.name,30);" maxlength="3"/></td>
                        <td><font class="error"><mtw:outError field="tolerancia" ><mtw:out/></mtw:outError></font></td>
        </tr>
        <tr>
            <td class="one" ><mtw:label klass="obrig" value="Duração do Plano:"/></td>
            <td>
                <mtw:select name="periodoSelect" id="periodoSelect" list="periodos" klass="selectOptions" style="width: 171px;" defValue="3" />
            </td>
            <td>
        <font class="errors"><mtw:outError field="periodoSelect" ><mtw:out/></mtw:outError></font>
        </td>
        </tr>
    </table>
</td>
<td valign="top"  width="50%">
    <div class="faixasForm" style="width: 94%; height:150px">
        <table width="100%">
            <tr>
                <td align="center"><br>
                    <mtw:label klass="obrig" value="Forma de Desconto: "/>
                    <input type="radio" name="formasDeDesconto" <mtw:if test="formasDeDesconto" value="R$">checked="true"</mtw:if> onchange="formaDeDesconto(1)" value="R$"/>R$
                    <input type="radio" name="formasDeDesconto" <mtw:if test="formasDeDesconto" value="%">checked="true"</mtw:if> onchange="formaDeDesconto(2)" value="%"/>%
                </td>
            </tr>
        </table>
        <table width="100%">
            <tr id="descontoUm" style="display: <mtw:if test="formasDeDesconto" value="R$">inline</mtw:if><mtw:if test="formasDeDesconto" value="%">none</mtw:if>">
                <td class="one"><mtw:label klass="obrig" value="R$:"/></td>
                <td align="left">
                    <mtw:input name="descontoReal"  id="descontoReal" value="0" klass="inputTelefone" maxlength="11"/>
                </td>
            </tr>
            <tr id="descontoDois" style="display: <mtw:if test="formasDeDesconto" value="R$">none</mtw:if><mtw:if test="formasDeDesconto" value="%">inline</mtw:if>">
                <td class="one"><mtw:label klass="obrig" value="%:"/></td>
                <td align="left"><mtw:input name="descontoPercentual"  id="descontoPercentual" value="0" klass="inputTelefone" extra="onkeyup=verificaMaximo(this.name,100);" /></td>
            </tr>
        </table>
    </div>
</td>
</tr>
</table>
</td>
</tr>
<tr>
    <td colspan="2"><div id="divPagamentos" style="display: none"><%@include file="pagamentos.jsp" %></div></td>
</tr>
</table>
</div>