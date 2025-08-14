package br.vianna.trabalho.sigei.config.security.model;

import br.vianna.trabalho.sigei.model.Organizador;
import br.vianna.trabalho.sigei.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserLogado implements UserDetails {

    private String login, senha, nome;
    private int id;
    private ArrayList<GrantedAuthority> autorizacoes;

    public UserLogado(Usuario u){
        login = u.getLogin();
        senha = u.getSenha();
        nome = u.getNome();
        id = u.getId();
        autorizacoes = new ArrayList<>();
        if (u instanceof Organizador){
            autorizacoes.add( new SimpleGrantedAuthority("ROLE_ORGANIZADOR"));
            if (u.getLogin().equals("admin")){
                autorizacoes.add( new SimpleGrantedAuthority("ROLE_ADMIN"));
                autorizacoes.add( new SimpleGrantedAuthority("CAN_FILTER"));
            }
        }else{
            autorizacoes.add( new SimpleGrantedAuthority("ROLE_PARTICIPANTE"));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return autorizacoes;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
