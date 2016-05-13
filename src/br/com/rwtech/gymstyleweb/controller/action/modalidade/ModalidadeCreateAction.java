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
import java.util.List;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Eder Faria
 */
public class ModalidadeCreateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;

        String[] dispositivos = input.getStrings("dispositivo");
        if (isPost() && dispositivos != null) {
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
            ServiceLocator.getModalidadeService().create(pojo);
            session.setAttribute("mensagem", "Modalidade criada com sucesso!");
            consequence = SUCCESS;
        } else {
            ModalidadeAction.preload(output,null);
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

    public void setFaixa(String dia, String novo, List<Dispositivo> catracas, List<Faixa> lista) {
        //anda de dois em dois para pegar Inicio e Fim
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