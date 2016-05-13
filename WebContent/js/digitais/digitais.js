$(document).ready(function(){
    refreshDigitais();
});


function eventoSelectMao(){
    var val = document.getElementById("dedo").value;
    if(val == "Indicador Direito"){
        document.getElementById("mao").src="images/maoDirInd.png";
    }else if(val == "Polegar Direito"){
        document.getElementById("mao").src="images/maoDirPol.png";
    }else if(val == "Médio Direito"){
        document.getElementById("mao").src="images/maoDirMed.png";
    }else if(val == "Mínimo Direito"){
        document.getElementById("mao").src="images/maoDirMin.png";
    }else if(val == "Anelar Direito"){
        document.getElementById("mao").src="images/maoDirAne.png";
    }else if(val == "Polegar Esquerdo"){
        document.getElementById("mao").src="images/maoEsqPol.png";
    }else if(val == "Indicador Esquerdo"){
        document.getElementById("mao").src="images/maoEsqInd.png";
    }else if(val == "Médio Esquerdo"){
        document.getElementById("mao").src="images/maoEsqMed.png";
    }else if(val == "Mínimo Esquerdo"){
        document.getElementById("mao").src="images/maoEsqMin.png";
    }else if(val == "Anelar Esquerdo"){
        document.getElementById("mao").src="images/maoEsqAne.png";
    }else {
        document.getElementById("mao").src="images/mao.png";
    }
}

function returnIdDedo(val){
    if(val == "Polegar Direito"){
        return 1;
    }else if(val == "Indicador Direito"){
        return 2;
    }else if(val == "Médio Direito"){
        return 3;
    }else if(val == "Anelar Direito"){
        return 4;
    }else if(val == "Mínimo Direito"){
        return 5;
    }else if(val == "Polegar Esquerdo"){
        return 6;
    }else if(val == "Indicador Esquerdo"){
        return 7;
    }else if(val == "Médio Esquerdo"){
        return 8;
    }else if(val == "Anelar Esquerdo"){
        return 9;
    }else if(val == "Mínimo Esquerdo"){
        return 10;
    }else return 0;
}

function capturarDigital(){
    //Selecionado via hamister
    if(document.getElementsByName("opcao").item(0).checked){
        $("#templatesApplet").val($("#templates").val());
        // a url é setada no refreshDivHamister
        $("#ramisterDiv").show();
    }else if(document.getElementsByName("opcao").item(1).checked){
        //Selecionado via catraca
        $("#ramisterDiv").hide("slow");

        var tam = document.getElementsByName("dispositivo").length;
        var idDispositivo = null;
        if(tam>0){
            for (i = 0; i < tam; i++){
                if(document.getElementsByName("dispositivo").item(i).checked){
                    idDispositivo = document.getElementsByName("dispositivo").item(i).valueOf().value;
                    break;
                }
            }
        }
        if(idDispositivo != null){
            var posicao = document.getElementById("dedo").selectedIndex;
            var conteudo = document.getElementById("dedo").options;
            var idDedo = returnIdDedo(conteudo[posicao].text);
            if(idDedo==0){
                alert("Selecione dedo!");
            }else{
                //chamando o botao capturar, disabilita o butão.
                document.getElementById("btCapturar").disabled = true;
                $("#btCapturar").removeClass('botao').addClass('botaoDes');
                var val = "digitalAjax.create.do?id="+$("#id").val()+"&idDispositivo="+ idDispositivo+"&idDedo="+ idDedo;
                $.post(val, $(this).serialize(), function(d){
                    $("#retorno").html(d);
                    $("#retorno").show(500);
                    $("#carregando").hide(500);
                    atualizaMudouDigitais();
                    //Após o retorno atualizar o SelectDedos
                    //==========================================================================
                    selectDedos();
                    //}
                    document.getElementById("btCapturar").disabled = false;
                    $("#btCapturar").removeClass('botaoDes').addClass('botao');
                        
                });

                $("#retorno").hide(500);
                $("#carregando").show(500);
            }
        }else{
            if(document.getElementsByName("dispositivo").length < 1){
                alert("É necessário cadastrar catracas!");
            }else{
                alert("Selecione a catraca!");
            }
        }
    }else {
        alert("Selecione a opção!");
    }
}
    
    
//Evento dentro do select opção de capturar digitais
//Toda vez que selecionar uma opção, será chamado um ajax para atualizar o applet ou o atualizar o select do usuário
function eventoSelectOpcao(){
    if(document.getElementsByName("opcao").item(0).checked){
        $("#catracas").hide("slow");
        refreshDivHamister();
    }else{
        $("#ramisterDiv").hide("slow");
        $("#catracas").show(700);
        selectDedos();
    }
}

function fecharRetorno(){
    $("#retorno").hide(500);
}

var contador = 3;
function refreshAtualizarTabelaDigitais() {
    if(contador == 0) {
        //Se catraca selecionado executa o refresh
        if(document.getElementsByName("opcao").item(1).checked){
            refreshDigitais();
        }
        contador = 3
    }
    if (contador != 0){
        contador = contador-1;
        setTimeout("refreshAtualizarTabelaDigitais()", 600);
    }
}
refreshAtualizarTabelaDigitais();

function refreshDivHamister(){
    var val = "buscaAppletAjax.do";
    $.post(val, $(this).serialize(), function(d){
        $("#ramisterDiv").html(d);
    });
    var loc = document.URL;
    var pathName = loc.substring(0, loc.lastIndexOf('/') + 1);
    //fazer o parametro pra baixar as dll local
    $("#urlApplet").val(pathName+"applet/dlls.zip");
    console.log(pathName+"applet/dlls.zip");
}

function refreshDigitais(){
    
    var val = "digitalAjax.read.do?id="+$("#id").val();
    $.post(val, $(this).serialize(), function(d){
        $("#corpoDigitais").html(d);
    });
    atualizaTemplates();
}
    
function atualizaTemplates(){
    var val = "digitaisUsuarioAjax.do?criterioUsuario="+$("#id").val();
    $.post(val, $(this).serialize(), function(d){
        //remove os espaços por completo
        d = d.replace(/^\s+|\s+$/g,"");
        $("#templates").val(d);
    });
}

function selectDedos(){
    var val = "selectDedosAjax.do?idUsuario="+$("#id").val();
    $.post(val, $(this).serialize(), function(d){
        $("#selectDigitais").html(d);
        document.getElementById("mao").src="images/mao.png";
    });
}
    
//metodo acessado pelo applet em seu retorno
function setTemplates(templates) {
    //    console.log("Retorno do applet - "+templates);
    //verificação acaso o retorno foi cancelado no cadastrar digitais
    if((templates== null || templates == "") && ($("#templates").val() != null && $("#templates").val() !="")){
    //            if(confirm("Deseja apagar todas as digitais")){
    //                $("#templates").val(templates);
    //                createDigital();
    //            }
    }else{
        $("#templates").val(templates);
        createDigital();
        atualizaMudouDigitais();
    }
    $("#ramisterDiv").hide("slow");
}

function createDigital(){
    var idUsuario= $("#id").val();
    var templates= $("#templates").val();
    $.ajax({
        url: "digitalCreateAjax.do",
        type: "POST",
        data: {
            "idUsuario":idUsuario,
            "templates":templates
        },
        success: function(d){
            //$("#content").html(d);
            refreshDigitais();
        }
    });
}
function resetOpcao(){
    document.getElementsByName("opcao").item(0).checked = false;
    document.getElementsByName("opcao").item(1).checked = false;
    $("#ramisterDiv").hide("slow");
    $("#catracas").hide("slow");
}
   
function deleteDigital(idDelete){
    var val = "digitalAjax.delete.do?idDelete="+idDelete+"&id="+$("#id").val();
    decisao = confirm("Deseja realmente remover esta digital?");
    if (decisao){
        $.post(val, $(this).serialize(), function(d){
            $("#corpoDigitais").html(d);
            //resetOpcao();
            refreshDigitais();
            atualizaMudouDigitais();
        });
    }
}
//Servirá para redirecionar acaso for diferente de vazio.
function atualizaMudouDigitais(){
    $("#mudouDigitais").val("TRUE");
}