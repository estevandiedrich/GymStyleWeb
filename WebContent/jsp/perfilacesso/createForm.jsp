<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="title_bottom">
    <%@include file="topo.jsp" %>
    <h3>Novo</h3>
    <mtw:form method="post" action="perfilAcessoCreate.do">
        <mtw:bean value="pojo">
            <table width="98%" align="center" class="faixasForm">
                <tr>
                    <td class="one">
                        <mtw:input type="hidden" name="id"/>
                        <mtw:label klass="obrig" value="Identificação"/>
                    </td>
                    <td width="25%">
                        <mtw:input klass="input" type="text" name="perfilAcesso"/>
                    </td>
                    <td width="50%">
                        <font class="errors"><mtw:outError field="perfilAcesso" ><mtw:out/></mtw:outError></font>
                        </td>
                    </tr>
                </table>
                <table width="100%" align="center" style="font-size: 13px;">
                    <tr>
                        <td colspan="4"><hr></hr></td>
                    </tr>
                    <tr>
                        <td>
                            <div class="top_frame"></div>
                            <div class="middle_frame" style="width: 190px;">
                                <h4>1°Período</h4>
                                <table align="center">
                                    <tr>
                                        <td class="one"></td>
                                        <td><mtw:label value="Entrada:"/></td>
                                    <td><mtw:inputMask klass="inputTime" name="entrada1"textAlign="center"  maskCustom="99:99"/></td>
                                </tr>
                                <tr>
                                    <td class="one"></td>
                                    <td><mtw:label value="Saída:"/></td>
                                    <td><mtw:inputMask klass="inputTime" name="saida2" textAlign="center" maskCustom="99:99"/></td>
                                </tr>
                            </table>
                            <div class="clear"></div>
                        </div>
                        <div class="bottom_frame"></div>
                    </td>
                    <td>
                        <div class="top_frame"></div>
                        <div class="middle_frame" style="width: 190px;">
                            <h4>2°Período</h4>
                            <table align="center">
                                <tr>
                                    <td class="one"></td>
                                    <td><mtw:label value="Entrada:"/></td>
                                    <td><mtw:inputMask klass="inputTime" name="entrada3" textAlign="center"  maskCustom="99:99"/></td>
                                </tr>
                                <tr>
                                    <td class="one"></td>
                                    <td><mtw:label value="Saída:"/></td>
                                    <td><mtw:inputMask klass="inputTime" name="saida4" textAlign="center" maskCustom="99:99"/></td>
                                </tr>
                            </table>
                            <div class="clear"></div>
                        </div>
                        <div class="bottom_frame"></div>
                    </td>
                    <td>
                        <div class="top_frame"></div>
                        <div class="middle_frame" style="width: 190px;">
                            <h4>3°Período</h4>
                            <table align="center">
                                <tr>
                                    <td class="one"></td>
                                    <td><mtw:label value="Entrada:"/></td>
                                    <td><mtw:inputMask klass="inputTime" name="entrada5" textAlign="center" maskCustom="99:99"/></td>
                                </tr>
                                <tr>
                                    <td class="one"></td>
                                    <td><mtw:label value="Saída:"/></td>
                                    <td><mtw:inputMask klass="inputTime" name="saida6" textAlign="center" maskCustom="99:99"/></td>
                                </tr>
                            </table>
                            <div class="clear"></div>
                        </div>
                        <div class="bottom_frame"></div>
                    </td>
                    <td>
                        <div class="top_frame"></div>
                        <div class="middle_frame" style="width: 190px;">
                            <h4>4°Período</h4>
                            <table align="center">
                                <tr>
                                    <td class="one"></td>
                                    <td><mtw:label value="Entrada:"/></td>
                                    <td><mtw:inputMask klass="inputTime" name="entrada7" textAlign="center" maskCustom="99:99"/></td>
                                </tr>
                                <tr>
                                    <td class="one"></td>
                                    <td><mtw:label value="Saída:"/></td>
                                    <td><mtw:inputMask klass="inputTime" name="saida8"  textAlign="center" maskCustom="99:99"/></td>
                                </tr>
                            </table>
                            <div class="clear"></div>
                        </div>
                        <div class="bottom_frame"></div>
                    </td>
                </tr>
            </table>
            <table width="95%" align="center">
                <tr>
                    <td width="100%"  align="center">
                        <mtw:buttonAction klass="aplicar" name="aplicar" value="Aplicar" onclick="eventoAplicarFaixas()"/>
                    </td>
                </tr>
            </table>
            <h1>Dias da Semana</h1>
            <table width="95%" align="center" class="faixasForm">
                <tr>
                    <td class="chek">
                        <input type="checkbox" name="todos" <c:if test="${todos}">checked="true"</c:if> id="todos" onclick="eventoTodosAplicarFaixas()" /> <mtw:label value="Todos"/>
                    </td>
                    <td>
                        <h4>1°Período</h4>
                    </td>
                    <td>
                        <h4>2°Período</h4>
                    </td>
                    <td>
                        <h4>3°Período</h4>
                    </td>
                    <td>
                        <h4>4°Período</h4>
                    </td>
                </tr>
                <tr>
                    <td class="chek">
                        <input type="checkbox" name="domingo" id="domingo" <c:if test="${domingo}">checked="true"</c:if>  value="0" /> <mtw:label  value="Domingo"/>
                        <mtw:out value="domingo"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="domingo1" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="domingo2" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="domingo3" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="domingo4" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="domingo5" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="domingo6" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="domingo7" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="domingo8" textAlign="center" maskCustom="99:99"/>
                    </td>
                </tr>
                <tr>
                    <td class="chek">
                        <INPUT TYPE="checkbox" NAME="segunda" <c:if test="${segunda}">checked="true"</c:if>  id="segunda" value="0"/> <mtw:label  value="Segunda"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="segunda1" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="segunda2" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="segunda3" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="segunda4" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="segunda5" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="segunda6" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="segunda7" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="segunda8" textAlign="center" maskCustom="99:99"/>
                    </td>
                </tr>
                <tr>
                    <td class="chek">
                        <INPUT TYPE="checkbox" NAME="terca" <c:if test="${terca}">checked="true"</c:if>  id="terca" VALUE="0"/> <mtw:label  value="Terça"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="terca1" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="terca2" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="terca3" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="terca4" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="terca5" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="terca6" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="terca7" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="terca8" textAlign="center" maskCustom="99:99"/>
                    </td>
                </tr>
                <tr>
                    <td class="chek">
                        <INPUT TYPE="checkbox" NAME="quarta" <c:if test="${quarta}">checked="true"</c:if>  id="quarta" VALUE="0"/> <mtw:label  value="Quarta"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="quarta1" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="quarta2" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="quarta3" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="quarta4" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="quarta5" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="quarta6" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="quarta7" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="quarta8" textAlign="center" maskCustom="99:99"/>
                    </td>
                </tr>
                <tr>
                    <td class="chek">
                        <INPUT TYPE="checkbox" NAME="quinta" <c:if test="${quinta}">checked="true"</c:if>  id="quinta" VALUE="0"/> <mtw:label  value="Quinta"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="quinta1" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="quinta2" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="quinta3" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="quinta4" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="quinta5" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="quinta6" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="quinta7" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="quinta8" textAlign="center" maskCustom="99:99"/>
                    </td>
                </tr>
                <tr>
                    <td class="chek">
                        <INPUT TYPE="checkbox" NAME="sexta" <c:if test="${sexta}">checked="true"</c:if>  id="sexta" VALUE="0"/> <mtw:label  value="Sexta"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="sexta1" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="sexta2" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="sexta3" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="sexta4" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="sexta5" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="sexta6" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="sexta7" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="sexta8" textAlign="center" maskCustom="99:99"/>
                    </td>
                </tr>
                <tr>
                    <td class="chek">
                        <INPUT TYPE="checkbox" NAME="sabado" <c:if test="${sabado}">checked="true"</c:if> id="sabado" VALUE="0"/> <mtw:label  value="Sabado"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="sabado1" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="sabado2" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="sabado3" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="sabado4" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="sabado5" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="sabado6" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="sabado7" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="sabado8" textAlign="center" maskCustom="99:99"/>
                    </td>
                </tr>
                <tr>
                    <td class="chek">
                        <INPUT TYPE="checkbox" NAME="feriado" <c:if test="${feriado}">checked="true"</c:if>  id="feriado" VALUE="0"/>
                        <mtw:label  value="Feriado"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="feriado1" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="feriado2" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="feriado3" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="feriado4" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="feriado5" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="feriado6" textAlign="center" maskCustom="99:99"/>
                    </td>
                    <td>
                        <mtw:inputMask klass="inputTime" name="feriado7" textAlign="center" maskCustom="99:99"/>
                        <mtw:inputMask klass="inputTime" name="feriado8" textAlign="center" maskCustom="99:99"/>
                    </td>
                </tr>
            </table>
            <h1>Catracas</h1>
            <table width="95%" align="center" class="faixasForm">
                <tr>
                    <td class="chek">
                        <mtw:checkboxes list="dispositivos" id="dispositivo" name="dispositivo" />
                    </td>
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