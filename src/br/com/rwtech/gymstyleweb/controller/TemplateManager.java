package br.com.rwtech.gymstyleweb.controller;

import org.mentawai.template.ConventionController;
import org.mentawai.template.Page;

public class TemplateManager extends org.mentawai.template.TemplateManager {

    @Override
    public void configurePages() {
        Page page = new Page(".*", "jsp/template/template.jsp", ConventionController.class);
        page.setBlock("header", new Page("jsp/template/header.jsp"));
        page.setBlock("menu", new Page("jsp/template/menu.jsp"));
        page.setBlock("menuEsquerdo", new Page("jsp/template/menuEsquerdo.jsp"));
        page.setBlock("footer", new Page("jsp/template/footer.jsp"));
        this.add(page);
    }
}
