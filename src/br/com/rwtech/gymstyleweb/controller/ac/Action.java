package br.com.rwtech.gymstyleweb.controller.ac;

import org.mentawai.core.ApplicationManager;

/**
 *
 * @author Éder Faria
 */
public abstract class Action extends Ac {

    protected ApplicationManager am;

    protected abstract void createActions();
    
}