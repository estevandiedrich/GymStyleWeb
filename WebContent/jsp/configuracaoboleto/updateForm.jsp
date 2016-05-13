<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/configuracaoboleto/update.js"></script>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/maskMoney.js" type="text/javascript"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>

<div class="title_bottom">
    <table class="topo" width="100%">
        <tr>
            <td width="90%"><h1>Configurações Boleto</h1></td>
            <td align="right" id="filtro"  width="10%">
                <div id="item"><a id="list" href="configuracaoBoletoRead.do">Listar</a></div>
            </td>
        </tr>
    </table>
    <h3>Novo</h3>
    <mtw:form method="post" action="configurarBoleto.do">
        <mtw:bean value="pojo">
            <mtw:input klass="input" type="hidden" name="id" id="id"/>
            <table width="98%" class="faixasForm">
                <tr>
                    <td>
                        <h1>Cedente</h1>
                        <fieldset class="field">
                            <legend></legend>
                            <div>
                                <table>
                                    <tr>
                                        <td class="one"><mtw:label klass="obrig" value="Razão Social:"/></td>
                                        <td><mtw:input klass="input" type="text" name="cedenteRazaoSocial" id="cedenteRazaoSocial" maxlength="49"/></td>
                                        <td><font class="errors"><mtw:outError field="cedenteRazaoSocial" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr>
                                        <td class="one"><mtw:label klass="obrig" value="CNPJ:"/></td>
                                        <td><mtw:input klass="input" type="text" name="cedenteCnpj" id="cedenteCnpj"/></td>
                                        <td><font class="errors"><mtw:outError field="cedenteCnpj" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr><td><br></td></tr>
                                </table>
                            </div>
                        </fieldset>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h1>Conta Bancária</h1>
                        <fieldset class="field">
                            <legend></legend>
                            <div>
                                <table>
                                    <tr>
                                        <td class="one"><mtw:label klass="obrig" value="Banco:"/></td>
                                        <td>
                                            <mtw:select klass="selectOptions" name="bancoSelect" id="bancoSelect" list="bancos"
                                                        emptyField="true" emptyFieldValue="Selecione..." />
                                        </td>
                                        <td><font class="errors"><mtw:outError field="bancoSelect" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr>
                                        <td class="one"><mtw:label klass="obrig" value="Agência:"/></td>
                                        <td><mtw:input klass="inputNumber" type="text" name="bancoAgencia" maxlength="4"/></td>
                                        <td><font class="errors"><mtw:outError field="bancoAgencia" ><mtw:out/></mtw:outError></font></td>
                                    <td class="one"><mtw:label klass="obrig" value="Variação:"/></td>
                                    <td><mtw:input klass="inputTime" type="text" name="bancoAgenciaVariacao" maxlength="3"/></td>
                                    <td><font class="errors"><mtw:outError field="bancoAgenciaVariacao" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr>
                                        <td class="one"><mtw:label klass="obrig" value="Número da Conta:"/><br><label class="obrig" style="font-size:10px">(Código Cedente)</label> </td>
                                        <td><mtw:input klass="inputNumber" type="text" name="bancoNumero" maxlength="7"/></td>
                                        <td><font class="errors"><mtw:outError field="bancoNumeroDigito" ><mtw:out/></mtw:outError></font></td>
                                    <td class="one"><mtw:label klass="obrig" value="Dígito:"/></td>
                                    <td><mtw:input klass="inputTime" type="text" name="bancoNumeroDigito" maxlength="3"/></td>
                                    <td><font class="errors"><mtw:outError field="bancoNumeroDigito" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr>
                                        <td class="one"><mtw:label klass="obrig" value="Carteira:"/></td>
                                        <td>
                                            <div id="carteiraResult">
                                                <%@include file="carteiras.jsp" %>
                                            </div>
                                        </td>
                                        <td><font class="errors"><mtw:outError field="bancoCarteira" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr><td><br></td></tr>
                                </table>
                            </div>
                        </fieldset>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h1>Título</h1>
                        <fieldset class="field">
                            <legend></legend>
                            <div>
                                <table>
                                    <tr>
                                        <td class="one"><mtw:label klass="obrig" value="Nosso Número:"/></td>
                                        <td><mtw:input klass="inputNumber" type="text" name="tituloNossoNumero" maxlength="17"/></td>
                                        <td class="one"><mtw:label klass="obrig" value="Digito:"/></td>
                                        <td><mtw:input klass="inputTime" type="text" name="tituloDigitoNossoNumero" maxlength="3"/></td>
                                        
                                    </tr>
                                    <tr>
                                        <td class="one"><mtw:label klass="obrig" value="Tipo de Documento:"/></td>
                                        <td>
                                            <mtw:select klass="selectOptions" name="tipoDocumento" id="tipoDocumento" list="tipoDeTitulos"
                                                        emptyField="true" emptyFieldValue="Selecione..."/>
                                        </td>
                                        <td><font class="errors"><mtw:outError field="tipoDocumento" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr>
                                        <td class="one" ><mtw:label klass="obrig" value="Aceite:"/></td>
                                        <td>
                                            <input type="radio" name="aceite" title="SIM!" value="A"
                                                   <mtw:if test="aceite" value="A">checked=""</mtw:if> onclick="eventoSelectRadioCriterio(A)"/>A
                                            <input type="radio" name="aceite" title="NÃO!" value="N"
                                                   <mtw:if test="aceite" value="N">checked=""</mtw:if> onclick="eventoSelectRadioCriterio(N)"/>N
                                        </td>
                                        <td>
                                    <font class="errors"><mtw:outError field="aceite" ><mtw:out/></mtw:outError></font>
                                    <div style="display: none">O campo Aceite indica se o Pagador (quem recebe o boleto) aceitou o boleto, ou seja, se ele assinou o documento de cobrança que originou o boleto. O padrão é usar "Não" no aceite, pois nesse caso não é necessário a autorização do Pagador para protestar o título.
                                        Se escolher "Sim", o Beneficiário (quem emite o boleto) precisará de algum documento onde o Pagador reconhece a dívida para poder protestar o título.</div>        
                                    </td>
                                    </tr>
                                    <tr><td><br></td></tr>
                                </table>
                            </div>
                        </fieldset>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h1>Boleto</h1>
                        <fieldset class="field">
                            <legend></legend>
                            <div>
                                <table>
                                    <tr>
                                        <td class="one"><mtw:label klass="obrig" value="Local de Pagamento:"/></td>
                                        <td><mtw:input klass="inputAutenticacao" type="text" name="boletoLocalPagamento" maxlength="100" value=""/></td>
                                        <td><font class="errors"><mtw:outError field="boletoLocalPagamento" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr>
                                        <td class="one"><mtw:label klass="obrig" value="Instrução ao Sacado:"/></td>
                                        <td><mtw:input klass="inputAutenticacao" type="text" name="boletoInstrucaoSacado" maxlength="100"/></td>
                                        <td><font class="errors"><mtw:outError field="boletoInstrucaoSacado" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr>
                                        <td class="one"><mtw:label klass="obrig" value="Instrução 1:"/></td>
                                        <td><mtw:input klass="inputAutenticacao" type="text" name="boletoInstrucao1" maxlength="100"/></td>
                                        <td><font class="errors"><mtw:outError field="boletoInstrucao1" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr>
                                        <td class="one"><mtw:label klass="obrig" value="Instrução 2:"/></td>
                                        <td><mtw:input klass="inputAutenticacao" type="text" name="boletoInstrucao2" maxlength="100"/></td>
                                        <td><font class="errors"><mtw:outError field="boletoInstrucao2" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr>
                                        <td class="one"><mtw:label klass="obrig" value="Instrução 3:"/></td>
                                        <td><mtw:input klass="inputAutenticacao" type="text" name="boletoInstrucao3" maxlength="100"/></td>
                                        <td><font class="errors"><mtw:outError field="boletoInstrucao3" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr>
                                        <td class="one"><mtw:label klass="obrig" value="Instrução 4:"/></td>
                                        <td><mtw:input klass="inputAutenticacao" type="text" name="boletoInstrucao4" maxlength="100"/></td>
                                        <td><font class="errors"><mtw:outError field="boletoInstrucao4" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr>
                                        <td class="one"><mtw:label klass="obrig" value="Instrução 5:"/></td>
                                        <td><mtw:input klass="inputAutenticacao" type="text" name="boletoInstrucao5" maxlength="100"/></td>
                                        <td><font class="errors"><mtw:outError field="boletoInstrucao5" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr> 
                                        <td class="one"><mtw:label klass="obrig" value="Instrução 6:"/></td>
                                        <td><mtw:input klass="inputAutenticacao" type="text" name="boletoInstrucao6" maxlength="100"/></td>
                                        <td><font class="errors"><mtw:outError field="boletoInstrucao6" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr>
                                        <td class="one"><mtw:label klass="obrig" value="Instrução 7:"/></td>
                                        <td><mtw:input klass="inputAutenticacao" type="text" name="boletoInstrucao7" maxlength="100"/></td>
                                        <td><font class="errors"><mtw:outError field="boletoInstrucao7" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr>
                                        <td class="one"><mtw:label klass="obrig" value="Instrução 8:"/></td>
                                        <td><mtw:input klass="inputAutenticacao" type="text" name="boletoInstrucao8" maxlength="100"/></td>
                                        <td><font class="errors"><mtw:outError field="boletoInstrucao8" ><mtw:out/></mtw:outError></font></td>
                                    </tr>
                                    <tr><td><br></td></tr>
                                </table>
                            </div>
                        </fieldset>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="faixasForm" align="center" style="font-size: 12px;color: #EC0000 ;font-style: italic">Algumas informações como Sacado(Aluno), Data de Vencimento,Valor, entre outros ,<br>serão apresentadas quando gerar o boleto ao aluno!</td>
                </tr>
                <tr>
                    <td width="100%" class="panelButtonForm">
                        <mtw:buttonAction action="configuracaoBoletoRead.do" klass="botao" name="Cancelar" value="Cancelar"/>
                        <mtw:submit klass="botao" value="Salvar"/>
                    </td>
                </tr>
            </table>
        </mtw:bean>
    </mtw:form>
</div>