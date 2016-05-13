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
public class PerfilAcessoUpdateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        Long id = input.getLong("id");

        String consequence = SHOW;
        if (isPost()) {
            PerfilAcesso pojo = (PerfilAcesso) input.getValue("VOperfilAcesso");
//            pojo.setModoOperacao(ServiceLocator.getModoOperacaoService().readById(input.getLong("modo")));
            pojo.setFaixas(getFaixas());
            ServiceLocator.getPerfilAcessoService().update(pojo);
            session.setAttribute("mensagem", "Perfil de acesso alterado com sucesso!");

            consequence = SUCCESS;
        } else {
            PerfilAcessoAction.preload(output);
        }

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

        PerfilAcesso pojo = ServiceLocator.getPerfilAcessoService().readById(id);
        output.setValue("pojo", pojo);
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
        if (domingo && segunda && terca && quarta && quinta && sexta && sabado && feriado) {
            output.setValue("todos", true);
        }

        String[] dispos = null;

        if (pojo.getFaixas() != null) {
            if (pojo.getFaixas().size() > 0) {
                List<Dispositivo> dispositivos = pojo.getFaixas().get(0).getDispositivos();
                dispos = new String[dispositivos.size()];
                for (int i = 0; i < dispositivos.size(); i++) {
                    dispos[i] = String.valueOf(dispositivos.get(i).getId());
                }
            }
        }
        if (dispos == null) {
            dispos = new String[1];
        }

        output.setValue("dispositivos", ServiceLocator.getDispositivoService().readList());
        output.setValue("dispositivo", dispos);

        return consequence;
    }

    public List<Faixa> getFaixas() {
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