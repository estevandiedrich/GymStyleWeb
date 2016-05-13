function ocultar(){
    if($("#justificativa").val()!=""){
        $("#formLiberar").hide(700);
        $("#aguardando").show(700);
    }
}

$(document).ready(function(){
    //eventos das abas
    $("#salvar").click(function(event){
        event.preventDefault();
        ocultar();
    });
    $("#justificativa").css("maxWidth","500px" );
    $("#justificativa").css("maxHeight","150px");
    $("#justificativa").css("minWidth","500px");
    $("#justificativa").css("minHeight","150px");
        
});