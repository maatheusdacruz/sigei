package br.vianna.trabalho.sigei.service;

import br.vianna.trabalho.sigei.model.Organizador;
import br.vianna.trabalho.sigei.model.Participante;
import br.vianna.trabalho.sigei.model.dto.OrganizadorListDTO;
import br.vianna.trabalho.sigei.model.dto.ParticipanteListDTO;
import br.vianna.trabalho.sigei.model.repository.OrganizadorDAO;
import br.vianna.trabalho.sigei.model.repository.ParticipanteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizadorService {

    @Autowired
    OrganizadorDAO oDao;

    @Autowired
    PasswordEncoder pass;

    public List<OrganizadorListDTO> getOrganizadores(String pesquisa) {
        if (pesquisa == null) {
            return oDao.getOrganizadores();
        }else{
            return oDao.getOrganizadores("%"+pesquisa+"%");
        }
    }

    public void save(Organizador organizador) {
        organizador.setSenha(pass.encode(organizador.getSenha()));

        oDao.save(organizador);
    }

    public Organizador getOrganizador(int id) {
        return oDao.findById(id).get();
    }
}
