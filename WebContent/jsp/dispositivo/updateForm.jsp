<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="title_bottom">
    <%@include file="topo.jsp" %>
    <h3>Editar</h3>
    <mtw:form method="post" action="dispositivoUpdate.do">
        <mtw:bean value="pojo">
            <div class="faixasForm">
                <br>
                <table class="form" >
                    <tr>
                        <td class="one">
                            <mtw:input type="hidden" name="id"/>
                            <mtw:input type="hidden" name="entrada"/>
                            <mtw:input type="hidden" name="imprimir"/>
                            <mtw:input type="hidden" name="modoAcessoValor"/>
                            <mtw:label klass="obrig" value="Nome:"/>
                        </td>
                        <td><mtw:input klass="input" type="text" name="dispositivo"/></td>
                        <td><font class="errors"><mtw:outError field="dispositivo" ><mtw:out/></mtw:outError></font></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Endereço IP:"/></td>
                        <td><mtw:inputText klass="inputDisabled" name="enderecoIp" readonly="true" /></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Porta:"/></td>
                        <td><mtw:inputText klass="inputDisabled" name="porta" readonly="true" /></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Modo de Controle:"/></td>
                        <td><mtw:select klass="input" style="padding-left:3px" name="modoAcessoPojo" list="modosAcessos" defValue="1"/></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Lado de Entrada:"/></td>
                        <td><mtw:select klass="selectOptions" name="entradaDirEsq" list="entradas" defValue="entradaDirEsq"/></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Imprime Ficha de Treino:"/></td>
                        <td class="one"><mtw:radiobuttons klass="obrig" name="imprime" list="imprimes" defValue="imprime"/></td>
                    </tr>
                    <tr>
                        <td class="one">Sentidos:</td>
                        <td>
                            <div class="faixasForm" style="width: 220px">
                                <img src="images/sentido1.png" height="220px" width="220px"/>
                            </div>
                        </td>
                    </tr>
                </table>
                <table width="100%" >
                    <tr>
                        <td width="100%" class="panelButtonForm">
                            <mtw:buttonAction action="dispositivoRead.do" klass="botao" name="Cancelar" value="Cancelar"/>
                            <mtw:submit klass="botao" value="Salvar"/>
                        </td>
                    </tr>
                </table>
            </div>
        </mtw:bean>
    </mtw:form>
</div>