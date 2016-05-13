$(document).ready(function(){
    $(".inputTime").click(function(event){
        event.preventDefault();
        document.getElementById("mudouFaixas").value = "true";
    });
    
    $("input[name='dispositivo']").change(function(event){
        event.preventDefault();
        document.getElementById("mudouFaixas").value = "true";
    });
    
    var temAcesso = document.getElementById("temAcesso").checked;
    if(temAcesso){
        mostraAcesso(1);
    }else{
        mostraAcesso(2);
    }
        
    var livre = document.getElementById("livre").value;
    if(livre=="false"){
        $("#tabelaFaixas").slideDown(700);
        $('#abaConfigurado').removeClass('aba').addClass('abaCurrent');
        $('#abaLivre').removeClass('abaCurrent').addClass('aba');
    }
        
    $(".inputTime").mask("99:99");
    
});

function mostraAcesso(opcao){
    var TIME = 500;
    if(opcao == 1){
        $("#acessoDiv").slideDown(TIME);
    }else{
        $("#acessoDiv").slideUp(TIME);
    }
}

function mostraTabelaFaixas(opcao){
    var TIME = 500;
    if(opcao == 1){
        $("#tabelaFaixas").slideDown(TIME);
        $('#abaConfigurado').removeClass('aba').addClass('abaCurrent');
        $('#abaLivre').removeClass('abaCurrent').addClass('aba');
        $("#livre").val(false);
    }else{
        $("#tabelaFaixas").slideUp(TIME);
        $('#abaLivre').removeClass('aba').addClass('abaCurrent');
        $('#abaConfigurado').removeClass('abaCurrent').addClass('aba');
        $("#livre").val(true);
    }
}
    
function validaForm(){
    //dentro da aba plano
    //dentro do vincular plano, dentro da pasta plano
    // se existe parcelas em branco, não dá submit
    var tamanho = parseInt($("#tamanhoLista").val());
    var opcao = parseInt($("#periodoSelect").val());
    val = true;
    if(!isNaN(tamanho)){
        if(opcao == 2){
            opcao = 1;
        }//Diario semanal
        if(opcao == 3 || opcao == 4){
            opcao = 2;
        }//Mensal e Bimestral 
        if(opcao == 5){
            opcao = 3;
        }//Tri
        if(opcao == 6){
            opcao = 4;
        }//Quadri
        if(opcao == 7){
            opcao = 6;
        }//sem
        if(opcao == 8){
            opcao = 12;
        }//Anual
        if(tamanho != opcao){
            var val = false;
            $("#errorDuracao").html("<font class='errors'>Parcelas não batem de acordo com o Plano escolhido, pressione Gerar Pagamentos Novamente!<img src='images/alert.png' title='Campo Obrigatório!'/></font>");
            $("#errorDuracao").show();
        }

        for(var i = 0;i<= tamanho;i++){
            if($("#valorTotal"+i).val()==""){
                $("#error"+i).html("<font class='errors'>Campo Obrigatório<img src='images/alert.png' title='Campo Obrigatório!'/></font>");
                val = false;
            }
        }
    }
    if(val){
        document.form.submit();
    }
}
