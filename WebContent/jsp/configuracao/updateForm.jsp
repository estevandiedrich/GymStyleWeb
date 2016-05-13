<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="title_bottom">
    <%@include file="topo.jsp" %>
    <h3>Editar</h3>
    <mtw:form method="post" action="configuracaoUpdate.do">
        <mtw:bean value="pojo">
            <table class="faixasForm" width="98%" >
                <tr>
                    <td><br></td>
                </tr>
                <tr>
                    <td class="one" width="25%" >
                        <mtw:input type="hidden" name="id"/>
                        <mtw:input type="hidden" name="campo"/>
                        <mtw:input type="hidden" name="descricao"/>
                        <label class="obrig"><mtw:out value="descricao"/>:</label>
                    </td>
                    <td width="25%" >
                        <input name="valor" id="valor" class="input" value="<mtw:out value="valor"/>" maxlength="<mtw:if test="campo" value="rodapeRecibo" >70</mtw:if><mtw:if test="campo" value="tolerancia" >2</mtw:if><mtw:if test="campo" value="diaVencimento" >2</mtw:if>"/>
                    </td>
                    <td  width="50%" ><font class="errors"><mtw:outError field="valor" ><mtw:out/></mtw:outError></font></td>
                </tr>
                <tr>
                    <td><br></td>
                </tr>
            </table>
            <table width="100%" >
                <tr>
                    <td width="100%" class="panelButtonForm">
                        <mtw:buttonAction action="configuracaoRead.do" klass="botao" name="Cancelar" value="Cancelar"/>
                        <mtw:submit klass="botao" value="Salvar"/>
                    </td>
                </tr>
            </table>
        </mtw:bean>
    </mtw:form>
</div>