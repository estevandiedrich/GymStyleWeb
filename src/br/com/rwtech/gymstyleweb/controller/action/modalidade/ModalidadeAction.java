package br.com.rwtech.gymstyleweb.controller.action.modalidade;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Dispositivo;
import br.com.rwtech.gymstylecore.model.pojo.Modalidade;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mentawai.core.Output;

/**
 *
 * @author Éder Faria
 */
public class ModalidadeAction {

    public static void preload(Output output, Long id) {
        output.setValue("horarios", ServiceLocator.getPerfilAcessoService().readList());
        Map<Long, String> mapaDispositivos = ServiceLocator.getDispositivoService().readListImages();

        if (id != null && id != -1) {
            Modalidade pojo = ServiceLocator.getModalidadeService().readById(id);
            Map<Long, String> dispositivosOffLine = new HashMap<Long, String>();
            if (pojo.getPerfilAcesso() != null && pojo.getPerfilAcesso().getFaixas() != null) {
                if (pojo.getPerfilAcesso().getFaixas().size() > 0) {
                    List<Dispositivo> dispositivos = pojo.getPerfilAcesso().getFaixas().get(0).getDispositivos();
                    int i = 0;
                    for (Dispositivo dis : dispositivos) {
                        if (!mapaDispositivos.containsKey(dis.getId())) {
                            dispositivosOffLine.put(dis.getId(), ((dis.getDispositivo() == null || dis.getDispositivo().isEmpty()) ? dis.getEnderecoIp() : dis.getDispositivo()));
                        }
                        i++;
                    }
                }
            }
            output.setValue("dispositivosOffLine", dispositivosOffLine);
        }
        output.setValue("dispositivos", mapaDispositivos);
    }
}
