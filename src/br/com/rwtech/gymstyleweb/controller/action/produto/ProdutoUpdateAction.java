package br.com.rwtech.gymstyleweb.controller.action.produto;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Produto;
import br.com.rwtech.gymstylecore.model.util.Validador;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;
import org.apache.commons.fileupload.FileItem;
import org.mentawai.core.BaseAction;
import org.mentawai.core.StreamConsequence;

/**
 *
 * @author Eder Faria
 */
public class ProdutoUpdateAction extends BaseAction {

    static final long serialVersionUID = 1L;
    private byte[] foto = null;

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;
        Long id = input.getLong("id");
        if (isPost()) {
            Produto pojo = (Produto) input.getValue(Ac.VO + Ac.PRODUTO);
            pojo.setPrecoCusto(Validador.getMoney(input.getString("valorCusto")));
            pojo.setPrecoVenda(Validador.getMoney(input.getString("valorVenda")));
            pojo.setCategoria(ServiceLocator.getCategoriaService().readById(input.getLong("categoriaFk")));
            pojo.setFoto(foto);
            pojo.setAlertaEstoqueMinimo(input.getBoolean("alerta"));
            ServiceLocator.getProdutoService().update(pojo);
            session.setAttribute("mensagem", "Produto alterado com sucesso!");
            consequence = SUCCESS;
        } else {
            Produto pojo = ServiceLocator.getProdutoService().readById(id);
            output.setValue("pojo", pojo);
            output.setValue("alerta", pojo.getAlertaEstoqueMinimo());
            output.setValue("categoriaFk", (pojo != null && pojo.getCategoria() != null ? pojo.getCategoria().getId() : ""));

            if (pojo.getPrecoCusto() != null) {
                output.setValue("valorCusto", Validador.getMoney(pojo.getPrecoCusto()));
            }
            if (pojo.getPrecoVenda() != null) {
                output.setValue("valorVenda", Validador.getMoney(pojo.getPrecoVenda()));
            }

            foto = null;
            if (pojo.getFoto() != null) {
                foto = pojo.getFoto();
                adhere();
                output.setValue(ProdutoAction.FOTO, Ac.PRODUTO_UPDATE + ".preview.do");
            } else {
                output.setValue(ProdutoAction.FOTO, ProdutoAction.IMAGES);
            }
        }
        return consequence;
    }
    private String PREVIEW = Ac.PRODUTO_UPDATE + ".preview.do";

    public String definir() {
        FileItem objImageFile = (FileItem) input.getValue("imagem");
        if (objImageFile != null) {
            foto = objImageFile.get();
            adhere();
            output.setValue(ProdutoAction.FOTO, PREVIEW);
            return SUCCESS;
        } else if (objImageFile == null && foto != null) {
            adhere();
            output.setValue(ProdutoAction.FOTO, PREVIEW);
            return SUCCESS;
        }

        if (foto != null) {
            output.setValue(ProdutoAction.FOTO, PREVIEW);
        } else {
            output.setValue(ProdutoAction.FOTO, ProdutoAction.IMAGES);
        }
        return SUCCESS;
    }

    public String preview() {
        output.setValue(StreamConsequence.STREAM_KEY, foto);
        output.setValue(StreamConsequence.CONTENT_LENGTH_KEY, foto.length);
//        output.setValue(StreamConsequence.STREAM, foto);
//        output.setValue(StreamConsequence.CONTENT_LENGTH, foto.length);
        return SUCCESS;
    }
}