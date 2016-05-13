$(document).ready(function(){
    $("#funcionarioLabel").hide();
    
    $("#criterioCpf").mask("999.999.999-99");
    
    $("#criterioMatricula").keydown(function(event){
        /* Allow backspace, delete, tab, esc e enter */
        if ( event.keyCode == 46 || event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 27 || event.keyCode == 13 || 
            /* Allow CTRL+A */
            (event.keyCode == 65 && event.ctrlKey === true) || 
            /* Allow CTRL+C */
            (event.keyCode == 67 && event.ctrlKey === true) || 
            /* Allow CTRL+X */
            (event.keyCode == 88 && event.ctrlKey === true) || 
            /* Allow CTRL+V */
            (event.keyCode == 86 && event.ctrlKey === true) || 
            /* Allow Command+A (Mac) */
            (event.keyCode == 65 && event.metaKey === true) || 
            /* Allow Command+C (Mac) */
            (event.keyCode == 67 && event.metaKey === true) || 
            /* Allow Command+X (Mac) */
            (event.keyCode == 88 && event.metaKey === true) || 
            /* Allow Command+V (Mac) */
            (event.keyCode == 86 && event.metaKey === true) || /* Allow home, end, left e right keys */
            (event.keyCode >= 35 && event.keyCode <= 39)) {
    			
            /* Boo */
            return;
        }else {
            /* Stop key press */
            if (event.shiftKey || (event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105 )) {
                event.preventDefault(); 
            }   
        }
    });
    $("#funcionarioRadio").click(function(e){
        $("#labelMatricula").hide(500);
        $("#criterioMatricula").hide(500);
    });
    $("#alunoCheck").click(function(e){
        $("#labelMatricula").show(500);
        $("#criterioMatricula").show(500);
    });
});

function eventoAluno(){
//    $("#criterioMatricula").show(700);
//document.getElementById("var1").value = 1;
};
function eventoFuncionario(){
//    $("#criterioMatricula").hide(700);
//document.getElementById("var1").value = 2;
};

function submitFormFiltrar(p){
    if(p!=""){
        $("#p").val(p);
    }
    submitFormRead();
}
    
var imprimir = false;
function consultaNome(){
    imprimir = true;
    setTimeout(function(){
        if(imprimir == true){
            //console.log(document.querySelector('#criterioNome').value);submitFormRead();
            submitFormRead();
        }
        imprimir = false;
    }, 1000);
}
    
function submitFormRead(){
    $("#processando").show(700);
    var criterioNome= $("#criterioNome").val();
    var criterioCpf= $("#criterioCpf").val();
    var criterioMatricula= $("#criterioMatricula").val();
    var criterioNaoSincronizado= false;
    if(document.getElementById("criterioNaoSincronizado").checked){
        criterioNaoSincronizado = true;
    }
    var p= $("#p").val();
    if(p == undefined){
        p = 1;                
    }
    var orderBy= false;
    if(document.getElementById("orderBy").checked){
        orderBy = true;
    }
    var consultaAluno = true;
    if(!document.getElementById("alunoCheck").checked){
        consultaAluno = false;
    }

    if(!consultaAluno){
        criterioMatricula = "";
    }
    
    $.ajax({
        url: "requisicaoRead.do",
        type: "POST",
        data: {
            "criterioNome":criterioNome,
            "criterioAtivos":"true",
            "criterioCpf":criterioCpf,
            "consultaAluno":consultaAluno,
            "criterioUsuarioAtivo":true,
            "criterioMatricula":criterioMatricula,
            "criterioNaoSincronizado":criterioNaoSincronizado,
            "pag.p":p,
            "orderBy":orderBy,
            "pag.currentPage":p
        },
        success: function(d){
            $("#content").html(d);
        }
    });
}