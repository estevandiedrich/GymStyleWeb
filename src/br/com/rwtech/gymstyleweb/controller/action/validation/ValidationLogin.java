package br.com.rwtech.gymstyleweb.controller.action.validation;

import org.mentawai.core.*;
import org.mentawai.filter.*;
import org.mentawai.rule.*;
import org.mentawai.validation.*;

public class ValidationLogin extends ValidationFilter {

    public void prepareValidator(Validator val, Action action, String innerAction) {
        val.add("username", new RequiredFieldRule(), ValidationMessage.getImageCampoObrigatorio());
        val.add("password", new RequiredFieldRule(), ValidationMessage.getImageCampoObrigatorio());
    }
}
