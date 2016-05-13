function consultaUsuarios(){
    var criterioNome = $("#criterioNome").val();
    $.ajax({
        url: "usuarioAjaxRead.do",
        type: "POST",
        data: {
            "criterioNome":criterioNome
        },
        success: function(d){
            $("#usuarioAjax").html(d);
        }
    });
}

function selecionarUsuario(id,nome){
    $("#usuarioId").val(id);
    $("#usuarioNome").val(nome);
    closeShowConsultaUsuario();
}

    
function showConsultaUsuario(){
    var id = $(this).attr('href');
    id = ('#windowConsultaUsuario');

    //armazena a largura e a altura da tela
    var maskHeight = $(document).height();
    var maskWidth = $(window).width();

    //Define largura e altura do div#mask iguais ás dimensões da tela
    $('#windowConsultaUsuario').css({
        'width':maskWidth,
        'height':maskHeight
    });

    //efeito de transição
    $('#windowConsultaUsuario').fadeIn(1000);
    //$('#mask').fadeTo("slow",0.8);

    //armazena a largura e a altura da janela
    var winH = $(window).height();
    var winW = $(window).width();

    //centraliza na tela a janela popup
    //$(id).css('top', winH/2-$(id).height()/2);
    $(id).css('top', winH/2-$(id).height()/2);
    $(id).css('left', winW/2-$(id).width()/2);

    $("#statusConsultaUsuario").css('left', (winW-700) / 2);
    //efeito de transição
    $(id).fadeIn(2000);
}

function closeShowConsultaUsuario(){
    $('#windowConsultaUsuario').hide(1000);
}