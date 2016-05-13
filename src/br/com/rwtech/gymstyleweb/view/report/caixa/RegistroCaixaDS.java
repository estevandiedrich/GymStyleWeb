/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rwtech.gymstyleweb.view.report.caixa;

import br.com.rwtech.gymstylecore.model.pojo.report.RegistroCaixaReport;
import java.util.Iterator;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Software1
 */
public class RegistroCaixaDS implements JRDataSource {

    private Iterator<RegistroCaixaReport> iterator;
    private RegistroCaixaReport cursor;

    public RegistroCaixaDS(List<RegistroCaixaReport> lista) {
        super();
        iterator = lista.iterator();
    }

    @Override
    public boolean next() throws JRException {
        boolean retorno = iterator.hasNext();
        if (retorno) {
            cursor = iterator.next();
        }
        return retorno;
    }

    @Override
    public Object getFieldValue(JRField nome) throws JRException {
        RegistroCaixaReport pojo = cursor;
        if (nome.getName().equals("dia")) {
            return pojo.getDia();
        }
        if (nome.getName().equals("entrada")) {
            return pojo.getEntrada();
        }
        if (nome.getName().equals("retirada")) {
            return pojo.getRetirada();
        }
        if (nome.getName().equals("saldoAcumulado")) {
            return pojo.getSaldoAcumulado();
        }
        if (nome.getName().equals("saldoDia")) {
            return pojo.getSaldoDia();
        }
        if (nome.getName().equals("saldoInicial")) {
            return pojo.getSaldoInicial();
        }
        if (nome.getName().equals("acimaDataAtual")) {
            return pojo.getAcimaDataAtual();
        }
        return null;
    }
}
