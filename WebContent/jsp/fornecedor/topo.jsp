<table class="topo" width="100%">
    <tr>
        <td width="85%">
            <h1>Fornecedor</h1>
        </td>
        <td align="right" id="filtro" width="15%">
            <div id="item">
                <mtw:hasAuthorization permission="fornecedorRead">
                    <a id="list" href="fornecedorRead.do">Listar</a>
                    <mtw:hasAuthorization permission="fornecedorManager">
                        <a id="novo" href="fornecedorCreate.do">Novo</a>
                    </mtw:hasAuthorization>
                </mtw:hasAuthorization>
            </div>
        </td>
    </tr>
</table>