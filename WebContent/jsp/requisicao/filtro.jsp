<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <table width="98%" class="faixasForm">
        <tr><td><br></td></tr>
        <tr>
            <td class="one" width="15%" >
                <label class="obrig">Consulta:</label>
            </td>
            <td width="30%" >
                <input type="radio" onclick="submitFormRead()" name="radio" id="alunoCheck" value="1" <c:if test="${aluno}">checked="checked"</c:if> />Aluno
                <input type="radio" onclick="submitFormRead()" name="radio" id="funcionarioRadio" value="2" <c:if test="${aluno==false}">checked="checked"</c:if> />Funcionário
            </td>
            <td class="one"  width="15%" >

            </td>
            <td width="20%" ></td>
            <td width="20%" ></td>
        </tr>
        <tr>
            <td class="one" >
                <mtw:label klass="obrig" value="Nome:" />
            </td>
            <td><mtw:input type="text" klass="input" name="criterioNome" id="criterioNome" extra="onkeyup=consultaNome();" /></td>
            <td class="one" ><mtw:label klass="obrig" id="labelMatricula" value="Matrícula:"/></td>
            <td><mtw:input klass="inputNumber" name="criterioMatricula" id="criterioMatricula" extra="onkeyup=submitFormRead()" maxlength="15" /></td>
        </tr>
        <tr>
            <td align="left" class="one"><mtw:label klass="obrig" value="CPF:"/></td>
            <td align="left" >
                <mtw:input klass="inputNumber" name="criterioCpf" id="criterioCpf" extra="onkeyup=submitFormRead()"/>
            </td>
            <td align="right" class="one">
                <img src="images/sinc_error.png" title="Consulta Não Sincronizado!">
            </td>
            <td align="left" >
                <input type="checkbox" name="criterioNaoSincronizado" id="criterioNaoSincronizado" onclick="submitFormRead()" title="Consulta Não Sincronizado!"> <mtw:label klass="obrig" value="Não Sincronizado" />
            </td>
            <td align="left" >
                <mtw:buttonAction id="filtrar" value="Filtrar" name="filtrar" onclick="submitFormRead()"/>
            </td>
            <td align="left" ></td>
        </tr>
        <tr><td><br></td></tr>
    </table>
</div>
