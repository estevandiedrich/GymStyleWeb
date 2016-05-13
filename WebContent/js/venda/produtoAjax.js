function consultaProdutos(){
    var criterioNomeProduto = $("#criterioNomeProduto").val();
    var criterioCodigo = $("#criterioCodigo").val();
    $.ajax({
        url: "produtoAjaxRead.do",
        type: "POST",
        data: {
            "criterioNome":criterioNomeProduto,
            "criterioCodigo":criterioCodigo
        },
        success: function(d){
            $("#produtosAjax").html(d);
        }
    });
}

function selecionarProduto(id,codigo,nome){
    $("#idProduto").val(id);
    $("#codigoProduto").val(codigo);
    $("#produto").val(nome);
    closeShowConsultaProduto();
}

function showConsultaProduto(){
    var id = $(this).attr('href');
    id = ('#windowConsultaProduto');

    //armazena a largura e a altura da tela
    var maskHeight = $(document).height();
        
    var maskWidth = $(window).width();

    //Define largura e altura do div#mask iguais ás dimensões da tela
    $('#windowConsultaProduto').css({
        'width':maskWidth,
        'height':maskHeight
    });

    //efeito de transição
    $('#windowConsultaProduto').fadeIn(1000);
    //$('#mask').fadeTo("slow",0.8);

    //armazena a largura e a altura da janela
    var winH = $(window).height();
    var winW = $(window).width();

    //centraliza na tela a janela popup
    //$(id).css('top', winH/2-$(id).height()/2);
    $(id).css('top', winH/2-$(id).height()/2);
    $(id).css('left', winW/2-$(id).width()/2);

    $("#statusConsultaProduto").css('left', (winW-700) / 2);
    //efeito de transição
    $(id).fadeIn(2000);
}
    
function closeShowConsultaProduto(){
    $('#windowConsultaProduto').hide(1000);
}

$(document).ready(function(){
    $('#criterioCodigo').bind('keydown',soNums); // o "#input" é o input que vc quer aplicar a funcionalidade
});
 
function soNums(e){
 
    //teclas adicionais permitidas (tab,delete,backspace,setas direita e esquerda)
    keyCodesPermitidos = new Array(8,9,37,39,46);
     
    //numeros e 0 a 9 do teclado alfanumerico
    for(x=48;x<=57;x++){
        keyCodesPermitidos.push(x);
    }
     
    //numeros e 0 a 9 do teclado numerico
    for(x=96;x<=105;x++){
        keyCodesPermitidos.push(x);
    }
     
    //Pega a tecla digitada
    keyCode = e.which; 
     
    //Verifica se a tecla digitada é permitida
    if ($.inArray(keyCode,keyCodesPermitidos) != -1){
        return true;
    }
    return false;
}