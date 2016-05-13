
<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/usuario/update/identificacao.js"></script>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/maskMoney.js" type="text/javascript"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>

<div class="title_bottom">
    <mtw:form method="post" action="usuarioUpdateIdentificacao.do" name="form">
        <!--mtw:bean value="pojo"-->
            <%@include file="../topo.jsp" %>
            <h3>Editar</h3>
            <table width="100%">
                <tr>
                    <td width="100%"><mtw:input type="hidden" name="id" id="id"/></td>
                </tr>
                <tr>
                    <td class="title_bottom">
                        <mtw:buttonAction klass="aba" id="eventoInformacoes" action="usuarioUpdateInformacao.do" name="anterior" value="Informações" />
                        <input class="abaCurrent" id="eventoIdentificacao" type="button" value="Identificação" />
                        <mtw:buttonAction klass="aba" id="eventoPlanos" action="usuarioUpdateIdentificacao.do?acao=plano" name="anterior" value="Planos" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <div id="identificacao">
                            <table width="100%">
                                <tr>
                                    <td>
                                        <div class="title_bottom">
                                            <!--mtw:form action="digitalCreate.do" method="post" name="formIdentificacao"-->
                                                <!--mtw:bean value="pojo"-->
                                                    <table width="100%">
                                                        <tr>
                                                            <td >
                                                                <mtw:input type="hidden" name="varCatraca" id="varCatraca" value="1"  />
                                                                <mtw:input type="hidden" name="varSistema" id="varSistema" value="1"  />
                                                                <%@include file="../info.jsp" %>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="padding-bottom: 10px"><font class="errors" ><mtw:out value="msgDigitais"/></font></td>
                                                        </tr>
                                                        <tr>
                                                            <td width="100%">
                                                                <h1>Digitais</h1>
                                                                <fieldset id="digitais" class="field">
                                                                    <legend></legend>
                                                                    <div>
                                                                        <%@include file="../../digitais/digitais.jsp" %>
                                                                    </div>
                                                                </fieldset>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td width="100%">
                                                                <h1>Cartão Proximidade</h1>
                                                                <h4></h4>
                                                                <fieldset class="field">
                                                                    <legend></legend>
                                                                    <div>
                                                                        <br>
                                                                        <table class="form" >
                                                                            <tr>
                                                                                <td class="one"><mtw:label klass="obrig" value="Numero do Cartão:"/></td>
                                                                                <td>
                                                                                    <mtw:input type="hidden" name="cartaoProximidade2" />
                                                                                    <mtw:input klass="input" name="cartaoProximidade" id="cartaoProximidade" maxlength="10" />
                                                                                </td>
                                                                                <td>
                                                                                    <div class="errors" style="height: 40px"><mtw:outError field="cartaoProximidade" ><mtw:out/></mtw:outError></div>
                                                                                    </td>
                                                                                </tr>
                                                                            </table>
                                                                            <br>
                                                                        </div>
                                                                    </fieldset>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                <!--/mtw:bean-->
                                            <!--/mtw:form-->
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td width="100%" class="panelButtonForm">
                        <mtw:buttonAction klass="botao" action="usuarioRead.do" name="Cancelar" value="Cancelar"/>
                        <mtw:buttonAction klass="botao" action="usuarioUpdateInformacao.do" name="anterior" value="Anterior"/>
                        <mtw:buttonAction klass="botao" action="usuarioUpdateIdentificacao.do?acao=plano" name="proximo" value="Próximo"/>
                        <mtw:submit klass="botao" value="Salvar" name="buttonSalvarUpdateUsuario" />
                    </td>
                </tr>
            </table>
        <!--/mtw:bean-->
    </mtw:form>
</div>