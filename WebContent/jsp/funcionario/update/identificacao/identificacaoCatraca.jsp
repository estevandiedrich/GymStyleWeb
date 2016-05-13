<%@include file="../../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<table width="100%" >
    <tr>
        <td style="padding-bottom: 10px;padding-left: 20px">
    <font class="errors" ><mtw:out value="msgDigitais"/></font>
</td>
</tr>
<tr>
    <td width="100%" valign="top" class="one">
        <div class="title_bottom">
            <h1>Digitais</h1>
            <fieldset>
                <legend></legend>
                <div>
                    <%@include file="../../../digitais/digitais.jsp" %>
                </div>
            </fieldset>
        </div>
    </td>
</tr>
<tr>
    <td width="100%" valign="top" class="one">
        <div class="title_bottom">
            <h1>Cartão Proximidade</h1>
            <h4></h4>
            <fieldset>
                <legend></legend>
                <div>
                    <br>
                    <table class="form" >
                        <tr>
                            <td><mtw:label klass="obrig" value="Numero do Cartão:"/></td>
                            <td>
                                <mtw:input type="hidden" name="cartaoProximidade2" />
                                <mtw:input klass="input" name="cartaoProximidade" id="cartaoProximidade" maxlength="10" />
                            </td>
                            <td>
                                <div class="errors" style="min-height: 40px"><mtw:outError field="cartaoProximidade" ><mtw:out/></mtw:outError></div>
                            </td>
                        </tr>
                        <tr><td><br></td></tr>
                    </table>
                </div>
            </fieldset>
        </div>
    </td>
</tr>
</table>