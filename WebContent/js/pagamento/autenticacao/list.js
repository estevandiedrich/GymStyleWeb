var imprimir = false;
function consultaAutenticacao(){
    imprimir = true;
    setTimeout(function(){
        if(imprimir == true){
            //console.log(document.querySelector('#criterioNome').value);submitFormRead();
            submitFormAutenticacao();
        }
        imprimir = false;
    }, 1000);
}

function down(){
    imprimir = false;
}
    
function submitFormAutenticacao(){
    var criterioAutenticacao= $("#criterioAutenticacao").val();
        
    for(var i =0; i < 27;i++){
        criterioAutenticacao = criterioAutenticacao.replace("_", "");
    }
    if (criterioAutenticacao.length >= 29) {
        $.ajax({
            url: "autenticacaoPagamentoRead.do",
            type: "POST",
            data: {
                "criterioAutenticacao":criterioAutenticacao
            },
            success: function(d){
                $("#content").html(d);
            }
        });
    }
}
    
$(document).ready(function(){
    $("#criterioAutenticacao").mask("999999999-999999999-999999999");
});