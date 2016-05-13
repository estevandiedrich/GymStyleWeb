package br.com.rwtech.gymstyleweb.controller.action.perfilacesso;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Dispositivo;
import br.com.rwtech.gymstylecore.model.pojo.Faixa;
import br.com.rwtech.gymstylecore.model.pojo.PerfilAcesso;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import java.util.ArrayList;
import java.util.List;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class PerfilAcessoCreateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;

        if (isPost()) {
            PerfilAcesso pojo = (PerfilAcesso) input.getValue("VOperfilAcesso");
            pojo.setFaixas(getFaixas());
            ServiceLocator.getPerfilAcessoService().create(pojo);
            session.setAttribute("mensagem", "Perfil de Acesso criado com sucesso!");
            consequence = SUCCESS;
        } else {
            PerfilAcessoAction.preload(output);
        }
        return consequence;
    }

    private List<Faixa> getFaixas() {
        String[] dispositivos = input.getStrings("dispositivo");
        List<Dispositivo> catracas = new ArrayList<Dispositivo>();
        if (dispositivos != null) {
            for (String id : dispositivos) {
                catracas.add(ServiceLocator.getDispositivoService().readById(Long.parseLong(id)));
            }
        }
        List<Faixa> lista = new ArrayList<Faixa>();
        String dia = "domingo";
        if (input.getValue(dia) != null) {
            //anda de dois em dois para pegar Inicio e Fim
            for (int i = 1; i <= 8; i++) {
                if (input.getValue(dia + i) != null && input.getValue(dia + (i + 1)) != null) {
                    if (!input.getString(dia + i).isEmpty() && !input.getString(dia + (i + 1)).isEmpty()) {
                        Faixa faixa = new Faixa();
                        faixa.setDiaSemana(ServiceLocator.getDiaSemanaService().readByName(dia));
                        faixa.setHorarioInicio(CalendarUtil.setTimeCalendar(input.getString(dia + i)));
                        faixa.setHorarioFim(CalendarUtil.setTimeCalendar(input.getString(dia + (i + 1))));
                        faixa.setDispositivos(catracas);
                        lista.add(faixa);
                    }
                }
                i++;
            }
        }
        dia = "segunda";
        if (input.getValue(dia) != null) {
            //anda de dois em dois para pegar Inicio e Fim
            for (int i = 1; i <= 8; i++) {
                if (input.getValue(dia + i) != null && input.getValue(dia + (i + 1)) != null) {
                    if (!input.getString(dia + i).isEmpty() && !input.getString(dia + (i + 1)).isEmpty()) {
                        Faixa faixa = new Faixa();
                        faixa.setDiaSemana(ServiceLocator.getDiaSemanaService().readByName(dia));
                        faixa.setHorarioInicio(CalendarUtil.setTimeCalendar(input.getString(dia + i)));
                        faixa.setHorarioFim(CalendarUtil.setTimeCalendar(input.getString(dia + (i + 1))));
                        faixa.setDispositivos(catracas);
                        lista.add(faixa);
                    }
                }
                i++;
            }
        }
        dia = "terca";
        if (input.getValue(dia) != null) {
            //anda de dois em dois para pegar Inicio e Fim
            for (int i = 1; i <= 8; i++) {
                if (input.getValue(dia + i) != null && input.getValue(dia + (i + 1)) != null) {
                    if (!input.getString(dia + i).isEmpty() && !input.getString(dia + (i + 1)).isEmpty()) {
                        Faixa faixa = new Faixa();
                        faixa.setDiaSemana(ServiceLocator.getDiaSemanaService().readByName("terça"));
                        faixa.setHorarioInicio(CalendarUtil.setTimeCalendar(input.getString(dia + i)));
                        faixa.setHorarioFim(CalendarUtil.setTimeCalendar(input.getString(dia + (i + 1))));
                        faixa.setDispositivos(catracas);
                        lista.add(faixa);
                    }
                }
                i++;
            }
        }
        dia = "quarta";
        if (input.getValue(dia) != null) {
            //anda de dois em dois para pegar Inicio e Fim
            for (int i = 1; i <= 8; i++) {
                if (input.getValue(dia + i) != null && input.getValue(dia + (i + 1)) != null) {
                    if (!input.getString(dia + i).isEmpty() && !input.getString(dia + (i + 1)).isEmpty()) {
                        Faixa faixa = new Faixa();
                        faixa.setDiaSemana(ServiceLocator.getDiaSemanaService().readByName(dia));
                        faixa.setHorarioInicio(CalendarUtil.setTimeCalendar(input.getString(dia + i)));
                        faixa.setHorarioFim(CalendarUtil.setTimeCalendar(input.getString(dia + (i + 1))));
                        faixa.setDispositivos(catracas);
                        lista.add(faixa);
                    }
                }
                i++;
            }
        }
        dia = "quinta";
        if (input.getValue(dia) != null) {
            //anda de dois em dois para pegar Inicio e Fim
            for (int i = 1; i <= 8; i++) {
                if (input.getValue(dia + i) != null && input.getValue(dia + (i + 1)) != null) {
                    if (!input.getString(dia + i).isEmpty() && !input.getString(dia + (i + 1)).isEmpty()) {
                        Faixa faixa = new Faixa();
                        faixa.setDiaSemana(ServiceLocator.getDiaSemanaService().readByName(dia));
                        faixa.setHorarioInicio(CalendarUtil.setTimeCalendar(input.getString(dia + i)));
                        faixa.setHorarioFim(CalendarUtil.setTimeCalendar(input.getString(dia + (i + 1))));
                        faixa.setDispositivos(catracas);
                        lista.add(faixa);
                    }
                }
                i++;
            }
        }
        dia = "sexta";
        if (input.getValue(dia) != null) {
            //anda de dois em dois para pegar Inicio e Fim
            for (int i = 1; i <= 8; i++) {
                if (input.getValue(dia + i) != null && input.getValue(dia + (i + 1)) != null) {
                    if (!input.getString(dia + i).isEmpty() && !input.getString(dia + (i + 1)).isEmpty()) {
                        Faixa faixa = new Faixa();
                        faixa.setDiaSemana(ServiceLocator.getDiaSemanaService().readByName(dia));
                        faixa.setHorarioInicio(CalendarUtil.setTimeCalendar(input.getString(dia + i)));
                        faixa.setHorarioFim(CalendarUtil.setTimeCalendar(input.getString(dia + (i + 1))));
                        faixa.setDispositivos(catracas);
                        lista.add(faixa);
                    }
                }
                i++;
            }
        }
        dia = "sabado";
        if (input.getValue(dia) != null) {
            //anda de dois em dois para pegar Inicio e Fim
            for (int i = 1; i <= 8; i++) {
                if (input.getValue(dia + i) != null && input.getValue(dia + (i + 1)) != null) {
                    if (!input.getString(dia + i).isEmpty() && !input.getString(dia + (i + 1)).isEmpty()) {
                        Faixa faixa = new Faixa();
                        faixa.setDiaSemana(ServiceLocator.getDiaSemanaService().readByName("sábado"));
                        faixa.setHorarioInicio(CalendarUtil.setTimeCalendar(input.getString(dia + i)));
                        faixa.setHorarioFim(CalendarUtil.setTimeCalendar(input.getString(dia + (i + 1))));
                        faixa.setDispositivos(catracas);
                        lista.add(faixa);
                    }
                }
                i++;
            }
        }
        dia = "feriado";
        if (input.getValue(dia) != null) {
            //anda de dois em dois para pegar Inicio e Fim
            for (int i = 1; i <= 8; i++) {
                if (input.getValue(dia + i) != null && input.getValue(dia + (i + 1)) != null) {
                    if (!input.getString(dia + i).isEmpty() && !input.getString(dia + (i + 1)).isEmpty()) {
                        Faixa faixa = new Faixa();
                        faixa.setDiaSemana(ServiceLocator.getDiaSemanaService().readByName(dia));
                        faixa.setHorarioInicio(CalendarUtil.setTimeCalendar(input.getString(dia + i)));
                        faixa.setHorarioFim(CalendarUtil.setTimeCalendar(input.getString(dia + (i + 1))));
                        faixa.setDispositivos(catracas);
                        lista.add(faixa);
                    }
                }
                i++;
            }
        }
        return lista;
    }
}