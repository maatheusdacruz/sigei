package br.vianna.trabalho.sigei.controller;

import br.vianna.trabalho.sigei.model.dto.EventoListDTO;
import br.vianna.trabalho.sigei.model.dto.NotificacaoListDTO;
import br.vianna.trabalho.sigei.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/notificacao")
public class NotificacaoController {

    @Autowired
    NotificacaoService nServ;

    @GetMapping("")
    public String lista(Model model){
        List<NotificacaoListDTO> notificacoes = nServ.getNotificoes();
        model.addAttribute("listanotificacoes", notificacoes);
        return "notificacao/list";
    }
}
