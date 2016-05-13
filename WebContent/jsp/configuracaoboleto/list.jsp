<%@include file="../../WEB-INF/imports.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<mtw:bean value="pojo">
    <div class="title_bottom">
        <table class="topo" width="100%">
            <tr>
                <td width="90%">
                    <h1>Configurações Boleto</h1>
                </td>
                <td align="right" id="filtro"  width="10%">
                    <div id="item">
                        <a class='update' title='Editar Boleto?' href="configurarBoleto.do?id=${pojo.id}">Editar</a>
                    </div>
                </td>
            </tr>
        </table>
        <h3>Listar</h3>
        <div id="content" >
            <h1 style="padding-top: 15px">Modelo de Boleto</h1>
            <table width="98%">
                <tbody><tr><td width="100%" align="right"><label>RECIBO SACADO</label></td></tr></tbody>
            </table>
            <table width="98%" class="boletoForm">
                <tbody>
                    <tr>
                        <td>
                            <table width="98%" class="boletoForm">
                                <tbody>
                                    <tr>
                                        <td width="20%" >
                                            <label>
                                                <c:if test="${pojo.banco!=null}">
                                                    <img height="35" width="135" src="images/<mtw:out value="banco.codigoDeCompensacao"  />.png">
                                                </c:if>
                                            </label>
                                        </td>
                                        <td width="5%" >
                                            <label class="conteudo titulo"><mtw:out value="banco.codigoDeCompensacao" /></label>
                                        </td>
                                        <td width="75%" >
                                            <label class="conteudo titulo"> 00000.00000 00000.000000 00000.000000 0 00000000000000</label>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table width="98%" class="boletoForm">
                                <tbody>
                                    <tr>
                                        <td valign="top" width="40%">
                                            <label class="celula" >Cedente</label><br><label class="conteudo" ><mtw:out value="cedenteRazaoSocial" /></label>
                                        </td>
                                        <td valign="top" width="20%">
                                            <label class="celula" >Agencia/Codigo Cedente</label><br>
                                            <label class="conteudo" ><mtw:out value="bancoAgencia" />-<mtw:out value="bancoAgenciaVariacao" /> / <mtw:out value="bancoNumero" />-<mtw:out value="bancoNumeroDigito" /></label>
                                        </td>
                                        <td valign="top" width="10%">
                                            <label class="celula" title="">Especie</label><br><label class="conteudo" >REAL</label>
                                        </td>
                                        <td valign="top" width="10%">
                                            <label class="celula" title="">Quantidade</label><br>
                                        </td>
                                        <td valign="top" width="20%">
                                            <label class="celula" title="">Nosso Numero</label>
                                            <br><label class="conteudo" ><mtw:out value="tituloNossoNumero" />-<mtw:out value="tituloDigitoNossoNumero" /></label>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table width="98%" class="boletoForm">
                                <tbody>
                                    <tr>
                                        <td valign="top" width="30%">
                                            <label class="celula" >Numero do Documento</label><br><label class="conteudo" ></label>
                                        </td>
                                        <td valign="top" width="22%">
                                            <label class="celula" >CPF / CNPJ</label><br>
                                            <label class="conteudo" ><mtw:out value="cedenteCnpj" /></label><br>
                                        </td>
                                        <td valign="top" width="22%">
                                            <label class="celula" >Data de Vencimento</label><br><label class="conteudo">00/00/0000</label>
                                        </td>
                                        <td valign="top" width="26%">
                                            <label class="celula">Valor Documento</label><br><label class="conteudo">R$ 0,00</label>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table width="98%" class="boletoForm">
                                <tbody>
                                    <tr>
                                        <td valign="top" width="19%">
                                            <label class="celula" >(-)Descontos /Abatimentos</label><br><label class="conteudo" >R$ 0,00</label>
                                        </td>
                                        <td valign="top" width="19%">
                                            <label class="celula" >(-)Outras Deduções</label><br><label class="conteudo">R$ 0,00</label>
                                        </td>
                                        <td valign="top" width="19%">
                                            <label class="celula" >(+)Mora / Multa</label><br><label class="conteudo">R$ 0,00</label>
                                        </td>
                                        <td valign="top" width="19%">
                                            <label class="celula">(+)Outros Acréscimos</label><br><label class="conteudo">R$ 0,00</label>
                                        </td>
                                        <td valign="top" width="26%">
                                            <label class="celula">(=)Valor Cobrado</label><br><label class="conteudo">R$ 0,00</label>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table width="98%" class="boletoForm">
                                <tbody>
                                    <tr>
                                        <td valign="top">
                                            <label class="celula" >Sacado/Aluno</label>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table width="98%" class="boletoForm">
                                <tbody>
                                    <tr>
                                        <td valign="top" width="70%">
                                            <label class="celula" >Instruções</label><br><label class="conteudo" ><mtw:out value="boletoInstrucaoSacado" /></label>
                                        </td>
                                        <td valign="top" width="30%">
                                            <label class="celula" >Autênticação Mecânica</label>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div style="padding-left: 20px">----------------------------------------------------------------------------------------------------------------------------------------------------------</div>
            <table width="98%" class="boletoForm">
                <tbody>
                    <tr>
                        <td>
                            <table width="98%" class="boletoForm">
                                <tbody>
                                    <tr>
                                        <td width="20%" >
                                            <label>
                                                <c:if test="${pojo.banco!=null}">
                                                    <img height="35" width="135" src="images/<mtw:out value="banco.codigoDeCompensacao"  />.png">
                                                </c:if>
                                            </label>
                                        </td>
                                        <td width="5%" align="center" >
                                            <label class="conteudo titulo"><mtw:out value="banco.codigoDeCompensacao" /></label>
                                        </td>
                                        <td width="75%" >
                                            <label class="conteudo titulo"> 00000.00000 00000.000000 00000.000000 0 00000000000000</label>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table width="98%" class="boletoForm">
                                <tbody>
                                    <tr>
                                        <td valign="top" width="80%">
                                            <label class="celula" >Local de Pagamento</label><br><label class="conteudo" ><mtw:out value="boletoLocalPagamento" /></label>
                                        </td>
                                        <td valign="top" width="20%">
                                            <label class="celula" >Vencimento</label><br><label class="conteudo">00/00/0000</label>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table width="98%" class="boletoForm">
                                <tbody>
                                    <tr>
                                        <td valign="top" width="80%">
                                            <label class="celula" >Cedente</label><br><label class="conteudo"><mtw:out value="cedenteRazaoSocial" /></label>
                                        </td>
                                        <td valign="top" width="20%">
                                            <label class="celula" >Agência/Código Cedente</label><br><label class="conteudo" ><mtw:out value="bancoAgencia" />-<mtw:out value="bancoAgenciaVariacao" /> / <mtw:out value="bancoNumero" />-<mtw:out value="bancoNumeroDigito" /></label>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <table width="98%" class="boletoForm">
                                <tbody>
                                    <tr>
                                        <td valign="top" width="18%">
                                            <label class="celula" >Data do Documento</label><br><label class="conteudo">00/00/0000</label>
                                        </td>
                                        <td valign="top" width="18%">
                                            <label class="celula" >N° Documento</label>
                                        </td>
                                        <td valign="top" width="18%">
                                            <label class="celula" >Espécie Doc</label><br><label class="conteudo"><mtw:out value="tituloTipoDocumento.sigla" /></label>
                                        </td>
                                        <td valign="top" width="10%">
                                            <label class="celula">Aceite</label><br><label class="conteudo"><mtw:out value="tituloAceite" /></label>
                                        </td>
                                        <td valign="top" width="16%">
                                            <label class="celula">Data Processamento</label><br><label class="conteudo">00/00/0000</label>
                                        </td>
                                        <td valign="top" width="20%">
                                            <label class="celula">Nosso Número</label><br><label class="conteudo"><mtw:out value="tituloNossoNumero" />-<mtw:out value="tituloDigitoNossoNumero" /></label>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <table width="98%" class="boletoForm">
                                <tbody>
                                    <tr>
                                        <td valign="top" width="18%">
                                            <label class="celula" >Uso do Banco</label>
                                        </td>
                                        <td valign="top" width="10%">
                                            <label class="celula" >Carteira</label>
                                        </td>
                                        <td valign="top" width="18%">
                                            <label class="celula" >Espécie</label><br><label class="conteudo">REAL</label>
                                        </td>
                                        <td valign="top" width="18%">
                                            <label class="celula">Quantidade</label>
                                        </td>
                                        <td valign="top" width="16%">
                                            <label class="celula">Valor</label><br><label class="conteudo">R$ 0,00</label>
                                        </td>
                                        <td valign="top" width="20%">
                                            <label class="celula">Valor Documento</label><br><label class="conteudo">R$ 0,00</label>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table width="98%" class="boletoForm">
                                <tbody>
                                    <tr>
                                        <td valign="top" width="80%" rowspan="5">
                                            <label class="celula" >Instruções (Texto de responsabilidade do cliente)</label>
                                            <c:if test="${pojo.boletoInstrucaoSacado.length()>0}">
                                                <br><label class="conteudo"><mtw:out value="boletoInstrucaoSacado" /></label>
                                            </c:if>
                                            <c:if test="${pojo.boletoInstrucao1.length()>0}">
                                                <br><label class="conteudo"><mtw:out value="boletoInstrucao1" /></label>
                                            </c:if>
                                            <c:if test="${pojo.boletoInstrucao2.length()>0}">
                                                <br><label class="conteudo"><mtw:out value="boletoInstrucao2" /></label>
                                            </c:if>
                                            <c:if test="${pojo.boletoInstrucao3.length()>0}">
                                                <br><label class="conteudo"><mtw:out value="boletoInstrucao3" /></label>
                                            </c:if>
                                            <c:if test="${pojo.boletoInstrucao4.length()>0}">
                                                <br><label class="conteudo"><mtw:out value="boletoInstrucao4" /></label>
                                            </c:if>
                                            <c:if test="${pojo.boletoInstrucao5.length()>0}">
                                                <br><label class="conteudo"><mtw:out value="boletoInstrucao5" /></label>
                                            </c:if>
                                            <c:if test="${pojo.boletoInstrucao6.length()>0}">
                                                <br><label class="conteudo"><mtw:out value="boletoInstrucao6" /></label>
                                            </c:if>
                                            <c:if test="${pojo.boletoInstrucao7.length()>0}">
                                                <br><label class="conteudo"><mtw:out value="boletoInstrucao7" /></label>
                                            </c:if>
                                            <c:if test="${pojo.boletoInstrucao8.length()>0}">
                                                <br><label class="conteudo"><mtw:out value="boletoInstrucao8" /></label>
                                            </c:if>
                                        </td>
                                        <td valign="top" width="20%">
                                            <label class="celula" >(-)Descontos /Abatimentos</label><br><label class="conteudo" >R$ 0,00</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top" width="19%">
                                            <label class="celula" >(-)Outras Deduções</label><br><label class="conteudo">R$ 0,00</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top" width="19%">
                                            <label class="celula" >(+)Mora / Multa</label><br><label class="conteudo">R$ 0,00</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top" width="19%">
                                            <label class="celula">(+)Outros Acréscimos</label><br><label class="conteudo">R$ 0,00</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top" width="26%">
                                            <label class="celula">(=)Valor Cobrado</label><br><label class="conteudo">R$ 0,00</label>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table width="98%" class="boletoForm">
                                <tbody>
                                    <tr>
                                        <td valign="top">
                                            <label class="celula" >Sacado/Aluno</label><br><label class="conteudo" ></label>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                </tbody>
            </table>
            <table width="98%">
                <tbody><tr><td width="100%" align="right"><label>RECIBO SACADO</label></td></tr></tbody>
            </table>

            <table width="98%" class="faixasForm">
                <tr>
                    <td class="faixasForm" align="center" style="font-size: 12px;color: #EC0000 ;font-style: italic">
                        Os campos não configuráveis como Vencimento,Valor, Sacado(Aluno), Data de Vencimento, entre outros, <br>seram apresentados quando gerar boleto ao aluno!
                    </td>
                </tr>
            </table>
        </div>
    </div>
</mtw:bean>