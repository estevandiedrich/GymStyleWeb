
function cadastrarRetirada(){
    if (validaRetiradaContaBancaria()){
        var decisao = confirm("Deseja registrar retirada da Conta Bancária?");
        if (decisao){
            submitRetiradaContaBancaria();
        }
    }
}

function validaRetiradaContaBancaria(){
    var retorno = true;
    var valor = $("#valorRetiradaContaBancaria").val();
    var descricao = $("#descricaoRetirada").val();
    var formaPagamento = $("#formaPagamentoRetirada").val();
        
    $("#valorRetiradaError").hide();
    $("#valorRetiradaError").html("Campo Obrigatório");
        
    if(valor=="" || valor=="0,00"){
        $("#valorRetiradaError").show(200);
        retorno = false;
    }

    if(descricao==""){
        $("#descricaoRetiradaError").show(200);
        retorno = false;
    }else{
        $("#descricaoRetiradaError").hide(200);
    }
    if(formaPagamento==0){
        $("#formaPagamentoRetiradaError").show(200);
        retorno = false;
    }else{
        $("#formaPagamentoRetiradaError").hide(200);
    }
    
    return retorno;
}

function converteFloat(val){
    val = val.replace(".", "");
    val = val.replace(".", "");
    val = val.replace(".", "");
    val = val.replace(",", ".");
    if(val==""){
        val = 0;
    }
    return val;
}

function submitRetiradaContaBancaria(){
    var valor = $("#valorRetiradaContaBancaria").val();
    var descricao = $("#descricaoRetirada").val();
    var formaPagamento= $("#formaPagamentoRetirada").val();
    var idContaBancaria = $("#conta").val();
    var idRegistroEntrada = $("#idRegistroRetirada").val();
    var idRegistroCaixa = $("#idRegistroCaixa").val();
    
    var registroCaixa = false;
    if(document.getElementById("criterioSim").checked){
        registroCaixa = true;
    }
    

    if( idContaBancaria == undefined){
        idContaBancaria = "";
    }
    $.ajax({
        url: "entradaRetiradaContaBancaria.do",
        type: "POST",
        data: {
            "entrada":false,
            "retirada":true,
            "valor":valor,
            "descricao":descricao,
            "formaPagamento":formaPagamento,
            "idContaBancaria":idContaBancaria,
            "idRegistroEntrada":idRegistroEntrada,
            "idRegistroCaixa":idRegistroCaixa,
            "registroCaixa":registroCaixa
        },
        success: function(d){
            readRegistros();
            closeShowRetirada();
        }
    });
}