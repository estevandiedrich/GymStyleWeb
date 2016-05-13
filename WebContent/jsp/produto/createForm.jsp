<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/produto/createForm.js"></script>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/maskMoney.js" type="text/javascript"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>

<div class="title_bottom">
    <%@include file="topo.jsp" %>
    <h3>Novo</h3>
    <mtw:form method="post" action="produtoCreate.do" klass="faixasForm" >
        <mtw:bean value="pojo">
            <div class="faixasForm" width="98%" >
                <table width="98%" >
                    <tr class="even">
                        <td width="40%" valign="top">
                            <div class="faixasForm" style="width: 400px;height: 140px;">
                                <h3></h3>
                                <table class="displaytag" width="100%">
                                    <tr class="odd">
                                        <td width="25%" class="one" align="left">
                                            <mtw:input type="hidden" name="id"/>
                                            <mtw:input klass="input" type="hidden" name="ativo" value="true"/><!-- Para setar o cadastro como true no banco-->
                                            <mtw:label klass="obrig" value="Código:"/>
                                        </td>
                                        <td width="50%" align="left">
                                            <mtw:input klass="inputNumber" type="text" name="codigo" maxlength="15" extra="onkeypress=return somenteNumero(event)"/>
                                        </td>
                                        <td width="25%"><font class="error"><mtw:outError field="codigo" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr class="even">
                                        <td class="one" align="left"><mtw:label klass="obrig" value="Nome:"/></td>
                                        <td align="left">
                                            <mtw:input name="nome" id="nome" klass="input" maxlength="55" />
                                        </td>
                                        <td><font class="error"><mtw:outError field="nome" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr class="odd">
                                        <td class="one" align="left"><mtw:label klass="obrig" value="Categoria:"/></td>
                                        <td align="left">
                                            <mtw:select klass="selectOptions" name="categoriaFk" list="categorias" emptyField="true" emptyFieldValue="Selecione..."/></td>
                                        <td><font class="error"><mtw:outError field="categoriaFk" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr class="odd">
                                        <td class="one" align="left" colspan="3">
                                            <mtw:input type="hidden" value="111111" name="codigoExistente" />
                                    <font class="errors"><mtw:outError field="codigoExistente" ><mtw:out/></mtw:outError></font>
                                    </td>
                                    </tr>
                                </table>
                            </div>
                            <div class="faixasForm" style="width: 400px;height: 200px;">
                                <h3></h3>
                                <table class="displaytag" width="100%">
                                    <tr class="odd">
                                        <td width="40%" class="one" align="left">
                                            <mtw:label klass="obrig" value="Preço de Custo:" />
                                        </td>
                                        <td width="52%">
                                            <mtw:input name="valorCusto" id="valorCusto" klass="inputNumber" maxlength="11" />
                                        </td>
                                        <td width="8%"><font class="error"><mtw:outError field="valorCusto" ><mtw:out/></mtw:outError></font></td>
                                    </tr>     
                                    <tr class="even">
                                        <td class="one" align="left">
                                            <mtw:label klass="obrig" value="Preço de Venda:" />
                                        </td>
                                        <td>
                                            <mtw:input name="valorVenda" id="valorVenda" klass="inputNumber" maxlength="11" />
                                        </td>
                                        <td><font class="error"><mtw:outError field="valorVenda" ><mtw:out/></mtw:outError></font></td>
                                    </tr>                                                            
                                    <tr class="odd">
                                        <td class="one" align="left">
                                            <mtw:label klass="obrig" value="Estoque Atual:" />
                                        </td>
                                        <td>
                                            <mtw:input name="estoqueAtual" id="estoqueAtual" klass="inputNumber" maxlength="11" extra="onkeypress=return somenteNumero(event)"/>
                                        </td>
                                        <td><font class="error"><mtw:outError field="estoqueAtual" ><mtw:out/></mtw:outError></font></td>
                                    </tr>     
                                    <tr class="even">
                                        <td class="one" align="left">
                                            <mtw:label klass="obrig" value="Estoque Mínimo:" />
                                        </td>
                                        <td>
                                            <mtw:input name="estoqueMinimo" id="estoqueMinimo" klass="inputNumber" maxlength="11" extra="onkeypress=return somenteNumero(event)"/>
                                        </td>
                                        <td><font class="error"><mtw:outError field="estoqueMinimo" ><mtw:out/></mtw:outError></font></td>
                                    </tr>                                                            
                                    <tr class="odd">
                                        <td colspan="3" class="one" align="left">
                                            <mtw:label klass="obrig" value="Alerta de Estoque Mínimo:" />
                                            <input type="radio" name="alerta" value="true" <c:if test="${alerta}" >checked</c:if> />Sim
                                            <input type="radio" name="alerta" value="false" <c:if test="${alerta==false}" >checked</c:if> />Não
                                        </td>
                                    </tr>                                                            
                                    <tr class="odd">
                                        <td class="one" align="left" colspan="3">
                                            <mtw:input type="hidden" value="111111" name="valorError" />
                                    <font class="errors"><mtw:outError field="valorError" ><mtw:out/></mtw:outError></font>
                                    </td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                        <td width="40%" valign="top">
                            <div class="faixasForm" style="width: 320px;height:200px;">
                                <h3></h3>
                                <table width="100%">
                                    <tr>
                                        <td width="100%">
                                    <legend></legend>
                                    <iframe src="produtoCreate.definir.do" width="100%" height="195" 
                                            marginwidth="0" marginheight="10" frameborder="0" scrollbar="no" scrolling="no" >
                                    </iframe>
                                    </td>
                                    </tr>
                                </table>
                            </div>
                            <div class="faixasForm" style="width: 320px;height: 120px;">
                                <h3></h3>
                                <table class="displaytag" width="100%">
                                    <tr class="odd">
                                        <td class="one" align="left">
                                            <mtw:label klass="obrig" value="Código de Barras:"/>
                                        </td>
                                    </tr>
                                    <tr class="even">
                                        <td class="one" align="left">
                                            <mtw:input klass="input" type="text" name="codigoBarras" maxlength="115"/>
                                        </td>
                                    </tr>
                                    <tr class="odd"><td></td></tr>
                                </table>
                            </div>
                        </td>
                        <td width="20%"></td>
                    </tr>
                </table>
            </div>
            <div width="98%" class="faixasForm">
                <h3></h3>
                <table width="98%" >
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Observações:"/></td>
                        <td>
                            <mtw:textarea name="observacao" id="observacao" cols="70" rows="8" maxlength="299"/>
                        </td>
                    </tr>
                </table>
            </div>
            <table width="100%" >
                <tr>
                    <td width="100%" class="panelButtonForm">
                        <mtw:buttonAction action="produtoRead.do" klass="botao" name="Cancelar" value="Cancelar"/>
                        <mtw:submit klass="botao" value="Salvar"/>
                    </td>
                </tr>
            </table>
        </mtw:bean>
    </mtw:form>
</div>