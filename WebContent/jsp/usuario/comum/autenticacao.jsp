<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="js/usuario/comum.js"></script>

<div>
    <h3></h3>
    <mtw:bean value="pojo"></mtw:bean>
    <table class="form" >
        <tr>
            <td class="one">Login:</td>
            <td>
                <input type="hidden" name="loginUsuario" id="loginUsuario" value="<mtw:out value="loginUsuario"/>"/>
                <input type="hidden" name="loginAux" id="loginAux"  value="<mtw:out value="loginAux"/>" />
                <mtw:input klass="input" type="text" name="login" id="login" extra="onkeyup=verificaLogin(this.value)" value="" />
            </td>
            <td>
                <font class="errors"><mtw:outError field="login" ><mtw:out/></mtw:outError></font>
                <div id="resultLogin" ><mtw:out value="retornoVerificaLogin"/><mtw:input type="hidden" name="retornoVerificaLogin" value=""/></div>                    
            </td>
        </tr>
        <tr>
            <td class="one"></td><td></td>
        </tr>
        <tr>
            <td class="one">Senha:</td>
            <td><mtw:input klass="input" type="password" name="senha" id="senha" /></td>
            <td><font class="errors"><mtw:outError field="senha" > <mtw:out /></mtw:outError></font></td>
        </tr>
        <tr>
            <td class="one">Repita Senha:</td>
            <td><mtw:input klass="input" type="password" name="senha2"/></td>
            <td><font class="errors"><mtw:outError field="senha2" > <mtw:out /></mtw:outError></font></td>
        </tr>
    </table>
</div>