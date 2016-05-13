<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table width="100%">
    <tr>
        <td width="100%">
            <h1 id="eventoImportar"  class="menos"  title="Selecione">Selecionar um horário cadastrado</h1>
            <fieldset id="importar" class="field" <c:if test="${horariosImportar}">style="display: inline"</c:if> >
                    <legend></legend>
                    <div>
                        <table width="98%" align="center" class="faixasForm">
                            <tr>
                                <td class="one" width="10%">
                                <mtw:label klass="obrig" value="Horários:"/>
                            </td>
                            <td width="25%">
                                <mtw:select list="horarios" klass="selectOptions" name="horarioSelect" id="horarioSelect" emptyField="true"
                                            emptyFieldValue="Selecione..."/>
                            </td>
                            <td width="65%" align="left">
                                <mtw:buttonAction klass="botao" value="Importar" name="buttonImportar" onclick="refreshImportar()" />
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="corpoHorarios">
                    <%@include file="horarioForm.jsp" %>
                </div>
            </fieldset>
        </td>
    </tr>
    <tr>
        <td>
            <h1 id="eventoCadastrarHorarios"  class="mais"  title="Selecione">Cadastrar novo horário</h1>
            <fieldset id="cadastrarHorarios" <c:if test="${horariosNovo}">style="display: inline"</c:if> class="field">
                    <legend></legend>
                    <div>
                    <%@include file="horarioFormNovo.jsp" %>
                </div>
            </fieldset>
        </td>
    </tr>
</table>