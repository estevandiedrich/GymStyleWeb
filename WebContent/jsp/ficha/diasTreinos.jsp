<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<mtw:input type="hidden" name="treinoDomingoValue" id="treinoDomingoValue" value="-1" />
<mtw:input type="hidden" name="treinoSegundaValue" id="treinoSegundaValue" value="-1" />
<mtw:input type="hidden" name="treinoTercaValue" id="treinoTercaValue" value="-1" />
<mtw:input type="hidden" name="treinoQuartaValue" id="treinoQuartaValue" value="-1" />
<mtw:input type="hidden" name="treinoQuintaValue" id="treinoQuintaValue" value="-1" />
<mtw:input type="hidden" name="treinoSextaValue" id="treinoSextaValue" value="-1" />
<mtw:input type="hidden" name="treinoSabadoValue" id="treinoSabadoValue" value="-1" />
<h3 id="treinoss">Treinos</h3>
<div class="faixasForm" style="width: 99.5%;margin-left: 1px;min-height: 150px" >
    <table width="100%" class="displaytag">
        <thead>
            <tr>
                <th>Treino</th>
                <th colspan="7">Dias da Semana</th>
            </tr>
        </thead>
        <tbody id="tBody">
            <tr class="odd">
                <td align="center" width="5%" >
                    <mtw:label klass="obrig" value="A"/>
                </td>
                <td align="center" width="12%" class="sub">
                    <input type="radio" name="treinoSegunda" id="treinoSegundaRadio" value="A" <mtw:if test="treinoSegunda" value="A">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,1)" /> Segunda
                    </td>
                    <td align="center" width="12%" class="even">
                        <input type="radio" name="treinoTerca" value="A" <mtw:if test="treinoTerca" value="A">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,1)" /> Terça
                    </td>
                    <td align="center" width="12%" class="odd">
                        <input type="radio" name="treinoQuarta" value="A" <mtw:if test="treinoQuarta" value="A">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,1)" /> Quarta
                    </td>
                    <td align="center" width="12%" class="sub">
                        <input type="radio" name="treinoQuinta" value="A" <mtw:if test="treinoQuinta" value="A">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,1)" /> Quinta
                    </td>
                    <td align="center" width="12%" class="even">
                        <input type="radio" name="treinoSexta" value="A" <mtw:if test="treinoSexta" value="A">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,1)" /> Sexta
                    </td>
                    <td align="center" width="12%" class="odd">
                        <input type="radio" name="treinoSabado" value="A" <mtw:if test="treinoSabado" value="A">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,1)" /> Sábado
                    </td>
                    <td align="center" width="12%" class="sub2">
                        <input type="radio" name="treinoDomingo" value="A" <mtw:if test="treinoDomingo" value="A">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,1)" /> Domingo
                    </td>

                </tr>
                <tr class="even">
                    <td align="center" width="5%">
                    <mtw:label klass="obrig" value="B"/>
                </td>
                <td align="center" width="12%" class="sub">
                    <input type="radio" name="treinoSegunda" value="B" <mtw:if test="treinoSegunda" value="B">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,2)" /> Segunda
                    </td>
                    <td align="center" width="12%" class="even">
                        <input type="radio" name="treinoTerca" value="B" <mtw:if test="treinoTerca" value="B">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,2)" /> Terça
                    </td>
                    <td align="center" width="12%" class="odd">
                        <input type="radio" name="treinoQuarta" value="B" <mtw:if test="treinoQuarta" value="B">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,2)" /> Quarta
                    </td>
                    <td align="center" width="12%" class="sub">
                        <input type="radio" name="treinoQuinta" value="B" <mtw:if test="treinoQuinta" value="B">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,2)" /> Quinta
                    </td>
                    <td align="center" width="12%" class="even">
                        <input type="radio" name="treinoSexta" value="B" <mtw:if test="treinoSexta" value="B">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,2)" /> Sexta
                    </td>
                    <td align="center" width="12%" class="odd">
                        <input type="radio" name="treinoSabado" value="B" <mtw:if test="treinoSabado" value="B">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,2)" /> Sábado
                    </td>
                    <td align="center" width="12%" class="sub2">
                        <input type="radio" name="treinoDomingo" value="B"<mtw:if test="treinoDomingo" value="B">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,2)" /> Domingo
                    </td>
                </tr>
                <tr class="odd">
                    <td align="center" width="5%" >
                    <mtw:label klass="obrig" value="C"/>
                </td>
                <td align="center" width="12%" class="sub">
                    <input type="radio" name="treinoSegunda" value="C" <mtw:if test="treinoSegunda" value="C">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,3)" /> Segunda
                    </td>
                    <td align="center" width="12%" class="even">
                        <input type="radio" name="treinoTerca" value="C" <mtw:if test="treinoTerca" value="C">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,3)" /> Terça
                    </td>
                    <td align="center" width="12%" class="odd">
                        <input type="radio" name="treinoQuarta" value="C" <mtw:if test="treinoQuarta" value="C">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,3)" /> Quarta
                    </td>
                    <td align="center" width="12%" class="sub">
                        <input type="radio" name="treinoQuinta" value="C" <mtw:if test="treinoQuinta" value="C">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,3)" /> Quinta
                    </td>
                    <td align="center" width="12%" class="even">
                        <input type="radio" name="treinoSexta" value="C" <mtw:if test="treinoSexta" value="C">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,3)" /> Sexta
                    </td>
                    <td align="center" width="12%" class="odd">
                        <input type="radio" name="treinoSabado" value="C" <mtw:if test="treinoSabado" value="C">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,3)" /> Sábado
                    </td>
                    <td align="center" width="12%" class="sub2">
                        <input type="radio" name="treinoDomingo" value="C" <mtw:if test="treinoDomingo" value="C">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,3)" /> Domingo
                    </td>
                </tr>
                <tr class="even">
                    <td align="center" width="5%">
                    <mtw:label klass="obrig" value="D"/>
                </td>
                <td align="center" width="12%" class="sub">
                    <input type="radio" name="treinoSegunda" value="D" <mtw:if test="treinoSegunda" value="D">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,4)" /> Segunda
                    </td>
                    <td align="center" width="12%" class="even">
                        <input type="radio" name="treinoTerca" value="D" <mtw:if test="treinoTerca" value="D">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,4)" /> Terça
                    </td>
                    <td align="center" width="12%" class="odd">
                        <input type="radio" name="treinoQuarta" value="D" <mtw:if test="treinoQuarta" value="D">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,4)" /> Quarta
                    </td>
                    <td align="center" width="12%" class="sub">
                        <input type="radio" name="treinoQuinta" value="D" <mtw:if test="treinoQuinta" value="D">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,4)" /> Quinta
                    </td>
                    <td align="center" width="12%" class="even">
                        <input type="radio" name="treinoSexta" value="D" <mtw:if test="treinoSexta" value="D">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,4)" /> Sexta
                    </td>
                    <td align="center" width="12%" class="odd">
                        <input type="radio" name="treinoSabado" value="D" <mtw:if test="treinoSabado" value="D">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,4)" /> Sábado
                    </td>
                    <td align="center" width="12%" class="sub2">
                        <input type="radio" name="treinoDomingo"value="D" <mtw:if test="treinoDomingo" value="D">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,4)" /> Domingo
                    </td>
                </tr>
                <tr class="odd">
                    <td align="center" width="5%" >
                    <mtw:label klass="obrig" value="E"/>
                </td>
                <td align="center" width="12%" class="sub">
                    <input type="radio" name="treinoSegunda" value="E" <mtw:if test="treinoSegunda" value="E">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,5)" /> Segunda
                    </td>
                    <td align="center" width="12%" class="even">
                        <input type="radio" name="treinoTerca" value="E" <mtw:if test="treinoTerca" value="E">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,5)" /> Terça
                    </td>
                    <td align="center" width="12%" class="odd">
                        <input type="radio" name="treinoQuarta" value="E" <mtw:if test="treinoQuarta" value="E">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,5)" /> Quarta
                    </td>
                    <td align="center" width="12%" class="sub">
                        <input type="radio" name="treinoQuinta" value="E" <mtw:if test="treinoQuinta" value="E">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,5)" /> Quinta
                    </td>
                    <td align="center" width="12%" class="even">
                        <input type="radio" name="treinoSexta" value="E" <mtw:if test="treinoSexta" value="E">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,5)" /> Sexta
                    </td>
                    <td align="center" width="12%" class="odd">
                        <input type="radio" name="treinoSabado" value="E" <mtw:if test="treinoSabado" value="E">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,5)" /> Sábado
                    </td>
                    <td align="center" width="12%" class="sub2">
                        <input type="radio" name="treinoDomingo" value="E" <mtw:if test="treinoDomingo" value="E">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,5)" /> Domingo
                    </td>
                </tr>
                <tr class="even">
                    <td align="center" width="5%">
                    <mtw:label klass="obrig" value="F"/>
                </td>
                <td align="center" width="12%" class="sub">
                    <input type="radio" name="treinoSegunda" value="F" <mtw:if test="treinoSegunda" value="F">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,6)" /> Segunda
                </td>
                <td align="center" width="12%" class="even">
                    <input type="radio" name="treinoTerca" value="F" <mtw:if test="treinoTerca" value="F">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,6)" /> Terça
                </td>
                <td align="center" width="12%" class="odd">
                    <input type="radio" name="treinoQuarta" value="F" <mtw:if test="treinoQuarta" value="F">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,6)" /> Quarta
                </td>
                <td align="center" width="12%" class="sub">
                    <input type="radio" name="treinoQuinta" value="F" <mtw:if test="treinoQuinta" value="F">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,6)" /> Quinta
                </td>
                <td align="center" width="12%" class="even">
                    <input type="radio" name="treinoSexta" value="F" <mtw:if test="treinoSexta" value="F">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,6)" /> Sexta
                </td>
                <td align="center" width="12%" class="odd">
                    <input type="radio" name="treinoSabado" value="F" <mtw:if test="treinoSabado" value="F">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,6)" /> Sábado
                </td>
                <td align="center" width="12%" class="sub2">
                    <input type="radio" name="treinoDomingo" value="F" <mtw:if test="treinoDomingo" value="F">checked</mtw:if> onclick="eventoSelectRadioTreino(this.name,6)" /> Domingo
                </td>
            </tr>
        </tbody>
    </table>
</div>