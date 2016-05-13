var contador = 5;
function conta() {
    if(contador == 0) {
        atualizarEventos();
        contador = 5;
    }
    if (contador != 0){
        contador = contador-1;
        setTimeout("conta()", 1000);
    }
}
conta();

function atualizarEventos(){
    var val = "ultimosEventos.do?ultimo="+$("#ultimo").val();
    $.post(val, $(this).serialize(), function(d){
        $("#ticker").html(d);
        var i = 0;
        for(i = 0;i < 5;i++){
            if($("#linhaEvento"+i).val()!=null){
                $('#linhaEvento'+i).hide(800);
                $('#linhaEvento'+i).show(600);
            }
        }
    });
}

$(document).ready(function(){
    atualizarEventos();
        
    var nome = window.navigator.appName;
    if(nome == "Microsoft Internet Explorer"){
        $("#internetExplorer").show(700);
    }
});
function encerrarSessao(){
    decisao = confirm("Todos os serviços em execução seram finalizados. Deseja realmente sair?");
    if (decisao){
        window.location = "logout.do";
    }
}
