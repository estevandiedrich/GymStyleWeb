function confirmarFecharCaixa(){
    var decisao = confirm("Deseja fechar o caixa?");
    if (decisao){
        submitFecharCaixa();
    }
}
    
function submitFecharCaixa(){
    //alert("AAAAAAA");
    var idFluxoCaixa = $("#idFluxoCaixa").val();
    var valorInicial = $("#valorInicialFechar").val();
    var valorFinal = $("#valorFinalFechar").val();
    var valorDinheiro = 0;
    var valorDeposito = 0;
    var valorBoleto = 0;
    var valorCheque = 0;
    var valorCartao = 0;
    //alert("BBBBBBBBB"+idFluxoCaixa);

    $.ajax({
        url: "fecharFluxoCaixa.do",
        type: "POST",
        data: {
            "idFluxoCaixa":idFluxoCaixa,
            "valorInicial":valorInicial,
            "valorFinal":valorFinal,
            "valorDinheiro":valorDinheiro,
            "valorDeposito":valorDeposito,
            "valorCheque":valorCheque,
            "valorCartao":valorCartao,
            "valorBoleto":valorBoleto
        },
        success: function(d){
            window.location = "managerFluxoCaixa.do";
        }
    });
}
    

