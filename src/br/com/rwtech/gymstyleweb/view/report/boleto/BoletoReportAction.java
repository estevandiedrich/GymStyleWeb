package br.com.rwtech.gymstyleweb.view.report.boleto;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.ConfiguracaoBoleto;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstylecore.model.util.Validador;
import java.math.BigDecimal;
import java.util.Date;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.mentawai.core.BaseAction;

/**
 *
 * @author Software1
 */
public class BoletoReportAction extends BaseAction {

    protected static final String CAMINHO = "/br/com/rwtech/gymstyleweb/view/report/";
    protected String CONSEQUENCE = ERROR;

    @Override
    public String execute() throws Exception {
        Usuario usu = ServiceLocator.getUsuarioService().readById(input.getLong("idUsuario"));
        ConfiguracaoBoleto pojo = ServiceLocator.getConfiguracaoBoletoService().read();
        if (pojo != null && usu != null) {
            byte[] saida = null;
            try {
                Cedente cedente = null;
                /*
                 * INFORMANDO DADOS SOBRE O CEDENTE.
                 */
                cedente = new Cedente(pojo.getCedenteRazaoSocial(), pojo.getCedenteCnpj());

                /*
                 * INFORMANDO DADOS SOBRE O SACADO (Aluno).
                 */
                Sacado sacado = new Sacado(usu.getUsuario(), usu.getCpf());

                // Informando o endereço do sacado.
                Endereco enderecoSac = new Endereco();
                String uf = usu.getUf();
                uf = uf.trim();
                uf = uf.toUpperCase();
                if (uf != null && !uf.isEmpty()) {
                    enderecoSac.setUF(UnidadeFederativa.valueOf(uf));
                } else {
                    enderecoSac.setUF(UnidadeFederativa.MG);
                }
                enderecoSac.setLocalidade(usu.getCidade());
                if (usu.getCep() != null) {
                    enderecoSac.setCep(new CEP(usu.getCep()));
                } else {
                    enderecoSac.setCep(new CEP("000000-00"));
                }
                enderecoSac.setBairro(usu.getBairro());
                enderecoSac.setLogradouro(usu.getEndereco());
                enderecoSac.setNumero("");
                sacado.addEndereco(enderecoSac);

                /*
                 * INFORMANDO DADOS SOBRE O SACADOR AVALISTA.
                 */
//                SacadorAvalista sacadorAvalista = new SacadorAvalista("JRimum Enterprise", "00.000.000/0001-91");
//
//                // Informando o endereço do sacador avalista.
//                Endereco enderecoSacAval = new Endereco();
//                enderecoSacAval.setUF(UnidadeFederativa.DF);
//                enderecoSacAval.setLocalidade("Brasília");
//                enderecoSacAval.setCep(new CEP("59000-000"));
//                enderecoSacAval.setBairro("Grande Centro");
//                enderecoSacAval.setLogradouro("Rua Eternamente Principal");
//                enderecoSacAval.setNumero("001");
//                sacadorAvalista.addEndereco(enderecoSacAval);

                /*
                 * INFORMANDO OS DADOS SOBRE O TÍTULO.
                 */
                // Informando dados sobre a conta bancária do título.
                ContaBancaria contaBancaria = new ContaBancaria(pojo.getBanco().create());
                contaBancaria.setNumeroDaConta(new NumeroDaConta(pojo.getBancoAgenciaInt(), pojo.getBancoAgenciaVariacao()));
                contaBancaria.setCarteira(new Carteira(101));
                contaBancaria.setAgencia(new Agencia(1234, "1"));

//                Titulo titulo = new Titulo(contaBancaria, sacado, cedente, sacadorAvalista);
                Titulo titulo = new Titulo(contaBancaria, sacado, cedente);
                titulo.setNumeroDoDocumento(pojo.getTituloNumeroDoDocumento());
                titulo.setNossoNumero(pojo.getTituloNossoNumero());
                titulo.setDigitoDoNossoNumero(pojo.getTituloDigitoNossoNumero());

                titulo.setDataDoDocumento(new Date());
                titulo.setTipoDeDocumento(pojo.getTituloTipoDocumento());
                titulo.setAceite(pojo.getTituloAceite());
                titulo.setDeducao(BigDecimal.ZERO);
                titulo.setMora(BigDecimal.ZERO);

                titulo.setValor(new BigDecimal(input.getString("valorParcela")));
                titulo.setDesconto(Validador.getBigDecimal(input.getString("desconto")));
                titulo.setAcrecimo(Validador.getBigDecimal(input.getString("multa")));
                titulo.setValorCobrado(Validador.getBigDecimal(input.getString("valorAPagar")));
                titulo.setDataDoVencimento(input.getDate("vencimento.time"));
                //titulo.set

                /*
                 * INFORMANDO OS DADOS SOBRE O BOLETO.
                 */
                Boleto boleto = new Boleto(titulo);
                boleto.setLocalPagamento(pojo.getBoletoLocalPagamento());
                boleto.setInstrucaoAoSacado(pojo.getBoletoInstrucaoSacado());
                boleto.setInstrucao1(pojo.getBoletoInstrucao1());
                boleto.setInstrucao2(pojo.getBoletoInstrucao2());
                boleto.setInstrucao3(pojo.getBoletoInstrucao3());
                boleto.setInstrucao4(pojo.getBoletoInstrucao4());
                boleto.setInstrucao5(pojo.getBoletoInstrucao5());
                boleto.setInstrucao6(pojo.getBoletoInstrucao6());
                boleto.setInstrucao7(pojo.getBoletoInstrucao7());
                boleto.setInstrucao8(pojo.getBoletoInstrucao8());

                /*
                 * GERANDO O BOLETO BANCÁRIO.
                 */
                // Instanciando um objeto "BoletoViewer", classe responsável pela
                // geração do boleto bancário.
                BoletoViewer boletoViewer = new BoletoViewer(boleto);

                // Gerando o arquivo. No caso o arquivo mencionado será salvo na mesma
                // pasta do projeto. Outros exemplos:
                // WINDOWS: boletoViewer.getAsPDF("C:/Temp/MeuBoleto.pdf");
                // LINUX: boletoViewer.getAsPDF("/home/temp/MeuBoleto.pdf");
                //File arquivoPdf = boletoViewer.getPdfAsFile("C:/excluir/" + System.currentTimeMillis() + ".pdf");

                // Mostrando o boleto gerado na tela.
                //mostreBoletoNaTela(arquivoPdf);
                saida = boletoViewer.getPdfAsByteArray();
                //File arquivoPdf = boletoViewer.getPdfAsFile("C:/MeuPrimeiroBoletoBrasil.pdf");
                output.setValue("stream", saida);
                CONSEQUENCE = "PDF";
            } catch (Exception e) {
                e.printStackTrace();
                output.setValue("erro", e.getMessage());
            }
        }
        return CONSEQUENCE;
    }
}
