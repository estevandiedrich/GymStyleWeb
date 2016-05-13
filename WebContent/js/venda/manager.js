$(document).ready(function(){
    $('#cartaoProximidade').keypress(function(event) {
        var tecla = (window.event) ? event.keyCode : event.which;
        if ((tecla > 47 && tecla < 58)) return true;
        else {
            if (tecla != 8) return false;
            else return true;
        }
    });
    
    $(".opcao").hide(1000);
    $(".middle_frame").height(80);
    $("#labelAluno").click(function(e){
        e.preventDefault();
        if($("#labelAluno").hasClass("mais")) {
            $("#divAluno").show(700);
            $('#labelAluno').removeClass('mais').addClass('menos');
        }else {
            $("#divAluno").hide(700);
            $('#labelAluno').removeClass('menos').addClass('mais');
        }
    });

    $("#consultaUsuario").click(function(e){
        e.preventDefault();
        showConsultaUsuario();
    });
    $("#limparUsuario").click(function(e){
        e.preventDefault();
        $("#usuarioId").val('');
        $("#usuarioNome").val('Clique para consultar...');
    });

    $("#consultaProduto").click(function(e){
        e.preventDefault();
        showConsultaProduto();
    });
    $("#limparUsuario").click(function(e){
        e.preventDefault();
        $("#usuarioId").val('');
        $("#usuarioNome").val('Clique para consultar...');
    });
    
    $('#codigoProduto').keypress(function(event) {
        var tecla = (window.event) ? event.keyCode : event.which;
        if ((tecla > 47 && tecla < 58)) return true;
        else {
            if (tecla != 8) return false;
            else return true;
        }
    });
    $('#codigoProduto').keyup(function(e){
        e.preventDefault();
        //Implementar consulta por aluno
//        $.get("produtoAjaxRead.do", function(output) {
//            alert(output);
//        });
    });
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
    //$(id).css('top', winH/2-$(id).height()/2);
    //    $(id).css('top', winH/2-$(id).height()/2);
    //    $(id).css('left', winW/2-$(id).width()/2);
    //    $("#statusFecharCaixa").css('left', (winW-700) / 2);

    $(id).css('top', 0);
    $(id).css('left',0);
    $("#"+ status).css('top', -200);
    $("#"+ status).css('left', (winW-700) / 2);
    
    //efeito de transição
    $(id).fadeIn(2000);
}