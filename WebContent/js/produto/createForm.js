
$(document).ready(function(){
    $("#valorVenda").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    })
    $("#valorCusto").maskMoney({
        symbol:"R$",
        decimal:",",
        thousands:"."
    })
    $("#observacao").css("maxWidth","500px");
    $("#observacao").css("maxHeight","100px");
    $("#observacao").css("minWidth","500px");
    $("#observacao").css("minHeight","100px");
    //        $(".inputTime").mask("99:99");        

//    $(".mais").click(function(){
//        if($(this).hasClass("mais")){
//            $(this).removeClass("mais");
//            $(this).addClass("menos");
//            $("#div-" + $(this).attr("id")).show(TIME);
//        }else{
//            $(this).removeClass("menos");
//            $(this).addClass("mais");
//            $("#div-" + $(this).attr("id")).hide(TIME);
//        }
//    });

});

function somenteNumero(e){
    var tecla=(window.event)?event.keyCode:e.which;
    if((tecla>47 && tecla<58)) return true;
    else{
        if (tecla==8 || tecla==0) return true;
        else  return false;
    }
}  