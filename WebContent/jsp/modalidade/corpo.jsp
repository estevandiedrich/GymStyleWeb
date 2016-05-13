<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="js/excluir.js" type="text/javascript"></script>

<mtw:bean value="paginator" var="pag">
    <table width="100%" class="displaytag">
        <thead>
            <tr><th><%@include file="../template/orderbyPaginator.jsp" %> Modalidade</th>
                <th title="1x por semana">Valor 1</th>
                <th title="2x por semana">Valor 2</th>
                <th title="3x por semana">Valor 3</th>
                <th title="4x por semana">Valor 4</th>
                <th title="5x por semana">Valor 5</th>
                <th title="6x por semana">Valor 6</th>
                <th title="livre na semana">Livre</th>
                <mtw:hasAuthorization permission="modalidadeManager,modalidadeDelete">
                    <th>Ação</th>
                </mtw:hasAuthorization>
            </tr>
        </thead>
        <tbody>
            <mtw:bean value="pag.list">
                <mtw:list value="pag.list">
                    <mtw:isEmpty>
                        <tr class="sub">
                            <td colspan="9" >Dados Vazios</td>
                        </tr>
                    </mtw:isEmpty>
                    <mtw:loop var="row" counterStart="0" counter="i">
                        <tr class="
                            <c:choose>
                                <c:when test="${i%2==0}">odd</c:when>
                                <c:otherwise>even</c:otherwise>
                            </c:choose>
                            ">
                            <td width="20%">
                                <label title="<mtw:out value="modalidade"/>!"><mtw:out value="modalidade" max="21"/></label>
                            </td>
                            <td width="5%" style="max-width: 14px">
                                <label title="<mtw:out value="valor1" formatter="realFormatter" />"><mtw:out value="valor1" formatter="realFormatter" max="6"/></label>
                            </td>
                            <td width="5%" style="max-width: 14px">
                                <label title="<mtw:out value="valor2" formatter="realFormatter" />"><mtw:out value="valor2" formatter="realFormatter" max="6"/></label>
                            </td>
                            <td width="5%" style="max-width: 14px">
                                <label title="<mtw:out value="valor3" formatter="realFormatter" />"><mtw:out value="valor3" formatter="realFormatter" max="6"/></label>
                            </td>
                            <td width="5%" style="max-width: 14px">
                                <label title="<mtw:out value="valor4" formatter="realFormatter" />"><mtw:out value="valor4" formatter="realFormatter" max="6"/></label>
                            </td>
                            <td width="5%" style="max-width: 14px">
                                <label title="<mtw:out value="valor5" formatter="realFormatter" />"><mtw:out value="valor5" formatter="realFormatter" max="6"/></label>
                            </td>
                            <td width="5%" style="max-width: 14px">
                                <label title="<mtw:out value="valor6" formatter="realFormatter" />"><mtw:out value="valor6" formatter="realFormatter" max="6"/></label>
                            </td>
                            <td width="5%" style="max-width: 14px">
                                <label title="<mtw:out value="valor7" formatter="realFormatter" />"><mtw:out value="valor7" formatter="realFormatter" max="6"/></label>                                
                            </td>
                            <mtw:hasAuthorization permission="modalidadeManager,modalidadeDelete">
                                <td width="10%">
                                    <mtw:hasAuthorization permission="modalidadeManager">
                                        <a class='update' title='Editar'  href="modalidadeUpdate.do?id=${row.id}">Editar</a>
                                    </mtw:hasAuthorization>
                                    <mtw:hasAuthorization permission="modalidadeDelete">
                                       | <a class='delete' title='Excluir' href="javascript:excluirModalidade(${row.id});">Excluir</a>
                                    </td>
                                </mtw:hasAuthorization>
                            </tr>
                            </mtw:hasAuthorization>
                    </mtw:loop>
                    <tr>
                        <td align="right" width="100%" colspan="9" >
                            <%@include file="../template/paginacaoPaginator.jsp" %>
                        </td>
                    </tr>
                </mtw:list>
            </mtw:bean>
        </tbody>
    </table>
</mtw:bean>
