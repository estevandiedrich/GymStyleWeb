$(document).ready(function(){
    var varDetalhes = 1;
    $("#eventoDetalhesPlano").click(function(event){
        event.preventDefault();
        if(varDetalhes == 1){
            varDetalhes = 2;
            $("#corpoInfoPlano").show(700);
            $('#eventoDetalhesPlano').removeClass('mais').addClass('menos');
        }else {
            varDetalhes = 1;
            $("#corpoInfoPlano").hide("slow");
            $('#eventoDetalhesPlano').removeClass('menos').addClass('mais');
        }
    });
    $("#observacao").attr('disabled', true);
    $("#observacao").css("background-color","white");
    $("#observacao").css("maxWidth","600px");
    $("#observacao").css("maxHeight","150px");
    $("#observacao").css("minWidth","600px");
    $("#observacao").css("minHeight","150px");
});