package br.com.rwtech.gymstyleweb.controller.action.usuarioplano;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.DuracaoPlano;
import br.com.rwtech.gymstylecore.model.pojo.Pagamento;
import br.com.rwtech.gymstylecore.model.util.Validador;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class ParcelasAjaxAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        int tolerancia = 0;
        if (input.getValue("tolerancia") != null && input.getFloat("tolerancia") != -1) {
            tolerancia = input.getInt("tolerancia");
        }
        List<Pagamento> lista = new ArrayList<Pagamento>();
        int total = 1;
        Long id = input.getLong("periodoSelect");
        total = 0;
        DuracaoPlano periodo = ServiceLocator.getDuracaoPlanoService().readById(id);
        if (periodo.getDuracao().equalsIgnoreCase("Diario")) {
            total = 0;
        } else if (periodo.getDuracao().equalsIgnoreCase("Semanal")) {
            total = 0;
        } else if (periodo.getDuracao().equalsIgnoreCase("Mensal")) {
            total = 2;
        } else if (periodo.getDuracao().equalsIgnoreCase("Bimestral")) {
            total = 2;
        } else if (periodo.getDuracao().equalsIgnoreCase("Trimestral")) {
            total = 3;
        } else if (periodo.getDuracao().equalsIgnoreCase("Quadrimestral")) {
            total = 4;
        } else if (periodo.getDuracao().equalsIgnoreCase("Semestral")) {
            total = 6;
        } else if (periodo.getDuracao().equalsIgnoreCase("Anual")) {
            total = 12;
        } else if (periodo.getDuracao().equalsIgnoreCase("Quinzenal")) {
        	total = 0;
        }
        Calendar dataAtual = Calendar.getInstance();
        int diaAtual = dataAtual.get(Calendar.DAY_OF_MONTH);
        int mesAtual = dataAtual.get(Calendar.MONTH);

        int diaVencimento = 0;
        if (input.getValue("diaVencimento") != null && input.getFloat("diaVencimento") != -1) {
            diaVencimento = input.getInt("diaVencimento");
        }
        if (diaAtual > input.getInt("diaVencimento")) {
            mesAtual = mesAtual + 1;//por causa do vetor iniciado em zero e mais um devolver o mes atual
        } else {
            mesAtual = mesAtual + 1;//por causa do vetor iniciado em zero e mais um devolver o mes atual
        }

        if (diaVencimento == 0) {
            diaVencimento = diaAtual;
        }

        Double valorMatricula = new Double(0);
        if (input.getValue("valorMatricula") != null && !input.getString("valorMatricula").isEmpty()) {
            valorMatricula = Validador.getMoney(input.getString("valorMatricula"));
        }

        Double descontoReal = new Double(0);
        if (input.getValue("descontoReal") != null && !input.getString("descontoReal").isEmpty()) {
            descontoReal = Validador.getMoney(input.getString("descontoReal"));
        }

        Double valorFinal = new Double(0);
        if (input.getValue("valorTotal") != null && !input.getString("valorTotal").isEmpty()) {
            valorFinal = (input.getDouble("valorTotal"));
        }

        if (descontoReal == 0) {
            Double descontoPercentual = new Double(0);
            if (input.getValue("descontoPercentual") != null && !input.getString("descontoPercentual").isEmpty()) {
                descontoPercentual = Validador.getMoney(input.getString("descontoPercentual"));
            }
            if (descontoPercentual != 0) {
                valorFinal = valorFinal - (valorFinal * (descontoPercentual / 100));
            }
        } else {
            valorFinal = valorFinal - descontoReal;
        }
        if (valorFinal < 0) {
            valorFinal = new Double(0);
        }
        for (int i = 1; i <= total; i++) {
            Pagamento pojo = new Pagamento();
            Calendar data = Calendar.getInstance();

            if (i == 1) {
                pojo.setVencimento(Calendar.getInstance());//Seta o vencimento com a data de hoje
                pojo.setFimAcesso(Calendar.getInstance());//Seta o vencimento com a data de hoje
                pojo.getFimAcesso().add(Calendar.MONTH, (i));//incrementa o meses
                pojo.getFimAcesso().set(Calendar.DAY_OF_MONTH, diaVencimento + tolerancia);//set ano mes e dia

                pojo.setValor(valorFinal+ valorMatricula);
                
            } else {
                pojo.setVencimento(Calendar.getInstance());
                pojo.getVencimento().set(data.get(Calendar.YEAR), data.get(Calendar.MONTH), diaVencimento);//set ano mes e dia
                pojo.getVencimento().add(Calendar.MONTH, (i - 1));//incrementa o meses
                pojo.setFimAcesso(Calendar.getInstance());
                pojo.getFimAcesso().set(data.get(Calendar.YEAR), data.get(Calendar.MONTH), diaVencimento + tolerancia);//set ano mes e dia
                pojo.getFimAcesso().add(Calendar.MONTH, (i));//incrementa o meses
                pojo.setValor(valorFinal);
            }
            lista.add(pojo);
        }

        if (total == 0) {
            Pagamento pojo = new Pagamento();
            pojo.setVencimento(Calendar.getInstance());
            if (periodo.getDuracao().equalsIgnoreCase("Semanal")) {
                pojo.setValor(valorFinal / 4);
                pojo.setFimAcesso(Calendar.getInstance());
                pojo.getFimAcesso().add(Calendar.DAY_OF_MONTH, 6);//incrementa o meses
            } else if (periodo.getDuracao().equalsIgnoreCase("Diário")) {
                pojo.setValor(valorFinal / 30);
                pojo.setFimAcesso(Calendar.getInstance());
            } else if (periodo.getDuracao().equalsIgnoreCase("Quinzenal")) {
            	pojo.setValor(valorFinal / 2);
                pojo.setFimAcesso(Calendar.getInstance());
                pojo.getFimAcesso().add(Calendar.DAY_OF_MONTH, 12);//incrementa o meses
            }
            lista.add(pojo);
        }

        output.setValue("total", lista.size());
        output.setValue("tolerancia", tolerancia);
        output.setValue("lista", lista);
        output.setValue("tamanhoLista", lista.size());

        output.setValue("selecionado", "usuarioPlanoRead");
        return SUCCESS;
    }
}
