$(document).ready(function(){
    $("#criterioInicio").mask("99/99/9999");
    $("#criterioFim").mask("99/99/9999");
    $("#criterioCpf").mask("999.999.999-99");
});
  
function getPosicaoElemento(elemID){
    var offsetTrail = document.getElementById(elemID);
    var offsetLeft = 0;
    var offsetTop = 0;
    while (offsetTrail) {
        offsetLeft += offsetTrail.offsetLeft;
        offsetTop += offsetTrail.offsetTop;
        offsetTrail = offsetTrail.offsetParent;
    }
    if (navigator.userAgent.indexOf("Mac") != -1 && 
        typeof document.body.leftMargin != "undefined") {
        offsetLeft += document.body.leftMargin;
        offsetTop += document.body.topMargin;
    }
    return {
        left:offsetLeft, 
        top:offsetTop
    };
}    
 
var consultando = false;
function consultaData(){
    if(!consultando){
        consultando = true;
        setTimeout(function(){
            submitRelatorioFormEventoRead();
            consultando = false;    
        }, 1200);
    }
}
 
function submitFormEventoRead(p){
    if(p!=""){
        $("#p").val(p);
    }
    submitRelatorioFormEventoRead();
}
function valida(){
    var retornoInicio = true;
    if($("#criterioInicio").val() == ""){            
        retornoInicio = true;
    }else if($("#criterioInicio").val().length < 10){
        retornoInicio = false;
    }
    var retornoFim= true;
    if($("#criterioFim").val() == ""){
        retornoFim = true;
    }else if($("#criterioFim").val().length < 10){
        retornoFim = false;
    }
    if(retornoInicio && retornoFim){
        return true;
    }
    return false;
}
    
function submitRelatorioFormEventoRead(){
    if(valida()){
        $("#processando").show(700);
        var criterioCatraca = $("#criterioCatraca").val();
        var criterioNome= $("#criterioNome").val();
        var criterioMatricula= $("#criterioMatricula").val();
        var criterioDescricao= $("#criterioDescricao").val();
        var criterioCpf= $("#criterioCpf").val();
        var criterioInicio= $("#criterioInicio").val();
        var criterioFim= $("#criterioFim").val();
        var p= $("#p").val();
        var orderBy= false;
        if(document.getElementById("orderBy").checked){
            orderBy = true;
        }
        if(p == undefined){
            p = 1;
        }
        for(var i = 0;i<10;i++){
            criterioInicio=criterioInicio.replace("_", "");
            criterioFim = criterioFim.replace("_", "");
        }
        if(criterioInicio.length == 10 && criterioFim.length == 10){
            console.log("Entrou");
            $.ajax({
                url: "eventoRead.do",
                type: "POST",
                data: {
                    "criterioInicio":criterioInicio,
                    "criterioFim":criterioFim,
                    "criterioNome":criterioNome,
                    "criterioMatricula":criterioMatricula,
                    "criterioDescricao":criterioDescricao,
                    "criterioCatraca":criterioCatraca,
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
    }
}
function submitRelatorioFormEventoReport(){
    if(valida()){
        document.relatorioForm.action = "eventoReport.do";
        document.relatorioForm.target='_blank';
        document.relatorioForm.submit();
    }
}

