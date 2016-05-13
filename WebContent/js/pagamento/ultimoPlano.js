$(document).ready(function(){
    $("#observacao").attr('disabled', true);
    $("#observacao").css("background-color","white");
    atualizar();
});
    
function imprimirPagamentoCatraca(id){
    var tam = document.getElementsByName("dispositivo").length;
    var idDispositivo = null;
    if(tam>0){
        for (var i = 0; i < tam; i++){
            if(document.getElementsByName("dispositivo").item(i).checked){
                idDispositivo = document.getElementsByName("dispositivo").item(i).valueOf().value;
                break;
            }
        }
        if(idDispositivo!=null){
            if(confirm("Deseja imprimir esta parcela?")){
                $.ajax({
                    url: "imprimirPagamento.do",
                    type: "POST",
                    data: {
                        "id":id,
                        "idDispositivo":idDispositivo
                    },
                    success: function(d){
                    //$("#content").html(d);
                    }
                });
            }
        }else{
            alert("Selecione a Catraca para imprimir!");
        }
    }else{
        alert("Catracas fora da rede!");
    }
}
//=========================================================================================================

function getStatus(){
    var status = 3;
    if ($('#imprime1').attr('checked')) {
        status = 1;
    }else if ($('#imprime2').attr('checked')) {
        status = 2;
    }else if ($('#imprime3').attr('checked')) {
        status = 3;
    }
    return status;
}

function imprimirPagamento(){
    var status = getStatus();
    
    var tamanho = $("#pagamentoSize").val();
    for(var j = 1;j <= tamanho;j++){
        var checado = false;
        if ($('#pagamentoChek'+$('#idParcela'+j).val()).attr('checked')) {
            checado = true;
        }
        if(status!=3){
            var val = "pagamentoUpdateImprimirAjax.do?id="+$('#idParcela'+j).val()
            +"&imprime="+checado
            +"&status="+status;
            $.post(val, $(this).serialize(), function(g){
                });
        }
    }
    refreshStatus();
}

function refreshStatus(){
    var temParcelaChecado = false;
    var status = getStatus();
    var tamanho = $("#pagamentoSize").val();
    for(var j = 1;j <= tamanho;j++){
        if ($('#pagamentoChek'+$('#idParcela'+j).val()).attr('checked')) {
            temParcelaChecado = true;
        }
    }
    if(status == 3 && temParcelaChecado){
        $("#msgImprimirEntradaSaida").show(700);
    }else{
        $("#msgImprimirEntradaSaida").hide();
    }
    if(status != 3 && !temParcelaChecado){
        $("#msgSelecioneParcela").show(700);
    }else{
        $("#msgSelecioneParcela").hide();
    }
}

function desmarcarTodosImprimir(){
    var tamanho = $("#pagamentoSize").val();
    for(var j = 1;j <= tamanho;j++){
        $('#pagamentoChek'+$('#idParcela'+j).val()).attr('checked', false);
        var val = "pagamentoUpdateImprimirAjax.do?id="+$('#idParcela'+j).val()
        +"&imprime=" + false
        +"&status=" + 2;
        $.post(val, $(this).serialize(), function(g){});
    }
    $("#msgImprimirEntradaSaida").hide();
    $("#msgSelecioneParcela").hide();
}

var tempoUltimoPlano = 5;
function contaUltimoPlano() {
    if(tempoUltimoPlano == 0) {
        atualizar();
        tempoUltimoPlano = 5;
    }
    if (tempoUltimoPlano != 0){
        tempoUltimoPlano = tempoUltimoPlano-1;
        setTimeout("contaUltimoPlano()", 500);
    }
}
contaUltimoPlano();

function atualizar(){
    var status = getStatus();
    if(status !=3){
        var size = $('#pagamentoSize').val();
        for(var i = 1;i <= size;i++){
            if($("#idParcela"+i).val()!=null){
                var id = $("#idParcela"+i).val();
                confere(id);
            }
        }
    }
    refreshStatus();
}
function confere(id){
    if ($('#pagamentoChek'+id).attr('checked')) {
        $.ajax({
            url: "readStatusImprimirPagamento.do",
            type: "POST",
            data: {
                "id":id
            },
            success: function(d){
                var aux = d.toString();
                if(aux.substring(18, 22) == "true"){
                }else if(aux.substring(18, 23) == "false"){
                    var idAux = aux.substring(29, aux.lenght)
                    $('#pagamentoChek'+idAux).attr('checked', false);
                }
            }
        });
    }
}