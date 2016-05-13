function eventoTodosPagos(){
    var tam = $("#pagamentoSize").val()
    var chek = false;
    if ($('#todosZerar').attr('checked')) {
        chek = true;
    }
    for(var i = 1;i<= tam;i++){
        $('#zerarParcela'+i).attr('checked', chek);
        eventoZerarParcela(i);
    }
}
    
    
function eventoTodosPagamentos(){
    var tam = $("#pagamentoSize").val()
    var chek = false;
    if ($('#todos').attr('checked')) {
        chek = true;
    }
    for(var i = 1;i<= tam;i++){
        $('#parcela'+i).attr('checked', chek);
        eventoAnularParcela(i);
    }
}
    
function eventoAjustarPagamentos(){
    var tam = $("#pagamentoSize").val()
    var valor = 0;
    var valorPago = 0;
    var i = 0;
    for(i = 1;i<= tam;i++){
        if(($('#valorParcela'+i).val())!= null){
            valor = valor + parseFloat(($('#valorParcela'+i).val()));
        }
        if(($('#valorPago'+i).val())!= null){
            valorPago = valorPago + parseFloat($('#valorPago'+i).val());
        }
    }
    $('#valorPagamento').html(float2moeda(valor));
    $('#valorPagoPagamento').html(float2moeda(valorPago));
    if(valor > valorPago){
        $('#valorPagoPagamento').css("color","red");
    }else if(valor < valorPago){
        $('#valorPagoPagamento').css("color","green");
    }else {
        $('#valorPagoPagamento').css("color","black");
    }

    var valorTotal = valor - valorPago;
    if(valorTotal<0){
        valorTotal = 0;
    }
    $('#valorTotalPagamento').val(float2moeda(valorTotal));
}
function eventoAnularParcela(i){
    if ($('#parcela'+i).attr('checked')) {
        $('#valorPar'+i).html("");
        $('#valorParcela'+i).val(0);
        $('#valorPag'+i).html("");
        $('#valorPago'+i).val(0);
        $('#pagamento'+i).html("");
        $('#zerarParcela'+i).css("display","none");
    }else{
        $('#valorPar'+i).html(float2moeda($('#valorParcela2'+i).val()));
        $('#valorParcela'+i).val($('#valorParcela2'+i).val());
        $('#valorPag'+i).html(float2moeda($('#valorPago2'+i).val()));
            
        $('#valorPag'+i).html(float2moeda($('#valorPago2'+i).val()));
        $('#valorPago'+i).val($('#valorPago2'+i).val());
        $('#pagamento'+i).html($('#pagamento2'+i).val());
        $('#zerarParcela'+i).css("display","inline");
        $('#zerarParcela'+i).attr('checked', false);
    }
    eventoAjustarPagamentos();
}
function eventoZerarParcela(i){
    if ($('#zerarParcela'+i).attr('checked')) {
        $('#valorPag'+i).html("");
        $('#valorPago'+i).val(0);
        $('#pagamento'+i).html("");
    }else{
        $('#valorPag'+i).html(float2moeda($('#valorPago2'+i).val()));
        $('#valorPago'+i).val($('#valorPago2'+i).val());
        $('#pagamento'+i).html($('#pagamento2'+i).val());
    }
    eventoAjustarPagamentos();
}
    

$(document).ready(function(){
    $("#valorTotalPagamento").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    })
});
