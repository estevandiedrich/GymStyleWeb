<%@include file="../../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="div-cadastro" class="div-autenticacao">
    <h3>Cadastros</h3>
    <div style="width: 700px;min-height: 350px" class="faixasForm">
        <table width="100%">
            <tr>
                <td align="right" valign="bottom"><h4 style="font-size: 11px;padding-top: 3px">Para Cadastrar, Editar e Excluir, é obrigatório a opção de Consulta</h4></td>
            </tr>
        </table>
        <table class="displaytag" >
            <thead>
                <tr>
                    <th width="70%">Permissão</th>
                    <th width="10%" title="Consulta!"><a id='list' ></a></th>
                    <th width="10%" title="Cadastrar / Editar!"><a id='novo' ></a> | <a class='update' ></a></th>
                    <th width="10%" title="Excluir!"><a class='delete' ></a></th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${cadastro.size==0}">
                    <tr class="sub"><td colspan="4">Dados Vazios</td></tr>
                </c:if>
                <c:forEach items="${cadastro}" var="row" varStatus="i">
                    <c:choose>  
                        <c:when test="${i.count % 2 == 0}">    
                            <tr class="odd"> 
                            </c:when>  
                            <c:otherwise>   
                            <tr class="even"> 
                            </c:otherwise>  
                        </c:choose> 
                        <td>${row.value}</td>
                        <td>
                            <input type="checkbox" class="chek-cadastro" name="${row.key}Read" id="${row.key}Read" <mtw:out value="${row.key}ReadCheck"/> />
                        </td>
                        <td>
                            <input type="checkbox" class="chek-cadastro" name="${row.key}Manager" id="${row.key}Manager" <mtw:out value="${row.key}ManagerCheck"/> />
                        </td>
                        <td>
                            <c:if test="${row.key!='empresa'}">
                                <input type="checkbox" class="chek-cadastro" name="${row.key}Delete" id="${row.key}Delete" <mtw:out value="${row.key}DeleteCheck"/> />
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>