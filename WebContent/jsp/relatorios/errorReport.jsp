<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script>
    $(document).ready(function(){
        $("#detalhesErro").click(function(event){
            event.preventDefault();
            if($('#detalhesErro').hasClass('mais')) {
                $("#erroRelatorio").show(700);
                $('#detalhesErro').removeClass('mais').addClass('menos');
            }else {
                $("#erroRelatorio").hide("slow");
                $('#detalhesErro').removeClass('menos').addClass('mais');
            }
        });
    });
</script>

<div class="topo">
    <h1 class="errors">Erro ao gerar relatório.</h1>
    <h1 class="mais" id="detalhesErro">Detalhes</h1>
    <div id="erroRelatorio" class="error faixasForm one"><mtw:out value="erro"/></div>
</div>
