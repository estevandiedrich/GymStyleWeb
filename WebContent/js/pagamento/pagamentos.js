function eventoTodosPagamentos(){
    var tam = $("#pagamentoSize").val()
    var chek = false;
    if ($('#todos').attr('checked')) {
        chek = true;
    }
    
    for(var i = 1;i<= tam;i++){
        $('#parcela'+i).attr('checked', chek);
        mostrarImprimir($('#idParcela'+i).val(), i);
    }
    eventoAjustarParcelas();
    imprimirPagamento();
}

function imprimirPagamento(){
    var temParcelaChecado = false;
    var status = 3;
    if ($('#imprime1').attr('checked')) {
        status = 1;
    }else
    if ($('#imprime2').attr('checked')) {
        status = 2;
    }else
    if ($('#imprime3').attr('checked')) {
        status = 3;
    }
    var tamanho = $("#pagamentoSize").val();
    for(var j = 1;j <= tamanho;j++){
        var checado = false;
        if ($('#pagamento'+$('#idParcela'+j).val()).attr('checked')) {
            temParcelaChecado = true;
            checado = true;
        }

        if(status!=3){
            var val = "pagamentoUpdateImprimirAjax.do?id="+$('#idParcela'+j).val()
            +"&imprime="+checado
            +"&status="+status;
            $.post(val, $(this).serialize(), function(g){});
        }
    }
    //alert(status +" - " + temParcelaChecado +" - " + parcelaMarcado());
    if(status == 3 && temParcelaChecado){
        $(".msgImprimirEntradaSaida").html("Para Imprimir recibo, selecione entrada ou saída");
        $(".msgImprimirEntradaSaida").show(700);
    }else
    if(status != 3 && !temParcelaChecado){
        $(".msgImprimirEntradaSaida").html("Marque a parcela que deseja imprimir!");
        $(".msgImprimirEntradaSaida").show(700);
    }else if(status != 3 && temParcelaChecado){
        $(".msgImprimirEntradaSaida").hide();
    }else{
        if(parcelaMarcado()){
            $(".msgImprimirEntradaSaida").html("Pagamentos não serão impressos na catraca!");
            $(".msgImprimirEntradaSaida").show(700);
        }
    }
    //alert(parcelaMarcado()+" - "+status+" - "+temParcelaChecado);

    if(!temParcelaChecado && status != 3 && parcelaMarcado()){
        $(".selecioneImprimir").css("color","red" );
        $(".selecioneImprimir").css("background-color","#FFEFD5" );
    }else{
        $(".selecioneImprimir").css("color","#888" );
        $(".selecioneImprimir").css("background-color","#FFFFFF" );
    }
    mostraMsg(status);
}

function parcelaMarcado(){
    var chek = false;
    var tam = $("#pagamentoSize").val()
    for(var i = 1;i<= tam;i++){
        if ($('#parcela'+i).attr('checked')) {
            chek = true;
            break;
        }
    }
    return chek;
}

function parcelaHaImprimir(){
    var chek = false;
    var tamanho = $("#pagamentoSize").val();
    for(var j = 1;j <= tamanho;j++){
        if ($('#pagamento'+$('#idParcela'+j).val()).attr('checked')) {
            chek = true;
            break;
        }
    }
    return chek;
}
function desmarcarTodosImprimir(){
    var tamanho = $("#pagamentoSize").val();
    for(var j = 1;j <= tamanho;j++){
        $('#pagamento'+$('#idParcela'+j).val()).attr('checked', false);
        var val = "pagamentoUpdateImprimirAjax.do?id="+$('#idParcela'+j).val()
        +"&imprime=" + false
        +"&status=" + 2;
        $.post(val, $(this).serialize(), function(g){});
    }
    $(".msgImprimirEntradaSaida").hide();
}

function mostraMsgQuandoChekado(){
    if ($('#imprime3').attr('checked')) {
        $(".msgImprimirEntradaSaida").show(700);
    }else{
        $(".msgImprimirEntradaSaida").hide();
    }
}

function mostrarImprimir(id,i){
    if(($('#parcela'+i).val()!= undefined)){
        if ($('#parcela'+i).attr('checked')) {
            $("#divPagamento"+id).show(200);
        //$('#pagamento'+id).attr('checked', true);//Nao checar
        }else{
            $("#divPagamento"+id).hide(200);
            $('#pagamento'+id).attr('checked', false);
        }
    }
}