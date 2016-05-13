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
    var criterioMatricula= $("#criterioMatricula").val();
    var p= $("#p").val();
    var orderBy= false;
    if(document.getElementById("orderBy").checked){
        orderBy = true;
    }
    if(p == undefined){
        p = 1;                
    }

    $.ajax({
        url: "usuarioAvaliacaoFisicaRead.do",
        type: "POST",
        data: {
            "criterioNome":criterioNome,
            "criterioCpf":criterioCpf,
            "criterioMatricula":criterioMatricula,
            "criterioAlunoOuFuncionario":true,
            "Funcionario":true,
            "pag.p":p,
            "orderBy":orderBy,
            "pag.currentPage":p
        },
        success: function(d){
            $("#content").html(d);
        }
    });
}