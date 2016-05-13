package br.com.rwtech.gymstyleweb.controller.action.modalidade;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Dispositivo;
import br.com.rwtech.gymstylecore.model.pojo.Faixa;
import br.com.rwtech.gymstylecore.model.pojo.Modalidade;
import br.com.rwtech.gymstylecore.model.pojo.PerfilAcesso;
import br.com.rwtech.gymstylecore.model.pojo.tipos.DiaDaSemana;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstylecore.model.util.Validador;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class ModalidadeUpdateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        Long id = input.getLong("id");

        String consequence = SHOW;
        String[] listaDispositivos = input.getStrings("dispositivo");
        if (isPost() && listaDispositivos != null) {
            Modalidade pojo = (Modalidade) input.getValue("VOmodalidade");
            pojo.setValor1(Validador.getMoney(input.getString("valor11")));
            pojo.setValor2(Validador.getMoney(input.getString("valor22")));
            pojo.setValor3(Validador.getMoney(input.getString("valor33")));
            pojo.setValor4(Validador.getMoney(input.getString("valor44")));
            pojo.setValor5(Validador.getMoney(input.getString("valor55")));
            pojo.setValor6(Validador.getMoney(input.getString("valor66")));
            pojo.setValor7(Validador.getMoney(input.getString("valor77")));
            pojo.setPerfilAcesso(new PerfilAcesso());
            if (input.getValue("idHorario") != null && input.getLong("idHorario") > 0) {
                pojo.getPerfilAcesso().setId(input.getLong("idHorario"));
                pojo.getPerfilAcesso().setPerfilAcesso(input.getString("identificacao"));
                pojo.getPerfilAcesso().setFaixas(getFaixas(""));
            } else {
                pojo.getPerfilAcesso().setPerfilAcesso(input.getString("identificacaoNovo"));
                pojo.getPerfilAcesso().setFaixas(getFaixas("Novo"));
            }
            ServiceLocator.getModalidadeService().update(pojo);
            session.setAttribute("mensagem", "Modalidade alterada com sucesso!");

            consequence = SUCCESS;
        } else {
            //aqui verifica novamente se acaso ele ja foi post e passou pela validação
            if (!isPost()) {
                ModalidadeAction.preload(output, id);

                Modalidade pojo = ServiceLocator.getModalidadeService().readById(id);
                output.setValue("pojo", pojo);
                output.setValue("todos", false);
                output.setValue("domingo", false);
                output.setValue("segunda", false);
                output.setValue("terca", false);
                output.setValue("quarta", false);
                output.setValue("quinta", false);
                output.setValue("sexta", false);
                output.setValue("sabado", false);
                output.setValue("feriado", false);
                output.setValue("horarioImport", 2);

                boolean domingo = false;
                boolean segunda = false;
                boolean terca = false;
                boolean quarta = false;
                boolean quinta = false;
                boolean sexta = false;
                boolean sabado = false;
                boolean feriado = false;

                if (pojo.getPerfilAcesso() != null) {
                    PerfilAcesso perfil = pojo.getPerfilAcesso();
                    output.setValue("idHorario", perfil.getId());
                    output.setValue("identificacao", perfil.getPerfilAcesso());
                    for (Faixa faixa : perfil.getFaixas()) {
                        String dia = "";
                        if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase("domingo")) {
                            dia = "domingo";
                            output.setValue(dia, true);
                            domingo = true;
                            setRetornoFaixa(dia, faixa);
                        } else if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase("segunda")) {
                            dia = "segunda";
                            output.setValue(dia, true);
                            segunda = true;
                            setRetornoFaixa(dia, faixa);
                        } else if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase("terça")) {
                            dia = "terca";
                            output.setValue(dia, true);
                            terca = true;
                            setRetornoFaixa(dia, faixa);
                        } else if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase("quarta")) {
                            dia = "quarta";
                            output.setValue(dia, true);
                            quarta = true;
                            setRetornoFaixa(dia, faixa);
                        } else if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase("quinta")) {
                            dia = "quinta";
                            output.setValue(dia, true);
                            quinta = true;
                            setRetornoFaixa(dia, faixa);
                        } else if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase("sexta")) {
                            dia = "sexta";
                            output.setValue(dia, true);
                            sexta = true;
                            setRetornoFaixa(dia, faixa);
                        } else if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase("sábado")) {
                            dia = "sabado";
                            output.setValue(dia, true);
                            sabado = true;
                            setRetornoFaixa(dia, faixa);
                        } else if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase("feriado")) {
                            dia = "feriado";
                            output.setValue(dia, true);
                            feriado = true;
                            setRetornoFaixa(dia, faixa);
                        }
                    }
                }
                if (domingo && segunda && terca && quarta && quinta && sexta && sabado && feriado) {
                    output.setValue("todos", true);
                }
                String[] dispos = null;

                Map<Long, String> dispositivosOffLine = new HashMap<Long, String>();
                Map<Long, String> mapaDispositivos = ServiceLocator.getDispositivoService().readListImages();
                if (pojo.getPerfilAcesso() != null && pojo.getPerfilAcesso().getFaixas() != null) {
                    if (pojo.getPerfilAcesso().getFaixas().size() > 0) {
                        List<Dispositivo> dispositivos = pojo.getPerfilAcesso().getFaixas().get(0).getDispositivos();
                        dispos = new String[dispositivos.size()];
                        int i = 0;
                        for (Dispositivo dis : dispositivos) {
                            dispos[i] = String.valueOf(dispositivos.get(i).getId());
                            if (!mapaDispositivos.containsKey(dis.getId())) {
                                dispositivosOffLine.put(dis.getId(), (dis.getDispositivo() == null || dis.getDispositivo().isEmpty() ? dis.getEnderecoIp() : dis.getDispositivo()));
                            }
                            i++;
                        }
                    }
                }
                if (dispos == null) {
                    dispos = new String[1];
                }

                output.setValue("horariosImportar", true);// para vir a aba dos horarios importados aberta

                output.setValue("dispositivos", mapaDispositivos);
                output.setValue("dispositivosOffLine", dispositivosOffLine);
                output.setValue("dispositivo", dispos);

                if (pojo.getValor1() != null) {
                    output.setValue("valor11", Validador.getMoney(pojo.getValor1()));
                }
                if (pojo.getValor2() != null) {
                    output.setValue("valor22", Validador.getMoney(pojo.getValor2()));
                }
                if (pojo.getValor3() != null) {
                    output.setValue("valor33", Validador.getMoney(pojo.getValor3()));
                }
                if (pojo.getValor4() != null) {
                    output.setValue("valor44", Validador.getMoney(pojo.getValor4()));
                }
                if (pojo.getValor5() != null) {
                    output.setValue("valor55", Validador.getMoney(pojo.getValor5()));
                }
                if (pojo.getValor6() != null) {
                    output.setValue("valor66", Validador.getMoney(pojo.getValor6()));
                }
                if (pojo.getValor7() != null) {
                    output.setValue("valor77", Validador.getMoney(pojo.getValor7()));
                }
            }
        }
        return consequence;
    }

    private List<Faixa> getFaixas(String novo) {
        String[] dispositivos = input.getStrings("dispositivo");
        List<Dispositivo> catracas = new ArrayList<Dispositivo>();
        if (dispositivos != null) {
            for (String id : dispositivos) {
                catracas.add(ServiceLocator.getDispositivoService().readById(Long.parseLong(id)));
            }
        }
        List<Faixa> lista = new ArrayList<Faixa>();
        for (int i = 0; i <= 7; i++) {
            String dia = "";
            switch (i) {
                case 0:
                    dia = DiaDaSemana.DOMINGO.toString().toLowerCase();
                    break;
                case 1:
                    dia = DiaDaSemana.SEGUNDA.toString().toLowerCase();
                    break;
                case 2:
                    dia = DiaDaSemana.TERCA.toString().toLowerCase();
                    break;
                case 3:
                    dia = DiaDaSemana.QUARTA.toString().toLowerCase();
                    break;
                case 4:
                    dia = DiaDaSemana.QUINTA.toString().toLowerCase();
                    break;
                case 5:
                    dia = DiaDaSemana.SEXTA.toString().toLowerCase();
                    break;
                case 6:
                    dia = DiaDaSemana.SABADO.toString().toLowerCase();
                    break;
                case 7:
                    dia = DiaDaSemana.FERIADO.toString().toLowerCase();
                    break;
            }
            String checado = dia + novo;
            if (input.getValue(checado) != null) {
                setFaixa(dia, novo, catracas, lista);
            }
        }
        return lista;
    }

    public void setRetornoFaixa(String dia, Faixa faixa) {
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

    public void setFaixa(String dia, String novo, List<Dispositivo> catracas, List<Faixa> lista) {
        for (int i = 1; i <= 8; i++) {
            String horaUm = dia + i + novo;
            String horaDois = dia + (++i) + novo;//i incrementado para a segunda coluna q contem o segundo horário
            if (input.getValue(horaUm) != null && input.getValue(horaDois) != null) {
                if (!input.getString(horaUm).isEmpty() && !input.getString(horaDois).isEmpty()) {
                    Faixa faixa = new Faixa();
                    faixa.setDiaSemana(ServiceLocator.getDiaSemanaService().readByName(dia));
                    faixa.setHorarioInicio(CalendarUtil.setTimeCalendar(input.getString(horaUm)));
                    faixa.setHorarioFim(CalendarUtil.setTimeCalendar(input.getString(horaDois)));
                    faixa.setDispositivos(catracas);
                    lista.add(faixa);
                }
            }
        }
    }
}