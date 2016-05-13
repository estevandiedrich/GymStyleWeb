<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="style/styleModal.css" type="text/css"/>
<script type="text/javascript" src="js/bancobackup/list.js"></script>

<div class="title_bottom">
    <%@include  file="topo.jsp" %>
    <mtw:form method="post" action="bancoBackupRead.do" name="listForm">
        <!--a href="#dialog" name="modal">Janela Modal Simples</a-->
        <div id="dialog" class="window">
        </div>
        <!-- Não remova o div#mask, pois ele é necessário para preencher toda a janela -->
        <div id="mask" class="window" >
            <div id="status">
                <!--a href="#" class="close">Fechar [X]</a><br /-->
                <table class="topo" width="100%">
                    <tr><td><h1>Backup do Banco de Dados</h1></td></tr>
                </table>
                <table class="displaytag" width="100%">
                    <thead>
                        <tr><th width="100%" colspan="2" ></th></tr>
                    </thead>
                    <tbody>
                        <tr class="sub">
                            <td><img src="images/carregandoPeq.gif"/></td>
                            <td><div id="status2">Aguarde ...</div></td>
                        </tr>
                        <tr class="even"><td colspan="2"></td></tr>
                        <tr><td colspan="2" class="sub2" style="font-size: 12px;font-style: italic">Este processo levará alguns segundos!</td></tr>
                    </tbody>
                </table>
            </div>
        </div>
        <h3>Listar</h3>
        <div id="content" valign="top"><%@include file="corpo.jsp" %></div>
    </mtw:form>
</div>