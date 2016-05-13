function submitFormFiltrar(p){
    if(p!=""){
        $("#p").val(p);
    }
    submitFormRead();
}
function submitFormRead(){
    $("#processando").show(700);
    var criterioJustificativa= $("#criterioJustificativa").val();
    var p= $("#p").val();
    var orderBy= false;
    if(document.getElementById("orderBy").checked){
        orderBy = true;
    }
    if(p == undefined){
        p = 1;                
    }
    $.ajax({
        url: "liberarRead.do",
        type: "POST",
        data: {
            "criterioJustificativa":criterioJustificativa,
            "pag.p":p,
            "orderBy":orderBy,
            "pag.currentPage":p
        },
        success: function(d){
            $("#content").html(d);
        }
    });
}