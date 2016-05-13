
$(document).ready(function(){
    ajustar();
    
    $("#valorMatricula").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    })
    $("#valorTotal").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    })
    $("#valorPercentual").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    })
    $("#descontoReal").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    })
    $("#valor").attr('readonly', true);
    $("#observacao").css("maxWidth","500px");
    $("#observacao").css("maxHeight","100px");
    $("#observacao").css("minWidth","500px");
    $("#observacao").css("minHeight","100px");
    //        $(".inputTime").mask("99:99");        

    function ajustar(){
        if($('#todos').attr('checked')) {
            eventoTodosCadastrarPlanos();
        }else{
            var tam = ($('#tamanho').val());
            if(tam > 0){
                for (i=1;i<=tam;i++){
                    if($('#checados'+i).val()=="true"){
                        $("#modalidades"+i).attr('checked', true);
                    }else{
                        $("#modalidades"+i).attr('checked', false);
                    }
                }
            }
        }
        if(document.getElementById("formaDeDesconto1").checked){            
            $("#descontoUm").show(700);
            $("#descontoDois").hide(700);
        }else if(document.getElementById("formaDeDesconto2").checked){
            $("#descontoDois").show(700);
            $("#descontoHide").hide(700);
        }
    }

    function selectLinha(){
        //alert(name);
       // alert("i");
    //    if($('#' + name).prop('checked') ){
    //        alert("Chekado");
    //    }
    }

    function eventoTodosCadastrarPlanos(){
        alert("plano");
        var tam = ($('#tamanho').val());
        var valor = 000;
        var val = false;
        if(tam > 0){
            if ($('#todos').attr('checked')) {
                val = true;
            }else{
                valor = "";
            }
            for (i=1;i<=tam;i++){
                $("#modalidades"+i).attr('checked', val);
                valor = valor + parseFloat(($("#valorFinal"+i).val()));
            }
            var descontoReal = ($('#descontoReal').val());
            descontoReal = getValor(descontoReal);
            var descontoPercentual = ($('#descontoPercentual').val());
            descontoPercentual = getValor(descontoPercentual);
            var valorTotal = 0;
            if ($('#todos').attr('checked')) {
                $("#valor").val(float2moeda(valor));
                valorTotal = (float2moeda(valor - descontoReal- ((valor * descontoPercentual)/100)));
            }else{
                $("#valor").val(float2moeda(0));
                valorTotal = (float2moeda(descontoReal - ((descontoPercentual)/100)));
            }
            if(getValor(valorTotal)<0){
                valorTotal = 0;
            }
            $("#valorTotal").val(valorTotal);
        }
    }

    //Evento selecionar modalidades no cadastrar planos
    function eventoSelectCadastrarPlanos(){
        alert("AAA");
        var tam = ($('#tamanho').val());
        var valor = 000;
        if(tam > 0){
            var j = 0;
            for (var i=1;i<=tam;i++){
                if ($("#modalidades"+i).attr('checked')) {
                    valor = valor + parseFloat(($("#valorFinal"+i).val()));
                    j++;
                }
            }
            if(j == tam){
                $("#todos").attr('checked', true);
            }else{
                $("#todos").attr('checked', false);
            }
            $("#valor").val(float2moeda(valor));
            var descontoReal = ($('#descontoReal').val());
            descontoReal = getValor(descontoReal);
            var descontoPercentual = ($('#descontoPercentual').val());
            descontoPercentual = getValor(descontoPercentual);
            var valorTotal = (float2moeda(valor - descontoReal- ((valor * descontoPercentual)/100)));
            if(getValor(valorTotal)<0){
                valorTotal = 0;
            }
            $("#valorTotal").val(valorTotal);
        }
    }
});