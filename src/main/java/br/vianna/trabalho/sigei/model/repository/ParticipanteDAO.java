package br.vianna.trabalho.sigei.model.repository;

import br.vianna.trabalho.sigei.model.Participante;
import br.vianna.trabalho.sigei.model.dto.ParticipanteListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ParticipanteDAO extends JpaRepository<Participante, Integer> {

    static String pkg = "br.vianna.trabalho.sigei.model.dto.";

    @Query("select new "+pkg+"ParticipanteListDTO(c.id, c.nome, c.email) " +
            "from Participante c ")
    List<ParticipanteListDTO> getParticipantes();

    @Query("select new "+pkg+"ParticipanteListDTO(c.id, c.nome, c.email) " +
            "from Participante c " +
            "where c.nome like :nome ")
    List<ParticipanteListDTO> getParticipantes(String nome);

//    public List<Cliente> findByContaingNome(String nome);

    public Participante findByLoginAndSenha(String login, String senha);

    public List<Participante> findByLoginIgnoreCase(String login);


    Participante findByLogin(String username);

    @Query("SELECT DISTINCT p FROM Participante p " +
            "JOIN p.inscricoes i " +
            "WHERE i.date = :hoje")
    List<Participante> findParticipantesComEventosHoje(@Param("hoje") LocalDate hoje);
}
