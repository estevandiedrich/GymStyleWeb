<%@include file="../WEB-INF/imports.jspf"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>    
    $(document).ready(function(){
        var loc = document.URL;
        var pathName = loc.substring(0, loc.lastIndexOf('/') + 1);
        //fazer o parametro pra baixar as dll local
        var link = "<img src='images/aplicar.png'><a name='help' href='"+pathName+"driver/InstalacaoDriverHamister.zip' target='blank'>Drivers. Selecione para baixar zip</a>"
        $("#url").html(link);
    });
    function fechaJanela(){
        window.close();
    }
</script>

<div class="bodyLogin" align="center">
    <table id="painelLogin" style="width: 100%;height: 10%">
        <tr>
            <td colspan="2" width="100%" style="padding-left: 20px;padding-top: 10px"><h1>Ajuda</h1></td>
        </tr>
        <tr>
            <td style="padding-left: 20px;padding-top: 10px;font-style: italic;font-size: 14px">Para utilizar o cadastro de digitais utilizando o Hamister,
                será necessário baixar os drivers do aplicativos contido neste link.<br>
                <br>
                <div class="faixasForm" id="url">
                    <!--img src="images/aplicar.png"><a name="help" href="http://localhost:8080/GymStyleWeb/driver/InstalacaoDriverHamister.zip" target="blank">Drivers. Selecione para baixar zip</a-->
                </div>
        </tr>
        <tr>
            <td colspan="2" width="100%" style="padding-left: 20px;padding-top: 10px"><h1>Após baixar sigas o seguintes passos para instalação:</h1></td>
        </tr>
        <tr><td style="padding-left: 20px;padding-top: 10px;">1 - Descompacte o arquivo e entre dentro da pasta - 'InstalacaoDriverHamister'.</td></tr>
        <tr><td style="padding-left: 20px;padding-top: 10px;">2 - Dê dois cliques no aplicativo setup.(<label style="font-style: italic;font-size: 13px">Em seguida irá abrir uma janela de instalação dos Drivers</label>). Aguarde...</td></tr>
        <tr><td style="padding-left: 20px;padding-top: 10px;">3 - Selecione a opção Repair e em seguida next.</td></tr>
        <tr><td style="padding-left: 20px;padding-top: 10px;">4 - Após o final da instalação, selecione a opção Finish para finalizar a instalação</td></tr>
        <tr><td style="padding-left: 20px;padding-top: 10px;">5 - Volte a aba de cadastro do usuário, atualize a página e tente novamente capturar digitais.</td></tr>
        <tr><td style="padding-left: 20px;padding-top: 10px;"><mtw:buttonAction name="fechar" klass="botao" onclick="fechaJanela()" value="Fechar" /></td></tr>
    </table>
</div>
