<table class="topo" width="100%">
    <tr>
        <td width="85%">
            <h1>Conta Bancária</h1>
        </td>
        <td align="right" id="filtro" width="15%">
            <div id="item">
                <mtw:hasAuthorization permission="contaBancariaRead">
                    <a id="list" href="contaBancariaRead.do">Listar</a>
                    <mtw:hasAuthorization permission="contaBancariaManager">
                        <a id="novo" href="contaBancariaCreate.do">Novo</a>
                    </mtw:hasAuthorization>
                </mtw:hasAuthorization>
            </div>
        </td>
    </tr>
</table>