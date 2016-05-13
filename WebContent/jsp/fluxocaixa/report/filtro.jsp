<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <table width="100%" class="paginacao" style="font-size: 12px">
        <tr>
            <td style="padding-top: 10px"></td>
        </tr>
        <tr>
            <td class="one" align="left" width="5%" >
                <mtw:label klass="obrig" value="Mês:" />
            </td>
            <td align="left" width="25%">
                <mtw:select klass="selectOptions" name="criterioMes" id="criterioMes" list="meses"  extra="onchange=submitRelatorioFormCaixaRead()" 
                            emptyField="true" emptyFieldValue="Selecione..."/>
            </td>
            <td width="5%" class="one"><mtw:label  klass="obrig" value="Ano:"/></td>
            <td align="left" width="25%">
                <mtw:select klass="selectOptions" name="criterioAno" id="criterioAno" list="anos"  extra="onchange=submitRelatorioFormCaixaRead()" 
                            emptyField="true" emptyFieldValue="Selecione..."/>
            </td>
            <td align="left" width="10%">
                <mtw:buttonAction name="filtrar" value="filtrar" id="filtrar"  onclick="submitRelatorioFormCaixaRead()"/>
            </td>
            <td align="left" width="20%">
                <mtw:hasAuthorization permission="caixaReportPDF">
                    <mtw:buttonAction klass="pdf" value="Imprimir" name="Imprimir" onclick="submitRelatorioFormCaixaReadReport()" />
                </mtw:hasAuthorization>
            </td>
        </tr>
        <tr>
            <td class="one" colspan="2"><div id="mesError" class="campoError" >Campo Obrigatório</div></td>
            <td width="5%" class="one" colspan="2"><div id="anoError" class="campoError" >Campo Obrigatório</div></td>
            <td align="left" width="10%"></td>
            <td align="left" width="10%"></td>
        </tr>
        <tr>
            <td style="padding-top: 10px"></td>
        </tr>
    </table>
</div>