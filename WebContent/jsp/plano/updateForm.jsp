<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--script src="js/plano/updateForm.js"></script-->

<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/maskMoney.js" type="text/javascript"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>

<script>
    
    function verificaMaximo(name,num){
        if($("#"+name).val() > num ){
            $("#"+name).val(num); 
        }
    }
    
    $(document).ready(function(){
        ajustar();
        $("#observacao").css("maxWidth","500px");
        $("#observacao").css("maxHeight","100px");
        $("#observacao").css("minWidth","500px");
        $("#observacao").css("minHeight","100px");
        
        $(".inputNumber").maskMoney({
            symbol:"R$",
            decimal:",",
            thousands:"."
        });
        
        $("#descontoReal").val(float2moeda($("#descontoReal").val()));
        $("#valor").val(float2moeda($("#valor").val()));
        $("#valorTotal").val(float2moeda($("#valorTotal").val()));
        
        $(".inputTelefone").keydown(function(event){
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

    function ajustar(){
        if($('#todos').attr('checked')) {
            eventoTodosCadastrarPlanos();
        }else{
            var tam = ($('#tamanho').val());
            if(tam > 0){
                for (var i=1; i <= tam;i++){
                    if($('#checados'+i).val()=="true"){
                        $("#modalidades"+i).attr('checked', true);
                    }else{
                        $("#modalidades"+i).attr('checked', false);
                    }
                    selectLinha("modalidades"+i,i);
                }
            }
        }
        if($("#formaDeDesconto1").val()!= undefined && document.getElementById("formaDeDesconto1").checked){
            $("#descontoDois").hide(700);
        }else if($("#formaDeDesconto2").val()!= undefined && document.getElementById("formaDeDesconto2").checked){
            $("#descontoUm").hide(700);
        }
    }
    function setValorModalidade(i,valor){
        $("#valorFinalLabel"+i).html(float2moeda(valor));
        $("#valorFinal"+i).val(valor);
        eventoSelectCadastrarPlanos();
    }

    function selectLinha(name,i){
        if(document.getElementById(name).checked){
            $('#linha'+i).removeClass().addClass('linhaSelecionada');
        }else{
            if(i%2==0){
                $('#linha'+i).removeClass().addClass('odd');
            }else{
                $('#linha'+i).removeClass().addClass('even');
            }
            
        }
    }

    function eventoTodosCadastrarPlanos(){
        var tam = ($('#tamanho').val());
        var valor = 000;
        var val = false;
        if(tam > 0){
            if ($('#todos').attr('checked')) {
                val = true;
            }else{
                valor = "";
            }
            for (var i=1;i<=tam;i++){
                $("#modalidades"+i).attr('checked', val);
                selectLinha("modalidades"+i,i);
                valor = valor + parseFloat(($("#valorFinal"+i).val()));
            }
            var descontoReal = ($('#descontoReal').val());
            descontoReal = getValor(descontoReal);
            var descontoPercentual = ($('#descontoPercentual').val());
            descontoPercentual = getValor(descontoPercentual);
            var valorTotal = 0;
            if ($('#todos').attr('checked')) {
                $("#valor").val(float2moeda(valor));
                valorTotal = (float2moeda(valor - descontoReal- ((valor * descontoPercentual)/100)));
            }else{
                $("#valor").val(float2moeda(0));
                valorTotal = (float2moeda(descontoReal - ((descontoPercentual)/100)));
            }
            if(getValor(valorTotal)<0){
                valorTotal = 0;
            }
            $("#valorTotal").val(valorTotal);
        }
    }

    //Evento selecionar modalidades no cadastrar planos
    function eventoSelectCadastrarPlanos(){
        var tam = ($('#tamanho').val());
        var valor = 000;
        if(tam > 0){
            var j = 0;
            for (var i=1;i<=tam;i++){
                if ($("#modalidades"+i).attr('checked')) {
                    valor = valor + parseFloat(($("#valorFinal"+i).val()));
                    j++;
                }
            }
            if(j == tam){
                $("#todos").attr('checked', true);
            }else{
                $("#todos").attr('checked', false);
            }
            $("#valor").val(float2moeda(valor));
            var descontoReal = ($('#descontoReal').val());
            descontoReal = getValor(descontoReal);
            var descontoPercentual = ($('#descontoPercentual').val());
            descontoPercentual = getValor(descontoPercentual);
            var valorTotal = (float2moeda(valor - descontoReal- ((valor * descontoPercentual)/100)));
            if(getValor(valorTotal)<0){
                valorTotal = 0;
            }
            $("#valorTotal").val(valorTotal);
        }
    }    
    
    //Evento selecionar modalidades no cadastrar planos
    function eventoSelectCadastrarPlanos(){
        var tam = ($('#tamanho').val());
        var valor = 000;
        if(tam > 0){
            var j = 0;
            for (var i = 1; i <= tam;i++){
                if ($("#modalidades"+i).attr('checked')) {
                    valor = valor + parseFloat(($("#valorFinal"+i).val()));
                    j++;
                }
            }
            if(j == tam){
                $("#todos").attr('checked', true);
            }else{
                $("#todos").attr('checked', false);
            }
            $("#valor").val(float2moeda(valor));
            var descontoReal = ($('#descontoReal').val());
            descontoReal = getValor(descontoReal);
            var descontoPercentual = ($('#descontoPercentual').val());
            descontoPercentual = getValor(descontoPercentual);
            var valorTotal = (float2moeda(valor - descontoReal- ((valor * descontoPercentual)/100)));
            if(getValor(valorTotal)<0){
                valorTotal = 0;
            }
            $("#valorTotal").val(valorTotal);
        }
    }

    //Evento Desconto
    function eventoDescontoCadastrarPlanos(){
        var descontoPercentual = ($('#descontoPercentual').val());
        descontoPercentual = getValor(descontoPercentual);
        var valor = $("#valor").val();
        valor = getValor(valor);
        var descontoReal = ($('#descontoReal').val());
        descontoReal = getValor(descontoReal);
        var result = valor - descontoReal - ((valor * descontoPercentual)/100);
        if(result < 0){
            result = 0;
        }
        $("#valorTotal").val(float2moeda(result));
    }
    
    function formaDeDesconto(valor){
        if(valor == 2){
            $('#descontoUm').hide();
            $('#descontoDois').show(700);
        }else{
            $('#descontoDois').hide();
            $('#descontoUm').show(700);
        }
        $('#descontoReal').val("0,00");
        $("#descontoPercentual").val("");
    }
    
    //do cadastrar planos
    function eventoDesconto(){
        if($('#todos').attr('checked')) {
        }else{
            $('#valorTotal').val($('#valor').val());
        }
    }
</script>

<div class="title_bottom">
    <%@include file="topo.jsp" %>
    <h3>Editar</h3>
    <mtw:form method="post" action="planoUpdate.do" klass="faixasForm" >
        <mtw:bean value="pojo">
            <h3></h3>
            <table width="98%" align="center" class="faixasForm">
                <tr>
                    <td colspan="3" >
                        <h1>Planos</h1>
                    </td>
                </tr>
                <tr>
                    <td class="one">
                        <mtw:input type="hidden" name="id"/>
                        <mtw:input klass="input" type="hidden" name="ativo" value="true"/>
                        <mtw:label klass="obrig" value="Nome:"/>
                    </td>
                    <td width="25%">
                        <mtw:input klass="input" type="text" name="plano" maxlength="69"/>
                    </td>
                    <td width="50%">
                <font class="errors"><mtw:outError field="plano" ><mtw:out/></mtw:outError></font>
                    </td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Matrícula(R$):"/></td>
                    <td>
                        <mtw:inputMoney name="valorMatricula" id="valorMatricula" klass="inputNumber" decimals="2" dec_point="," title="Será referente a primeira parcela ao vincular plano ao usuário!"
                                        thousands_sep="." textAlign="right" />
                <font class="errors"><mtw:outError field="valorMatricula" ><mtw:out/></mtw:outError></font>
                    </td>
                    <td align="left"></td></tr>
                    <tr>
                        <td colspan="3" >
                    <font class="errors"><mtw:out value="modalidadesError"/></font>
                <div class="faixasForm">
                    <table width="100%" class="displaytag">
                        <mtw:bean value="lista">
                            <thead>
                                <tr>
                                    <th width="3%" align="center">
                                        <input type="checkbox" title="Selecionar todos!" name="todos"
                                               <c:if test="${todos}">checked="true"</c:if>
                                                   onclick="eventoTodosCadastrarPlanos()" id="todos" value=""/></th>
                                        <th width="42%">Modalidade</th>
                                        <th width="35%" title="Quantidade de Acessos na Semana!" colspan="7">Quantidade X na Semana</th>
                                        <th width="20%">Valor</th>
                                    </tr>
                                </thead>
                            <mtw:list value="lista">
                                <tbody>
                                    <mtw:isEmpty>
                                        <tr>
                                            <td colspan="4" >
                                                Modalidades não cadastradas 
                                            </td>
                                            <td>
                                                <mtw:hasAuthorization permission="modalidadeManager" >
                                                    <a id="novo" href="modalidadeCreate.do">Cadastrar Modalidade</a>
                                                </mtw:hasAuthorization>
                                            </td>
                                        </tr>
                                    </mtw:isEmpty>
                                    <mtw:loop var="row" counterStart="1" counter="i">
                                        <tr class="
                                            <c:choose>
                                                <c:when test="${i%2==0}">odd</c:when>
                                                <c:otherwise>even</c:otherwise>
                                            </c:choose>
                                            " id="linha${i}">
                                            <td>
                                                <input type="checkbox" name="modalidades${i}" id="modalidades${i}" value="<mtw:out value="id"/>"onclick="eventoSelectCadastrarPlanos(),selectLinha(this.name,${i})"/>
                                                <input type="hidden" name="checados${i}" id="checados${i}" value="<mtw:out value="checados${i}"/>"/>
                                            </td>
                                            <td  align="center">
                                                <mtw:hasAuthorization permission="modalidadeManager" >
                                                    <a class="info" href="modalidadeUpdate.do?id=<mtw:out value="id"/>" title="Visualizar Modalidade: <mtw:out value="modalidade"/>!">
                                                    </mtw:hasAuthorization>
                                                    <mtw:out value="modalidade" max="30"/>
                                                    <mtw:hasAuthorization permission="modalidadeManager" >
                                                    </a>
                                                </mtw:hasAuthorization>
                                            </td>
                                            <td  align="center" width="5%">
                                                <mtw:if test="valor1" value="null" negate="true">
                                                    <input type="Radio" name="valor${i}" <mtw:if test="1chek${i}">checked="true"</mtw:if> onclick="setValorModalidade(${i},<mtw:out value="valor1"/>)" value="1"> 1
                                                </mtw:if>
                                            </td>
                                            <td  align="center" width="5%">
                                                <mtw:if test="valor2" value="null" negate="true">
                                                    <input type="Radio" name="valor${i}" <mtw:if test="2chek${i}">checked="true"</mtw:if> onclick="setValorModalidade(${i},<mtw:out value="valor2"/>)" value="2"> 2
                                                </mtw:if>
                                            </td>
                                            <td  align="center" width="5%">
                                                <mtw:if test="valor3" value="null" negate="true">
                                                    <input type="Radio" name="valor${i}" <mtw:if test="3chek${i}">checked="true"</mtw:if> onclick="setValorModalidade(${i},<mtw:out value="valor3"/>)" value="3"> 3
                                                </mtw:if>
                                            </td>
                                            <td  align="center" width="5%">
                                                <mtw:if test="valor4" value="null" negate="true">
                                                    <input type="Radio" name="valor${i}" <mtw:if test="4chek${i}">checked="true"</mtw:if> onclick="setValorModalidade(${i},<mtw:out value="valor4"/>)" value="4"> 4
                                                </mtw:if>
                                            </td>
                                            <td  align="center" width="5%">
                                                <mtw:if test="valor5" value="null" negate="true">
                                                    <input type="Radio" name="valor${i}" <mtw:if test="5chek${i}">checked="true"</mtw:if> onclick="setValorModalidade(${i},<mtw:out value="valor5"/>)" value="5"> 5
                                                </mtw:if>
                                            </td>
                                            <td  align="center" width="5%">
                                                <mtw:if test="valor6" value="null" negate="true">
                                                    <input type="Radio" name="valor${i}" <mtw:if test="6chek${i}">checked="true"</mtw:if> onclick="setValorModalidade(${i},<mtw:out value="valor6"/>)" value="6"> 6
                                                </mtw:if>
                                            </td>
                                            <td  align="center" width="8%">
                                                <mtw:if test="valor7" value="null" negate="true">
                                                    <input type="Radio" name="valor${i}" <mtw:if test="7chek${i}">checked="true"</mtw:if> onclick="setValorModalidade(${i},<mtw:out value="valor7"/>)" value="7" title="Livre"> Livre
                                                </mtw:if>
                                            </td>
                                            <td  align="center" width="5%">
                                                <input type="hidden" name="valorFinal${i}" id="valorFinal${i}" value="<mtw:out value="valorFinal${i}"/>" />
                                                <div id="valorFinalLabel${i}"><mtw:out value="valorFinal${i}" formatter="realFormatter"/></div>
                                            </td>
                                        </tr>
                                    </mtw:loop>
                                </tbody>
                            </mtw:list>
                        </mtw:bean>
                    </table>
                    <input type="hidden" name="tamanho" id="tamanho" value="<mtw:out value="lista.size"/>"/>
                </div>
                </td>
                </tr>
                <mtw:list value="modalidadesOff">
                    <mtw:isEmpty negate="true">
                        <tr>
                            <td colspan="3" >
                                <div class="faixasForm">
                                    <table width="100%" class="displaytag">
                                        <thead>
                                            <tr>
                                                <th width="100%">Modalidade Inativa Vinculada a Este PLano</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <mtw:loop var="row" counterStart="1" counter="i">
                                                <tr class="
                                                    <c:choose>
                                                        <c:when test="${i%2==0}">even</c:when>
                                                        <c:otherwise>odd</c:otherwise>
                                                    </c:choose>
                                                    ">
                                                    <td class="one" align="center">
                                                        <mtw:hasAuthorization permission="modalidadeManager" >
                                                        <a class="info" href="modalidadeUpdate.do?id=<mtw:out value="id"/>" title="Visualizar Modalidade: <mtw:out value="modalidade"/>!"><mtw:out value="modalidade" max="30"/></a>
                                                        </mtw:hasAuthorization>
                                                    </td>
                                                </tr>
                                            </mtw:loop>
                                            <tr class="sub2">
                                                <td class="chek" colspan="<mtw:out value="dispositivosOffLine.size"/>">
                                                    <h4>Ao salvar, estas modalidades deixaram de estar vinculadas</h4>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </td>
                        </tr>
                    </mtw:isEmpty>
                </mtw:list>
            </table>
            <table class="faixasForm" width="98%" style="height: 100px">
                <tr>
                    <td width="50%">
                        <table width="95%" class="faixasFor">
                            <tr>
                                <td class="one"><mtw:label klass="obrig" value="Valor Mensal:"/></td>
                                <td>
                                    <mtw:input name="valor" id="valor" value="0" klass="inputDisabledNumber" maxlength="12" extra="readonly=true"/>
                                </td>
                                <td><font class="errors"><mtw:outError field="valor" ><mtw:out/></mtw:outError></font></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Valor Total:"/></td>
                    <td>
                        <mtw:input name="valorTotal" id="valorTotal" klass="inputNumber" maxlength="12" />
                <font class="error"><mtw:outError field="valorTotal" ><mtw:out/></mtw:outError>
                        </td>
                        <td align="left"></font></td>
                    </tr>
                </table>
            </td>
            <td width="50%">
                <div style="width: 98%; height:60px">
                    <table width="100%" >
                        <tr class="odd">
                            <td align="center" colspan="3">
                                <input type="radio" name="formasDeDesconto" id="formaDeDesconto1" <c:if test="${formaDeDesconto1}" >checked="true"</c:if>
                                       onchange="formaDeDesconto(1)" value="R$" onclick="eventoDesconto()"/>R$
                                <input type="radio" name="formasDeDesconto" id="formaDeDesconto2" <c:if test="${formaDeDesconto2}">checked="true"</c:if>
                                       onchange="formaDeDesconto(2)" value="%" onclick="eventoDesconto()" />%
                            </td>
                        </tr>
                        <tr id="descontoUm" >
                            <td class="one"><mtw:label klass="obrig" value="Desconto:(R$)"/></td>
                        <td align="left"><mtw:inputMoney name="descontoReal" klass="inputNumber" decimals="2" value="0,00" maxlength="12"
                                        dec_point="," thousands_sep="." textAlign="right" onkeyup="eventoDescontoCadastrarPlanos()" /></td>
                        <td width="20%"><font class="errors"><mtw:outError field="descontoReal" ><mtw:out/></mtw:outError></font></td>
                        </tr>
                        <tr id="descontoDois">
                            <td class="one"><mtw:label klass="obrig" value="Desconto:(%)"/></td>
                        <td align="left">
                            <mtw:input name="descontoPercentual" id="descontoPercentual" klass="inputTelefone" maxlength="3" extra="onkeyup=eventoDescontoCadastrarPlanos(),verificaMaximo(this.name,100)" /></td>
                        </td>
                        <td width="20%"></td>
                    </tr>
                </table>
            </div>
        </td>
    </tr>
</table>
<table width="98%" class="faixasForm">
    <tr>
        <td class="one"><mtw:label klass="obrig" value="Observação:"/></td>
        <td>
            <mtw:textarea name="observacao" id="observacao" cols="70" rows="8" maxlength="299"/>
        </td>
    </tr>
</table>
<table width="100%" >
    <tr>
        <td width="100%" class="panelButtonForm">
            <mtw:buttonAction action="planoRead.do" klass="botao" name="Cancelar" value="Cancelar"/>
            <mtw:submit klass="botao" value="Salvar"/>
        </td>
    </tr>
</table>
</mtw:bean>
</mtw:form>
</div>