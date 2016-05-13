<%@include file="../../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<mtw:hasAuthorization permission="identificacaoSoftwareManager" negate="true">
    <table width="100%" class="title_bottom">
        <tr>
            <td align="center" width="80%" class="errors">
                <div id="informe-permissoes" style="min-width: 500px;padding-bottom: 5px"><font class="errors" style="min-width: 500px">Seu Usuário não possui permissão de alterar dados de Identificação do Software</font></div>
            </td>
        </tr>
    </table>
</mtw:hasAuthorization>
<mtw:hasAuthorization permission="identificacaoSoftwareManager" >
    <table width="100%" >
        <tr>
            <td width="100%" valign="top" class="one">
                <div class="title_bottom">
                    <h1>Autenticação</h1>
                    <fieldset id="digitais" class="field">
                        <legend></legend>
                        <div>
                            <h3></h3>
                            <table class="form" >
                                <tr>
                                    <td class="one">Login:</td>
                                    <td>
                                        <input type="hidden" name="loginUsuario" id="loginUsuario" value="<mtw:out value="loginUsuario"/>"/>
                                        <input type="hidden" name="loginAux" id="loginAux"  value="<mtw:out value="loginAux"/>" />
                                        <mtw:input klass="input" type="text" name="login" id="login" extra="onkeyup=verificaLogin(this.value)" value="" />
                                    </td>
                                    <td>
                                <font class="errors"><mtw:outError field="login" ><mtw:out/></mtw:outError></font>
                                <div id="resultLogin" ><mtw:out value="retornoVerificaLogin"/><mtw:input type="hidden" name="retornoVerificaLogin" value=""/></div>                    
                                </td>
                                </tr>
                                <tr>
                                    <td class="one"></td><td></td>
                                </tr>
                                <tr>
                                    <td class="one">Senha:</td>
                                    <td><mtw:input klass="input" type="password" name="senha" id="senha" /></td>
                                    <td><font class="errors"><mtw:outError field="senha" > <mtw:out /></mtw:outError></font></td>
                                </tr>
                                <tr>
                                    <td class="one">Repita Senha:</td>
                                    <td><mtw:input klass="input" type="password" name="senha2"/></td>
                                    <td><font class="errors"><mtw:outError field="senha2" > <mtw:out /></mtw:outError></font></td>
                                </tr>
                            </table>
                        </div>
                    </fieldset>
                </div>
            </td>
        </tr>
        <tr>
            <td width="100%" valign="top" class="one">
                <div class="title_bottom">
                    <br>
                    <h1 title="Permissões">Permissões</h1>
                    <table width="100%">
                        <tr>
                            <td align="center" width="80%">
                                <div id="informe-permissoes" style="display: none;min-width: 500px;padding-bottom: 5px"><font class="errors" style="min-width: 500px">Informe Permissões</font></div>
                            </td>
                        </tr>
                    </table>
                    <fieldset id="permissao" class="field">
                        <legend></legend>
                        <table width="100%">
                            <tr>
                                <td class="title_bottom">
                                    <input class="aba-autenticacao current-autenticacao" id="cadastro" type="button" value="Cadastros" />
                                    <input class="aba-autenticacao" id="gerenciar" type="button" value="Gerenciar" />
                                    <input class="aba-autenticacao" id="configuracao" type="button" value="Configurações" />
                                    <input class="aba-autenticacao" id="relatorio" type="button" value="Relatórios" />
                                </td>
                                <td class="title_bottom" align="right">
                                    <table>
                                        <tr>
                                            <td>
                                                <input class="" type="checkbox" id="chek-todos" name="chek-todos" value="todos" /> Marcar/Desmarcar Todos
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <div style="min-height: 400px" id="div-principal">
                                        <%@include file="cadastro.jsp" %>
                                        <%@include file="gerenciar.jsp" %>
                                        <%@include file="configuracao.jsp" %>
                                        <%@include file="relatorio.jsp" %>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </div>
            </td>
        </tr>
    </table>
</mtw:hasAuthorization>
