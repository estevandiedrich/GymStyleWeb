<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<mtw:bean value="infoConta">
    <table width="100%" class="displaytag" id="detalhesConta" >
        <thead>
            <tr>
                <th>Banco</th>
                <th>Agência</th>
                <th>Número</th>
                <th>Titular</th>
            </tr>
        </thead>
        <tr class="odd" style="height: 30px">
            <td width="30%"><mtw:out value="banco.nome" max="30"/></td>
            <td width="15%"><mtw:out value="agencia"/></td>
            <td width="15%"><mtw:out value="numeroConta"/></td>
            <td width="40%"><mtw:out value="titular"/></td>
        </tr>
        <tr class="even"><td colspan="4"></td></tr>
    </table>
</mtw:bean>