package br.com.rwtech.gymstyleweb.controller.action.produto;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.Output;

/**
 *
 * @author Eder Faria
 */
public class ProdutoAction {

    public static String FOTO = "foto";
    public static String IMAGES = "images/semFoto.png";

    public static void preload(Output output) {
        output.setValue("categorias", ServiceLocator.getCategoriaService().readList());
    }
}