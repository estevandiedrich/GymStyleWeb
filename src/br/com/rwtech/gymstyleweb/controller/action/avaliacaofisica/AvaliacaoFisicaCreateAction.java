package br.com.rwtech.gymstyleweb.controller.action.avaliacaofisica;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.AvaliacaoFisica;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstylecore.model.util.Validador;
import java.util.Calendar;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class AvaliacaoFisicaCreateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;
        Long id = input.getLong("id");
        if (isPost()) {
            AvaliacaoFisica pojo = new AvaliacaoFisica();
            pojo.setPeso(Validador.getMoney(input.getString("peso")));
            pojo.setAltura(Validador.getMoney(input.getString("altura")));

            pojo.setBracoDir(Validador.getMoney(input.getString("bracoDireito")));
            pojo.setBracoEsq(Validador.getMoney(input.getString("bracoEsquerdo")));
            pojo.setCoxaDir(Validador.getMoney(input.getString("coxaDireita")));
            pojo.setCoxaEsq(Validador.getMoney(input.getString("coxaEsquerda")));
            pojo.setPanturrilhaDir(Validador.getMoney(input.getString("panturrilhaDireita")));
            pojo.setPanturrilhaEsq(Validador.getMoney(input.getString("panturrilhaEsquerda")));
            pojo.setTorax(Validador.getMoney(input.getString("torax")));
            pojo.setQuadril(Validador.getMoney(input.getString("quadril")));
            pojo.setCintura(Validador.getMoney(input.getString("cintura")));
            pojo.setAbdomen(Validador.getMoney(input.getString("abdomen")));

            pojo.setSubescapular(Validador.getMoney(input.getString("subescapular")));
            pojo.setTricipital(Validador.getMoney(input.getString("tricipital")));
            pojo.setPeitoral(Validador.getMoney(input.getString("peitoral")));
            pojo.setAbdominal(Validador.getMoney(input.getString("abdominal")));
            pojo.setSupraIliaca(Validador.getMoney(input.getString("supraIliaca")));
            pojo.setCoxa(Validador.getMoney(input.getString("coxa")));
            pojo.setPanturrilha(Validador.getMoney(input.getString("panturrilha")));
            pojo.setAxilarMedia(Validador.getMoney(input.getString("axilarMedia")));

            pojo.setGorduraAtual(Validador.getMoney(input.getString("gorduraAtual")));
            pojo.setGorduraIdeal(input.getString("gorduraIdeal"));
            pojo.setMassaMagra(Validador.getMoney(input.getString("massaMagra")));
            pojo.setMassaGorda(Validador.getMoney(input.getString("massaGorda")));

            pojo.setDataAvaliacao(Calendar.getInstance());
            pojo.setDataProximaAvaliacao(CalendarUtil.setDateCalendar(input.getString("dataProximaAvaliacao")));
            pojo.setDescricao(input.getString("descricao"));
            pojo.setImc(input.getDouble("imc"));

            pojo.setProtocolo(ServiceLocator.getProtocoloService().readById(input.getLong("protocolo")));

            pojo.setInstrutor((Usuario) getUserSession());
            ServiceLocator.getAvaliacaoFisicaService().create(pojo, id);
            session.setAttribute("mensagem", "Avaliação Física criada com sucesso!");
            consequence = SUCCESS;
        } else {
            if (id == null || id < 0) {
                consequence = LIST;
            } else {
                AvaliacaoFisicaAction.preload(output, input);
            }
        }
        return consequence;
    }
}