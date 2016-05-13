<%@include file="../../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="div-gerenciar" class="div-autenticacao">
    <h3>Gerenciar</h3>
    <div style="width: 700px;min-height: 450px" class="faixasForm">
        <table width="100%">
            <tr>
                <td width="100%">
                    <table class="displaytag" >
                        <thead>
                            <tr>
                                <th width="90%">Permissão</th>
                                <th width="10%" title="Gerenciar!"><a class='gerenciar' ></a></th>
                            </tr>
                        </thead>
                        <tbody>
                            <mtw:bean value="gerenciar">
                                <mtw:list value="gerenciar">
                                    <mtw:isEmpty>
                                        <tr class="sub">
                                            <td colspan="2">
                                                Dados Vazios
                                            </td>
                                        </tr>
                                    </mtw:isEmpty>
                                    <mtw:loop var="row" counterStart="0" counter="i">
                                        <tr class="
                                            <c:choose><c:when test="${i%2==0}">odd</c:when><c:otherwise>even</c:otherwise></c:choose>">
                                            <td align="center"><mtw:out value="descricao"/></td>
                                            <td><input type="checkbox" name="<mtw:out value="nome"/>" <mtw:out value="${row.nome}Check" /> /></td>
                                        </tr>
                                    </mtw:loop>
                                </mtw:list>
                            </mtw:bean>
                        </tbody>
                    </table>
                </td>
            </tr>
        </table>
    </div>
</div>