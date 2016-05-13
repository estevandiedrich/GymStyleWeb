<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="faixasForm" style="max-height: 250px;overflow: auto">
    <mtw:bean value="lista">
        <table width="100%" class="displaytag">
            <mtw:bean value="lista">
                <thead>
                    <tr>
                        <th width="49%" colspan="3">Descrição</th>
                        <th width="20%">Data-Hora</th>
                        <th width="14%">Valor</th>
                        <th  width="17%">Ação</th>
                    </tr>
                </thead>
                <tbody>
                    <mtw:list value="registros">
                        <mtw:isEmpty>
                            <tr class="sub">
                                <td colspan="6">
                                    Dados Vazios
                                </td>
                            </tr>
                        </mtw:isEmpty>
                        <mtw:loop var="reg" counterStart="1" counter="i">
                            <tr class="
                                <c:choose>
                                    <c:when test="${i%2==0}">odd</c:when>
                                    <c:otherwise>even</c:otherwise>
                                </c:choose>
                                ">
                                <td width="4%">
                                    <mtw:if test="reg.entrada"><img src="images/up.png" title="Entrada!" style="cursor: help;"/></mtw:if>
                                    <mtw:if test="reg.entrada" negate="true" ><img src="images/down.png" style="cursor: help;" title="Retirada!"/></mtw:if>
                                </td>
                                <td width="4%" ><mtw:out value="reg.formaDePagamento.image"/></td>
                                <td align="center"><label title="<mtw:out value="reg.descricao"/>" ><mtw:out value="reg.descricao" max="30"/></label></td>
                                <td align="center" ><mtw:out value="reg.dataHora.time"/></td>
                                <td align="center"><mtw:out value="reg.valor" formatter="realFormatter"/></td>
                                <td align="center" ><a class="update" href="#">Editar</a> | <a class="delete" href="#">Excluir</a></td>
                            </tr>
                        </mtw:loop>
                    </mtw:list>
                    </div>
                </mtw:bean>
        </table>
    </mtw:bean>
</div>
<div class="faixasForm">
    <mtw:bean value="pojo">
        <table width="100%" class="displaytag">
            <tr><th colspan="8">Resumo</th></tr>
            <tr>
                <td class="even" width="11%" ><label class="obrig">Inicial:</label></td>
                <td class="odd"  width="14%"><mtw:out value="valorInicial" formatter="realFormatter"/></td>
                <td class="sub" width="11%" ><label class="obrig">Entradas:</label></td>
                <td class="odd"  width="14%"><mtw:out value="valorEntrada" formatter="realFormatter"/></td>
                <td class="sub2" width="11%" ><label class="obrig">Retiradas:</label></td>
                <td class="odd"  width="14%"><mtw:out value="valorRetirada" formatter="realFormatter"/></td>
                <td class="even" width="11%" ><label class="obrig">Saldo Atual:</label></td>
                <td class="odd"  width="14%"><mtw:out value="valorFinal" formatter="realFormatter"/></td>
            </tr>
            <tr><td colspan="8"></td></tr>
        </table>
    </mtw:bean>
</div>