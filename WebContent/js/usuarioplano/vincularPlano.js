
function verificaMaximo(name,num){
    if($("#"+name).val() > num ){
        $("#"+name).val(num); 
    }
}

function eventoSelectPlano(){
    $("#tabelaPagamentos").html("");
    var opc = $("#planoSelect").find('option').filter(':selected').val();
    if(opc == null || opc == ""){
        $("#divPagamentos").hide(300);
    }else{
        $("#divPagamentos").show(300);
    }
}
//tem que colocar esse trecho para atualizar toda vez que fizer validação.
$(document).ready(function(){
    refreshImportarPlano();
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
});
function validaForm(){
    var tamanho = parseInt($("#tamanhoLista").val());
    var opcao = parseInt($("#periodoSelect").val());
    var val = true;
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

        for(var i = 0;i<= tamanho;i++){
            if($("#valorTotal"+i).val()==""){
                $("#error"+i).html("<font class='errors'>Campo Obrigatório<img src='images/alert.png' title='Campo Obrigatório!'/></font>");
                // $("#error"+i).html("<img src='images/alert.png' title='Campo Obrigatório!'/>");
                val = false;
            }
        }
    }
    if(val){
        document.formVincular.submit();
    }
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

function refreshImportarPlano(){
    var id = $("#planoSelect").find('option').filter(':selected').val();
    if(id==""){
        $("#planoUsuarioForm").hide(900);
    }else{
        $.ajax({
            url: "importarPlanoAjax.do",
            type: "POST",
            data: {
                "id":id
            },
            success: function(d){
                $("#planoUsuarioForm").html(d);
                $("#planoUsuarioForm").show(600);
            }
        });
        
    }
    //para verificar se o plano esta selecionado e voltar a aparecer a divPagamentos
    var opc = $("#planoSelect").find('option').filter(':selected').val();
    if(opc != null && opc != ""){
        $("#divPagamentos").show(300);
    }
}

function refreshTabelaPagamentos(){
    var id = $("#periodoSelect").find('option').filter(':selected').val();
    var valorMatricula = $("#valorMatricula").val();
    var valorTotal = $("#valorTotal").val();
    var descontoReal = $("#descontoReal").val();
    var descontoPercentual = $("#descontoPercentual").val();
    var diaVencimento = $("#diaVencimento").val();
    var tolerancia = $("#tolerancia").val();
    if(id == ""){
        $("#tabelaPagamentos").html("");
    }else{
        var ajax = "parcelasAjax.do?periodoSelect="+id
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
            $("#errorDuracao").hide();
            $(".inputNumber").maskMoney({
                symbol:"R$",
                decimal:",",
                thousands:"."
            })
        });
    }
}