$(document).ready(function(){
    $("#observacaoAdd").css("maxWidth","600px");
    $("#observacaoAdd").css("maxHeight","150px");
    $("#observacaoAdd").css("minWidth","600px");
    $("#observacaoAdd").css("minHeight","150px");
    var loc = document.URL;
    var pathName = loc.substring(0, loc.lastIndexOf('/') + 1);
    //fazer o parametro pra baixar as dll local

    console.log(pathName+"applet/dlls.zip");
    $("#urlApplet").val(pathName+"applet/dlls.zip");

    var tipoUsuario = document.getElementById("idTipoUsuario").value;
    if(tipoUsuario != 2){
        $("#autenticacaoDiv").show(700);
    }
    //eventos das abas
    $("#eventoInformacoes").click(function(event){
        event.preventDefault();
        $("#identificacao").hide();
        $("#planos").hide();
        //$("#acessos").hide();
        $("#informacoes").show();
        $('#eventoInformacoes').removeClass('aba').addClass('abaCurrent');
        $('#eventoIdentificacao').removeClass('abaCurrent').addClass('aba');
        $('#eventoPlanos').removeClass('abaCurrent').addClass('aba');
    //$('#eventoAcessos').removeClass('abaCurrent').addClass('aba');

    // $('#buttonSalvarUpdateUsuario').val("Avançar");
    });

    $("#eventoIdentificacao").click(function(event){
        event.preventDefault();
        $("#informacoes").hide();
        $("#planos").hide();
        //$("#acessos").hide();
        $("#identificacao").show();
        $('#eventoIdentificacao').removeClass('aba').addClass('abaCurrent');
        $('#eventoInformacoes').removeClass('abaCurrent').addClass('aba');
        $('#eventoPlanos').removeClass('abaCurrent').addClass('aba');
    // $('#eventoAcessos').removeClass('abaCurrent').addClass('aba');

    //$('#buttonSalvarUpdateUsuario').val("Avançar");
    });

    $("#eventoPlanos").click(function(event){
        event.preventDefault();
        $("#identificacao").hide();
        $("#informacoes").hide();
        //$("#acessos").hide();
        $("#planos").show();
        $('#eventoPlanos').removeClass('aba').addClass('abaCurrent');
        $('#eventoInformacoes').removeClass('abaCurrent').addClass('aba');
        $('#eventoIdentificacao').removeClass('abaCurrent').addClass('aba');
        //$('#eventoAcessos').removeClass('abaCurrent').addClass('aba');

        $('#buttonSalvarUpdateUsuario').val("Salvar");
    });

    //        $("#eventoAcessos").click(function(event){
    //            event.preventDefault();
    //            $("#identificacao").hide();
    //            $("#informacoes").hide();
    //            $("#planos").hide();
    //            //$("#acessos").show();
    //
    //            //$('#eventoAcessos').removeClass('aba').addClass('abaCurrent');
    //            $('#eventoPlanos').removeClass('abaCurrent').addClass('aba');
    //            $('#eventoInformacoes').removeClass('abaCurrent').addClass('aba');
    //            $('#eventoIdentificacao').removeClass('abaCurrent').addClass('aba');
    //
    //            $('#buttonSalvarUpdateUsuario').val("Salvar");
    //        });
        
    //selecionar a aba que deverá ser aberta no validar
    abaSelect = $("#abaSelect").val();
    //$("#"+abaSelect).click();
    if(abaSelect=="eventoInformacoes"){
        $("#identificacao").hide();
        $("#planos").hide();
        $("#informacoes").show();
        $('#eventoInformacoes').removeClass('aba').addClass('abaCurrent');
        $('#eventoIdentificacao').removeClass('abaCurrent').addClass('aba');
        $('#eventoPlanos').removeClass('abaCurrent').addClass('aba');
    //            $('#buttonSalvarUpdateUsuario').val("Avançar");
    }else if(abaSelect=="eventoIdentificacao"){
        $("#informacoes").hide();
        $("#planos").hide();
        $("#identificacao").show();
        $('#eventoIdentificacao').removeClass('aba').addClass('abaCurrent');
        $('#eventoInformacoes').removeClass('abaCurrent').addClass('aba');
        $('#eventoPlanos').removeClass('abaCurrent').addClass('aba');
    //            $('#buttonSalvarUpdateUsuario').val("Avançar");
    }else if(abaSelect=="eventoPlanos"){
        $("#identificacao").hide();
        $("#informacoes").hide();
        $("#planos").show();
        $('#eventoPlanos').removeClass('aba').addClass('abaCurrent');
        $('#eventoInformacoes').removeClass('abaCurrent').addClass('aba');
        $('#eventoIdentificacao').removeClass('abaCurrent').addClass('aba');
    //$('#buttonSalvarUpdateUsuario').val("Salvar");
    //        }else if(abaSelect=="eventoAcessos"){
    //            $("#identificacao").hide();
    //            $("#informacoes").hide();
    //            $("#planos").hide();
    //            $("#acessos").show();
    //            $('#eventoAcessos').removeClass('aba').addClass('abaCurrent');
    //            $('#eventoPlanos').removeClass('abaCurrent').addClass('aba');
    //            $('#eventoInformacoes').removeClass('abaCurrent').addClass('aba');
    //            $('#eventoIdentificacao').removeClass('abaCurrent').addClass('aba');
    //$('#buttonSalvarUpdateUsuario').val("Salvar");
    }

    $("#btCapturar").unbind().click(function(){
        var val = "digitalAjax.read.do?id="+$("#id").val();
        $.post(val, $(this).serialize(), function(d){
            $("#corpoDigitais").html(d);
        });
        return false;
    });

    $("#opcao").unbind().click(function(){
        var val = $("#opcao").val();
    });

    var varDigitais = 1;
    $("#eventoDigitais").click(function(event){
        event.preventDefault();
        if(varDigitais == 1){
            varDigitais = 2;
            $("#digitais").show(700);
            $('#eventoDigitais').removeClass('mais').addClass('menos');
        }else {
            varDigitais = 1;
            $("#digitais").hide("slow");
            $('#eventoDigitais').removeClass('menos').addClass('mais');
        }
        document.getElementById("var6").value = varCartao;
    });

    var varCartao = 1;
    $("#eventoCartao").click(function(event){
        event.preventDefault();
        if(varCartao == 1){
            varCartao = 2;
            $("#cartao").show(700);
            $('#eventoCartao').removeClass('mais').addClass('menos');
        }else {
            varCartao = 1;
            $("#cartao").hide("slow");
            $('#eventoCartao').removeClass('menos').addClass('mais');
        }
        document.getElementById("var7").value = varCartao;
    });

    var varVincular = 1;
    $("#eventoVincularPlano").click(function(event){
        event.preventDefault();
        if(varVincular == 1){
            varVincular = 2;
            $("#vinculados").show(700);
            $('#eventoVincularPlano').removeClass('mais').addClass('menos');
        }else {
            varVincular = 1;
            $("#vinculados").hide("slow");
            $('#eventoVincularPlano').removeClass('menos').addClass('mais');
        }
        document.getElementById("var8").value = varVincular;
    });


    //via onload jquery ele seleciona os que ja foram selecionados
    var val6 = document.getElementById("var6").value;
    if(val6 == 2){
        $("#digitais").show(700);
        $('#eventoDigitais').removeClass('mais').addClass('menos');
        varDigitais = 2;
        document.getElementById("var6").value = varDigitais;
    }

    var val7 = document.getElementById("var7").value;
    if(val7 == 2){
        $("#cartao").show(700);
        $('#eventoCartao').removeClass('mais').addClass('menos');
        varCartao = 2;
        document.getElementById("var7").value = varCartao;
    }
        
    //via onload jquery ele seleciona os q ja foram selecionados
    var val8 = document.getElementById("var8").value;
    if(val8 == 2){
        $("#vinculados").show(700);
        $('#eventoVincularPlano').removeClass('mais').addClass('menos');
        varVincular = 2;
        document.getElementById("var8").value = varVincular;
    }
        
    $('#cartaoProximidade').keypress(function(event) {
        var tecla = (window.event) ? event.keyCode : event.which;
        if ((tecla > 47 && tecla < 58)) return true;
        else {
            if (tecla != 8) return false;
            else return true;
        }
    });
        
    //        $("#telefone").mask("(99)9999-9999");
    //        $("#celular").mask("(99)9999-9999");
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
        
    if($("#loginAux").val() == ""){
        document.getElementById("login").value = "";
        document.getElementById("senha").value = "";
    }else if(($("#loginAux").val() == $("#loginUsuario").val())){
        document.getElementById("senha").value = "";
    }
});
    
function validaForm(){
    //dentro da aba plano
    //dentro do vincular plano, dentro da pasta plano
    // se existe parcelas em branco, não dá submit
    var tamanho = parseInt($("#tamanhoLista").val());
    var opcao = parseInt($("#periodoSelect").val());
    val = true;
    if(!isNaN(tamanho)){
        if(opcao == 2){
            opcao = 1;
        }//Diario semanal
        if(opcao == 3 || opcao == 4){
            opcao = 2;
        }//Mensal e Bimestral 
        if(opcao == 5){
            opcao = 3;
        }//Tri
        if(opcao == 6){
            opcao = 4;
        }//Quadri
        if(opcao == 7){
            opcao = 6;
        }//sem
        if(opcao == 8){
            opcao = 12;
        }//Anual
        if(tamanho != opcao){
            val = false;
            $("#errorDuracao").html("<font class='errors'>Parcelas não batem de acordo com o Plano escolhido, pressione Gerar Pagamentos Novamente!<img src='images/alert.png' title='Campo Obrigatório!'/></font>");
            $("#errorDuracao").show();
        }

        for(i = 0;i<= tamanho;i++){
            if($("#valorTotal"+i).val()==""){
                $("#error"+i).html("<font class='errors'>Campo Obrigatório<img src='images/alert.png' title='Campo Obrigatório!'/></font>");
                val = false;
            }
        }
    }
    if(val){
        document.form.submit();
    }
}
