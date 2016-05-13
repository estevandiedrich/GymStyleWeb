var CONT = 70;//Tempo default para um destino. Na Action será calculado os destinos retornando o tempo final
var continua = true;
var atualizaSoPendentes = false;
function requisicaoAtualizarUsuario(linha){
    var clas = "";
    var idUsuario = $("#aluno"+linha).val();
    var contador = $("#contador"+linha).val();
    //alert(contador);
    if(contador==1){
        clas = $("#col1lin"+linha).attr("class");
        $("#col1lin"+linha).attr("class","sub3");
        $("#col2lin"+linha).attr("class","sub3");
        $("#col3lin"+linha).attr("class","sub3");
        $("#col4lin"+linha).attr("class","sub3");
    }
    var tempo = $("#tempo"+linha).val();
    tempo = parseInt(tempo);
    if(isNaN(tempo)){
        tempo = CONT;
    }
    var idRequisicao = $("#idRequisicao"+linha).val();
    var idPlanoUsuario = $("#idPlanoUsuario"+linha).val();
    if(contador == null || contador >= tempo){
        contador = -1;
        idRequisicao = -1;
        idPlanoUsuario = -1;            
    }
    if(idRequisicao == undefined){
        idRequisicao = -1;
    }
    if(idPlanoUsuario == undefined){
        idPlanoUsuario = -1;
    }

    var val = "atualizarUsuario.do?contador="+contador
    +"&id="+idUsuario
    +"&linha="+linha
    +"&tempo="+tempo
    +"&idRequisicao="+idRequisicao
    +"&idUsuario="+idUsuario
    +"&idPlanoUsuario="+idPlanoUsuario;
    $.post(val, $(this).serialize(), function(r){
        //alert(r);
        $("#result"+linha).html(r);
        $("#result"+linha).show(700);
        contador = $("#contador"+linha).val();    
        tempo = $("#tempo"+linha).val();
        tempo = parseInt(tempo);
        if(contador < tempo){
            if(continua){
                setTimeout("requisicaoAtualizarUsuario("+linha+")", 250);
            //conferir para diminuir esse tempo no ajax e aumentar o tempo de espera
            }else{
                $("#refresh"+linha).attr("src","images/sinc_error.png");
                $("#refresh"+linha).attr("title","Não Sincronizado! OK");
                $("#refresh"+linha).show(100);
                $("#result"+linha).hide(100);
                $("#col1lin"+linha).attr("class",clas);
                $("#col2lin"+linha).attr("class",clas);
                $("#col3lin"+linha).attr("class",clas);
                $("#col4lin"+linha).attr("class",clas);
            }
        }else{          
            $("#col1lin"+linha).attr("class",clas);
            $("#col2lin"+linha).attr("class",clas);
            $("#col3lin"+linha).attr("class",clas);
            $("#col4lin"+linha).attr("class",clas);
            linha++;
            if(atualizaSoPendentes){
                tam =  $("#tamanho").val();
                while(linha < tam){
                    var sincronizado = $("#sincronizado"+linha).val();
                    if(sincronizado == 0){
                        break;
                    }
                    linha++;
                }
            }
            $("#refresh"+linha).hide(100);
            var tam =  $("#tamanho").val();
            if(linha < tam){
                requisicaoAtualizarUsuario(linha);
            }else{
                habilita();
            }
        }
    });
}
    
function enviarRequisicoes(){
    continua = true;
    atualizaSoPendentes = false;
    var tam =  $("#tamanho").val();
    var i = 0;
    $("#refresh"+i).hide(100);
    $("#result"+i).show(100);
    requisicaoAtualizarUsuario(0);
}

function enviarRequisicoesPendentes(){
    continua = true;
    atualizaSoPendentes = true;
    var tam =  $("#tamanho").val();
    var i = 0;
    while(i < tam){
        var sincronizado = $("#sincronizado"+i).val();
        if(sincronizado == 0){
            break;
        }
        i++;
    }
    if(i < tam){
        $("#refresh"+i).hide(100);
        $("#result"+i).show(100);
        requisicaoAtualizarUsuario(i);
    }else{
        habilita();
    }
}

function paraAtualizacoes(){
    continua = false;
}

function desabilita(){
    $('#todos').attr('disabled', 'disabled');
    $('#pendentes').attr('disabled', 'disabled');
    $("#todos").removeClass('botao').addClass('botaoDes');
    $("#pendentes").removeClass('botao').addClass('botaoDes');
    $('#parar').removeAttr('disabled');
    $("#parar").removeClass('botaoDes').addClass('botao');

    $('#todos2').attr('disabled', 'disabled');
    $("#todos2").removeClass('botao').addClass('botaoDes');
    $('#pendentes2').attr('disabled', 'disabled');
    $("#pendentes2").removeClass('botao').addClass('botaoDes');
    $('#parar2').removeAttr('disabled');
    $("#parar2").removeClass('botaoDes').addClass('botao');
}
function habilita(){
    $('#todos').removeAttr('disabled');
    $("#todos").removeClass('botaoDes').addClass('botao');
    $('#pendentes').removeAttr('disabled');
    $("#pendentes").removeClass('botaoDes').addClass('botao');
    $('#parar').attr('disabled', 'disabled');
    $("#parar").removeClass('botao').addClass('botaoDes');

    $('#todos2').removeAttr('disabled');
    $("#todos2").removeClass('botaoDes').addClass('botao');
    $('#pendentes2').removeAttr('disabled');
    $("#pendentes2").removeClass('botaoDes').addClass('botao');
    $('#parar2').attr('disabled', 'disabled');
    $("#parar2").removeClass('botao').addClass('botaoDes');
}