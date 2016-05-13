
$(document).ready(function(){
    $("#criterioCpf").mask("999.999.999-99");
    
    $("#criterioMatricula").keydown(function(event){
        /* Allow backspace, delete, tab, esc e enter */
        if ( event.keyCode == 46 || event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 27 || event.keyCode == 13 || 
            /* Allow CTRL+A */
            (event.keyCode == 65 && event.ctrlKey === true) || 
            /* Allow CTRL+C */
            (event.keyCode == 67 && event.ctrlKey === true) || 
            /* Allow CTRL+X */
            (event.keyCode == 88 && event.ctrlKey === true) || 
            /* Allow CTRL+V */
            (event.keyCode == 86 && event.ctrlKey === true) || 
            /* Allow Command+A (Mac) */
            (event.keyCode == 65 && event.metaKey === true) || 
            /* Allow Command+C (Mac) */
            (event.keyCode == 67 && event.metaKey === true) || 
            /* Allow Command+X (Mac) */
            (event.keyCode == 88 && event.metaKey === true) || 
            /* Allow Command+V (Mac) */
            (event.keyCode == 86 && event.metaKey === true) || /* Allow home, end, left e right keys */
            (event.keyCode >= 35 && event.keyCode <= 39)) {
    			
            /* Boo */
            return;
        }
        else {
            /* Stop key press */
            if (event.shiftKey || (event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105 )) {
                event.preventDefault(); 
            }   
        }
    });
});

function submitFormFiltrar(p){
    if(p!=""){
        $("#p").val(p);
    }
    submitFormRead();
}

var imprimir = false;
function consultaNome(){
    if(!imprimir){
        imprimir = true;
        setTimeout(function(){
            submitFormRead();
            imprimir = false;
        }, 1200);
    }
}

function down(){
    imprimir = false;
}
    
function submitFormRead(){
    $("#processando").show(700);
    var criterioNome= $("#criterioNome").val();
    var criterioCpf= $("#criterioCpf").val();
    var criterioMatricula= $("#criterioMatricula").val();
    var criterioDebitoDia= $("#criterioDebitoDia").val();

    var p= $("#p").val();
    var orderBy= false;
    if(document.getElementById("orderBy").checked){
        orderBy = true;
    }
    if(p == undefined){
        p = 1;                
    }
    $.ajax({
        url: "pagamentoRead.do",
        type: "POST",
        data: {
            "criterioNome":criterioNome,
            "criterioCpf":criterioCpf,
            "criterioMatricula":criterioMatricula,
            "criterioDebitoDia":criterioDebitoDia,
            //"criterioUsuarioAtivo":true,
            "pag.p":p,
            "orderBy":orderBy,
            "pag.currentPage":p
        },
        success: function(d){
            $("#content").html(d);
        }
    });
}

function showAbrirCaixa(){
    carregarAbrirCaixa();
    var idd = $(this).attr('href');
    idd = ('#windowAbrirCaixa');

    //armazena a largura e a altura da tela
    var maskHeight = $(document).height();
        
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
    $(idd).css('top',  winH/2-$(idd).height()/2);
    $(idd).css('left', winW/2-$(idd).width()/2);

    $("#statusAbrirCaixa").css('left', (winW-700) / 2);
        
    //efeito de transição
    $(idd).fadeIn(2000);
}
    
function closeShowAbrirCaixa(status){
    $('#windowAbrirCaixa').hide(1000);
    if(status){
        $("#abrirCaixaMsg").hide(500);
        $(".msgCaixaFechado").hide(500);
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
