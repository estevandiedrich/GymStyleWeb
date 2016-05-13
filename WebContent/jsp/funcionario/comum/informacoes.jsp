<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3></h3>
<mtw:bean value="pojo">
    <table class="form" >
        <tr>
            <td class="one"><mtw:label value="Número de Filhos:"/></td>
            <td><mtw:inputMask klass="inputNumber" name="numeroFilhos" maskCustom="99" textAlign="right"/></td>
            <td><font class="errors"><mtw:outError field="numeroFilhos" ><mtw:out/></mtw:outError></font></td>
        </tr>
        <tr>
            <td class="one"><mtw:label value="Profissão:"/></td>
        <td><mtw:input klass="input" type="text" name="profissao" maxlength="50"/></td>
            <td><font class="errors"><mtw:outError field="profissao" ><mtw:out/></mtw:outError></font></td>
        </tr>
        <tr>
            <td class="one"><mtw:label value="Estado Civil:"/></td>
            <td><mtw:select klass="selectOptions"  name="idEstadoCivil" list="estadosCivis"  emptyField="true" emptyFieldValue="Selecione..."/></td>
            <td></td>
        </tr>
        <tr>
            <td class="one"><mtw:label value="Redes Sociais:"/></td>
            <td><mtw:select klass="selectOptions"  name="idRedeSocial" list="redesSociais" /></td>
            <td></td>
        </tr>
        <tr>
            <td class="one"><mtw:label value="Observação:"/></td>
            <td><mtw:textarea name="observacaoAdd" id="observacaoAdd" cols="85" rows="9" maxlength="254"/></td>
            <td><font class="errors"><mtw:outError field="observacaoAdd" ><mtw:out/></mtw:outError></font></td>
        </tr>
    </table>
</mtw:bean>