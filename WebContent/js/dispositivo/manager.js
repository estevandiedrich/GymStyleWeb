var CONT = 20;

//Reenviar requisição configurar catraca
function reenviarRequisicaoConfigurar(idReq){
    var div = "";
    div+="<br><table width='400px' height='100%'>";
    div+="        <tr>";
    div+="            <td>";
    div+="                <img src='images/carregando.gif'/>";
    div+="            </td>";
    div+="            <td>";
    div+="                <label>Sincronizando Configurar Catraca...</label>";
    div+="                <input type='hidden' name='contadorConfigurar' id='contadorConfigurar' value='-10'/>";
    div+="                <input type='hidden' name='idRequisicaoConfigurar' id='idRequisicaoConfigurar' value='";
    div+=idReq;
    div+="'/>";
    div+="            </td>";
    div+="        </tr>";
    div+="        <tr><td><br></td></tr>";
    div+="    </table>";
    div+="</div>";
    $("#resultConfigurar").html(div);
    $("#resultConfigurar").show(700);
    requisicaoConfigurarCatraca();
}

function requisicaoConfigurarCatraca(){
    var contadorConfigurar = $("#contadorConfigurar").val();
    if(contadorConfigurar == null){
        contadorConfigurar = 1;
    }
    if(contadorConfigurar <= CONT){
        var val = "reenviarConfigurarCatraca.do?contadorConfigurar="+contadorConfigurar
        +"&idRequisicaoConfigurar="+$("#idRequisicaoConfigurar").val()
        +"&idDispositivo="+$("#idDispositivo").val();
        $.post(val, $(this).serialize(), function(r){
            $("#resultConfigurar").html(r);
            $("#resultConfigurar").show(500);
            requisicaoConfigurarCatraca();//chama ele mesmo
        });
    }
}

function requisicaoConfigurar(){
    var contador = $("#contador").val();
    if(contador == null){
        contador = 1;
    }
    if(contador <= CONT){
        var val = "configurarCatraca.do?contador="+contador
        +"&idRequisicao="+$("#idRequisicao").val()
        +"&idDispositivo="+$("#idDispositivo").val();
        $.post(val, $(this).serialize(), function(r){
            $("#result").html(r);
            $("#result").show(700);
            requisicaoConfigurar();//chama ele mesmo
        });
    }else{
    //alert("Acabou");
    }
}

$(document).ready(function(){
    requisicaoConfigurar();
});