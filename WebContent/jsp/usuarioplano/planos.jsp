<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/usuarioplano/planos.js"></script>

<div class="title_bottom">
    <table width="100%">
        <tr>
            <td >
                <%@include file="../info.jsp" %>
            </td>
        </tr>
        <tr>
            <td width="100%">
                <h1 id="eventoVincularPlano"  class="menos"  title="Selecione">Vincular plano existente</h1>
                <fieldset id="vinculados" class="field">
                    <legend></legend>
                    <div>
                        <%@include file="vincularPlano.jsp" %>
                    </div>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td width="100%">
                <h1 id="eventoPlanosUsuario"  class="mais"  title="Selecione">Planos</h1>
                <fieldset id="planosVincular" class="field" style="display: none">
                    <legend></legend>
                    <div>
                        <table whith="100%" class="displaytag">
                            <mtw:bean value="pojoInfo.planos">
                                <thead>
                                    <tr><th>Código</th><th>Plano</th><th>Duração</th><th>Ação</th></tr>
                                </thead>
                                <tbody>
                                    <mtw:list value="pojoInfo.planos">  
                                        <mtw:isEmpty>  
                                            <tr>
                                                <td colspan="4">
                                                    Dados Vazios
                                                </td>
                                            </tr>
                                        </mtw:isEmpty>  
                                        <mtw:loop var="pojo" counterStart="0" counter="i">  
                                            <tr class="
                                                <c:choose>
                                                    <c:when test="${i%2==0}">odd</c:when>
                                                    <c:otherwise>even</c:otherwise>
                                                </c:choose>
                                                ">
                                                <td class="firstColumn" >
                                                    <mtw:out value="id"/><br/>
                                                </td>
                                                <td>
                                                    <mtw:out value="plano"/>
                                                </td>
                                                <td>
                                                    <mtw:out value="duracaoPlano.duracao"/>
                                                </td>
                                                <td class="acao">
                                                    <mtw:hasAuthorization permission="usuarioUpdateInformacao" >
                                                        <a class='update' title='Editar'  href="usuarioUpdateInformacao.do?id=${usuario.id}">Editar</a>
                                                    </mtw:hasAuthorization>
                                                </td>
                                            </tr>
                                        </mtw:loop>  
                                    </mtw:list>  
                                </tbody>
                            </mtw:bean>  
                        </table>
                    </div>
                </fieldset>
            </td>
        </tr>
    </table>
</div>