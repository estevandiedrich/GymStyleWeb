<table class="topo" width="100%">
    <tr>
        <td width="75%">
            <h1>Backup do Banco de Dados</h1>
        </td>
        <td align="right" id="filtro" width="25%">
            <div id="item">
                <mtw:hasAuthorization permission="bancoBackupRead"><a id="list" href="bancoBackupRead.do">Listar</a></mtw:hasAuthorization>
                <mtw:hasAuthorization permission="bancoGerarBackup"><a id="novo" href="bancoGerarBackup.do" onclick="mostraStatus()">Gerar Backup</a></mtw:hasAuthorization>
            </div>
        </td>
    </tr>
</table>