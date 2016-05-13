<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3></h3>
<mtw:bean value="pojo">
    <table class="form" >
        <tr>
            <td class="one"><mtw:label value="Telefone:"/></td>
            <td>
                <mtw:input  klass="inputTelefone" name="telefone" id="telefone" maxlength="14"/>
                <!--mtw:checkboxes list="mascaraTel" name="mascaraTelefone" id="mascaraTelefone" extra="onclick=eventoMascaraTel()"/-->
            </td>
            <td><font class="errors"><mtw:outError field="telefone" ><mtw:out/></mtw:outError></font></td>
        </tr>
        <tr>
            <td class="one"><mtw:label value="Celular:"/></td>
            <td>
                <mtw:input klass="inputTelefone" name="celular" id="celular" maxlength="14"/>
                <!--mtw:checkboxes list="mascaraCel" name="mascaraCelular" id="mascaraCelular" /-->
            </td>
            <td><font class="errors"><mtw:outError field="celular" ><mtw:out/></mtw:outError></font></td>
        </tr>
        <tr>
            <td class="one"><mtw:label value="Email:"/></td>
        <td><mtw:input klass="input" type="text" name="email" maxlength="60"/></td>
            <td><font class="errors"><mtw:outError field="email" ><mtw:out/></mtw:outError></font></td>
        </tr>
    </table>
</mtw:bean>
