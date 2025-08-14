package br.vianna.trabalho.sigei.controller;

import br.vianna.trabalho.sigei.config.security.model.UserLogado;
import br.vianna.trabalho.sigei.model.Evento;
import br.vianna.trabalho.sigei.model.Organizador;
import br.vianna.trabalho.sigei.model.dto.EventoListDTO;
import br.vianna.trabalho.sigei.model.dto.OrganizadorListDTO;
import br.vianna.trabalho.sigei.service.EventoService;
import br.vianna.trabalho.sigei.service.OrganizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    EventoService eServ;

    @Autowired
    OrganizadorService oServ;

    @GetMapping("")
    public String lista(Model model, @RequestParam(required = false) String cpPesq){

        List<EventoListDTO> eventos = eServ.getEventos(cpPesq);
        model.addAttribute("lista", eventos);
        return "evento/list";
    }

    @GetMapping("/novo")
    public String novo(Model model){
        Evento evento = new Evento();
        model.addAttribute("evento", evento);
        List<OrganizadorListDTO> organizadores = oServ.getOrganizadores(null);
        model.addAttribute("organizadores", organizadores);
        return "evento/novo";
    }

    @PostMapping("")
    public String saveEvento(
            @ModelAttribute Evento evento,
            @AuthenticationPrincipal UserLogado usuarioLogado,
            @RequestParam(name = "idOrganizador", required = false) Integer idOrganizador ){

        Organizador organizador;

        if (idOrganizador != null) {
            organizador = oServ.getOrganizador(idOrganizador);
        } else {
            organizador = oServ.getOrganizador(usuarioLogado.getId());
        }

        evento.setOrganizador(organizador);
        eServ.save(evento);

        return "redirect:/evento";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable int id){

        Evento evento = eServ.getEvento(id);
        model.addAttribute("evento", evento);
        List<OrganizadorListDTO> organizadores = oServ.getOrganizadores(null);
        model.addAttribute("organizadores", organizadores);
        return "evento/novo";
    }


}
