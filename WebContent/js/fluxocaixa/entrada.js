$(document).ready(function(){
    $(".inputNumber").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
});
    
function showContaEntrada(){
    detalhesContaEntrada = 1;
    $('#linkDetalhesContaEntrada').removeClass('mais').addClass('menos');
    $("#detalhesContaEntrada").show(700);
}
    
function hideContaEntrada(){
    detalhesContaEntrada = 0;
    $('#linkDetalhesContaEntrada').removeClass('menos').addClass('mais');
    $("#contaEntrada option[value='0']").attr('selected',true);
    $("#detalhesContaEntrada").hide(500);
}

var detalhesContaEntrada = 0;
function mostraContaEntrada(){
    if(detalhesContaEntrada == 0){
        showContaEntrada();
    }else{
        hideContaEntrada();
    }
}
    
function cadastrarEntrada(){
    if (validaEntrada()){
        var decisao = confirm("Deseja registrar entrada do caixa?");
        if (decisao){
            submitEntradaCaixa();
        }
    }
}

function validaEntrada(){
    var retorno = true;
    var valor = $("#valorEntradaCaixa").val();
    var descricao = $("#descricaoEntrada").val();
    var formaPagamento= $("#formaPagamentoEntrada").val();
    if(valor=="" || valor == "0,00"){
        $("#valorError").show(200);
        retorno = false;
    }else{
        $("#valorError").hide(500);
    }
    if(descricao==""){
        $("#descricaoError").show(200);
        retorno = false;
    }else{
        $("#descricaoError").hide(500);
    }
    if(formaPagamento==0){
        $("#formaPagamentoError").show(200);
        retorno = false;
    }else{
        $("#formaPagamentoError").hide(500);
    }
    return retorno;
}

function submitEntradaCaixa(){
    console.log("Entrada Caixa");
    var valor = $("#valorEntradaCaixa").val();
    var descricao = $("#descricaoEntrada").val();
    var formaPagamento= $("#formaPagamentoEntrada").val();
    var idContaBancaria = $("#contaEntrada").val();
    var idFluxoCaixa = $("#idFluxoCaixa").val();
    var idRegistroEntrada = $("#idRegistroEntrada").val();
        
    if( idContaBancaria == undefined){
        idContaBancaria = "";
    }
    $.ajax({
        url: "entradaFluxoCaixa.do",
        type: "POST",
        data: {
            "valor":valor,
            "descricao":descricao,
            "formaPagamento":formaPagamento,
            "idContaBancaria":idContaBancaria,
            "idFluxoCaixa":idFluxoCaixa,
            "idRegistroEntrada":idRegistroEntrada
        },
        success: function(d){
            window.location = "managerFluxoCaixa.do";
        }
    });
}
