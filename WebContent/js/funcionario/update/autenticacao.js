
TIME = 500;
$(document).ready(function(){

    $(".aba-autenticacao").click(function(){
        $(".aba-autenticacao").removeClass("current-autenticacao");
        $(this).addClass("current-autenticacao");
        $(".div-autenticacao").hide(TIME);
        $("#div-" + $(this).attr("id")).show(TIME);
    });

    $("#chek-todos").click(function(e){
        var ecode = e.which;
        var chek = false;
        if($(this).is(':checked')){
            chek = true;
        }
        $("#div-principal input[type=checkbox]").each(function(){
            console.log(this.name);
            //$("input[name="+this.name+"]").prop('checked');
            $("input[name="+this.name+"]").attr('checked',chek);
        //        .attr( 'checked' ): checked
        //.prop( 'checked' ): false
        //.is( ':checked' ): false
        });
    });
});
