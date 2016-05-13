<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/empresa/update.js"></script>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/maskMoney.js" type="text/javascript"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>

<div class="title_bottom">
    <%@include file="topo.jsp" %>
    <h3>Novo</h3>
    <mtw:form method="post" action="empresaUpdate.do">
        <mtw:bean value="pojo">
            <mtw:input klass="input" type="hidden" name="id" id="id"/>
            <table width="98%" class="faixasForm">
                <tr>
                    <td>
                        <h1>Identificação</h1>
                        <fieldset id="dadosPessoais" class="field">
                            <legend></legend>
                            <div>
                                <table>
                                    <tr>
                                        <td class="one"><mtw:label klass="obrig" value="Razão Social:"/></td>
                                        <td><mtw:input klass="input" type="text" name="razaoSocial" id="razaoSocial" maxlength="41"/></td>
                                        <td><font class="errors"><mtw:outError field="razaoSocial" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr>
                                        <td class="one"><mtw:label klass="obrig" value="Nome Fantasia:"/></td>
                                        <td><mtw:input klass="input" type="text" name="nomeFantasia" id="nomeFantasia" maxlength="41"/></td>
                                        <td><font class="errors"><mtw:outError field="nomeFantasia" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr>
                                        <td class="one"><mtw:label klass="obrig" value="CNPJ:"/></td>
                                        <td><mtw:input klass="input" type="text" name="cnpj" id="cnpj"/></td>
                                        <td><font class="errors"><mtw:outError field="cnpj" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr>
                                        <td><br></td>
                                    </tr>
                                </table>
                            </div>
                        </fieldset>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h1>Endereço</h1>
                        <fieldset id="dadosPessoais" class="field">
                            <legend></legend>
                            <div>
                                <table>
                                    <tr>
                                        <td class="one"><mtw:label value="Logradouro:"/></td>
                                        <td style="padding-left: 22px"><mtw:input klass="input" type="text" name="endereco" maxlength="100"/></td>
                                        <td><font class="errors"><mtw:outError field="endereco" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr>
                                        <td class="one"><mtw:label value="Bairro:"/></td>
                                        <td style="padding-left: 22px"><mtw:input klass="input" type="text" name="bairro" maxlength="42"/></td>
                                        <td><font class="errors"><mtw:outError field="bairro" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr>
                                        <td class="one"><mtw:label value="Cidade:"/></td>
                                        <td style="padding-left: 22px"><mtw:input klass="input" type="text" name="cidade" maxlength="42"/></td>
                                        <td><font class="errors"><mtw:outError field="cidade" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr>
                                        <td class="one"><mtw:label value="Estado:"/></td>
                                        <td style="padding-left: 22px"><mtw:select klass="selectOptions" name="uf" list="estados" emptyField="true" emptyFieldValue="Selecione..."/></td>
                                        <td><font class="errors"><mtw:outError field="uf" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr>
                                        <td class="one"><mtw:label value="CEP:"/></td>
                                        <td style="padding-left: 22px"><mtw:input klass="inputNumber" name="cep" id="cep" /></td>
                                        <td><font class="errors"><mtw:outError field="cep" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr>
                                        <td class="one"><mtw:label value="Telefone:"/></td>
                                        <td style="padding-left: 22px"><mtw:input klass="input" type="text" name="telefone" id="telefone" maxlength="15" /></td>
                                        <td><font class="errors"><mtw:outError field="telefone" ><mtw:out/></mtw:outError></font></td>
                                        </tr>
                                        <tr>
                                            <td><br></td>
                                        </tr>
                                    </table>
                                </div>
                            </fieldset>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="faixasForm" align="center" style="font-size: 12px;color: #EC0000 ;font-style: italic">Os campos Nome Fantasia e CNPJ, deveram estar cadastrados para aparecer no recibo de pagamento!</td>
                    </tr>
                    <tr><td colspan="2" ><br></td></tr>
                    <tr>
                        <td width="100%" class="panelButtonForm">
                        <mtw:buttonAction action="empresaRead.do" klass="botao" name="Cancelar" value="Cancelar"/>
                        <mtw:submit klass="botao" value="Salvar"/>
                    </td>
                </tr>
            </table>
        </mtw:bean>
    </mtw:form>
</div>