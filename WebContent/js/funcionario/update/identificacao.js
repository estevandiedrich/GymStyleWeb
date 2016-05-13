$(document).ready(function(){
    $("#btCapturar").unbind().click(function(){
        var val = "digitalAjax.read.do?id="+$("#id").val();
        $.post(val, $(this).serialize(), function(d){
            $("#corpoDigitais").html(d);
        });
        return false;
    });

    var varCatraca = document.getElementById("varCatraca").value;//1
    $("#eventoCatraca").click(function(event){
        event.preventDefault();
        if(varCatraca == 2){
            varCatraca = 1;
            $("#div-catraca").show(700);
            $('#eventoCatraca').removeClass('mais').addClass('menos');
        }else {
            varCatraca = 2;
            $("#div-catraca").hide("slow");
            $('#eventoCatraca').removeClass('menos').addClass('mais');
        }
        document.getElementById("varSistema").value = varSistema;
    });

    var varSistema = document.getElementById("varSistema").value;//1
    $("#eventoSistema").click(function(event){
        event.preventDefault();
        if(varSistema == 1){
            varSistema = 2;
            $("#div-sistema").show(700);
            $('#eventoSistema').removeClass('mais').addClass('menos');
        }else {
            varSistema = 1;
            $("#div-sistema").hide("slow");
            $('#eventoSistema').removeClass('menos').addClass('mais');
        }
        document.getElementById("varSistema").value = varSistema;
    });

    //via onload jquery ele seleciona os que ja foram selecionados
    if(varCatraca == 1){
        $("#div-catraca").show(700);
        $('#eventoCatraca').removeClass('mais').addClass('menos');
        document.getElementById("varCatraca").value = 1;
    }

    if(varSistema == 2){
        $("#div-sistema").show(700);
        $('#eventoSistema').removeClass('mais').addClass('menos');
        document.getElementById("varSistema").value = 1;

    }

    $('#cartaoProximidade').keypress(function(event) {
        var tecla = (window.event) ? event.keyCode : event.which;
        if ((tecla > 47 && tecla < 58)) return true;
        else {
            if (tecla != 8) return false;
            else return true;
        }
    });
    //--------------------------------------------------------------------------
    TIME = 500;
    $(".aba-autenticacao").click(function(){
        $(".aba-autenticacao").removeClass("current-autenticacao");
        $(this).addClass("current-autenticacao");
        $(".div-autenticacao").hide(TIME);
        $("#div-" + $(this).attr("id")).show(TIME);
    });

    if($("#chek-todos").val()!= undefined){
        $("#chek-todos").click(function(e){
            var chek = false;
            if($(this).is(':checked')){
                chek = true;
            }
            $("#div-principal input[type=checkbox]").each(function(){
                //console.log(this.name);
                $("input[name="+this.name+"]").attr('checked',chek);
            });
        });
        $(".chek-cadastro").click(function(e){
            var name = $(this).attr("name");
            if($(this).is(':checked')){
                if(name.indexOf("Manager") !=-1){
                    name = name.replace("Manager","Read");
                    $("#div-principal input[name="+name+"]").attr('checked',true);
                }else
                if(name.indexOf("Delete") !=-1){
                    name = name.replace("Delete","Read");
                    $("#div-principal input[name="+name+"]").attr('checked',true);
                }
            
            }else{
                if(name.indexOf("Read") !=-1){
                    name = name.replace("Read","Delete");
                    $("#div-principal input[name="+name+"]").attr('checked',false);
                    name = name.replace("Delete","Manager");
                    $("#div-principal input[name="+name+"]").attr('checked',false);
                }            
            }
        });
        //Colocado esta opção para remover o espaço de quando o usuário estiver com as senhas salvas
        if($("#login").val()== " "){
            $("#login").val("");
        }
    
        if($("#senha").val()== " "){
            $("#senha").val("");
        }
    
        if($("#id").val()==1){
            $("#div-principal input[type=checkbox]").each(function(){
                //console.log(this.name);
                $("input[name="+this.name+"]").attr('checked',true);
                $("input[name="+this.name+"]").attr('disabled',"disabled");
            });
            $("#chek-todos").attr('checked',true);
            $("#chek-todos").attr('disabled',"disabled");
        }
    }
    
    //    $("#div-catraca").hide(500);
    //    $("#div-sistema").show(500);
    $(".proximo").click(function(e){
        $("#acao").val("acesso");
        if(validaPermissoes()){
            document.form.submit();
        }
    });
    $("#salvar").click(function(e){
        e.preventDefault();
        $("#acao").val("");
        if(validaPermissoes()){
            document.form.submit();
        }
    });
});

function validaPermissoes(){
    var retorno = false;
    //Se nao tem login, nao valida
    if($("#login").val()== undefined){
        return true;
    }
    var chek = false;
    var temLogin = false;
    
    var login = $("#login").val();
    login = login.replace(" ", "");
    login = login.replace(" ", "");
    login = login.replace(" ", "");

    if(login != ""){
        temLogin = true;
    };
    
    //Senao tem permissao de alterar permissoes,nem aparece na tela
    
    $("#informe-permissoes").hide(250);
    $("#div-principal input[type=checkbox]").each(function(){
        if($("input[name="+this.name+"]").is(':checked')){
            chek = true;
        }
    });

    if(!temLogin){
        return true;
    }
    if(temLogin && chek){
        return true;
    }
    $("#informe-permissoes").show(250);
    $("#div-catraca").show(700);
    $('#eventoCatraca').removeClass('mais').addClass('menos');
    return false;
}
