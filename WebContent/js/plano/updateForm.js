
$(document).ready(function(){
    ajustar();
    $("#observacao").css("maxWidth","500px");
    $("#observacao").css("maxHeight","100px");
    $("#observacao").css("minWidth","500px");
    $("#observacao").css("minHeight","100px");
        
    $("#descontoReal").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    })
    $("#valor").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    })
    $("#valorTotal").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    })
    $("#descontoReal").val(float2moeda($("#descontoReal").val()));
    $("#valor").val(float2moeda($("#valor").val()));
    $("#valorTotal").val(float2moeda($("#valorTotal").val()));
});

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
    if($("#formaDeDesconto1").val()!= undefined && document.getElementById("formaDeDesconto1").checked){            
        $("#descontoDois").hide(700);
    }else if($("#formaDeDesconto2").val()!= undefined && document.getElementById("formaDeDesconto2").checked){
        $("#descontoUm").hide(700);
    }
}