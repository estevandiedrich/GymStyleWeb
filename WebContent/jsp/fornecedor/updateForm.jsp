<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="title_bottom">
    <%@include file="topo.jsp" %>
    <h3>Editar</h3>
    <mtw:form method="post" action="fornecedorUpdate.do">
        <mtw:bean value="pojo">
            <table width="80%" class="faixasForm">
                <tr>
                    <td><br></td>
                </tr>
                <tr>
                    <td width="10%" class="one"><mtw:label klass="obrig" value="Nome:"/><mtw:input klass="input" type="hidden" name="id" id="id"/></td>
                    <td width="30%" ><mtw:input klass="input" type="text" name="nome" id="nome" maxlength="25"/></td>
                    <td width="60%" ><font class="errors"><mtw:outError field="nome" ><mtw:out/></mtw:outError></font></td>
                </tr>
                <tr>
                    <td class="one"><mtw:label value="Cidade:"/></td>
                    <td><mtw:input klass="input" type="text" name="cidade" maxlength="100"/></td>
                    <td><font class="errors"><mtw:outError field="cidade" ><mtw:out/></mtw:outError></font></td>
                </tr>
                <tr>
                    <td class="one"><mtw:label value="Estado:"/></td>
                    <td><mtw:select klass="selectOptions" name="estado" list="estados" emptyField="true" emptyFieldValue="Selecione..."/></td>
                    <td><font class="errors"><mtw:outError field="estado" ><mtw:out/></mtw:outError></font></td>
                </tr>
                <tr>
                    <td class="one"><mtw:label value="Telefone:"/></td>
                    <td>
                        <mtw:input  klass="inputTelefone" name="telefone" id="telefone" maxlength="14"/>
                    </td>
                    <td><font class="errors"><mtw:outError field="telefone" ><mtw:out/></mtw:outError></font></td>
                </tr>
                <tr>
                    <td><br></td>
                </tr>
            </table>
            <table width="100%" >
                <tr>
                    <td width="100%" class="panelButtonForm">
                        <mtw:buttonAction action="fornecedorRead.do" klass="botao" name="Cancelar" value="Cancelar"/>
                        <mtw:submit klass="botao" value="Salvar"/>
                    </td>
                </tr>
            </table>
        </mtw:bean>
    </mtw:form>
</div>