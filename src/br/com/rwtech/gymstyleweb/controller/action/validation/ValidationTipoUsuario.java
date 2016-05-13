package br.com.rwtech.gymstyleweb.controller.action.validation;

import org.mentawai.core.*;
import org.mentawai.filter.*;
import org.mentawai.rule.*;
import org.mentawai.validation.*;

public class ValidationTipoUsuario extends ValidationFilter {

    public void prepareValidator(Validator val, Action action, String innerAction) {
        val.add("tipoUsuario", new RequiredFieldRule(), ValidationMessage.getCampoObrigatorio());
       // val.add("tipoUsuario", new StringRule(6, 30), ValidationMessage.getCampoMinimo6Maximo52Caracteres());
    }
}
