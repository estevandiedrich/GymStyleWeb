<table class="topo" width="100%">
    <tr>
        <td width="85%">
            <h1>Categoria</h1>
        </td>
        <td align="right" id="filtro" width="15%">
            <div id="item">
                <mtw:hasAuthorization permission="categoriaRead">
                    <a id="list" href="categoriaRead.do">Listar</a>
                    <mtw:hasAuthorization permission="categoriaManager">
                        <a id="novo" href="categoriaCreate.do">Novo</a>
                    </mtw:hasAuthorization>
                </mtw:hasAuthorization>
            </div>
        </td>
    </tr>
</table>