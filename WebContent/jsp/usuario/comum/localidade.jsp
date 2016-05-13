<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3></h3>
<mtw:bean value="pojo">
    <table class="form" >
        <tr>
            <td class="one"><mtw:label value="Endereço:"/></td>
            <td><mtw:input klass="input" type="text" name="endereco" maxlength="255"/></td>
            <td><font class="errors"><mtw:outError field="endereco" ><mtw:out/></mtw:outError></font></td>
        </tr>
        <tr>
            <td class="one"><mtw:label value="Complemento:"/></td>
        <td><mtw:input klass="input" type="text" name="complemento" maxlength="40"/></td>
            <td><font class="errors"><mtw:outError field="complemento" ><mtw:out/></mtw:outError></font></td>
        </tr>
        <tr>
            <td class="one"><mtw:label value="Bairro:"/></td>
        <td><mtw:input klass="input" type="text" name="bairro" maxlength="60"/></td>
            <td><font class="errors"><mtw:outError field="bairro" ><mtw:out/></mtw:outError></font></td>
        </tr>
        <tr>
            <td class="one"><mtw:label value="Cidade:"/></td>
        <td><mtw:input klass="input" type="text" name="cidade" maxlength="100"/></td>
            <td><font class="errors"><mtw:outError field="cidade" ><mtw:out/></mtw:outError></font></td>
        </tr>
        <tr>
            <td class="one"><mtw:label value="Estado:"/></td>
            <td><mtw:select klass="selectOptions" name="uf" list="estados" emptyField="true" emptyFieldValue="Selecione..."/></td>
            <td><font class="errors"><mtw:outError field="uf" ><mtw:out/></mtw:outError></font></td>
        </tr>
        <tr>
            <td class="one"><mtw:label value="CEP:"/></td>
            <td><mtw:input klass="inputNumber" name="cep" id="cep"  /></td>
            <td><font class="errors"><mtw:outError field="cep" ><mtw:out/></mtw:outError></font></td>
        </tr>
    </table>
</mtw:bean>