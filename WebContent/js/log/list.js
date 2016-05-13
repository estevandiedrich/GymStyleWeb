
function setCampoOrderBy(){
    if(document.listForm['orderBy'].checked){
        document.getElementById("status").value = true;
    }else{
        document.getElementById("status").value = false;
    }
}

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
    var criterioUsuario= $("#criterioUsuario").val();
    var criterioDescricao= $("#criterioDescricao").val();
    var criterioInicio= $("#criterioInicio").val();
    var criterioFim= $("#criterioFim").val();
    var criterioTipo= $("#criterioTipo").val();
    var p= $("#p").val();
    var orderBy= false;
    if(document.getElementById("orderBy").checked){
        orderBy = true;
    }
    if(p == undefined){
        p = 1;                
    }

    $.ajax({
        url: "logRead.do",
        type: "POST",
        data: {
            "criterioUsuario":criterioUsuario,
            "criterioInicio":criterioInicio,
            "criterioFim":criterioFim,
            "criterioTipo":criterioTipo,
            "criterioDescricao":criterioDescricao,
            "pag.p":p,
            "orderBy":orderBy,
            "pag.currentPage":p
        },
        success: function(d){
            $("#content").html(d);
        }
    });
}
    
$(document).ready(function(){
    $("#criterioInicio").mask("99/99/9999");
    $("#criterioFim").mask("99/99/9999");

});
