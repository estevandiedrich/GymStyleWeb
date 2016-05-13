var TIME = 500;

$(document).ready(function(){
    $("#detalhesConta").hide(0);
    
    var varDetalhesConta = 1;
    $("#eventoDetalhesConta").click(function(event){
        event.preventDefault();
        var conta= $("#conta").val();
        if(conta !=0){
            if(varDetalhesConta == 1){
                varDetalhesConta = 2;
                $("#detalhesConta").slideDown(TIME);
                $('#eventoDetalhesConta').removeClass('mais').addClass('menos');
            }else {
                varDetalhesConta = 1;
                $("#detalhesConta").slideUp(TIME);
                $('#eventoDetalhesConta').removeClass('menos').addClass('mais');
            }
        }
    });
        
    $("#conta").change(function(event){
        event.preventDefault();
        eventoConta();
    });
    
    
    function eventoConta(){
        var conta= $("#conta").val();
        if(conta==0){
            $('#eventoDetalhesConta').removeClass('menos');
            $('#eventoDetalhesConta').removeClass('mais');
            $("#detalhesConta").slideUp(TIME);
            varDetalhesConta = 1;
            $('#entradaConta').removeClass('entradaCaixa').addClass('entradaCaixaInativo');
            $('#retiradaConta').removeClass('retiradaCaixa').addClass('retiradaCaixaInativo');
            $("#corpoTable").slideUp(TIME);
        }else{
            $("#msgSelecioneConta").hide(500);
            $("#corpoTable").slideDown(TIME);
            if(varDetalhesConta==2){
                $('#eventoDetalhesConta').addClass('menos');
            }else{
                $('#eventoDetalhesConta').addClass('mais');
            }
            readContaById(conta);
            $('#entradaConta').removeClass('entradaCaixaInativo').addClass('entradaCaixa');
            $('#retiradaConta').removeClass('retiradaCaixaInativo').addClass('retiradaCaixa');
        }
    }
    
    function readContaById(idConta){
        $.ajax({
            url: "contaBancariaReadbyIdAjax.do",
            type: "POST",
            data: {
                "idConta":idConta
            },
            success: function(d){
                $("#infoConta").html(d);
                $("#detalhesConta").show(500);
                $('#eventoDetalhesConta').removeClass('mais').addClass('menos');
                varDetalhesConta = 2;
                readRegistros();
            }
        });
    }
    if($("#conta").val()>0){
        readContaById($("#conta").val());
        eventoConta();
    }
    
    $( "#entradaConta" ).click(function() {
        if ( $("#entradaConta").is(".entradaCaixa" ) ) {
            $("#msgSelecioneConta").slideUp(500);
            showEntrada();
        }else{
            $("#msgSelecioneConta").slideUp(500);
            ;
            $("#msgSelecioneConta").slideDown(500);
        }
    });
    $( "#retiradaConta" ).click(function() {
        if ( $("#retiradaConta").is(".retiradaCaixa" ) ) {
            $("#msgSelecioneConta").slideUp(500);
            showRetirada();
        }else{
            $("#msgSelecioneConta").slideUp(500);
            ;
            $("#msgSelecioneConta").slideDown(500);
        }
    });
    
    $("#criterioInicio").mask("99/99/9999");
    $("#criterioFim").mask("99/99/9999");

});

    
function showRetirada(){
    showDialog("windowRetirada","statusRetirada");
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
    $('#'+win).fadeIn(TIME);
    //        $('#mask').fadeTo("slow",0.8);

    //armazena a largura e a altura da janela
    var winH = $(window).height();
    var winW = $(window).width();

    //centraliza na tela a janela popup
    $(id).css('top', 0);
    $(id).css('left',0);
    $("#"+ status).css('top', -150);
    $("#"+ status).css('left', (winW-700) / 2);

    $(id).fadeIn(2000);
}
    
function closeShowRetirada(){
    $('#windowRetirada').hide(TIME);
    $("#idRegistroRetirada").val("");
    $("#valorRetiradaContaBancaria").val("0,00");
    $("#descricaoRetirada").val("RETIRADA CONTA BANCÁRIA");
    $("#formaPagamentoRetirada option[value='"+1+"']").attr('selected',true);
        
    $("#valorRetiradaError").hide();
    $("#descricaoRetiradaError").hide();
    $("#formaPagamentoRetiradaError").hide();
    document.getElementById("criterioNao").checked=true;
    $("#idRegistroCaixa").val(0);

    hideContaSaida();
}    
    
function showEditar(entrada,id,valor,idForma,descricao,idRegistroCaixa){
    if(entrada){
        $("#idRegistroEntrada").val(id);
        $("#valorEntradaContaBancaria").val(float2moeda(valor));
        $("#descricaoEntrada").val(descricao);
        $("#formaPagamentoEntrada option[value='"+idForma+"']").attr('selected',true);
        if(idRegistroCaixa > 0){
            document.getElementById("criterioSim").checked=true;
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
        $("#valorRetiradaContaBancaria").val(float2moeda(valor));
        $("#descricaoSaida").val(descricao);
        $("#formaPagamentoSaida option[value='"+idForma+"']").attr('selected',true);
        $("#idRegistroCaixa").val(idRegistroCaixa);
        if(idRegistroCaixa > 0){
            document.getElementById("criterioSim").checked=true;
        }
        showRetirada();
    }
}
    
function showEntrada(){
    showDialog("windowEntrada","statusEntrada");
}
    
function closeShowEntrada(){
    $('#windowEntrada').hide(TIME);
    $("#idRegistroEntrada").val("");
    $("#valorEntradaContaBancaria").val("0,00");
    $("#descricaoEntrada").val("ENTRADA CONTA BANCARIA");
    $("#formaPagamentoEntrada option[value='"+1+"']").attr('selected',true);
        
    $("#valorEntradaError").hide();
    $("#descricaoEntradaError").hide();
    $("#formaPagamentoEntradaError").hide();   
    hideContaEntrada();
}

function submitFormFiltrar(p){
    if(p!=""){
        $("#p").val(p);
    }
    readRegistros();
}

function readRegistros(){
    $("#processando").show(700);
    var criterioConta= $("#conta").val();
    var criterioInicio= $("#criterioInicio").val();
    var criterioFim = $("#criterioFim").val();
    if(criterioConta != 0 && criterioInicio.length==10 && criterioFim.length==10){
        var p= $("#p").val();
        var orderBy= false;
        //if(document.getElementById("orderBy").checked){
        orderBy = true;
        //}
        if(p == undefined){
            p = 1;                
        }
        
        $.ajax({
            url: "regContaBancariaReadAjax.do",
            type: "POST",
            data: {
                "criterioConta":criterioConta,
                "criterioInicio":criterioInicio,
                "criterioFim":criterioFim,
                "pag.p":p,
                "orderBy":orderBy,
                "pag.currentPage":p
            },
            success: function(d){
                $("#content").html(d);
            }
        });
    }
}

function excluirRegistroContaBancaria(id){
    decisao = confirm("Deseja realmente excluir registro de conta bancária?");
    if (decisao){
        //window.location = "registroContaBancariaDelete.do?id="+id;
        $.ajax({
            url: "registroContaBancariaDelete.do",
            type: "POST",
            data: {
                "id":id
            },
            success: function(d){
                //$("#content").html(d);
                readRegistros();
            }
        });
    }
}