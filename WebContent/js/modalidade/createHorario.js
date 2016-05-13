function eventoAplicarFaixas(){
    var j=1;
    var i=2;
    var selecionados = 0;
    for (j=1;j<=7;j++){
        if ($('#domingo').attr('checked')) {
            $("#domingo"+j).val($("#entrada"+j).val());
            $("#domingo"+i).val($("#saida"+i).val());
            selecionados++;
        }

        if ($('#segunda').attr('checked')) {
            $("#segunda"+j).val($("#entrada"+j).val());
            $("#segunda"+i).val($("#saida"+i).val());
            selecionados++;
        }
        if ($('#terca').attr('checked')) {
            $("#terca"+j).val($("#entrada"+j).val());
            $("#terca"+i).val($("#saida"+i).val());
            selecionados++;
        }

        if ($('#quarta').attr('checked')) {
            $("#quarta"+j).val($("#entrada"+j).val());
            $("#quarta"+i).val($("#saida"+i).val());
            selecionados++;
        }
        if ($('#quinta').attr('checked')) {
            $("#quinta"+j).val($("#entrada"+j).val());
            $("#quinta"+i).val($("#saida"+i).val());
            selecionados++;
        }
        if ($('#sexta').attr('checked')) {
            $("#sexta"+j).val($("#entrada"+j).val());
            $("#sexta"+i).val($("#saida"+i).val());
            selecionados++;
        }
        if ($('#sabado').attr('checked')) {
            $("#sabado"+j).val($("#entrada"+j).val());
            $("#sabado"+i).val($("#saida"+i).val());
            selecionados++;
        }
        if ($('#feriado').attr('checked')) {
            $('#feriado').val(1);
            $("#feriado"+j).val($("#entrada"+j).val());
            $("#feriado"+i).val($("#saida"+i).val());
            selecionados++;
        }
        else{
            $('#feriado').val(0);
        }
        j++;
        i = i+2;
    }
    if(selecionados == 0){
        alert("Selecione o dia da semana!");
    }
}

function eventoTodosAplicarFaixas(){
    if ($('#todos').attr('checked')) {
        $('#domingo').attr('checked', true);
        $('#segunda').attr('checked', true);
        $('#terca').attr('checked', true);
        $('#quarta').attr('checked', true);
        $('#quinta').attr('checked', true);
        $('#sexta').attr('checked', true);
        $('#sabado').attr('checked', true);
        $('#feriado').attr('checked', true);
    }
    else{
        $('#domingo').attr('checked', false);
        $('#segunda').attr('checked', false);
        $('#terca').attr('checked', false);
        $('#quarta').attr('checked', false);
        $('#quinta').attr('checked', false);
        $('#sexta').attr('checked', false);
        $('#sabado').attr('checked', false);
        $('#feriado').attr('checked', false);
    }
}


//Importar horário
function refreshImportar(){
    var id = $("#horarioSelect").find('option').filter(':selected').val();
    if(id==""){
        alert("Selecione um horário para importar!");
    }
    var val = "importarHorarioAjax.do?id="+id;
    $.post(val, $(this).serialize(), function(d){
        $("#corpoHorarios").html(d);
        $("#entrada1").mask("99:99");
        $("#entrada3").mask("99:99");
        $("#entrada5").mask("99:99");
        $("#entrada7").mask("99:99");
        $("#saida2").mask("99:99");
        $("#saida4").mask("99:99");
        $("#saida6").mask("99:99");
        $("#saida8").mask("99:99");
        aplicaMascaraTime("domingo");
        aplicaMascaraTime("segunda");
        aplicaMascaraTime("terca");
        aplicaMascaraTime("quarta");
        aplicaMascaraTime("quinta");
        aplicaMascaraTime("sexta");
        aplicaMascaraTime("sabado");
        aplicaMascaraTime("feriado");
    });
}

function aplicaMascaraTime(dia){
    for(var i = 1;i<=8;i++){
        $("#"+dia+i).mask("99:99");
    }
}