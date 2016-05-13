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
    var orderBy= false;
    if(document.getElementById("orderBy").checked){
        orderBy = true;
    }
    var p= $("#p").val();
    if(p == undefined){
        p = 1;                
    }
    $.ajax({
        url: "fornecedorRead.do",
        type: "POST",
        data: {
            "criterioNome":criterioNome,
            "criterioAtivo":true,
            "pag.p":p,
            "orderBy":orderBy,
            "pag.currentPage":p
        },
        success: function(d){
            $("#content").html(d);
        }
    });
}