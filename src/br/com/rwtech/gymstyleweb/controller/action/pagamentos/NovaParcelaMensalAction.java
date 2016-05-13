package br.com.rwtech.gymstyleweb.controller.action.pagamentos;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Dispositivo;
import br.com.rwtech.gymstylecore.model.pojo.Evento;
import br.com.rwtech.gymstylecore.model.pojo.Pagamento;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class NovaParcelaMensalAction extends PagamentoAction {

    @Override
    public String execute() throws Exception {
        Long idPlanoUsuario = (input.getLong(ID_PLANO_USUARIO));
        String consequence = SUCCESS;

        Map<String, Object> mapa = ServiceLocator.getPlanoService().readPlanoUsuarioByIdPlanoUsuario(idPlanoUsuario);
        output.setValue("mapa", mapa);
        Usuario usuario = (Usuario) mapa.get("usuario");
        Evento ultimoEvento = ServiceLocator.getEventoService().readUltimoEventoUsuario(usuario.getId());
        String evento = "";
        if (ultimoEvento != null) {
            evento = "Ultima passagem realizada em " + CalendarUtil.getDateCalendar(ultimoEvento.getDataHora());
            if (ultimoEvento.getDispositivo() != null) {
                Dispositivo dis = ultimoEvento.getDispositivo();
                String catraca = (dis.getDispositivo() == null ? dis.getEnderecoIp() : dis.getDispositivo());
                if (ultimoEvento.getEntrada()) {
                    evento += " - Entrada: 'Catraca " + catraca + "'";
                } else {
                    evento += " - Saída: 'Catraca " + catraca + "'";
                }
            }
        }

        Plano planoUsuario = (Plano) mapa.get(PLANO);
        String ultimoPagamento = "";
        Pagamento ultimoPag = null;
        List<Pagamento> lista = planoUsuario.getPagamentos();
        for (Pagamento pag : lista) {
            if (pag.getPagamento() != null) {
                ultimoPagamento = CalendarUtil.getDateCalendar(pag.getPagamento());
                ultimoPagamento += " referente a parcela " + CalendarUtil.getDateCalendar(pag.getVencimento());
            }
            ultimoPag = pag;
        }
        //quando acabar o for, irá apontar para o ultimo pagamento
        if (!ultimoPagamento.isEmpty()) {
            if (!evento.isEmpty()) {
                evento += "<br>";
            }
            evento += "Ultimo pagamento em " + ultimoPagamento + "<br>";
        }
        output.setValue("evento", evento);
        output.setValue(QTDE_PARCELA, 1);
        output.setValue(TIPO_PLANO, "Mensal");

        Plano atual = ServiceLocator.getPlanoService().readById(planoUsuario.getId());
        
        Pagamento pagamento = new Pagamento();
        pagamento.setNumeroParcela(2);//Somente para plano mensal
        pagamento.setVencimento(Calendar.getInstance());
        pagamento.setInicioAcesso(Calendar.getInstance());
        pagamento.setFimAcesso(Calendar.getInstance());

        pagamento.getFimAcesso().set(Calendar.DAY_OF_MONTH, ultimoPag.getFimAcesso().get(Calendar.DAY_OF_MONTH));
        pagamento.getFimAcesso().add(Calendar.MONTH, 1);//add um mes pra frente do pagamento

        pagamento.setValor(atual.getValorTotal());
        pagamento.setMulta(new Double(0));
        pagamento.setDesconto(new Double(0));

        output.setValue("id", idPlanoUsuario);

        output.setValue(VALOR_A_PAGAR, pagamento.getValor());
        output.setValue("pagamento", pagamento);
        output.setValue(PLANO, planoUsuario);
        output.setValue(FORMA_DE_PAGAMENTO_MAP, ServiceLocator.getFormaPagamentoService().readList());
        setSelecionado(Ac.PAGAMENTO_READ);

        return consequence;
    }
}