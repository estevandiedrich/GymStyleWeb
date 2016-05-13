package br.com.rwtech.gymstyleweb.controller.action.avaliacaofisica;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import java.util.Calendar;
import org.mentawai.core.Input;
import org.mentawai.core.Output;

/**
 *
 * @author Éder Faria
 */
public class AvaliacaoFisicaAction {

    public static void preload(Output output, Input input) {
        output.setValue("selecionado", "avaliacaoFisicaRead");

        Long id = input.getLong("id");
        if (id != null || id > 0) {
            Usuario usuario = ServiceLocator.getUsuarioService().readById(id);
            output.setValue("usuario", usuario);
            if (usuario.getDataNascimento() != null) {
                output.setValue("idade", calculaIdade(usuario.getDataNascimento()));
            }
        }
        output.setValue("formulas", ServiceLocator.getProtocoloService().readList());
    }

    public static int calculaIdade(Calendar dateNascimento) {
        Calendar dataAtual = Calendar.getInstance();
        int age = dataAtual.get(Calendar.YEAR) - dateNascimento.get(Calendar.YEAR);
        dateNascimento.add(Calendar.YEAR, age);

        if (dataAtual.before(dateNascimento)) {
            age--;
        }
        return age;
    }
}