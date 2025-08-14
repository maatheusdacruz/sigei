package br.vianna.trabalho.sigei.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String descricao;
    private String categoria;
    private LocalDate date;
    @ManyToOne(fetch = FetchType.EAGER)
    private Organizador organizador;
    @ManyToMany
    @JoinTable(name = "evento_participante",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "participante_id"))
    private List<Participante> participantes;

    public Evento(int id, String nome, String descricao, String categoria, LocalDate date, Organizador organizador, List<Participante> participantes) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.date = date;
        this.organizador = organizador;
        this.participantes = participantes;
    }

    public Evento() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Organizador getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void addParticipante(Participante participante) {
        this.participantes.add(participante);
    }
}
