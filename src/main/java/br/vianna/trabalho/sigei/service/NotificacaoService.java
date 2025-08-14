package br.vianna.trabalho.sigei.service;

import br.vianna.trabalho.sigei.model.Evento;
import br.vianna.trabalho.sigei.model.Notificacao;
import br.vianna.trabalho.sigei.model.Participante;
import br.vianna.trabalho.sigei.model.dto.EventoListDTO;
import br.vianna.trabalho.sigei.model.dto.NotificacaoListDTO;
import br.vianna.trabalho.sigei.model.repository.EventoDAO;
import br.vianna.trabalho.sigei.model.repository.NotificacaoDAO;
import br.vianna.trabalho.sigei.model.repository.ParticipanteDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificacaoService {

    private final ParticipanteDAO participanteDAO;

    @Autowired
    NotificacaoDAO nDao;

    public NotificacaoService(ParticipanteDAO participanteDAO) {
        this.participanteDAO = participanteDAO;
    }

    public List<NotificacaoListDTO> getNotificoes() {
        return nDao.getNotificacoes();
    }

    public void verificarEventosHoje() {
        LocalDate hoje = LocalDate.now();
        List<Participante> participantes = participanteDAO.findParticipantesComEventosHoje(hoje);

        for(Participante participante : participantes) {
            for(Evento inscricao : participante.getInscricoes()){
                if(hoje.equals(inscricao.getDate())){
                    String mensagem = "Evento " + inscricao.getNome() + " ocorre hoje!";
                    Notificacao notificacao = new Notificacao(0, mensagem, participante);
                    participante.addNotificacao(notificacao);
                    nDao.save(notificacao);
                }
            }
        }
    }
}