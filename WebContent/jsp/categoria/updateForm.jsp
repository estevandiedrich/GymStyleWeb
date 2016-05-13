<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="title_bottom">
    <%@include file="topo.jsp" %>
    <h3>Editar</h3>
    <mtw:form method="post" action="categoriaUpdate.do">
        <mtw:bean value="pojo">
            <mtw:input type="hidden" name="id" id="id"/>
            <input type="hidden" name="ativo" value="true"/><!-- Para setar o cadastro como true no banco-->
            <table width="98%" class="faixasForm">
                <tr><td><br></td></tr>
                <tr>
                    <td width="15%" class="one"><mtw:label klass="obrig" value="Categoria:"/></td>
                    <td width="30%" ><mtw:input klass="input" type="text" name="nome" id="nome" maxlength="25"/></td>
                    <td width="30%" ><font class="errors"><mtw:outError field="nome" ><mtw:out/></mtw:outError></font></td>
                <td width="25%" ></td>
                </tr>
                <tr><td><br></td></tr>
            </table>
            <table width="100%" >
                <tr>
                    <td width="100%" class="panelButtonForm">
                        <mtw:buttonAction action="categoriaRead.do" klass="botao" name="Cancelar" value="Cancelar"/>
                        <mtw:submit klass="botao" value="Salvar"/>
                    </td>
                </tr>
            </table>
        </mtw:bean>
    </mtw:form>
</div>