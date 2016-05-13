<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/dispositivo/manager.js"></script>


<div class="title_bottom" style="width: 100%">
    <mtw:form action="dispositivoRead.do">
        <table width="100%" height="10px">
            <tr>
                <td>
                    <h1>Configurando a Catraca</h1>
                </td>
            </tr>
            <tr>
                <td style="width: 100%">
                    <div id="corpo" style="width: 100%">
                        <div id="carregando" style="width: 100%"></div>
                        <div id="result" style="width: 100%" >
                            <%@include file="sincronizando.jsp" %>
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