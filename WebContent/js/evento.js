
function EfeitoMsg(){
    if(document.getElementById("mensagem") != null){
        $("#mensagem").fadeIn();
        $("#mensagem").fadeOut();
    }
}
clearInterval(intervalo);
var intervalo = window.setInterval(function() {
    EfeitoMsg();
}, 2000);
window.setTimeout(function() {
    clearInterval(intervalo);
}, 3000);

        
function selecionado(){
    var sel = null;
    sel = document.getElementById("selecionado").value;
    if(sel!=null && sel != ""){
        //        document.getElementById(sel).style.background ='#FFF  url(images/ico_arrow_orange.gif) no-repeat left;';
        document.getElementById(sel).style.background ='#FFFFF0';
        document.getElementById(sel).style.color='#FF9600';
        document.getElementById(sel).style.color='#FF0000';
    }else{
        //        document.getElementById(sel).style.background ='#FFF  url(images/ico_arrow_grey.gif) no-repeat left;';
        if(sel != ""){
            if(document.getElementById(sel)!=null){
                document.getElementById(sel).style.color='#015b86';
            }
        }
    }
}

function efeitoMensagem(){
    if(document.getElementById("msg") != null){
        $("#msg").fadeIn();
        $("#msg").fadeOut();
    }
}

//Importar plano o no vincular plano
//function refreshImportarPlano(){
//    var id = $("#planoSelect").find('option').filter(':selected').val();
//    if(id==""){
//        $("#planoUsuarioForm").hide(900);
//    }else{
//        var val = "importarPlanoAjax.do?id="+id;
//        $.post(val, $(this).serialize(), function(d){
//            $("#planoUsuarioForm").html(d);
//            $("#planoUsuarioForm").show(600);
//        });
//    }
//    //para verificar se o plano esta selecionado e voltar a aparecer a divPagamentos
//    var opc = $("#planoSelect").find('option').filter(':selected').val();
//    if(opc != null && opc != ""){
//        $("#divPagamentos").show(300);
//    }
//}

function getValor(num){
    if(num == ""){
        return 0;
    }
    num = num.replace(" ","");
    num = num.replace(" ","");
    num = num.replace(" ","");
    num = num.replace(".","");
    num = num.replace(".","");
    num = num.replace(".","");
    num = num.replace(".","");
    num = num.replace(".","");
    num = num.replace(".","");
    num = num.replace(".","");
    num = num.replace(".","");
    //tem q colocar várias vezes para tirar ponto de ate um 1.000.000.000
    // o remove all nao funciona em alguns navegadores
    num = num.replace(",",".");
    num = num.replace(",",".");
    
    num = parseFloat(num);
    if(isNaN(num)){
        num = 0;
    }
    return (num);
}

function float2moeda(num) {
    x = 0;
    if(num<0) {
        num = Math.abs(num);
        x = 1;
    }
    if(isNaN(num)) num = "0";
    cents = Math.floor((num*100+0.5)%100);

    num = Math.floor((num*100+0.5)/100).toString();

    if(cents < 10) cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
        num = num.substring(0,num.length-(4*i+3))+'.'
        +num.substring(num.length-(4*i+3));
    ret = num + ',' + cents;
    if (x == 1) ret = ' - ' + ret;
    //    alert(ret);
    return ret;
}

//function formaDeDesconto(valor){
//    if(valor == 2){
//        $('#descontoUm').hide();
//        $('#descontoDois').show(700);
//    }else{
//        $('#descontoDois').hide();
//        $('#descontoUm').show(700);
//    }
//    $('#descontoReal').val("0,00");
//    $("#descontoPercentual").val("");
//}