$(document).ready(function(){
    if(document.getElementsByName("ativa").length > 0){
        statusFicha();
    }
    
//    $( "input[name=ativa]:radio" ).change(function(e){
//        //e.preventDefault();
//        alert("AAa");
//    });
});

function atualizaStatusFicha(idFicha){
    var idUsuario = document.getElementById("idUsuario").value;
    var status = eventoSelectRadioFichaAtiva(idFicha);
    var val = "atualizarStatusFicha.do?idFicha="+idFicha
    +"&status="+status
    +"&idUsuario="+idUsuario;
    $.post(val, $(this).serialize(), function(g){
        //            $("#status"+id).hide();
        //            $("#status"+id).html(g);
        //            $("#status"+id).show(700);
        statusFicha();
    });
}
function statusFicha(){
    var idUsuario = document.getElementById("idUsuario").value;
    var val = "statusFicha.do?idUsuario="+idUsuario;
    $.post(val, $(this).serialize(), function(g){
        $("#msgFicha").hide(0);
        $("#msgFicha").html(g);
        $("#msgFicha").show(700);
    });
}
function eventoSelectRadioFichaAtiva(idFicha){
//    var grupo = $("#ativa"+idFicha).parents("tbody");//.attr("id").replace("div-", "");
//    alert(grupo);
    var num = document.getElementById("fichaValue").value;
    if(idFicha == num){
        document.getElementById("ativa"+idFicha).checked = false;
        document.getElementById("fichaValue").value = -1;
        return false;
    }else{
        document.getElementById("fichaValue").value = idFicha;
        return true;
    }
}
