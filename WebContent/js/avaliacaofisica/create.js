$(document).ready(function(){
    abrirImagem('peitoral');
    abrirImagem('supraIliaca');
    abrirImagem('subescapular');
    abrirImagem('tricipital');
    abrirImagem('abdominal');
    abrirImagem('coxa');
    abrirImagem('panturrilha');
    //alert("Antes");
    $("#peso").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    })
    $("#altura").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    })
    $("#bracoDireito").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
    $("#bracoEsquerdo").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
    $("#coxaDireita").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
    $("#coxaEsquerda").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
    $("#panturrilhaDireita").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
    $("#panturrilhaEsquerda").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
    $("#torax").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
    $("#quadril").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
    $("#cintura").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
    $("#abdomen").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
    $("#subescapular").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
    $("#tricipital").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
    $("#peitoral").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
    $("#abdominal").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
    $("#supraIliaca").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
    $("#coxa").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
    $("#panturrilha").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
    $("#axilarMedia").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    });
        
    $("#dataProximaAvaliacao").mask("99/99/9999");
    $("#peso").focus();
    document.getElementById('gorduraAtual').readOnly = true;
    document.getElementById('gorduraIdeal').readOnly = true;
    document.getElementById('massaMagra').readOnly = true;
    document.getElementById('massaGorda').readOnly = true;
    $("#descricao").css("maxWidth","500px" );
    $("#descricao").css("maxHeight","100px");
    $("#descricao").css("minWidth","500px");
    $("#descricao").css("minHeight","100px");
});
function abrirImagem(nome){
    var val = document.getElementById(nome+"Estado").value;
    if(val == 2){
        $("#"+nome+"Image").show(700);
    }
}
    
function eventoDobraCutanea(nome){
    var val = document.getElementById(nome+"Estado").value;
    if(val == 1){
        $("#"+nome+"Image").show(700);
        document.getElementById(nome+"Estado").value = 2;
    }else{
        $("#"+nome+"Image").hide(700);
        document.getElementById(nome+"Estado").value = 1;
    }
}

function validaCampos(){
    verificaCampo("peso");
    verificaCampo("altura");
    verificaCampo("subescapular");
    verificaCampo("tricipital");
    verificaCampo("peitoral");
    verificaCampo("abdominal");
    verificaCampo("supraIliaca");
    verificaCampo("coxa");
    //        verificaCampo("panturrilha");
    verificaCampo("axilarMedia");
}
    
function verificaCampo(nome){
    if($("#"+nome).val()==""){
        $("#"+nome+"Error").html("<img src='images/alert.png' title='Campo Obrigatório!'/>"); 
    }
}

function gerarComposicaoCorporal(){
    if($("#validado").val()== ""){
        validaCampos();
    }
    var subescapular = getValor($("#subescapular").val());
    var tricipital = getValor($("#tricipital").val());
    var peitoral = getValor($("#peitoral").val());
    var abdominal = getValor($("#abdominal").val());
    var supraIliaca = getValor($("#supraIliaca").val());
    var coxa = getValor($("#coxa").val());
    var panturrilha = getValor($("#panturrilha").val());
    var axilarMedia = getValor($("#axilarMedia").val());

    var protocolo = 0;
    if (document.formAvaliacao.protocolo[0].checked == true){
        protocolo = document.formAvaliacao.protocolo[0].value;
    }
    if (document.formAvaliacao.protocolo[1].checked == true){
        protocolo = document.formAvaliacao.protocolo[1].value;
    }
        
    var idade = getValor($("#idade").val());
    var peso = getValor($("#peso").val());
    var altura = getValor($("#altura").val());
    var sexo = $("#sexo").val();
        
    calculaImc(peso, altura, sexo);
    if(protocolo==1){        
        calculoPollock3Dobras(idade, peso, sexo, tricipital, supraIliaca, coxa);
    }else if(protocolo==2){
        calculoPollock7Dobras(idade, peso, sexo, tricipital, subescapular, supraIliaca, abdominal, axilarMedia, peitoral, coxa);
    }
}
 
function calculoPollock7Dobras(idade,peso,sexo,tricipital,subescapular,supraIliaca,abdominal,axilarMedia,peitoral,coxa){
    var tresDobras = tricipital+supraIliaca+coxa;
    var seteDobras = tricipital+subescapular+supraIliaca+abdominal+axilarMedia+peitoral+coxa;
    var gorduraAtual = 0;
    var massaMagra = 0;
    var massaGorda = 0;
    var gorduraAbsoluta = 0;
    var DC = 0;
    if(sexo == "M"){
        //DC = 1.112 - (0.00043499*seteDobras) + (0.00000055 * (tresDobras*tresDobras)) - (0.00012882*idade);
        DC = 1.11200000 - (0.00043499*seteDobras + 0.00000055 * (seteDobras*seteDobras)) - (0.00012882*idade);
    }else{
        //DC = 1.097 - (0.0004697*seteDobras) + (0.00000056 * (tresDobras*tresDobras)) - (0.00012828*idade);
        DC = 1.097 - ((0.0004697*seteDobras) + (0.00000056 * (seteDobras*seteDobras))) - (0.00012828*idade);
    }
    gorduraAtual = ((4.95/DC)-4.5)*100;
    if(gorduraAtual < 0){
        gorduraAtual = 0;
    }
    $("#gorduraAtual").val(float2moeda(gorduraAtual));
    gorduraAbsoluta = (gorduraAtual/100)

    massaGorda = peso * gorduraAbsoluta;
    massaMagra = peso - massaGorda;

    $("#massaMagra").val(float2moeda(massaMagra));
    $("#massaGorda").val(float2moeda(massaGorda));
        
    var gorduraIdeal = "";
    if(idade >= 18 && idade <=25){
        if (sexo == "M"){
            gorduraIdeal = "8 a 10%";
        }else{
            gorduraIdeal = "17 a 19%";
        }
    }else if(idade >= 26 && idade <=35){
        if (sexo == "M"){
            gorduraIdeal = "12 a 15%";
        }else{
            gorduraIdeal = "18 a 20%";
        }
    }else if(idade >= 36 && idade <=45){
        if (sexo == "M"){
            gorduraIdeal = "16 a 18%";
        }else{
            gorduraIdeal = "20 a 23%";
        }
    }else if(idade >= 46 && idade <=55){
        if (sexo == "M"){
            gorduraIdeal = "18 a 20%";
        }else{
            gorduraIdeal = "23 a 25%";
        }
    }else if(idade >= 56 && idade <=65){
        if (sexo == "M"){
            gorduraIdeal = "20 a 21%";
        }else{
            gorduraIdeal = "24 a 26%";
        }
    }else if(idade < 18){
        gorduraIdeal = "Idade menor que 18 anos.";
    }
    $("#gorduraIdeal").val(gorduraIdeal);        
}
 
function calculaImc(peso,altura,sexo){
    var imc = peso / (altura * altura);
    var result = "";
    if(sexo == "M"){
        if(imc < 20.7){
            result = " - Abaixo do peso.";
        }else if(imc >= 20.7 && imc <26.4){
            result = " - Peso normal.";
        }else if(imc >= 26.4 && imc <27.8){
            result = " - Pouco acima do peso.";
        }else if(imc >= 27.8 && imc <31.1){
            result = " - Acima do peso ideal.";
        }else if(imc >= 31.1){
            result = " - Obeso.";
        }
    }else{
        if(imc <19.1){
            result = " - Abaixo do peso.";
        }else if(imc >= 19.1 && imc <25.8){
            result = " - Peso normal";
        }else if(imc >= 25.8 && imc <27.3){
            result = " - Pouco acima do peso.";
        }else if(imc >= 27.3 && imc <32.3){
            result = " - Acima do peso ideal.";
        }else if(imc >= 32.3){
            result = " - Obeso";
        }
    }
    $("#imcLabel").html(float2moeda(imc)+ result);
    $("#imcValue").val(float2moeda(imc)+ result);
    $("#imc").val(imc);        
}
 
function calculoPollock3Dobras(idade,peso,sexo,triceps,supraIliaca,coxa){
    var tresDobras = triceps+supraIliaca+coxa;
    var gorduraAtual = 0;
    var massaMagra = 0;
    var massaGorda = 0;
    var gorduraAbsoluta = 0;
    var DC = 0;

    if(sexo == "M"){
        DC = 1.1093800 - (0.0008267*tresDobras) + (0.0000016 * (tresDobras*tresDobras)) - (0.0002574*idade);
    }else{
        DC = 1.0994921 - (0.0009929*tresDobras) + (0.0000023 * (tresDobras*tresDobras)) - (0.0001393*idade);
    }
        
    gorduraAtual = ((4.95/DC)-4.5)*100;

    if(gorduraAtual < 0){
        gorduraAtual = 0;
    }
    $("#gorduraAtual").val(float2moeda(gorduraAtual));
    gorduraAbsoluta = (gorduraAtual/100)

    massaGorda = peso * gorduraAbsoluta;
    massaMagra = peso - massaGorda;

    $("#massaMagra").val(float2moeda(massaMagra));
    $("#massaGorda").val(float2moeda(massaGorda));
        
    var gorduraIdeal = "";
    if(idade >= 18 && idade <=25){
        if (sexo == "M"){
            gorduraIdeal = "8 a 10%";
        }else{
            gorduraIdeal = "17 a 19%";
        }
    }else if(idade >= 26 && idade <=35){
        if (sexo == "M"){
            gorduraIdeal = "12 a 15%";
        }else{
            gorduraIdeal = "18 a 20%";
        }
    }else if(idade >= 36 && idade <=45){
        if (sexo == "M"){
            gorduraIdeal = "16 a 18%";
        }else{
            gorduraIdeal = "20 a 23%";
        }
    }else if(idade >= 46 && idade <=55){
        if (sexo == "M"){
            gorduraIdeal = "18 a 20%";
        }else{
            gorduraIdeal = "23 a 25%";
        }
    }else if(idade >= 56 && idade <=65){
        if (sexo == "M"){
            gorduraIdeal = "20 a 21%";
        }else{
            gorduraIdeal = "24 a 26%";
        }
    }else if(idade < 18){
        gorduraIdeal = "Idade menor que 18 anos.";
    }
    $("#gorduraIdeal").val(gorduraIdeal);
        
}
    