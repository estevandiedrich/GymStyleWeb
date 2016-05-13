<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div style="width:650; height:280;z-index:1; overflow: auto" class="faixasForm">
    <table width="100%"  class="displaytag" >
        <thead>
            <tr><th>Codigo</th><th>Nome</th></tr>
        </thead>
        <tbody>
            <mtw:bean value="bancos">
                <mtw:list value="bancos">
                    <mtw:isEmpty>
                        <tr class="sub">
                            <td colspan="3">
                                Dados Vazios
                            </td>
                        </tr>
                    </mtw:isEmpty>
                    <mtw:loop var="row" counterStart="0" counter="i">
                        <tr class="
                            <c:choose>
                                <c:when test="${i%2==0}">odd</c:when>
                                <c:otherwise>even</c:otherwise>
                            </c:choose>
                                " onclick="selecionarBanco(${row.id},'${row.nome}')" style="cursor: pointer">
                            <td width="30%" ><a class="info" title='Selecionar Banco!'  href="javascript:selecionarBanco(${row.id},'${row.nome}');"><mtw:out value="codigo"/></a></td>
                            <td width="70%" ><a class="info" title='Selecionar Banco!'  href="javascript:selecionarBanco(${row.id},'${row.nome}');"><mtw:out value="nome"/></a></td>
                        </tr>
                    </mtw:loop>
                </mtw:list>
            </mtw:bean>
        </tbody>
    </table>
</div>
