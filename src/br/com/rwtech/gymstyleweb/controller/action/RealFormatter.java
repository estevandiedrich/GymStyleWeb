package br.com.rwtech.gymstyleweb.controller.action;

import java.text.NumberFormat;
import java.util.Locale;
import org.mentawai.formatter.Formatter;

/**
 *
 * @author Éder Faria
 */
public class RealFormatter implements Formatter {

    public RealFormatter() {
    }

    public String format(Object obj, Locale loc) {
        String real = obj.toString();
        if (real != null && real.length() > 0) {
            NumberFormat formatMoney = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            return formatMoney.format(Double.parseDouble(real)).replaceAll("R\\$\\ ", "");
        }
        return "";
    }
}
