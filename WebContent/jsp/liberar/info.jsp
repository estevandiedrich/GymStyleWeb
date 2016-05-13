<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/liberar/info.js"></script>

<div class="title_bottom">
    <%@include file="topo.jsp" %>
    <h3>Liberar Catraca</h3>
    <mtw:form method="post" action="liberarRead.do">
        <mtw:bean value="pojo">
            <table class="faixasForm" width="95%">
                <tr><td><br></td></tr>
                <tr>
                    <td class="one"><mtw:label klass="obrig" value="Liberado por:"/></td>
                    <td>
                        <mtw:out value="operador.usuario" />
                    </td>
                </tr>
                <tr><td></td></tr>
                <tr>
                    <td class="one"><mtw:label klass="obrig" value="Catraca:"/></td>
                    <td style="background-color: transparent;border: transparent">
                        <mtw:out value="dispositivo" />
                    </td>
                </tr>
                <tr><td></td></tr>
                <tr>
                    <td class="one" valign="top"><mtw:label klass="obrig" value="Justificativa:"/></td>
                    <td>
                        <mtw:textarea name="justificativa" id="justificativa" cols="70" rows="8" extra="disabled='true'" style="background-color: #FFFFFF;"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <br>
                        <br>
                    </td>
                </tr>
                <tr>
                    <td width="100%" class="panelButtonForm" colspan="2">
                        <mtw:submit klass="botao" value="Voltar"/>
                    </td>
                </tr>
            </table>
        </mtw:bean>
    </mtw:form>
</div>