$(document).ready(function(){
    $(".opcao").hide(1000);
    $(".middle_frame").height(80);
        
    var varLocal = 1;
    $("#inicial").click(function(event){
        event.preventDefault();
        if(varLocal == 1){
            varLocal = 2;
            $("#detalhesValorInicial").show(700);
            $('#inicial').removeClass('mais').addClass('menos');
        }else {
            varLocal = 1;
            $("#detalhesValorInicial").hide(700);
            $('#inicial').removeClass('menos').addClass('mais');
        }
    });
    
    $("#eventoMovimentacao").click(function(event){
        event.preventDefault();
        $("#divHistorico").hide();
        $("#divMovimentacao").show();
        $('#eventoMovimentacao').removeClass('aba').addClass('abaCurrent');
        $('#eventoHistorico').removeClass('abaCurrent').addClass('aba');
    });
    
    $("#eventoHistorico").click(function(event){
        event.preventDefault();
        $("#divMovimentacao").hide();
        $("#divHistorico").show();
        $('#eventoHistorico').removeClass('aba').addClass('abaCurrent');
        $('#eventoMovimentacao').removeClass('abaCurrent').addClass('aba');
    });
    
    abaSelect = $("#abaSelect").val();
    if(abaSelect=="eventoMovimentacao"){
        $("#divHistorico").hide();
        $("#divMovimentacao").show();
        $('#eventoMovimentacao').removeClass('aba').addClass('abaCurrent');
        $('#eventoHistorico').removeClass('abaCurrent').addClass('aba');
    }else if(abaSelect=="eventoHistorico"){
        $("#divHistorico").hide();
        $("#divMovimentacao").show();
        $('#eventoHistorico').removeClass('aba').addClass('abaCurrent');
        $('#eventoMovimentacao').removeClass('abaCurrent').addClass('aba');
    }
});
    
function esconde(num){
    if(num == 0){
        $(".opcao").show(1000);
        $(".middle_frame").height(110);
    }else{
        $(".opcao").hide(800);
        $(".middle_frame").height(80);
    }
}
    
function showRetirada(){
    showDialog("windowRetirada","statusRetirada");
}
    
function closeShowRetirada(){
    $('#windowRetirada').hide(1000);
    $("#idRegistroRetirada").val("");
    $("#valorRetiradaCaixa").val("0,00");
    $("#descricaoRetirada").val("SAIDA CAIXA");
    $("#formaPagamentoRetirada option[value='"+1+"']").attr('selected',true);
        
    $("#valorRetiradaError").hide();
    $("#descricaoRetiradaError").hide();
    $("#formaPagamentoRetiradaError").hide();
    hideContaRetirada();
}    
    
function showEditar(entrada,id,valor,idForma,descricao,idContaBancaria){
    if(entrada){
        $("#idRegistroEntrada").val(id);
        $("#valorEntradaCaixa").val(float2moeda(valor));
        $("#descricaoEntrada").val(descricao);
        $("#formaPagamentoEntrada option[value='"+idForma+"']").attr('selected',true);
        if(idContaBancaria == 0){}
        if(idContaBancaria > 0){
            $("#contaEntrada option[value='"+idContaBancaria+"']").attr('selected',true);
            showContaEntrada();
        }
        showEntrada();
    }else{
        $("#idRegistroRetirada").val(id);
        if(idForma==1){
            $("#valorDinheiroRetirada").val(valor);
        }else
        if(idForma==5){
            $("#valorChequeRetirada").val(valor);
        }
        $("#valorRetiradaCaixa").val(float2moeda(valor));
        $("#descricaoRetirada").val(descricao);
        $("#formaPagamentoRetirada option[value='"+idForma+"']").attr('selected',true);
        if(idContaBancaria == 0){}
        if(idContaBancaria > 0){
            $("#contaRetirada option[value='"+idContaBancaria+"']").attr('selected',true);
            showContaRetirada();
        }
        showRetirada();
    }
}

function msgCaixaFechadoShow(){
    $('.msgCaixaFechado').hide().slideDown(800);
}

function showEntrada(){
    showDialog("windowEntrada","statusEntrada");
}
    
function closeShowEntrada(){
    $('#windowEntrada').hide(1000);
    $("#idRegistroEntrada").val("");
    $("#valorEntradaCaixa").val("0,00");
    $("#descricaoEntrada").val("ENTRADA CAIXA");
    $("#formaPagamentoEntrada option[value='"+1+"']").attr('selected',true);
        
    $("#valorError").hide();
    $("#descricaoError").hide();
    $("#formaPagamentoError").hide();   
    hideContaEntrada();
}    
    
function showAbrirCaixa(){
    showDialog("windowAbrirCaixa","statusAbrirCaixa");
}
    
function closeShowAbrirCaixa(status){
    $('#windowAbrirCaixa').hide(1000);
    window.location = "managerFluxoCaixa.do";
}    
    
function showFecharCaixa(){
    showDialog("windowFecharCaixa","statusFecharCaixa");
}
    
function closeShowFecharCaixa(){
    $('#windowFecharCaixa').hide(1000);
}    

function showDialog(win,status){
    var id = $(this).attr('href');
    id = ('#'+win);

    //armazena a largura e a altura da tela
    var maskHeight = $(document).height();
        
    var maskWidth = $(window).width();

    //Define largura e altura do div#mask iguais ás dimensões da tela
    $('#'+win).css({
        'width':maskWidth,
        'height':maskHeight
    });

    //efeito de transição
    $('#'+win).fadeIn(1000);
    //$('#mask').fadeTo("slow",0.8);

    //armazena a largura e a altura da janela
    var winH = $(window).height();
    var winW = $(window).width();

    //centraliza na tela a janela popup
    //$(id).css('top',  winH/2-$(id).height()/2);
    //    $(id).css('top',  winH/2-$(id).height()/2);
    //    $(id).css('left', winW/2-$(id).width()/2);
    //    $("#statusFecharCaixa").css('left', (winW-700) / 2);

    $(id).css('top', 0);
    $(id).css('left',0);
    $("#"+ status).css('top', -200);
    $("#"+ status).css('left', (winW-700) / 2);
    
    //efeito de transição
    $(id).fadeIn(2000);
}