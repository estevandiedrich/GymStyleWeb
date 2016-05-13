<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table width="100%" class="displaytag"> 
    <mtw:bean value="lista">
        <thead><tr><th>#</th><th>Vencimento</th><th>Fim de Acesso</th><th>Valor</th></tr></thead>
        <tbody>
            <mtw:list value="lista">  
                <mtw:isEmpty>  
                    <tr>
                        <td colspan="4">
                            Dados Vazios
                        </td>
                    </tr>
                </mtw:isEmpty>  
                <mtw:loop  var="pojo" counterStart="1" counter="i">  
                    <tr class="
                        <c:choose>
                            <c:when test="${i%2==0}">odd</c:when>
                            <c:otherwise>even</c:otherwise>
                        </c:choose>
                        ">
                        <td  align="center" width="5%">${i}</td>
                        <td  align="center" width="45%">
                            <input type="hidden" name="vencimento${i}" value="<mtw:out value="vencimento.time" formatter="dateFormatter" />"/>
                            <label><mtw:out value="vencimento.time"  formatter="dateFormatter"/> - </label>
                            <label> <mtw:out value="i"/>/<mtw:out value="total"/></label>
                        </td>
                        <td  align="center" width="45%">
                            <input type="hidden" name="fimAcesso${i}" value="<mtw:out value="fimAcesso.time" formatter="dateFormatter" />"/>
                            <label><mtw:out value="fimAcesso.time"  formatter="dateFormatter"/></label>
                        </td>
                        <td align="center" width="50%">
                            <mtw:if test="i" value="1" negate="false">
                                <mtw:inputMoney name="valorTotal${i}"  id="valorTotal${i}" value="${pojo.valor}" klass="inputNumber"
                                                textAlign="right" thousands_sep="." decimals="2" dec_point="," title="Valor da parcela mais a matricula." />
                            </mtw:if>
                            <mtw:if test="i" value="1" negate="true">
                                <mtw:inputMoney name="valorTotal${i}"  id="valorTotal${i}" value="${pojo.valor}" klass="inputNumber"
                                                textAlign="right" thousands_sep="." decimals="2" dec_point=","/>
                            </mtw:if>
                            <div id="error${i}"/>
                        </td>
                    </tr>
                </mtw:loop>  
            </mtw:list>  
        </tbody>
    </mtw:bean>
</table>
<mtw:input type="hidden" name="tamanhoLista"  id="tamanhoLista" />