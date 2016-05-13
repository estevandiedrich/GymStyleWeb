<%@include file="WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="style/style.css" type="text/css"/>
<script src="js/jquery.js"  type="text/javascript"></script>
<link rel="shortcut icon" href="images/gym.ico" type="image/x-icon" />
<script>
    $(document).ready(function() {
        window.location = "index.do";
    });
</script>
<title>GymStyle</title>
<mtw:form action="login.do">
    <div style="width: 100%">
        <table width="100%" align="center">
            <tr>
                <td width="100%" align="center">
                    <img src="images/logoMarca.png"/>
                </td>
            </tr>
            <tr>
                <td width="100%" align="center">
                    <h1>Sessão Expirada</h1>
                </td>
            </tr>
            <tr>
                <td width="100%" align="center">
                    <mtw:buttonAction klass="botao" action="index.do" name="Logar"  id="logar" value="Logar"/>
                </td>
            </tr>
        </table>
    </div>
</mtw:form>