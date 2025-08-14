package br.vianna.trabalho.sigei.service;

import br.vianna.trabalho.sigei.model.Participante;
import br.vianna.trabalho.sigei.model.dto.ParticipanteListDTO;
import br.vianna.trabalho.sigei.model.repository.ParticipanteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipanteService {

    @Autowired
    ParticipanteDAO pDao;

    @Autowired
    PasswordEncoder pass;


    public List<ParticipanteListDTO> getParticipantes(String pesquisa) {

        if (pesquisa == null) {
            return pDao.getParticipantes();
        }else{
            return pDao.getParticipantes("%"+pesquisa+"%");
        }
    }

    public void save(Participante cliente) {
        cliente.setSenha(pass.encode(cliente.getSenha()));
        pDao.save(cliente);
    }

    public Participante getParticipante(int id) {

        return pDao.findById(id).get();
    }
}
