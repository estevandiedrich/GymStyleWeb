<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/avaliacaofisica/create.js"></script>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/maskMoney.js" type="text/javascript"></script>
<script src="js/maskedinput-1.2.2.js" type="text/javascript"></script>

<div class="title_bottom">
    <%@include file="topo.jsp" %>
    <h3>Nova Avaliação Física</h3>
    <mtw:form method="post" action="avaliacaoFisicaCreate.do" name="formAvaliacao">
        <div class="faixasForm">
            <mtw:bean value="usuario">
                <table class="form" width="100%">
                    <tr>
                        <td width="100%"><br><h1>Informações Pessoais</h1></td>
                    </tr>
                    <tr>
                        <td width="100%">
                            <div class="faixasForm">
                                <table class="displaytag" width="100%">
                                    <tr>
                                        <th>Aluno</th>
                                        <th>Matrícula</th>
                                        <th>Cpf</th>
                                        <th>Idade</th>
                                        <th>Sexo</th>
                                    </tr>
                                    <tr class="odd">
                                        <td>
                                            <mtw:input type="hidden" name="id"/>
                                            <mtw:out value="usuario"/>
                                            <input type="hidden" id="idade" name="idade" value="<mtw:out value="idade" />"/>
                                        </td>
                                        <td><mtw:out value="matricula"/></td>
                                        <td><mtw:out value="cpf"/></td>
                                        <td><mtw:out value="idade"/></td>
                                        <td><mtw:out value="sexo" /><input type="hidden" id="sexo" name="sexo" value="<mtw:out value="sexo" />"</td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
            </mtw:bean>
        </div>
        <div class="faixasForm">
            <mtw:bean value="pojo">
                <table class="form" style="width: 98%">
                    <tr>
                        <td colspan="6"><br><h1>Índice de Massa Corporal - (IMC)</h1></td>
                    </tr>
                    <tr>
                        <td class="one" width="10%"><mtw:label klass="obrig" value="Peso:"/></td>
                        <td width="30%">
                            <mtw:inputMask name="peso" id="peso" size="50" maxlength="10" klass="inputNumber" textAlign="right"/> 
                        </td>
                        <td  width="10%">
                            <div id="pesoError"></div>
                    <font class="error" ><mtw:outError field="peso" ><mtw:out/></mtw:outError></font>
                        </td>
                        <td class="one"  width="10%"><mtw:label klass="obrig" value="Altura:"/></td>
                    <td  width="30%"><mtw:inputMask name="altura" id="altura" size="50" maxlength="10" klass="inputNumber" textAlign="right" title="Ex:1,70"/></td>
                    <td  width="10%">
                        <div id="alturaError"></div>
                    <font class="error" ><mtw:outError field="altura" ><mtw:out/></mtw:outError></font>
                        </td>
                        </tr>
                        <tr>
                            <td class="one"><mtw:label klass="obrig" value="IMC:"/></td>
                        <td><div id="imcLabel"><mtw:out value="imcValue"/></div></td>
                        <td>
                            <mtw:input type="hidden" name="imc" id="imc"/>
                            <mtw:input type="hidden" name="imcValue" id="imcValue"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6"><br><h1>Circunferências</h1></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Braço (Dir):"/></td>
                        <td><mtw:inputMask name="bracoDireito" id="bracoDireito" size="50" maxlength="10" klass="inputNumber" textAlign="right"/></td>
                        <td><font class="error"><mtw:outError field="bracoDireito" ><mtw:out/></mtw:outError></font></td>
                    <td class="one"><mtw:label klass="obrig" value="Braço (Esq):"/></td>
                    <td><mtw:inputMask name="bracoEsquerdo" id="bracoEsquerdo" size="50" maxlength="10" klass="inputNumber" textAlign="right"/></td>
                    <td><font class="error"><mtw:outError field="bracoEsquerdo" ><mtw:out/></mtw:outError></font></td>
                        </tr>
                        <tr>
                            <td class="one"><mtw:label klass="obrig" value="Coxa (Dir):"/></td>
                        <td><mtw:inputMask name="coxaDireita" id="coxaDireita" size="50" maxlength="10" klass="inputNumber" textAlign="right"/></td>
                        <td><font class="error"><mtw:outError field="coxaDireita" ><mtw:out/></mtw:outError></font></td>
                    <td class="one"><mtw:label klass="obrig" value="Coxa (Esq):"/></td>
                    <td><mtw:inputMask name="coxaEsquerda" id="coxaEsquerda" size="50" maxlength="10" klass="inputNumber" textAlign="right"/></td>
                    <td><font class="error"><mtw:outError field="coxaEsquerda" ><mtw:out/></mtw:outError></font></td>
                        </tr>
                        <tr>
                            <td class="one"><mtw:label klass="obrig" value="Panturrilha (Dir):"/></td>
                        <td><mtw:inputMask name="panturrilhaDireita" id="panturrilhaDireita" size="50" maxlength="10" klass="inputNumber" textAlign="right"/></td>
                        <td><font class="error"><mtw:outError field="panturrilhaDireita" ><mtw:out/></mtw:outError></font></td>
                    <td class="one"><mtw:label klass="obrig" value="Panturrilha (Esq):"/></td>
                    <td><mtw:inputMask name="panturrilhaEsquerda" id="panturrilhaEsquerda" size="50" maxlength="10" klass="inputNumber" textAlign="right"/></td>
                    <td><font class="error"><mtw:outError field="panturrilhaEsquerda" ><mtw:out/></mtw:outError></font></td>
                        </tr>
                        <tr>
                            <td class="one"><mtw:label klass="obrig" value="Tórax/Busto:"/></td>
                        <td><mtw:inputMask name="torax" id="torax" size="50" maxlength="10" klass="inputNumber" textAlign="right"/></td>
                        <td><font class="error"><mtw:outError field="torax" ><mtw:out/></mtw:outError></font></td>
                    <td class="one"><mtw:label klass="obrig" value="Quadril:"/></td>
                    <td><mtw:inputMask name="quadril" id="quadril" size="50" maxlength="10" klass="inputNumber" textAlign="right"/></td>
                    <td><font class="error"><mtw:outError field="quadril" ><mtw:out/></mtw:outError></font></td>
                        </tr>
                        <tr>
                            <td class="one"><mtw:label klass="obrig" value="Cintura:"/></td>
                        <td><mtw:inputMask name="cintura" id="cintura" size="50" maxlength="10" klass="inputNumber" textAlign="right"/></td>
                        <td><font class="error"><mtw:outError field="cintura" ><mtw:out/></mtw:outError></font></td>
                    <td class="one"><mtw:label klass="obrig" value="abdomen:"/></td>
                    <td><mtw:inputMask name="abdomen" id="abdomen" size="50" maxlength="10" klass="inputNumber" textAlign="right"/></td>
                    <td><font class="error"><mtw:outError field="abdomen" ><mtw:out/></mtw:outError></font></td>
                        </tr>
                        <tr>
                            <td colspan="3"><br><h1>Dobras Cutâneas</h1></td>
                        </tr>
                        <tr>
                            <td class="one" ><a class="selecione" onclick="eventoDobraCutanea('subescapular')" title="Selecione para visualizar!" >
                                <mtw:label klass="obrigLink" value="Subescapular(SB):"/></a>
                        </td>
                        <td><mtw:inputMask name="subescapular" id="subescapular" size="50" maxlength="10" klass="inputNumber" textAlign="right"/></td>
                        <td>
                            <div id="subescapularError"></div>
                    <font class="error"><mtw:outError field="subescapular" ><mtw:out/></mtw:outError></font></td>
                        <td class="one" ><a class="selecione" onclick="eventoDobraCutanea('tricipital')" title="Selecione para visualizar!" >
                            <mtw:label klass="obrigLink" value="Triciptal(TC):"/></a></td>
                    <td><mtw:inputMask name="tricipital" id="tricipital" size="50" maxlength="10" klass="inputNumber" textAlign="right"/></td>
                    <td>
                        <div id="tricipitalError"></div>
                    <font class="error"><mtw:outError field="tricipital" ><mtw:out/></mtw:outError></font>
                        </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><div id="subescapularImage" style="display: none"
                                     title="&nbsp;&nbsp;Dobra Cutânea Subescapular - A medida é executada obliquamente em relação ao eixo longitudinal, seguindo a orientação dos arcos costais, sendo localizada a dois centímetros abaixo do ângulo inferior da escápula.">
                                    <img src="images/Subescapular.jpg" /> </div></td>
                            <td><mtw:input type="hidden" name="subescapularEstado" id="subescapularEstado" value="1"/></td>
                        <td><mtw:input type="hidden" name="tricipitalEstado" id="tricipitalEstado" value="1"/></td>
                        <td>
                            <div id="tricipitalImage" style="display: none;" 
                                 title="&nbsp;&nbsp;Dobra Cutânea Triciptal - É medida na face posterior do braço, paralelamente ao eixo longitudinal, no ponto que compreende a metade da distância entre a borda súpero-lateral do acrômio e o olécrano.">
                                <img src="images/Tricipital.jpg" />
                            </div>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="one" ><a class="selecione" onclick="eventoDobraCutanea('peitoral')" title="Selecione para visualizar!" >
                                <mtw:label klass="obrigLink" value="Peitoral(PEI):"/></a>
                        </td>
                        <td><mtw:inputMask name="peitoral" id="peitoral" size="50" maxlength="10" klass="inputNumber" textAlign="right"/></td>
                        <td>
                            <div id="peitoralError"></div><font class="error"><mtw:outError field="peitoral" ><mtw:out/></mtw:outError></font>
                        </td>
                        <td class="one" ><a class="selecione" onclick="eventoDobraCutanea('abdominal')" title="Selecione para visualizar!" >
                            <mtw:label klass="obrigLink" value="Abdominal(AB):"/></a>
                    </td>
                    <td><mtw:inputMask name="abdominal" id="abdominal" size="50" maxlength="10" klass="inputNumber" textAlign="right"/></td>
                    <td>
                        <div id="abdominalError"></div><font class="error"><mtw:outError field="abdominal" ><mtw:out/></mtw:outError></font>
                        </td>
                        </tr>
                        <tr>
                            <td><mtw:input type="hidden" name="peitoralEstado" id="peitoralEstado" value="1"/></td>
                        <td>
                            <div id="peitoralImage" style="display: none"
                                 title="&nbsp;&nbsp;Dobra Cutânea Peitoral - É uma medida oblíqua em relação ao eixo longitudinal, na metade da distância entre a linha axilar anterior e o mamilo, para homens, e a um terço da linha axilar anterior, para mulheres.">
                                <img src="images/Peitoral.jpg" />
                            </div>
                        </td>
                        <td></td>
                        <td><mtw:input type="hidden" name="abdominalEstado" id="abdominalEstado" value="1"/></td>
                        <td>
                            <div id="abdominalImage" style="display: none"
                                 title="&nbsp;&nbsp;Dobra Cutânea Abdominal - É medida aproximadamente a dois centímetros à direita da cicatriz umbilical, paralelamente ao eixo longitudinal.">
                                <img src="images/Abdominal.jpg" />
                            </div>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="one" ><a class="selecione" onclick="eventoDobraCutanea('supraIliaca')" title="Selecione para visualizar!" >
                                <mtw:label klass="obrigLink" value="Supra-Ilíaca(SP):"/></a>
                        </td>
                        <td><mtw:inputMask name="supraIliaca" id="supraIliaca" size="50" maxlength="10" klass="inputNumber" textAlign="right"/></td>
                        <td>
                            <div id="supraIliacaError"></div>
                    <font class="error"><mtw:outError field="supraIliaca" ><mtw:out/></mtw:outError></font>
                        </td>
                        <td class="one" ><a class="selecione" onclick="eventoDobraCutanea('coxa')" title="Selecione para visualizar!" >
                            <mtw:label klass="obrigLink" value="Coxa(CX):"/></a>
                    </td>
                    <td><mtw:inputMask name="coxa" id="coxa" size="50" maxlength="10" klass="inputNumber" textAlign="right"/></td>
                    <td>
                        <div id="coxaError"></div>
                    <font class="error"><mtw:outError field="coxa" ><mtw:out/></mtw:outError></font>
                    </td>
                    </tr>
                    <tr>
                        <td><mtw:input type="hidden" name="supraIliacaEstado" id="supraIliacaEstado" value="1"/></td>
                        <td>
                            <div id="supraIliacaImage" style="display: none"
                                 title="&nbsp;&nbsp;Dobra Cutânea Supra-ilíaca - É obtida obliquamente em relação ao eixo longitudinal, na metade da distância entre o último arco costal e a crista ilíaca, sobre a linha axilar medial. É necessário que o avaliado afaste o braço para trás para permitir a execução da medida.">
                                <img src="images/SupraIliaca.jpg" />
                            </div>
                        </td>
                        <td></td>
                        <td><mtw:input type="hidden" name="coxaEstado" id="coxaEstado" value="1"/></td>
                        <td>
                            <div id="coxaImage" style="display: none"
                                 title="&nbsp;&nbsp;Dobra Cutânea da Coxa - É medida paralelamente ao eixo longitudinal, sobre o músculo reto femural a um terço da distância do ligamento inguinal e a borda superior da patela, segundo proposta por Guedes (1985) e na metade desta distância segundo Pollock & Wilmore (1993). Para facilitar o pinçamento desta dobra o avaliado deverá deslocar o membro inferior direito à frente, com uma semi-flexão do joelho, e manter o peso do corpo no membro inferior esquerdo.">
                                <img src="images/Coxa.jpg" />
                            </div>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="one">
                            <a class="selecione" onclick="eventoDobraCutanea('panturrilha')" title="Selecione para visualizar!" ><mtw:label klass="obrigLink" value="Panturrilha(PANT):"/></a>
                        </td>
                        <td><mtw:inputMask name="panturrilha" id="panturrilha" size="50" maxlength="10" klass="inputNumber" textAlign="right"/></td>
                        <td>
                            <div id="panturrilhaError"></div>
                    <font class="error"><mtw:outError field="panturrilha" ><mtw:out/></mtw:outError></font>
                        </td>
                        <td class="one" ><a class="selecione" onclick="eventoDobraCutanea('axilarMedia')" title="Selecione para visualizar!" >
                            <mtw:label klass="obrigLink" value="Axilar Média(AM):"/></a>
                    </td>
                    <td><mtw:inputMask name="axilarMedia" id="axilarMedia" size="50" maxlength="10" klass="inputNumber" textAlign="right"/></td>
                    </td>
                    <td>
                        <div id="axilarMediaError"></div>
                    <font class="error"><mtw:outError field="axilarMedia" ><mtw:out/></mtw:outError></font>
                        </td>
                        </tr>
                        <tr>
                            <td><mtw:input type="hidden" name="panturrilhaEstado" id="panturrilhaEstado" value="1"/></td>
                        <td>
                            <div id="panturrilhaImage" style="display: none"
                                 title="&nbsp;&nbsp;Dobra Cutânea Panturrilha Medial - Para a execução desta medida, o avaliado deve estar sentado, com a articulação do joelho em flexão de 90 graus, o tornozelo em posição anatômica e o pé sem apoio. A dobra é pinçada no ponto de maior perímetro da perna, com o polegar da mão esquerda apoiado na borda medial da tíbia.">
                                <img src="images/Panturrilha.jpg" />
                            </div>
                        </td>
                        <td></td>
                        <td><mtw:input type="hidden" name="axilarMediaEstado" id="axilarMediaEstado" value="1"/></td>
                        <td>
                            <div id="axilarMediaImage" style="display: none"
                                 title="&nbsp;&nbsp;Dobra Cutânea Axilar Média - É localizada no ponto de intersecção entre a linha axilar média e uma linha imaginária transversal na altura do apêndice xifóide do esterno. A medida é realizada obliquamente ao eixo longitudinal, com o braço do avaliado deslocado para trás, a fim de facilitar a obtenção da medida.">
                                <img src="images/AxilarMedia.jpg" />
                            </div>
                        </td>
                        <td></td>
                    </tr>
                </table>
            </mtw:bean>
        </div>
        <div class="faixasForm">
            <table class="form" >
                <tr>
                    <td colspan="3"><br><h1>Formulas</h1></td>
                </tr>
                <tr>
                    <td class="one">
                        <mtw:radiobuttons list="formulas" name="protocolo" defValue="1"/>
                    </td>
                    <td align="center"><mtw:buttonAction name="aplicar" klass="aplicar" value="Aplicar" onclick="gerarComposicaoCorporal()"/></td>
                    <td><font class="errors"><mtw:outError field="imc" ><mtw:out/></mtw:outError></font></td>
                    </tr>
                </table>
            </div>
            <div class="faixasForm">
                <table class="form">
                    <tr>
                        <td colspan="6"><br><h1>Composição Corporal</h1></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Gordura Atual(%):"/></td>
                    <td >
                        <mtw:input name="gorduraAtual" id="gorduraAtual" klass="inputTimeDisabled"  />
                    </td>
                    <td><font class="error"><mtw:outError field="gorduraAtual" ><mtw:out/></mtw:outError></font></td>
                <td class="one"><mtw:label klass="obrig" value="Gordura Ideal:"/></td>
                <td >
                    <mtw:input id="gorduraIdeal" name="gorduraIdeal" type="text" klass="inputDisabled" style="text-align: center" />
                </td>
                <td><font class="error"><mtw:outError field="gorduraIdeal" ><mtw:out/></mtw:outError></font></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Massa Magra(Kg):"/></td>
                    <td >
                        <mtw:input name="massaMagra" id="massaMagra" klass="inputTimeDisabled"  />
                    </td>
                    <td><font class="error"><mtw:outError field="massaMagra" ><mtw:out/></mtw:outError></font></td>
                <td class="one"><mtw:label klass="obrig" value="Massa Gorda(Kg):"/></td>
                <td >
                    <mtw:input name="massaGorda" id="massaGorda" klass="inputTimeDisabled"  />
                </td>
                <td><font class="error"><mtw:outError field="massaGorda" ><mtw:out/></mtw:outError></font></td>
                    </tr>
                </table>
            </div>
            <div class="faixasForm">
                <table class="form">
                    <tr>
                        <td colspan="3"><br><h1>Meta para a próxima avaliação</h1></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Próxima Avaliação:"/></td>
                    <td>
                        <mtw:input name="dataProximaAvaliacao"  id="dataProximaAvaliacao" klass="inputDate" size="10"/>

                <font class="errors"><mtw:outError field="dataProximaAvaliacao" ><mtw:out/></mtw:outError></font></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Descrição:"/></td>
                    <td colspan="3" style="max-width: 500px"><mtw:textarea name="descricao" id="descricao" cols="70" rows="7" maxlength="254" /></td>
                    <td><font class="error"><mtw:outError field="descricao" ><mtw:out/></mtw:outError></font></td>
                    </tr>
                </table>
            </div>
            <table width="100%" >
                <tr>
                    <td width="100%" class="panelButtonForm">
                    <mtw:buttonAction action="usuarioAvaliacoes.do" klass="cancelar" name="Cancelar" value="Cancelar"/>
                    <mtw:submit klass="botao" value="Salvar"/>
                </td>
            </tr>
        </table>
    </mtw:form>
</div>