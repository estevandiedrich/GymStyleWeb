<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/funcionario/update/informacoes.js" type="text/javascript" ></script>

<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/maskMoney.js" type="text/javascript"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>

<div class="title_bottom">
    <mtw:form method="post" action="funcionarioUpdateInformacao.do" name="form">
        <mtw:bean value="pojo">
            <%@include file="../topo.jsp" %>
            <h3>Editar</h3>
            <table width="100%">
                <tr>
                    <td width="100%">
                        <mtw:input type="hidden" name="id" id="id"/>
                    </td>
                </tr>
                <tr>
                    <td class="title_bottom">
                        <input class="abaCurrent" id="eventoInformacoes" type="button" value="Informações" />
                        <mtw:buttonAction klass="aba" id="eventoIdentificacao" action="funcionarioUpdateInformacao.do?acao=identificacao" name="identificacao" value="Identificação"/>
                        <input class="abaDisabled" id="eventoAcessos" type="button" value="Acesso" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <div id="informacoes">
                            <table width="100%">
                                <tr>
                                    <td>
                                        <div class="title_bottom">
                                            <table width="100%">
                                                <tr>
                                                    <td width="100%">
                                                        <mtw:input type="hidden" name="var1" id="var1" value="1"  />
                                                        <mtw:input type="hidden" name="var2" id="var2" value="1"  />
                                                        <mtw:input type="hidden" name="var3" id="var3" value="1"  />
                                                        <mtw:input type="hidden" name="var4" id="var4" value="1"  />
                                                        <mtw:input type="hidden" name="var5" id="var5" value="1"  />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td >
                                                        <%@include file="../info.jsp" %>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <h1 id="eventoDadosPessoais"  class="menos"  title="Selecione">Dados Pessoais</h1>
                                                        <fieldset id="dadosPessoais" class="field">
                                                            <legend></legend>
                                                            <div>
                                                                <%@include file="../comum/dadosPessoais.jsp" %>
                                                            </div>
                                                        </fieldset>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="100%">
                                                        <h1 id="eventoLocal"  class="mais"  title="Selecione">Localidade</h1>
                                                        <fieldset id="localidade" class="field">
                                                            <legend></legend>
                                                            <div>
                                                                <%@include file="../comum/localidade.jsp" %>
                                                            </div>
                                                        </fieldset>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="100%">
                                                        <h1 id="eventoContato"  class="mais"  title="Selecione">Contato</h1>
                                                        <h4></h4>
                                                        <fieldset id="contato" class="field">
                                                            <legend></legend>
                                                            <div>
                                                                <%@include file="../comum/contato.jsp" %>
                                                            </div>
                                                        </fieldset>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="100%">
                                                        <h1 id="eventoFoto"  class="mais"  title="Selecione">Foto</h1>
                                                        <h4></h4>
                                                        <fieldset id="foto" class="field">
                                                            <legend></legend>
                                                            <div style="display: inline">
                                                                <h3></h3>
                                                                <iframe src="funcionarioUpdateInformacao.definir.do" width="100%" 
                                                                        <mtw:if test="navegador" value="Chrome">
                                                                            height="500" 
                                                                        </mtw:if>
                                                                        <mtw:if test="navegador" value="Chrome" negate="true">
                                                                            height="195"
                                                                        </mtw:if>
                                                                        marginwidth="0" marginheight="10"
                                                                        frameborder="0" scrollbar="no" scrolling="no">
                                                                </iframe>
                                                            </div>
                                                        </fieldset>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="100%">
                                                        <h1 id="eventoInformacoesAdd" class="mais"  title="Selecione">Informações Adicionais</h1>
                                                        <fieldset id="informacoesAdd" class="field">
                                                            <legend></legend>
                                                            <div>
                                                                <%@include file="../comum/informacoes.jsp" %>
                                                            </div>
                                                        </fieldset>
                                                    </td>
                                                </tr>
                                            </table>
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
                        <mtw:buttonAction klass="botaoDes" name="anterior" disabled="true" value="Anterior" />
                        <mtw:buttonAction klass="botao" action="funcionarioUpdateInformacao.do?acao=identificacao" name="proximo" value="Próximo"/>
                        <mtw:submit klass="botao" value="Salvar" name="buttonSalvarUpdateUsuario" />
                    </td>
                </tr>
            </table>
        </mtw:bean>
    </mtw:form>
</div>