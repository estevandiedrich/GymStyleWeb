<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/ficha/info.js"></script>
<div class="title_bottom">
    <mtw:bean value="usuario">
        <table class="topo" width="100%">
            <tr>
                <td  width="80%">
                    <h1>Fichas de Treino</h1>
                </td>
                <td  width="20%" align="center">
                    <a id="list" href="usuarioFichasRead.do?idUsuario=<mtw:out value="id"/>" title="Listar Dispositivos!">Listar</a>
                    <mtw:hasAuthorization permission="fichaManager">
                        <a id='novo' title='Nova Ficha' href="fichaCreate.do?idUsuario=<mtw:out value="id"/>">Nova Ficha</a>
                    </mtw:hasAuthorization>
                </td>
            </tr>
        </table>
    </mtw:bean>
    <mtw:form method="post" action="fichaCreate.do">
        <mtw:bean value="pojo">
            <table width="100%">
                <tr>
                    <td width="100%">
                        <div>
                            <table style="width: 100%;">
                                <tr>
                                    <td>
                                        <div class="form2">
                                            <table id="fundoMenu" style="width: 100%;">
                                                <tr>
                                                    <td width="88%" valign="top">
                                                        <mtw:bean value="usuario">
                                                            <div class="faixasForm" style="width: 99.5%;margin-left: 1px;min-height: 50px" >
                                                                <table width="100%" class="displaytag">
                                                                    <tbody id="tbody">
                                                                        <tr class="odd">
                                                                            <td width="55%" style="min-width: 130px" ><label class="obrig">Impressões de Ficha</label></td>
                                                                            <td width="15%" >
                                                                                <input type="button" class="pdf" style="width: 115px; padding-left: 20px" value="Treino Completo" onclick="imprimirFicha('<mtw:out value="usuario.id"/>','<mtw:out value="idFicha"/>','')" />
                                                                            </td>
                                                                            <mtw:if test="treinoA">
                                                                                <td width="5%" >
                                                                                    <input type="button" class="pdf" value="Treino A" onclick="imprimirFicha('<mtw:out value="usuario.id"/>','<mtw:out value="idFicha"/>','A')" />
                                                                                </td>
                                                                            </mtw:if>
                                                                            <mtw:if test="treinoB">
                                                                                <td width="5%" >
                                                                                    <input type="button" class="pdf" value="Treino B" onclick="imprimirFicha('<mtw:out value="usuario.id"/>','<mtw:out value="idFicha"/>','B')" />
                                                                                </td>
                                                                            </mtw:if>
                                                                            <mtw:if test="treinoC">
                                                                                <td width="5%" >
                                                                                    <input type="button" class="pdf" value="Treino C" onclick="imprimirFicha('<mtw:out value="usuario.id"/>','<mtw:out value="idFicha"/>','C')" />
                                                                                </td>
                                                                            </mtw:if>
                                                                            <mtw:if test="treinoD">
                                                                                <td width="5%" >
                                                                                    <input type="button" class="pdf" value="Treino D" onclick="imprimirFicha('<mtw:out value="usuario.id"/>','<mtw:out value="idFicha"/>','D')" />
                                                                                </td>
                                                                            </mtw:if>
                                                                            <mtw:if test="treinoE">
                                                                                <td width="5%" >
                                                                                    <input type="button" class="pdf" value="Treino E" onclick="imprimirFicha('<mtw:out value="usuario.id"/>','<mtw:out value="idFicha"/>','E')" />
                                                                                </td>
                                                                            </mtw:if>
                                                                            <mtw:if test="treinoF">
                                                                                <td width="5%" >
                                                                                    <input type="button" class="pdf" value="Treino F" onclick="imprimirFicha('<mtw:out value="usuario.id"/>','<mtw:out value="idFicha"/>','F')" />
                                                                                </td>
                                                                            </mtw:if>
                                                                        </tr>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                            <div class="faixasForm" style="width: 99.5%;margin-left: 1px;min-height: 100px" >
                                                                <h1 id="serie">Ficha</h1>
                                                                <table width="100%" class="displaytag">
                                                                    <thead>
                                                                        <tr>
                                                                            <th width="12%">Início</th>
                                                                            <th width="12%">Término</th>
                                                                            <th width="35%">Aluno</th>
                                                                            <th width="30%">Instrutor</th>
                                                                            <th width="11%">Ativa</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody id="tbody">
                                                                        <tr class="odd">
                                                                            <td><mtw:out value="pojo.periodoInicial.time" formatter="dateFormatter"/></td>
                                                                            <td><mtw:out value="pojo.periodoFinal.time" formatter="dateFormatter"/></td>
                                                                            <td><mtw:out value="usuario"/></td>
                                                                            <td><mtw:out value="pojo.instrutor.usuario"/></td>
                                                                            <td>
                                                                                <mtw:if test="pojo.ativa">Sim</mtw:if> 
                                                                                <mtw:if test="pojo.ativa" negate="true">Não</mtw:if> 
                                                                                </td>
                                                                            </tr>
                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                        </mtw:bean>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="88%" valign="top">
                                                        <h3>Treinos</h3>
                                                        <div class="faixasForm" style="width: 99.5%;margin-left: 1px;min-height: 50px" >
                                                            <table width="100%" class="displaytag">
                                                                <tbody id="tBody">
                                                                    <mtw:if test="treinoA">
                                                                        <tr>
                                                                            <th colspan="7">
                                                                                <h4 style="font-size: 15px">Treino A</h4>
                                                                            </th>
                                                                        </tr>
                                                                        <tr style="height: 5px">
                                                                            <td colspan="7" align="center">
                                                                                <h4 style="font-size: 11px">(<mtw:out value="diasA"/>)</h4>
                                                                            </td>
                                                                        </tr>
                                                                        <%@include file="info/treinoA.jsp" %>
                                                                    </mtw:if>
                                                                    <mtw:if test="treinoB">
                                                                        <tr>
                                                                            <th colspan="7">
                                                                                <h4 style="font-size: 15px">Treino B</h4>
                                                                            </th>
                                                                        </tr>
                                                                        <tr style="height: 5px">
                                                                            <td colspan="7" align="center">
                                                                                <h4 style="font-size: 11px">(<mtw:out value="diasB"/>)</h4>
                                                                            </td>
                                                                        </tr>
                                                                        <%@include file="info/treinoB.jsp" %>
                                                                    </mtw:if>
                                                                    <mtw:if test="treinoC">
                                                                        <tr>
                                                                            <th colspan="7">
                                                                                <h4 style="font-size: 15px">Treino C</h4>
                                                                            </th>
                                                                        </tr>
                                                                        <tr style="height: 5px">
                                                                            <td colspan="7" align="center">
                                                                                <h4 style="font-size: 11px">(<mtw:out value="diasC"/>)</h4>
                                                                            </td>
                                                                        </tr>
                                                                        <%@include file="info/treinoC.jsp" %>
                                                                    </mtw:if>
                                                                    <mtw:if test="treinoD">
                                                                        <tr>
                                                                            <th colspan="7">
                                                                                <h4 style="font-size: 15px">Treino D</h4>
                                                                            </th>
                                                                        </tr>
                                                                        <tr style="height: 5px">
                                                                            <td colspan="7" align="center">
                                                                                <h4 style="font-size: 11px">(<mtw:out value="diasD"/>)</h4>
                                                                            </td>
                                                                        </tr>
                                                                        <%@include file="info/treinoD.jsp" %>
                                                                    </mtw:if>
                                                                    <mtw:if test="treinoE">
                                                                        <tr>
                                                                            <th colspan="7">
                                                                                <h4 style="font-size: 15px">Treino E</h4>
                                                                            </th>
                                                                        </tr>
                                                                        <tr style="height: 5px">
                                                                            <td colspan="7" align="center">
                                                                                <h4 style="font-size: 11px">(<mtw:out value="diasE"/>)</h4>
                                                                            </td>
                                                                        </tr>
                                                                        <%@include file="info/treinoE.jsp" %>
                                                                    </mtw:if>
                                                                    <mtw:if test="treinoF">
                                                                        <tr>
                                                                            <th colspan="7">
                                                                                <h4 style="font-size: 15px">Treino F</h4>
                                                                            </th>
                                                                        </tr>
                                                                        <tr style="height: 5px">
                                                                            <td colspan="7" align="center">
                                                                                <h4 style="font-size: 11px">(<mtw:out value="diasF"/>)</h4>
                                                                            </td>
                                                                        </tr>
                                                                        <%@include file="info/treinoF.jsp" %>
                                                                    </mtw:if>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <mtw:isEmpty test="descricao" negate="true"></mtw:isEmpty>
                                                    <tr>
                                                        <td width="88%" valign="top">
                                                            <h3></h3>
                                                            <div class="faixasForm" style="width: 99.5%;margin-left: 1px;min-height: 150px"  >
                                                                <h1 id="serie">Observação</h1>
                                                                <table width="100%" >
                                                                    <tr>
                                                                        <td align="left" width="100%" class="one">
                                                                        <mtw:textarea name="descricao" id="descricao" cols="95" rows="8" style="max-width: 650px;max-height: 160px" />
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="100%" class="panelButtonForm">
                                                        <mtw:buttonAction action="usuarioFichasRead.do?idUsuario=${usuario.id}" klass="botao" name="Voltar" value="Voltar"/>
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
            </table>
        </mtw:bean>
    </mtw:form>
</div>