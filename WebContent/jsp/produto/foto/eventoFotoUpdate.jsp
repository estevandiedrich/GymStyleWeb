<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="style/style.css" type="text/css"/>
<script src="js/produto/foto.js"></script>
<script src="js/jquery.js"></script>
<div>
    <form name="eventoFotoForm" action="produtoUpdate.definir.do" enctype="multipart/form-data" method="post" >
        <table>
            <tr>
                <td valign="top">
                    <input type="file" onchange="eventoMudarFoto();" name="imagem"  id="image" style="width: 0;height: 0"/>
                    <img src="${foto}" width="220px" height="150" id="fotoUser" class="fotoUser" 
                         onclick="eventoCapturarFoto()" title="Clique para carregar a foto." />
                </td>
                <!--td valign="top">
                    <input type="button" class="limpar" id="limpar" onclick="limparFoto()" title="Limpar foto!"/>
                </td-->
            </tr>
            <tr>
                <td style="font-size: 10px" class="one">Clique na imagem para carregar a foto...</td>
            </tr>
        </table>
    </form>
</div>