
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
    if(!imprimir){
        imprimir = true;
        setTimeout(function(){
            submitFormRead();
            imprimir = false;
        }, 1200);
    }
}

function submitFormRead(){
    $("#processando").show(700);
    var criterioNome= $("#criterioNome").val();
    //var criterioMatricula= $("#criterioMatricula").val();
    var criterioCpf = $("#criterioCpf").val();
    var criterioAtivoFuncionario = true;
    if(document.getElementById("criterioAtivo").checked){
        criterioAtivoFuncionario = false;
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
        url: "funcionarioRead.do",
        type: "POST",
        data: {
            "criterioNome":criterioNome,
            "criterioCpf":criterioCpf,
            //"criterioMatricula":criterioMatricula,
            "criterioAtivoFuncionario":criterioAtivoFuncionario,
            "criterioTipoUsuario":"funcionario",//Tipo Usuario
            "pag.p":p,
            "orderBy":orderBy,
            "pag.currentPage":p
        },
        success: function(d){
            $("#content").html(d);
        }
    });
}