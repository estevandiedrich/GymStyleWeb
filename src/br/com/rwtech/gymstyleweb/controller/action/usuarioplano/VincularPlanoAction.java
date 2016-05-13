package br.com.rwtech.gymstyleweb.controller.action.usuarioplano;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Pagamento;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstylecore.model.util.Validador;
import br.com.rwtech.gymstyleweb.controller.ApplicationManager;
import java.util.ArrayList;
import java.util.Calendar;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class VincularPlanoAction extends BaseAction {

    //private Boolean postergar = false;
    public static String ATUALIZAR = "ATUALIZAR";

    @Override
    public String execute() throws Exception {
        Long idUsuario = (input.getLong("id"));
        String consequence = SHOW;
        if (isPost()) {
            Plano plano = getPlanos();
            ServiceLocator.getUsuarioPlanoService().create(plano, idUsuario);
            consequence = SUCCESS;
            session.setAttribute("mensagem", "Plano vinculado com sucesso!");
        } else {
            output.setValue("diaVencimento", ServiceLocator.getConfiguracaoService().readByCampo("diaVencimento").getValor());
            output.setValue("tolerancia", ServiceLocator.getConfiguracaoService().readByCampo("tolerancia").getValor());
            if (input.getValue("formasDeDesconto") == null) {
                output.setValue("formasDeDesconto", "%");
            }

            Usuario usuario = ServiceLocator.getUsuarioService().readById(idUsuario);
            if (usuario != null) {
                output.setValue("usuario", usuario);
                output.setValue("cpf", usuario.getCpf());//não precisar ler novamente ao usuário.
                output.setValue("planos", ServiceLocator.getPlanoService().readList());
                output.setValue("periodos", ServiceLocator.getDuracaoPlanoService().readList());
            } else {
                consequence = ApplicationManager.EXCEPTION;
            }
        }
        return consequence;
    }

    public Plano getPlanos() {
        Plano plano = ServiceLocator.getPlanoService().readById(input.getLong("planoSelect"));
        if (plano != null) {
            if (input.getValue("periodoSelect") != null) {
                plano.setDuracaoPlano(ServiceLocator.getDuracaoPlanoService().readById(input.getLong("periodoSelect")));
            }
            int i = 1;//primeira parcela
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
                        pagamento.setTolerancia(Integer.parseInt(input.getString("tolerancia")));
                        pagamento.setVencimento(Calendar.getInstance());
                        pagamento.setValor(Validador.getMoney(input.getString("valorTotal" + i)));

                        pagamento.setInicioAcesso(pagamento.getVencimento());
                        if (tipo.equalsIgnoreCase("Diário")) {
                            pagamento.setFimAcesso(Calendar.getInstance());
                            Double val = new Double(input.getString("valorTotal"));
                            pagamento.setValor(val / 30);
                            //pagamento.getFimAcesso().add(Calendar.DAY_OF_MONTH, 1);//aqui ele vai adicionar um dia de acesso
                        } else if (tipo.equalsIgnoreCase("Semanal")) {
                            pagamento.setFimAcesso(Calendar.getInstance());
                            pagamento.getFimAcesso().add(Calendar.DAY_OF_MONTH, 6);//aqui ele vai adicionar 6 dias de acesso mais o atual
                            Double val = new Double(input.getString("valorTotal"));
                            pagamento.setValor(val / 4);
                        } else if (tipo.equalsIgnoreCase("Quinzenal")) {
                        	pagamento.setFimAcesso(Calendar.getInstance());
                            pagamento.getFimAcesso().add(Calendar.DAY_OF_MONTH, 12);//aqui ele vai adicionar 6 dias de acesso mais o atual
                            Double val = new Double(input.getString("valorTotal"));
                            pagamento.setValor(val / 2);
                        } else {
                            //Se for a primeira parcela e plano mensal,seta-se com numero de parcela 0 
                            //para dizer q é a primeira parcela do aluno noplano mensal
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
                        pagamento.getFimAcesso().add(Calendar.MONTH, +1);
                        pagamento.getFimAcesso().add(Calendar.DAY_OF_MONTH, pagamento.getTolerancia());//aqui ele vai adicionar a tolerancia no periodo
                    }
                    plano.getPagamentos().add(pagamento);
                } else {
                    break;
                }
                i++;
            }
        }
        output.setValue("selecionado", "usuarioPlanoRead");

        return plano;
    }
}
