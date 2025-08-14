package br.vianna.trabalho.sigei.controller;

import br.vianna.trabalho.sigei.config.security.model.UserLogado;
import br.vianna.trabalho.sigei.model.Evento;
import br.vianna.trabalho.sigei.model.Participante;
import br.vianna.trabalho.sigei.model.dto.DiaCalendarioDTO;
import br.vianna.trabalho.sigei.model.dto.EventoListDTO;
import br.vianna.trabalho.sigei.service.EventoService;
import br.vianna.trabalho.sigei.service.NotificacaoService;
import br.vianna.trabalho.sigei.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/inscricao")
public class InscricaoController {

    @Autowired
    EventoService eServ;

    @Autowired
    ParticipanteService pServ;

    @GetMapping("")
    public String lista(Model model,
                        @RequestParam(required = false) String cpPesq,
                        @AuthenticationPrincipal UserLogado usuarioLogado){

        Participante p = pServ.getParticipante(usuarioLogado.getId());
        Integer i = p.getId();
        List<EventoListDTO> inscricoes = eServ.getEventosPorParticipante(i);
        model.addAttribute("listainscricoes", inscricoes);
        return "inscricao/list";
    }

    @GetMapping("/agenda")
    public String agendaDoParticipante(@AuthenticationPrincipal UserLogado usuarioLogado,
                                       @RequestParam(required = false) Integer mes,
                                       @RequestParam(required = false) Integer ano,
                                       Model model) {
        if (mes == null || ano == null) {
            LocalDate hoje = LocalDate.now();
            mes = hoje.getMonthValue();
            ano = hoje.getYear();
        }

        int idParticipante = usuarioLogado.getId();
        List<EventoListDTO> eventos = eServ.getEventosPorParticipanteEMesAno(idParticipante, mes, ano);

        // Organizar eventos por dia
        Map<Integer, List<EventoListDTO>> eventosPorDia = new HashMap<>();
        for (EventoListDTO evento : eventos) {
            int dia = evento.getDate().getDayOfMonth();
            eventosPorDia.computeIfAbsent(dia, k -> new ArrayList<>()).add(evento);
        }

        // Montar o calendário (semanas e dias)
        YearMonth ym = YearMonth.of(ano, mes);
        LocalDate primeiroDia = ym.atDay(1);
        int totalDias = ym.lengthOfMonth();

        List<List<DiaCalendarioDTO>> calendario = new ArrayList<>();
        List<DiaCalendarioDTO> semana = new ArrayList<>();

        int diaSemana = primeiroDia.getDayOfWeek().getValue(); // 1 = segunda, 7 = domingo
        diaSemana = diaSemana % 7; // ajustar para 0 = domingo

        // Preencher dias vazios no início
        for (int i = 0; i < diaSemana; i++) {
            semana.add(new DiaCalendarioDTO(0, new ArrayList<>()));
        }

        for (int dia = 1; dia <= totalDias; dia++) {
            List<EventoListDTO> evts = eventosPorDia.getOrDefault(dia, new ArrayList<>());
            semana.add(new DiaCalendarioDTO(dia, evts));
            if (semana.size() == 7) {
                calendario.add(semana);
                semana = new ArrayList<>();
            }
        }

        // preencher com dias vazios no final
        if (!semana.isEmpty()) {
            while (semana.size() < 7) {
                semana.add(new DiaCalendarioDTO(0, new ArrayList<>()));
            }
            calendario.add(semana);
        }

        List<String> meses = List.of(
                "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
                "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
        );

        model.addAttribute("meses", meses);
        model.addAttribute("calendario", calendario);
        model.addAttribute("mes", mes);
        model.addAttribute("ano", ano);
        return "inscricao/agenda";
    }

    @PostMapping("/inscrever")
    public String inscrever(@RequestParam int eventoId,
                            @AuthenticationPrincipal UserLogado user) {
        eServ.alternarInscricao(user.getId(), eventoId);

        return "redirect:/";
    }



}
