package br.com.rwtech.gymstyleweb.view.report.ficha;

import br.com.rwtech.gymstylecore.model.ConnectionManager;
import br.com.rwtech.gymstyleweb.view.report.ReportAction;
import java.util.HashMap;
import java.util.Map;

public class FichaReportAction extends ReportAction {

    @Override
    public String getJasper() {
        return ("ReportFicha.jasper");
    }

    @Override
    public String getPasta() {
        return ("ficha");
    }

    @Override
    public String execute() throws Exception {
        Map parameters = new HashMap();
        parameters.put(SUBREPORT_DIR, CAMINHO + getPasta() + "/");
        parameters.put(REPORT_CONNECTION, ConnectionManager.getInstance().getConnection());

        Long idUsuario = input.getLong("idUsuario");
        Long idFicha = input.getLong("idFicha");
        String treino = input.getString("treino");
        
        String filtro = "";
        if (treino != null && !treino.isEmpty()) {
            filtro = " and t.nome = '" + treino + "'";
        }
        if (idUsuario != null && idUsuario > 0
                && idFicha != null && idFicha > 0) {
            String sql = " select"
                    + " t.nome as treino,t.*,"
                    + " uf.*,u.nome as aluno,i.nome as instrutor"
                    + " from usuarios_fichas as uf"
                    + " left join usuarios as u on u.id_usuarios = uf.id_usuarios_fk"
                    + " left join usuarios as i on i.id_usuarios = uf.id_instrutor_fk"
                    + " left join treinos as t on t.id_fichas_fk = uf.id_usuarios_fichas"
                    + " where true"
                    + " and uf.id_usuarios_fk = " + idUsuario
                    + " and t.id_fichas_fk = " + idFicha //+ " and uf.ativa = true"
                    + filtro;
            compile(parameters, sql);
        }
        return CONSEQUENCE;
    }
}