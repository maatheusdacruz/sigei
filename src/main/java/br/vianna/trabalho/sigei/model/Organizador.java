package br.vianna.trabalho.sigei.model;

import jakarta.persistence.Entity;

@Entity
public class Organizador extends Usuario {

//    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Atendimento> atendimentos = new ArrayList<>();



    public Organizador() {
    }

    public Organizador(int id, String nome, String email, String login, String senha) {
        super(id, nome, email, login, senha);
    }

}
