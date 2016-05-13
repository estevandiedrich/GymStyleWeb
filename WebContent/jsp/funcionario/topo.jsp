<table class="topo" width="100%">
    <tr>
        <td width="80%">
            <h1>Funcionário</h1>
        </td>
        <td  width="5%" align="center">
            <img src="images/func.png"/>
        </td>
        <td align="right" id="filtro" width="15%">
            <div id="item">
                <mtw:hasAuthorization permission="funcionarioRead">
                    <a id="list" href="funcionarioRead.do">Listar</a>
                    <mtw:hasAuthorization permission="funcionarioManager">
                        <a id="novo" href="funcionarioCreateInformacao.do">Novo</a>
                    </mtw:hasAuthorization>
                </mtw:hasAuthorization>
            </div>
        </td>
    </tr>
</table>