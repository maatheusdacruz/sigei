package br.vianna.trabalho.sigei.model.dto;

import java.time.LocalDate;

public class EventoListDTO {
    private int id;
    private String nome, descricao, categoria;
    private LocalDate date;
    private String nomeOrganizador;

    public EventoListDTO(int id, String nome, String descricao, String categoria, LocalDate date, String nomeOrganizador) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.date = date;
        this.nomeOrganizador = nomeOrganizador;
    }

    public EventoListDTO() {
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

    public String getNomeOrganizador() {
        return nomeOrganizador;
    }

    public void setNomeOrganizador(String nomeOrganizador) {
        this.nomeOrganizador = nomeOrganizador;
    }
}
