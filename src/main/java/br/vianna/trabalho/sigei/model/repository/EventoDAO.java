package br.vianna.trabalho.sigei.model.repository;

import br.vianna.trabalho.sigei.model.Evento;
import br.vianna.trabalho.sigei.model.dto.EventoListDTO;
import br.vianna.trabalho.sigei.model.dto.ParticipanteListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventoDAO extends JpaRepository<Evento, Integer> {

    static String pkg = "br.vianna.trabalho.sigei.model.dto.";

    @Query("select new "+pkg+"EventoListDTO(e.id, e.nome, e.descricao, e.categoria, e.date, e.organizador.nome) " +
            "from Evento e")
    List<EventoListDTO> getEventos();

    @Query("select new "+pkg+"EventoListDTO(e.id, e.nome, e.descricao, e.categoria, e.date, e.organizador.nome) " +
            "from Evento e " +
            "where e.nome like :nome ")
    List<EventoListDTO> getEventosPorNome(String nome);

    @Query("select new "+pkg+"EventoListDTO(e.id, e.nome, e.descricao, e.categoria, e.date, e.organizador.nome) " +
            "from Evento e " +
            "where e.organizador.nome like :organizador")
    List<EventoListDTO> getEventosPorOrganizador(String organizador);

    @Query("select new "+pkg+"EventoListDTO(e.id, e.nome, e.descricao, e.categoria, e.date, e.organizador.nome) " +
            "from Evento e " +
            "join e.participantes p " +
            "where p.id = :idParticipante")
    List<EventoListDTO> getEventosPorParticipante(int idParticipante);

    @Query("select new "+pkg+"EventoListDTO(e.id, e.nome, e.descricao, e.categoria, e.date, e.organizador.nome) " +
            "from Evento e " +
            "join e.participantes p " +
            "where p.id = :idParticipante " +
            "and FUNCTION('MONTH', e.date) = :mes " +
            "and FUNCTION('YEAR', e.date) = :ano")
    List<EventoListDTO> getEventosPorParticipanteEMesAno(int idParticipante, int mes, int ano);





}
