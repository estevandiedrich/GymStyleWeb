$(document).ready(function(){
    //eventos das abas
    $("#eventoModalidade").click(function(event){
        event.preventDefault();
        $("#horarioMod").hide();
        $("#catracaMod").hide();
        $("#modalidadeMod").show(400);
        $('#eventoModalidade').removeClass('aba').addClass('abaCurrent');
        $('#eventoHorario').removeClass('abaCurrent').addClass('aba');
        $('#eventoCatraca').removeClass('abaCurrent').addClass('aba');
        document.getElementById("var1").value = 2;
        document.getElementById("var2").value = 1;
        document.getElementById("var3").value = 1;
    });

    $("#eventoHorario").click(function(event){
        event.preventDefault();

        $("#modalidadeMod").hide();
        $("#catracaMod").hide();
        $("#horarioMod").show(400);
        $('#eventoHorario').removeClass('aba').addClass('abaCurrent');
        $('#eventoModalidade').removeClass('abaCurrent').addClass('aba');
        $('#eventoCatraca').removeClass('abaCurrent').addClass('aba');
        document.getElementById("var1").value = 1;
        document.getElementById("var2").value = 2;
        document.getElementById("var3").value = 1;
    });
        
    $("#eventoCatraca").click(function(event){
        event.preventDefault();
        $("#modalidadeMod").hide();
        $("#horarioMod").hide();
        $("#catracaMod").show(400);
        $('#eventoCatraca').removeClass('aba').addClass('abaCurrent');
        $('#eventoModalidade').removeClass('abaCurrent').addClass('aba');
        $('#eventoHorario').removeClass('abaCurrent').addClass('aba');
        document.getElementById("var1").value = 1;
        document.getElementById("var2").value = 1;
        document.getElementById("var3").value = 2;
    });
        
    var varImportarHorarios = 1;
    $("#eventoImportar").click(function(event){
        event.preventDefault();
        if(varImportarHorarios == 1){
            varImportarHorarios = 2;
            varCadastrarHorarios = 1;
            $("#importar").show(800);
            $("#cadastrarHorarios").hide("slow");
            $('#eventoImportar').removeClass('mais').addClass('menos');
            $('#eventoCadastrarHorarios').removeClass('menos').addClass('mais');
        }else {
            varImportarHorarios = 1;
            $("#importar").hide("slow");
            $('#eventoImportar').removeClass('menos').addClass('mais');
            $('#eventoCadastrarHorarios').removeClass('menos').addClass('mais');
        }
        document.getElementById("horarioImport").value = varImportarHorarios;
        document.getElementById("horarioNovo").value = varCadastrarHorarios;
    });

    var varCadastrarHorarios = 1;
    $("#eventoCadastrarHorarios").click(function(event){
        event.preventDefault();
        if(varCadastrarHorarios == 1){
            varCadastrarHorarios = 2;
            varImportarHorarios = 1;
            $("#cadastrarHorarios").show(800);
            $("#importar").hide("slow");
            $('#eventoCadastrarHorarios').removeClass('mais').addClass('menos');
            $('#eventoImportar').removeClass('menos').addClass('mais');
            $('#corpoHorarios').html(" ");
        }else {
            varCadastrarHorarios = 1;
            $("#cadastrarHorarios").hide("slow");
            $('#eventoCadastrarHorarios').removeClass('menos').addClass('mais');
            document.getElementById("horarioImport").value = varImportarHorarios;
            document.getElementById("horarioNovo").value = varCadastrarHorarios;
        }
    });

    //via onload jquery ele seleciona os q ja foram selecionados
    var val1 = document.getElementById("var1").value;
    if(val1 == 2){
        document.getElementById("eventoModalidade").click();
    }
    var val2 = document.getElementById("var2").value;
    if(val2 == 2){
        document.getElementById("eventoHorario").click();
    }
    var val3 = document.getElementById("var3").value;
    if(val3 == 2){
        document.getElementById("eventoCatraca").click();
    }

    var varImport = document.getElementById("horarioImport").value;
    if(varImport == 2){
        document.getElementById("eventoImportar").click();
    //refreshImportar();
    }
        
    var varNovo = document.getElementById("horarioNovo").value;
    if(varNovo == 2){
        document.getElementById("eventoCadastrarHorarios").click();
    }
        
    $(".inputNumber").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
    $(".inputTime").mask("99:99");
});