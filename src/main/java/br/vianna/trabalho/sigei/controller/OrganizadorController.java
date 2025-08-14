package br.vianna.trabalho.sigei.controller;

import br.vianna.trabalho.sigei.model.Organizador;
import br.vianna.trabalho.sigei.model.dto.OrganizadorListDTO;
import br.vianna.trabalho.sigei.service.OrganizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/organizador")
public class OrganizadorController {

    @Autowired
    OrganizadorService oServ;

    @GetMapping("")
    public String lista(Model model, @RequestParam(required = false) String cpPesq){

        List<OrganizadorListDTO> organizadores = oServ.getOrganizadores(cpPesq);
        model.addAttribute("lista", organizadores);
        return "organizador/list";
    }

    @GetMapping("/novo")
    public String novo(Model model){
        Organizador organizador = new Organizador();
        organizador.setNome("abc");
        model.addAttribute("organizador", organizador);
        return "organizador/novo";
    }

    @PostMapping("")
    public String saveOrganizador(@ModelAttribute Organizador organizador ){

//        System.out.println(organizador.getNome());
        oServ.save(organizador);

        return "redirect:/organizador";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable int id){

        Organizador organizador = oServ.getOrganizador(id);
        model.addAttribute("organizador", organizador);
        return "organizador/novo";
    }

}
