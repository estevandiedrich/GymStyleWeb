<%@include file="../../WEB-INF/imports.jspf" %>
<div style="width:100%" align="left">
    <mtw:bean value="pojoInfo">
        <table class="form" >
            <tr>
                <td class="one"><mtw:label klass="obrig" value="Nome:"/></td>
                <td><mtw:out value="usuario"/></td>
                <td class="one"><mtw:label klass="obrig" value="CPF:"/></td>
                <td>
                    <div class="infoCpfLabel" id="infoCpfLabel" ><mtw:out  value="infoCpf"/></div>
                    <mtw:input id="infoCpf" klass="infoCpf" name="infoCpf" type="hidden"/>
                </td>
                <td class="one"><mtw:label klass="obrig" value="Matrícula:"/></td>
                <td><mtw:out value="matricula"/></td>
            </tr>
        </table>
        <br>
    </mtw:bean>
</div>