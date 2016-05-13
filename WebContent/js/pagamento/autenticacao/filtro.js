    $(document).ready(function(){
        $("#criterioInicio").mask("99/99/9999");
        $("#criterioFim").mask("99/99/9999");
    });
    
    function ocultaPendente(){
        $("#divPendente").hide(200);
        $("#realizado").attr('checked', true);
        $("#pendente").attr('checked', false);
    }
    
    function mostraPendente(){
        $("#divPendente").show(200);
        //        $("#realizado").attr('checked', false);
        //        $("#pendente").attr('checked', true);
    }
