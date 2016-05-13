
<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="style/styleModal.css" type="text/css"/>

<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/maskMoney.js" type="text/javascript"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>
<script type="text/javascript" src="js/contabancaria/manager.js"></script>

<mtw:bean value="pojo" >
    <div id="windowRetirada" ><div id="statusRetirada"><%@include file="retiradaForm.jsp" %> </div></div>
    <div id="windowEntrada" ><div id="statusEntrada"><%@include file="entradaForm.jsp" %></div></div>

    <table class="topo" width="100%">
        <tr>
            <td width="85%">
                <h1>Gerenciamento de Conta Bancária</h1>
            </td>
            <td width="30%" align="right"></td>
        </tr>
    </table>
</mtw:bean>
<div class="faixasForm" id="selecioneConta" >
    <h3>Selecione uma Conta</h3>
    <table width="100%" style="height: 35px" >
        <tr>
            <td width="12%" align="right" valign="center">
        <font id="eventoDetalhesConta" class="" title="Visualizar detalhes da conta!" style="font-weight: bold">Conta:</font>
        </td>
        <td width="30%"  valign="center" >
            <mtw:select klass="selectOptions" style="width: 278px" name="conta" id="conta" list="contas" emptyField="true" defEmptyFieldValue="0" emptyFieldValue="Selecione..."/>
        </td>
        <td width="58%"  valign="center">
            <div id="msgSelecioneConta" class="errors" style="display: none;width: 300px">Selecione conta!</div>
        </td>
        </tr>
    </table>
    <div id="infoConta" width="100%">
        <%@include file="infoConta.jsp" %> 
    </div>
</div>

<div>
    <div class="faixasForm"  id="divMovimentacao" >
        <table width="96%">
            <tr>
                <td width="30%">
                    <table width="100%" >
                        <tr>
                            <td colspan="3"><h1>Opções</h1></td>
                        </tr>
                        <tr>
                            <td width="12%" >
                                <mtw:hasAuthorization permission="entradaContaBancaria">
                                    <div class="entradaCaixaInativo" id="entradaConta" > Entrada</div>
                                </mtw:hasAuthorization>
                            </td>
                            <td width="12%" >
                                <mtw:hasAuthorization permission="retiradaContaBancaria">
                                    <div class="retiradaCaixaInativo" id="retiradaConta" > Retirada</div>
                                </mtw:hasAuthorization>
                            </td>
                        </tr>
                    </table>
                </td>
                <td width="40%"  class="one">
                    <table width="100%" >
                        <tr>
                            <td colspan="4"><h1>Mostrar Movimentação</h1></td>
                        </tr>
                        <tr>
                            <td >
                                <mtw:label klass="obrig" value="De:" />
                            </td>
                            <td align="left">
                                <div>
                                    <mtw:input klass="inputDate" name="criterioInicio" id="criterioInicio" extra="onkeyup=readRegistros()"/>
                                </div>
                            </td>
                            <td align="left" >
                                <mtw:label klass="obrig" value="Até:" />
                            </td>
                            <td align="left" >
                                <mtw:input klass="inputDate" name="criterioFim" id="criterioFim" extra="onkeyup=readRegistros()"/>
                            </td>
                            <td>
                                <mtw:buttonAction id="filtrar" value="Filtrar" name="filtrar" onclick="readRegistros()"/>
                            </td>
                        </tr>
                    </table>
                </td>
                <td width="30%"></td>
            </tr>
        </table>
    </div>
    <div class="faixasForm" >
        <div id="content">
            <%@include file="corpo.jsp" %>
        </div>
    </div>
</div>