package br.vianna.trabalho.sigei.model.repository;

import br.vianna.trabalho.sigei.model.Notificacao;
import br.vianna.trabalho.sigei.model.dto.EventoListDTO;
import br.vianna.trabalho.sigei.model.dto.NotificacaoListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificacaoDAO extends JpaRepository<Notificacao, Integer> {
    static String pkg = "br.vianna.trabalho.sigei.model.dto.";

    @Query("select new "+pkg+"NotificacaoListDTO(n.id, n.mensagem, n.participante.nome) " +
            "from Notificacao n")
    List<NotificacaoListDTO> getNotificacoes();
}
