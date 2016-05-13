
function showContaRetirada(){
    detalhesConta = 1;
    $('#linkDetalhesContaRetirada').removeClass('mais').addClass('menos');
    $("#detalhesContaRetirada").show(700);
}
    
function hideContaRetirada(){
    detalhesConta = 0;
    $('#linkDetalhesContaRetirada').removeClass('menos').addClass('mais');
    $("#contaRetirada option[value='0']").attr('selected',true);
    $("#detalhesContaRetirada").hide(500);
}
    
var detalhesConta = 0;
function mostraContaRetirada(){
    if(detalhesConta == 0){
        detalhesConta = 1;
        $('#linkDetalhesContaRetirada').removeClass('mais').addClass('menos');
        $("#detalhesContaRetirada").show(700);
    }else{
        detalhesConta = 0;
        $('#linkDetalhesContaRetirada').removeClass('menos').addClass('mais');
        $("#contaRetirada option[value='0']").attr('selected',true);
        $("#detalhesContaRetirada").hide(500);
    }
}

var detalhesRetiradaValorCaixa = 0;
function mostraRetiradaValorCaixa(){
    if(detalhesRetiradaValorCaixa == 0){
        detalhesRetiradaValorCaixa = 1;
        $('#linkDetalhesRetiradaValorCaixa').removeClass('mais').addClass('menos');
        $(".detalhesRetiradaValorCaixa").show();
    }else{
        detalhesRetiradaValorCaixa = 0;
        $('#linkDetalhesRetiradaValorCaixa').removeClass('menos').addClass('mais');
        $(".detalhesRetiradaValorCaixa").hide();
    }
}

function cadastrarRetirada(){
    if (validaRetirada()){
        var decisao = confirm("Deseja registrar retirada do caixa?");
        if (decisao){
            submitRetiradaCaixa();
        }
    }
}

function validaRetirada(){
    var retorno = true;
    var valor = $("#valorRetiradaCaixa").val();
    var descricao = $("#descricaoRetirada").val();
    var formaPagamento = $("#formaPagamentoRetirada").val();
        
    //$("#valorRetiradaError").width(138);
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
    var valorDinheiro = ($("#valorDinheiroRetirada").val());
    valorDinheiro = parseFloat(valorDinheiro);
        
    var valorCheque = ($("#valorChequeRetirada").val());
    valorCheque = parseFloat(valorCheque);


    if(formaPagamento == 1){
        var dinheiro = ($("#dinheiroCaixa").val());
        valor = converteFloat(valor);
        dinheiro = parseFloat(dinheiro);
        if(valor > (dinheiro + valorDinheiro)){
            if((dinheiro + valorDinheiro) == 0){
                $("#valorRetiradaError").html("Não há valor em dinheiro no caixa");
            }else{
                $("#valorRetiradaError").html("Valor em dinheiro no caixa " + float2moeda(dinheiro + valorDinheiro));
            }
            $("#valorRetiradaError").width(238);
            $("#valorRetiradaError").show(200);
            retorno = false;
        }
    }
    if(formaPagamento == 5){
        var cheque= ($("#chequeCaixa").val());
        valor = (converteFloat(valor));
        cheque = parseFloat(cheque);
        if(valor > (cheque+valorCheque)){
            if(cheque == 0){
                $("#valorRetiradaError").html("Não há valor em cheque no caixa");
            }else{
                $("#valorRetiradaError").html("Valor em cheque no caixa " + float2moeda(cheque+valorCheque));
            }
            $("#valorRetiradaError").width(238);
            $("#valorRetiradaError").show(200);
            retorno = false;
        }
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

function submitRetiradaCaixa(){
    var valor = $("#valorRetiradaCaixa").val();
    var descricao = $("#descricaoRetirada").val();
    var formaPagamento= $("#formaPagamentoRetirada").val();
    var idContaBancaria = $("#contaRetirada").val();
    var idFluxoCaixa = $("#idFluxoCaixa").val();
    var idRegistroRetirada = $("#idRegistroRetirada").val();

    if( idContaBancaria == undefined){
        idContaBancaria = "";
    }

    $.ajax({
        url: "retiradaFluxoCaixa.do",
        type: "POST",
        data: {
            "valor":valor,
            "descricao":descricao,
            "formaPagamento":formaPagamento,
            "idContaBancaria":idContaBancaria,
            "idFluxoCaixa":idFluxoCaixa,
            "idRegistroRetirada":idRegistroRetirada
        },
        success: function(d){
            window.location = "managerFluxoCaixa.do";
        }
    });
}