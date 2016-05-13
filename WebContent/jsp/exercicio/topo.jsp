<table class="topo" width="100%">
    <tr>
        <td width="75%">
            <h1>Exercícios</h1>
        </td>
        <td align="right" id="filtro" width="25%">
            <div id="item">
                <mtw:hasAuthorization permission="exercicioRead">
                    <a id="list" href="exercicioRead.do">Listar</a>
                    <mtw:hasAuthorization permission="exercicioManager">
                        <a id="novo" href="exercicioCreate.do">Novo</a>
                    </mtw:hasAuthorization>
                </mtw:hasAuthorization>
            </div>
        </td>
    </tr>
</table>