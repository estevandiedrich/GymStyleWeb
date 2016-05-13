
<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/funcionario/update/identificacao.js"></script>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/maskMoney.js" type="text/javascript"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>

<div class="title_bottom">
    <mtw:form method="post" action="funcionarioUpdateIdentificacao.do" name="form">
        <!--mtw:bean value="pojo"-->
            <%@include file="../topo.jsp" %>
            <h3>Editar</h3>
            <table width="100%">
                <tr>
                    <td width="100%">
                        <mtw:input type="hidden" name="id" id="id"/>
                        <mtw:input type="hidden" name="acao" id="acao"/>
                    </td>
                </tr>
                <tr>
                    <td class="title_bottom">
                        <mtw:buttonAction klass="aba" id="eventoInformacoes" action="funcionarioUpdateInformacao.do" name="informacoes" value="Informações"/>
                        <input class="abaCurrent" id="eventoIdentificacao" type="button" value="Identificação" />
                        <input type="button" class="aba proximo" id="eventoAcessos" name="proximo" value="Acesso" title="Acesso!" />

                    </td>
                </tr>
                <tr>
                    <td>
                        <div>
                            <table width="100%">
                                <tr>
                                    <td>
                                        <div class="title_bottom">
                                            <!--mtw:form action="digitalCreate.do" method="post" name="formIdentificacao"-->
                                            <!--mtw:bean value="pojo"-->
                                                <table width="100%">
                                                    <tr>
                                                        <td >
                                                            <mtw:input type="hidden" name="varCatraca" id="varCatraca" value="1" />
                                                            <mtw:input type="hidden" name="varSistema" id="varSistema" value="1" />
                                                            <%@include file="../info.jsp" %>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td style="padding-bottom: 10px"></td>
                                                    </tr>
                                                    <tr>
                                                        <td width="100%">
                                                            <h1 id="eventoCatraca" class="menos" title="Selecione">Catraca</h1>
                                                            <h4></h4>
                                                            <div id="div-catraca" >
                                                                <legend></legend>
                                                                <div><%@include file="identificacao/identificacaoCatraca.jsp" %>
                                                                </div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="100%">
                                                            <br>
                                                            <h1 id="eventoSistema" class="mais" title="Selecione">Software</h1>
                                                            <h4></h4>
                                                            <div id="div-sistema" >
                                                                <legend></legend>
                                                                <div>
                                                                    <%@include file="identificacao/identificacaoSistema.jsp" %>
                                                                </div>
                                                            </div>
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
                        <mtw:buttonAction klass="botao" action="funcionarioRead.do" name="Cancelar" value="Cancelar"/>
                        <mtw:buttonAction klass="botao" action="funcionarioUpdateInformacao.do" name="anterior" value="Anterior"/>
                        <input type="button" class="botao proximo" name="proximo" id="proximo" value="Próximo" title="Próximo!" />
                        <input type="button" class="botao" name="salvar" id="salvar" value="Salvar" title="Salvar!" />
                    </td>
                </tr>
            </table>
        <!--/mtw:bean-->
    </mtw:form>
</div>