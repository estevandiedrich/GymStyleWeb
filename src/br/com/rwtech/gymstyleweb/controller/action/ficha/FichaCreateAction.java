package br.com.rwtech.gymstyleweb.controller.action.ficha;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Exercicio;
import br.com.rwtech.gymstylecore.model.pojo.Ficha;
import br.com.rwtech.gymstylecore.model.pojo.Serie;
import br.com.rwtech.gymstylecore.model.pojo.Treino;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.service.Exercicios;
import br.com.rwtech.gymstylecore.model.util.CalendarUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class FichaCreateAction extends BaseAction {

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
    //private List<Exercicio> exercicios;
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
            Long idUsuario = input.getLong("idUsuario");
            Ficha ficha = new Ficha();
            ficha.setDescricao(input.getString("descricao"));
            ficha.setInstrutor((Usuario) getUserSession());
            ficha.setPeriodoInicial(CalendarUtil.setDateCalendar(input.getString("periodoInicial")));
            ficha.setPeriodoFinal(CalendarUtil.setDateCalendar(input.getString("periodoFinal")));
            ficha.setTreinos(getTreinosInput());
            ficha.setAtiva(Boolean.TRUE);
            ServiceLocator.getFichaService().create(idUsuario, ficha);
            session.setAttribute("mensagem", "Ficha criada com sucesso!");

            //Quando redirecionado para usuarioFichasRead ele pega este atributo na sessao
            session.setAttribute("idUsuarioFicha", idUsuario);

            input.setValue("idFicha", ficha.getId());
            consequence = input.getString("status");
        } else {
            Long idUsuario = input.getLong("idUsuario");
            if (idUsuario == null || idUsuario == -1) {
                consequence = LIST;
            } else {
                output.setValue("idUsuario", idUsuario);
                output.setValue("usuario", ServiceLocator.getUsuarioService().readById(idUsuario));
                output.setValue("periodoInicial", CalendarUtil.getDataCurrente());
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
                output.setValue("dias", getDias());
                output.setValue("treinos", getTreinos());
            }
        }
        return consequence;
    }

    public static Map getDias() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("1", "Domingo");
        map.put("2", "Segunda");
        map.put("3", "Terça");
        map.put("4", "Quarta");
        map.put("5", "Quinta");
        map.put("6", "Sexta");
        map.put("7", "Sábado");
        return map;
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

    /*
     * Se o radio estiver checado, instancia-se o treino
     */
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

        //Se o radio estiver checado, Instancia-se o treino
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
        } else if (grupoMuscular.equalsIgnoreCase(TREINO_ABDUTORES)) {
            return abdutores;
        } else if (grupoMuscular.equalsIgnoreCase(TREINO_ADUTORES)) {
            return adutores;
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
