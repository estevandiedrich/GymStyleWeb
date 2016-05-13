function eventoTodosPagamento(){
    if ($('#todos').attr('checked')) {
        $('#domingo').attr('checked', true);
        $('#segunda').attr('checked', true);
        $('#terca').attr('checked', true);
        $('#quarta').attr('checked', true);
        $('#quinta').attr('checked', true);
        $('#sexta').attr('checked', true);
        $('#sabado').attr('checked', true);
        $('#feriado').attr('checked', true);
    }else{
        $('#domingo').attr('checked', false);
        $('#segunda').attr('checked', false);
        $('#terca').attr('checked', false);
        $('#quarta').attr('checked', false);
        $('#quinta').attr('checked', false);
        $('#sexta').attr('checked', false);
        $('#sabado').attr('checked', false);
        $('#feriado').attr('checked', false);
    }
}

function eventoAjustarParcelas(pos){
    if ($('#parcela'+pos).attr('checked')) {
    }else{
        pos--;
    }
    for(var i = 1;i<=pos;i++){
        $('#parcela'+i).attr('checked', true);
    }
    var tam = $("#pagamentoSize").val();
    for(var i = (pos+1);i< tam;i++){
        $('#parcela'+i).attr('checked', false);
    }

    var valor = 0;
    for (var i = 1;i < tam;i++){
        if ($('#parcela'+i).attr('checked')) {
            valor += parseFloat($("#valorParcela"+i).val());
        }
    }
    $("#valorSoma").val(valor);
    $("#valorSomado").html(float2moeda(valor));
    var desconto = getValor($("#desconto").val());
    var multa = getValor($("#multa").val());
    var valorApagar = (valor+multa - desconto);
    if(valorApagar < 0){
        valorApagar = 0;
    }
    $("#valorAPagar").val(float2moeda(valorApagar));
}

function eventoSubmitForm(){
    var texto ="Valor de ajuste de R$"+$("#valorTotalPagamento").val() +" para o cancelamento do plano. Deseja cancelar o plano?";
    if(confirm(texto)){
        document.formCancelarPlano.submit();
    }
}