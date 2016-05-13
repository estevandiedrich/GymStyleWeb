<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/pagamento/pagamentoUltimoPlano.js"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>

<div class="title_bottom">
    <table class="topo" width="100%">
        <tr>
            <td  width="75%">
                <h1>Histórico</h1>
            </td>
            <td align="right" id="filtro"  width="25%">
                <div id="item">
                    <mtw:hasAuthorization permission="pagamentoUltimoPlanosRead" >
                        <a id="list" href="pagamentoUltimoPlanosRead.do">Listar</a>
                    </mtw:hasAuthorization>
                </div>
            </td>
        </tr>
    </table>
    <!--mtw:form method="post" action="pagamentoUltimoPlanosRead.do" name="listForm"-->
    <h3>Listar</h3>
    <div>
        <table width="100%" >
            <tr>
                <td align="left" width="10%" class="one"><mtw:label klass="obrig" value="Matrícula:"/></td>
                <td>
                    <mtw:input klass="selectOptions" name="criterioMatricula" id="criterioMatricula" extra="onkeyup=submitFormRead()" maxlength="12"/>
                </td>
                <td width="10%"></td>
                <td></td>
            </tr>            <tr>
                <td align="left" width="10%" class="one">
                    <mtw:label klass="obrig" value="Aluno:" />
                </td>
                <td align="left" width="10%">
                    <mtw:input type="text" klass="input" name="criterioNome"  id="criterioNome" extra="onkeyup=consultaNome()" maxlength="70" />
                </td>
                <td class="one">
                    <mtw:label klass="obrig" value="CPF:"/>
                </td>
                <td>
                    <mtw:input klass="inputNumber" name="criterioCpf" id="criterioCpf" extra="onkeyup=submitFormRead()" />
                </td>
                <td align="left"width="10%">
                    <mtw:buttonAction id="filtrar" value="Filtrar" name="filtrar" onclick="submitFormRead()"/>
                </td>
                <td align="left"width="70%"></td>
            </tr>
        </table>
    </div>
    <div id="content">
        <%@include file="corpoUltimoPlano.jsp" %>
    </div>
    <!--/mtw:form-->
</div>