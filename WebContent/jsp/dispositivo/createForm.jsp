<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="title_bottom">
    <%@include file="topo.jsp" %>
    <h3>Novo</h3>
    <mtw:form method="post" action="dispositivoCreate.do">
        <mtw:bean value="pojo">
            <table class="form">
                <tr>
                    <td class="one"><mtw:input type="hidden" name="id"/><mtw:label klass="obrig" value="Nome"/></td>
                    <td><mtw:input klass="input" type="text" name="dispositivo"/></td>
                    <td><font class="errors"><mtw:outError field="dispositivo" ><mtw:out/></mtw:outError></font></td>
                </tr>
            </table>
            <table width="100%" >
                <tr>
                    <td width="100%" class="panelButtonForm">
                        <mtw:buttonAction action="main.do" klass="botao" name="Cancelar" value="Cancelar"/>
                        <mtw:submit klass="botao" value="Salvar"/>
                    </td>
                </tr>
            </table>
        </mtw:bean>
    </mtw:form>
</div>