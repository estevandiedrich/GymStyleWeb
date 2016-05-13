package br.com.rwtech.gymstyleweb.controller.action.ficha;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Ficha;
import br.com.rwtech.gymstylecore.model.pojo.Treino;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class StatusFichaAjaxAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        Long idFicha = input.getLong("idFicha");
        Long idUsuario = input.getLong("idUsuario");
        List<Ficha> fichas = ServiceLocator.getFichaService().readFichasByIdUsuario(idUsuario);
        Boolean naoTemFichaAtiva = Boolean.TRUE;
        String msg = "";
        for (Ficha ficha : fichas) {
            if (ficha.getAtiva()) {
                naoTemFichaAtiva = Boolean.FALSE;
                if (ficha != null) {
                    Calendar data = Calendar.getInstance();
                    ficha.getPeriodoInicial().set(Calendar.HOUR_OF_DAY, 0);
                    ficha.getPeriodoInicial().set(Calendar.MINUTE, 0);
                    ficha.getPeriodoInicial().set(Calendar.SECOND, 0);

                    ficha.getPeriodoFinal().set(Calendar.HOUR_OF_DAY, 23);
                    ficha.getPeriodoFinal().set(Calendar.MINUTE, 59);
                    ficha.getPeriodoFinal().set(Calendar.SECOND, 59);

                    if (ficha.getPeriodoInicial().after(data) || ficha.getPeriodoFinal().before(data)) {
                        msg = "<h1><font class=\"errors\">Ficha ativa fora do periodo<img src=\"images/alert.png\" /></font></h1>";
                    } else {
                        int dia = data.get(Calendar.DAY_OF_WEEK);
                        Boolean tem = Boolean.FALSE;
                        for (Treino treino : ficha.getTreinos()) {
                            switch (dia) {
                                case 1:
                                    if (treino.getTreinaDomingo()) {
                                        tem = Boolean.TRUE;
                                        break;
                                    }
                                    break;
                                case 2:
                                    if (treino.getTreinaSegunda()) {
                                        tem = Boolean.TRUE;
                                        break;
                                    }
                                    break;
                                case 3:
                                    if (treino.getTreinaTerca()) {
                                        tem = Boolean.TRUE;
                                        break;
                                    }
                                    break;
                                case 4:
                                    if (treino.getTreinaQuarta()) {
                                        tem = Boolean.TRUE;
                                        break;
                                    }
                                    break;
                                case 5:
                                    if (treino.getTreinaQuinta()) {
                                        tem = Boolean.TRUE;
                                        break;
                                    }
                                    break;
                                case 6:
                                    if (treino.getTreinaSexta()) {
                                        tem = Boolean.TRUE;
                                        break;
                                    }
                                    break;
                                case 7:
                                    if (treino.getTreinaSabado()) {
                                        tem = Boolean.TRUE;
                                        break;
                                    }
                                    break;
                            }
                        }
                        if (!tem) {
                            msg = "<h1><font class=\"errors\">Ficha ativa, não possui treino para hoje<img src=\"images/alert.png\" /></font></h1>";
                        }
                    }
                }
            }
        }
        if (fichas != null && fichas.size() > 0 && naoTemFichaAtiva) {
            msg = "<h1><font class=\"errors\">Aluno não possui ficha ativa<img src=\"images/alert.png\" /></font></h1>";
        }
        output.setValue("msg", msg);

        return SUCCESS;
    }
}
