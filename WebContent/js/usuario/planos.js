
function verificaMaximo(name,num){
    if($("#"+name).val() > num ){
        $("#"+name).val(num); 
    }
}

function eventoSelectPlano(){
    var time = 500;
    $("#tabelaPagamentos").html("");
    var opc = $("#planoSelect").find('option').filter(':selected').val();
    if(opc == null || opc == ""){
        $("#divPagamentos").hide(time);
    }else{
        $("#divPagamentos").hide().slideDown(time);
    }
}
//tem que colocar esse trecho para atualizar toda vez que fizer validação.
$(document).ready(function(){
    refreshImportarPlanoUsuario();
    refreshTabelaPagamentos();
    $(".inputTelefone").keydown(function(event){
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
        }else {
            /* Stop key press */
            if (event.shiftKey || (event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105 )) {
                event.preventDefault(); 
            }
        }
    });
    
    $("#descontoReal").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });

    var varDetalhes = 1;
    $("#eventoDetalhes").click(function(event){
        event.preventDefault();
        if(varDetalhes == 1){
            varDetalhes = 2;
            $("#corpoInfoPlano").show(700);
            $('#eventoDetalhes').removeClass('mais').addClass('menos');
        }else {
            varDetalhes = 1;
            $("#corpoInfoPlano").hide("slow");
            $('#eventoDetalhes').removeClass('menos').addClass('mais');
        }
    });
    $("#observacao").attr('disabled', true);
    $("#observacao").css("background-color","white");
    $("#observacao").css("maxWidth","500px");
    $("#observacao").css("maxHeight","100px");
    $("#observacao").css("minWidth","500px");
    $("#observacao").css("minHeight","100px");
  
});

//-------------------------------------------
//------------- Pagamentos ------------------
//-------------------------------------------

function refreshTabelaPagamentos(){
    var idPlanoSelect = $("#planoSelect").find('option').filter(':selected').val();
    var idPeriodoSelect = $("#periodoSelect").find('option').filter(':selected').val();
    var valorMatricula = $("#valorMatricula").val();
    var valorTotal = $("#valorTotal").val();
    var descontoReal = $("#descontoReal").val();
    var descontoPercentual = $("#descontoPercentual").val();
    var diaVencimento = $("#diaVencimento").val();
    var tolerancia = $("#tolerancia").val();
    if(idPeriodoSelect == "" || idPlanoSelect == ""){
        $("#tabelaPagamentos").html("");
    }else{
        var ajax = "parcelasAjax.do?periodoSelect="+idPeriodoSelect
        +"&valorTotal="+valorTotal
        +"&valorMatricula="+valorMatricula
        +"&diaVencimento="+diaVencimento
        +"&descontoReal="+descontoReal
        +"&descontoPercentual="+descontoPercentual
        +"&tolerancia="+tolerancia;
        $.post(ajax, $(this).serialize(), function(d){
            $("#tabelaPagamentos").hide();
            $("#tabelaPagamentos").html(d);
            $("#tabelaPagamentos").show(500);
            $(".inputNumber").maskMoney({
                symbol:"R$",
                decimal:",",
                thousands:"."
            });
            $("#errorDuracao").hide();
        });
    }
    
}

function calculaValorPrimeiraParcela(){
    if($("#valorTotal").val()==""){
        return 0;
    }
    var valor = parseFloat($("#valorTotal").val());
    var valorMatricula = parseFloat($("#valorMatricula").val());
    var diaVencimento = $("#diaVencimento").val();
    var dataAtual = new Date();
    var dia = dataAtual.getDate();
    var qtde = 0;
    if(dia < diaVencimento){
        qtde = diaVencimento - dia;
    }else{
        qtde = dia - diaVencimento;
        qtde = 30 - qtde;
    }
        
    var descontoReal = parseFloat($("#descontoReal").val());
    var descontoPercentual = parseFloat($("#descontoPercentual").val());
    var valorFinal = (((valor / 30 )* qtde));
    valorFinal += valorMatricula;
    if(descontoReal == 0){
        if(descontoPercentual != 0){
            valorFinal = valorFinal - (valorFinal*(descontoPercentual/100));
        }
    }else{
        valorFinal = valorFinal - descontoReal;
        if(valorFinal<0){
            valorFinal = 0;
        }
    }
    return (valorFinal);
}

function formaDeDesconto(valor){
    if(valor == 2){
        $('#descontoUm').hide();
        $('#descontoDois').show(700);
    }else{
        $('#descontoDois').hide();
        $('#descontoUm').show(700);
    }
    $('#descontoReal').val("0,00");
    $("#descontoPercentual").val("0");
}

function refreshImportarPlanoUsuario(){
    var time= 700;
    var planoSelect = $("#planoSelect").find('option').filter(':selected').val();
    if(planoSelect==""){
        $("#planoUsuarioForm").slideUp(time);
    }else{
        $.ajax({
            url: "importarPlanoAjax.do",
            type: "POST",
            data: {
                "id":planoSelect
            },
            success: function(d){
                $("#planoUsuarioForm").html(d);
                $("#planoUsuarioForm").slideDown(time);
            }
        });
        
    }
    if(planoSelect != null && planoSelect != ""){
        $("#divPagamentos").hide().slideDown(time);
    }
}
