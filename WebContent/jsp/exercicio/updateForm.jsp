<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="title_bottom">
    <%@include file="topo.jsp" %>
    <h3>Editar</h3>
    <mtw:form method="post" action="exercicioUpdate.do">
        <mtw:bean value="pojo">
            <table width="80%" class="faixasForm">
                <tr>
                    <td><br></td>
                </tr>
                <tr>
                    <td class="one"><mtw:label klass="obrig" value="Exercício:"/><mtw:input klass="input" type="hidden" name="id" id="id"/></td>
                    <td><mtw:input klass="input" type="text" name="exercicio" id="exercicio" maxlength="25"/></td>
                    <td><font class="errors"><mtw:outError field="exercicio" ><mtw:out/></mtw:outError></font></td>
                </tr>
                <tr>
                    <td width="30%" class="one"><mtw:label klass="obrig" value="Grupo Muscular:"/></td>
                    <td width="20%">
                        <mtw:select klass="selectOptions" name="grupoMuscularExer" list="grupos" />
                    </td>
                    <td width="50%">
                        <font class="errors"><mtw:outError field="grupoMuscular" ><mtw:out/></mtw:outError></font></td>
                </tr>
                <tr>
                    <td><br></td>
                </tr>
            </table>
            <table width="100%" >
                <tr>
                    <td width="100%" class="panelButtonForm">
                        <mtw:buttonAction action="exercicioRead.do" klass="botao" name="Cancelar" value="Cancelar"/>
                        <mtw:submit klass="botao" value="Salvar"/>
                    </td>
                </tr>
            </table>
        </mtw:bean>
    </mtw:form>
</div>