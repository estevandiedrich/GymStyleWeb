
var TIME = 500;
var treinos = ['-f','-e','-d','-c','-b','-a'];
var GRUPOS = ['peitoral','dorsal','trapezio','deltoide','triceps','biceps','antebraco','quadriceps','isquiotibiais','gluteos','adutores','abdutores','gastrocnemicos','soleo','abdomen'];

$(document).ready(function(){
    $("#descricao").css("maxWidth","600px" );
    $("#descricao").css("maxHeight","100px" );
    $("#descricao").css("minWidth","600px" );
    $("#descricao").css("minHeight","100px" );
    
    $("#periodoInicial").mask("99/99/9999");
    $("#periodoFinal").mask("99/99/9999");
    
    $(".aba-ficha").click(function(){
        $(".aba-ficha").removeClass("current");
        $(this).addClass("current");
        $(".div-treino").hide(TIME);
        $("#div-" + $(this).attr("id")).show(TIME);
    });

    $(".mais").click(function(){
        if($(this).hasClass("mais")){
            $(this).removeClass("mais");
            $(this).addClass("menos");
            $("#div-" + $(this).attr("id")).show(TIME);
        }else{
            $(this).removeClass("menos");
            $(this).addClass("mais");
            $("#div-" + $(this).attr("id")).hide(TIME);
        }
    });
    
    $("#div-treino-a input[type=text]").keyup(function(e){
        var ecode = e.which;
        var grupo = $(this).parents(".grupo").attr("id").replace("div-", "");
        if(ecode!=8 && ecode!=46){//
            $("#treino-a").addClass("tem-dados");
            $("#"+grupo).css("color","#000");
        }
        else{
            verificaInputs("treino-a");
        }
        validaTreino("-a");
    });
    
    $("#div-treino-b input[type=text]").keyup(function(e){
        var ecode = e.which;
        var grupo = $(this).parents(".grupo").attr("id").replace("div-", "");
        if(ecode!=8 && ecode!=46){//
            $("#treino-b").addClass("tem-dados");
            $("#"+grupo).css("color","#000");
        }else{
            verificaInputs("treino-b");
        }
        validaTreino("-b");
    });
    $("#div-treino-c input[type=text]").keyup(function(e){
        var ecode = e.which;
        var grupo = $(this).parents(".grupo").attr("id").replace("div-", "");
        if(ecode!=8 && ecode!=46){//
            $("#treino-c").addClass("tem-dados");
            $("#"+grupo).css("color","#000");
        }else{
            verificaInputs("treino-c");
        }
        validaTreino("-c");
    });
    $("#div-treino-d input[type=text]").keyup(function(e){
        var ecode = e.which;
        var grupo = $(this).parents(".grupo").attr("id").replace("div-", "");
        if(ecode!=8 && ecode!=46){//
            $("#treino-d").addClass("tem-dados");
            $("#"+grupo).css("color","#000");
        }else{
            verificaInputs("treino-d");
        }
        validaTreino("-d");
    });
    $("#div-treino-e input[type=text]").keyup(function(e){
        var ecode = e.which;
        var grupo = $(this).parents(".grupo").attr("id").replace("div-", "");
        if(ecode!=8 && ecode!=46){//
            $("#treino-e").addClass("tem-dados");
            $("#"+grupo).css("color","#000");
        }else{
            verificaInputs("treino-e");
        }
        validaTreino("-e");
    });
    $("#div-treino-f input[type=text]").keyup(function(e){
        var ecode = e.which;
        var grupo = $(this).parents(".grupo").attr("id").replace("div-", "");
        if(ecode!=8 && ecode!=46){
            $("#treino-f").addClass("tem-dados");
            $("#"+grupo).css("color","#000");
        }else{
            verificaInputs("treino-f");
        }
        validaTreino("-f");
    });

    //    $(document).delegate("click",".mais",function(){
    //        $(this).removeClass("mais");
    //        $(this).addClass("menos");
    //        $("#div-" + $(this).attr("id")).show(TIME);
    //   });
    //----------------------------------------------------------------------
    $("#ordenar").click(function(event){
        event.preventDefault();
        if(!validaFichaCreate()){
            $("#status").val("ordenar");
            document.formFicha.submit();
        }
    });
    $("#salvar").click(function(event){
        event.preventDefault();
        if(!validaFichaCreate()){
            $("#status").val("success");
            document.formFicha.submit();
        }
    });
});

function verificaInputs(treino){
    var tem = false;
    $("#div-"+treino+" .grupo").each(function(){
        var grupo = this.id;
        var linkGrupo = (grupo.replace("div-", ""));
        var temGrupo = false;
        $("#" + grupo + " input[type=text]").each(function(){
            if($("#"+grupo+" input[name="+this.name+"]").val()!=''){
                tem = true;
                temGrupo=true;
            }
        });
        if(!temGrupo){
            $("#"+linkGrupo).removeAttr("style");
        }
    });
    if(!tem){
        $("#"+treino).removeAttr("style");
        $("#"+treino).removeClass("tem-dados");
        $("#"+treino).addClass("aba-ficha");
    }
}

function clearCampo(array){
    for (var i in array) {
        $("#"+array[i]).html('');
    }
}

var TEM_EXERCICIO = false;
function validaFichaCreate(){
    var ERRO = false;

    var array = ['treinoDias', 'periodoInicialError', 'periodoFinalError'];
    clearCampo(array);
    var divTreinoDias = "";
    
    var imgError = "<img src='images/alert.png' title='Campo Obrigatório!'/>";
    if($("#periodoInicial").val()=="" || !validaDat($("#periodoInicial").val())){
        $("#periodoInicialError").html("Informe data "+imgError);
        $("#periodoInicial").focus();
        ERRO = true;
    }
    if($("#periodoFinal").val()=="" || !validaDat($("#periodoFinal").val())){
        $("#periodoFinalError").html("Informe data "+imgError);
        if(!ERRO){
            $("#periodoFinal").focus();
        }
        ERRO = true;
    }
 
    if(validaDatasCreate()){
        if(!ERRO){
            $("#periodoFinal").focus();
        }
        ERRO = true;
    }

    if(($("#treinoDomingoValue").val()<=0)&&
        ($("#treinoSegundaValue").val()<=0)&&
        ($("#treinoTercaValue").val()<=0)&&
        ($("#treinoQuartaValue").val()<=0)&&
        ($("#treinoQuintaValue").val()<=0)&&
        ($("#treinoSextaValue").val()<=0)&&
        ($("#treinoSabadoValue").val()<=0)
        ){
        divTreinoDias = "Informe dia da semana para treino "+imgError;
        ERRO = true;
    }

    ERRO_TREINOS = false;
    TEM_EXERCICIO = false;
    for (var t in treinos) {
        var erroTreino = false;
        var treino = treinos[t];//"-a";
        erroTreino = validaTreino(treino,true);//Chama-se o show quando clicar no botao salvar
        if(erroTreino){
            ERRO_TREINOS = true;
            $("#treino"+treino).click();
            ERRO = true;
        }
    }
    //Se nao tem erro e nao exercicio
    if(ERRO == false && !TEM_EXERCICIO){
        if(divTreinoDias == ""){
            divTreinoDias = "Informe exercícios para treino "+imgError;
        }
        ERRO = true;
    }
 
    //verifica exercicios de cada treino e se tem radio radio checado. Retorna true se estiver OK
    var tA = verificaTreino("a", 1);
    var tB = verificaTreino("b", 2);
    var tC = verificaTreino("c", 3);
    var tD = verificaTreino("d", 4);
    var tE = verificaTreino("e", 5);
    var tF = verificaTreino("f", 6);
    if(TEM_EXERCICIO){
        if(!tA || !tB|| !tC|| !tD|| !tE|| !tF){//Se tA nao OK ou tB nao OK... entrara no erro
            if(divTreinoDias == ""){
                divTreinoDias = ("Informe dia dos treinos: "+((!tA?"A ":"")+(!tB?"B ":"")+(!tC?"C ":"")+(!tD?"D ":"")+(!tE?"E ":"")+(!tF?"F ":""))+imgError);
            }
            ERRO = true;
        }
    }
    var msg = verificaExerciciosParaRadio();
    if(msg!=""){
        if(divTreinoDias == ""){
            divTreinoDias = ("Informe exercícios para os treinos: "+ msg +imgError);
        }
        ERRO = true;
    }
    if(divTreinoDias == "" && ERRO_TREINOS){
        divTreinoDias = "Corriga os treinos abaixo "+imgError;
    }    
    $("#treinoDias").html(divTreinoDias);
    if(ERRO){
        $('html, body').animate({
            scrollTop:400
        }, 'slow');
    }
    
    return ERRO;
}

/*
 * Verifica se tem radio checado e nao tem exercicios informados para cada treino
 * e retorna uma mensagem diferente de vazio referente a cada exercicio
 */
function verificaExerciciosParaRadio(){
    var trenos = ['a','b','c','d','e','f'];
    var letras = ['A ','B ','C ','D ','E ','F '];

    var msg = "";
    var n = 1;
    for (var j in trenos) {
        var l = trenos[j];
        if(!$("#treino-"+l).hasClass("tem-dados")){
            if(($("#treinoDomingoValue").val()==n || $("#treinoSegundaValue").val()==n || $("#treinoTercaValue").val()==n ||
                $("#treinoQuartaValue").val()==n || $("#treinoQuintaValue").val()==n || $("#treinoSextaValue").val()==n ||
                $("#treinoSabadoValue").val()==n)){
                msg += letras[j];
            }
        }
        n++;
    }
    return msg;
}

function verificaTreino(t,n){
    if($("#treino-"+t).hasClass("tem-dados")){
        if(($("#treinoDomingoValue").val()==n || $("#treinoSegundaValue").val()==n || $("#treinoTercaValue").val()==n ||
            $("#treinoQuartaValue").val()==n || $("#treinoQuintaValue").val()==n || $("#treinoSextaValue").val()==n ||
            $("#treinoSabadoValue").val()==n)){
            return true;
        }
    }else{
        return true;
    }
    return false;
}

function validaDatasCreate(){
    $("#periodoMaior").html('');
    if(validaDat($("#periodoInicial").val()) && validaDat($("#periodoFinal").val())){
        var data1 = new Date(); 
        var data2 = new Date();

        var ini = $("#periodoInicial").val().split("/");
        var fim = $("#periodoFinal").val().split("/");

        if(ini.length==3){
            data1.setFullYear(ini[2],ini[1],ini[0]);
        }
        if(fim.length==3){
            data2.setFullYear(fim[2],fim[1],fim[0]);
        }
        if((ini.length==3)&&(fim.length==3)){
            if(data1.getTime() >= data2.getTime()) {
                $("#periodoFinalError").html('Informe data término superior ao início!');
                return true;
            }
        }
    }
    return false;
}

function validaTreino(treino){
    return validaTreino(treino, false);
}
function validaTreino(treino,showGrupoMuscular){
    var erroTreino = false;
    for (var g in GRUPOS) {
        var grupo = GRUPOS[g];
        var erro = validaGrupoMuscular(grupo,treino,showGrupoMuscular);
        //console.log("Erro- "+erro);
        //Só entra no if senao tiver encontrado algum erro
        if(erro){
            erroTreino= true;
        }
    }
    if(erroTreino){
        $("#treino"+treino).css('color', '#f00');
    }else{
        $("#treino"+treino).removeAttr("style");
    }
    return erroTreino;
}
function validaGrupoMuscular(grupo,treino,showGrupoComErro){
    var erroGrupo = false;
    $("#div-"+grupo+treino).find("tbody tr .inputCarga").map(function() {
        var carga = $(this).attr("name");
        var id = (carga.replace("carga"+treino, ""));
        var serie = id + "serie"+treino;
        var repeticao = id + "repeticao"+treino;
        
        if(($("input[name="+serie+"]").val()!="")
            ||($("input[name="+repeticao+"]").val()!="")
            ||($("input[name="+carga+"]").val()!="")){
            TEM_EXERCICIO = true;
        }
        
        if((($("input[name="+serie+"]").val()!="")&&($("input[name="+repeticao+"]").val()!="")&&($("input[name="+carga+"]").val()!=""))
            ||(($("input[name="+serie+"]").val()=="")&&($("input[name="+repeticao+"]").val()=="")&&($("input[name="+carga+"]").val()==""))){
            $("#"+id+"exercicio-error"+treino).removeClass("imgError");
        }else{
            $("#"+id+"exercicio-error"+treino).addClass("imgError");
            if(showGrupoComErro){
                $("#"+grupo+treino+'').removeClass("mais").addClass("menos");
                $("#div-"+grupo+treino+'').css('display', 'inline');
            }
            erroGrupo= true;
        }
    });
    return erroGrupo;
}

function eventoSelectRadio(name,n){
    var num = document.formFicha[name+"Value"].value;
    if(num == n){
        document.formFicha[name][n].checked = false;
        document.formFicha[name+"Value"].value = -1;
    }else{
        document.formFicha[name+"Value"].value = n;
    }
}
    
function eventoSelectRadioTreino(name,n){
    var num = document.formFicha[name+"Value"].value;
    if(num == n){
        document.formFicha[name][n-1].checked = false;
        document.formFicha[name+"Value"].value = -1;
    }else{
        document.formFicha[name+"Value"].value = n;
    }
}

function validaDat(valor) {
    var date=valor;
    var ardt=new Array;
    var ExpReg=new RegExp("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/[12][0-9]{3}");
    ardt=date.split("/");
    var erro=false;
    if ( date.search(ExpReg)==-1){
        erro = true;
    }
    else if (((ardt[1]==4)||(ardt[1]==6)||(ardt[1]==9)||(ardt[1]==11))&&(ardt[0]>30))
        erro = true;
    else if ( ardt[1]==2) {
        if ((ardt[0]>28)&&((ardt[2]%4)!=0))
            erro = true;
        if ((ardt[0]>29)&&((ardt[2]%4)==0))
            erro = true;
    }
    if (erro) {
        return false;
    }
    return true;
}
