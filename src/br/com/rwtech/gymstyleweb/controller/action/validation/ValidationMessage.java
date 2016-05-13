package br.com.rwtech.gymstyleweb.controller.action.validation;

import br.com.rwtech.gymstylecore.model.ServiceLocator;
import br.com.rwtech.gymstylecore.model.pojo.Usuario;
import br.com.rwtech.gymstyleweb.controller.ac.Ac;

public class ValidationMessage {

    private final static String IMAGE = "<img src='images/alert.png' title='Campo Obrigatório!'/>";

    public static String getCampoObrigatorio() {
        return "Campo Obrigatório" + IMAGE;
    }

    public static String getImageObrigatorio(String title) {
        return getImage(title);
    }

    public static String getImage(String title) {
        return "<img src='images/alert.png' title='" + title + "!'/>";
    }

    public static String getMensagem(String mensagem) {
        return (mensagem + "<img src='images/alert.png'/>");
    }

    public static String getImageCampoObrigatorio() {
        return IMAGE;
    }

    public static String getObrigatorio() {
        return "Obrigatório" + IMAGE;
    }

    public static String getCampoMinimo6Maximo52Caracteres() {
        return "Digite de 6 á 52 caracteres" + IMAGE;
    }

    public static String getInformeMinimo3() {
        return "Informe no mínimo 3 caracteres" + IMAGE;
    }

    public static String getInformeAte5() {
        return "Informe no máximo 5 caracteres" + IMAGE;
    }

    public static String getInformeValorEntre0a30() {
        return "Informe  valor entre 0 a 31" + IMAGE;
    }

    public static String getLoginExistente() {
        return "Login existente" + IMAGE;
    }

    public static String getCadastroExistente() {
        return "Cadastro existente" + IMAGE;
    }

    public static String getCodigoExistente() {
        return "Código existente" + IMAGE;
    }

    public static String getCampoMinimo2Maximo25Caracteres() {
        return "Digite de 2 á 25 caracteres" + IMAGE;
    }

    public static String getCampoMaximo42Caracteres() {
        return "Digite até 42 caracteres" + IMAGE;
    }

    public static String getSenhaDiferentes() {
        return "Senhas não conferem" + IMAGE;
    }

    public static String getInformeGrupoMuscular() {
        return "Informe ao menos um grupo muscular" + IMAGE;
    }

    public static String getInformeTreino() {
        return "Informe Treino" + IMAGE;
    }

    public static String getInformeTelefoneValido() {
        return "Informe Telefone Válido" + IMAGE;
    }

    public static String getInformeHorarioValido() {
        return "Informe Horário Válido" + IMAGE;
    }

    public static String getInformeHorario() {
        return "Informe Horário " + IMAGE;
    }

    public static String getInformeNomeValido() {
        return "Informe Nome Válido" + IMAGE;
    }

    public static String getInformeTelefoneCelular() {
        return "Informe Celular Válido" + IMAGE;
    }

    public static String getInformeSexo() {
        return "Informe Sexo" + IMAGE;
    }

    public static String getPreenchaTodoCampo() {
        return "Preencha todo o campo" + IMAGE;
    }

    public static String getInformeDataInferiorDataAtual() {
        return "Informe data inferior a data atual" + IMAGE;
    }

    public static String getInformeDataSuperiorDataAtual() {
        return "Informe data superior a data atual" + IMAGE;
    }

    public static String getDataInvalida() {
        return "Data Inválida" + IMAGE;
    }

    public static String getCpfInvalido() {
        return "CPF inválido" + IMAGE;
    }

    public static String getEmailInvalido() {
        return "Email inválido" + IMAGE;
    }

    public static String getCpfExistente() {
        return "CPF existente" + IMAGE;
    }

    public static String getCartaoDeOutroAluno(String cartao, Long id) {
        Usuario usuario = ServiceLocator.getUsuarioService().readUserByCartaoProximidade(cartao, id);
        String aux = "Cartão associado para "
                + "<a class='errors2' style='height:30px;' href='"
                + (usuario.isAluno() ? Ac.USUARIO_UPDATE_INFORMACAO : Ac.FUNCIONARIO_UPDATE_INFORMACAO)
                + ".do?id=" + usuario.getId() + "' title='Selecione para visualizar o cadastro!'>"
                + "<br>"
                + (usuario.isAluno() ? " Aluno / " : "")
                + ((usuario.getIsAdm() || usuario.getIsSecretaria() || usuario.getIsInstrutor()) ? " Funcionário / " : "")
                + usuario.getUsuario()
                + "</a> " + IMAGE;
        return aux;
    }

    public static String getInformeMatricula() {
        return "Informe Matrícula " + IMAGE;
    }

    public static String getInformeCpf() {
        return "Informe CPF " + IMAGE;
    }

    public static String getInformeValorNumerico() {
        return "Informe valor numérico" + IMAGE;
    }

    public static String getInformeValido() {
        return "Informe valor válido" + IMAGE;
    }

    public static String getInformeAoMenosUmaParcela() {
        return "Informe ao menos uma parcela " + IMAGE;
    }
}
