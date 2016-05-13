package br.com.rwtech.gymstyleweb.controller.action.ficha;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Ficha;
import br.com.rwtech.gymstylecore.model.pojo.Serie;
import br.com.rwtech.gymstylecore.model.pojo.Treino;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;

/**
 *
 * @author Éder Faria
 */
public class FichaUsuarioInfoAction extends ReadAction {

    @Override
    public String execute() throws Exception {
        String consequence = ERROR;
        Long idUsuario = input.getLong("idUsuario");
        Long idFicha = input.getLong("idFicha");
        if (idUsuario != null && idUsuario != -1 && idFicha != null && idFicha != -1) {
            Ficha ficha = ServiceLocator.getFichaService().readById(idFicha);

            for (Treino treino : ficha.getTreinos()) {
                String dias = "";
                output.setValue("treino" + treino.getNome(), true);
                output.setValue("Treino" + treino.getNome(), treino);
                if (treino.getTreinaDomingo()) {
                    dias += " Domingo,";
                }
                if (treino.getTreinaSegunda()) {
                    dias += " Segunda,";
                }
                if (treino.getTreinaTerca()) {
                    dias += " Terça,";
                }
                if (treino.getTreinaQuarta()) {
                    dias += " Quarta,";
                }
                if (treino.getTreinaQuinta()) {
                    dias += " Quinta,";
                }
                if (treino.getTreinaSexta()) {
                    dias += " Sexta,";
                }
                if (treino.getTreinaSabado()) {
                    dias += " Sábado,";
                }
                dias = dias.substring(0, dias.length() - 1);
                output.setValue("dias" + treino.getNome(), dias);

                String grupo = "";
                Long id = null;
                int i = 1;
                int j = 1;
                for (Serie serie : treino.getSeries()) {
                    String aux = serie.getExercicio().getGrupoMuscular().getGrupoMuscular();
                    if (!aux.equalsIgnoreCase(grupo)) {
                        grupo = aux;
                        id = serie.getExercicio().getId();
                        i = 1;
                        output.setValue("cor" + treino.getNome() + id, (j % 2 == 0 ? 1 : 2));
                        j++;
                    } else {
                        serie.getExercicio().getGrupoMuscular().setGrupoMuscular(null);
                    }
                    output.setValue("cor" + treino.getNome() + serie.getExercicio().getId(), (j % 2 == 0 ? 1 : 2));
                    output.setValue("rowGrupo" + treino.getNome() + id, i++);
                }
            }

            output.setValue("dias", FichaCreateAction.getDias());
            output.setValue("treinos", FichaCreateAction.getTreinos());
            output.setValue("pojo", ficha);
            output.setValue("usuario", ServiceLocator.getUsuarioService().readById(idUsuario));

            setMensagem();
            consequence = SUCCESS;
        } else {
            consequence = LIST;
        }
        return consequence;
    }
}