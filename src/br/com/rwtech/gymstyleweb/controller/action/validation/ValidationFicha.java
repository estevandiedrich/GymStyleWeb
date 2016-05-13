package br.com.rwtech.gymstyleweb.controller.action.validation;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Exercicio;
import br.com.rwtech.gymstylecore.model.service.Exercicios;
import br.com.rwtech.gymstyleweb.controller.action.ficha.FichaCreateAction;
import java.util.List;
import org.mentawai.core.*;
import org.mentawai.filter.*;
import org.mentawai.rule.*;
import org.mentawai.validation.Validator;

public class ValidationFicha extends ValidationFilter {

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
    private List<Exercicio> exercicios;
    private Output output = null;
    private Input input = null;
    private Boolean treinoA;
    private Boolean treinoB;
    private Boolean treinoC;
    private Boolean treinoD;
    private Boolean treinoE;
    private Boolean treinoF;
    private Validator validator;
    private static String PERIODO_INICIAL = "periodoInicial";
    private static String PERIODO_FINAL = "periodoFinal";

    @Override
    public void prepareValidator(Validator val, Action action, String innerAction) {
        validator = val;
        treinoA = false;
        treinoB = false;
        treinoC = false;
        treinoD = false;
        treinoE = false;
        treinoF = false;

        output = action.getOutput();
        input = action.getInput();

        Exercicios.refresh();
        exercicios = Exercicios.getExercicios();
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

        if (isPost(action)) {
            Long idFicha = null;
            if (input.getValue("idFicha") != null && input.getLong("idFicha") != -1) {
                idFicha = input.getLong("idFicha");
            }
            if (input.getValue(FichaCreateAction.TREINO_DOMINGO) == null
                    && input.getValue(FichaCreateAction.TREINO_SEGUNDA) == null
                    && input.getValue(FichaCreateAction.TREINO_TERCA) == null
                    && input.getValue(FichaCreateAction.TREINO_QUARTA) == null
                    && input.getValue(FichaCreateAction.TREINO_QUINTA) == null
                    && input.getValue(FichaCreateAction.TREINO_SEXTA) == null
                    && input.getValue(FichaCreateAction.TREINO_SABADO) == null) {
                val.add("treinoDias", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
                output.setValue("erroTreinoDias", true);//só pra constar no retorno do output esse erro
            } else {
                //vai setar as varias boolean privadas informando se tem treinoA, treinoB ... marcados
                setTreinos(FichaCreateAction.TREINO_DOMINGO);
                setTreinos(FichaCreateAction.TREINO_SEGUNDA);
                setTreinos(FichaCreateAction.TREINO_TERCA);
                setTreinos(FichaCreateAction.TREINO_QUARTA);
                setTreinos(FichaCreateAction.TREINO_QUINTA);
                setTreinos(FichaCreateAction.TREINO_SEXTA);
                setTreinos(FichaCreateAction.TREINO_SABADO);
            }
            //Boolean dataSemErro = true;
            PERIODO_INICIAL = "periodoInicial";
            PERIODO_FINAL = "periodoFinal";
            if (idFicha != null) {
                PERIODO_INICIAL = "periodoInicialUpdate";
                PERIODO_FINAL = "periodoFinalUpdate";
            }
            val.requiredFields(ValidationMessage.getImageCampoObrigatorio(), PERIODO_INICIAL,PERIODO_FINAL);
            val.add(PERIODO_INICIAL, new StringRule(10, 10), ValidationMessage.getPreenchaTodoCampo());
            val.add(PERIODO_FINAL, new StringRule(10, 10), ValidationMessage.getPreenchaTodoCampo());
//            if (input.getValue(PERIODO_INICIAL) != null
//                    && input.getValue(PERIODO_FINAL) != null
//                    && !input.getString(PERIODO_INICIAL).isEmpty()
//                    && !input.getString(PERIODO_FINAL).isEmpty()
//                    && input.getString(PERIODO_INICIAL).length() == 10
//                    && input.getString(PERIODO_FINAL).length() == 10) {
//                Calendar peInicio = CalendarUtil.setDateCalendar(input.getString(PERIODO_INICIAL));
//                Calendar peFinal = CalendarUtil.setDateCalendar(input.getString(PERIODO_FINAL));
////                if (peInicio.after(peFinal)) {
////                    val.add("periodoMaior", new RequiredFieldRule(), "Informe uma data de término após a data de início!");
////                    dataSemErro = false;
////                }
//                //validar datas
//                if (!Validador.isValidData(input.getString(PERIODO_INICIAL))) {
//                    val.add(PERIODO_INICIAL, new StringRule(1, 1), ValidationMessage.getDataInvalida());
//                    //dataSemErro = false;
//                }
//                if (!Validador.isValidData(input.getString(PERIODO_FINAL))) {
//                    val.add(PERIODO_FINAL, new StringRule(1, 1), ValidationMessage.getDataInvalida());
//                    //dataSemErro = false;
//                }
//            }

            output.setValue("id", idFicha);

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
            output.setValue("abdutores", adutores);
            output.setValue("abdutores", abdutores);
            output.setValue("gastronemicos", gastronemicos);
            output.setValue("soleos", soleos);
            output.setValue("abdomen", abdomen);

            output.setValue("dias", FichaCreateAction.getDias());
            output.setValue("treinos", FichaCreateAction.getTreinos());

            //Efeito dos radios
            output.setValue(FichaCreateAction.TREINO_DOMINGO, input.getValue(FichaCreateAction.TREINO_DOMINGO));
            output.setValue(FichaCreateAction.TREINO_SEGUNDA, input.getValue(FichaCreateAction.TREINO_SEGUNDA));
            output.setValue(FichaCreateAction.TREINO_TERCA, input.getValue(FichaCreateAction.TREINO_TERCA));
            output.setValue(FichaCreateAction.TREINO_QUARTA, input.getValue(FichaCreateAction.TREINO_QUARTA));
            output.setValue(FichaCreateAction.TREINO_QUINTA, input.getValue(FichaCreateAction.TREINO_QUINTA));
            output.setValue(FichaCreateAction.TREINO_SEXTA, input.getValue(FichaCreateAction.TREINO_SEXTA));
            output.setValue(FichaCreateAction.TREINO_SABADO, input.getValue(FichaCreateAction.TREINO_SABADO));
            output.setValue("usuario", ServiceLocator.getUsuarioService().readById(input.getLong("idUsuario")));

            output.setValue("tipo", input.getValue("tipo"));
        }
    }

    public void setReturnTreinos(String treino) {
        if (input.getValue(treino) != null) {
            String[] valor = input.getStrings(treino);
            for (int i = 0; i < valor.length; i++) {
                String aux = valor[i];
                output.setValue(treino + aux, true);
            }
        }
    }

    public String getNameVarByGrupoMuscular(String grupoMuscular) {
        if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_PEITORAL)) {
            return "varPeitoral";
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_DORSAL)) {
            return "varDorsal";
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_TRAPEZIO)) {
            return "varTrapezio";
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_DELTOIDE)) {
            return "varDeltoide";
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_TRICEPS)) {
            return "varTriceps";
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_BICEPS)) {
            return "varBiceps";
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_ANTEBRACO)) {
            return "varAnteBraco";
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_QUADRICEPS)) {
            return "varQuadriceps";
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_GLUTEOS)) {
            return "varGluteos";
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_ADUTORES)) {
            return "varAdutores";
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_GASTRONEMICOS)) {
            return "varGastronemicos";
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_ABDOMEN)) {
            return "varAbdomen";
        }
        return null;

    }

    public List<Exercicio> getListaByGrupoMuscular(String grupoMuscular) {
        if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_PEITORAL)) {
            return peitoral;
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_DORSAL)) {
            return dorsal;
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_TRAPEZIO)) {
            return trapezio;
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_DELTOIDE)) {
            return deltoide;
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_TRICEPS)) {
            return triceps;
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_BICEPS)) {
            return biceps;
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_ANTEBRACO)) {
            return antebraco;
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_QUADRICEPS)) {
            return quadriceps;
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_ISQUIOTIBIAIS)) {
            return isquiotibiais;
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_GLUTEOS)) {
            return gluteos;
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_ADUTORES)) {
            return adutores;
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_ABDUTORES)) {
            return abdutores;
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_GASTRONEMICOS)) {
            return gastronemicos;
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_SOLEOS)) {
            return soleos;
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_ABDOMEN)) {
            return abdomen;
        }
        return null;
    }

    public String getNomeErroByGrupoMuscular(String grupoMuscular) {
        if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_PEITORAL)) {
            return "erroPeitoral";
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_DORSAL)) {
            return "erroDorsal";
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_TRAPEZIO)) {
            return "erroTrapezio";
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_DELTOIDE)) {
            return "erroDeltoide";
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_TRICEPS)) {
            return "erroTriceps";
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_BICEPS)) {
            return "erroBiceps";
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_ANTEBRACO)) {
            return "erroAntebraco";
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_QUADRICEPS)) {
            return "erroQuadriceps";
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_GLUTEOS)) {
            return "erroGluteos";
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_ADUTORES)) {
            return "erroAdutores";
        } else if (grupoMuscular.equalsIgnoreCase(FichaCreateAction.TREINO_GASTRONEMICOS)) {
            return "erroGastronemicos";
        } else {
            return ("erro" + grupoMuscular);
        }
    }

    public void setTreinos(String treinoDia) {
        if (input.getString(treinoDia) != null) {
            String valor = input.getString(treinoDia);
            if (valor.equalsIgnoreCase("A")) {
                treinoA = true;
            } else if (valor.equalsIgnoreCase("B")) {
                treinoB = true;
            } else if (valor.equalsIgnoreCase("C")) {
                treinoC = true;
            } else if (valor.equalsIgnoreCase("D")) {
                treinoD = true;
            } else if (valor.equalsIgnoreCase("E")) {
                treinoE = true;
            } else if (valor.equalsIgnoreCase("F")) {
                treinoF = true;
            }
        }
    }
}
