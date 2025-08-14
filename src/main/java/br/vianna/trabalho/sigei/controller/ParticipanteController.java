package br.vianna.trabalho.sigei.controller;

import br.vianna.trabalho.sigei.model.Participante;
import br.vianna.trabalho.sigei.model.dto.ParticipanteListDTO;
import br.vianna.trabalho.sigei.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/participante")
public class ParticipanteController {

    @Autowired
    ParticipanteService pServ;

    @GetMapping("")
    public String lista(Model model, @RequestParam(required = false) String cpPesq){

        List<ParticipanteListDTO> participantes = pServ.getParticipantes(cpPesq);
        model.addAttribute("lista", participantes);
        return "participante/list";
    }

    @GetMapping("/novo")
    public String novo(Model model){
        Participante cli = new Participante();
        cli.setNome("abc");

        model.addAttribute("participante", cli);
        return "participante/novo";
    }

    @PostMapping("")
    public String saveParticipante(@ModelAttribute Participante participante ){

//        System.out.println(participante.getNome());
        pServ.save(participante);

        return "redirect:/participante";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable int id){

        Participante cli = pServ.getParticipante(id);
        model.addAttribute("participante", cli);
        return "participante/novo";
    }

}
