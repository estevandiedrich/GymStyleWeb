<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table width="100%" class="displaytag">
    <thead>
        <tr>
            <th>Catraca</th><th>Vencimento</th><th>Fim de Acesso</th><th>Pagamento</th><th>Valor Parcela</th><th>Valor Pago</th>
            <mtw:hasAuthorization permission="pagamentoUltimoPlanosRead" ><th>Ação</th></mtw:hasAuthorization>
        </tr>
    </thead>
    <tbody>    
        <mtw:bean value="pag">
            <mtw:isNull test="mac">
                <tr class="sub">
                    <td colspan="7">
                        Dados Vazios
                    </td>
                </tr>
            </mtw:isNull>
            <mtw:isNull test="mac" negate="true">
                <tr class="even">
                    <td width="15%" >
                        <mtw:out value="dispositivo" />
                    <td width="15%">
                        <mtw:out value="pag.vencimento.time" formatter="dateFormatter"/>
                    </td>
                    <td width="15%">
                        <mtw:out value="pag.fimAcesso.time" formatter="dateFormatter"/>
                    </td>
                    <td width="20%">
                        <mtw:out value="pag.pagamento.time" />
                    </td>
                    <td width="15%">
                        <mtw:out value="pag.valor" formatter="realFormatter"/>
                    </td>
                    <td width="15%">
                        <mtw:out value="pag.valorPago" formatter="realFormatter"/>
                    </td>
                    <mtw:hasAuthorization permission="pagamentoUltimoPlanosRead" >
                        <td width="5%">
                            <a id='plano' title='Ver Planos' href='pagamentosPlanoUsuario.do?idPlanoUsuario=<mtw:out value="idPlanoUsuario" />'>Ver</a>
                        </td>
                    </mtw:hasAuthorization>
                </tr>
            </mtw:isNull>
        </mtw:bean>
    </tbody>
</table>