<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<table width="98%" >
    <tr class="sub4">
        <td align="center">
            <div width="100%" class="faixasForm title_bottom" >
                <br>
                <table width="100%" >
                    <mtw:list value="list" >
                        <mtw:isEmpty>
                            <tr><td class="one" align="left" valign="top"><font class="errors">Registros Caixas Não Encontrados</td></tr>
                            </mtw:isEmpty>
                            <mtw:loop var="row" counterStart="0" counter="i"> 
                                <tr>
                                    <td align="left" class="one">
                                        <h1 class="mais" id="labelCaixa<mtw:out value="id"/>" onclick="mostraCaixa(<mtw:out value="id"/>,<mtw:out value="dia"/>)" >
                                            <table width="100%" >
                                                <tr>
                                                    <td width="50%" align="left">
                                                        <div>
                                                            <label class="obrig" >Abertura: </label><mtw:out value="abertura.time"/>
                                                            <mtw:if test="usuarioAbriu" value="null" negate="true">
                                                                <br><label class="obrig operador" >Operador:<mtw:out value="usuarioAbriu.usuario" /></label>
                                                            </mtw:if>
                                                        </div>
                                                    </td>
                                                    <td width="50%" align="left">
                                                        <div>
                                                            <mtw:if test="fechamento" value="null" negate="true">
                                                                <label class="obrig" >Fechamento: </label><mtw:out value="fechamento.time"/>
                                                            </mtw:if>
                                                            <mtw:if test="fechamento" value="null" negate="true">
                                                                <mtw:if test="usuarioFechou" value="null" negate="true">
                                                                    <br><label class="obrig operador" >Operador:<mtw:out value="usuarioFechou.usuario" /></label>
                                                                </mtw:if>
                                                            </mtw:if>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </h1>
                                        <div id="caixa<mtw:out value="id"/>"></div>
                                    </td>
                                </tr>
                            </mtw:loop>
                        </mtw:list>
                </table>
            </div>
        </td>
    </tr>
</table>
