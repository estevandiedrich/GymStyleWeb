
function imprimirBoleto(){
    document.formPagarParcela.action = "boletoReport.do";
    document.formPagarParcela.target='_blank';
    document.formPagarParcela.submit();
    document.formPagarParcela.target='';
    document.formPagarParcela.action = "pagarParcelaPlanoUsuario.do";
}

function eventoAjustarParcelas(){
    var valor = 0;
    valor = parseFloat($("#valorParcela").val());
    var desconto = getValor($("#desconto").val());
    var multa = getValor($("#multa").val());
    var valorApagar = (valor + multa - desconto);
    if(valorApagar<0){
        valorApagar = 0;
    }
    $("#valorAPagar").val(float2moeda(valorApagar));
}

function eventoSubmitForm(){
    var desconto = getValor($("#desconto").val());
    var multa = getValor($("#multa").val());
    var justificativa = $("#justificativa").val();
    if((desconto != 0 || multa !=0) && justificativa == ""){
        if(document.getElementById("idParcela").value==""){
            document.formPagarParcela.action="pagarNovaParcela.do";
        }
    }
    //var texto ="Valor á pagar de "+$("#valorAPagar").val() +". Deseja efetuar o pagamento?"; 
    //if(confirm(texto)){
    if(document.getElementById("idParcela").value==""){
        document.formPagarParcela.action="pagarNovaParcela.do";                
    }
    document.formPagarParcela.submit();
//}
    
}

$(document).ready(function(){
    $("#justificativa").css("maxWidth","500px" );
    $("#justificativa").css("maxHeight","100px" );
    $("#justificativa").css("minWidth","500px" );
    $("#justificativa").css("minHeight","100px" );
    
    $(".inputNumber").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
    
    $('.inputNumber').bind('keydown',function soNums(e){
        var keyCode = e.which; 
        if(keyCode != 53){//Nao permitir digitar %
            return true;
        }
        return false;
    }); 
    
    $("#desconto").val(float2moeda($("#desconto").val()));
    $("#multa").val(float2moeda($("#multa").val()));
    $("#valorAPagar").val(float2moeda($("#valorAPagar").val()));
    eventoAjustarParcelas();

    //$('.rowConta').hide(1000);
    showConta();
    if($("#fechado").val()=="CaixaFechado"){
        $('.inputNumber').attr('disabled',true);
        $('.inputNumber').css('color',"#888");
        $('#formaDePagamento').attr('disabled',true);
        $(':checkbox').attr('disabled',true);
        $(':radio').attr('disabled',true);
        $('#justificativa').attr('disabled',true);
    }
});

function liberarInputs(){
    $('#formaDePagamento').attr('disabled',false);
    $(':checkbox').attr('disabled',false);
    $(':radio').attr('disabled',false);
    $('#justificativa').attr('disabled',false);

    $('.inputNumber').attr('disabled',false);
    $('.inputNumber').css('color',"#333");
}

function showAbrirCaixa(){
    carregarAbrirCaixa();

    var id = $(this).attr('href');
    id = ('#windowAbrirCaixa');

    //armazena a largura e a altura da tela
    var maskHeight = $(document).height();
    //alert(maskHeight);
        
    var maskWidth = $(window).width();

    //Define largura e altura do div#mask iguais ás dimensões da tela
    $('#windowAbrirCaixa').css({
        'width':maskWidth,
        'height':maskHeight
    });

    //efeito de transição
    $('#windowAbrirCaixa').fadeIn(1000);
    //$('#mask').fadeTo("slow",0.8);

    //armazena a largura e a altura da janela
    var winH = $(window).height();
    var winW = $(window).width();

    //centraliza na tela a janela popup
    //$(id).css('top',  winH/2-$(id).height()/2);
    //$(id).css('top',  winH/2-$(id).height()/2);
    //    $(id).css('left', winW/2-$(id).width()/2);

    $(id).css('top', 0);
    $(id).css('left',0);

    $("#statusAbrirCaixa").css('top', -200);
    $("#statusAbrirCaixa").css('left', (winW-700) / 2);
        
    //efeito de transição
    $(id).fadeIn(2000);
}
    
function closeShowAbrirCaixa(status){
    $('#windowAbrirCaixa').hide(1000);
    if(status){
        $("#abrirCaixaMsg").hide(500);
        $(".msgCaixaFechado").hide(500);
        $("#efetuarPagamento").show(500);
    }
    liberarInputs();
}

function showConta(){
    var conta = $('#formaDePagamento').val();
    if(conta==2 || conta == 5){
        $('.rowConta').show(500);
    }else{
        $('.rowConta').hide(500);
    }
}

function carregarAbrirCaixa(){
    $.ajax({
        url: "carregarAbrirFluxoCaixa.do",
        type: "POST",
        data: {},
        success: function(e){
            $("#statusAbrirCaixa").html(e);
        }
    });
}

function submitPagamento(){
    var desconto = getValor($("#desconto").val());
    var multa = getValor($("#multa").val());
    var justificativa = $("#justificativa").val();
    $("#chekJustificativaErrors").hide(600);
    if((desconto != 0 || multa !=0) && justificativa == ""){
        $("#chekJustificativaErrors").slideDown(700);
    }else{
        showConfirmPag();
    }
}

function showConfirmPag(){
    $("#valorConfirm").html('R$ '+$("#valorAPagar").val());
    var id = $(this).attr('href');
    id = ('#windowConfirmPag');

    //armazena a largura e a altura da tela
    var maskHeight = $(document).height();
    //alert(maskHeight);
        
    var maskWidth = $(window).width();

    //Define largura e altura do div#mask iguais ás dimensões da tela
    $('#windowConfirmPag').css({
        'width':maskWidth,
        'height':maskHeight
    });

    //efeito de transição
    $('#windowConfirmPag').fadeIn(1000);
    //$('#mask').fadeTo("slow",0.8);

    //armazena a largura e a altura da janela
    var winH = $(window).height();
    var winW = $(window).width();

    //centraliza na tela a janela popup
    //$(id).css('top',  winH/2-$(id).height()/2);
    //    $(id).css('left', winW/2-$(id).width()/2);

    $(id).css('top', 0);
    $(id).css('left',0);

    $("#statusConfirmPag").css('top', -100);
    $("#statusConfirmPag").css('left', (winW-400) / 2);//Tem q ser a largura da tela menos a largura dividido pordois
        
    //efeito de transição
    $(id).fadeIn(2000);
}
    
function closeShowConfirmPag(status){
    $('#windowConfirmPag').hide(1000);
//if(status){
//        $("#abrirCaixaMsg").hide(500);
//        $(".msgCaixaFechado").hide(500);
//        $("#efetuarPagamento").show(500);
//        
//        liberarInputs();
//}
}

function mostraMsg(id){
    if(id==3){
        $("#msgStatusImprimir").html("O comprovante não será impresso na catraca!");
        $("#msgStatusImprimir").removeClass().addClass('errors');
    }else
    if(id==1){
        $("#msgStatusImprimir").html("O comprovante será impresso na catraca <br> durante a entrada do aluno!");
        $("#msgStatusImprimir").removeClass().addClass('msgCaixaAberto');
    }else
    if(id==2){
        $("#msgStatusImprimir").html("O comprovante será impresso na catraca<br> durante a saída do aluno!");
        $("#msgStatusImprimir").removeClass().addClass('msgCaixaAberto');
    }
}
