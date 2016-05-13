<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/funcionario/update/acesso.js" type="text/javascript" ></script>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/maskMoney.js" type="text/javascript"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>

<div class="title_bottom">
    <mtw:form method="post" action="funcionarioUpdateAcesso.do" name="form">
        <%@include file="../topo.jsp" %>
        <h3>Editar</h3>
        <table width="100%">
            <tr><td width="100%"><mtw:bean value="pojo"><mtw:input type="hidden" name="id" id="id"/></mtw:bean></td></tr>
            <tr>
                <td class="title_bottom">
                    <mtw:buttonAction klass="aba" id="eventoInformacoes" action="funcionarioUpdateInformacao.do" name="informacoes" value="Informações"/>
                    <mtw:buttonAction klass="aba" id="eventoIdentificacao" action="funcionarioUpdateIdentificacao.do" name="identificacao" value="Identificação"/>
                    <input class="abaCurrent" id="eventoAcessos" type="button" value="Acesso" />
                </td>
            </tr>
            <tr>
                <td>
                    <div class="title_bottom">
                        <table width="100%">
                            <tr>
                                <td >
                                    <%@include file="../info.jsp" %>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <mtw:bean value="acesso">
                        <input type="hidden" name="idAcesso" value="<mtw:out value='idAcesso'/> "/>
                        <mtw:input type="hidden" name="mudouFaixas" id="mudouFaixas"/>
                        <div>
                            <h1>Habilitar Acesso na Catraca</h1>
                            <table style="width: 180px" align="center" class="faixasForm displaytag">
                                <tr>
                                    <td width="50%" class="one odd"><input type="radio" name="temAcesso" id="temAcesso" checked="checked" value="sim" onclick="mostraAcesso(1)"/>Sim</td>
                                    <td width="50%" class="sub" ><input type="radio" name="temAcesso" <mtw:if test="temAcesso" value="nao">checked="checked" </mtw:if>  value="nao" onclick="mostraAcesso(2)"/>Não</td>
                                    </tr>
                                </table>
                            </div>
                            <div id="acessoDiv" class="faixasForm title_bottom" style="width: 98%">
                                <h1>Horário</h1>
                                <table align="center" width="100%" >
                                    <tr>
                                        <td>
                                            <input class="abaCurrent" id="abaLivre" type="button" value="Livre" onclick="mostraTabelaFaixas(2)" />
                                            <input class="aba" id="abaConfigurado" type="button" value="Configurado" onclick="mostraTabelaFaixas(1)" />
                                        <mtw:input type="hidden" name="livre" id="livre" value="true"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div id="tabelaFaixas" >
                                            <br><h3></h3>
                                            <h1>Faixas de Acesso</h1>
                                            <table align="center" class="faixasForm displaytag" style="width: 98%">
                                                <tr>
                                                    <th width="12%"><h4>Dia</h4></th>
                                                    <th width="22%"><h4>1°Período</h4></th>
                                                    <th width="22%"><h4>2°Período</h4></th>
                                                    <th width="22%"><h4>3°Período</h4></th>
                                                    <th width="22%"><h4>4°Período</h4></th>
                                                </tr>
                                                <tr class="even">
                                                    <td class="chek" align="center">
                                                        <mtw:label klass="obrig" value="Domingo"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="domingo1" id="domingo1" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="domingo2" id="domingo2" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="domingo3" id="domingo3" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="domingo4" id="domingo4" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="domingo5" id="domingo5" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="domingo6" id="domingo6" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="domingo7" id="domingo7" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="domingo8" id="domingo8" size="5" extra="title=Saída!"/>
                                                    </td>
                                                </tr>
                                                <tr class="odd">
                                                    <td class="chek" align="center">
                                                        <mtw:label klass="obrig" value="Segunda"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="segunda1" id="segunda1" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="segunda2" id="segunda2" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="segunda3" id="segunda3" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="segunda4" id="segunda4" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="segunda5" id="segunda5" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="segunda6" id="segunda6" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="segunda7" id="segunda7" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="segunda8" id="segunda8" size="5" extra="title=Saída!"/>
                                                    </td>
                                                </tr>
                                                <tr class="sub">
                                                    <td class="chek" align="center">
                                                        <mtw:label klass="obrig" value="Terça"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="terca1" id="terca1" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="terca2" id="terca2" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="terca3" id="terca3" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="terca4" id="terca4" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="terca5" id="terca5" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="terca6" id="terca6" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="terca7" id="terca7" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="terca8" id="terca8" size="5" extra="title=Saída!"/>
                                                    </td>
                                                </tr>
                                                <tr class="even">
                                                    <td class="chek" align="center">
                                                        <mtw:label klass="obrig" value="Quarta"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="quarta1" id="quarta1" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="quarta2" id="quarta2" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="quarta3" id="quarta3" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="quarta4" id="quarta4" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="quarta5" id="quarta5" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="quarta6" id="quarta6" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="quarta7" id="quarta7" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="quarta8" id="quarta8" size="5" extra="title=Saída!"/>
                                                    </td>
                                                </tr>
                                                <tr class="odd">
                                                    <td class="chek" align="center">
                                                        <mtw:label klass="obrig" value="Quinta"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="quinta1" id="quinta1" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="quinta2" id="quinta2" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="quinta3" id="quinta3" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="quinta4" id="quinta4" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="quinta5" id="quinta5" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="quinta6" id="quinta6" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="quinta7" id="quinta7" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="quinta8" id="quinta8" size="5" extra="title=Saída!"/>
                                                    </td>
                                                </tr>
                                                <tr class="sub">
                                                    <td class="chek" align="center">
                                                        <mtw:label klass="obrig" value="Sexta"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="sexta1" id="sexta1" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="sexta2" id="sexta2" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="sexta3" id="sexta3" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="sexta4" id="sexta4" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="sexta5" id="sexta5" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="sexta6" id="sexta6" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="sexta7" id="sexta7" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="sexta8" id="sexta8" size="5" extra="title=Saída!"/>
                                                    </td>
                                                </tr>
                                                <tr class="even">
                                                    <td class="chek" align="center">
                                                        <mtw:label klass="obrig" value="Sábado"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="sabado1" id="sabado1" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="sabado2" id="sabado2" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="sabado3" id="sabado3" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="sabado4" id="sabado4" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="sabado5" id="sabado5" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="sabado6" id="sabado6" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="sabado7" id="sabado7" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="sabado8" id="sabado8" size="5" extra="title=Saída!"/>
                                                    </td>
                                                </tr>
                                                <tr class="odd">
                                                    <td class="chek odd" align="center">
                                                        <mtw:label klass="obrig" value="Feriado"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="feriado1" id="feriado1" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="feriado2" id="feriado2" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="feriado3" id="feriado3" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="feriado4" id="feriado4" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="feriado5" id="feriado5" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="feriado6" id="feriado6" size="5" extra="title=Saída!"/>
                                                    </td>
                                                    <td>
                                                        <mtw:input klass="inputTime" name="feriado7" id="feriado7" size="5" extra="title=Entrada!"/>
                                                        <mtw:input klass="inputTime" name="feriado8" id="feriado8" size="5" extra="title=Saída!"/>
                                                    </td>
                                                </tr>
                                            </table>
                                            <mtw:input type="hidden" name="horarioError"/>
                                            <div style="padding-left: 10px;">
                                                <font class="errors"><mtw:outError field="horarioError" ><mtw:out/></mtw:outError></font>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div>
                                                <br><h3></h3>
                                                <h1>Catracas</h1>
                                                <table align="center" class="faixasForm displaytag" style="width: 98%">
                                                    <tr>
                                                        <th class="chek" colspan="<mtw:out value="dispositivos.size"/>">
                                                        <mtw:label klass="obrig" value="Catracas Online"/>
                                                    </th>
                                                </tr>
                                                <tr class="even">
                                                    <td class="one" align="left">
                                                        <mtw:checkboxes list="dispositivos" id="dispositivo" name="dispositivo" />
                                                        <mtw:isEmpty test="dispositivos" >Catracas fora da rede</mtw:isEmpty>
                                                        </td>
                                                    </tr>
                                                </table>
                                            <mtw:input type="hidden" name="catracasError"/>
                                            <div style="padding-left: 10px;padding-bottom: 4px">
                                                <font class="errors" style="font-size: 12px;"><mtw:outError field="catracasError" ><mtw:out/></mtw:outError></font>
                                                </div>
                                            <mtw:isEmpty test="dispositivosOffLine" negate="true">
                                                <table align="center" class="faixasForm displaytag" style="width: 98%">
                                                    <tr class="even">
                                                        <th class="chek" colspan="<mtw:out value="dispositivosOffLine.size"/>">
                                                            <mtw:label klass="obrig" value="Catracas Offline vinculadas a esta modalidade"/>
                                                        </th>
                                                    </tr>
                                                    <tr class="odd">
                                                        <td class="chek">
                                                            <mtw:list value="dispositivosOffLine" var="row">
                                                                <mtw:loop var="row">
                                                                    <img class="one" src="images/offline.png" /><mtw:out value="row" />
                                                                </mtw:loop>
                                                            </mtw:list>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="chek" colspan="<mtw:out value="dispositivosOffLine.size"/>">
                                                            <h4>Ao salvar, estas catracas deixaram de estar vinculadas</h4>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </mtw:isEmpty>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </mtw:bean>
                </td>
            </tr>
            <tr>
                <td width="100%" class="panelButtonForm">
                    <mtw:buttonAction klass="botao" action="funcionarioRead.do" name="Cancelar" value="Cancelar"/>
                    <mtw:buttonAction klass="botao" action="funcionarioUpdateIdentificacao.do" name="anterior" value="Anterior"/>
                    <mtw:buttonAction klass="botaoDes" name="proximo" disabled="true" value="Próximo" />
                    <mtw:submit klass="botao" value="Salvar" name="buttonSalvarUpdateUsuario" />
                </td>
            </tr>
        </table>
    </mtw:form>
</div>
