
function eventoAplicarFaixasNovo(){
    var j=1;
    var i=2;
    var selecionados = 0;
    for (j=1;j<=7;j++){
        if ($('#domingoNovo').attr('checked')) {
            $("#domingo"+j+"Novo").val($("#entradaNovo"+j).val());
            $("#domingo"+i+"Novo").val($("#saidaNovo"+i).val());
            selecionados++;
        }

        if ($('#segundaNovo').attr('checked')) {
            $("#segunda"+j+"Novo").val($("#entradaNovo"+j).val());
            $("#segunda"+i+"Novo").val($("#saidaNovo"+i).val());
            selecionados++;
        }
        if ($('#tercaNovo').attr('checked')) {
            $("#terca"+j+"Novo").val($("#entradaNovo"+j).val());
            $("#terca"+i+"Novo").val($("#saidaNovo"+i).val());
            selecionados++;
        }

        if ($('#quartaNovo').attr('checked')) {
            $("#quarta"+j+"Novo").val($("#entradaNovo"+j).val());
            $("#quarta"+i+"Novo").val($("#saidaNovo"+i).val());
            selecionados++;
        }
        if ($('#quintaNovo').attr('checked')) {
            $("#quinta"+j+"Novo").val($("#entradaNovo"+j).val());
            $("#quinta"+i+"Novo").val($("#saidaNovo"+i).val());
            selecionados++;
        }
        if ($('#sextaNovo').attr('checked')) {
            $("#sexta"+j+"Novo").val($("#entradaNovo"+j).val());
            $("#sexta"+i+"Novo").val($("#saidaNovo"+i).val());
            selecionados++;
        }
        if ($('#sabadoNovo').attr('checked')) {
            $("#sabado"+j+"Novo").val($("#entradaNovo"+j).val());
            $("#sabado"+i+"Novo").val($("#saidaNovo"+i).val());
            selecionados++;
        }
        if ($('#feriadoNovo').attr('checked')) {
            $('#feriado').val(1);
            $("#feriado"+j+"Novo").val($("#entradaNovo"+j).val());
            $("#feriado"+i+"Novo").val($("#saidaNovo"+i).val());
            selecionados++;
        }else{
            $('#feriado').val(0);
        }
        j++;
        i = i+2;
    }
    if(selecionados == 0){
        alert("Selecione o dia da semana!");
    }
}

function verificaTodosNovo(novo){
    var selecionados = 0;
    if ($('#domingo'+novo).attr('checked')) {
        selecionados++;
    }
    if ($('#segunda'+novo).attr('checked')) {
        selecionados++;
    }
    if ($('#terca'+novo).attr('checked')) {
        selecionados++;
    }

    if ($('#quarta'+novo).attr('checked')) {
        selecionados++;
    }
    if ($('#quinta'+novo).attr('checked')) {
        selecionados++;
    }
    if ($('#sexta'+novo).attr('checked')) {
        selecionados++;
    }
    if ($('#sabado'+novo).attr('checked')) {
        selecionados++;
    }
    if ($('#feriado'+novo).attr('checked')) {
        selecionados++;
    }
    if(selecionados == 8){
        $('#todos'+novo).attr('checked', true);
    }else{
        $('#todos'+novo).attr('checked', false);
    }
}

function eventoTodosAplicarFaixasNovo(){
    if ($('#todosNovo').attr('checked')) {
        $('#domingoNovo').attr('checked', true);
        $('#segundaNovo').attr('checked', true);
        $('#tercaNovo').attr('checked', true);
        $('#quartaNovo').attr('checked', true);
        $('#quintaNovo').attr('checked', true);
        $('#sextaNovo').attr('checked', true);
        $('#sabadoNovo').attr('checked', true);
        $('#feriadoNovo').attr('checked', true);
    }else{
        $('#domingoNovo').attr('checked', false);
        $('#segundaNovo').attr('checked', false);
        $('#tercaNovo').attr('checked', false);
        $('#quartaNovo').attr('checked', false);
        $('#quintaNovo').attr('checked', false);
        $('#sextaNovo').attr('checked', false);
        $('#sabadoNovo').attr('checked', false);
        $('#feriadoNovo').attr('checked', false);
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