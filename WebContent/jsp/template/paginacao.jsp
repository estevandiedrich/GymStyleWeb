<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div style="background-color: #FAF8FF">
    <table  whith="100%">
        <tr>
            <td whith="80%" align="center">
            </td>
            <td whith="20%" align="right">
                <table style="width: 130px" class="paginacao" >
                    <tr>
                        <td class="paginacao" style="width: 50px">
                            <mtw:if test="previous" negate="false">
                                <a class="obrig" href="#" onclick="submitFormFiltrar(<mtw:out value="previousPage" />);">
                                    <img src="images/previous.png" title="Página anterior" style="cursor: pointer" />
                                </a>
                            </mtw:if>
                            <mtw:if test="previous" negate="true">
                                <label class="obrig" style="font-size: 15px">-</label>
                            </mtw:if>
                        </td>
                        <td class="paginacao" style="width: 30px" >
                            <label class="obrig" title="Página Atual"  style="cursor: pointer" ><mtw:out value="currentPage" /></label>
                            <mtw:input name="p" id="p" type="hidden"/>
                        </td>
                        <td class="paginacao" style="width: 50px" >
                            <mtw:if test="next" >
                                <a class="obrig" href="#" onclick="submitFormFiltrar(<mtw:out value="nextPage" />);">
                                    <img src="images/next.png" title="Próxima página" style="cursor: pointer" /></a>  
                                </mtw:if>  
                                <mtw:if test="next" negate="true">
                                <label class="obrig" style="font-size: 15px">-</label>
                            </mtw:if>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
