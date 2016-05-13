package br.com.rwtech.gymstyleweb.controller.action.digital;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.dao.DispositivoDAO;
import br.com.rwtech.gymstylecore.model.pojo.ImpressaoDigital;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class ImpressaoDigitalUsuarioSelectAjaxAction extends BaseAction {

    private static String POLEGAR_DIREITO = "Polegar Direito";
    private static String INDICADOR_DIREITO = "Indicador Direito";
    private static String MEDIO_DIREITO = "Médio Direito";
    private static String ANELAR_DIREITO = "Anelar Direito";
    private static String MINIMO_DIREITO = "Mínimo Direito";
    private static String POLEGAR_ESQUERDO = "Polegar Esquerdo";
    private static String INDICADOR_ESQUERDO = "Indicador Esquerdo";
    private static String MEDIO_ESQUERDO = "Médio Esquerdo";
    private static String ANELAR_ESQUERDO = "Anelar Esquerdo";
    private static String MINIMO_ESQUERDO = "Mínimo Esquerdo";

    @Override
    public String execute() throws Exception {
        Long idUsuario = input.getLong("idUsuario");
        Logger.getLogger(ImpressaoDigitalUsuarioSelectAjaxAction.class.getName()).log(Level.SEVERE, null, "teste");
        List<ImpressaoDigital> digitais = ServiceLocator.getImpressaoDigitalEspelhoService().readDigitaisUsuario(idUsuario);
        Map<String, String> mapa = getDedos();
        for (ImpressaoDigital dedo : digitais) {
            if (dedo.getDedo().getId() == 1) {
                mapa.remove(POLEGAR_DIREITO);
            } else if (dedo.getDedo().getId() == 2) {
                mapa.remove(INDICADOR_DIREITO);
            } else if (dedo.getDedo().getId() == 3) {
                mapa.remove(MEDIO_DIREITO);
            } else if (dedo.getDedo().getId() == 4) {
                mapa.remove(ANELAR_DIREITO);
            } else if (dedo.getDedo().getId() == 5) {
                mapa.remove(MINIMO_DIREITO);
            } else if (dedo.getDedo().getId() == 6) {
                mapa.remove(POLEGAR_ESQUERDO);
            } else if (dedo.getDedo().getId() == 7) {
                mapa.remove(INDICADOR_ESQUERDO);
            } else if (dedo.getDedo().getId() == 8) {
                mapa.remove(MEDIO_ESQUERDO);
            } else if (dedo.getDedo().getId() == 9) {
                mapa.remove(ANELAR_ESQUERDO);
            } else if (dedo.getDedo().getId() == 10) {
                mapa.remove(MINIMO_ESQUERDO);
            }
        }
        output.setValue("dedos", mapa);
        return SUCCESS;
    }

    public static Map<String, String> getDedos() {
        Map<String, String> mapa = new LinkedHashMap<String, String>();
        mapa.put(POLEGAR_DIREITO, POLEGAR_DIREITO);
        mapa.put(INDICADOR_DIREITO, INDICADOR_DIREITO);
        mapa.put(MEDIO_DIREITO, MEDIO_DIREITO);
        mapa.put(ANELAR_DIREITO, ANELAR_DIREITO);
        mapa.put(MINIMO_DIREITO, MINIMO_DIREITO);

        mapa.put(POLEGAR_ESQUERDO, POLEGAR_ESQUERDO);
        mapa.put(INDICADOR_ESQUERDO, INDICADOR_ESQUERDO);
        mapa.put(MEDIO_ESQUERDO, MEDIO_ESQUERDO);
        mapa.put(ANELAR_ESQUERDO, ANELAR_ESQUERDO);
        mapa.put(MINIMO_ESQUERDO, MINIMO_ESQUERDO);
        return mapa;
    }
}
