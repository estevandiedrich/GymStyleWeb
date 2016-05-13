<%@include file="WEB-INF/imports.jspf"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style/style.css" type="text/css"/>
        <link rel="shortcut icon" href="images/gym.ico" type="image/x-icon" />
        <script src="js/jquery.js" type="text/javascript"></script>
        <title>GymStyle</title>
    </head>
    <script>

        var contador = 4;
        function refreshContador() {
            if(contador == 0) {
                mostraMsg();
            }
            if (contador != 0){
                contador = contador-1;
                setTimeout("refreshContador()", 1000);
            }
        }
        
        function mostraMsg(){
            $(".error").fadeIn();
            $(".error").fadeOut();
        }

        function focaCursor(){
            $("#username").focus();
        }

        function valida(e){
            //alert("AAAAAAAA"+ e.keyCode);
            if (e.keyCode == 13){
                //  alert("AAAAAAAA");
                document.getElementById('butao_actualizar').click();
            }
        }

        $(document).ready(function() {
            $("#password").keypress(function(e){
                if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)){
                    document.form.submit();
                }
            });
            $("#username").keypress(function(e){
                if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)){
                    document.form.submit();
                }
            });
            if (jQuery.browser.mozilla){  
                //alert("Seu código aqui se for o firefox seu navegador  ");
                $("#navegador").val('Mozila');
            }else if (jQuery.browser.msie){  
                //alert("Seu código aqui se for o Internet Explorer  ");
                $("#navegador").val('InternetExplorer');
            }else if (jQuery.browser.safari){//ou Chrome
                //alert("Seu código aqui se for o Safari   ");
                $("#navegador").val('Chrome');
            }else if (jQuery.browser.opera){  
                //alert("Seu código aqui se for Opera  ");
                $("#navegador").val('Opera');
            }
        });
    </script>
    <body onload="refreshContador();focaCursor()">
        <div class="bodyLogin" align="center" >
            <mtw:form method="post" action="login.do" name="form">
                <mtw:input type="hidden" name="navegador" id="navegador" value="" />
                <table id="painelLogin">
                    <tr >
                        <td colspan="2" width="100%" align="center">
                            <br>
                            <p><img src="images/logoMarca.png"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" width="100%" style="padding-left: 20px;padding-top: 10px"><h1>Login</h1></td>
                    </tr>
                    <tr >
                        <td width="10%" style="padding-left: 20px;padding-top: 10px">Usuário: </td>
                        <td width="90%"style="padding-left: 20px;padding-top: 10px" >
                            <mtw:input type="text" name="username" id="username" klass="inputLogin" value="" />
                            <font class="error" ><mtw:outError field="username" ><mtw:out/></mtw:outError></font>
                        </td>
                    </tr>
                    <tr>
                        <td width="10%" style="padding-left: 20px;padding-top: 5px;padding-bottom: 10px">Senha: </td>
                        <td width="90%" style="padding-left: 20px;padding-top: 5px;padding-bottom: 10px">
                            <mtw:input type="password" name="password" id="password" klass="inputLogin" value="" extra="onkeypress='valida(event)'" />
                            <font class="error" ><mtw:outError field="password" ><mtw:out/></mtw:outError></font>
                            <br/>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding-bottom: 20px" colspan="2" align="center">
                            <mtw:hasMessage>
                                <font class="error" id="error" ><mtw:message /></font>
                            </mtw:hasMessage>
                            <br/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" id="painelButton" align="center" style="padding-top: 5px" ><mtw:submit klass="button2" value="Entrar"/></td>
                    </tr>
                </table>
            </mtw:form>
        </div>
    </body>
</html>