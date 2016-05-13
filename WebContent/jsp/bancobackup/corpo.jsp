<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/excluir.js" type="text/javascript"></script>

<mtw:bean value="paginator" var="pag">
    <br>
    <h2>GymStyle <mtw:out value="versao"/></h2>
    <table width="100%" class="displaytag">
        <thead>
            <tr><th width="55%" >Arquivos</th><th width="25%" >Tamanho</th>
                <mtw:hasAuthorization groups="ADMINISTRADOR">
                    <th  width="20%">Ação</th>
                </mtw:hasAuthorization>
            </tr>
        </thead>
        <tbody>
            <mtw:bean value="lista">
                <mtw:list value="lista" >
                    <mtw:isEmpty>
                        <tr class="sub">
                            <td colspan="3">
                                Dados Vazios
                            </td>
                        </tr>
                    </mtw:isEmpty>
                    <mtw:loop var="row" counterStart="0" counter="i">
                        <tr class="
                            <c:choose>
                                <c:when test="${i%2==0}">odd</c:when>
                                <c:otherwise>even</c:otherwise>
                            </c:choose>
                            ">
                            <td>
                                <div><mtw:out value="row.file.name"/></div>
                            </td>
                            <td><mtw:out value="row.tamanho"/></td>
                            <mtw:hasAuthorization groups="ADMINISTRADOR">
                                <td>
                                    <mtw:hasAuthorization permission="bancoRestaurarBackup"><a class='restore' title='Restaurar'  href="javascript:restaurarBackup('${row.file.name}');">Restaurar</a></mtw:hasAuthorization>
                                        |<mtw:hasAuthorization permission="bancoBackupDelete"><a class='delete' title='Excluir' href="javascript:excluirBackupBanco('${row.file.name}');">Excluir</a></mtw:hasAuthorization>
                                </td>
                            </mtw:hasAuthorization>
                        </tr>
                    </mtw:loop>
                </mtw:list>
                <tr><td colspan="3" ></td></tr>
                <tr><td colspan="3" class="sub2" style="font-size: 12px;font-style: italic">Local dos arquivos para fazer cópia - "C:" ou outro diretório de instalação do SO + pasta "\backupBancoGymStyle"</td></tr>
            </mtw:bean>
        </tbody>
    </table>
</mtw:bean>
