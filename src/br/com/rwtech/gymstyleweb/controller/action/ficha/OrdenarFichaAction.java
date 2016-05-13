package br.com.rwtech.gymstyleweb.controller.action.ficha;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Exercicio;
import br.com.rwtech.gymstylecore.model.pojo.Ficha;
import br.com.rwtech.gymstylecore.model.pojo.Serie;
import br.com.rwtech.gymstylecore.model.pojo.Treino;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class OrdenarFichaAction extends BaseAction {

    private static int ALT = 30;
    private static int COM = 90;

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;

        Long idFicha = input.getLong("idFicha");
        Long idUsuario = input.getLong("idUsuario");

        if (isPost() && input.getValue("status") == null) {//status quando vem direto do create ou update
            Ficha ficha = ServiceLocator.getFichaService().readById(idFicha);
            ficha.setAtiva(Boolean.TRUE);
            setNewOrdem(ficha);
            ServiceLocator.getFichaService().update(idUsuario, ficha);
            session.setAttribute("idUsuarioFicha", idUsuario);
            consequence = SUCCESS;
        } else {
            if (idFicha == null || idFicha == -1) {
                session.setAttribute("idUsuarioFicha", idUsuario);
                consequence = SUCCESS;
            } else {
                Ficha ficha = ServiceLocator.getFichaService().readById(idFicha);
                if (ficha != null) {
                    List<Treino> treinos = ficha.getTreinos();

                    int div1 = COM;
                    int div2 = COM;
                    for (Treino treino : treinos) {
                        List<Serie> series = treino.getSeries();
                        Collections.sort(series);
                        switch (treino.getNome()) {
                            case "A":
                                output.setValue("exerciciosA", series);
                                int valA = (treino.getSeries().size() * ALT) + COM;
                                if (valA > div1) {
                                    div1 = valA;
                                }
                                break;
                            case "B":
                                output.setValue("exerciciosB", series);
                                int valB = (treino.getSeries().size() * ALT) + COM;
                                if (valB > div1) {
                                    div1 = valB;
                                }
                                break;
                            case "C":
                                output.setValue("exerciciosC", series);
                                int valC = (treino.getSeries().size() * ALT) + COM;
                                if (valC > div1) {
                                    div1 = valC;
                                }

                                break;
                            case "D":
                                output.setValue("exerciciosD", series);
                                int valD = (treino.getSeries().size() * ALT) + COM;
                                if (valD > div2) {
                                    div2 = valD;
                                }
                                break;
                            case "E":
                                output.setValue("exerciciosE", series);
                                int valE = (treino.getSeries().size() * ALT) + COM;
                                if (valE > div2) {
                                    div2 = valE;
                                }
                                break;
                            case "F":
                                output.setValue("exerciciosF", series);
                                int valF = (treino.getSeries().size() * ALT) + COM;
                                if (valF > div2) {
                                    div2 = valF;
                                }
                                break;
                        }
                    }
                    output.setValue("div1", div1);
                    output.setValue("div2", div2);
                }
                output.setValue("usuario", ServiceLocator.getUsuarioService().readById(idUsuario));
                output.setValue("idFicha", idFicha);
                output.setValue("idUsuario", idUsuario);
            }
        }
        return consequence;
    }

    public List<Treino> setNewOrdem(Ficha ficha) {
        //Mapa com a ordem dos exercicios pelo ID de cada exercício
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.putAll(getOrdemExercicio(input.getString("treinoAHtml"), "A"));
        map.putAll(getOrdemExercicio(input.getString("treinoBHtml"), "B"));
        map.putAll(getOrdemExercicio(input.getString("treinoCHtml"), "C"));
        map.putAll(getOrdemExercicio(input.getString("treinoDHtml"), "D"));
        map.putAll(getOrdemExercicio(input.getString("treinoEHtml"), "E"));
        map.putAll(getOrdemExercicio(input.getString("treinoFHtml"), "F"));

        for (Treino treino : ficha.getTreinos()) {
            List<Serie> series = treino.getSeries();
            for (Serie serie : series) {
                serie.setOrdem(map.get(serie.getExercicio().getId().toString() + treino.getNome()));//Mapa que retorna na linha a ser ordenada
            }
            //Ordenada as series pela ordem
            Collections.sort(series);
        }
        return null;
    }

    public Map getOrdemExercicio(String html, String treino) {
        //System.out.println("GET ORDEM EXERCICIO");
        //Eliminando os campos desnecessários de html
        html = html.replaceAll("class=\"\"", "");
        html = html.replaceAll(" style=\"display:none\"", "");
        html = html.replaceAll("<h9 class=\"head\"><a href=\"#\">", "");
        html = html.replaceAll("</a></h9></li>", "");
        html = html.replaceAll("<div>", "");
        html = html.replaceAll("style=\"position: relative; left: 0px; top: 0px;\"", "");
        html = html.replaceAll("<li style=\"position: relative;\" >", "");

        html = html.replaceAll("  ", "");

        Map<String, Integer> map = new HashMap<String, Integer>();
        //System.out.println("HTML " + treino + " - " + html);
        String nomes[] = html.split("#ID#");
        int linha = 1;
        for (int i = 0; i < nomes.length; i++) {
            String aux = nomes[i];
            aux = aux.replaceAll("\n", "");
            aux = aux.replaceAll("\r", "");
            if (!aux.isEmpty()) {
                //System.out.println(aux);
                int pos = aux.indexOf("#/ID#");//POS = pos da primeira letra do </div>
                if (pos != -1) {
                    aux = aux.substring(0, pos);
                    aux = aux.replaceAll("[\\D]", "");//remove todos os caracteres de de uma String
                    //System.out.println((linha) + " - " + aux);
                    Long id = Long.valueOf(aux);
                    //Compara se não é Grupo muscular abdomen
                    Exercicio exercicio = ServiceLocator.getExercicioService().readById(id);
                    //se exercicio for diferente de null
                    if (exercicio != null) {
                        map.put(id.toString() + treino, linha++);
                    }
                }
            }
        }
        return map;
    }
}
