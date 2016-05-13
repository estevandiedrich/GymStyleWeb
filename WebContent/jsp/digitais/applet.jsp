<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table width="95%" style="height: 200px;background-color: #FFF" class="faixasForm" >
    <tr>
        <td valign="top" style="height: 50px">
            <object type="application/x-java-applet" height="70px" width="400px">
                <param name="code" value="ColetorDeDigitais" />
                <param name="archive" value="applet/jna.jar,applet/ColetorDeDigitais.jar" />
                <!--param name="archive" value="applet/jna.jar,applet/ColetorDeDigitais.jar" /-->
                <!--param name="url" value="http://10.0.0.141:8084/GymStyleWeb/applet/dlls.zip"/-->
                <param name="url" id="urlApplet" />

                <!-- A url vai ser setada via java script.-->
                <!-- Como Assinar Applet-->
                <!-- C:\ProgramFiles \java\jdk1.6....\bin\jarsigner.exe -storepass 123456
                C:\Apps\ColetorDeDigitais.jar rwtech-->
                <param name="digitais" id="templatesApplet" />
            </object>
        </td>
    </tr>
    <tr><td>Carregando, aguarde...</td></tr>
</table>