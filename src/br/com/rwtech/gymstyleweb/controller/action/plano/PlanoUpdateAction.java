package br.com.rwtech.gymstyleweb.controller.action.plano;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Modalidade;
import br.com.rwtech.gymstylecore.model.pojo.Plano;
import br.com.rwtech.gymstylecore.model.util.Validador;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Éder Faria
 */
public class PlanoUpdateAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        Long id = input.getLong("id");

        String consequence = SHOW;
        List<Modalidade> modalidades = new ArrayList<Modalidade>();
        int tam = input.getInt("tamanho");
        for (int i = 1; i <= tam; i++) {
            Long idModalidade = input.getLong("modalidades" + i);
            if (idModalidade != null && idModalidade != -1) {
                Modalidade mod = ServiceLocator.getModalidadeService().readById(idModalidade);
                if (input.getValue("valor" + i) != null) {
                    mod.setQtdeAcessos(input.getInt("valor" + i));
                }
                modalidades.add(mod);
            }
        }

        if (isPost() && !modalidades.isEmpty()) {
            Plano pojo = (Plano) input.getValue("VOplano");
            pojo.setDescontoReal(Validador.getMoney(input.getString("descontoReal")));
            pojo.setDescontoPercentual(Validador.getInteger(input.getString("descontoPercentual")));
            pojo.setValorMatricula(Validador.getMoney(input.getString("valorMatricula")));
            pojo.setValor(Validador.getMoney(input.getString("valor")));
            pojo.setValorTotal(Validador.getMoney(input.getString("valorTotal")));
            pojo.setModalidades(modalidades);
            ServiceLocator.getPlanoService().update(pojo);
            session.setAttribute("mensagem", "Plano alterado com sucesso!");

            consequence = SUCCESS;
        } else {
            if (isPost() && modalidades.isEmpty()) {
                output.setValue("modalidadesError", "Selecione ao menos uma modalidade!");
            } else {
                PlanoAction.preload(output);
                Plano pojo = ServiceLocator.getPlanoService().readById(id);
                output.setValue("pojo", pojo);
                List<Modalidade> listaModalidades = ServiceLocator.getModalidadeService().readByCriteria(new HashMap<String, Object>());
                output.setValue("lista", listaModalidades);
                if (pojo.getDescontoPercentual() > 0) {
                    output.setValue("formaDeDesconto2", true);
                } else if (pojo.getDescontoReal() > 0) {
                    output.setValue("formaDeDesconto1", true);
                } else {
                    output.setValue("formaDeDesconto1", true);
                }

                if (listaModalidades != null && pojo.getModalidades() != null && equalList(listaModalidades, pojo.getModalidades())) {
//                if (listaModalidades != null && pojo.getModalidades() != null) {
                    output.setValue("todos", true);
                    //se todos estao chekados eu zero o chek
                    for (int i = 0; i < listaModalidades.size(); i++) {
                        int j = i + 1;
                        String chek = "chek";
                        output.setValue(1 + chek + j, false);
                        output.setValue(2 + chek + j, false);
                        output.setValue(3 + chek + j, false);
                        output.setValue(4 + chek + j, false);
                        output.setValue(5 + chek + j, false);
                        output.setValue(6 + chek + j, false);
                        output.setValue(7 + chek + j, false);
                    }
                    for (int i = 0; i < pojo.getModalidades().size(); i++) {
                        Modalidade mod = pojo.getModalidades().get(i);
                        //System.out.println("---------- " + mod.getQtdeAcessos() + "chek" + (i + 1));
                        if (mod.getAtivo()) {
                            output.setValue(mod.getQtdeAcessos() + "chek" + (i + 1), true);
                            setValorModalidade((i + 1), mod);
                        }
                    }
                }

                if (listaModalidades != null && pojo.getModalidades() != null) {
                    //primeiro for anda por todas as modalidades
                    for (int i = 0; i < listaModalidades.size(); i++) {
                        //aqui ele varre a lista dos checados no banco
                        for (int j = 0; j < pojo.getModalidades().size(); j++) {
                            Modalidade mod = pojo.getModalidades().get(j);
                            //se na lista de modalidades for igual a uma modalidade q tem no plano
                            //cheka a modalidade
                            if (listaModalidades.get(i).getId().equals(mod.getId())) {
                                setFalseChekLinha(i + 1);
                                output.setValue("checados" + (i + 1), true);
                                output.setValue(mod.getQtdeAcessos() + "chek" + (i + 1), true);
                                //System.out.println("---------- " + mod.getQtdeAcessos() + "chek" + (i + 1));
                                setValorModalidade((i + 1), mod);
                                break;
                            }
                        }
                    }
                }
                if (pojo.getModalidades() != null && pojo.getModalidades().size() > 0) {
                    List<Modalidade> modalidadesOff = new ArrayList<Modalidade>();
                    for (int j = 0; j < pojo.getModalidades().size(); j++) {
                        Modalidade mod = pojo.getModalidades().get(j);
                        if (!mod.getAtivo()) {
                            modalidadesOff.add(mod);
                        }
                    }
                    output.setValue("modalidadesOff", modalidadesOff);
                }
            }
        }
        return consequence;
    }

    public Boolean equalList(List<Modalidade> lista1, List<Modalidade> lista2) {
        if (lista1 != null && lista2 != null) {
            if (lista1.size() == lista2.size()) {
                int tam = lista2.size();
                for (Modalidade mod1 : lista1) {

                    for (int i = 0; i < lista2.size(); i++) {
                        Modalidade mod2 = lista2.get(i);
                        if (mod1.getModalidade().equalsIgnoreCase(mod2.getModalidade())) {
                            tam--;
                        }
                    }
                }
                if (tam == 0) {
                    return Boolean.TRUE;
                }
            }
        }

        return Boolean.FALSE;
    }

    public void setFalseChekLinha(int j) {
        String chek = "chek";
        output.setValue(1 + chek + j, false);
        output.setValue(2 + chek + j, false);
        output.setValue(3 + chek + j, false);
        output.setValue(4 + chek + j, false);
        output.setValue(5 + chek + j, false);
        output.setValue(6 + chek + j, false);
        output.setValue(7 + chek + j, false);
    }

    public void setValorModalidade(int linha, Modalidade mod) {
        String valor = "valorFinal" + linha;
        switch (mod.getQtdeAcessos()) {
            case 1:
                output.setValue(valor, mod.getValor1());
                break;
            case 2:
                output.setValue(valor, mod.getValor2());
                break;
            case 3:
                output.setValue(valor, mod.getValor3());
                break;
            case 4:
                output.setValue(valor, mod.getValor4());
                break;
            case 5:
                output.setValue(valor, mod.getValor5());
                break;
            case 6:
                output.setValue(valor, mod.getValor6());
                break;
            case 7:
                output.setValue(valor, mod.getValor7());
                break;
        }
    }
}