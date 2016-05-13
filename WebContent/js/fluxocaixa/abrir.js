var detalhesConta = 0;
function mostraConta(){
    if(detalhesConta == 0){
        detalhesConta = 1;
        $('#linkDetalhesConta').removeClass('mais').addClass('menos');
        $("#detalhesConta").show(700);
    }else{
        detalhesConta = 0;
        $('#linkDetalhesConta').removeClass('menos').addClass('mais');
        $("#conta option[value='0']").attr('selected',true);
        $("#detalhesConta").hide(500);
    }
}
    
function preencheCampos(){
    var val = $("#opcao").val();
    if(val == 0){//caixa anterior
        $("#viDinheiro").val($("#viDinheiro2").val());
        $("#viCheque").val($("#viCheque2").val());
        $("#viBoleto").val($("#viBoleto2").val());
        $("#viCartao").val($("#viCartao2").val());
        $("#viDeposito").val($("#viDeposito2").val());
        $("#valorInicialAbrir").val($("#valorInicial2").val());
            
        $('.inputNumber').removeClass('inputNumber').addClass('inputDisabledNumber');
        $('.inputDisabledNumber').attr('disabled',true);
    }else if(val == 1){//Caixa Zerado
        $(".inputDisabledNumber").val("0,00");
        $(".inputNumber").val("0,00");
        $('.inputNumber').removeClass('inputNumber').addClass('inputDisabledNumber');
        $('.inputDisabledNumber').attr('disabled',true);
    }else if(val == 2){//Com os valores informados abaixo
        $('.inputDisabledNumber').removeClass('inputDisabledNumber').addClass('inputNumber');
        $('.inputNumber').attr('disabled',false);
    }
    $("#valorInicialAbrir").addClass('inputDisabledNumber');
    $('#valorInicialAbrir').attr('disabled',true);
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
    
function atualizaSoma(){
    var viDinheiro = $("#viDinheiro").val();
    var viBoleto= $("#viBoleto").val();
    var viCheque = $("#viCheque").val();
    var viDeposito = $("#viDeposito").val();
    var viCartao = $("#viCartao").val();

    viDinheiro = converteFloat(viDinheiro);
    viBoleto = converteFloat(viBoleto);
    viCheque = converteFloat(viCheque);
    viDeposito = converteFloat(viDeposito);
    viCartao = converteFloat(viCartao);

    var valorTotal = (parseFloat(viDinheiro) +  parseFloat(viCheque)+ parseFloat(viBoleto) + parseFloat(viCartao) + parseFloat(viDeposito));
    if(valorTotal<0){
        valorTotal = 0;
    }
    $("#valorInicialAbrir").val(float2moeda(valorTotal));
}
    
function confirmarAbrirCaixa(){
    var decisao = confirm("Deseja abrir o fluxo de caixa?");
    if (decisao){
        submitAbrirCaixa();
    }
}
    
function submitAbrirCaixa(){
    var viInicial = $("#valorInicialAbrir").val();
    var viDinheiro = $("#viDinheiro").val();
    var viBoleto= $("#viBoleto").val();
    var viCheque = $("#viCheque").val();
    var viDeposito = $("#viDeposito").val();
    var viCartao = $("#viCartao").val();

    $.ajax({
        url: "abrirFluxoCaixa.do",
        type: "POST",
        data: {
            "viInicial":viInicial,
            "viDinheiro":viDinheiro,
            "viBoleto":viBoleto,
            "viCheque":viCheque,
            "viCartao":viCartao,
            "viDeposito":viDeposito
        },
        success: function(e){
            closeShowAbrirCaixa(true);
        }
    });
}
    
$(document).ready(function(){
    $(".inputDisabledNumber").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
    $('.inputDisabledNumber').attr('disabled',true);
});
