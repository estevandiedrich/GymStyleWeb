<table class="topo" width="100%">
    <tr>
        <td width="80%"><h1>Aluno</h1></td>
        <td width="05%" align="center"><img src="images/aluno.png"/></td>
        <td align="right" id="filtro" width="15%">
            <div id="item">
                <mtw:hasAuthorization permission="usuarioRead" >
                    <a id="list" href="usuarioRead.do">Listar</a>
                    <mtw:hasAuthorization permission="usuarioManager" >
                        <a id="novo" href="usuarioCreateInformacao.do">Novo</a>
                    </mtw:hasAuthorization>
                </mtw:hasAuthorization>
            </div>
        </td>
    </tr>
</table>