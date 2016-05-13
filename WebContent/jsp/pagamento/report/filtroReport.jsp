<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/pagamento/autenticacao/filtro.js"></script>

<div>
    <table width="98%" class="paginacao" style="font-size: 12px">
        <tr>
            <td style="padding-top: 10px">

            </td>
        </tr>
        <tr>
            <td align="left" width="15%" class="one"  style="padding-bottom: 5px">
                <mtw:label klass="obrig" value="Período por:" />
            </td>
            <td align="left" width="25%" style="padding-bottom: 5px">
                <table>
                    <tr>
                        <td><input type="radio" name="tipo" value="false" checked="true" onclick="submitRelatorioFormPagamentoReportRead();ocultaPendente()">Pagamento</td>
                        <td><input type="radio" name="tipo" id="vencimento" value="true" onclick="submitRelatorioFormPagamentoReportRead();mostraPendente()">Vencimento</td>
                    </tr>
                </table>                
            </td>
            <td class="one" align="left" width="15%" style="padding-bottom: 5px">
                <mtw:label klass="obrig" value="Pagamentos:" />
            </td>
            <td align="left" width="25%" style="padding-bottom: 5px">
                <table>
                    <tr>
                        <td><div><input type="radio" name="pagamento" id="realizado" value="true" checked="true" onclick="submitRelatorioFormPagamentoReportRead()">Quitado</div></td>
                        <td><div id="divPendente" style="display: none"><input type="radio" name="pagamento" value="false" onclick="submitRelatorioFormPagamentoReportRead()">Pendente</div></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td class="one" align="left" width="10%" >
                <mtw:label klass="obrig" value="Matrícula:" />
            </td>
            <td align="left" width="25%">
                <mtw:input klass="input" name="criterioMatricula" id="criterioMatricula"  extra="onkeyup=submitRelatorioFormPagamentoReportRead()" maxlength="12"/>
            </td>
            <td width="10%" class="one" style="padding-bottom: 5px"><mtw:label  klass="obrig" value="Operador:"/></td>
            <td style="padding-bottom: 5px" colspan="2" >
                <mtw:select klass="input" name="criterioFuncionario" id="criterioFuncionario" list="funcionarios"  extra="onchange=submitRelatorioFormPagamentoReportRead()" 
                            emptyField="true" emptyFieldValue="Selecione..."/>
            </td>
        </tr>
        <tr>
            <td class="one" align="left" width="10%" >
                <mtw:label klass="obrig" value="Aluno:" />
            </td>
            <td align="left" width="25%">
                <mtw:input klass="input" name="criterioNome" id="criterioNome"  extra="onkeyup=consultaNome()" maxlength="70"/>
            </td>
            <td class="one" align="left" width="10%" >
                <mtw:label klass="obrig" value="Início:" />
            </td>
            <td align="left" width="25%">
                <mtw:input klass="inputNumber" name="criterioInicio" id="criterioInicio" extra="onkeyup=submitRelatorioFormPagamentoReportRead()"/>
            </td>
            <td align="left"width="24%" style="padding-bottom: 5px">
                <mtw:hasAuthorization permission="pagamentoReport" >
                    <mtw:buttonAction klass="pdf" value="Imprimir" name="Imprimir" onclick="submitRelatorioFormPagamentoReport()" />
                </mtw:hasAuthorization>
            </td>
        </tr>
        <tr>
            <td width="10%" class="one" style="padding-bottom: 5px"><mtw:label  klass="obrig" value="Planos:"/></td>
            <td style="padding-bottom: 5px">
                <mtw:select klass="input" name="criterioPlano" id="criterioPlano" list="planos"  extra="onchange=submitRelatorioFormPagamentoReportRead()" 
                            emptyField="true" emptyFieldValue="Selecione..."/>
            </td>
            <td class="one" align="left" width="10%" style="padding-bottom: 5px" >
                <mtw:label klass="obrig" value="Término:" />
            </td>
            <td align="left" width="25%" style="padding-bottom: 5px">
                <mtw:input klass="inputNumber" name="criterioFim" id="criterioFim" extra="onkeyup=submitRelatorioFormPagamentoReportRead()" />
            </td>
            <td align="left"width="24%" style="padding-bottom: 5px">
                <mtw:buttonAction name="filtrar" value="filtrar" id="filtrar" onclick="submitRelatorioFormPagamentoReportRead()"/>
            </td>
        </tr>
    </table>
</div>