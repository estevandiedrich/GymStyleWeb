package br.com.rwtech.gymstyleweb.controller.action.pagamentos.autenticacao;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Dispositivo;
import br.com.rwtech.gymstylecore.model.pojo.Pagamento;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class PagamentoAutenticacaoRead extends BaseAction {

    @Override
    public String execute() throws Exception {
        String consequence = SUCCESS;

        if (input.getValue("criterioAutenticacao") == null) {
            consequence = SHOW;
        }
        String autenticacao = input.getString("criterioAutenticacao");
        if (autenticacao != null && !autenticacao.isEmpty()) {
            autenticacao = autenticacao.replaceAll("_", "");
            if (autenticacao.length() >= 29) {
                String mac = autenticacao.substring(0, 19);
                mac = getMacConvertido(mac);
                String idParcela = autenticacao.substring(20, autenticacao.length());
                Dispositivo dis = ServiceLocator.getDispositivoService().readByMac(mac);
                if (dis != null) {
                    Pagamento pag = ServiceLocator.getPagamentoService().readById(getIdConvertido(idParcela));
                    output.setValue("pag", pag);
                    if (pag != null && pag.getPagamento() != null) {
                        output.setValue("mac", mac);
                        output.setValue("dispositivo", dis);
                        if (pag != null) {
                            output.setValue("idPlanoUsuario", ServiceLocator.getPagamentoService().readIdPlanoUsuarioByIdPagamento(pag.getId()));
                        }
                    }
                }
            }
        }
        return consequence;
    }

    public String getMacConvertido(String mac) {
        if (mac != null && !mac.isEmpty() && mac.length() == 19) {
            String hex1 = Integer.toHexString(Integer.valueOf(mac.substring(0, 3)));
            String hex2 = Integer.toHexString(Integer.valueOf(mac.substring(3, 6)));
            String hex3 = Integer.toHexString(Integer.valueOf(mac.substring(6, 9)));
            String hex4 = Integer.toHexString(Integer.valueOf(mac.substring(10, 13)));
            String hex5 = Integer.toHexString(Integer.valueOf(mac.substring(13, 16)));
            String hex6 = Integer.toHexString(Integer.valueOf(mac.substring(16, 19)));

            if (hex1.length() < 1) {
                hex1 = "00";
            } else if (hex1.length() < 2) {
                hex1 = "0" + hex1;
            }

            if (hex2.length() < 1) {
                hex2 = "00";
            } else if (hex2.length() < 2) {
                hex2 = "0" + hex2;
            }

            if (hex3.length() < 1) {
                hex3 = "00";
            } else if (hex3.length() < 2) {
                hex3 = "0" + hex3;
            }

            if (hex4.length() < 1) {
                hex4 = "00";
            } else if (hex1.length() < 2) {
                hex4 = "0" + hex4;
            }

            if (hex5.length() < 1) {
                hex5 = "00";
            } else if (hex5.length() < 2) {
                hex5 = "0" + hex5;
            }

            if (hex6.length() < 1) {
                hex6 = "00";
            } else if (hex6.length() < 2) {
                hex6 = "0" + hex6;
            }

            mac = (hex1 + "-" + hex2 + "-" + hex3 + "-" + hex4 + "-" + hex5 + "-" + hex6);
        }
        return mac;
    }

    public Long getIdConvertido(String id) {
        id = id.replaceAll("-", "");
        id = id.replaceAll("-", "");
        id = id.replaceAll("-", "");
        id = id.replaceAll("-", "");
        id = id.replaceAll("-", "");
        id = id.replaceAll("-", "");
        id = id.replaceAll("-", "");
        id = id.replaceAll("-", "");
        id = id.replaceAll("-", "");

        id = id.replaceAll(" ", "");
        id = id.replaceAll(" ", "");
        id = id.replaceAll(" ", "");
        id = id.replaceAll(" ", "");
        id = id.replaceAll(" ", "");
        id = id.replaceAll(" ", "");
        id = id.replaceAll(" ", "");
        id = id.replaceAll(" ", "");
        id = id.replaceAll(" ", "");

        id = id.replaceAll("_", "");
        id = id.replaceAll("_", "");
        id = id.replaceAll("_", "");
        id = id.replaceAll("_", "");
        id = id.replaceAll("_", "");
        id = id.replaceAll("_", "");
        id = id.replaceAll("_", "");
        id = id.replaceAll("_", "");
        id = id.replaceAll("_", "");

        Long idParcela = null;
        if (id != null && !id.isEmpty()) {
            idParcela = new Long(id);
        } else {
            idParcela = new Long(0);
        }
        return idParcela;
    }
}
