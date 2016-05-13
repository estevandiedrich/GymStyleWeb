package br.com.rwtech.gymstyleweb.controller.action.ficha;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Exercicio;
import br.com.rwtech.gymstylecore.model.pojo.Ficha;
import br.com.rwtech.gymstylecore.model.pojo.Serie;
import br.com.rwtech.gymstylecore.model.pojo.Treino;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.service.Exercicios;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import br.com.rwtech.gymstyleweb.controller.action.ReadAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Éder Faria
 */
public class FichaUpdateAction extends ReadAction {

    public static String VALUE = "Value";
    public static String TREINO_DOMINGO = "treinoDomingo";
    public static String TREINO_SEGUNDA = "treinoSegunda";
    public static String TREINO_TERCA = "treinoTerca";
    public static String TREINO_QUARTA = "treinoQuarta";
    public static String TREINO_QUINTA = "treinoQuinta";
    public static String TREINO_SEXTA = "treinoSexta";
    public static String TREINO_SABADO = "treinoSabado";
    public static String TREINO_PEITORAL = "treinoPeitoral";
    public static String TREINO_DORSAL = "treinoDorsal";
    public static String TREINO_TRAPEZIO = "treinoTrapezio";
    public static String TREINO_DELTOIDE = "treinoDeltoide";
    public static String TREINO_TRICEPS = "treinoTriceps";
    public static String TREINO_BICEPS = "treinoBiceps";
    public static String TREINO_ANTEBRACO = "treinoAntebraco";
    public static String TREINO_QUADRICEPS = "treinoQuadriceps";
    public static String TREINO_ISQUIOTIBIAIS = "treinoIsquiotibiais";
    public static String TREINO_GLUTEOS = "treinoGluteos";
    public static String TREINO_ADUTORES = "treinoAdutores";
    public static String TREINO_ABDUTORES = "treinoAbdutores";
    public static String TREINO_GASTRONEMICOS = "treinoGastronemicos";
    public static String TREINO_SOLEOS = "treinoSoleos";
    public static String TREINO_ABDOMEN = "TreinoAbdomen";
    private Treino treinoA = null;
    private Treino treinoB = null;
    private Treino treinoC = null;
    private Treino treinoD = null;
    private Treino treinoE = null;
    private Treino treinoF = null;
    private List<Exercicio> exercicios;
    private List<Exercicio> peitoral;
    private List<Exercicio> dorsal;
    private List<Exercicio> trapezio;
    private List<Exercicio> deltoide;
    private List<Exercicio> triceps;
    private List<Exercicio> biceps;
    private List<Exercicio> antebraco;
    private List<Exercicio> quadriceps;
    private List<Exercicio> isquiotibiais;
    private List<Exercicio> gluteos;
    private List<Exercicio> adutores;
    private List<Exercicio> abdutores;
    private List<Exercicio> gastronemicos;
    private List<Exercicio> soleos;
    private List<Exercicio> abdomen;
    private static String A = "A";
    private static String B = "B";
    private static String C = "C";
    private static String D = "D";
    private static String E = "E";
    private static String F = "F";
    private Map<String, Integer> mapLetra = null;

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;

        Exercicios.refresh();
        //exercicios = Exercicios.getExercicios();
        peitoral = Exercicios.getPeitoral();
        dorsal = Exercicios.getDorsal();
        trapezio = Exercicios.getTrapezio();
        deltoide = Exercicios.getDeltoide();
        triceps = Exercicios.getTriceps();
        biceps = Exercicios.getBiceps();
        antebraco = Exercicios.getAnteBraco();
        quadriceps = Exercicios.getQuadriceps();
        isquiotibiais = Exercicios.getIsquiotibiais();
        gluteos = Exercicios.getGluteos();
        adutores = Exercicios.getAdutores();
        abdutores = Exercicios.getAbdutores();
        gastronemicos = Exercicios.getGastrocnemicos();
        soleos = Exercicios.getSoleos();
        abdomen = Exercicios.getAbdomen();

        if (isPost()) {
            consequence = post();
        } else {
            Long idUsuario = input.getLong("idUsuario");
            Long idFicha = input.getLong("idFicha");
            output.setValue("usuario", ServiceLocator.getUsuarioService().readById(idUsuario));
            output.setValue("periodoInicial", CalendarUtil.getDataCurrente());
            output.setValue("tipo", "update");

            output.setValue("peitoral", peitoral);
            output.setValue("dorsal", dorsal);
            output.setValue("trapezio", trapezio);
            output.setValue("deltoide", deltoide);
            output.setValue("triceps", triceps);
            output.setValue("biceps", biceps);
            output.setValue("antebraco", antebraco);
            output.setValue("quadriceps", quadriceps);
            output.setValue("isquiotibiais", isquiotibiais);
            output.setValue("gluteos", gluteos);
            output.setValue("adutores", adutores);
            output.setValue("abdutores", abdutores);
            output.setValue("gastronemicos", gastronemicos);
            output.setValue("soleos", soleos);
            output.setValue("abdomen", abdomen);
            output.setValue("dias", FichaCreateAction.getDias());
            output.setValue("treinos", FichaCreateAction.getTreinos());

            if (idUsuario != null && idUsuario != -1 && idFicha != null && idFicha != -1) {
                Ficha ficha = ServiceLocator.getFichaService().readById(idFicha);
                output.setValue("periodoInicialUpdate", CalendarUtil.getDateCalendar(ficha.getPeriodoInicial()));
                output.setValue("periodoFinalUpdate", CalendarUtil.getDateCalendar(ficha.getPeriodoFinal()));
                for (Treino treino : ficha.getTreinos()) {
                    output.setValue("treino" + treino.getNome(), true);
                    output.setValue("Treino" + treino.getNome(), treino);
                    if (treino.getTreinaDomingo()) {
                        output.setValue(TREINO_DOMINGO, treino.getNome());//Seta o radio
                        output.setValue(TREINO_DOMINGO + VALUE, getDiaByTreino(treino.getNome()));//Seta o valor da caixa de texto oculta
                    }
                    if (treino.getTreinaSegunda()) {
                        output.setValue(TREINO_SEGUNDA, treino.getNome());
                        output.setValue(TREINO_SEGUNDA + VALUE, getDiaByTreino(treino.getNome()));
                    }
                    if (treino.getTreinaTerca()) {
                        output.setValue(TREINO_TERCA, treino.getNome());
                        output.setValue(TREINO_TERCA + VALUE, getDiaByTreino(treino.getNome()));
                    }
                    if (treino.getTreinaQuarta()) {
                        output.setValue(TREINO_QUARTA, treino.getNome());
                        output.setValue(TREINO_QUARTA + VALUE, getDiaByTreino(treino.getNome()));
                    }
                    if (treino.getTreinaQuinta()) {
                        output.setValue(TREINO_QUINTA, treino.getNome());
                        output.setValue(TREINO_QUINTA + VALUE, getDiaByTreino(treino.getNome()));
                    }
                    if (treino.getTreinaSexta()) {
                        output.setValue(TREINO_SEXTA, treino.getNome());
                        output.setValue(TREINO_SEXTA + VALUE, getDiaByTreino(treino.getNome()));
                    }
                    if (treino.getTreinaSabado()) {
                        output.setValue(TREINO_SABADO, treino.getNome());
                        output.setValue(TREINO_SABADO + VALUE, getDiaByTreino(treino.getNome()));
                    }

                    for (Serie serie : treino.getSeries()) {
                        Exercicio exercicio = serie.getExercicio();
                        String l = treino.getNome().toLowerCase();
                        output.setValue(exercicio.getId() + "serie-" + l, serie.getSerie());
                        output.setValue(exercicio.getId() + "repeticao-" + l, serie.getRepeticao());
                        output.setValue(exercicio.getId() + "carga-" + l, serie.getCarga());
                        if (serie.getOrdem() != null) {
                            output.setValue(exercicio.getId() + "ordem-" + l, serie.getOrdem());
                        }
                    }
                }

                output.setValue("dias", FichaCreateAction.getDias());
                output.setValue("treinos", FichaCreateAction.getTreinos());
                output.setValue("pojo", ficha);
                output.setValue("usuario", ServiceLocator.getUsuarioService().readById(idUsuario));
                output.setValue("idUsuario", idUsuario);
               
                setMensagem();
                consequence = SHOW;
            } else {
                consequence = LIST;
            }
        }
        return consequence;
    }

    private String post() {
        Long idUsuario = input.getLong("idUsuario");
        Long idFicha = input.getLong("idFicha");
        Ficha ficha = new Ficha();
        ficha.setId(idFicha);
        ficha.setDescricao(input.getString("descricao"));
        ficha.setInstrutor((Usuario) getUserSession());
        ficha.setPeriodoInicial(CalendarUtil.setDateCalendar(input.getString("periodoInicialUpdate")));
        ficha.setPeriodoFinal(CalendarUtil.setDateCalendar(input.getString("periodoFinalUpdate")));
        ficha.setTreinos(getTreinosInput());
        ficha.setAtiva(Boolean.TRUE);
        //Se tipo
        if (input.getValue("tipo") != null && input.getString("tipo").equalsIgnoreCase("create")) {
            ServiceLocator.getFichaService().create(idUsuario, ficha);
            input.setValue("idFicha", ficha.getId());// Para ordenar a ficha nova criada
            session.setAttribute("mensagem", "Ficha criada com sucesso!");
        } else {
            ServiceLocator.getFichaService().update(idUsuario, ficha);
            session.setAttribute("mensagem", "Ficha alterada com sucesso!");
        }
        session.setAttribute("idUsuarioFicha", idUsuario);
        if (input.getValue("status") != null && !input.getString("status").isEmpty()) {
            return input.getString("status");
        }
        return SUCCESS;
    }

    public int getDiaByTreino(String letra) {
        if (mapLetra == null) {
            mapLetra = new LinkedHashMap<String, Integer>();
            mapLetra.put(A, 1);
            mapLetra.put(B, 2);
            mapLetra.put(C, 3);
            mapLetra.put(D, 4);
            mapLetra.put(E, 5);
            mapLetra.put(F, 6);
        }
        return mapLetra.get(letra);
    }

    public static Map getTreinos() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put(A, A);
        map.put(B, B);
        map.put(C, C);
        map.put(D, D);
        map.put(E, E);
        map.put(F, F);
        return map;
    }

    public Treino getTreino(Treino treino, String nome) {
        if (treino == null) {
            treino = new Treino();
            treino.setSeries(new ArrayList<Serie>());
            treino.setNome(nome);
        }
        return treino;
    }

    private void setDiaTreino(Integer dia, Treino treino) {
        switch (dia) {
            case 1: {
                treino.setTreinaDomingo(Boolean.TRUE);
                break;
            }
            case 2: {
                treino.setTreinaSegunda(Boolean.TRUE);
                break;
            }
            case 3: {
                treino.setTreinaTerca(Boolean.TRUE);
                break;
            }
            case 4: {
                treino.setTreinaQuarta(Boolean.TRUE);
                break;
            }
            case 5: {
                treino.setTreinaQuinta(Boolean.TRUE);
                break;
            }
            case 6: {
                treino.setTreinaSexta(Boolean.TRUE);
                break;
            }
            case 7: {
                treino.setTreinaSabado(Boolean.TRUE);
                break;
            }
        }
    }

    public void setTreino(String treinoDia, Integer dia) {
        if (input.getValue(treinoDia) != null) {
            if (!input.getString(treinoDia).isEmpty()) {
                String treino = input.getString(treinoDia);
                if (treino.equalsIgnoreCase(A)) {
                    treinoA = getTreino(treinoA, A);
                    setDiaTreino(dia, treinoA);
                } else if (treino.equalsIgnoreCase(B)) {
                    treinoB = getTreino(treinoB, B);
                    setDiaTreino(dia, treinoB);
                } else if (treino.equalsIgnoreCase(C)) {
                    treinoC = getTreino(treinoC, C);
                    setDiaTreino(dia, treinoC);
                } else if (treino.equalsIgnoreCase(D)) {
                    treinoD = getTreino(treinoD, D);
                    setDiaTreino(dia, treinoD);
                } else if (treino.equalsIgnoreCase(E)) {
                    treinoE = getTreino(treinoE, E);
                    setDiaTreino(dia, treinoE);
                } else if (treino.equalsIgnoreCase(F)) {
                    treinoF = getTreino(treinoF, F);
                    setDiaTreino(dia, treinoF);
                }
            }
        }
    }

    public List<Treino> getTreinosInput() {
        List<Treino> treinos = new ArrayList<>();
        setTreino(TREINO_DOMINGO, 1);
        setTreino(TREINO_SEGUNDA, 2);
        setTreino(TREINO_TERCA, 3);
        setTreino(TREINO_QUARTA, 4);
        setTreino(TREINO_QUINTA, 5);
        setTreino(TREINO_SEXTA, 6);
        setTreino(TREINO_SABADO, 7);

        //Set grupos musculares
        setSeries(TREINO_PEITORAL);
        setSeries(TREINO_DORSAL);
        setSeries(TREINO_TRAPEZIO);
        setSeries(TREINO_DELTOIDE);
        setSeries(TREINO_TRICEPS);
        setSeries(TREINO_BICEPS);
        setSeries(TREINO_ANTEBRACO);
        setSeries(TREINO_QUADRICEPS);
        setSeries(TREINO_ISQUIOTIBIAIS);
        setSeries(TREINO_GLUTEOS);
        setSeries(TREINO_ADUTORES);
        setSeries(TREINO_ABDUTORES);
        setSeries(TREINO_GASTRONEMICOS);
        setSeries(TREINO_SOLEOS);
        setSeries(TREINO_ABDOMEN);

        if (treinoA != null) {
            treinos.add(treinoA);
        }
        if (treinoB != null) {
            treinos.add(treinoB);
        }
        if (treinoC != null) {
            treinos.add(treinoC);
        }
        if (treinoD != null) {
            treinos.add(treinoD);
        }
        if (treinoE != null) {
            treinos.add(treinoE);
        }
        if (treinoF != null) {
            treinos.add(treinoF);
        }
        return treinos;
    }

    public List<Exercicio> getListaByGrupoMuscular(String grupoMuscular) {
        if (grupoMuscular.equalsIgnoreCase(TREINO_PEITORAL)) {
            return peitoral;
        } else if (grupoMuscular.equalsIgnoreCase(TREINO_DORSAL)) {
            return dorsal;
        } else if (grupoMuscular.equalsIgnoreCase(TREINO_TRAPEZIO)) {
            return trapezio;
        } else if (grupoMuscular.equalsIgnoreCase(TREINO_DELTOIDE)) {
            return deltoide;
        } else if (grupoMuscular.equalsIgnoreCase(TREINO_TRICEPS)) {
            return triceps;
        } else if (grupoMuscular.equalsIgnoreCase(TREINO_BICEPS)) {
            return biceps;
        } else if (grupoMuscular.equalsIgnoreCase(TREINO_ANTEBRACO)) {
            return antebraco;
        } else if (grupoMuscular.equalsIgnoreCase(TREINO_QUADRICEPS)) {
            return quadriceps;
        } else if (grupoMuscular.equalsIgnoreCase(TREINO_ISQUIOTIBIAIS)) {
            return isquiotibiais;
        } else if (grupoMuscular.equalsIgnoreCase(TREINO_GLUTEOS)) {
            return gluteos;
        } else if (grupoMuscular.equalsIgnoreCase(TREINO_ADUTORES)) {
            return adutores;
        } else if (grupoMuscular.equalsIgnoreCase(TREINO_ABDUTORES)) {
            return abdutores;
        } else if (grupoMuscular.equalsIgnoreCase(TREINO_GASTRONEMICOS)) {
            return gastronemicos;
        } else if (grupoMuscular.equalsIgnoreCase(TREINO_SOLEOS)) {
            return soleos;
        } else if (grupoMuscular.equalsIgnoreCase(TREINO_ABDOMEN)) {
            return abdomen;
        }
        return null;
    }

    public void setSeries(String grupoMuscular) {
        List<Exercicio> lista = getListaByGrupoMuscular(grupoMuscular);
        String[] treinos = new String[]{"-a", "-b", "-c", "-d", "-e", "-f"};
        for (int i = 0; i < treinos.length; i++) {
            String t = treinos[i];
            Treino treino = null;
            List<Serie> series = new ArrayList<Serie>();

            if (i == 0) {
                if (treinoA != null) {
                    treino = treinoA;
                }
            } else if (i == 1) {
                if (treinoB != null) {
                    treino = treinoB;
                }
            } else if (i == 2) {
                if (treinoC != null) {
                    treino = treinoC;
                }
            } else if (i == 3) {
                if (treinoD != null) {
                    treino = treinoD;
                }
            } else if (i == 4) {
                if (treinoE != null) {
                    treino = treinoE;
                }
            } else if (i == 5) {
                if (treinoF != null) {
                    treino = treinoF;
                }
            }
            if (treino != null) {
                for (Exercicio exercicio : lista) {
                    Long id = exercicio.getId();
                    if (input.getValue(id + "serie" + t) != null && !input.getString(id + "serie" + t).isEmpty()) {
                        Serie serie = new Serie();
                        serie.setSerie(input.getString(id + "serie" + t));
                        serie.setRepeticao(input.getString(id + "repeticao" + t));
                        serie.setCarga(input.getString(id + "carga" + t));
                        if (input.getValue(id + "ordem" + t) != null && !input.getString(id + "ordem" + t).isEmpty()) {
                            serie.setOrdem(input.getInt(id + "ordem" + t));
                        }
                        serie.setExercicio(exercicio);
                        series.add(serie);
                    }
                }
                if (!series.isEmpty()) {
                    //Ordenada as series pela ordem
                    Collections.sort(series);
                    treino.getSeries().addAll(series);
                }
            }
        }
    }

    public Treino getTreinoByValor(String valor) {
        if (valor.equalsIgnoreCase(A)) {
            return getTreino(treinoA, A);
        }
        if (valor.equalsIgnoreCase(B)) {
            return getTreino(treinoB, B);
        }
        if (valor.equalsIgnoreCase(C)) {
            return getTreino(treinoC, C);
        }
        if (valor.equalsIgnoreCase(D)) {
            return getTreino(treinoD, D);
        }
        if (valor.equalsIgnoreCase(E)) {
            return getTreino(treinoE, E);
        }
        if (valor.equalsIgnoreCase(F)) {
            return getTreino(treinoF, F);
        }
        return null;
    }
}