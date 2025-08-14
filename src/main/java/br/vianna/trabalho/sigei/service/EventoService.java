package br.vianna.trabalho.sigei.service;

import br.vianna.trabalho.sigei.model.Evento;
import br.vianna.trabalho.sigei.model.Participante;
import br.vianna.trabalho.sigei.model.dto.EventoListDTO;
import br.vianna.trabalho.sigei.model.repository.EventoDAO;
import br.vianna.trabalho.sigei.model.repository.ParticipanteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    EventoDAO eDao;

    @Autowired
    ParticipanteDAO pDao;

    public List<EventoListDTO> getEventos(String pesquisa) {

        if (pesquisa == null) {
            return eDao.getEventos();
        }else{
            return eDao.getEventosPorNome("%"+pesquisa+"%");
        }
    }


    public List<EventoListDTO> getEventosPorParticipante(int idParticipante) {
        return eDao.getEventosPorParticipante(idParticipante);
    }

    public List<EventoListDTO> getEventosPorParticipanteEMesAno(int idParticipante, int mes, int ano) {
        return eDao.getEventosPorParticipanteEMesAno(idParticipante, mes, ano);
    }

    public void save(Evento evento) {
        //validação

        eDao.save(evento);
    }

    public Evento getEvento(int id) {

        return eDao.findById(id).get();
    }

    public boolean alternarInscricao(int id, int eventoId) {

        Participante p = pDao.findById(id).orElseThrow();
        Evento e = eDao.findById(eventoId).orElseThrow();

        if (e.getParticipantes().contains(p)){
            e.getParticipantes().remove(p);
            eDao.save(e);
            return false;
        } else{
            e.getParticipantes().add(p);
            eDao.save(e);
            return true;
        }
    }
}
