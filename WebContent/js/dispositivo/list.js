
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
    var criterioCpf= $("#criterioCpf").val();
    var p= $("#p").val();
    var orderBy= false;
    if(document.getElementById("orderBy").checked){
        orderBy = true;
    }
    if(p == undefined){
        p = 1;                
    }

    $.ajax({
        url: "dispositivoRead.do",
        type: "POST",
        data: {
            "criterioNome":criterioNome,
            "criterioCpf":criterioCpf,
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
    submitFormRead();
    contaDispositivo();
});
var cont = 10;
function contaDispositivo(){
    if(cont == 0) {
        submitFormRead();
        cont = 10;
    }
    if (cont != 0){
        cont = cont-1;
        setTimeout("contaDispositivo()", 1000);
    }
}
contaDispositivo();
    
function limpar(id){
    decisao = confirm("Deseja realmente limpar catraca?\nTodos os dados da catraca seram perdidos!");
    if (decisao){
        window.location = "limparDispositivo.do?id="+id;
    }
}