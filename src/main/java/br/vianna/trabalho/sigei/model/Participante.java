package br.vianna.trabalho.sigei.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Participante extends Usuario {

//    private Agenda agenda;
    @ManyToMany(mappedBy = "participantes")
    private List<Evento> inscricoes;
    @OneToMany(mappedBy = "participante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notificacao> notificacoes;
    public Participante() {
    }

    public Participante(int id, String nome, String email, String login, String senha, List<Evento> inscricoes, List<Notificacao> notificacoes) {
        super(id, nome, email, login, senha);
        this.inscricoes = inscricoes;
        this.notificacoes = notificacoes;
    }

    //    public Agenda getAgenda() {
//        return agenda;
//    }
//
//    public void setAgenda(Agenda agenda) {
//        this.agenda = agenda;
//    }

    public List<Notificacao> getNotificacoes() {
        return notificacoes;
    }

    public void addNotificacao(Notificacao notificacao) {
        this.notificacoes.add(notificacao);
    }

    public List<Evento> getInscricoes() {
        return inscricoes;
    }

    public void addInscricao(Evento inscricao) {
        this.inscricoes.add(inscricao);
    }
}
