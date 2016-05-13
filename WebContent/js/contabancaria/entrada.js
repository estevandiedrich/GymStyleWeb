$(document).ready(function(){
    $(".inputNumber").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
});
    
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
        var decisao = confirm("Deseja registrar entrada da Conta Bancária");
        if (decisao){
            submitEntradaContaBancaria();
        }
    }
}

function validaEntrada(){
    var retorno = true;
    var valor = $("#valorEntradaContaBancaria").val();
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

function submitEntradaContaBancaria(){
    var valor = $("#valorEntradaContaBancaria").val();
    var descricao = $("#descricaoEntrada").val();
    var formaPagamento= $("#formaPagamentoEntrada").val();
    var idContaBancaria = $("#conta").val();
    var idRegistroEntrada = $("#idRegistroEntrada").val();
        
    $.ajax({
        url: "entradaRetiradaContaBancaria.do",
        type: "POST",
        data: {
            "entrada":true,
            "retirada":false,
            "valor":valor,
            "descricao":descricao,
            "formaPagamento":formaPagamento,
            "idContaBancaria":idContaBancaria,
            "idRegistroEntrada":idRegistroEntrada
        },
        success: function(d){
            readRegistros();
            closeShowEntrada();
        }
    });
}
