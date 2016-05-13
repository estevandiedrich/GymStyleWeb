<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/usuario/comum.js"></script>

<mtw:bean value="pojo">
    <h3></h3>
    <table class="form" width="100%">
        <tr>
            <td class="one" width="20%"><mtw:label klass="obrig" value="Matrícula:"/></td>
            <td>
                <input class="inputTelefone" name="matricula" id="matricula" value="<mtw:out value='matricula'/>" maxlength="9"
                       style="text-align: right" onkeyup="verificaMatricula(this.value);" onkeypress="return somenteNumero(event);" />
            </td>
            <td>
                <mtw:isEmpty test="retornoVerificaMatricula" >
                    <div><font class="errors" id="errorResultMatricula" style="width: 500px;height: 80px"><mtw:outError field="matricula" ><mtw:out/></mtw:outError></font></div>
                </mtw:isEmpty>
                <div id="resultMatricula">
                    <mtw:out value="retornoVerificaMatricula"/>
                    <mtw:input type="hidden" name="retornoVerificaMatricula" value="retornoVerificaMatricula"/>
                </div>
            </td> 
        </tr>
        <tr>
            <td class="one"><mtw:label klass="obrig" value="Nome:"/></td>
            <td><mtw:input klass="input" type="text" name="usuario" id="usuario" maxlength="53"/></td>
            <td colspan="2"><font class="errors"><mtw:outError field="usuario" ><mtw:out/></mtw:outError></font></td>
    </tr>
    <tr>
        <td class="one" width="25%"><mtw:label klass="obrig" value="CPF:"/></td>
    <td width="30%" align="left">
        <table>
            <tr>
                <mtw:if test="pojoInfo.cpf" value="" negate="true">
                    <td><mtw:input klass="inputNumber" name="cpf" id="cpf" type="hidden" />
                        <input class="inputDisabledNumber" name="cpf2" disabled="true" value="<mtw:out value='cpf'/>" style="text-align: right"/></td>
                    </mtw:if>
                    <mtw:if test="pojoInfo.cpf" value="" negate="false">
                    <td><mtw:input klass="inputNumber" name="cpf" id="cpf" extra="onkeyup=verificaCpf(this.value,'aluno');" /></td>
                </mtw:if>
                <mtw:if test="cpf" value="" negate="false">
                    <td><input type="button" name="gerarCPF" onclick="gerarCpf();" class="botao" value="Gerar" style="align: right;width: 50px"/></td>
                    </mtw:if>
            </tr>
        </table>
    </td>
    <td width="45%" align="left">
        <mtw:isEmpty test="retornoVerificaCpf" >
            <div class="errors" id="errorResultCpf" style="width: 100px" ><mtw:outError field="cpf" ><mtw:out/></mtw:outError></div>
        </mtw:isEmpty>
        <mtw:isEmpty test="cpfError" >
    <font class="errors"><mtw:out value="cpfError" /></font>
</mtw:isEmpty>
<mtw:if test="pojoInfo.cpf" value="" negate="false">
    <div id="resultCpf">
        <mtw:out value="retornoVerificaCpf"/>
        <mtw:input type="hidden" name="retornoVerificaCpf" value="retornoVerificaCpf"/>
    </div>
</mtw:if>
</td>
</tr>
<tr>
    <td class="one"><mtw:label klass="obrig" value="Data de Nascimento:"/></td>
    <td>
        <mtw:input name="dataNascimentoFormat" id="dataNascimentoFormat" klass="inputNumber" size="10"/>
    </td>
    <td><font class="errors"><mtw:outError field="dataNascimentoFormat" > <mtw:out/></mtw:outError></font>
</td>
</tr>
<tr>
    <td class="one"><mtw:label klass="obrig" value="Data de Cadastro:"/></td>
    <td>
        <input class="inputDisabledNumber" name="dataCadastroFormat2" disabled="true" value="<mtw:out value='dataCadastroFormat'/>" style="text-align: right"/>
    </td>
    <td><font class="errors"><mtw:outError field="dataCadastroFormat" > <mtw:out/></mtw:outError></font>
</td>
</tr>
<tr>
    <td class="one"><mtw:label value="RG:"/></td>
<td>
    <mtw:input klass="input" type="text" name="rg" maxlength="13"/></td>
<td><font class="errors"><mtw:outError field="rg" > <mtw:out/></mtw:outError></font></td>
</tr>
<tr>
    <td class="one"><mtw:label klass="obrig" value="Sexo:"/></td>
<td><mtw:select klass="selectOptions" name="sexo" list="sexos" emptyField="true" emptyFieldValue="Selecione..."/></td>
<td><font class="errors"><mtw:outError field="sexo" > <mtw:out/></mtw:outError></font></td>
</tr>
</table>
</mtw:bean>
