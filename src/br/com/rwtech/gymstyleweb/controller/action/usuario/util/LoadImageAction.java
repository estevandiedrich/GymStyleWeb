package br.com.rwtech.gymstyleweb.controller.action.usuario.util;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import org.mentawai.core.BaseAction;
import org.mentawai.core.StreamConsequence;

public class LoadImageAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        Long id = input.getLong("id");
        Usuario usuario = ServiceLocator.getUsuarioService().readById(id);
        if (usuario != null && usuario.getFoto() != null) {
            byte[] image = usuario.getFoto();
            output.setValue(StreamConsequence.STREAM_KEY, image);
            output.setValue(StreamConsequence.CONTENT_LENGTH_KEY, image.length);
//            output.setValue(StreamConsequence.STREAM, image);
//            output.setValue(StreamConsequence.CONTENT_LENGTH, image.length);
        }
        return SUCCESS;
    }
}