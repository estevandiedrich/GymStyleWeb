<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <table width="100%" >
        <tr>
            <td align="left" width="10%" class="one">
                <mtw:label klass="obrig" value="Justificativa:" />
            </td>
            <td align="left" width="10%">
                <mtw:input type="text" klass="input" name="criterioJustificativa" id="criterioJustificativa" extra="onkeyup=submitFormRead()" />
            </td>
            <td align="left"width="10%">
                <mtw:buttonAction id="filtrar" value="Filtrar" name="filtrar" onclick="submitFormRead()"/>
            </td>
            <td align="left"width="70%">
            </td>
        </tr>
    </table>
</div>
