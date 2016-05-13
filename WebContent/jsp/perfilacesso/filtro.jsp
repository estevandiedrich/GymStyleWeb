<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <table width="100%" >
        <tr>
            <td align="left" width="10%" class="one">
                <mtw:label klass="obrig" value="Nome:" />
            </td>
            <td align="left" width="10%">
                <mtw:input type="text" klass="input" name="criterioNome" />
            </td>
            <td align="left"width="10%">
                <mtw:submit id="filtrar" value="Filtrar"/>
            </td>
            <td align="left"width="70%">
            </td>
        </tr>
    </table>
</div>
