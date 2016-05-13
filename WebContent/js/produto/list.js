$(document).ready(function(){
    $("#criterioCodigo").keyup(function() {
        submitFormRead();
    });
    $("#statusAtivo").click(function() {
        submitFormRead();
    });
    $("#statusInativo").click(function() {
        submitFormRead();
    });
    $("#criterioCategoria").change(function() {
        submitFormRead();
    });
});

function submitFormFiltrar(p){
    if(p!=""){
        $("#p").val(p);
    }
    submitFormRead();
}

var imprimir = false;
function consultaNome(){
    imprimir = true;
    setTimeout(function(){
        if(imprimir == true){
            //console.log(document.querySelector('#criterioNome').value);submitFormRead();
            submitFormRead();
        }
        imprimir = false;
    }, 1000);
}

function submitFormRead(){
    $("#processando").show(700);
    var criterioNome= $("#criterioNome").val();
    var criterioCodigo= $("#criterioCodigo").val();
    var p= $("#p").val();
    var orderBy= false;
    if(document.getElementById("orderBy").checked){
        orderBy = true;
    }
    var criterioAtivo = true;
    if(document.getElementById("statusInativo").checked){
        criterioAtivo = false;
    }
    var criterioCategoria = $("#criterioCategoria").val();
    if(document.getElementById("statusInativo").checked){
        criterioAtivo = false;
    }
    if(p == undefined){
        p = 1;
    }
    $.ajax({
        url: "produtoRead.do",
        type: "POST",
        data: {
            "criterioNome":criterioNome,
            "criterioCategoria":criterioCategoria,
            "criterioCodigo":criterioCodigo,
            "criterioAtivo":criterioAtivo,
            "pag.p":p,
            "orderBy":orderBy,
            "pag.currentPage":p
        },
        success: function(d){
            $("#content").html(d);
        }
    });
}

function somenteNumero(e){
    var tecla=(window.event)?event.keyCode:e.which;
    if((tecla>47 && tecla<58)) return true;
    else{
        if (tecla==8 || tecla==0) return true;
        else return false;
    }
}