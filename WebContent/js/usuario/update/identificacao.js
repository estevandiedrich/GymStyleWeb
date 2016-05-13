$(document).ready(function(){

    $("#btCapturar").unbind().click(function(){
        var val = "digitalAjax.read.do?id="+$("#id").val();
        $.post(val, $(this).serialize(), function(d){
            $("#corpoDigitais").html(d);
        });
        //return false;
    });

//    var varDigitais = 1;
//    $("#eventoDigitais").click(function(event){
//        event.preventDefault();
//        if(varDigitais == 2){
//            varDigitais = 1;
//            $("#digitais").show(700);
//            $('#eventoDigitais').removeClass('mais').addClass('menos');
//        }else {
//            varDigitais = 2;
//            $("#digitais").hide("slow");
//            $('#eventoDigitais').removeClass('menos').addClass('mais');
//        }
//        document.getElementById("varCatraca").value = varCatraca;
//    });

    $('#cartaoProximidade').keypress(function(event) {
        var tecla = (window.event) ? event.keyCode : event.which;
        if ((tecla > 47 && tecla < 58)) return true;
        else {
            if (tecla != 8) return false;
            else return true;
        }
    });
});
