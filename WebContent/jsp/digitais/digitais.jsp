<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/digitais/digitais.js"></script>

<h3></h3>
<mtw:input name="mudouDigitais"  id="mudouDigitais" type="hidden" />
<div style="min-height: 380px">
    <table width="100%">
        <tr>
            <td colspan="2" align="center">
                <div id="carregando" style="display: none">
                    <%@include file="capturando.jsp" %>
                </div>
                <div id="retorno"></div>
            </td>
        </tr>        
        <tr>
            <td width="35%">
                <div class="corpoDigitais" id="corpoDigitais" style="height: 400px " >
                    <%@include file="digitalRead.jsp" %>
                </div>
            </td>
            <td width="65%" >
                <div class="corpoDigitais" id="digitaisCreate" style="width: 95%;height: 400px;" >
                    <table width="100%" >
                        <tr>
                            <td colspan="5">
                                <h4>Cadastrar</h4>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="5">
                                <h3 id="solicitacao"></h3>
                            </td>
                        </tr>
                        <tr>
                            <td class="one" align="right"><mtw:label klass="obrig" value="Opção:"/></td>
                            <td colspan="2" align="center">
                                <mtw:radiobuttons list="opcoes" name="opcao" defValue="1" extra="onchange=eventoSelectOpcao();" />
                            </td>
                            <td></td>
                            <td>
                                <a name="help" href="help.do" id="help" target="Blank" title="Caso não consiga utilizar o Hamister, selecione aqui para instalar os drivers!">Ajuda</a>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="5" valign="top">
                                <table width="100%" heigth="100%"
                                       <tr>
                                        <td valign="top" width="100%">
                                            <div class="title_bottom" id="catracas" style="display: none;width: 100%" >
                                                <table style="width: 100%">
                                                    <tr>
                                                        <td class="one" align="right"><mtw:label klass="obrig" value="Dedo:"/></td>
                                                        <td align="left" valign="top">
                                                            <div id="selectDigitais">
                                                                <mtw:select klass="selectOptions" id="dedo" name="dedo" list="dedos" emptyField="true" emptyFieldValue="Selecione..." extra="onchange=eventoSelectMao();" style="width: 155px;" />
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" ></td>
                                                        <td align="left" valign="top" >
                                                            &nbsp;<img id="mao" class="mao" src="images/mao.png" height="150px" width="150px" />
                                                        </td>
                                                        <td></td>
                                                    </tr>                                                    
                                                </table>
                                                <div class="faixasForm" style="height: 40px;width: 95%">
                                                    <label class="obrig">Catracas:</label>
                                                    <mtw:isEmpty test="dispositivos">
                                                        Catracas fora da rede!
                                                    </mtw:isEmpty>
                                                    <mtw:isEmpty test="dispositivos" negate="true" >
                                                        <mtw:radiobuttons list="dispositivos" id="dispositivo" name="dispositivo" />
                                                    </mtw:isEmpty>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top">
                                            <div>
                                                <div id="ramisterDiv" style="display: none">
                                                    <%@include file="applet.jsp" %>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top" align="center">
                                            <div class="faixasForm">
                                                <mtw:buttonAction name="capturar" id="btCapturar" klass="botao" value="Capturar" onclick="capturarDigital()"/>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </div>
            </td>
        </tr>
    </table>
</div>
<mtw:input name="templates" id="templates" type="hidden"/>