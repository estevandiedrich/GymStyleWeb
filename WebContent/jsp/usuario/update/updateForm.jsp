
<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/usuario/update.js"></script>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/maskMoney.js" type="text/javascript"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>


<div class="title_bottom">
    <mtw:form method="post" action="usuarioUpdate.do" name="form">
        <mtw:bean value="pojo">
            <%@include file="../topo.jsp" %>
            <h3>Editar</h3>
            <table width="100%">
                <tr>
                    <td width="100%">
                        <mtw:input type="hidden" id="abaSelect" name="abaSelect" value="eventoInformacoes"/>
                        <mtw:input type="hidden" name="varDadosPessoais" value="1"/>
                        <mtw:input type="hidden" name="id" id="id"/>
                    </td>
                </tr>
                <tr>
                    <td class="title_bottom">
                        <input class="abaCurrent" id="eventoInformacoes" type="button" value="Informações" />
                        <input class="aba" id="eventoIdentificacao" type="button" value="Identificação" />
                        <input class="aba" id="eventoPlanos" type="button" value="Planos"/>
                        <!--input class="aba" id="eventoAcessos" type="button" value="Acesso" style="display: inline"/-->
                    </td>
                </tr>
                <tr>
                    <td>
                        <div id="informacoes">
                            <table width="100%">
                                <tr><td><%@include file="informacoes.jsp" %></td></tr>
                            </table>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div id="identificacao">
                            <table width="100%">
                                <tr><td><%@include file="identificacao.jsp" %></td></tr>
                            </table>
                        </div>
                        <div id="planos">
                            <table width="100%">
                                <tr><td><%@include file="../plano/planos.jsp" %></td></tr>
                            </table>
                        </div>
                        <div id="acessos">
                            <!--table width="100%">
                                <tr>
                                    <td><!--%@include file="acesso/horarioForm.jsp" %></td>
                                </tr>
                            </table-->
                        </div>
                    </td>
                </tr>
                <tr>
                    <td width="100%" class="panelButtonForm">
                        <mtw:buttonAction klass="botao" action="usuarioRead.do" name="Cancelar" value="Cancelar"/>
                        <mtw:buttonAction klass="botao" id="buttonSalvarUpdateUsuario" value="Salvar" name="buttonSalvarUpdateUsuario" onclick="validaForm()"/>
                    </td>
                </tr>
            </table>
        </mtw:bean>
    </mtw:form>
</div>