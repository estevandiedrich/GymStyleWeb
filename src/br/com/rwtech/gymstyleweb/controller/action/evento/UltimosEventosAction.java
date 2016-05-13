package br.com.rwtech.gymstyleweb.controller.action.evento;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Evento;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import java.util.List;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class UltimosEventosAction extends BaseAction {

    private static int TAM = 20;

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;
        if (isLogged()) {
            consequence = SUCCESS;
            Long ultimo = (Long) session.getAttribute("ultimoEvento");
            if (ultimo == null || ultimo == -1) {
                ultimo = new Long(0);
            }
            List<Evento> lista = ServiceLocator.getEventoService().readUltimosEventos();
//            List<Evento> lista = new ArrayList<Evento>();
            String div = "<ul>";
            Long ultimoId = ultimo;
            if (!lista.isEmpty()) {
                int i = 0;
                for (Evento evento : lista) {
                    if (evento.getId() > ultimo) {
                        div += "<li id='linhaEvento" + i + "' style='display: none;padding-left:3px' ";
                        if (evento.getId() > ultimoId) {
                            ultimoId = evento.getId();
                        }
                    } else {
                        div += "<li style='padding-left:3px' ";
                    }
                    div += "class='" + (i % 2 == 0 ? "row1" : "row2") + "'>";
                    String nome = "";
                    if (evento.getDispositivo() != null && evento.getDispositivo().getDispositivo() != null
                            && !evento.getDispositivo().getDispositivo().isEmpty()) {
                        nome = evento.getDispositivo().getDispositivo();
                    } else {
                        nome = evento.getDispositivo().getEnderecoIp();
                    }
                    if (evento.getMotivo().getMotivo().equalsIgnoreCase("Passagem Liberada na Catraca")) {
                        if (evento.getRealizado()) {
                            div += " <img src='images/ok.png' title='Catraca: " + nome + "' />";
                        } else {
                            div += " <img src='images/ok_red.png' title='Catraca: " + nome + " - Liberado, Não Realizado!' />";
                        }
                    } else if (evento.getMotivo().getMotivo().equalsIgnoreCase("Liberado")) {
                        if (evento.getRealizado()) {
                            div += " <img src='images/ok.png' title='Catraca: " + nome + "' />";
                        } else {
                            div += " <img src='images/ok_red.png' title='Catraca: " + nome + " - Liberado, Não Realizado!' />";
                        }
                    } else {
                        if (!evento.getMotivo().getMotivo().startsWith("Cartão Inválido")) {
                            div += " <img src='images/errorPeq.png' title='" + evento.getMotivo().getMotivo()
                                    + " - Catraca:" + nome + "' style='cursor:help;'/>";
                        }
                    }
                    String usuario = "";
                    Usuario user = null;
                    if (evento.getUsuario() != null) {
                        user = evento.getUsuario();
                    }
                    if (user != null && user.getUsuario() != null) {
                        usuario = user.getUsuario();
                        if (usuario != null && !usuario.isEmpty()) {
                            while (usuario.length() > TAM) {
                                usuario = usuario.substring(0, usuario.lastIndexOf(" "));
                            }
                        }
                    }
                    if (usuario.isEmpty()) {
                        if (evento.getMotivo().getMotivo().equalsIgnoreCase("Passagem Liberada na Catraca")) {
                            div += "<font style='font-size:11px'>Passagem Liberada</font>";
                        } else if (evento.getMotivo().getMotivo().equalsIgnoreCase("Liberado")) {
                            div += "<font style='font-size:11px'>" + evento.getMotivo().getMotivo() + "</font>";
                        } else {
                            div += "<font class='error' style='font-size:11px'> " + evento.getMotivo().getMotivo() + "</font>";
                        }
                    } else {
                        div += "<strong style='font-size:11px'>";
                        if (user.isAluno() && user.getAtivoAluno()) {
                            Boolean bool = (Boolean) getSession().getAttribute(Ac.PAGAMENTO_ULTIMO_PLANOS_READ);
                            if (bool != null && bool) {
                                div += "<a href='pagamentosPlanoUsuario.do?idUsuario=" + user.getId() + "' title='Ver Histórico' >"
                                        + "<font>" + usuario + "</font></a>";
                            } else {
                                div += "<font>" + usuario + "</font>";
                            }
                        } else {
                            div += "<font>" + usuario + "</font>";
                        }
                        div += "</strong>";
                    }

                    String motivo = evento.getMotivo().getMotivo();
                    if (!motivo.equalsIgnoreCase("Impressão Digital Inválida") && !motivo.equalsIgnoreCase("Violação da Catraca")) {
                        if (evento.getEntrada()) {
                            div += " <img src='images/entrada.png' title='Entrada - " + nome + "' style='cursor:help;'/>";
                        } else {
                            div += " <img src='images/saida.png' title='Saída - " + nome + "' style='cursor:help;'/>";
                        }
                    }

                    div += "<p>" + CalendarUtil.getDateCalendar(evento.getDataHora()) + " - " + CalendarUtil.getHorMinSegCalendar(evento.getDataHora()) + "</p>";
                    div += "</li>";
                    i++;
                    if (i == 6) {
                        break;
                    }
                }
            } else {
                div += "<li>";
                div += "<p>Não houve acessos.</p>";
                div += "</li>";
            }
            div += "</ul>";

            div += "<input type='hidden' name='ultimo' id='ultimo' value='" + ultimoId + "'/>";
            session.setAttribute("ultimoEvento", ultimoId);
            output.setValue("ultimosAcessos", div);
        }
        return consequence;
    }
}