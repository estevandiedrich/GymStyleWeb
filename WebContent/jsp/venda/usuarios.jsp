<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div style="width:650; height:280;z-index:1; overflow: auto" class="faixasForm">
    <table width="100%"  class="displaytag" >
        <thead>
            <tr><th>Nome</th></tr>
        </thead>
        <tbody>
            <mtw:bean value="usuarios">
                <mtw:list value="usuarios">
                    <mtw:isEmpty>
                        <tr class="sub">
                            <td colspan="3">
                                Dados Vazios
                            </td>
                        </tr>
                    </mtw:isEmpty>
                    <mtw:loop var="row" counterStart="0" counter="i">
                        <tr class="<c:choose><c:when test="${i%2==0}">odd</c:when><c:otherwise>even</c:otherwise></c:choose>"
                            onclick="selecionarUsuario(${row.id},'${row.usuario}')" style="cursor: pointer">
                            <td width="70%" ><a class="info" title='Selecionar Usuario!' href="javascript:selecionarUsuario(${row.id},'${row.usuario}');"><mtw:out value="usuario"/></a></td>
                        </tr>
                    </mtw:loop>
                </mtw:list>
            </mtw:bean>
        </tbody>
    </table>
</div>
