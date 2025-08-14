package br.vianna.trabalho.sigei.model.dto;

public class NotificacaoListDTO {
    private int id;
    private String mensagem;
    private String nomeParticipante;

    public NotificacaoListDTO() {
    }

    public NotificacaoListDTO(int id, String mensagem, String nomeParticipante) {
        this.id = id;
        this.mensagem = mensagem;
        this.nomeParticipante = nomeParticipante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeParticipante() {
        return nomeParticipante;
    }

    public void setNomeParticipante(String nomeParticipante) {
        this.nomeParticipante = nomeParticipante;
    }
}
