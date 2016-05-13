<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div>
    <table width="98%" class="faixasForm" >
        <tr><td><br></td></tr>
        <tr>
            <td align="left" width="10%" class="one"><mtw:label klass="obrig" value="Matrícula:"/></td>
            <td>
                <mtw:input klass="selectOptions" name="criterioMatricula" id="criterioMatricula" extra="onkeyup=submitRelatorioFormEventoRead()" maxlength="12"/>
            </td>
            <td width="10%"></td>
            <td></td>
        </tr>
        <tr>
            <td align="left" width="10%" class="one"><mtw:label klass="obrig" value="Catraca:"/></td>
            <td>
                <mtw:select klass="selectOptions" name="criterioCatraca" id="criterioCatraca" list="catracas"
                            emptyField="true" emptyFieldValue="Selecione..." extra="onchange=submitRelatorioFormEventoRead()"/>
            </td>
            <td width="10%" class="one"><mtw:label klass="obrig" value="Descrição:"/></td>
            <td>
                <mtw:select klass="selectOptions" name="criterioDescricao" id="criterioDescricao" list="descricoes"
                            emptyField="true" emptyFieldValue="Selecione..." extra="onchange=submitRelatorioFormEventoRead()"/>
            </td>
        </tr>
        <tr>
            <td align="left" width="10%" class="one">
                <mtw:label klass="obrig" value="Nome:" />
            </td>
            <td align="left" width="25%" >
                <mtw:input klass="selectOptions" name="criterioNome" id="criterioNome" extra="onkeyup=consultaData()" maxlength="70"/>
            </td>
            <td width="10%" class="one"><mtw:label klass="obrig" value="CPF:"/></td>
            <td>
                <mtw:input klass="inputNumber" name="criterioCpf" id="criterioCpf" extra="onkeyup=consultaData()"/>
            </td>
        </tr>
        <tr>
            <td align="left" width="10%" class="one">
            </td>
            <td align="left" width="10%">
                <div id="criterioInicioError" style="display:none"><font class="errors">Data Inválida</font><img src='images/alert.png' title='Campo Obrigatório!'/></div>
            </td>
            <td align="left" width="10%" ></td>
            <td align="left" width="10%"></td>
            <td align="left"width="24%" ></td>
        </tr>
        <tr>
            <td align="left" width="10%" class="one">
                <mtw:label klass="obrig" value="Início:" />
            </td>
            <td align="left" width="25%">
                <div>
                    <mtw:input klass="inputNumber" name="criterioInicio" id="criterioInicio" extra="onkeyup=consultaData()"/>
                </div>
            </td>
            <td align="left" width="10%" class="one">
                <mtw:label klass="obrig" value="Término:" />
            </td>
            <td align="left" width="25%">
                <mtw:input klass="inputNumber" name="criterioFim" id="criterioFim" extra="onkeyup=submitRelatorioFormEventoRead()"/>
            </td>
            <td align="left"width="24%" >
                <mtw:buttonAction name="filtrar" value="filtrar" id="filtrar" onclick="submitRelatorioFormEventoRead()"/>
                <mtw:hasAuthorization permission="eventoReport">
                    <mtw:buttonAction klass="pdf" value="Imprimir" name="Imprimir" onclick="submitRelatorioFormEventoReport()" />
                </mtw:hasAuthorization>
            </td>
        </tr>
        <tr><td><br></td></tr>
    </table>
</div>