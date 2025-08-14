package br.vianna.trabalho.sigei.model.repository;

import br.vianna.trabalho.sigei.model.Organizador;
import br.vianna.trabalho.sigei.model.dto.OrganizadorListDTO;
import br.vianna.trabalho.sigei.model.dto.ParticipanteListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrganizadorDAO extends JpaRepository<Organizador, Integer> {

    static String pkg = "br.vianna.trabalho.sigei.model.dto.";

    public Organizador findByNome(String nome);

    public Organizador findByLoginAndSenha(String login, String senha);


    Organizador findByLogin(String username);


    @Query("select new "+pkg+"OrganizadorListDTO(o.id, o.nome, o.email) " +
            "from Organizador o ")
    List<OrganizadorListDTO> getOrganizadores();

    @Query("select new "+pkg+"OrganizadorListDTO(o.id, o.nome, o.email) " +
            "from Organizador o " +
            "where o.nome like :nome")
    List<OrganizadorListDTO> getOrganizadores(String nome);
}
