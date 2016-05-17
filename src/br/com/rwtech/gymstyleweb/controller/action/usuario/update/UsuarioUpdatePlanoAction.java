package br.com.rwtech.gymstyleweb.controller.action.usuario.update;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Pagamento;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
import br.com.rwtech.gymstylecore.model.pojo.Requisicao;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.pojo.tipos.TipoRequisicaoResposta;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstylecore.model.util.StatusPlano;
import br.com.rwtech.gymstylecore.model.util.Validador;
import br.com.rwtech.gymstyleweb.controller.action.usuario.UsuarioAction;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import org.mentawai.core.BaseAction;
import org.mentawai.core.Input;
import org.mentawai.core.Output;

/**
 *
 * @author Éder Faria
 */
public class UsuarioUpdatePlanoAction extends BaseAction {

    public static String SELECT_PLANO = "planoSelect";
    public static String SELECT_PERIODO = "periodoSelect";
    public static String TOLERANCIA = "tolerancia";
    public static String DIA_VENCIMENTO = "diaVencimento";
    public static String VINCULAR = "vincularPlanoUsuario";
    public static String MES = "mesPagamento";
    private Plano planoPrivate = null;

    @Override
    public String execute() throws Exception {
        Long idUsuario = input.getLong("id");
        String cpf = (String) input.getValue("cpf");
        String consequence = SHOW;
        if (idUsuario != null) {
            if (isPost() && input.getValue(VINCULAR) != null) {

                if (input.getValue("acao") != null && input.getString("acao").equals(UsuarioAction.IDENTIFICACAO)) {
                    return UsuarioAction.IDENTIFICACAO;
                }

                Plano p = null;
                if (input.getValue(SELECT_PLANO) != null && !input.getString(SELECT_PLANO).isEmpty()) {
                    p = getPlano();//quando chamado o getPlanos, ele instancia o planoPrivate
                    consequence = UsuarioAction.PAGAMENTOS;//para ir para a tela de pagamentos no retorno se acaso o usuário vinculou um novo plano
                    ServiceLocator.getUsuarioPlanoService().create(p, idUsuario);
                    output.setValue("idPlanoUsuario", ServiceLocator.getUsuarioPlanoService().readIdUltimoPlanoUsuario(idUsuario));//esse id será passado para o manager de requisicao buscar os dispositivos a serem atualizados
                    session.setAttribute("mensagem", "Plano vinculado com sucesso!");
                }

                if (p == null) {//vai entrar aqui se acaso NAO entrou em vincular planos
                    consequence = SUCCESS;
                }

            } else {
                output.setValue("id", idUsuario);
                //Dentro do preload, buscará as digitais da tabela digitais_espelho
                this.preload(output, input);
                if (output.getValue("pojoInfo") == null) {
                    consequence = UsuarioAction.INESPERADO;
                    session.setAttribute("mensagem", "Não foi possível Alterar. Tente novamente!");
                }
            }
        } else {
            consequence = UsuarioAction.INESPERADO;
            session.setAttribute("mensagem", "Não foi possível Alterar. Tente novamente!");
        }
        return consequence;
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

    public Plano getPlano() {
        Plano plano = ServiceLocator.getPlanoService().readById(input.getLong(SELECT_PLANO));
        if (plano != null) {
            if (input.getValue(SELECT_PERIODO) != null) {
                plano.setDuracaoPlano(ServiceLocator.getDuracaoPlanoService().readById(input.getLong(SELECT_PERIODO)));
            }
            int i = 1;
            Calendar dataAtual = CalendarUtil.getDateCurrent();
            int anoAtual = dataAtual.get(Calendar.YEAR);
            int diaAtual = dataAtual.get(Calendar.DAY_OF_MONTH);
            plano.setPagamentos(new ArrayList<Pagamento>());
            while (true) {
                if (input.getValue("vencimento" + i) != null) {
                    Pagamento pagamento = new Pagamento();
                    pagamento.setNumeroParcela(i);
                    pagamento.setPostergar(Boolean.FALSE);
                    String tipo = plano.getDuracaoPlano().getDuracao();
                    if (i == 1) {
                        pagamento.setTolerancia(Integer.parseInt(input.getString(TOLERANCIA)));
                        pagamento.setVencimento(Calendar.getInstance());

                        pagamento.setValor(Validador.getMoney(input.getString("valorTotal" + i)));

                        pagamento.setInicioAcesso(pagamento.getVencimento());
                        if (tipo.equalsIgnoreCase("Diário")) {
                            pagamento.setFimAcesso(Calendar.getInstance());
                            Double val = new Double(input.getString("valorTotal"));//Ja vem no formato para Double
                            pagamento.setValor(val / 30);
                            //pagamento.getFimAcesso().add(Calendar.DAY_OF_MONTH, 1);//aqui ele vai adicionar um dia de acesso
                        } else if (tipo.equalsIgnoreCase("Semanal")) {
                            pagamento.setFimAcesso(Calendar.getInstance());
                            pagamento.getFimAcesso().add(Calendar.DAY_OF_MONTH, 6);//aqui ele vai adicionar 6 dias de acesso mais o atual
                            Double val = new Double(input.getString("valorTotal"));//Ja vem no formato para Double
                            pagamento.setValor(val / 4);
                        } else if (tipo.equalsIgnoreCase("Quinzenal")){
                        	pagamento.setFimAcesso(Calendar.getInstance());
                            pagamento.getFimAcesso().add(Calendar.DAY_OF_MONTH, 14);//aqui ele vai adicionar 6 dias de acesso mais o atual
                            Double val = new Double(input.getString("valorTotal"));//Ja vem no formato para Double
                            pagamento.setValor(val / 2);
                        } else {
                            //Se for a primeira parcela e plano mensal,seta-se com numero de parcela 0 
                            //para dizer q é a primeira parcela do aluno no plano mensal
                            if (tipo.equalsIgnoreCase("Mensal")) {
                                pagamento.setNumeroParcela(0);//Referente a primeira parcela
                            }
                            int diaDeVencimentoAtual = input.getInt("diaVencimento");
                            pagamento.setFimAcesso(CalendarUtil.getDateByDay(diaDeVencimentoAtual));
                            //se dia de vencimento for igual ou maior q o dia atual, somamos fim de acesso para o proximo mes
                            pagamento.getFimAcesso().add(Calendar.MONTH, 1);
                            pagamento.getFimAcesso().add(Calendar.DAY_OF_MONTH, pagamento.getTolerancia());
                        }
                    } else {
                        //Plano mensal a primeira parcela será com numero 0 e as demais com numero 1. Novas parcelas com 2
                        if (tipo.equalsIgnoreCase("Mensal")) {
                            pagamento.setNumeroParcela(1);//Referente a primeira parcela
                        }
                        pagamento.setValor(Validador.getMoney(input.getString("valorTotal" + i)));
                        pagamento.setTolerancia(Integer.parseInt(input.getString("tolerancia")));

                        //if
                        pagamento.setVencimento(CalendarUtil.setDateCalendar(input.getString("vencimento" + i) + "/" + anoAtual));
                        if (pagamento.getVencimento().get(Calendar.MONTH) == 0) {
                            anoAtual++;
                        }
                        pagamento.setInicioAcesso(CalendarUtil.setDateCalendar(input.getString("vencimento" + i) + "/" + anoAtual));
                        pagamento.setFimAcesso(CalendarUtil.setDateCalendar(input.getString("vencimento" + i) + "/" + anoAtual));
                        pagamento.getFimAcesso().add(Calendar.MONTH, 1);
                        pagamento.getFimAcesso().add(Calendar.DAY_OF_MONTH, pagamento.getTolerancia());//aqui ele vai adicionar a tolerancia no periodo
                    }
                    plano.getPagamentos().add(pagamento);
                } else {
                    break;
                }
                i++;
            }
            planoPrivate = plano;
        }
        return plano;
    }

    public static void preload(Output output, Input input) {
        Long id = (Long) output.getValue("id");
        if (id != null) {
            Map planos = ServiceLocator.getPlanoService().readList();
            output.setValue("planos", planos);
            if (planos.isEmpty()) {
                output.setValue("planosVazios", true);
            } else {
                output.setValue("planosVazios", false);
            }
            output.setValue("periodos", ServiceLocator.getDuracaoPlanoService().readList());
            if (input.getValue(DIA_VENCIMENTO) == null) {
                output.setValue(DIA_VENCIMENTO, ServiceLocator.getConfiguracaoService().readByCampo(DIA_VENCIMENTO).getValor());
            }
            if (input.getValue(TOLERANCIA) == null) {
                output.setValue(TOLERANCIA, ServiceLocator.getConfiguracaoService().readByCampo(TOLERANCIA).getValor());
            }

            if (input.getValue("formasDeDesconto") == null) {
                output.setValue("formasDeDesconto", "%");
            }

            //pegar o mes corrente para ser mostrado na primeira parcela
            int mes = (Calendar.getInstance().get(Calendar.MONTH));
            mes++;
            mes++;
            if (mes < 10) {
                output.setValue(MES, "0" + mes);
            } else {
                if (mes == 13) {
                    mes = 1;
                }
                output.setValue(MES, mes);
            }

            if (id > 0) {
                Usuario pojo = ServiceLocator.getUsuarioService().readById(id);
                output.setValue("pojoInfo", pojo);//pojo é para aparecer as informações do usuário em cima na tela
                pojo.setPlanos(ServiceLocator.getUsuarioPlanoService().readPlanosToUsuario(id, Boolean.TRUE));

                if (pojo != null) {
                    output.setValue(VINCULAR, true);
                    //para aparecer a opção de vincular plano ao usuário no evento alterar usuário
                    Plano plano = ServiceLocator.getUsuarioPlanoService().readUltimoPlano(pojo.getId());
                    if (plano != null) {
                        if (!plano.getCancelado()) {
                            if (plano.getFinalizado()) {
                                output.setValue("planoQuitado", "Plano Quitado.");
                            }
                            if (!plano.getFinalizado()) {
                                output.setValue(VINCULAR, false);
                                output.setValue("infoPlano", plano);
                            }
                        }
                    }
                }
            }
        }
    }
}