package br.com.rwtech.gymstyleweb.view.report.pagamentos;

import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstylecore.model.util.ConsultaUtil;
import br.com.rwtech.gymstyleweb.view.report.ReportAction;
import java.util.HashMap;
import java.util.Map;

public class PagamentoReportAction extends ReportAction {

    public static String VENCIMENTO = "vencimento";
    public static String PAGAMENTO = "pagamento";
    public static Boolean POR_PAGAMENTO = true;

    @Override
    public String getPasta() {
        return "pagamentos";
    }

    @Override
    public String getJasper() {
        if (POR_PAGAMENTO) {
            return "ReportPagamentos.jasper";
        } else {
            return "ReportPagamentosVencimento.jasper";
        }
    }

    @Override
    public String execute() throws Exception {
        Map parameters = new HashMap();
        String inicio = input.getString("criterioInicio");
        String fim = input.getString("criterioFim");
        String matricula = input.getString("criterioMatricula");
        if (fim.isEmpty()) {
            fim = CalendarUtil.getDataCurrente();
        }
        parameters.put("periodo", inicio + " à " + fim);

        String tipo = input.getString("tipo");
        String data = "vencimento";

        if (tipo.equalsIgnoreCase("false")) {
            POR_PAGAMENTO = Boolean.TRUE;
            data = "pagamento";
        } else {
            POR_PAGAMENTO = Boolean.FALSE;
            data = "vencimento";
        }

        String pagos = "NOT NULL"; //Não nullos
        if (!input.getBoolean("realizado")) {
            pagos = "NULL";
            pagos += " and pu.cancelado = false"
                    + " and pu.finalizado = false";
        }

        String filtros = " and pagamento is " + pagos;


        String dataDeInicio = "";
        String dataDeFim = "";
        int anoI = 0;
        int mesI = 0;
        int diaI = 0;
        if (inicio != null && !inicio.isEmpty()) {
            String[] dataInicio = inicio.split("/");
            anoI = Integer.parseInt(dataInicio[2]);
            mesI = Integer.parseInt(dataInicio[1]);
            diaI = Integer.parseInt(dataInicio[0]);
            dataDeInicio = anoI + "-" + mesI + "-" + diaI + " 00:00:00";
            filtros += " and " + data + " >= '" + dataDeInicio + "'";
        }

        int anoF = 0;
        int mesF = 0;
        int diaF = 0;
        if (fim != null && !fim.isEmpty()) {
            String[] dataFim = fim.split("/");
            anoF = Integer.parseInt(dataFim[2]);
            mesF = Integer.parseInt(dataFim[1]);
            diaF = Integer.parseInt(dataFim[0]);
            dataDeFim = anoF + "-" + mesF + "-" + diaF + " 23:59:59";
            filtros += " and " + data + " <= '" + dataDeFim + "'";
        }

        if (input.getValue("criterioNome") != null) {
            String criterioUsuario = input.getString("criterioNome");
            if (!criterioUsuario.isEmpty()) {
                filtros += " and (u.nome ilike '%" + criterioUsuario + "%' or u.nome ilike '%" + ConsultaUtil.normalize(criterioUsuario) + "%')";
            }
        }

        if (matricula != null && !matricula.isEmpty()) {
            filtros += " and (u.id_usuarios = '" + matricula + "')";
        }

        String criterioPlano = (String) input.getString("criterioPlano");
        if (criterioPlano != null && !criterioPlano.isEmpty()) {
            filtros += " and pu.id_planos_fk = " + criterioPlano;
        }

        String criterioFuncionario = (String) input.getString("criterioFuncionario");
        if (criterioFuncionario != null && !criterioFuncionario.isEmpty()) {
            filtros += " and p.id_funcionarios_fk = " + criterioFuncionario;
        }

        String tableJoin = " from pagamentos as p left join formas_pagamento as f on f.id_formas_pagamento = p.id_formas_pagamento_fk"
                + " left join planos_usuarios as pu on pu.id_planos_usuarios = p.id_planos_usuarios_fk"
                + " left join usuarios as u on u.id_usuarios = pu.id_usuarios_fk"
                + " where true";

        String sql = "select p.id_pagamentos,p.id_planos_usuarios_fk,p.numero_parcela, p.vencimento, p.pagamento, p.valor_pago, p.valor, p.id_funcionarios_fk,(select nome from usuarios where id_usuarios = p.id_funcionarios_fk) as funcionario, u.nome, f.nome as forma,pu.cancelado,"
                + " (select sum(valor)"
                + tableJoin
                + filtros
                + " ) as valor_total,( "
                + " select sum(valor_pago) "
                + tableJoin
                + filtros
                + " ) as valor_pago_total"
                + tableJoin
                + filtros
                + " order by " + data + " desc,id_pagamentos DESC";
        compile(parameters, sql);

        return CONSEQUENCE;
    }
}
