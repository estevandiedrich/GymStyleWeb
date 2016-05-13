<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div style="width:650; height:280;z-index:1; overflow: auto" class="faixasForm">
    <table width="100%" class="displaytag" >
        <thead>
            <tr><th width="20%">Codigo</th><th width="80%">Nome</th></tr>
        </thead>
        <tbody>
            <mtw:bean value="produtos">
                <mtw:list value="produtos">
                    <mtw:isEmpty>
                        <tr class="sub">
                            <td colspan="3">
                                Dados Vazios
                            </td>
                        </tr>
                    </mtw:isEmpty>
                    <mtw:loop var="row" counterStart="0" counter="i">
                        <tr class="<c:choose><c:when test="${i%2==0}">odd</c:when><c:otherwise>even</c:otherwise></c:choose>"
                            onclick="selecionarProduto(${row.id},${row.codigo},'${row.nome}')" style="cursor: pointer">
                            <td><a class="info" title='Selecionar Produto!'  href="javascript:selecionarProduto(${row.id},'${row.codigo},'${row.nome}');"><mtw:out value="codigo" /></a></td>
                            <td><a class="info" title='Selecionar Produto!'  href="javascript:selecionarProduto(${row.id},'${row.codigo},'${row.nome}');"><mtw:out value="nome" max="50"/></a></td>
                        </tr>
                    </mtw:loop>
                </mtw:list>
            </mtw:bean>
        </tbody>
    </table>
</div>
