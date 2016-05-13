package br.com.rwtech.gymstyleweb.controller.action.funcionario.update;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Acesso;
import br.com.rwtech.gymstylecore.model.pojo.Dispositivo;
import br.com.rwtech.gymstylecore.model.pojo.Faixa;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import br.com.rwtech.gymstyleweb.controller.action.funcionario.FuncionarioAction;
import br.com.rwtech.gymstyleweb.controller.action.requisicao.RequisicaoUsuarioAction;
import br.com.rwtech.gymstyleweb.controller.action.usuario.UsuarioAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mentawai.core.BaseAction;
import org.mentawai.core.Input;
import org.mentawai.core.Output;

/**
 *
 * @author Software1
 */
public class FuncionarioUpdateAcessoAction extends BaseAction {

    private static String DOM = "domingo";
    private static String SEG = "segunda";
    private static String TER = "terca";
    private static String QUA = "quarta";
    private static String QUI = "quinta";
    private static String SEX = "sexta";
    private static String SAB = "sabado";
    private static String FER = "feriado";

    @Override
    public String execute() throws Exception {
        Long idUsuario = input.getLong("id");
        Long idAcesso = input.getLong("idAcesso");
        String mudouFaixas = (String) input.getValue("mudouFaixas");
        Boolean mudou = false;

        String consequence = SHOW;
        if (idUsuario != null) {
            if (isPost() && input.getValue("temAcesso") != null && !input.getString("temAcesso").isEmpty()) {

                if (input.getValue(FuncionarioAction.ACAO) != null && input.getString(FuncionarioAction.ACAO).equals(FuncionarioAction.IDENTIFICACAO)) {
                    return FuncionarioAction.IDENTIFICACAO;
                }

                boolean create = true;
                Acesso acesso = null;
                Acesso acessoOriginal = null;
                if (idAcesso != null && idAcesso > 0) {
                    acesso = ServiceLocator.getAcessoService().readById(idAcesso);
                    acessoOriginal = acesso.clone();
                    create = false;
                } else {
                    acesso = new Acesso();
                }

                acesso.setDispositivos(getDispositivos());
                if (input.getString("temAcesso").equalsIgnoreCase("sim")) {
                    //Sempre seto as faixas quando nullo ou nao for livre
                    if (input.getValue("livre") == null || !input.getBoolean("livre")) {
                        acesso.setFaixas(getFaixas());
                        acesso.setLivre(Boolean.FALSE);
                    } else {
                        acesso.setLivre(Boolean.TRUE);
                    }
                    if (create) {
                        ServiceLocator.getAcessoService().create(acesso, idUsuario);
                        mudou = true;
                    } else {
                        mudou = mudouAcesso(mudouFaixas, acessoOriginal, acesso);
                        if (mudou) {
                            ServiceLocator.getAcessoService().update(acesso);
                        }
                    }
                } else {
                    //Se tinha acesso eu chamo o excluir
                    if (idAcesso != null && idAcesso > 0) {
                        ServiceLocator.getAcessoService().delete(idUsuario, idAcesso);
                        mudou = true;
                    }
                }

                if (mudou) {
                    //Se mudou, tem q atualizar funcionario, mesmo para enviar o comando de exclusão na catraca acaso nao tem planos ou acesso
                    if (RequisicaoUsuarioAction.atualizar(idUsuario, output, (Usuario) getUserSession())) {
                        output.setValue("retorno", Ac.FUNCIONARIO_READ + ".do");//Retorno default
                        consequence = UsuarioAction.ATUALIZAR;
                    }

                } else {
                    session.setAttribute(FuncionarioAction.MENSAGEM, "Acesso alterado com sucesso!");
                    consequence = SUCCESS;
                }

            } else {
                output.setValue("id", idUsuario);
                //Dentro do preload, buscará as digitais da tabela digitais_espelho
                this.preload(output, input);

                Acesso pojo = ServiceLocator.getAcessoService().readByIdUsuario(idUsuario);
                if (pojo != null && pojo.getAtivo()) {
                    if (pojo.getFaixas() != null) {
                        for (Faixa faixa : pojo.getFaixas()) {
                            if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase(DOM)) {
                                setRetornoFaixa(DOM, faixa);
                            } else if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase(SEG)) {
                                setRetornoFaixa(SEG, faixa);
                            } else if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase("terça")) {
                                setRetornoFaixa(TER, faixa);
                            } else if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase(QUA)) {
                                setRetornoFaixa(QUA, faixa);
                            } else if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase(QUI)) {
                                setRetornoFaixa(QUI, faixa);
                            } else if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase(SEX)) {
                                setRetornoFaixa(SEX, faixa);
                            } else if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase("sábado")) {
                                setRetornoFaixa(SAB, faixa);
                            } else if (faixa.getDiaSemana().getDiaSemana().toLowerCase().toString().equalsIgnoreCase(FER)) {
                                setRetornoFaixa(FER, faixa);
                            }
                        }
                    }

                    if (pojo.getDispositivos() != null && !pojo.getDispositivos().isEmpty()) {
                        String[] dispos = null;

                        List<Dispositivo> dispositivos = pojo.getDispositivos();
                        dispos = new String[dispositivos.size()];
                        int i = 0;
                        for (Dispositivo dis : dispositivos) {
                            dispos[i] = String.valueOf(dis.getId());
                            i++;
                        }
                        if (dispos == null) {
                            dispos = new String[1];
                        }
                        output.setValue("dispositivo", dispos);
                    }
                    output.setValue("idAcesso", pojo.getId());
                    output.setValue("acesso", pojo);
                } else {
                    output.setValue("temAcesso", "nao");
                }
            }
        } else {
            consequence = Ac.EXCEPTION;
            session.setAttribute("mensagem", "Não foi possível Alterar. Tente novamente!");
        }
        return consequence;
    }

    /*
     * Confere se original for null é pq est criando
     * Se faixas mudou, retorna true
     * se livre ou dispositivos foi alterado, retorna true
     */
    private Boolean mudouAcesso(String mudouDigitais, Acesso original, Acesso atual) {
        //É pq esta criando
        if (original == null) {
            return Boolean.TRUE;
        } else if (!mudouDigitais.isEmpty()) {
            return Boolean.TRUE;
        } else if (!original.equals(atual)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private List<Dispositivo> getDispositivos() {
        String[] dispositivos = input.getStrings("dispositivo");
        List<Dispositivo> list = new ArrayList<Dispositivo>();
        if (dispositivos != null) {
            for (String id : dispositivos) {
                list.add(ServiceLocator.getDispositivoService().readById(Long.parseLong(id)));
            }
        }
        return list;
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
        for (int i = 0; i <= 7; i++) {
            String dia = "";
            switch (i) {
                case 0:
                    dia = DOM;
                    break;
                case 1:
                    dia = SEG;
                    break;
                case 2:
                    dia = TER;
                    break;
                case 3:
                    dia = QUA;
                    break;
                case 4:
                    dia = QUI;
                    break;
                case 5:
                    dia = SEX;
                    break;
                case 6:
                    dia = SAB;
                    break;
                case 7:
                    dia = FER;
                    break;
            }
            setFaixa(dia, catracas, lista);
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

    public void setFaixa(String dia, List<Dispositivo> catracas, List<Faixa> lista) {
        //anda de dois em dois para pegar Inicio e Fim
        for (int i = 1; i <= 8; i++) {
            String horaUm = dia + i;
            String horaDois = dia + (++i);//i incrementado para a segunda coluna q contem o segundo horário
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

    public void setMensagem() {
        String msg = "";
        if (session.getAttribute("mensagem") != null) {
            msg = session.getAttribute("mensagem").toString();
            session.setAttribute("mensagem", "");
        }
        if (!msg.equalsIgnoreCase("")) {
            addMessage(msg);
        }
    }

    public static void preload(Output output, Input input) {
        Long id = (Long) output.getValue("id");
        if (id != null) {
            if (id != null && id > 0) {
                Usuario pojo = ServiceLocator.getUsuarioService().readById(id);
                output.setValue("pojoInfo", pojo);

                Map<Long, String> mapaDispositivos = ServiceLocator.getDispositivoService().readListImages();

                Acesso acesso = ServiceLocator.getAcessoService().readByIdUsuario(id);
                if (acesso != null && acesso.getAtivo()) {
                    if (acesso.getDispositivos() != null && !acesso.getDispositivos().isEmpty()) {
                        String[] dispos = null;

                        Map<Long, String> dispositivosOffLine = new HashMap<Long, String>();
                        List<Dispositivo> dispositivos = acesso.getDispositivos();
                        dispos = new String[dispositivos.size()];
                        int i = 0;
                        for (Dispositivo dis : dispositivos) {
                            dispos[i] = String.valueOf(dispositivos.get(i).getId());
                            if (!mapaDispositivos.containsKey(dis.getId())) {
                                dispositivosOffLine.put(dis.getId(), (dis.getDispositivo() == null || dis.getDispositivo().isEmpty() ? dis.getEnderecoIp() : dis.getDispositivo()));
                            }
                            i++;
                        }
                        if (dispos == null) {
                            dispos = new String[1];
                        }
                        output.setValue("dispositivosOffLine", dispositivosOffLine);
                        //output.setValue("dispositivo", dispos);
                    }
                }
                output.setValue("dispositivos", mapaDispositivos);

            }
        }
    }
}
