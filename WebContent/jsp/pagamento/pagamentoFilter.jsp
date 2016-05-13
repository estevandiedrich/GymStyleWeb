<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/pagamento/pagamentoFilter.js"></script>

<div class="title_bottom">
    <table class="topo" width="100%">
        <tr>
            <td  width="100%">
                <h1>Relatório de Pagamentos</h1>
            </td>
        </tr>
    </table>
    <mtw:form action="pagamentoReport.do" name="pagamentoForm" >
        <h3>Filtro</h3>        
        <div class="faixasForm" style="height: 80px">
            <br>
            <table width="100%" >
                <tr>
                    <td align="left" width="10%" class="one">
                        <mtw:label value="Início:" />
                    </td>
                    <td align="left" width="25%">
                        <mtw:inputDate klass="inputNumber" name="criterioInicio" id="criterioInicio" textAlign="right"/>
                    </td>
                    <td align="left" width="3%">
                        <div id="criterioInicioError"></div>
                    </td>
                    <td align="left" width="10%" class="one">
                        <mtw:label value="Término:" />
                    </td>
                    <td align="left" width="25%">
                        <mtw:inputDate klass="inputNumber" name="criterioFim"  id="criterioFim" textAlign="right" />
                    </td>
                    <td align="left" width="3%">
                        <div id="criterioFimError"></div>
                    </td>
                    <td align="left"width="20%">
                        <mtw:buttonAction klass="pdf" value="Imprimir" name="Imprimir" onclick="submitPagamentoReportForm()" />
                    </td>
                </tr>
            </table>
        </div>
    </mtw:form>
</div>