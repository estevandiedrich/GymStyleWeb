<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="style/style.css" type="text/css"/>
<script src="js/evento.js" type="text/javascript"></script>
<script src="js/usuario/comum.js"></script>
<div>
    <form name="eventoFotoForm" action="usuarioUpdateInformacao.definir.do" enctype="multipart/form-data" method="post" >
        <table>
            <tr>
                <td valign="top">
                    <mtw:if test="navegador" value="Chrome">
                        <%@include file="buttonCameraUpdate.jsp" %>
                    </mtw:if>
                    <!-- nao usar display inline devido no crome dar pal algumas funcioes -->
                    <input type="file" onchange="eventoMudarFoto();" name="imagem"  id="image" style="width: 0;height: 0"/>
                    <img src="${foto}" width="120px" height="150"id="fotoUser" class="fotoUser" 
                         onclick="eventoCapturarFoto()" title="Clique para carregar a foto." />
                </td>
            </tr>
            <tr>
                <td style="font-size: 10px">Clique na imagem para carregar a foto...</td>
            </tr>
        </table>
    </form>
</div>