function valida(){
    var retorno =  true;
    $("#criterioInicioError").html("");
    $("#criterioFimError").html("");
    if($("#criterioInicio").val() == ""){
        $("#criterioInicioError").html("<img src='images/alert.png' title='Campo Obrigatório!'/>");
        retorno = false;
    }else if($("#criterioInicio").val().length < 10){
        $("#criterioInicioError").html("<img src='images/alert.png' title='Preencha todo o campo!'/>");
        retorno = false;
    }
    if($("#criterioFim").val() == ""){
        $("#criterioFimError").html("<img src='images/alert.png' title='Campo Obrigatório!'/>");
        retorno = false;
    }else if($("#criterioFim").val().length < 10){
        $("#criterioFimError").html("<img src='images/alert.png' title='Preencha todo o campo!'/>");
        retorno = false;
    }
    return retorno
}
function submitPagamentoReportForm(){
    if(valida()){
        document.pagamentoForm.target='_blank';
        document.pagamentoForm.submit();
    }
}