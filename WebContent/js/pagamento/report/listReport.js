function submitFormPagamentoReportRead(p){
    if(p!=""){
        $("#p").val(p);
    }
    submitRelatorioFormPagamentoReportRead();
}

var imprimir = false;
function consultaNome(){
    if(!imprimir){
        imprimir = true;
        setTimeout(function(){
            submitRelatorioFormPagamentoReportRead();
            imprimir = false;
        }, 1200);
    }
}

function submitRelatorioFormPagamentoReportRead(){
    var criterioMatricula= $("#criterioMatricula").val();
    var criterioNome= $("#criterioNome").val();
    var criterioCpf = $("#criterioCpf").val();
    var criterioInicio= $("#criterioInicio").val();
    var criterioFim= $("#criterioFim").val();
    var criterioPlano= $("#criterioPlano").val();
    var criterioFuncionario= $("#criterioFuncionario").val();
    var tipo = "pagamento";
    var realizado= false;
    if(document.getElementById("vencimento").checked){
        tipo = "vencimento";
    }else{
        realizado= true;
    }
    if(document.getElementById("realizado").checked){
        realizado = true;
    }
    var orderBy= false;
    if(document.getElementById("orderBy").checked){
        orderBy = true;
    }
    var p= $("#p").val();
    if(p == undefined){
        p = 1;
    }

    $.ajax({
        url: "pagamentoReportRead.do",
        type: "POST",
        data: {
            "criterioNome":criterioNome,
            "criterioMatricula":criterioMatricula,
            "criterioCpf":criterioCpf,
            "criterioInicio":criterioInicio,
            "criterioFim":criterioFim,
            "criterioPlano":criterioPlano,
            "criterioFuncionario":criterioFuncionario,
            "realizado":realizado,
            "tipo":tipo,
            "pag.p":p,
            "orderBy":orderBy,
            "pag.currentPage":p
        },
        success: function(d){
            $("#content").html(d);
        }
    });

}
function submitRelatorioFormPagamentoReport(){
    //Não precisa passar os parametros pq o submit ja reenvia todos
    //        var criterioNome= $("#criterioNome").val();
    //        var criterioCpf= $("#criterioCpf").val();
    //        var criterioInicio= $("#criterioInicio").val();
    //        var criterioFim= $("#criterioFim").val();
    //        var criterioPlano= $("#criterioPlano").val();
    //        var tipo = "pagamento";
    var realizado= false;
    if(document.getElementById("vencimento").checked){
        tipo = "vencimento";
    }else{
        realizado= true;
    }
    if(document.getElementById("realizado").checked){
        realizado = true;
    }
    document.relatorioForm.action = "pagamentoReport.do?"
    +"&realizado="+realizado
    //            +"&tipo="+tipo
    //            +"&criterioCpf="+criterioCpf
    //            +"&criterioFim="+criterioFim
    //            +"&criterioInicio="+criterioInicio
    //            +"&criterioFim="+criterioFim
    //            +"&criterioNome="+criterioNome
    //            +"&criterioPlano="+criterioPlano
    ;
    document.relatorioForm.target='_blank';
    document.relatorioForm.submit();
}