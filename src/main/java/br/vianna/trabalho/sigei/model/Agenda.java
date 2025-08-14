package br.vianna.trabalho.sigei.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Participante participante;

    public Agenda(int id, Participante participante) {
        this.id = id;
        this.participante = participante;
    }

    public Agenda() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }


}
