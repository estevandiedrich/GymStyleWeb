package br.com.rwtech.gymstyleweb.view.report.eventos;

import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstylecore.model.util.ConsultaUtil;
import br.com.rwtech.gymstyleweb.view.report.ReportAction;
import java.util.HashMap;
import java.util.Map;

public class EventoReportAction extends ReportAction {

    @Override
    public String getJasper() {
        return ("ReportEventos.jasper");
    }

    @Override
    public String getPasta() {
        return ("eventos");
    }

    @Override
    public String execute() throws Exception {
        Map parameters = new HashMap();
        String inicio = input.getString("criterioInicio");
        String fim = input.getString("criterioFim");
        String nome = input.getString("criterioNome");
        String cpf = input.getString("criterioCpf");
        String matricula = input.getString("criterioMatricula");
        String descricao = input.getString("criterioDescricao");
        if (inicio != null) {
            if (fim == null || fim.isEmpty()) {
                fim = CalendarUtil.getDataCurrente();
            }
            parameters.put("periodo", inicio + " à " + fim);

            String dataDeInicio = "";
            String dataDeFim = "";
            String[] dataInicio = inicio.split("/");
            int anoI = 0;
            int mesI = 0;
            int diaI = 0;
            if (!inicio.isEmpty()) {
                anoI = Integer.parseInt(dataInicio[2]);
                mesI = Integer.parseInt(dataInicio[1]);
                diaI = Integer.parseInt(dataInicio[0]);
                dataDeInicio = anoI + "-" + mesI + "-" + diaI + " 00:00:00";
            }
            String[] dataFim = fim.split("/");
            int anoF = 0;
            int mesF = 0;
            int diaF = 0;
            if (!inicio.isEmpty()) {
                anoF = Integer.parseInt(dataFim[2]);
                mesF = Integer.parseInt(dataFim[1]);
                diaF = Integer.parseInt(dataFim[0]);
                dataDeFim = anoF + "-" + mesF + "-" + diaF + " 23:59:59";
            }

            String sql = "select e.*,u.cpf,u.nome as aluno,e.data_hora,e.realizado,d.nome as dispositivo,d.endereco_ip as ip,m.motivo as nomemotivo from eventos as e"
                    + " left join dispositivos as d on d.id_dispositivos = e.id_dispositivos_fk"
                    + " left join usuarios as u on u.id_usuarios = e.id_usuarios_fk"
                    + " left join motivos_bloqueio as m on m.id_motivos_bloqueio = e.motivo"
                    + " where true";
            if (nome != null && !nome.isEmpty()) {
                sql += " and removeacento(u.nome) ilike '%" + nome + "%'";
            }

            if ((descricao != null) && (!descricao.isEmpty())) {
                if (descricao.equalsIgnoreCase("0")) {
                    sql += " and m.motivo = 'Liberado'";
                    sql += " and cpf isnull ";
                } else {
                    sql += " and e.motivo = '" + descricao + "'";
                }
            }

            if (cpf != null && !cpf.isEmpty()) {
                if (cpf != null && !cpf.isEmpty()) {
                    cpf = cpf.replace(".", "");
                    cpf = cpf.replace(".", "");
                    cpf = cpf.replace(".", "");
                    cpf = cpf.replace(".", "");
                    cpf = cpf.replace("-", "");
                }
                sql += " and removeacento(u.cpf) ilike '%" + cpf + "%'";
            }
            if (matricula != null && !matricula.isEmpty()) {
                sql += " and (u.id_usuarios ='" + matricula + "')";
            }
            sql += " and (m.id_motivos_bloqueio <> '7') and (m.id_motivos_bloqueio <> '12')";

            if (!dataDeInicio.isEmpty()) {
                sql += " and data_hora >= '" + dataDeInicio + "'";
            }
            if (!dataDeFim.isEmpty()) {
                sql += " and data_hora <= '" + dataDeFim + "'";
            }

            sql += " order by data_hora desc";
            compile(parameters, sql);
        }

        return CONSEQUENCE;
    }
}