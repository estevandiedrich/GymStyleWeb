$(document).ready(function(){
    $("#descricao").attr('disabled', true);
    $("#descricao").css("background-color","white");
    $("#descricao").css("maxWidth","600px" );
    $("#descricao").css("maxHeight","100px" );
    $("#descricao").css("minWidth","600px" );
    $("#descricao").css("minHeight","100px" );
});

function imprimirFicha(idUsuario,idFicha,treino){
    var url = "";
    url = "fichaReport.do?idUsuario="+idUsuario+"&idFicha="+idFicha;
    if(treino != ''){
        url += "&treino="+treino;
    }
    var win = window.open(url, "_blank");
    //win.document.title="Test doc title";
    win.focus();
    
//window.open(url, "_blank", "menubar=yes, toolbar=no, location=no, scrollbars=yes, scrolling=yes");
}