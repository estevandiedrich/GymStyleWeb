
var TEMP = 20;

function reenviarRequisicao(id){
    var img = "<table style='width: 100%'><tr><td align='center'>"
    +"<img src='images/carregandoPeq.gif' title='Sincronizando...'/></td></tr></table>";
    $("#status"+id).hide();
    $("#status"+id).html(img);
    $("#status"+id).show(700);
        
    var val = "requisicaoReenviar.do?idRequisicao="+id;
    $.post(val, $(this).serialize(), function(g){
        $("#status"+id).hide();
        $("#status"+id).html(g);
        $("#status"+id).show(700);
    });
}

function requisicaoManagerUsuario(){
    var contador = $("#contador").val();
    if(contador == null){
        contador = 1;
    }
    $.ajax({
        url: "requisicaoManagerExcluir.do",
        type: "POST",
        data: {
            "contador":contador,
            "idRequisicao":$("#idRequisicao").val(),
            "idPlanoUsuario":$("#idPlanoUsuario").val(),
            "idUsuario":$("#idUsuario").val()
        },
        success: function(d){
            $("#result").html(d);
            $("#result").show(700);
            refreshDestinos();
        }
    });
}

function refreshDestinos(){
    var contador = getValor($("#contador").val());
    if(contador == null){
        contador = 1;
    }else{
        contador++;
    }
    
    if(contador==(getValor($("#tempoFinal").val()+1))){
        if($("#listaVazia").val()!= undefined){
            var img = "<table style='width: 100%'><tr><td align='center' style='font-size: 12px;font-style: italic'>"
            + "<img src='images/sinc_error.png' /> <label>Erro ao encontrar destinos de acesso. \n\</br>Verifique se a catraca está atrelada ao cadastro de modalidade!</label></td></tr></table>";
            $("#listaVazia").hide();
            $("#listaVazia").html(img);
            $("#listaVazia").show(700);        
        }
    }else {
        $.ajax({
            url: "requisicaoManagerExcluir.do",
            type: "POST",
            data: {
                "contador":contador,
                "idRequisicao":$("#idRequisicao").val(),
                "idUsuario":$("#idUsuario").val(),
                "idPlanoUsuario":$("#idPlanoUsuario").val()
            },
            success: function(d){
                $("#result").html(d);
                $("#result").show(700);
                var valorContador = getValor($("#contador").val());
                var tempoFinal = getValor($("#tempoFinal").val());
                //alert(tempoFinal);
                if(isNaN(tempoFinal)){
                    tempoFinal = TEMP;
                }
                //            if(valorContador<CONT){
                if(valorContador<tempoFinal){
                    refreshDestinos();
                }else{
                    $("#carregando").hide(700);
                    $("#carregando").html("");
                
                    if(contador==41){
                        if($("#listaVazia").val()!= undefined){
                            var img = "<table style='width: 100%'><tr><td align='center' style='font-size: 12px;font-style: italic'>"
                            + "<img src='images/sinc_error.png' /> <label>Erro ao encontrar destinos de acesso. \n\</br>Verifique o plano do aluno ou acesso do funcionário!</label></td></tr></table>";
                            $("#listaVazia").hide();
                            $("#listaVazia").html(img);
                            $("#listaVazia").show(700);        
                        }
                    }
                }
            }
        });
    }
}
$(document).ready(function(){
    requisicaoManagerUsuario();
});