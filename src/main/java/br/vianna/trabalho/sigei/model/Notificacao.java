package br.vianna.trabalho.sigei.model;

import jakarta.persistence.*;

@Entity
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String mensagem;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Participante participante;

    public Notificacao(int id, String mensagem, Participante participante) {
        this.id = id;
        this.mensagem = mensagem;
        this.participante = participante;
    }

    public Notificacao() {
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

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }


}
