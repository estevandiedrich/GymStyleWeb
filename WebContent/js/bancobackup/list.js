function mostraStatus(){
        $('#status').show(700);
        $('#status2').html("<label class='obrig'>Gerando Backup</label> - Aguarde . . .");
        showDialog();
    }
    function mostraStatusRestore(){
        $('#status2').html("<label class='obrig'>Restaurando Backup</label> - Aguarde . . .");
        showDialog();
    }
    
    function restaurarBackup(arquivo){
        alert("Para restaurar é necessário: \n"
            + "- 'PARAR' o GymStyleCore(Núcleo);"
            +"\n- Fechar outras páginas(GymStyle) em aberto;"
            +"\n- Fechar outro acesso do GymStyle na rede;"
            +"\n- (Caso esteja em acesso fora do servidor, deverá ir no servidor fechar o núcleo);"
            +"\n Após a Restauração, 'INICIE' o Núcleo novamente!");
        decisao = confirm("Você perderá todas as informações apartir da data deste Backup. \n\nDeseja realmente restaurar este Backup?");
        if (decisao){
            mostraStatusRestore();
            window.location = "bancoRestaurarBackup.do?arquivo="+arquivo;
        }
    }    

    function showDialog(){
        var id = $(this).attr('href');
        id = ('#holder');
        id = ('#mask');
        //alert(id);

        //armazena a largura e a altura da tela
        var maskHeight = $(document).height();
        var maskWidth = $(window).width();

        //Define largura e altura do div#mask iguais ás dimensões da tela
        $('#mask').css({'width':maskWidth,'height':maskHeight});

        //efeito de transição
        $('#mask').fadeIn(1000);
        //        $('#mask').fadeTo("slow",0.8);

        //armazena a largura e a altura da janela
        var winH = $(window).height();
        var winW = $(window).width();
        //        var winH = 400;
        //        var winW = 500;
        //centraliza na tela a janela popup
        $(id).css('top',  winH/2-$(id).height()/2);
        $(id).css('left', winW/2-$(id).width()/2);
        //        $(id).css('top', 0);
        //        $(id).css('left', winH*0.25);
        //efeito de transição
        $(id).fadeIn(2000);
    }
    
    function closeShow(){
        $('#mask, .window').hide(1000);
    }

    $(document).ready(function() {

        //seleciona os elementos a com atributo name="modal"
        $('a[name=modal]').click(function(e) {
            //cancela o comportamento padrão do link
            e.preventDefault();
            showDialog();
        });

        //se o botãoo fechar for clicado
        $('.window .close').click(function (e) {
            //cancela o comportamento padrão do link
            e.preventDefault();
            closeShow();
        });
    });