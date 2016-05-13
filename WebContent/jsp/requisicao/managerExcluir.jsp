<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/requisicao/managerExcluir.js"></script>

<div class="title_bottom" style="width: 100%">
    <mtw:form action="${retorno}">
        <table class="faixasForm" width="99%" >
            <tr>
                <td width="80%">
                    <h1>Atualizando Aluno na Catraca</h1>
                </td>
                <td align="center" width="20%">
                    <a id="list" href="requisicaoRead.do">Listar</a>
                </td>
            </tr>
            <tr>
                <td width="100%" colspan="2">
                    <table class="faixasForm" width="98%">
                        <tr>
                            <td colspan="2"><h3></h3></td>
                        </tr>
                        <tr>
                            <td class="one" width="80%">
                                <mtw:label klass="obrig" value="Cancelando Plano de Aluno: "/><mtw:out value="usuario.usuario" />
                            </td>
                            <td class="one" width="20%">
                                <a id='vincular' title='Vincular'  href='vincularPlano.do?id=<mtw:out value="usuario.id" />'>Vincular Novo Plano</a>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td style="width: 100%" colspan="2">
                    <div id="corpo"  style="width: 100%">
                        <input type="hidden" name="idRequisicao" id="idRequisicao" value="<mtw:out value="idRequisicao"/>"/>
                        <input type="hidden" name="idPlanoUsuario" id="idPlanoUsuario" value="<mtw:out value="idPlanoUsuario"/>"/>
                        <input type="hidden" name="idUsuario" id="idUsuario" value="<mtw:out value="usuario.id"/>"/>
                        <div id="carregando"  style="width: 100%">
                            <!--%@include  file="carregando.jsp" %-->
                        </div>
                        <div id="result" style="width: 98%" >
                            <%@include file="destinos.jsp" %>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="100%" class="panelButtonForm" colspan="2">
                    <mtw:submit name="voltar" value="voltar" klass="botao" />
                </td>
            </tr>
        </table>
    </mtw:form>
</div>