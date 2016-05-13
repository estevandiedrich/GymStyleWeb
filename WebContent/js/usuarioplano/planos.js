$(document).ready(function(){
    var varVincular = 2;
    $("#eventoVincularPlano").click(function(event){
        event.preventDefault();
        if(varVincular == 1){
            varVincular = 2;
            $("#vinculados").show(700);
            $('#eventoVincularPlano').removeClass('mais').addClass('menos');
        }else {
            varVincular = 1;
            $("#vinculados").hide("slow");
            $('#eventoVincularPlano').removeClass('menos').addClass('mais');
        }
    });
    var varPlanos = 2;
    $("#eventoPlanosUsuario").click(function(event){
        event.preventDefault();
        if(varPlanos == 1){
            varPlanos = 2;
            $("#planosVincular").show(700);
            $('#eventoPlanosUsuario').removeClass('mais').addClass('menos');
        }else {
            varPlanos = 1;
            $("#planosVincular").hide("slow");
            $('#eventoPlanosUsuario').removeClass('menos').addClass('mais');
        }
    });
});
