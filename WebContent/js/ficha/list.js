function submitFormFiltrar(p){
    if(p!=""){
        $("#p").val(p);
    }
    submitFormRead();
}

var imprimir = false;
function consultaNome(){
    if(!imprimir){
        imprimir = true;
        setTimeout(function(){
            submitFormRead();
            imprimir = false;
        }, 1200);
    }
}

function submitFormRead(){
    $("#processando").show(700);
    var criterioNome= $("#criterioNome").val();
    var criterioCpf= $("#criterioCpf").val();
    var criterioMatricula= $("#criterioMatricula").val();
    var orderBy= false;
    if(document.getElementById("orderBy").checked){
        orderBy = true;
    }
    var p= $("#p").val();
    if(p == undefined){
        p = 1;                
    }
    $.ajax({
        url: "fichaRead.do",
        type: "POST",
        data: {
            "criterioNome":criterioNome,
            "criterioCpf":criterioCpf,
            "criterioMatricula":criterioMatricula,
            "criterioAlunoOuFuncionario":true,
            "pag.p":p,
            "orderBy":orderBy,
            "pag.currentPage":p
        },
        success: function(d){
            $("#content").html(d);
        }
    });
}