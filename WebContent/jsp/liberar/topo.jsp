<table class="topo" width="100%">
    <tr>
        <td  width="85%">
            <h1>Acessos Liberados</h1>
        </td>
        <td align="right" id="filtro"  width="15%">
            <div id="item">
                <mtw:hasAuthorization permission="liberarRead">
                    <a id="list" href="liberarRead.do">Listar</a>
                    <mtw:hasAuthorization permission="liberarManager">
                        <a id="novo" href="liberarCatraca.do" title="Liberar Catraca!">Novo</a>
                    </mtw:hasAuthorization>
                </mtw:hasAuthorization>
            </div>
        </td>
    </tr>
</table>