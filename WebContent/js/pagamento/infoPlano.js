$(document).ready(function(){
    var varDetalhes = 1;
    $("#eventoDetalhesPlano").click(function(event){
        event.preventDefault();
        if(varDetalhes == 1){
            varDetalhes = 2;
            $("#informacoesPlano").show(700);
            $('#eventoDetalhesPlano').removeClass('mais').addClass('menos');
        }else {
            varDetalhes = 1;
            $("#informacoesPlano").hide("slow");
            $('#eventoDetalhesPlano').removeClass('menos').addClass('mais');
        }
    });
});