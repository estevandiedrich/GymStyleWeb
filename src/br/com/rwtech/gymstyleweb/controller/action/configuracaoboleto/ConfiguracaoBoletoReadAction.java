/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rwtech.gymstyleweb.controller.action.configuracaoboleto;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Software1
 */
public class ConfiguracaoBoletoReadAction extends BaseAction{

    @Override
    public String execute() throws Exception {
        output.setValue("pojo", ServiceLocator.getConfiguracaoBoletoService().read());
        return SUCCESS;
    }
}
