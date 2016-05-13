<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/ficha/fichaCreate.js"></script>
<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.10.2.custom.min.js"></script>
<!--script src="js/jquery.min.js" type="text/javascript"></script-- Nao pode usar junto com (jquery-ui-1.10.2)-->
<script src="js/maskMoney.js" type="text/javascript"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>

<div class="title_bottom">
    <table class="topo" width="100%">
        <tr>
            <td width="80%">
                <h1>Fichas de Treinos</h1>
            </td>
            <td align="right" id="filtro" width="20%">
                <div id="item">
                    <mtw:bean value="usuario">
                        <a id="list" href="usuarioFichasRead.do?idUsuario=<mtw:out value="id"/>">Listar</a>
                        <a id='novo' title='Nova Ficha!' href="fichaCreate.do?idUsuario=<mtw:out value="id"/>">Nova Ficha</a>
                    </mtw:bean>
                </div>
            </td>
        </tr>
    </table>
    <h3>Novo</h3>
    <mtw:form method="post" action="fichaCreate.do" name="formFicha">
        <mtw:bean value="pag.list">
            <mtw:input type="hidden" name="status" id="status" value=""/>
            <table width="100%" >
                <tr>
                    <td width="100%" valign="top" >
                        <mtw:bean value="usuario">
                            <div class="faixasForm" style="width: 98%;min-height: 100px" >
                                <h1 id="serie">Ficha de Aluno</h1>
                                <table width="100%" class="displaytag">
                                    <thead>
                                        <tr>
                                            <th width="50%">Aluno</th>
                                            <th width="20%">CPF</th>
                                            <th width="15%">Telefone</th>
                                            <th width="15%">Celular</th>
                                        </tr>
                                    </thead>
                                    <tbody id="tBody">
                                        <tr class="odd">
                                            <td align="center" class="one"><mtw:out value="usuario"/></td>
                                            <td align="center"><mtw:out value="cpf"/></td>
                                            <td align="center"><mtw:out value="telefone"/></td>
                                            <td align="center">
                                                <mtw:out value="celular"/>
                                                <mtw:input type="hidden" name="idUsuario" value="${usuario.id}"/>
                                                <%--<mtw:input type="hidden" name="idUsuario" value="${usuario.id}"/>--%>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </mtw:bean>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div>
                            <table class="faixasForm" style="width: 98%;">
                                <tr>
                                    <td width="100%">
                                        <div>
                                            <table width="100%">
                                                <tr>
                                                    <td>
                                                        <div class="form2">
                                                            <table id="fundoMenu" style="width: 100%;">                                                                
                                                                <tr>
                                                                    <td valign="top">
                                                                        <h3></h3>
                                                                        <div class="faixasForm" style="width: 99.5%;margin-left: 1px;min-height: 80px" >
                                                                            <h1 id="serie">Período da Ficha</h1>
                                                                            <table width="100%" id="tablePeriodo">
                                                                                <tr>
                                                                                    <td align="left" width="5%" class="one">
                                                                                        <mtw:label value="Início:" klass="obrig" />
                                                                                    </td>
                                                                                    <td align="left" width="10%">
                                                                                        <mtw:input klass="inputDate" name="periodoInicial" id="periodoInicial" />
                                                                                    </td>
                                                                                    <td align="left" width="25%">
                                                                                        <div id="periodoInicialError" class="errors" style="min-width: 75px"></div>
                                                                                    </td>
                                                                                    <td align="left" width="5%" >
                                                                                        <mtw:label value="Término:" klass="obrig" />
                                                                                    </td>
                                                                                    <td align="left" width="10%">
                                                                                        <mtw:input klass="inputDate" name="periodoFinal" id="periodoFinal" />
                                                                                    </td>
                                                                                    <td align="left" width="45%">
                                                                                        <div id="periodoFinalError" class="errors" style="min-width: 175px"></div>
                                                                                    </td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td colspan="6" align="center"></td>
                                                                                </tr>
                                                                            </table>
                                                                        </div>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="100%" valign="top" >
                                                                        <div style="padding: 4px;">
                                                                            <input type="hidden" name="treinoDias"/>
                                                                            <div id="treinoDias" class="errors"></div>
                                                                        </div>
                                                                        <%@include file="diasTreinos.jsp" %>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="100%" valign="top" >
                                                                        <div class="faixasForm " style="width: 100%;margin: 1px;">
                                                                            <table width="100%" >
                                                                                <tr>
                                                                                    <td class="title_bottom" style="padding-top:8px" >
                                                                                        <div>
                                                                                            <input class="aba-ficha" id="treino-a" type="button" title="Treino A!" value="Treino A" />
                                                                                            <input class="aba-ficha" id="treino-b" type="button" title="Treino B!" value="Treino B" />
                                                                                            <input class="aba-ficha" id="treino-c" type="button" title="Treino C!" value="Treino C" />
                                                                                            <input class="aba-ficha" id="treino-d" type="button" title="Treino D!" value="Treino D" />
                                                                                            <input class="aba-ficha" id="treino-e" type="button" title="Treino E!" value="Treino E" />
                                                                                            <input class="aba-ficha" id="treino-f" type="button" title="Treino F!" value="Treino F" />
                                                                                        </div>
                                                                                    </td>
                                                                                </tr>
                                                                            </table>
                                                                            <table width="99%">
                                                                                <tr><td><%@include file="gruposmusculares/a/divTreino.jsp" %></td></tr>
                                                                                <tr><td><%@include file="gruposmusculares/b/divTreino.jsp" %></td></tr>
                                                                                <tr><td><%@include file="gruposmusculares/c/divTreino.jsp" %></td></tr>
                                                                                <tr><td><%@include file="gruposmusculares/d/divTreino.jsp" %></td></tr>
                                                                                <tr><td><%@include file="gruposmusculares/e/divTreino.jsp" %></td></tr>
                                                                                <tr><td><%@include file="gruposmusculares/f/divTreino.jsp" %></td></tr>
                                                                            </table>
                                                                        </div>
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
                                    <td width="88%" valign="top">
                                        <h3></h3>
                                        <div class="faixasForm" style="width: 99.5%;margin-left: 1px;min-height: 150px" >
                                            <h1>Observação</h1>
                                            <table width="100%" >
                                                <tr>
                                                    <td align="left" width="100%" class="one">
                                                        <mtw:textarea name="descricao" id="descricao" cols="95" rows="8" maxlength="250"/>
                                                    </td>
                                                    <td><font class="errors"><mtw:outError field="descricao" ><mtw:out/></mtw:outError></font></td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="100%" class="panelButtonForm">
                                        <mtw:buttonAction action="usuarioFichasRead.do" klass="botao" name="Cancelar" value="Cancelar"/>
                                        <input type="button" class="botao" name="salvar" id="salvar" value="Salvar" title="Salvar ficha!" />
                                        <!--input type="button" class="botao" name="salvarAvancar" id="ordenar" value="Salvar e Ordenar" title="Avançar para ordenar exercícios!" /-->
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </td>
                </tr>
            </table>
        </mtw:bean>
    </mtw:form>
</div>