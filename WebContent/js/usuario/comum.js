/*Aluno*/
function gera_random(n){
    var ranNum = Math.round(Math.random()*n);
    return ranNum;
}
function mod(dividendo,divisor){
    return Math.round(dividendo - (Math.floor(dividendo/divisor)*divisor));
}
function cpf(){
    var n = 9;
    var n1 = gera_random(n);
    var n2 = gera_random(n);
    var n3 = gera_random(n);
    var n4 = gera_random(n);
    var n5 = gera_random(n);
    var n6 = gera_random(n);
    var n7 = gera_random(n);
    var n8 = gera_random(n);
    var n9 = gera_random(n);
    var d1 = n9*2+n8*3+n7*4+n6*5+n5*6+n4*7+n3*8+n2*9+n1*10;
    d1 = 11 - ( mod(d1,11) );
    if (d1>=10) d1 = 0;
    var d2 = d1*2+n9*3+n8*4+n7*5+n6*6+n5*7+n4*8+n3*9+n2*10+n1*11;
    d2 = 11 - ( mod(d2,11) );
    if (d2>=10) d2 = 0;
    return ''+n1+n2+n3+'.'+n4+n5+n6+'.'+n7+n8+n9+'-'+d1+d2;
}
    
function gerarCpf(){
    $("#cpf").val(cpf());
    atualizaInfoCpf($("#cpf").val());
    verificaCpf(cpf);
}
    
function atualizaInfoCpf(cpf){
    $(".infoCpfLabel").html(cpf);
    $(".infoCpf").val(cpf);
}

/*Aluno*/
function verificaCpf(cpf,tipo){
    for (var i=0;i<11;i++){
        cpf = cpf.replace("_", "");
    }
    var time = 700;
    if(cpf.toString().length >= 13){
        var val = "verificaCpf.do?cpf="+cpf+"&tipo="+tipo;
        $.post(val, $(this).serialize(), function(r){
            $("#resultCpf").html(r);
            $("#resultCpf").hide().slideDown(time);
        });
    }
}
    
function verificaMatricula(matricula){
    var val = "verificaMatricula.do?matricula="+matricula;
    var id = null;
    if((document.getElementById("id"))!=null){
        id = document.getElementById("id").value;
        val += "&id="+id;
    }
    var time = 700;
    $.post(val, $(this).serialize(), function(r){
        $("#resultMatricula").html(r);
        $("#resultMatricula").hide().slideDown(time);
    //.slideDown(TIME);
    //$("#detalhesConta").slideUp(TIME);
    });
}
function somenteNumero(e){
    var tecla=(window.event)?event.keyCode:e.which;
    if((tecla>47 && tecla<58)) return true;
    else{
        if (tecla==8 || tecla==0) return true;
        else return false;
    }
}
function eventoTipoUsuario(){
    var tipo = document.getElementById("idTipoUsuario").value;
    if(tipo != 2){
        $("#autenticacaoDiv").show(700);
    }else{
        $("#autenticacaoDiv").hide(700);
    }
}   

function verificaLogin(login){
    $("#loginAux").val(login);
    var loginUsuario = document.getElementById("loginUsuario").value;
    var idUsuario = "";
    if($("#id").val()!= undefined){
        idUsuario = $("#id").val();
    }
    var val = "verificaLogin.do?login="+login
    +"&loginUsuario="+loginUsuario
    +"&idUsuario="+idUsuario;
    $.post(val, $(this).serialize(), function(r){
        $("#resultLogin").html(r);
        $("#resultLogin").show(700);
    });
}

function eventoCapturarFoto(){
    document.getElementById("image").style.zIndex="1";
    document.getElementById("image").click();
}
function eventoMudarFoto(){
    document.eventoFotoForm.submit();
}
