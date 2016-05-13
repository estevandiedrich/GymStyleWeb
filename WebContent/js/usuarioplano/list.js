
$(document).ready(function(){
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
});


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
    var criterio= $("#criterioValue").val();
    if(criterio==-1){
        criterio = "";
    }else if(criterio==0){
        criterio = "planos";
    }else if(criterio==-1){
        criterio = "pendencias";
    }
    var p= $("#p").val();
    var orderBy= false;
    if(document.getElementById("orderBy").checked){
        orderBy = true;
    }
    if(p == undefined){
        p = 1;
    }
    $.ajax({
        url: "usuariosPlanoRead.do",
        type: "POST",
        data: {
            "criterioNome":criterioNome,
            "criterioCpf":criterioCpf,
            "criterioAtivoAluno":true,
            "criterioMatricula":criterioMatricula,
            "criterio":criterio,
            "pag.p":p,
            "orderBy":orderBy,
            "pag.currentPage":p
        },
        success: function(d){
            $("#content").html(d);
        }
    });
}