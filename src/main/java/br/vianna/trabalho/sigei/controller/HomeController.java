package br.vianna.trabalho.sigei.controller;


import br.vianna.trabalho.sigei.config.security.model.UserLogado;
import br.vianna.trabalho.sigei.model.Evento;
import br.vianna.trabalho.sigei.model.Participante;
import br.vianna.trabalho.sigei.model.dto.EventoListDTO;
import br.vianna.trabalho.sigei.model.repository.ParticipanteDAO;
import br.vianna.trabalho.sigei.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    EventoService eServ;

    @Autowired
    ParticipanteDAO pDao;

    @GetMapping({"","/home"})
    public String goHome(UsernamePasswordAuthenticationToken auth, Model model, @AuthenticationPrincipal UserLogado user){;
        List<EventoListDTO> eventos = eServ.getEventos(null);

        model.addAttribute("eventos", eventos);
        if (user.getAuthorities().toString().contains("ROLE_PARTICIPANTE")) {
            Participante p = pDao.findById(user.getId()).orElse(null);
            if (p != null) {
                List<EventoListDTO> inscritos = eServ.getEventosPorParticipante(p.getId());
                List<Integer> idinscritos = new ArrayList<>();
                for (EventoListDTO inscrito : inscritos) {
                    idinscritos.add(inscrito.getId());
                }
                model.addAttribute("idinscritos", idinscritos);
            }
        }
        return "principal";
    }

    @GetMapping("login")
    public String goLogin(){
        return "login";
    }

    @GetMapping("/card/{id}")
    public String verCard(@PathVariable int id, Model model, @AuthenticationPrincipal UserLogado user) {
        Evento evento = eServ.getEvento(id);

        model.addAttribute("evento", evento);
        if (user.getAuthorities().toString().contains("ROLE_PARTICIPANTE")) {
            Participante p = pDao.findById(user.getId()).orElse(null);
            if (p != null) {
                boolean estaInscrito = evento.getParticipantes().contains(p);
                model.addAttribute("estaInscrito", estaInscrito);
            }
        }
        return "/card";
    }

}
