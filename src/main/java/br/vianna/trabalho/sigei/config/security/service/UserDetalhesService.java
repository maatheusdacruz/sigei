package br.vianna.trabalho.sigei.config.security.service;


import br.vianna.trabalho.sigei.config.security.model.UserLogado;
import br.vianna.trabalho.sigei.model.Usuario;
import br.vianna.trabalho.sigei.model.repository.ParticipanteDAO;
import br.vianna.trabalho.sigei.model.repository.OrganizadorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetalhesService implements UserDetailsService {

    @Autowired
    OrganizadorDAO oDao;
    @Autowired
    ParticipanteDAO pDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = oDao.findByLogin(username);
        if (user == null){
            user = pDao.findByLogin(username);
            if (user == null){
                throw new UsernameNotFoundException("Usuário incorreto");
            }
        }

        return new UserLogado(user);
    }
}
