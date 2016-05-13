<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/ficha/ordenar.js"></script>
<script src="js/jquery.js"></script>
<script src="js/jquery-ui-1.10.2.custom.min.js"></script>
<!--script src="js/jquery.min.js" type="text/javascript"></script-- Nao pode usar junto com (jquery-ui-1.10.2)-->

<div class="title_bottom">
    <table class="topo" width="100%">
        <tr>
            <td width="85%">
                <h1>Fichas de Treinos</h1>
            </td>
            <td align="right" id="filtro" width="15%">
                <div id="item">
                    <mtw:bean value="usuario">
                        <a id="list" href="usuarioFichasRead.do?idUsuario=<mtw:out value="id"/>">Listar</a>
                    </mtw:bean>
                </div>
            </td>
        </tr>
    </table>
    <h3>Ordenação de Exercícios</h3>
    <mtw:form method="post" action="ordenarFicha.do" name="formOrdenar">
        <mtw:bean value="pag.list">
            <table width="98%" class="faixasForm">
                <tr>
                    <td width="100%" valign="top" >
                        <mtw:input type="hidden" name="idFicha" value=""/>
                        <mtw:bean value="usuario">
                            <div class="faixasForm" style="width: 98%;min-height: 100px" >
                                <h1 id="serie">Informações</h1>
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
                                                <%--<mtw:input type="hidden" name="idUsuario" value="id"/>--%>
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
                        <div class="faixasForm" style="background-color: #FFF">
                            <table class="faixasForms" style="width: 99%;">
                                <tr>
                                    <td width="88%" valign="top">
                                        <h1 title="Selecione e arraste para ordenar!" >Ordene os Exercícios<span style="font-size:14px;font-weight: normal;font-style: italic"> - Arraste para cima e para baixo para ordenar</span></h1>
                                        <table style="width: 100%">
                                            <tr>
                                                <td class="one">
                                                    <div class="top_frame"></div>
                                                    <div id="treinoAOrdem" >
                                                        <input name="treinoAHtml" id="treinoAHtml" type="hidden" value=""/>
                                                        <div class="middle_frame" id="treinoA" style="width: 190px; height: ${div1}px;">
                                                            <h4>Treino A</h4>
                                                            <div id="menu">
                                                                <ul id="sortableTreinoA" >
                                                                    <mtw:list value="exerciciosA">
                                                                        <mtw:isEmpty></mtw:isEmpty>
                                                                        <mtw:loop var="row" counterStart="0" counter="i">
                                                                            <li><div style="display:none">#ID#<mtw:out value="exercicio.id"/>#/ID#</div><h9 class="head"><a href="#" title="<mtw:out value="exercicio.exercicio"/> !" ><mtw:out value="exercicio.exercicio" max="17"/></a></h9></li>
                                                                        </mtw:loop>
                                                                    </mtw:list>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <div class="bottom_frame"></div>
                                                    </div>
                                                </td>
                                                <td class="one">
                                                    <div class="top_frame"></div>
                                                    <div id="treinoBOrdem" >
                                                        <input name="treinoBHtml" id="treinoBHtml" type="hidden" value=""/>
                                                        <div class="middle_frame" id="treinoB" style="width: 190px;height: ${div1}px;">
                                                            <h4>Treino B</h4>
                                                            <div id="menu">
                                                                <ul id="sortableTreinoB" >
                                                                    <mtw:list value="exerciciosB">
                                                                        <mtw:isEmpty></mtw:isEmpty>
                                                                        <mtw:loop var="row" counterStart="0" counter="i">
                                                                            <li><div style="display:none">#ID#<mtw:out value="exercicio.id"/>#/ID#</div><h9 class="head"><a href="#" title="<mtw:out value="exercicio.exercicio"/> !" ><mtw:out value="exercicio.exercicio" max="17"/></a></h9></li>
                                                                        </mtw:loop>
                                                                    </mtw:list>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <div class="bottom_frame"></div>
                                                    </div>
                                                </td>
                                                <td class="one">
                                                    <div class="top_frame"></div>
                                                    <div id="treinoCOrdem" >
                                                        <input name="treinoCHtml" id="treinoCHtml" type="hidden" value=""/>
                                                        <div class="middle_frame" id="treinoC" style="width: 190px; height: ${div1}px;">
                                                            <h4 id="serie">Treino C</h4>
                                                            <div id="menu">
                                                                <ul id="sortableTreinoC" >
                                                                    <mtw:list value="exerciciosC">
                                                                        <mtw:isEmpty></mtw:isEmpty>
                                                                        <mtw:loop var="row" counterStart="0" counter="i">
                                                                            <li><div style="display:none">#ID#<mtw:out value="exercicio.id"/>#/ID#</div><h9 class="head"><a href="#" title="<mtw:out value="exercicio.exercicio"/> !" ><mtw:out value="exercicio.exercicio" max="17"/></a></h9></li>
                                                                        </mtw:loop>
                                                                    </mtw:list>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <div class="bottom_frame"></div>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="one">
                                                    <div class="top_frame"></div>
                                                    <div id="treinoDOrdem" >
                                                        <input name="treinoDHtml" type="hidden" id="treinoDHtml" value=""/>
                                                        <div class="middle_frame" id="treinoD" style="width: 190px; height: ${div2}px;">
                                                            <h4 id="serie">Treino D</h4>
                                                            <div id="menu">
                                                                <ul id="sortableTreinoD" >
                                                                    <mtw:list value="exerciciosD">
                                                                        <mtw:isEmpty></mtw:isEmpty>
                                                                        <mtw:loop var="row" counterStart="0" counter="i">
                                                                            <li><div style="display:none">#ID#<mtw:out value="exercicio.id"/>#/ID#</div><h9 class="head"><a href="#" title="<mtw:out value="exercicio.exercicio"/> !" ><mtw:out value="exercicio.exercicio" max="17"/></a></h9></li>
                                                                        </mtw:loop>
                                                                    </mtw:list>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <div class="bottom_frame"></div>
                                                    </div>
                                                </td>
                                                <td class="one">
                                                    <div class="top_frame"></div>
                                                    <div id="treinoEOrdem" >
                                                        <input name="treinoEHtml" id="treinoEHtml" type="hidden" value=""/>
                                                        <div class="middle_frame" id="treinoE" style="width: 190px; height:${div2}px;">
                                                            <h4 id="serie">Treino E</h4>
                                                            <div id="menu">
                                                                <ul id="sortableTreinoE" >
                                                                    <mtw:list value="exerciciosE">
                                                                        <mtw:isEmpty></mtw:isEmpty>
                                                                        <mtw:loop var="row" counterStart="0" counter="i">
                                                                            <li><div style="display:none">#ID#<mtw:out value="exercicio.id"/>#/ID#</div><h9 class="head"><a href="#" title="<mtw:out value="exercicio.exercicio"/> !" ><mtw:out value="exercicio.exercicio" max="17"/></a></h9></li>
                                                                        </mtw:loop>
                                                                    </mtw:list>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <div class="bottom_frame"></div>
                                                    </div>
                                                </td>
                                                <td class="one">
                                                    <div class="top_frame"></div>
                                                    <div id="treinoFOrdem" >
                                                        <input name="treinoFHtml" id="treinoFHtml" type="hidden" value=""/>
                                                        <div class="middle_frame" id="treinoF" style="width: 190px; height:${div2}px;">
                                                            <h4 id="serie">Treino F</h4>
                                                            <div id="menu">
                                                                <ul id="sortableTreinoF" >
                                                                    <mtw:list value="exerciciosF">
                                                                        <mtw:isEmpty></mtw:isEmpty>
                                                                        <mtw:loop var="row" counterStart="0" counter="i">
                                                                            <li><div style="display:none">#ID#<mtw:out value="exercicio.id"/>#/ID#</div><h9 class="head"><a href="#" title="<mtw:out value="exercicio.exercicio"/> !" ><mtw:out value="exercicio.exercicio" max="17"/></a></h9></li>
                                                                        </mtw:loop>
                                                                    </mtw:list>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <div class="bottom_frame"></div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </td>
                <tr>
                    <td width="100%" class="panelButtonForm">
                        <mtw:buttonAction klass="botao" action="usuarioFichasRead.do" name="cancelar" value="Sair"/>
                        <mtw:buttonAction klass="botao" name="salvar" id="salvar" value="Salvar" />
                    </td>
                </tr>
                </tr>
            </table>
        </mtw:bean>
    </mtw:form>
</div>