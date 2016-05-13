<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<mtw:isEmpty negate="true">
    <div style="background-color: #FAF8FF">
        <table  width="100%">
            <tr>
                <td width="80%" align="right">
                    <div id="processando" style="display: none" ><img src="images/carregandoPeq.gif" class="paginacao" width="25" title=" Processando..."/></div>
                </td>
                <td width="20%" align="right">
                    <table style="width: 160px" class="paginacao" >
                        <tr>
                            <td class="paginacao" style="width: 25px">
                                <img src="images/first.png" title="Primeira Página!"style="cursor: pointer" onclick="submitFormEventoRead(1);"/>
                            </td>
                            <td class="paginacao" style="width: 25px">
                                <mtw:if test="pag.prev" negate="false">
                                    <img src="images/prev.png" title="Página anterior" style="cursor: pointer" onclick="submitFormEventoRead(<mtw:out value="pag.prevPage" />);" />
                                </mtw:if>
                                <mtw:if test="pag.prev" negate="true">
                                    <img src="images/prev.png" title="Página anterior <mtw:out value="pag.prevPage" />" style="cursor: pointer" onclick="submitFormEventoRead(1);" />
                                </mtw:if>
                            </td>
                            <td class="paginacao" style="width: 30px" >
                                <label class="obrig" title="Página Atual"  style="cursor: pointer" >
                                    <mtw:if test="pag.paginas" value="1"><mtw:out value="pag.currentPage" /></mtw:if>
                                    <mtw:if test="pag.paginas" value="1" negate="true">
                                        <mtw:select list="pag.qtdePaginas" name="pag.currentPage" extra="onchange=submitFormEventoRead(this.value)" />
                                    </mtw:if>
                                </label>
                                <mtw:input name="pag.p" id="p" type="hidden"/>
                            </td>
                            <td class="paginacao" style="width: 30px" >
                                <label class="obrig" style="cursor: pointer" ><mtw:out value="pag.paginas"/></label>
                            </td>
                            <td class="paginacao" style="width: 50px" >
                                <mtw:if test="pag.next" >
                                    <img src="images/next.png" title="Próxima página" style="cursor: pointer" onclick="submitFormEventoRead(<mtw:out value="pag.nextPage" />);"/>
                                </mtw:if>
                                <mtw:if test="pag.next" negate="true">

                                    <img src="images/next.png" title="Ultima Página!" style="cursor: pointer" onclick="submitFormEventoRead(<mtw:out value="pag.paginas" />);"/>
                                </mtw:if>
                            </td>
                            <td class="paginacao" style="width: 25px">

                                <img src="images/last.png" title="Ultima Página!" style="cursor: pointer" onclick="submitFormEventoRead(<mtw:out value="pag.paginas"/>);"/>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </div>
</mtw:isEmpty>