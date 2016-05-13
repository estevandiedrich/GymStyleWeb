package br.com.rwtech.gymstyleweb.controller.action.perfilacesso;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Faixa;
import br.com.rwtech.gymstylecore.model.pojo.PerfilAcesso;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import org.mentawai.core.BaseAction;

/**
 * @author Éder Faria
 */
public class ImportarPerfilAcessoAjaxAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        output.setValue("todos", false);
        output.setValue("domingo", false);
        output.setValue("segunda", false);
        output.setValue("terca", false);
        output.setValue("quarta", false);
        output.setValue("quinta", false);
        output.setValue("sexta", false);
        output.setValue("sabado", false);
        output.setValue("feriado", false);

        boolean domingo = false;
        boolean segunda = false;
        boolean terca = false;
        boolean quarta = false;
        boolean quinta = false;
        boolean sexta = false;
        boolean sabado = false;
        boolean feriado = false;

        Long id = input.getLong("id");
        if (id != null && id != 0) {
            PerfilAcesso pojo = ServiceLocator.getPerfilAcessoService().readById(id);
            output.setValue("idHorario", pojo.getId());
            output.setValue("identificacao", pojo.getPerfilAcesso());
            for (Faixa faixa : pojo.getFaixas()) {
                String dia = "";
                if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase("domingo")) {
                    dia = "domingo";
                    output.setValue(dia, true);
                    domingo = true;
                    if (output.getValue(dia + "1") == null) {
                        output.setValue(dia + "1", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "2", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "3") == null) {
                        output.setValue(dia + "3", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "4", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "5") == null) {
                        output.setValue(dia + "5", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "6", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "7") == null) {
                        output.setValue(dia + "7", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "8", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    }
                } else if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase("segunda")) {
                    dia = "segunda";
                    output.setValue(dia, true);
                    segunda = true;
                    if (output.getValue(dia + "1") == null) {
                        output.setValue(dia + "1", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "2", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "3") == null) {
                        output.setValue(dia + "3", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "4", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "5") == null) {
                        output.setValue(dia + "5", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "6", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "7") == null) {
                        output.setValue(dia + "7", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "8", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    }
                } else if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase("terça")) {
                    dia = "terca";
                    output.setValue(dia, true);
                    terca = true;

                    if (output.getValue(dia + "1") == null) {
                        output.setValue(dia + "1", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "2", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "3") == null) {
                        output.setValue(dia + "3", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "4", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "5") == null) {
                        output.setValue(dia + "5", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "6", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "7") == null) {
                        output.setValue(dia + "7", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "8", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    }
                } else if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase("quarta")) {
                    dia = "quarta";
                    output.setValue(dia, true);
                    quarta = true;

                    if (output.getValue(dia + "1") == null) {
                        output.setValue(dia + "1", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "2", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "3") == null) {
                        output.setValue(dia + "3", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "4", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "5") == null) {
                        output.setValue(dia + "5", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "6", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "7") == null) {
                        output.setValue(dia + "7", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "8", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    }
                } else if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase("quinta")) {
                    dia = "quinta";
                    output.setValue(dia, true);
                    quinta = true;
                    if (output.getValue(dia + "1") == null) {
                        output.setValue(dia + "1", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "2", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "3") == null) {
                        output.setValue(dia + "3", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "4", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "5") == null) {
                        output.setValue(dia + "5", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "6", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "7") == null) {
                        output.setValue(dia + "7", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "8", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    }
                } else if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase("sexta")) {
                    dia = "sexta";
                    output.setValue(dia, true);
                    sexta = true;
                    if (output.getValue(dia + "1") == null) {
                        output.setValue(dia + "1", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "2", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "3") == null) {
                        output.setValue(dia + "3", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "4", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "5") == null) {
                        output.setValue(dia + "5", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "6", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "7") == null) {
                        output.setValue(dia + "7", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "8", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    }
                } else if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase("sábado")) {
                    dia = "sabado";
                    output.setValue(dia, true);
                    sabado = true;
                    if (output.getValue(dia + "1") == null) {
                        output.setValue(dia + "1", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "2", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "3") == null) {
                        output.setValue(dia + "3", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "4", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "5") == null) {
                        output.setValue(dia + "5", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "6", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "7") == null) {
                        output.setValue(dia + "7", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "8", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    }
                } else if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase("feriado")) {
                    dia = "feriado";
                    output.setValue(dia, true);
                    feriado = true;
                    if (output.getValue(dia + "1") == null) {
                        output.setValue(dia + "1", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "2", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "3") == null) {
                        output.setValue(dia + "3", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "4", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "5") == null) {
                        output.setValue(dia + "5", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "6", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    } else if (output.getValue(dia + "7") == null) {
                        output.setValue(dia + "7", CalendarUtil.getTimeCalendar(faixa.getHorarioInicio()));
                        output.setValue(dia + "8", CalendarUtil.getTimeCalendar(faixa.getHorarioFim()));
                    }
                }
            }
        }
        if (domingo && segunda && terca && quarta && quinta && sexta && sabado && feriado) {
            output.setValue("todos", true);
        }
        output.setValue("dispositivos", ServiceLocator.getDispositivoService().readList());
        return SUCCESS;
    }
}
