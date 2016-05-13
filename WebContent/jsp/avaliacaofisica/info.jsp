<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/avaliacaofisica/info.js"></script>
<div class="title_bottom">
    <%@include file="topo.jsp" %>
    <h3>Avaliação Física</h3>
    <mtw:form method="post" action="usuarioAvaliacoes.do">
        <div class="faixasForm">
            <mtw:bean value="usuario">
                <table class="" style="width: 100%">
                    <tr>
                        <td width="100%" class="one"><br><h1>Informações Pessoais</h1></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="faixasForm" width="100%">
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
        <mtw:bean value="avaliacao">
            <div class="faixasForm">
                <table class="form">
                    <tr>
                        <td colspan="6"><br><h1>Índice de Massa Corporal - (IMC)</h1></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Peso:"/></td>
                        <td class="branco" width="30%">
                            <mtw:out value="peso"  formatter="realFormatter" />
                        </td>
                        <td class="one"><mtw:label klass="obrig" value="Altura:"/></td>
                        <td class="branco" width="25%"><mtw:out value="altura" formatter="realFormatter" /></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="IMC:"/></td>
                        <td class="branco"><mtw:out value="imc"  formatter="realFormatter" /></td>
                    </tr>
                    <tr>
                        <td colspan="6"><br><h1>Circunferências</h1></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Braço (Dir):"/></td>
                        <td class="branco" width="25%"><mtw:out value="bracoDir" formatter="realFormatter" /></td>
                        <td class="one"><mtw:label klass="obrig" value="Braço (Esq):"/></td>
                        <td class="branco" width="25%"><mtw:out value="bracoEsq" formatter="realFormatter" /></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Coxa (Dir):"/></td>
                        <td class="branco" width="25%"><mtw:out value="coxaDir" formatter="realFormatter" /></td>
                        <td class="one"><mtw:label klass="obrig" value="Coxa (Esq):"/></td>
                        <td class="branco" width="25%"><mtw:out value="coxaEsq" formatter="realFormatter" /></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Panturrilha (Dir):"/></td>
                        <td class="branco" width="25%"><mtw:out value="panturrilhaDir" formatter="realFormatter" /></td>
                        <td class="one"><mtw:label klass="obrig" value="Panturrilha (Esq):"/></td>
                        <td class="branco" width="25%"><mtw:out value="panturrilhaEsq" formatter="realFormatter" /></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Tórax/Busto:"/></td>
                        <td class="branco" width="25%"><mtw:out value="torax" formatter="realFormatter" /></td>
                        <td class="one"><mtw:label klass="obrig" value="Quadril:"/></td>
                        <td class="branco" width="25%"><mtw:out value="quadril" formatter="realFormatter" /></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Cintura:"/></td>
                        <td class="branco" width="25%"><mtw:out value="cintura" formatter="realFormatter" /></td>
                        <td class="one"><mtw:label klass="obrig" value="abdomen:"/></td>
                        <td class="branco" width="25%"><mtw:out value="abdomen" formatter="realFormatter" /></td>
                    </tr>
                    <tr>
                        <td colspan="4"><br><h1>Dobras Cutâneas</h1></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Subescapular(SB):"/></td>
                        <td class="branco" width="25%"><mtw:out value="subescapular" formatter="realFormatter" /></td>
                        <td class="one"><mtw:label klass="obrig" value="Tricipital(TC):"/></td>
                        <td class="branco" width="25%"><mtw:out value="tricipital" formatter="realFormatter" /></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Peitoral(PEI):"/></td>
                        <td class="branco" width="25%"><mtw:out value="peitoral" formatter="realFormatter" /></td>
                        <td class="one"><mtw:label klass="obrig" value="Abdominal(AB):"/></td>
                        <td class="branco" width="25%"><mtw:out value="abdominal" formatter="realFormatter" /></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Supra-Ilíaca(SP):"/></td>
                        <td class="branco" width="25%"><mtw:out value="supraIliaca" formatter="realFormatter" /></td>
                        <td class="one"><mtw:label klass="obrig" value="Coxa(CX):"/></td>
                        <td class="branco" width="25%"><mtw:out value="coxa" formatter="realFormatter" /></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Panturrilha(PANT):"/></td>
                        <td class="branco" width="25%"><mtw:out value="panturrilha" formatter="realFormatter" /></td>
                        <td class="one"><mtw:label klass="obrig" value="Axilar Média(AM):"/></td>
                        <td class="branco" width="25%"><mtw:out value="axilarMedia" formatter="realFormatter" /></td>
                    </tr>
                </table>
            </div>
            <div class="faixasForm">
                <table class="form" style="width: 100%">
                    <tr>
                        <td colspan="3"><br><h1>Formula</h1></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:out value="protocolo.protocolo"/></td>
                    </tr>
                </table>
            </div>
            <div class="faixasForm">
                <table class="form">
                    <tr>
                        <td colspan="6"><br><h1>Composição Corporal</h1></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Gordura Atual:"/></td>
                        <td class="branco"  width="25%"><mtw:out value="gorduraAtual" formatter="realFormatter" /> %</td>
                        <td class="one"><mtw:label klass="obrig" value="Gordura Ideal:"/></td>
                        <td class="branco" width="25%"><mtw:out value="gorduraIdeal"/></td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Massa Magra:"/></td>
                        <td class="branco" width="25%"><mtw:out value="massaMagra" formatter="realFormatter" /> Kg</td>
                        <td class="one"><mtw:label klass="obrig" value="Massa Gorda:"/></td>
                        <td class="branco" width="25%"><mtw:out value="massaGorda" formatter="realFormatter" /> Kg</td>
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
                        <td class="branco">
                            <mtw:out value="dataProximaAvaliacao.time" formatter="dateFormatter"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="one"><mtw:label klass="obrig" value="Descrição:"/></td>
                        <td colspan="3"><mtw:textarea klass="info" name="descricao" id="descricao" cols="70" rows="7" /></td>
                    </tr>
                </table>
            </div>
            <table width="100%" >
                <tr>
                    <td width="100%" class="panelButtonForm">
                        <mtw:submit klass="botao" value="Voltar"/>
                    </td>
                </tr>
            </table>
        </mtw:bean>
    </mtw:form>
</div>