<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h4>Digitais</h4>
<div class="divDigitais">
    <h3></h3>
    <h1 style="font-size: 13px">
        Ao utilizar a identificação por digitais, informe uma de cada mão!
    </h1>
    <table width="100%" class="digitais" title="É necessário conter apenas duas digitais ou nenhuma, quando utilizando o cartão de proximidade para salvar o cadastro!">
        <thead>
            <tr><th colspan="2" class="order1" align="center">Descrição</th><th  class="acao" align="center">Ação</th></tr>
        </thead>
        <tbody>
            <mtw:list value="digitais">  
                <mtw:isEmpty>  
                    <tr>
                        <td colspan="4">
                            Dados Vazios
                        </td>
                    </tr>
                </mtw:isEmpty>  
                <mtw:loop var="row" counterStart="1" counter="i">  
                    <tr class="
                        <c:choose>
                            <c:when test="${i%2!=0}">odd</c:when>
                            <c:otherwise>even</c:otherwise>
                        </c:choose>
                        ">
                        <td>
                            <mtw:out value="i"/>
                        </td>
                        <td>
                            <mtw:out value="dedo"/>
                        </td>
                        <td class="acao">
                            <a class='delete' title='Excluir' href="javascript:deleteDigital(${row.id});" >Excluir</a>
                        </td>
                    </tr>
                </mtw:loop>  
            </mtw:list>
            <tr class="odd">
                <td colspan="3">
        <font class="errors"><mtw:out value="mensagemDigitais"/></font>
        <input type="hidden" name="mensagemDigitais" value="<mtw:out value="mensagemDigitais"/>" />
        </td>
        </tr>
        <tr>
            <td colspan="3">
                <input type="hidden" name="tamanhoDigitais" value="<mtw:out value="digitais.size"/>" />
            </td>
        </tr>
        </tbody>
    </table>
</div>