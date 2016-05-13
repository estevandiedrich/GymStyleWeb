<%@include file="../../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<mtw:isEmpty negate="true">
    <div style="background-color: #FAF8FF" width="100%">
        <table  width="100%">
            <tr>
                <td align="center" colspan="3">
                    <h3>Resumo do Período</h3>
                </td>
            </tr>
            <tr>
                <td width="80%" align="center">
                    <table width="100%" class="paginacao" >
                        <tr>
                            <td class="paginacao" style="width: 20%">
                                <label class="obrig" title="Valor Total a Receber">Valor Total a Receber</label>
                            </td>
                            <td class="paginacao" style="width: 30%" >
                                <mtw:out value="pag.valorTotalAPagar" formatter="realFormatter"/>
                            </td>
                            <td class="paginacao" style="width: 20%">
                                <label class="obrig" title="Valor Total Recebido!">Valor Total Recebido</label>
                            </td>
                            <td class="paginacao" style="width: 30%" >
                                <mtw:out value="pag.valorPagoTotal" formatter="realFormatter"/>
                            </td>
                        </tr>
                    </table>
                </td>
                <td width="20%" align="right">
                    <table style="width: 160px" class="paginacao" >
                        <tr>
                            <td class="paginacao" style="width: 25px">
                                <img src="images/first.png" title="Primeira Página!" onclick="submitFormPagamentoReportRead(1);" style="cursor: pointer"/>
                            </td>
                            <td class="paginacao" style="width: 25px">
                                <mtw:if test="pag.prev" negate="false">
                                    <img src="images/prev.png" onclick="submitFormPagamentoReportRead(<mtw:out value="pag.prevPage" />);" title="Página Anterior" style="cursor: pointer" />
                                </mtw:if>
                                <mtw:if test="pag.prev" negate="true">
                                    <img src="images/prev.png" onclick="submitFormPagamentoReportRead(1);" title="Primeira Página!" style="cursor: pointer" />
                                </mtw:if>
                            </td>
                            <td class="paginacao" style="width: 30px" >
                                <label class="obrig" title="Página Atual"  style="cursor: pointer" ><!--mtw:out value="pag.currentPage" /-->
                                    <mtw:if test="pag.paginas" value="1"><mtw:out value="pag.currentPage" /></mtw:if>
                                    <mtw:if test="pag.paginas" value="1" negate="true">
                                        <mtw:select list="pag.qtdePaginas" name="pag.currentPage" extra="onchange=submitFormPagamentoReportRead(this.value)" />
                                    </mtw:if>
                                </label>
                                <mtw:input name="pag.p" id="p" type="hidden"/>
                            </td>
                            <td class="paginacao" style="width: 30px" >
                                <label class="obrig" style="cursor: pointer" ><mtw:out value="pag.paginas"/></label>
                            </td>                            
                            <td class="paginacao" style="width: 25px" >
                                <mtw:if test="pag.next" >
                                    <img src="images/next.png" title="Próxima Página" style="cursor: pointer" onclick="submitFormPagamentoReportRead(<mtw:out value="pag.nextPage" />);" />
                                </mtw:if>
                                <mtw:if test="pag.next" negate="true">
                                    <img src="images/next.png" title="Próxima Página" style="cursor: pointer" onclick="submitFormPagamentoReportRead(<mtw:out value="pag.paginas" />);" />
                                </mtw:if>
                            </td>
                            <td class="paginacao" style="width: 25px">
                                <img src="images/last.png" title="Ultima Página" style="cursor: pointer" onclick="submitFormPagamentoReportRead(<mtw:out value="pag.paginas" />);"/>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </div>
</mtw:isEmpty>