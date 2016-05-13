var TIME = 500;

$(document).ready(function(){
    //----------------------------------------------------------------------
    $("#salvar").click(function(event){
        event.preventDefault();
        carregaHtml();
        document.formOrdenar.submit();
    });
    $("#cancelar").click(function(event){
        document.formOrdenar.submit();
    });
    

    //        mostraOrdem();
    //        carregarExercicios();

    $("#sortableTreinoA").sortable();
    $("#sortableTreinoB").sortable();
    $("#sortableTreinoC").sortable();
    $("#sortableTreinoD").sortable();
    $("#sortableTreinoE").sortable();
    $("#sortableTreinoF").sortable();

});

function carregaHtml(){
    var val = ($("#sortableTreinoA").html());
    $("#treinoAHtml").val(val);
    val = ($("#sortableTreinoB").html());
    $("#treinoBHtml").val(val);
    val = ($("#sortableTreinoC").html());
    $("#treinoCHtml").val(val);
    val = ($("#sortableTreinoD").html());
    $("#treinoDHtml").val(val);
    val = ($("#sortableTreinoE").html());
    $("#treinoEHtml").val(val);
    val = ($("#sortableTreinoF").html());
    $("#treinoFHtml").val(val);
}