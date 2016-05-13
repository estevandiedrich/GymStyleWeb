function submitRelatorioFormCaixaRead(){
    var criterioMes= $("#criterioMes").val();
    var criterioAno= $("#criterioAno").val();

    var submit = true;
    if($("#criterioAno").val()==""){
        $("#anoError").show(500);
        submit = false;
    }else{
        $("#anoError").hide(500);
    }
    
    if($("#criterioMes").val()==""){
        $("#mesError").show(500);
        submit = false;
    }else{
        $("#mesError").hide(500);
    }
    if(submit){

        $.ajax({
            url: "caixaReportRead.do",
            type: "POST",
            data: {
                "criterioAno":criterioAno,
                "criterioMes":criterioMes
            },
            success: function(d){
                $("#content").html(d);
                $("#content").fadeIn(1000).slideDown(1800);
            }
        });
    }
}

function submitRelatorioFormCaixaReadReport(){
    //Não precisa passar os parametros pq o submit ja reenvia todos
    $("#anoError").hide(200);
    $("#mesError").hide(200);
    var submit = true;
    if($("#criterioAno").val()==""){
        $("#anoError").show(500);
        submit = false;
    }
    if($("#criterioMes").val()==""){
        $("#mesError").show(500);
        submit = false;
    }
    if(submit){
        document.relatorioCaixaForm.action = "caixaReportPDF.do";
        document.relatorioCaixaForm.target='_blank';
        document.relatorioCaixaForm.submit();
    }
}

function mostraLinha(dia){
    var linha = $("#linha"+dia);
    var label = $("#label"+dia);
    if (label.is (".menos")) {
        linha.fadeOut(1000).slideUp(1800);
        label.removeClass('menos').addClass('mais');
    }else{
        linha.fadeIn(1000).slideDown(1800);
        label.removeClass('mais').addClass('menos');
        readCaixas(dia);
    }
}

function readCaixas(dia){
    var criterioMes= $("#criterioMes").val();
    var criterioAno= $("#criterioAno").val();
    var data = "";
    if(($("#criterioAno").val()!="") && ($("#criterioMes").val()!="")){        
        data = dia+"/"+criterioMes+"/"+criterioAno;
        $.ajax({
            url: "readCaixasAjax.do",
            type: "POST",
            data: {
                "criterioDia":data,
                "dia":dia
            },
            success: function(d){
                $("#caixas"+dia).html(d);
                $("#caixas"+dia).fadeIn(1000).slideDown(1800);
            }
        });
    }
}

function mostraCaixa(id,dia){
    var mes= $("#criterioMes").val();
    var ano= $("#criterioAno").val();

    var linha = $("#caixa"+id);
    var label = $("#labelCaixa"+id);
    if (label.is (".menos")) {
        linha.fadeOut(1000).slideUp(1800);
        label.removeClass('menos').addClass('mais');
    }else{
        //   linha.fadeIn(1000).slideDown(1800);
        label.removeClass('mais').addClass('menos');
        readCaixaById(id,ano,mes,dia);
    }
}

function readCaixaById(id,ano,mes,dia){
    $.ajax({
        url: "readCaixaAjax.do",
        type: "POST",
        data: {
            "dia":dia,
            "mes":mes,
            "ano":ano,
            "id":id
        },
        success: function(d){
            //alert(d);
            $("#caixa"+id).html(d);
            $("#caixa"+id).fadeIn(1000).slideDown(1800);
        }
    });
}

