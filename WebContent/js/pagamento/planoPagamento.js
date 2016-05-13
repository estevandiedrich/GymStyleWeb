$(document).ready(function(){
    eventoAjustarParcelas();
    $("#justificativa").css("maxWidth","500px" );
    $("#justificativa").css("maxHeight","80px" );
    $("#justificativa").css("minWidth","500px" );
    $("#justificativa").css("minHeight","80px" );

    $(".inputNumber").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    })
    
    $('.inputNumber').bind('keydown',function soNums(e){
        var keyCode = e.which; 
        if(keyCode != 53){//Nao permitir digitar %
            return true;
        }
        return false;
    }); 
        
    showConta();
    atualizar();
    eventoTodosPagamentos();
    if($("#fechado").val()=="CaixaFechado"){
        $('.inputNumber').attr('disabled',true);
        $('.inputNumber').css('color',"#888");
        $('#formaDePagamento').attr('disabled',true);
        $(':checkbox').attr('disabled',true);
        $(':radio').attr('disabled',true);
        $('#justificativa').attr('disabled',true);
    }
    
    contaUltimoPlano();
});

var tempo = 5;
function contaUltimoPlano() {
    if(tempo == 0) {
        atualizar();
        tempo = 5;
    }
    if (tempo != 0){
        tempo = tempo-1;
        setTimeout("contaUltimoPlano()", 500);
    }
}

function liberarInputs(){
    $('.inputDisabledNumber').removeClass().addClass('inputNumber');
    $('.inputNumber').attr('disabled',false);
    $('.inputNumber').css('color',"#333");
    $('#formaDePagamento').attr('disabled',false);
    $(':checkbox').attr('disabled',false);
    $(':radio').attr('disabled',false);
    $('#justificativa').attr('disabled',false);
}

function atualizar(){
    //Se o botao nao imprimir estiver selecionado, nao conferir no banco
    if (!$('#imprime3').attr('checked')) {
        var size = $('#pagamentoSize').val();
        for(var i = 1;i <= size;i++){
            if($("#idParcela"+i).val()!=null){
                var id = $("#idParcela"+i).val();
                if (!$('#parcela'+i).attr('checked')) {
                    confere(id);
                }
            }
        }
    }
}

function confere(id){
    if ($('#pagamento'+id).attr('checked')) {
        $.ajax({
            url: "readStatusImprimirPagamento.do",
            type: "POST",
            data: {
                "id":id
            },
            success: function(d){
                var aux = d.toString();
                if(aux.substring(18, 22) == "true"){
                //Mantem
                }else if(aux.substring(18, 23) == "false"){
                    //Se for false
                    var idAux = aux.substring(29, aux.lenght)
                    $('#pagamento'+idAux).attr('checked', false);
                }
            }
        });
    }
}

function eventoAjustarParcelas(posicao){
    var duracao = $("#duracao").val();
    if(posicao!=undefined){
        if ($('#parcela'+posicao).attr('checked')) {
        }else{
            $('#todos').attr('checked', false);
            posicao--;
        }
        if(duracao != "Mensal"){
            for(var i = 1;i<=posicao;i++){
                if($('#parcela'+i).val()!= undefined){
                    $('#parcela'+i).attr('checked', true);
                    mostrarImprimir($('#idParcela'+i).val(), i);
                }                
            }
        }
    }
    var tam = $("#pagamentoSize").val();
    if(duracao != "Mensal"){
        for(i = (posicao+1);i <= tam;i++){
            if($('#parcela' + i).val()!= undefined){
                $('#parcela' + i).attr('checked', false);
                mostrarImprimir($('#idParcela'+i).val(), i);
            }
        }
    }
    var todos = true;
    for(i = 1; i <= tam;i++){            
        if($('#parcela' + i).val()!= undefined){
            if (!$('#parcela' + i).attr('checked')) {
                todos = false;
                break;
            }
        }
    }
    if(todos){
        $('#todos').attr('checked', true);
    }else {
        $('#todos').attr('checked', false);
    }

    var valor = 0;
    for (i = 1;i <= tam;i++){
        if ($('#parcela'+i).attr('checked')) {
            valor += parseFloat($("#valorParcela"+i).val());
        }    
    }    
    $("#valorSoma").val(valor);
    $("#valorSomado").html(float2moeda(valor));
    var desconto = getValor($("#desconto").val());
    var multa = getValor($("#multa").val());
    var valorApagar = (valor + multa - desconto);
    if(valorApagar < 0){
        valorApagar = 0;
    }
    $("#valorAPagar").val(float2moeda(valorApagar));
    
    imprimirPagamento();
    $('#chekErrors').hide(1000);
}

function valida(){
    var chek = false;
    var tam = $("#pagamentoSize").val();
    for(var i = 1;i <= tam;i++){
        if($('#parcela' + i).val()!= undefined && $('#parcela'+i).attr('checked')) {
            chek =true;
            break;
        }
    }
    
    var desconto = $("#desconto").val();
    var multa = $("#multa").val();
    var justificativa = $("#justificativa").val();
    
    if(!chek){
        $("#chekErrors").show(500);
    }else{
        $('#chekErrors').hide(1000);
    }
    
    var chekJus = true;
    if((desconto != "" && desconto != "0,00") && justificativa==""){
        chekJus = false;
    }
    if((multa != "" && multa != "0,00") &&   justificativa==""){
        chekJus = false;
    }
    if(!chekJus){
        $("#chekJustificativaErrors").show(500);
    }else{
        $('#chekJustificativaErrors').hide(1000);
    }
//    alert("Chek - "+(chek));
//    alert("Just - "+(chekJus));
//    alert(((chek&&chekJus)?true:false));
    return ((chek&&chekJus)?true:false);
}

function eventoSubmitForm(){
    document.formPagarParcelas.submit();
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
        
        liberarInputs();
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

function showConta(){
    var conta = $('#formaDePagamento').val();
    if(conta==2 || conta == 5){
        $('.rowConta').show(500);
    }else{
        $('.rowConta').hide(500);
    }
}

function showConfirmPag(){
    if(valida()){
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
        $("#statusConfirmPag").css('left', (winW-350) / 2);//Tem q ser a largura da tela menos a largura dividido pordois
        
        //efeito de transição
        $(id).fadeIn(2000);
    }
}
    
function closeShowConfirmPag(status){
    $('#windowConfirmPag').hide(1000);
    if(status){
    //        $("#abrirCaixaMsg").hide(500);
    //        $(".msgCaixaFechado").hide(500);
    //        $("#efetuarPagamento").show(500);
    //        
    //        liberarInputs();
    }
}

function mostraMsg(id){
    if(id==3 || !parcelaHaImprimir()){
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