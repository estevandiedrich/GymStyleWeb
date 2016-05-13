<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <mtw:inputMaskConfig />
        <mtw:tabPanelConfig skin="winXp"/>
        <mtw:inputMoneyConfig />
        <link rel="shortcut icon" href="images/gym.ico" type="image/x-icon" />

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <meta name="Robots" content="noindex, nofollow">

        <script src="js/jquery.js"  type="text/javascript"></script>
        <script src="js/evento.js" type="text/javascript"></script>
        <!--script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/maskMoney.js" type="text/javascript"></script>
        <script src="js/maskedinput-1.2.2.js" type="text/javascript"></script-->

        <link rel="stylesheet" href="style/style.css" type="text/css"/>
        <link rel="stylesheet" href="style/styleMenu.css" type="text/css"/>
        <link rel="stylesheet" href="style/menu.css" type="text/css"/>
        <title>GymStyle</title>
    </head>
    <body onload="clearInterval(4000);selecionado()">
        <div id="body" align="center" >
            <div id="holder">
                <div id="footer_terms">
                    <mtw:input type="hidden" id="selecionado" name="selecionado"/>
                </div>
                <div>
                    <template:block id="header"/>
                </div>
                <div>
                    <template:block id="menu"/>
                </div>
                <div style="width: 100%;min-height: 400px;">
                    <table width="100%" >
                        <tr>
                            <td width="15%" valign="top" style="background: #f9f9f9"><template:block id="menuEsquerdo"/></td>
                            <td width="85%" valign="top" ><template:block id="body"/><td>
                        </tr>
                    </table>
                </div>
                <div><template:block id="footer"/></div>
            </div>
        </div>
    </body>
</html>