$(document).ready(function(){
    
    $("#observacaoAdd").css("maxWidth","600px");
    $("#observacaoAdd").css("maxHeight","150px");
    $("#observacaoAdd").css("minWidth","600px");
    $("#observacaoAdd").css("minHeight","150px");

    $("#cpf").mask("999.999.999-99");
    $("#cep").mask("99999-999");
    //$("#cartaoProximidade").mask("9999999999");

    $("#dataNascimentoFormat").mask("99/99/9999");
    var idUsuario = document.getElementById("id").value;
    if(idUsuario==1){
        document.getElementById("idTipoUsuario").disabled = true; 
    }
    //para quando entrar no onload verificar CPF
    verificaCpf($("#cpf").val());

    var varDadosPessoais = 2;
    $("#eventoDadosPessoais").click(function(event){
        event.preventDefault();
        if(varDadosPessoais == 1){
            varDadosPessoais = 2;
            $("#dadosPessoais").show(700);
            $('#eventoDadosPessoais').removeClass('mais').addClass('menos');
        }else {
            varDadosPessoais = 1;
            $("#dadosPessoais").hide("slow");
            $('#eventoDadosPessoais').removeClass('menos').addClass('mais');
        }
        document.getElementById("var1").value = varDadosPessoais;
    });

    var varLocal = 1;
    $("#eventoLocal").click(function(event){
        event.preventDefault();
        if(varLocal == 1){
            varLocal = 2;
            $("#localidade").show(700);
            $('#eventoLocal').removeClass('mais').addClass('menos');
        }else {
            varLocal = 1;
            $("#localidade").hide(700);
            $('#eventoLocal').removeClass('menos').addClass('mais');
        }
        document.getElementById("var2").value = varLocal;
    });
         
    var varContato = 1;
    $("#eventoContato").click(function(event){
        event.preventDefault();
        if(varContato == 1){
            varContato = 2;
            $("#contato").show(700);
            $('#eventoContato').removeClass('mais').addClass('menos');
        }else{
            varContato = 1;
            $("#contato").hide(700);
            $('#eventoContato').removeClass('menos').addClass('mais');
        }
        document.getElementById("var3").value = varContato;
    });
        
    var varFoto = 1;
    $("#eventoFoto").click(function(event){
        event.preventDefault();
        if(varFoto == 1){
            varFoto = 2;
            $("#foto").show(700);
            $('#eventoFoto').removeClass('mais').addClass('menos');
        }else{
            varFoto = 1;
            $("#foto").hide(700);
            $('#eventoFoto').removeClass('menos').addClass('mais');
        }
        document.getElementById("var4").value = varFoto;
    });

    var varInformacoesAdd = 1;
    $("#eventoInformacoesAdd").click(function(event){
        event.preventDefault();
        if(varInformacoesAdd== 1){
            varInformacoesAdd= 2;
            $("#informacoesAdd").show(700);
            $('#eventoInformacoesAdd').removeClass('mais').addClass('menos');
        }else {
            varInformacoesAdd = 1;
            $("#informacoesAdd").hide("slow");
            $('#eventoInformacoesAdd').removeClass('menos').addClass('mais');
        }
        document.getElementById("var6").value = varInformacoesAdd;
    });


    //via onload jquery ele seleciona os q ja foram selecionados
    var val1 = document.getElementById("var1").value;
    if(val1 == 2){
        $("#dadosPessoais").show(700);
        $('#eventoDadosPessoais').removeClass('mais').addClass('menos');
        varDadosPessoais = 2;
        document.getElementById("var1").value = varDadosPessoais;
    }
    var val2 = document.getElementById("var2").value;
    if(val2 == 2){
        $("#localidade").show(700);
        $('#eventoLocal').removeClass('mais').addClass('menos');
        varLocal = 2;
        document.getElementById("var2").value = varLocal;
    }
    var val3 = document.getElementById("var3").value;
    if(val3 == 2){
        $("#contato").show(700);
        $('#eventoContato').removeClass('mais').addClass('menos');
        varContato = 2;
        document.getElementById("var3").value = varContato;
    }
    var val4 = document.getElementById("var4").value;
    if(val4 == 2){
        $("#foto").show(700);
        $('#eventoFoto').removeClass('mais').addClass('menos');
        varFoto = 2;
        document.getElementById("var4").value = varFoto;
    }
    //var5 autenticacao
});