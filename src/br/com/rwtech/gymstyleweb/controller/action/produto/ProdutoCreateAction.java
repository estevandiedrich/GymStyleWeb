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
public class ProdutoCreateAction extends BaseAction {

    static final long serialVersionUID = 1L;
    private byte[] foto = null;

    @Override
    public String execute() throws Exception {
        String consequence = SHOW;
        if (isPost()) {
            Produto pojo = (Produto) input.getValue(Ac.VO + Ac.PRODUTO);
            pojo.setPrecoCusto(Validador.getMoney(input.getString("valorCusto")));
            pojo.setPrecoVenda(Validador.getMoney(input.getString("valorVenda")));
            pojo.setCategoria(ServiceLocator.getCategoriaService().readById(input.getLong("categoriaFk")));
            pojo.setAlertaEstoqueMinimo(input.getBoolean("alerta"));
            pojo.setFoto(foto);
            ServiceLocator.getProdutoService().create(pojo);
            session.setAttribute("mensagem", "Produto criado com sucesso!");
            consequence = SUCCESS;
        } else {
            foto = null;
            output.setValue("codigo", ServiceLocator.getProdutoService().readNextCodigo());
            ProdutoAction.preload(output);
            output.setValue("alerta", true);
        }
        return consequence;
    }
    private String PREVIEW = Ac.PRODUTO_CREATE + ".preview.do";

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
        output.setValue(ProdutoAction.FOTO, ProdutoAction.IMAGES);
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