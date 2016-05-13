
function calculaValorPrimeiraParcela(){
    if($("#valorTotal").val()==""){
        return 0;
    }
    var valor = parseFloat($("#valorTotal").val());
    var valorMatricula = parseFloat($("#valorMatricula").val());
    var diaVencimento = $("#diaVencimento").val();
    var dataAtual = new Date();
    var dia = dataAtual.getDate();
    var qtde = 0;
    if(dia < diaVencimento){
        qtde = diaVencimento - dia;
    }else{
        qtde = dia - diaVencimento;
        qtde = 30 - qtde;
    }
    var descontoReal = parseFloat($("#descontoReal").val());
    var descontoPercentual = parseFloat($("#descontoPercentual").val());
    var valorFinal = (((valor / 30 )* qtde));
    valorFinal += valorMatricula;
    if(descontoReal == 0){
        if(descontoPercentual != 0){
            valorFinal = valorFinal - (valorFinal*(descontoPercentual/100));
        }
    }else{
        valorFinal = valorFinal - descontoReal;
        if(valorFinal<0){
            valorFinal = 0;
        }
    }
    return (valorFinal);
}
    
function eventoPostergar(){
    if($("#valorTotal0").val() != 0){
        $("#valorLabelTotal0").html("0,00");
        var valor = ((parseFloat($("#valorTotal").val())- parseFloat($("#descontoReal").val())) + calculaValorPrimeiraParcela());
        $("#valorTotal0").val(0);
        $("#valorTotal1").val(float2moeda(valor));
    }else{
        $("#valorTotal0").val(float2moeda(calculaValorPrimeiraParcela()));
        var valorTotal = $("#valorTotal").val() - parseFloat($("#descontoReal").val());
        var descontoPercentual = parseFloat($("#descontoPercentual").val());
        if(descontoPercentual>0){
            valorTotal = valorTotal - (valorTotal * (descontoPercentual/100));
        }
        $("#valorTotal1").val(float2moeda(valorTotal));
    }
}