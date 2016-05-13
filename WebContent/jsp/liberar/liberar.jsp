<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/liberar/liberar.js"></script>

<div class="title_bottom">
    <%@include file="topo.jsp" %>
    <h3>Liberar Catraca</h3>
    <div class="faixasForm" style="height: 56px;display: none" id="aguardando">
        <table width="100%">
            <tr>
                <td width="10%">
                    <img src="images/loading.gif" style="padding-top: 10px;padding-left: 10px"/>
                </td>
                <td width="90%">
                    <h2>Aguardando Catraca Liberar Passagem</h2>
                </td>
            </tr>
        </table>
    </div>
    <div id="formLiberar">
        <mtw:form method="post" action="liberarCatraca.do" >
            <mtw:bean value="pojo">
                <table class="faixasForm" width="95%">
                    <tr><td><br></td></tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Catraca:"/></td>
                        <td>
                            <mtw:select list="dispositivos" id="dispositivo" name="dispositivo" klass="selectOptions" />
                            <font class="errors" ><mtw:outError field="dispositivo" ><mtw:out/></mtw:outError></font>
                        </td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Justificativa:"/></td>
                        <td><mtw:textarea name="justificativa" id="justificativa" cols="70" maxlength="250" rows="8" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><font class="errors" ><mtw:outError field="justificativa" ><mtw:out/></mtw:outError></font></td>
                    </tr>
                    <tr><td><br><br></td></tr>
                    <tr>
                        <td width="100%" class="panelButtonForm" colspan="2">
                            <mtw:buttonAction action="liberarRead.do" klass="botao" name="Cancelar" value="Cancelar"/>                           
                            <mtw:submit name="Salvar" klass="botao" id="salvar" value="Liberar" />
                        </td>
                    </tr>
                </table>
            </mtw:bean>
        </mtw:form>
    </div>
</div>