<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/modalidade/createForm.js"></script>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/maskMoney.js" type="text/javascript"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>

<div class="title_bottom">
    <%@include file="topo.jsp" %>
    <h3>Novo</h3>
    <mtw:form method="post" action="modalidadeCreate.do">
        <mtw:bean value="pojo">
            <table width="100%" >
                <tr>
                    <td width="100%">
                        <mtw:input type="hidden" name="var1" id="var1" value="2"  />
                        <mtw:input type="hidden" name="var2" id="var2" value="1"  />
                        <mtw:input type="hidden" name="var3" id="var3" value="1"  />
                        <mtw:input type="hidden" name="horarioImport" id="horarioImport" value="1" />
                        <mtw:input type="hidden" name="horarioNovo" id="horarioNovo" value="1" />
                    </td>
                </tr>
                <tr>
                    <td class="title_bottom">
                        <input class="abaCurrent" id="eventoModalidade" type="button" value="Modalidade" />
                        <input class="aba" id="eventoHorario" type="button" value="Horários"/>
                        <input class="aba" id="eventoCatraca" type="button" value="Catracas"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div id="modalidadeMod">
                            <h3></h3>
                            <table class="faixasForm" style="width: 600px">
                                <tr>
                                    <td class="one"><mtw:label klass="obrig" value="Modalidade:"/></td>
                                    <td><mtw:input klass="input" type="text" name="modalidade" id="modalidade" maxlength="49"/></td>
                                    <td><font class="errors"><mtw:outError field="modalidade" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                </table>
                                <div class="faixasForm" style="width: 600px">
                                    <font class="errors"><mtw:outError field="valorErrors" ><mtw:out/></mtw:outError></font>
                                    <table class="displaytag" >
                                        <thead>
                                            <tr><th title="Vezes por semana!">Quantidade:</th><th>Valor:</th></tr>
                                        </thead>
                                        <tbody>
                                            <tr class="odd">
                                                <td class="one"><mtw:label klass="obrig" value="1 x por semana:"/></td>
                                            <td align="center" class="one">
                                                <mtw:input name="valor11" id="valor11" klass="inputNumber"  maxlength="12" />
                                    <font class="errors"><mtw:outError field="valor11" ><mtw:out/></mtw:outError></font>
                                        </td>
                                        </tr>
                                        <tr class="even">
                                            <td class="one"><mtw:label klass="obrig" value="2 x por semana:"/></td>
                                        <td align="center" class="one">
                                            <mtw:input name="valor22" klass="inputNumber"  maxlength="12" />
                                    <font class="errors"><mtw:outError field="valor22" ><mtw:out/></mtw:outError></font>
                                        </td>
                                        </tr>
                                        <tr class="odd">
                                            <td class="one"><mtw:label klass="obrig" value="3 x por semana:"/></td>
                                        <td align="center" class="one">
                                            <mtw:input name="valor33" klass="inputNumber"  maxlength="12" />
                                    <font class="errors"><mtw:outError field="valor33" ><mtw:out/></mtw:outError></font>
                                        </td>
                                        </tr>
                                        <tr class="even">
                                            <td class="one"><mtw:label klass="obrig" value="4 x por semana:"/></td>
                                        <td align="center" class="one">
                                            <mtw:input name="valor44" klass="inputNumber" maxlength="12" />
                                    <font class="errors"><mtw:outError field="valor44" ><mtw:out/></mtw:outError></font>
                                        </td>
                                        </tr>
                                        <tr class="odd">
                                            <td class="one"><mtw:label klass="obrig" value="5 x por semana:"/></td>
                                        <td align="center" class="one">
                                            <mtw:input name="valor55" klass="inputNumber"  maxlength="12" />
                                    <font class="errors"><mtw:outError field="valor55" ><mtw:out/></mtw:outError></font>
                                        </td>
                                        </tr>
                                        <tr class="even">
                                            <td class="one"><mtw:label klass="obrig" value="6 x por semana:"/></td>
                                        <td align="center" class="one">
                                            <mtw:input name="valor66" klass="inputNumber"  maxlength="12" />
                                    <font class="errors"><mtw:outError field="valor66" ><mtw:out/></mtw:outError></font>
                                        </td>
                                        </tr>
                                        <tr class="odd">
                                            <td class="one" title="Livre de Quantidade de Acesso Semanal" ><mtw:label klass="obrig" value="Livre:"/></td>
                                        <td align="center" class="one">
                                            <mtw:input name="valor77" klass="inputNumber" maxlength="12" />
                                    <font class="errors"><mtw:outError field="valor77" ><mtw:out/></mtw:outError></font>
                                        </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div id="horarioMod">
                                <h3></h3>
                            <%@include file="createHorario.jsp" %>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div id="catracaMod">
                            <h3></h3>
                            <h1>Catracas</h1>
                            <table width="95%" align="center" class="faixasForm">
                                <tr>
                                    <td class="chek">
                                        <mtw:checkboxes list="dispositivos" id="dispositivo" name="dispositivo" />
                                    </td>
                                </tr>
                            </table>
                            <font class="errors" style="font-size: 12px;"><mtw:out value="catracasError"/></font>
                        </div>
                    </td>
                </tr>
            </table>
            <table width="100%" >
                <tr>
                    <td width="100%" class="panelButtonForm">
                        <mtw:buttonAction action="modalidadeRead.do" klass="botao" name="Cancelar" value="Cancelar"/>
                        <mtw:submit klass="botao" value="Salvar"/>
                    </td>
                </tr>
            </table>
        </mtw:bean>
    </mtw:form>
</div>