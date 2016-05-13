$(document).ready(function() {
    $('#bancoSelect').change(function(e) {
        buscaCarteiras($('#bancoSelect').val());
    });
    $("#cedenteCnpj").mask("99.999.999/9999-99");
    
});

function buscaCarteiras(banco){
    
    $.ajax({
        url: "carteirasAjax.do",
        type: "POST",
        data: {
            "banco":banco
        },
        success: function(d){
            $("#carteiraResult").html(d);
        }
    });
}