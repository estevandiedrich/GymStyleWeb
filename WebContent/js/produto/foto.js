
function eventoCapturarFoto(){
    document.getElementById("image").style.zIndex="1";
    document.getElementById("image").click();
}
function eventoMudarFoto(){
    document.eventoFotoForm.submit();
}
function limparFoto(){
    $("#fotoUser").attr('src','images/semFoto.png');
}